package com.ruoyi.utils.mqtt.control;

import com.alibaba.fastjson2.JSONObject;

import com.ruoyi.barrier.domain.SysWindbarrier;
import com.ruoyi.barrier.mapper.SysWindbarrierMapper;
import com.ruoyi.utils.mqtt.MqttPushClient;
import com.ruoyi.utils.mqtt.PushCallback;
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

    public  void RaiseBarrierCurrentThreshold() {
        JSONObject message = pushCallback.receive();
        JSONObject msgJson = message.getJSONObject("msg");
        long id = msgJson.getLong("id");
        float current = msgJson.getFloat("current");
        float RaiseBarrierCurrentThreshold = windbarrierMapper.selectSysWindbarrierById(id).getRaiseBarrierCurrentThreshold();

        if (current >= RaiseBarrierCurrentThreshold) {
            JSONObject sendMsg=new JSONObject();
            sendMsg.put("Working","false");
            windbarrierMapper.updateIsWorkingById(id,false);
            mqttPushClient.publish(2,false,"iot/state",sendMsg.toString());
        }
    }
}
