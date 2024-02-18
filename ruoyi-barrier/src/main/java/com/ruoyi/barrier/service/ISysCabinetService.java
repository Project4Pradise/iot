package com.ruoyi.barrier.service;

import java.util.List;
import com.ruoyi.barrier.domain.SysCabinet;

/**
 * 柜管理Service接口
 * 
 * @author ruoyi
 * @date 2024-02-01
 */
public interface ISysCabinetService 
{
    /**
     * 查询柜管理
     * 
     * @param id 柜管理主键
     * @return 柜管理
     */
    public SysCabinet selectSysCabinetById(Long id);

    /**
     * 查询柜管理列表
     * 
     * @param sysCabinet 柜管理
     * @return 柜管理集合
     */
    public List<SysCabinet> selectSysCabinetList(SysCabinet sysCabinet);

    /**
     * 新增柜管理
     * 
     * @param sysCabinet 柜管理
     * @return 结果
     */
    public int insertSysCabinet(SysCabinet sysCabinet);

    /**
     * 修改柜管理
     * 
     * @param sysCabinet 柜管理
     * @return 结果
     */
    public int updateSysCabinet(SysCabinet sysCabinet);

    /**
     * 批量删除柜管理
     * 
     * @param ids 需要删除的柜管理主键集合
     * @return 结果
     */
    public int deleteSysCabinetByIds(Long[] ids);

    /**
     * 删除柜管理信息
     * 
     * @param id 柜管理主键
     * @return 结果
     */
    public int deleteSysCabinetById(Long id);
}
