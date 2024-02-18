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
import com.ruoyi.barrier.domain.SysCabinet;
import com.ruoyi.barrier.service.ISysCabinetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 柜管理Controller
 * 
 * @author ruoyi
 * @date 2024-02-01
 */
@RestController
@RequestMapping("/config/cabinet")
public class SysCabinetController extends BaseController
{
    @Autowired
    private ISysCabinetService sysCabinetService;

    /**
     * 查询柜管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:cabinet:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCabinet sysCabinet)
    {
        startPage();
        List<SysCabinet> list = sysCabinetService.selectSysCabinetList(sysCabinet);
        return getDataTable(list);
    }

    /**
     * 导出柜管理列表
     */
    @PreAuthorize("@ss.hasPermi('config:cabinet:export')")
    @Log(title = "柜管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCabinet sysCabinet)
    {
        List<SysCabinet> list = sysCabinetService.selectSysCabinetList(sysCabinet);
        ExcelUtil<SysCabinet> util = new ExcelUtil<SysCabinet>(SysCabinet.class);
        util.exportExcel(response, list, "柜管理数据");
    }

    /**
     * 获取柜管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('config:cabinet:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysCabinetService.selectSysCabinetById(id));
    }

    /**
     * 新增柜管理
     */
    @PreAuthorize("@ss.hasPermi('config:cabinet:add')")
    @Log(title = "柜管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCabinet sysCabinet)
    {
        return toAjax(sysCabinetService.insertSysCabinet(sysCabinet));
    }

    /**
     * 修改柜管理
     */
    @PreAuthorize("@ss.hasPermi('config:cabinet:edit')")
    @Log(title = "柜管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCabinet sysCabinet)
    {
        return toAjax(sysCabinetService.updateSysCabinet(sysCabinet));
    }

    /**
     * 删除柜管理
     */
    @PreAuthorize("@ss.hasPermi('config:cabinet:remove')")
    @Log(title = "柜管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCabinetService.deleteSysCabinetByIds(ids));
    }
}
