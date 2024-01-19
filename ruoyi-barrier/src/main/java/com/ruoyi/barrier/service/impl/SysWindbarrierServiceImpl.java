package com.ruoyi.barrier.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.barrier.mapper.SysWindbarrierMapper;
import com.ruoyi.barrier.domain.SysWindbarrier;
import com.ruoyi.barrier.service.ISysWindbarrierService;

/**
 * 风障参数Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
@Service
public class SysWindbarrierServiceImpl implements ISysWindbarrierService 
{
    @Autowired
    private SysWindbarrierMapper sysWindbarrierMapper;

    /**
     * 查询风障参数
     * 
     * @param id 风障参数主键
     * @return 风障参数
     */
    @Override
    public SysWindbarrier selectSysWindbarrierById(Long id)
    {
        return sysWindbarrierMapper.selectSysWindbarrierById(id);
    }

    /**
     * 查询风障参数列表
     * 
     * @param sysWindbarrier 风障参数
     * @return 风障参数
     */
    @Override
    public List<SysWindbarrier> selectSysWindbarrierList(SysWindbarrier sysWindbarrier)
    {
        return sysWindbarrierMapper.selectSysWindbarrierList(sysWindbarrier);
    }

    /**
     * 新增风障参数
     * 
     * @param sysWindbarrier 风障参数
     * @return 结果
     */
    @Override
    public int insertSysWindbarrier(SysWindbarrier sysWindbarrier)
    {
        return sysWindbarrierMapper.insertSysWindbarrier(sysWindbarrier);
    }

    /**
     * 修改风障参数
     * 
     * @param sysWindbarrier 风障参数
     * @return 结果
     */
    @Override
    public int updateSysWindbarrier(SysWindbarrier sysWindbarrier)
    {
        return sysWindbarrierMapper.updateSysWindbarrier(sysWindbarrier);
    }

    /**
     * 批量删除风障参数
     * 
     * @param ids 需要删除的风障参数主键
     * @return 结果
     */
    @Override
    public int deleteSysWindbarrierByIds(Long[] ids)
    {
        return sysWindbarrierMapper.deleteSysWindbarrierByIds(ids);
    }

    /**
     * 删除风障参数信息
     * 
     * @param id 风障参数主键
     * @return 结果
     */
    @Override
    public int deleteSysWindbarrierById(Long id)
    {
        return sysWindbarrierMapper.deleteSysWindbarrierById(id);
    }
}
