package com.ruoyi.barrier.mapper;

import java.util.List;
import com.ruoyi.barrier.domain.SysErrorReportingLog;

/**
 * 错误日志Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-02
 */
public interface SysErrorReportingLogMapper 
{
    /**
     * 查询错误日志
     * 
     * @param id 错误日志主键
     * @return 错误日志
     */
    public SysErrorReportingLog selectSysErrorReportingLogById(Long id);

    /**
     * 查询错误日志列表
     * 
     * @param sysErrorReportingLog 错误日志
     * @return 错误日志集合
     */
    public List<SysErrorReportingLog> selectSysErrorReportingLogList(SysErrorReportingLog sysErrorReportingLog);

    /**
     * 新增错误日志
     * 
     * @param sysErrorReportingLog 错误日志
     * @return 结果
     */
    public int insertSysErrorReportingLog(SysErrorReportingLog sysErrorReportingLog);

    /**
     * 修改错误日志
     * 
     * @param sysErrorReportingLog 错误日志
     * @return 结果
     */
    public int updateSysErrorReportingLog(SysErrorReportingLog sysErrorReportingLog);

    /**
     * 删除错误日志
     * 
     * @param id 错误日志主键
     * @return 结果
     */
    public int deleteSysErrorReportingLogById(Long id);

    /**
     * 批量删除错误日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysErrorReportingLogByIds(Long[] ids);
    public List<SysErrorReportingLog> selectErrorsByCabinetId(Long id);
}
