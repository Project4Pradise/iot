package com.ruoyi.barrier.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.barrier.mapper.SysCabinetMapper;
import com.ruoyi.barrier.domain.SysCabinet;
import com.ruoyi.barrier.service.ISysCabinetService;

import javax.annotation.Resource;

/**
 * 柜管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-01
 */
@Service
public class SysCabinetServiceImpl implements ISysCabinetService 
{
    @Resource
    private SysCabinetMapper sysCabinetMapper;

    /**
     * 查询柜管理
     * 
     * @param id 柜管理主键
     * @return 柜管理
     */
    @Override
    public SysCabinet selectSysCabinetById(Long id)
    {
        return sysCabinetMapper.selectSysCabinetById(id);
    }

    /**
     * 查询柜管理列表
     * 
     * @param sysCabinet 柜管理
     * @return 柜管理
     */
    @Override
    public List<SysCabinet> selectSysCabinetList(SysCabinet sysCabinet)
    {
        return sysCabinetMapper.selectSysCabinetList(sysCabinet);
    }

    /**
     * 新增柜管理
     * 
     * @param sysCabinet 柜管理
     * @return 结果
     */
    @Override
    public int insertSysCabinet(SysCabinet sysCabinet)
    {
        return sysCabinetMapper.insertSysCabinet(sysCabinet);
    }

    /**
     * 修改柜管理
     * 
     * @param sysCabinet 柜管理
     * @return 结果
     */
    @Override
    public int updateSysCabinet(SysCabinet sysCabinet)
    {
        return sysCabinetMapper.updateSysCabinet(sysCabinet);
    }

    /**
     * 批量删除柜管理
     * 
     * @param ids 需要删除的柜管理主键
     * @return 结果
     */
    @Override
    public int deleteSysCabinetByIds(Long[] ids)
    {
        return sysCabinetMapper.deleteSysCabinetByIds(ids);
    }

    /**
     * 删除柜管理信息
     * 
     * @param id 柜管理主键
     * @return 结果
     */
    @Override
    public int deleteSysCabinetById(Long id)
    {
        return sysCabinetMapper.deleteSysCabinetById(id);
    }
}
