package com.ruoyi.barrier.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.barrier.mapper.SysGroupMapper;
import com.ruoyi.barrier.domain.SysGroup;
import com.ruoyi.barrier.service.ISysGroupService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-01-22
 */
@Service
public class SysGroupServiceImpl implements ISysGroupService 
{
    @Autowired
    private SysGroupMapper sysGroupMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysGroup selectSysGroupById(Long id)
    {
        return sysGroupMapper.selectSysGroupById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysGroup 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysGroup> selectSysGroupList(SysGroup sysGroup)
    {
        return sysGroupMapper.selectSysGroupList(sysGroup);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysGroup 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysGroup(SysGroup sysGroup)
    {
        return sysGroupMapper.insertSysGroup(sysGroup);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysGroup 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysGroup(SysGroup sysGroup)
    {
        return sysGroupMapper.updateSysGroup(sysGroup);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysGroupByIds(Long[] ids)
    {
        return sysGroupMapper.deleteSysGroupByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysGroupById(Long id)
    {
        return sysGroupMapper.deleteSysGroupById(id);
    }
}
