package com.ruoyi.barrier.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.barrier.mapper.SysMqttLogMapper;
import com.ruoyi.barrier.domain.SysMqttLog;
import com.ruoyi.barrier.service.ISysMqttLogService;

import javax.annotation.Resource;

/**
 * mqtt日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-04
 */
@Service
public class SysMqttLogServiceImpl implements ISysMqttLogService 
{
    @Resource
    private SysMqttLogMapper sysMqttLogMapper;

    /**
     * 查询mqtt日志
     * 
     * @param id mqtt日志主键
     * @return mqtt日志
     */
    @Override
    public SysMqttLog selectSysMqttLogById(Long id)
    {
        return sysMqttLogMapper.selectSysMqttLogById(id);
    }

    /**
     * 查询mqtt日志列表
     * 
     * @param sysMqttLog mqtt日志
     * @return mqtt日志
     */
    @Override
    public List<SysMqttLog> selectSysMqttLogList(SysMqttLog sysMqttLog)
    {
        return sysMqttLogMapper.selectSysMqttLogList(sysMqttLog);
    }

    /**
     * 新增mqtt日志
     * 
     * @param sysMqttLog mqtt日志
     * @return 结果
     */
    @Override
    public int insertSysMqttLog(SysMqttLog sysMqttLog)
    {
        return sysMqttLogMapper.insertSysMqttLog(sysMqttLog);
    }

    /**
     * 修改mqtt日志
     * 
     * @param sysMqttLog mqtt日志
     * @return 结果
     */
    @Override
    public int updateSysMqttLog(SysMqttLog sysMqttLog)
    {
        return sysMqttLogMapper.updateSysMqttLog(sysMqttLog);
    }

    /**
     * 批量删除mqtt日志
     * 
     * @param ids 需要删除的mqtt日志主键
     * @return 结果
     */
    @Override
    public int deleteSysMqttLogByIds(Long[] ids)
    {
        return sysMqttLogMapper.deleteSysMqttLogByIds(ids);
    }

    /**
     * 删除mqtt日志信息
     * 
     * @param id mqtt日志主键
     * @return 结果
     */
    @Override
    public int deleteSysMqttLogById(Long id)
    {
        return sysMqttLogMapper.deleteSysMqttLogById(id);
    }
}
