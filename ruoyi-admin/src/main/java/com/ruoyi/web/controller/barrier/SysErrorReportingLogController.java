package com.ruoyi.web.controller.barrier;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.barrier.domain.SysErrorReportingLog;
import com.ruoyi.barrier.service.ISysErrorReportingLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 错误日志Controller
 * 
 * @author ruoyi
 * @date 2024-02-02
 */
@RestController
@RequestMapping("/maintenance/log")
public class SysErrorReportingLogController extends BaseController
{
    @Autowired
    private ISysErrorReportingLogService sysErrorReportingLogService;

    /**
     * 查询错误日志列表
     */
    @PreAuthorize("@ss.hasPermi('maintenance:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysErrorReportingLog sysErrorReportingLog)
    {
        startPage();
        List<SysErrorReportingLog> list = sysErrorReportingLogService.selectSysErrorReportingLogList(sysErrorReportingLog);
        return getDataTable(list);
    }

    /**
     * 导出错误日志列表
     */
    @PreAuthorize("@ss.hasPermi('maintenance:log:export')")
    @Log(title = "错误日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysErrorReportingLog sysErrorReportingLog)
    {
        List<SysErrorReportingLog> list = sysErrorReportingLogService.selectSysErrorReportingLogList(sysErrorReportingLog);
        ExcelUtil<SysErrorReportingLog> util = new ExcelUtil<SysErrorReportingLog>(SysErrorReportingLog.class);
        util.exportExcel(response, list, "错误日志数据");
    }

    /**
     * 获取错误日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('maintenance:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysErrorReportingLogService.selectSysErrorReportingLogById(id));
    }

    /**
     * 新增错误日志
     */
    @PreAuthorize("@ss.hasPermi('maintenance:log:add')")
    @Log(title = "错误日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysErrorReportingLog sysErrorReportingLog)
    {
        return toAjax(sysErrorReportingLogService.insertSysErrorReportingLog(sysErrorReportingLog));
    }

    /**
     * 修改错误日志
     */
    @PreAuthorize("@ss.hasPermi('maintenance:log:edit')")
    @Log(title = "错误日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysErrorReportingLog sysErrorReportingLog)
    {
        return toAjax(sysErrorReportingLogService.updateSysErrorReportingLog(sysErrorReportingLog));
    }

    /**
     * 删除错误日志
     */
    @PreAuthorize("@ss.hasPermi('maintenance:log:remove')")
    @Log(title = "错误日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysErrorReportingLogService.deleteSysErrorReportingLogByIds(ids));
    }


    @PreAuthorize("@ss.hasPermi('maintenance:log:list')")
    @Log(title = "错误日志", businessType = BusinessType.DELETE)
    @GetMapping("/selectErrorsByCabinetId/{id}")
    public AjaxResult selectErrorsByCabinetId(@PathVariable Long id)
    {
        return AjaxResult.success(sysErrorReportingLogService.selectErrorsByCabinetId(id));
    }
}
