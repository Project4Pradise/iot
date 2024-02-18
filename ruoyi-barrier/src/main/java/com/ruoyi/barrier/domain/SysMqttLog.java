package com.ruoyi.barrier.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.context.annotation.Bean;

/**
 * mqtt日志对象 sys_mqtt_log
 * 
 * @author ruoyi
 * @date 2024-02-04
 */
public class SysMqttLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 日志 */
    @Excel(name = "日志")
    private String log;

    private long barrierId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLog(String log)
    {
        this.log = log;
    }

    public String getLog()
    {
        return log;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("log", getLog())
                .append("cabinet",getBarrierId())
            .toString();
    }

    public long getBarrierId() {
        return barrierId;
    }

    public void setBarrierId(long cabinetId) {
        this.barrierId = cabinetId;
    }
}
