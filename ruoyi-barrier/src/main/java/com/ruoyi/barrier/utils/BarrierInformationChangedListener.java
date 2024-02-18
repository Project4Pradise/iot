package com.ruoyi.barrier.utils;

import com.ruoyi.common.utils.listener.BarrierInformationChangedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BarrierInformationChangedListener implements ApplicationListener<BarrierInformationChangedEvent> {
    @Override
    public void onApplicationEvent(BarrierInformationChangedEvent event) {
        boolean isRetrying = event.getisRetrying();
        // 在用户信息变化时触发的逻辑，可以使用 userId 和 newName 进行处理
        if(isRetrying==true){
        //
        }
    }




}
