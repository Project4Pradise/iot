package com.ruoyi.barrier.service;

import java.util.List;
import com.ruoyi.barrier.domain.SysArea;

/**
 * 区域Service接口
 * 
 * @author ruoyi
 * @date 2024-01-22
 */
public interface ISysAreaService 
{
    /**
     * 查询区域
     * 
     * @param id 区域主键
     * @return 区域
     */
    public SysArea selectSysAreaById(Long id);

    /**
     * 查询区域列表
     * 
     * @param sysArea 区域
     * @return 区域集合
     */
    public List<SysArea> selectSysAreaList(SysArea sysArea);

    /**
     * 新增区域
     * 
     * @param sysArea 区域
     * @return 结果
     */
    public int insertSysArea(SysArea sysArea);

    /**
     * 修改区域
     * 
     * @param sysArea 区域
     * @return 结果
     */
    public int updateSysArea(SysArea sysArea);

    /**
     * 批量删除区域
     * 
     * @param ids 需要删除的区域主键集合
     * @return 结果
     */
    public int deleteSysAreaByIds(Long[] ids);

    /**
     * 删除区域信息
     * 
     * @param id 区域主键
     * @return 结果
     */
    public int deleteSysAreaById(Long id);
}
