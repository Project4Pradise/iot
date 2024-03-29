package com.ruoyi.web.controller.barrier;

import com.ruoyi.barrier.domain.SysWindbarrier;
import com.ruoyi.barrier.mapper.SysWindbarrierMapper;
import com.ruoyi.barrier.service.ISysWindbarrierService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.listener.BarrierInformationChangedEvent;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 风障参数Controller
 * 
 * @author ruoyi
 * @date 2024-01-15
 */
@RestController
@RequestMapping("/config/barrier")
@Api("风障接口")
@Component
public class SysWindbarrierController extends BaseController
{
    @Autowired
    private ISysWindbarrierService sysWindbarrierService;
    @Autowired
    private SysWindbarrierMapper sysWindbarrierMapper;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    /**
     * 查询风障参数列表
     */
    @PreAuthorize("@ss.hasPermi('config:barrier:list')")
    @GetMapping("/list")
    @ApiOperation("查询风障参数列表")
    public TableDataInfo list(SysWindbarrier sysWindbarrier)
    {
        startPage();
        List<SysWindbarrier> list = sysWindbarrierService.selectSysWindbarrierList(sysWindbarrier);
        return getDataTable(list);
    }

    /**
     * 导出风障参数列表
     */
    @PreAuthorize("@ss.hasPermi('config:barrier:export')")
    @Log(title = "风障参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysWindbarrier sysWindbarrier)
    {
        List<SysWindbarrier> list = sysWindbarrierService.selectSysWindbarrierList(sysWindbarrier);
        ExcelUtil<SysWindbarrier> util = new ExcelUtil<SysWindbarrier>(SysWindbarrier.class);
        util.exportExcel(response, list, "风障参数数据");
    }


    /**
     * 获取风障参数详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:barrier:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysWindbarrierService.selectSysWindbarrierById(id));
    }

    /**
     * 新增风障参数
     */
    @PreAuthorize("@ss.hasPermi('config:barrier:add')")
    @Log(title = "风障参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysWindbarrier sysWindbarrier)
    {
        return toAjax(sysWindbarrierService.insertSysWindbarrier(sysWindbarrier));
    }

    /**
     * 修改风障参数
     */
    @PreAuthorize("@ss.hasPermi('config:barrier:edit')")
    @Log(title = "风障参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysWindbarrier sysWindbarrier)

    {

        return toAjax(sysWindbarrierService.updateSysWindbarrier(sysWindbarrier));
    }

    /**
     * 删除风障参数
     */
    @PreAuthorize("@ss.hasPermi('config:barrier:remove')")
    @Log(title = "风障参数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysWindbarrierService.deleteSysWindbarrierByIds(ids));
    }


    @PreAuthorize("@ss.hasPermi('config:barrier:edit')")
    @Log(title = "风障工作趋势", businessType = BusinessType.UPDATE)
    @PutMapping("/workingTrend")
    @ApiOperation("批量修改趋势")
    public AjaxResult updateWorkingTrend(@RequestParam Long[] ids,@RequestParam int workingTrend)
    {
        return success(sysWindbarrierService.updateBarrierWorkingTrendByIds(ids,workingTrend));
    }

    @PreAuthorize("@ss.hasPermi('config:barrier:query')")
    @GetMapping(value = "/getByControCabinetId/{id}")
    public AjaxResult getByControCabinetId(@PathVariable Long id)
    {
        return success(sysWindbarrierService.selectBarrierByControlCabinet(id));
    }
    @PreAuthorize("@ss.hasPermi('config:barrier:query')")
    @GetMapping(value = "/getByCommunicationCabinetId/{id}")
    public AjaxResult getByCommunicationCabinetId(@PathVariable Long id)
    {
        return success(sysWindbarrierService.selectBarrierByCommunicationCabinet(id));
    }

}
