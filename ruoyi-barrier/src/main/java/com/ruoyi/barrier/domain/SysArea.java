package com.ruoyi.barrier.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 区域对象 sys_area
 * 
 * @author ruoyi
 * @date 2024-01-22
 */
public class SysArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 区域id */
    private Long id;

    /** 区域名字 */
    @Excel(name = "区域名字")
    private String name;

    /** 区域总电流阈值 */
    @Excel(name = "区域总电流阈值")
    private Long totalCurrentThreshold;

    /** 同时工作叶数 */
    @Excel(name = "同时工作叶数")
    private Long togetherWorkingCount;

    /** 是否开启智慧组合 */
    @Excel(name = "是否开启智慧组合")
    private Integer smartCombination;

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
    public void setTotalCurrentThreshold(Long totalCurrentThreshold) 
    {
        this.totalCurrentThreshold = totalCurrentThreshold;
    }

    public Long getTotalCurrentThreshold() 
    {
        return totalCurrentThreshold;
    }
    public void setTogetherWorkingCount(Long togetherWorkingCount) 
    {
        this.togetherWorkingCount = togetherWorkingCount;
    }

    public Long getTogetherWorkingCount() 
    {
        return togetherWorkingCount;
    }
    public void setSmartCombination(Integer smartCombination) 
    {
        this.smartCombination = smartCombination;
    }

    public Integer getSmartCombination() 
    {
        return smartCombination;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("totalCurrentThreshold", getTotalCurrentThreshold())
            .append("togetherWorkingCount", getTogetherWorkingCount())
            .append("smartCombination", getSmartCombination())
            .toString();
    }
}
