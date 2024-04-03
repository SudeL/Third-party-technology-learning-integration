package com.ruoyi.gruop.service;

import java.util.List;
import com.ruoyi.gruop.domain.GroupInfo;

/**
 * 管理用户组Service接口
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
public interface IGroupInfoService 
{
    /**
     * 查询管理用户组
     * 
     * @param id 管理用户组主键
     * @return 管理用户组
     */
    public GroupInfo selectGroupInfoById(Long id);

    /**
     * 查询管理用户组列表
     * 
     * @param groupInfo 管理用户组
     * @return 管理用户组集合
     */
    public List<GroupInfo> selectGroupInfoList(GroupInfo groupInfo);

    /**
     * 新增管理用户组
     * 
     * @param groupInfo 管理用户组
     * @return 结果
     */
    public int insertGroupInfo(GroupInfo groupInfo);

    /**
     * 修改管理用户组
     * 
     * @param groupInfo 管理用户组
     * @return 结果
     */
    public int updateGroupInfo(GroupInfo groupInfo);

    /**
     * 批量删除管理用户组
     * 
     * @param ids 需要删除的管理用户组主键集合
     * @return 结果
     */
    public int deleteGroupInfoByIds(Long[] ids);

    /**
     * 删除管理用户组信息
     * 
     * @param id 管理用户组主键
     * @return 结果
     */
    public int deleteGroupInfoById(Long id);
}
