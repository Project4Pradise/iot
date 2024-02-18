package com.ruoyi.common.utils.listener;

import org.springframework.context.ApplicationEvent;

public class BarrierInformationChangedEvent extends ApplicationEvent {
    private boolean isRetrying;
    private int id;


    // 构造函数名称应该与类名相同，且不带返回类型
    public BarrierInformationChangedEvent(Object source, boolean isRetrying,int id) {
        super(source); // 调用父类构造函数，传递事件源
        this.isRetrying = isRetrying;
        this.id=id;
    }

    public boolean getisRetrying() {
        return isRetrying;
    }
}
