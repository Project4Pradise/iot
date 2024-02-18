package com.ruoyi.barrier.service;

import java.util.List;
import com.ruoyi.barrier.domain.SysError;

/**
 * 错误管理Service接口
 * 
 * @author ruoyi
 * @date 2024-02-01
 */
public interface ISysErrorService 
{
    /**
     * 查询错误管理
     * 
     * @param id 错误管理主键
     * @return 错误管理
     */
    public SysError selectSysErrorById(Long id);

    /**
     * 查询错误管理列表
     * 
     * @param sysError 错误管理
     * @return 错误管理集合
     */
    public List<SysError> selectSysErrorList(SysError sysError);

    /**
     * 新增错误管理
     * 
     * @param sysError 错误管理
     * @return 结果
     */
    public int insertSysError(SysError sysError);

    /**
     * 修改错误管理
     * 
     * @param sysError 错误管理
     * @return 结果
     */
    public int updateSysError(SysError sysError);

    /**
     * 批量删除错误管理
     * 
     * @param ids 需要删除的错误管理主键集合
     * @return 结果
     */
    public int deleteSysErrorByIds(Long[] ids);

    /**
     * 删除错误管理信息
     * 
     * @param id 错误管理主键
     * @return 结果
     */
    public int deleteSysErrorById(Long id);
}
