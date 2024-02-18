package com.ruoyi.barrier.mapper;

import java.util.List;
import com.ruoyi.barrier.domain.SysCabinet;
import org.springframework.context.annotation.Bean;

/**
 * 柜管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-02-01
 */
public interface SysCabinetMapper
{
    /**
     * 查询柜管理
     * 
     * @param id 柜管理主键
     * @return 柜管理
     */
    public SysCabinet selectSysCabinetById(Long id);

    /**
     * 查询柜管理列表
     * 
     * @param sysCabinet 柜管理
     * @return 柜管理集合
     */
    public List<SysCabinet> selectSysCabinetList(SysCabinet sysCabinet);

    /**
     * 新增柜管理
     * 
     * @param sysCabinet 柜管理
     * @return 结果
     */
    public int insertSysCabinet(SysCabinet sysCabinet);

    /**
     * 修改柜管理
     * 
     * @param sysCabinet 柜管理
     * @return 结果
     */
    public int updateSysCabinet(SysCabinet sysCabinet);

    /**
     * 删除柜管理
     * 
     * @param id 柜管理主键
     * @return 结果
     */
    public int deleteSysCabinetById(Long id);

    /**
     * 批量删除柜管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCabinetByIds(Long[] ids);
}
