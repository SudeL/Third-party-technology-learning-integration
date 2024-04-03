package com.ruoyi.web.controller.facedom;

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
import com.ruoyi.gruop.domain.GroupInfo;
import com.ruoyi.gruop.service.IGroupInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 管理用户组Controller
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
@RestController
@RequestMapping("/ruoyi-facedom/gruop_info")
public class GroupInfoController extends BaseController
{
    @Autowired
    private IGroupInfoService groupInfoService;

    /**
     * 查询管理用户组列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-facedom:gruop_info:list')")
    @GetMapping("/list")
    public TableDataInfo list(GroupInfo groupInfo)
    {
        startPage();
        List<GroupInfo> list = groupInfoService.selectGroupInfoList(groupInfo);
        return getDataTable(list);
    }

    /**
     * 导出管理用户组列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-facedom:gruop_info:export')")
    @Log(title = "管理用户组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GroupInfo groupInfo)
    {
        List<GroupInfo> list = groupInfoService.selectGroupInfoList(groupInfo);
        ExcelUtil<GroupInfo> util = new ExcelUtil<GroupInfo>(GroupInfo.class);
        util.exportExcel(response, list, "管理用户组数据");
    }

    /**
     * 获取管理用户组详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-facedom:gruop_info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(groupInfoService.selectGroupInfoById(id));
    }

    /**
     * 新增管理用户组
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-facedom:gruop_info:add')")
    @Log(title = "管理用户组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GroupInfo groupInfo)
    {
        return toAjax(groupInfoService.insertGroupInfo(groupInfo));
    }

    /**
     * 修改管理用户组
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-facedom:gruop_info:edit')")
    @Log(title = "管理用户组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GroupInfo groupInfo)
    {
        return toAjax(groupInfoService.updateGroupInfo(groupInfo));
    }

    /**
     * 删除管理用户组
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-facedom:gruop_info:remove')")
    @Log(title = "管理用户组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(groupInfoService.deleteGroupInfoByIds(ids));
    }
}
