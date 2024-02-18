package com.ruoyi.barrier.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 错误日志对象 sys_error_reporting_log
 * 
 * @author ruoyi
 * @date 2024-02-02
 */
public class SysErrorReportingLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 日志名称 */
    @Excel(name = "日志名称")
    private String name;

    /** 错误id */
    @Excel(name = "错误id")
    private Long errorId;

    /** 故障柜id */
    @Excel(name = "故障柜id")
    private Long cabinetId;

    /** 故障风障id */
    @Excel(name = "故障风障id")
    private Long barrierId;

    /** 错误描述 */
    @Excel(name = "错误描述")
    private String des;

    /** 图片url */
    @Excel(name = "图片url")
    private String imgURL;

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
    public void setErrorId(Long errorId) 
    {
        this.errorId = errorId;
    }

    public Long getErrorId() 
    {
        return errorId;
    }
    public void setCabinetId(Long cabinetId) 
    {
        this.cabinetId = cabinetId;
    }

    public Long getCabinetId() 
    {
        return cabinetId;
    }
    public void setBarrierId(Long barrierId) 
    {
        this.barrierId = barrierId;
    }

    public Long getBarrierId() 
    {
        return barrierId;
    }
    public void setDes(String des) 
    {
        this.des = des;
    }

    public String getDes() 
    {
        return des;
    }
    public void setImgURL(String imgURL) 
    {
        this.imgURL = imgURL;
    }

    public String getImgURL() 
    {
        return imgURL;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("errorId", getErrorId())
            .append("cabinetId", getCabinetId())
            .append("barrierId", getBarrierId())
            .append("des", getDes())
            .append("imgURL", getImgURL())
            .toString();
    }
}
