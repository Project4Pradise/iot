package com.ruoyi.barrier.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.barrier.mapper.SysErrorReportingLogMapper;
import com.ruoyi.barrier.domain.SysErrorReportingLog;
import com.ruoyi.barrier.service.ISysErrorReportingLogService;

import javax.annotation.Resource;

/**
 * 错误日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-02-02
 */
@Service
public class SysErrorReportingLogServiceImpl implements ISysErrorReportingLogService 
{
    @Resource
    private SysErrorReportingLogMapper sysErrorReportingLogMapper;

    /**
     * 查询错误日志
     * 
     * @param id 错误日志主键
     * @return 错误日志
     */
    @Override
    public SysErrorReportingLog selectSysErrorReportingLogById(Long id)
    {
        return sysErrorReportingLogMapper.selectSysErrorReportingLogById(id);
    }

    /**
     * 查询错误日志列表
     * 
     * @param sysErrorReportingLog 错误日志
     * @return 错误日志
     */
    @Override
    public List<SysErrorReportingLog> selectSysErrorReportingLogList(SysErrorReportingLog sysErrorReportingLog)
    {
        return sysErrorReportingLogMapper.selectSysErrorReportingLogList(sysErrorReportingLog);
    }

    /**
     * 新增错误日志
     * 
     * @param sysErrorReportingLog 错误日志
     * @return 结果
     */
    @Override
    public int insertSysErrorReportingLog(SysErrorReportingLog sysErrorReportingLog)
    {
        return sysErrorReportingLogMapper.insertSysErrorReportingLog(sysErrorReportingLog);
    }

    /**
     * 修改错误日志
     * 
     * @param sysErrorReportingLog 错误日志
     * @return 结果
     */
    @Override
    public int updateSysErrorReportingLog(SysErrorReportingLog sysErrorReportingLog)
    {
        return sysErrorReportingLogMapper.updateSysErrorReportingLog(sysErrorReportingLog);
    }

    /**
     * 批量删除错误日志
     * 
     * @param ids 需要删除的错误日志主键
     * @return 结果
     */
    @Override
    public int deleteSysErrorReportingLogByIds(Long[] ids)
    {
        return sysErrorReportingLogMapper.deleteSysErrorReportingLogByIds(ids);
    }

    /**
     * 删除错误日志信息
     * 
     * @param id 错误日志主键
     * @return 结果
     */
    @Override
    public int deleteSysErrorReportingLogById(Long id)
    {
        return sysErrorReportingLogMapper.deleteSysErrorReportingLogById(id);
    }

    @Override
    public List<SysErrorReportingLog> selectErrorsByCabinetId(Long id)
    {
        return sysErrorReportingLogMapper.selectErrorsByCabinetId(id);
    }
}
