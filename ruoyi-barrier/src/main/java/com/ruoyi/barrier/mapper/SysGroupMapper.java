package com.ruoyi.barrier.mapper;

import java.util.List;
import com.ruoyi.barrier.domain.SysGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2024-01-22
 */
@Mapper
public interface    SysGroupMapper
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysGroup selectSysGroupById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysGroup 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysGroup> selectSysGroupList(SysGroup sysGroup);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysGroup 【请填写功能名称】
     * @return 结果
     */
    public int insertSysGroup(SysGroup sysGroup);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysGroup 【请填写功能名称】
     * @return 结果
     */
    public int updateSysGroup(SysGroup sysGroup);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysGroupById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysGroupByIds(Long[] ids);
}
