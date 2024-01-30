package com.ruoyi.utils.mqtt.control;

import com.alibaba.fastjson2.JSONObject;

import com.ruoyi.barrier.domain.SysWindbarrier;
import com.ruoyi.barrier.mapper.SysWindbarrierMapper;
import com.ruoyi.utils.mqtt.MqttPushClient;
import com.ruoyi.utils.mqtt.PushCallback;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("barrier")
public class BarrierControl {
    @Autowired
    private PushCallback pushCallback;
    @Autowired
    private SysWindbarrierMapper windbarrierMapper;
    @Autowired
    private MqttPushClient mqttPushClient;

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
        JSONObject message = pushCallback.receive();
        JSONObject msgJson = message.getJSONObject("msg");
        long id = msgJson.getLong("id");
        if(windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend()==1){
            float current = msgJson.getFloat("current");
            float RaiseBarrierCurrentThreshold = windbarrierMapper.selectSysWindbarrierById(id).getRaiseBarrierCurrentThreshold();

            if (current >= RaiseBarrierCurrentThreshold) {
                JSONObject sendMsg=new JSONObject();
                sendMsg.put("id",id);
                sendMsg.put("working","false");
                windbarrierMapper.updateIsWorkingById(id,false);
                mqttPushClient.publish(1,false,"iot/state",sendMsg.toString());
            }
        }

    }
    //降障电流判断
    public  void FallBarrierCurrentThreshold() {
        JSONObject message = pushCallback.receive();
        JSONObject msgJson = message.getJSONObject("msg");
        long id = msgJson.getLong("id");
        if(windbarrierMapper.selectSysWindbarrierById(id).getWorkingTrend()==2){
            float current = msgJson.getFloat("current");
            float RaiseBarrierCurrentThreshold = windbarrierMapper.selectSysWindbarrierById(id).getFallBarrierCurrentThreshold();

            if (current >= RaiseBarrierCurrentThreshold) {
                JSONObject sendMsg=new JSONObject();
                sendMsg.put("id",id);
                sendMsg.put("working","false");
                windbarrierMapper.updateIsWorkingById(id,false);
                mqttPushClient.publish(1,false,"iot/state",sendMsg.toString());
            }
        }

    }
    //电机到位发送信息
    public void sendRaiseComplete(){
        JSONObject message = pushCallback.receive();
        JSONObject msgJson = message.getJSONObject("msg");
        long id = msgJson.getLong("id");

    }


}
