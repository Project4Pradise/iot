package com.ruoyi.web.controller.mqtt;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttUtils;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.utils.mqtt.PushCallback;
import com.ruoyi.utils.mqtt.MqttPushClient;
@RestController
@RequestMapping("/mqtt")
@Api("mqtt测试")
public class MqttController {
    @Autowired
    private MqttPushClient mqttPushClient;
    @Autowired
    private PushCallback pushCallback;

    @PostMapping(value = "/publishTopic")
    @ApiOperation("mqtt发布")
    public AjaxResult publishTopic(@RequestBody MqttMessage mqttMessage,@RequestParam String topic){
        //System.out.println("Received qos: " + qos);  // 添加调试语句
        int qos=mqttMessage.getQos();
        boolean retained=mqttMessage.isRetained();
        String pushMessage=mqttMessage.toString();

        mqttPushClient.publish(qos,retained,topic,pushMessage);
        return AjaxResult.success();
    }
    @GetMapping(value = "/receiveTopic")
    @ApiOperation("接受mqtt")
    public AjaxResult receiveTopic(){

        return AjaxResult.success(pushCallback.receive());
    }
}
