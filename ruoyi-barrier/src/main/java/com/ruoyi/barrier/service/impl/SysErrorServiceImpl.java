package com.ruoyi.barrier.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.barrier.mapper.SysErrorMapper;
import com.ruoyi.barrier.domain.SysError;
import com.ruoyi.barrier.service.ISysErrorService;

import javax.annotation.Resource;

/**
 * 错误管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-01
 */
@Service
public class SysErrorServiceImpl implements ISysErrorService 
{
    @Resource
    private SysErrorMapper sysErrorMapper;

    /**
     * 查询错误管理
     * 
     * @param id 错误管理主键
     * @return 错误管理
     */
    @Override
    public SysError selectSysErrorById(Long id)
    {
        return sysErrorMapper.selectSysErrorById(id);
    }

    /**
     * 查询错误管理列表
     * 
     * @param sysError 错误管理
     * @return 错误管理
     */
    @Override
    public List<SysError> selectSysErrorList(SysError sysError)
    {
        return sysErrorMapper.selectSysErrorList(sysError);
    }

    /**
     * 新增错误管理
     * 
     * @param sysError 错误管理
     * @return 结果
     */
    @Override
    public int insertSysError(SysError sysError)
    {
        return sysErrorMapper.insertSysError(sysError);
    }

    /**
     * 修改错误管理
     * 
     * @param sysError 错误管理
     * @return 结果
     */
    @Override
    public int updateSysError(SysError sysError)
    {
        return sysErrorMapper.updateSysError(sysError);
    }

    /**
     * 批量删除错误管理
     * 
     * @param ids 需要删除的错误管理主键
     * @return 结果
     */
    @Override
    public int deleteSysErrorByIds(Long[] ids)
    {
        return sysErrorMapper.deleteSysErrorByIds(ids);
    }

    /**
     * 删除错误管理信息
     * 
     * @param id 错误管理主键
     * @return 结果
     */
    @Override
    public int deleteSysErrorById(Long id)
    {
        return sysErrorMapper.deleteSysErrorById(id);
    }
}
