package com.ruoyi.barrier.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 sys_group
 * 
 * @author ruoyi
 * @date 2024-01-22
 */
public class SysGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 小组名字 */
    @Excel(name = "小组名字")
    private String name;

    /** 是否启用（1启用，0不启用） */
    @Excel(name = "是否启用", readConverterExp = "1=启用，0不启用")
    private Boolean enable;

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
    public void setEnable(Boolean enable)
    {
        this.enable = enable;
    }

    public Boolean getEnable()
    {
        return enable;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("enable", getEnable())
            .toString();
    }
}
