package com.ruoyi.utils.mqtt.control;

import com.alibaba.fastjson2.JSONObject;

import com.ruoyi.barrier.domain.SysMqttLog;
import com.ruoyi.barrier.domain.SysWindbarrier;
import com.ruoyi.barrier.mapper.SysMqttLogMapper;
import com.ruoyi.barrier.mapper.SysWindbarrierMapper;
import com.ruoyi.utils.mqtt.MqttPushClient;
import com.ruoyi.utils.mqtt.PushCallback;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component("barrier")
public class BarrierControl {
    @Autowired
    private PushCallback pushCallback;
    @Autowired
    private SysWindbarrierMapper windbarrierMapper;
    @Autowired
    private MqttPushClient mqttPushClient;
    @Autowired
    private SysMqttLogMapper mqttLogMapper;




    public void updateCurrent() {
        JSONObject message = pushCallback.receive();

            JSONObject msgJson = message.getJSONObject("msg");
            long id = msgJson.getLong("id");
            float current = msgJson.getFloat("current");
/*                System.out.println("Received id: " + id);
                System.out.println("Received current value: " + current);*/
            windbarrierMapper.updateCurrentById(id, current);


    }
//升障电流判断
    public  void RaiseBarrierCurrentThreshold() {

        JSONObject msg=getMsg();
        long id=msg.getLong("id");
        JSONObject  sendMsg=initSendMsg(id);
        if(windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend()==1){
            float current = msg.getFloat("current");

            float RaiseBarrierCurrentThreshold = windbarrierMapper.selectSysWindbarrierById(id).getRaiseBarrierCurrentThreshold();
            System.out.println(windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend());
            System.out.println(windbarrierMapper.selectSysWindbarrierById(id).getRetryCurrentCount());
            if (current >= RaiseBarrierCurrentThreshold) {
                sendMsg.put("working","false");
                sendMsg.put("retrying","true");
                sendMsg.put("log","超过升障电流 开始重试");
                Map<String,Object> paraMap=new HashMap<>();
                windbarrierMapper.updateIsWorkingById(id,false);
                SysWindbarrier sysWindbarrier = new SysWindbarrier();
                sysWindbarrier.setId(id); // 设置需要更新的记录的id
                sysWindbarrier.setIsRetrying(true);
                System.out.println(windbarrierMapper.selectSysWindbarrierById(id).getRetryCurrentCount());
                sysWindbarrier.setRetryCurrentCount(windbarrierMapper.selectSysWindbarrierById(id).getRetryCurrentCount());
                System.out.println(windbarrierMapper.selectSysWindbarrierById(id).getRetryCurrentCount());
                sysWindbarrier.setWorkingTrend(windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend());
                windbarrierMapper.updateSysWindbarrier(sysWindbarrier);
                mqttPushClient.publish(1,false,"iot/barrier",sendMsg.toString());
            }
        }

    }
    //降障电流判断
    public  void FallBarrierCurrentThreshold() {

        JSONObject msg=getMsg();
        long id=msg.getLong("id");
        JSONObject  sendMsg=initSendMsg(id);
        if(windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend()==2){
            float current = msg.getFloat("current");
            float FallBarrierCurrentThreshold = windbarrierMapper.selectSysWindbarrierById(id).getFallBarrierCurrentThreshold();

            if (current >= FallBarrierCurrentThreshold) {
                sendMsg.put("working","false");
                sendMsg.put("retrying","true");
                sendMsg.put("log","超过降障电流 开始重试");
                windbarrierMapper.updateIsWorkingById(id,false);
                SysWindbarrier sysWindbarrier = new SysWindbarrier();
                sysWindbarrier.setId(id); // 设置需要更新的记录的id

                sysWindbarrier.setIsRetrying(true);
                sysWindbarrier.setRetryCurrentCount(windbarrierMapper.selectSysWindbarrierById(id).getRetryCurrentCount());
                sysWindbarrier.setWorkingTrend(windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend());
                windbarrierMapper.updateSysWindbarrier(sysWindbarrier);
                mqttPushClient.publish(1,false,"iot/barrier",sendMsg.toString());
            }
        }

    }
    //电机到位发送信息
    public void sendWokingComplete(){
        JSONObject msg=getMsg();
        long id=msg.getLong("id");
        JSONObject  sendMsg=initSendMsg(id);
        int workingTrend=msg.getIntValue("workingTrend");
        SysWindbarrier sysWindbarrier = new SysWindbarrier();
        if(workingTrend==4&&windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend()==2){
            sysWindbarrier.setId(id); // 设置需要更新的记录的id
            sysWindbarrier.setWorkingTrend(workingTrend); // 设置新的WorkingTrend的值
            sendMsg.put("log","下降到位");
            sysWindbarrier.setRetryCurrentCount(windbarrierMapper.selectSysWindbarrierById(id).getRetryCurrentCount());
            sysWindbarrier.setWorkingTrend(workingTrend);
            windbarrierMapper.updateSysWindbarrier(sysWindbarrier);
            mqttPushClient.publish(1,false,"iot/system",sendMsg.toString());
        } else if (workingTrend==3&&windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend()==1) {
            sysWindbarrier.setId(id); // 设置需要更新的记录的id
            sysWindbarrier.setWorkingTrend(workingTrend); // 设置新的WorkingTrend的值
            sendMsg.put("log","上升到位");
            sysWindbarrier.setRetryCurrentCount(windbarrierMapper.selectSysWindbarrierById(id).getRetryCurrentCount());
            sysWindbarrier.setWorkingTrend(workingTrend);
            windbarrierMapper.updateSysWindbarrier(sysWindbarrier);
            mqttPushClient.publish(1,false,"iot/system",sendMsg.toString());
        }


    }
    public void isretrying(){
        JSONObject msg=getMsg();
        long id=msg.getLong("id");
        JSONObject  sendMsg=initSendMsg(id);
        boolean isretrying=msg.getBoolean("retrying");
        int retrycount=windbarrierMapper.selectSysWindbarrierById(id).getRetryCurrentCount();
        long retryMaxCount=windbarrierMapper.selectSysWindbarrierById(id).getRetryCount();
        int workingTrend=windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend();
        SysWindbarrier sysWindbarrier = new SysWindbarrier();
        float current = msg.getFloat("current");
        if(isretrying==true&&retrycount<=retryMaxCount){
            if(workingTrend==1){
                if(current>windbarrierMapper.selectSysWindbarrierById(id).getRaiseBarrierRetryCurrentThreshold()){
                    sysWindbarrier.setRetryCurrentCount(++retrycount);
                    sysWindbarrier.setId(id);
                    sysWindbarrier.setWorkingTrend(windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend());
                    //windbarrierMapper.selectSysWindbarrierById(id).setRetryCurrentCount(retrycount++);
                    sendMsg.put("log","超过上升重试阈值，已重试"+windbarrierMapper.selectSysWindbarrierById(id).getRetryCurrentCount()+"次");
                    sendMsg.put("retrying","true");
                    sendMsg.put("workingTrend","1");
                    windbarrierMapper.updateSysWindbarrier(sysWindbarrier);
                    mqttPushClient.publish(1,false,"iot/barrier",sendMsg.toString());

                }
            }
            if(workingTrend==2){
                if(current>windbarrierMapper.selectSysWindbarrierById(id).getFallBarrierRetryCurrentThreshold()){
                    sysWindbarrier.setRetryCurrentCount(retrycount++);
                    sysWindbarrier.setId(id);
                    sysWindbarrier.setWorkingTrend(windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend());
                    sendMsg.put("log","超过下降重试阈值，已重试"+retrycount+"次");
                    sendMsg.put("retrying","true");
                    sendMsg.put("workingTrend","2");
                    windbarrierMapper.updateSysWindbarrier(sysWindbarrier);
                    mqttPushClient.publish(1,false,"iot/barrier",sendMsg.toString());
                }
            }
        }else if (isretrying==true){
            sysWindbarrier.setRetryCurrentCount(0);
            sysWindbarrier.setId(id);
            sysWindbarrier.setIsRetrying(false);
            sysWindbarrier.setIsWorking(false);
            windbarrierMapper.updateSysWindbarrier(sysWindbarrier);
            sendMsg.put("log","超过重试次数，停止重试");
            sendMsg.put("retrying","false");
            mqttPushClient.publish(1,false,"iot/barrier",sendMsg.toString());
            mqttPushClient.publish(1,false,"iot/system",sendMsg.toString());
        }

    }
    private JSONObject initSendMsg(long id){
        JSONObject sendMsg= new JSONObject();
        sendMsg.put("id",id);

        sendMsg.put("current",windbarrierMapper.selectSysWindbarrierById(id).getCurrentCurrent());
        sendMsg.put("working",windbarrierMapper.selectSysWindbarrierById(id).getIsWorking());
        sendMsg.put("retrying",windbarrierMapper.selectSysWindbarrierById(id).getIsRetrying());
        sendMsg.put("workingTrend",windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend());
        sendMsg.put("log",null);
        return sendMsg;
    }

    private JSONObject getMsg(){
        JSONObject message = pushCallback.receive();
        JSONObject msgJson = message.getJSONObject("msg");
        return msgJson;
    }
    public void logInsert(){
       JSONObject msg=getMsg();
        long id=msg.getLong("id");
        String log=msg.getString("log");
        String final_log=id+"号风障日志："+log;
        SysMqttLog mqttLog=new SysMqttLog();
        mqttLog.setLog(final_log);
        mqttLog.setBarrierId(id);
        mqttLogMapper.insertSysMqttLog(mqttLog);


    }


}
