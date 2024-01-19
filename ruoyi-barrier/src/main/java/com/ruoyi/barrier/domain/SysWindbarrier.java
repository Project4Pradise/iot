package com.ruoyi.barrier.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.context.annotation.Bean;

/**
 * 风障参数对象 sys_windbarrier
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
public class SysWindbarrier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String name;

    /** 风障运行高度 */
    @Excel(name = "风障运行高度")
    private Long CurrentHeight;
    /** 风障运行高度 */
    @Excel(name = "电机当前电流")
    private Long CurrentCurrent;
    /** 所属分组id */
    @Excel(name = "所属分组id")
    private Long GroupId;

    /** 区域Id */
    @Excel(name = "区域Id")
    private Long AreaId;

    /** 所属的通信柜id（16片） */
    @Excel(name = "所属的通信柜id")
    private Long CommunicationCabinetId;

    /** 所属的控制柜id（四片） */
    @Excel(name = "所属的控制柜id")
    private Long ControlCabinetId;
    /** 是否工作（工作为1，停运为0） */
    @Excel(name = "是否工作", readConverterExp = "1=工作，0=停运")
    private Boolean isWorking;
    /** 是否工作（工作为1，停运为0） */
    @Excel(name = "工作趋势", readConverterExp = "1=上升，2=下降，0=停运")
    private int WorkingTrend;

    /** 是否在重试（0为不在重试，1为在重试） */
    @Excel(name = "是否在重试", readConverterExp = "0=不在重试，1=在重试")
    private Boolean isRetrying;

    /** 升障电流超过阈值后电机停行 */
    @Excel(name = "升障电流阈值")
    private Long RaiseBarrierCurrentThreshold;

    /** 降障电流超过阈值后电机停行 */
    @Excel(name = "降障电流阈值")
    private Long FallBarrierCurrentThreshold;

    /** 升障时间超过阈值后电机停行 */
    @Excel(name = "升障时间阈值")
    private Long RaiseBarrierTimeThreshold;

    /** 降障时间超过阈值后电机停行 */
    @Excel(name = "降障时间阈值")
    private Long FallBarrierTimeThreshold;

    /** 升降过程过流/超时停止后重试次数 */
    @Excel(name = "最大重试次数")
    private Long RetryCount;

    /** 重试升障电流超过阈值后电机停运，重试电流阈值大于电流阈值 */
    @Excel(name = "重试升障电流阈值（重试电流阈值大于电流阈值）")
    private Long RaiseBarrierRetryCurrentThreshold;

    /** 重试降障电流超过阈值后电机停运，重试电流阈值大于电流阈值 */
    @Excel(name = "重试降障电流阈值（重试电流阈值大于电流阈值）")
    private Long FallBarrierRetryCurrentThreshold;

    /** 重试升障超时后电机停运 */
    @Excel(name = "升障重试时间阈值")
    private Long RaiseBarrierRetryTimeThreshold;

    /** 重试降障超时后电机停运 */
    @Excel(name = "降障重试时间阈值")
    private Long FallBarrierRetryTimeThreshold;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCurrentHeight(Long CurrentHeight) 
    {
        this.CurrentHeight = CurrentHeight;
    }

    public Long getCurrentHeight() 
    {
        return CurrentHeight;
    }
    public void setGroupId(Long GroupId) 
    {
        this.GroupId = GroupId;
    }

    public Long getGroupId() 
    {
        return GroupId;
    }
    public void setAreaId(Long AreaId) 
    {
        this.AreaId = AreaId;
    }

    public Long getAreaId() 
    {
        return AreaId;
    }
    public void setCommunicationCabinetId(Long CommunicationCabinetId) 
    {
        this.CommunicationCabinetId = CommunicationCabinetId;
    }

    public Long getCommunicationCabinetId() 
    {
        return CommunicationCabinetId;
    }
    public void setControlCabinetId(Long ControlCabinetId) 
    {
        this.ControlCabinetId = ControlCabinetId;
    }

    public Long getControlCabinetId() 
    {
        return ControlCabinetId;
    }
    public void setIsWorking(Boolean isWorking)
    {
        this.isWorking = isWorking;
    }

    public Boolean getIsWorking()
    {
        return isWorking;
    }
    public void setIsRetrying(Boolean isRetrying)
    {
        this.isRetrying = isRetrying;
    }

    public Boolean getIsRetrying()
    {
        return isRetrying;
    }
    public void setRaiseBarrierCurrentThreshold(Long RaiseBarrierCurrentThreshold) 
    {
        this.RaiseBarrierCurrentThreshold = RaiseBarrierCurrentThreshold;
    }

    public Long getRaiseBarrierCurrentThreshold() 
    {
        return RaiseBarrierCurrentThreshold;
    }
    public void setFallBarrierCurrentThreshold(Long FallBarrierCurrentThreshold) 
    {
        this.FallBarrierCurrentThreshold = FallBarrierCurrentThreshold;
    }

    public Long getFallBarrierCurrentThreshold() 
    {
        return FallBarrierCurrentThreshold;
    }
    public void setRaiseBarrierTimeThreshold(Long RaiseBarrierTimeThreshold) 
    {
        this.RaiseBarrierTimeThreshold = RaiseBarrierTimeThreshold;
    }

    public Long getRaiseBarrierTimeThreshold() 
    {
        return RaiseBarrierTimeThreshold;
    }
    public void setFallBarrierTimeThreshold(Long FallBarrierTimeThreshold) 
    {
        this.FallBarrierTimeThreshold = FallBarrierTimeThreshold;
    }

    public Long getFallBarrierTimeThreshold() 
    {
        return FallBarrierTimeThreshold;
    }
    public void setRetryCount(Long RetryCount) 
    {
        this.RetryCount = RetryCount;
    }

    public Long getRetryCount() 
    {
        return RetryCount;
    }
    public void setRaiseBarrierRetryCurrentThreshold(Long RaiseBarrierRetryCurrentThreshold) 
    {
        this.RaiseBarrierRetryCurrentThreshold = RaiseBarrierRetryCurrentThreshold;
    }

    public Long getRaiseBarrierRetryCurrentThreshold() 
    {
        return RaiseBarrierRetryCurrentThreshold;
    }
    public void setFallBarrierRetryCurrentThreshold(Long FallBarrierRetryCurrentThreshold) 
    {
        this.FallBarrierRetryCurrentThreshold = FallBarrierRetryCurrentThreshold;
    }

    public Long getFallBarrierRetryCurrentThreshold() 
    {
        return FallBarrierRetryCurrentThreshold;
    }
    public void setRaiseBarrierRetryTimeThreshold(Long RaiseBarrierRetryTimeThreshold) 
    {
        this.RaiseBarrierRetryTimeThreshold = RaiseBarrierRetryTimeThreshold;
    }

    public Long getRaiseBarrierRetryTimeThreshold() 
    {
        return RaiseBarrierRetryTimeThreshold;
    }
    public void setFallBarrierRetryTimeThreshold(Long FallBarrierRetryTimeThreshold) 
    {
        this.FallBarrierRetryTimeThreshold = FallBarrierRetryTimeThreshold;
    }

    public Long getFallBarrierRetryTimeThreshold() 
    {
        return FallBarrierRetryTimeThreshold;
    }
    public Long getCurrentCurrent() {
        return CurrentCurrent;
    }

    public void setCurrentCurrent(Long currentCurrent) {
        CurrentCurrent = currentCurrent;
    }

    public int getWorkingTrend() {
        return WorkingTrend;
    }

    public void setWorkingTrend(int workingTrend) {
        WorkingTrend = workingTrend;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("CurrentHeight", getCurrentHeight())
            .append("GroupId", getGroupId())
            .append("AreaId", getAreaId())
            .append("CommunicationCabinetId", getCommunicationCabinetId())
            .append("ControlCabinetId", getControlCabinetId())
            .append("isWorking", getIsWorking())
            .append("isRetrying", getIsRetrying())
            .append("RaiseBarrierCurrentThreshold", getRaiseBarrierCurrentThreshold())
            .append("FallBarrierCurrentThreshold", getFallBarrierCurrentThreshold())
            .append("RaiseBarrierTimeThreshold", getRaiseBarrierTimeThreshold())
            .append("FallBarrierTimeThreshold", getFallBarrierTimeThreshold())
            .append("RetryCount", getRetryCount())
            .append("RaiseBarrierRetryCurrentThreshold", getRaiseBarrierRetryCurrentThreshold())
            .append("FallBarrierRetryCurrentThreshold", getFallBarrierRetryCurrentThreshold())
            .append("RaiseBarrierRetryTimeThreshold", getRaiseBarrierRetryTimeThreshold())
            .append("FallBarrierRetryTimeThreshold", getFallBarrierRetryTimeThreshold())
                .append("CurrentCurrent", getCurrentCurrent())
                .append("WorkingTrend", getWorkingTrend())
            .toString();
    }


}
