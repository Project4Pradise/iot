package com.ruoyi.barrier.service;

import java.util.List;
import com.ruoyi.barrier.domain.SysWindbarrier;

/**
 * 风障参数Service接口
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
public interface ISysWindbarrierService 
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
     * 批量删除风障参数
     * 
     * @param ids 需要删除的风障参数主键集合
     * @return 结果
     */
    public int deleteSysWindbarrierByIds(Long[] ids);

    /**
     * 删除风障参数信息
     * 
     * @param id 风障参数主键
     * @return 结果
     */
    public int deleteSysWindbarrierById(Long id);

    public int updateBarrierWorkingTrendByIds(Long[] ids,int WorkingTrend);
    public List<SysWindbarrier> selectBarrierByControlCabinet(Long id);
    public List<SysWindbarrier> selectBarrierByCommunicationCabinet(Long id);

}
