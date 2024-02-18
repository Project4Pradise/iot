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
import com.ruoyi.barrier.domain.SysError;
import com.ruoyi.barrier.service.ISysErrorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 错误管理Controller
 * 
 * @author ruoyi
 * @date 2024-02-01
 */
@RestController
@RequestMapping("/config/error")
public class SysErrorController extends BaseController
{
    @Autowired
    private ISysErrorService sysErrorService;

    /**
     * 查询错误管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:error:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysError sysError)
    {
        startPage();
        List<SysError> list = sysErrorService.selectSysErrorList(sysError);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('config:error:list')")
    @GetMapping("/listAll")
    public TableDataInfo listAll(SysError sysError)
    {

        List<SysError> list = sysErrorService.selectSysErrorList(sysError);
        return getDataTable(list);
    }
    /**
     * 导出错误管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:error:export')")
    @Log(title = "错误管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysError sysError)
    {
        List<SysError> list = sysErrorService.selectSysErrorList(sysError);
        ExcelUtil<SysError> util = new ExcelUtil<SysError>(SysError.class);
        util.exportExcel(response, list, "错误管理数据");
    }

    /**
     * 获取错误管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:error:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysErrorService.selectSysErrorById(id));
    }

    /**
     * 新增错误管理
     */
    @PreAuthorize("@ss.hasPermi('config:error:add')")
    @Log(title = "错误管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysError sysError)
    {
        return toAjax(sysErrorService.insertSysError(sysError));
    }

    /**
     * 修改错误管理
     */
    @PreAuthorize("@ss.hasPermi('config:error:edit')")
    @Log(title = "错误管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysError sysError)
    {
        return toAjax(sysErrorService.updateSysError(sysError));
    }

    /**
     * 删除错误管理
     */
    @PreAuthorize("@ss.hasPermi('config:error:remove')")
    @Log(title = "错误管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysErrorService.deleteSysErrorByIds(ids));
    }
}
