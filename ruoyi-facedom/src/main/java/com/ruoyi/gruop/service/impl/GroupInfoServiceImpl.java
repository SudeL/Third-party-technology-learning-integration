package com.ruoyi.gruop.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gruop.mapper.GroupInfoMapper;
import com.ruoyi.gruop.domain.GroupInfo;
import com.ruoyi.gruop.service.IGroupInfoService;

/**
 * 管理用户组Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
@Service
public class GroupInfoServiceImpl implements IGroupInfoService 
{
    @Autowired
    private GroupInfoMapper groupInfoMapper;

    /**
     * 查询管理用户组
     * 
     * @param id 管理用户组主键
     * @return 管理用户组
     */
    @Override
    public GroupInfo selectGroupInfoById(Long id)
    {
        return groupInfoMapper.selectGroupInfoById(id);
    }

    /**
     * 查询管理用户组列表
     * 
     * @param groupInfo 管理用户组
     * @return 管理用户组
     */
    @Override
    public List<GroupInfo> selectGroupInfoList(GroupInfo groupInfo)
    {
        return groupInfoMapper.selectGroupInfoList(groupInfo);
    }

    /**
     * 新增管理用户组
     * 
     * @param groupInfo 管理用户组
     * @return 结果
     */
    @Override
    public int insertGroupInfo(GroupInfo groupInfo)
    {
        groupInfo.setCreateTime(DateUtils.getNowDate());
        return groupInfoMapper.insertGroupInfo(groupInfo);
    }

    /**
     * 修改管理用户组
     * 
     * @param groupInfo 管理用户组
     * @return 结果
     */
    @Override
    public int updateGroupInfo(GroupInfo groupInfo)
    {
        groupInfo.setUpdateTime(DateUtils.getNowDate());
        return groupInfoMapper.updateGroupInfo(groupInfo);
    }

    /**
     * 批量删除管理用户组
     * 
     * @param ids 需要删除的管理用户组主键
     * @return 结果
     */
    @Override
    public int deleteGroupInfoByIds(Long[] ids)
    {
        return groupInfoMapper.deleteGroupInfoByIds(ids);
    }

    /**
     * 删除管理用户组信息
     * 
     * @param id 管理用户组主键
     * @return 结果
     */
    @Override
    public int deleteGroupInfoById(Long id)
    {
        return groupInfoMapper.deleteGroupInfoById(id);
    }
}
