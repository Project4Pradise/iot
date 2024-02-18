package com.ruoyi.barrier.mapper;

import java.util.List;
import com.ruoyi.barrier.domain.SysWindbarrier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 风障参数Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-15
 *
 */
@Mapper
public interface SysWindbarrierMapper 
{
    /**
     * 查询风障参数
     * 
     * @param id 风障参数主键
     * @return 风障参数
     */
    public SysWindbarrier selectSysWindbarrierById(Long id);

    /**
     * 查询风障参数列表
     * 
     * @param sysWindbarrier 风障参数
     * @return 风障参数集合
     */
    public List<SysWindbarrier> selectSysWindbarrierList(SysWindbarrier sysWindbarrier);

    /**
     * 新增风障参数
     * 
     * @param sysWindbarrier 风障参数
     * @return 结果
     */
    public int insertSysWindbarrier(SysWindbarrier sysWindbarrier);

    /**
     * 修改风障参数
     * 
     * @param sysWindbarrier 风障参数
     * @return 结果
     */
    public int updateSysWindbarrier(SysWindbarrier sysWindbarrier);

    /**
     * 删除风障参数
     * 
     * @param id 风障参数主键
     * @return 结果
     */
    public int deleteSysWindbarrierById(Long id);

    public int updateCurrentById(@Param("id") Long id, @Param("current") float current);
    public int updateIsWorkingById(@Param("id") Long id, @Param("isWorking") boolean isWorking);

    public int updateIsRetryingById(@Param("id") Long id, @Param("isRetrying") boolean isRetrying);

    /**
     * 批量删除风障参数
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysWindbarrierByIds(Long[] ids);
    public List<SysWindbarrier> selelctBarrierBygroupId(Long id);
    public int updateBarrierWorkingTrendByIds( @Param("array") Long[] ids,@Param("WorkingTrend") int WorkingTrend);
    public List<SysWindbarrier> selectBarrierByControlCabinetId(Long id);
    public List<SysWindbarrier> selectBarrierByCommunicationCabinetId(Long id);
}
