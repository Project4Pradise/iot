package com.ruoyi.web.controller.mqtt;

import com.ruoyi.common.core.domain.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping(value = "/publishTopic")
    @ApiOperation("mqtt发布")
    public AjaxResult publishTopic(Integer qos, boolean retained, String topic, String pushMessage){
        //System.out.println("Received qos: " + qos);  // 添加调试语句
        mqttPushClient.publish(qos,retained,topic,pushMessage);
        return AjaxResult.success();
    }
    @GetMapping(value = "/receiveTopic")
    @ApiOperation("接受mqtt")
    public AjaxResult receiveTopic(){

        return AjaxResult.success(pushCallback.receive());
    }
}
