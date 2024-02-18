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
import com.ruoyi.barrier.domain.SysMqttLog;
import com.ruoyi.barrier.service.ISysMqttLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * mqtt日志Controller
 * 
 * @author ruoyi
 * @date 2024-02-04
 */
@RestController
@RequestMapping("/mqttLog/log")
public class SysMqttLogController extends BaseController
{
    @Autowired
    private ISysMqttLogService sysMqttLogService;

    /**
     * 查询mqtt日志列表
     */
    @PreAuthorize("@ss.hasPermi('mqttLog:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMqttLog sysMqttLog)
    {
        startPage();
        List<SysMqttLog> list = sysMqttLogService.selectSysMqttLogList(sysMqttLog);
        return getDataTable(list);
    }

    /**
     * 导出mqtt日志列表
     */
    @PreAuthorize("@ss.hasPermi('mqttLog:log:export')")
    @Log(title = "mqtt日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysMqttLog sysMqttLog)
    {
        List<SysMqttLog> list = sysMqttLogService.selectSysMqttLogList(sysMqttLog);
        ExcelUtil<SysMqttLog> util = new ExcelUtil<SysMqttLog>(SysMqttLog.class);
        util.exportExcel(response, list, "mqtt日志数据");
    }

    /**
     * 获取mqtt日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('mqttLog:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysMqttLogService.selectSysMqttLogById(id));
    }

    /**
     * 新增mqtt日志
     */
    @PreAuthorize("@ss.hasPermi('mqttLog:log:add')")
    @Log(title = "mqtt日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMqttLog sysMqttLog)
    {
        return toAjax(sysMqttLogService.insertSysMqttLog(sysMqttLog));
    }

    /**
     * 修改mqtt日志
     */
    @PreAuthorize("@ss.hasPermi('mqttLog:log:edit')")
    @Log(title = "mqtt日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMqttLog sysMqttLog)
    {
        return toAjax(sysMqttLogService.updateSysMqttLog(sysMqttLog));
    }

    /**
     * 删除mqtt日志
     */
    @PreAuthorize("@ss.hasPermi('mqttLog:log:remove')")
    @Log(title = "mqtt日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysMqttLogService.deleteSysMqttLogByIds(ids));
    }
}
