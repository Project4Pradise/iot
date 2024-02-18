package com.ruoyi.barrier.mapper;

import java.util.List;
import com.ruoyi.barrier.domain.SysMqttLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * mqtt日志Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-04
 */
@Mapper
public interface SysMqttLogMapper 
{
    /**
     * 查询mqtt日志
     * 
     * @param id mqtt日志主键
     * @return mqtt日志
     */
    public SysMqttLog selectSysMqttLogById(Long id);

    /**
     * 查询mqtt日志列表
     * 
     * @param sysMqttLog mqtt日志
     * @return mqtt日志集合
     */
    public List<SysMqttLog> selectSysMqttLogList(SysMqttLog sysMqttLog);

    /**
     * 新增mqtt日志
     * 
     * @param sysMqttLog mqtt日志
     * @return 结果
     */
    public int insertSysMqttLog(SysMqttLog sysMqttLog);

    /**
     * 修改mqtt日志
     * 
     * @param sysMqttLog mqtt日志
     * @return 结果
     */
    public int updateSysMqttLog(SysMqttLog sysMqttLog);

    /**
     * 删除mqtt日志
     * 
     * @param id mqtt日志主键
     * @return 结果
     */
    public int deleteSysMqttLogById(Long id);

    /**
     * 批量删除mqtt日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysMqttLogByIds(Long[] ids);
}
