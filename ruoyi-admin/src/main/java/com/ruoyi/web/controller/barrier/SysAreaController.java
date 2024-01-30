package com.ruoyi.web.controller.barrier;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.barrier.mapper.SysAreaMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.barrier.domain.SysArea;
import com.ruoyi.barrier.service.ISysAreaService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 区域Controller
 * 
 * @author ruoyi
 * @date 2024-01-22
 */
@RestController
@RequestMapping("/config/area")
@Api("区域接口")
public class SysAreaController extends BaseController
{
    @Autowired
    private ISysAreaService sysAreaService;

    @Autowired
    private SysAreaMapper areaMapper;

    /**
     * 查询区域列表
     */
    @PreAuthorize("@ss.hasPermi('config:area:list')")
    @GetMapping("/list")
    @ApiOperation("查询")
    public TableDataInfo list(SysArea sysArea)
    {
        startPage();
        List<SysArea> list = sysAreaService.selectSysAreaList(sysArea);
        return getDataTable(list);
    }

    /**
     * 导出区域列表
     */
    @PreAuthorize("@ss.hasPermi('config:area:export')")
    @Log(title = "区域", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysArea sysArea)
    {
        List<SysArea> list = sysAreaService.selectSysAreaList(sysArea);
        ExcelUtil<SysArea> util = new ExcelUtil<SysArea>(SysArea.class);
        util.exportExcel(response, list, "区域数据");
    }

    /**
     * 获取区域详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:area:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysAreaService.selectSysAreaById(id));
    }

    /**
     * 新增区域
     */
    @PreAuthorize("@ss.hasPermi('config:area:add')")
    @Log(title = "区域", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysArea sysArea)
    {
        return toAjax(sysAreaService.insertSysArea(sysArea));
    }

    /**
     * 修改区域
     */
    @PreAuthorize("@ss.hasPermi('config:area:edit')")
    @Log(title = "区域", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysArea sysArea)
    {
        return toAjax(sysAreaService.updateSysArea(sysArea));
    }

    /**
     * 删除区域
     */
    @PreAuthorize("@ss.hasPermi('config:area:remove')")
    @Log(title = "区域", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysAreaService.deleteSysAreaByIds(ids));
    }
    @ApiOperation("查询所有区域")
    @PreAuthorize("@ss.hasPermi('config:area:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {

        return  success(areaMapper.selectAreaList());
    }


}
