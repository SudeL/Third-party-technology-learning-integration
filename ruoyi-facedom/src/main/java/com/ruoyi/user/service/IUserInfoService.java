package com.ruoyi.user.service;

import java.util.List;
import com.ruoyi.user.domain.UserInfo;

/**
 * 用户管理Service接口
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
public interface IUserInfoService 
{
    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    public UserInfo selectUserInfoById(Long id);

    /**
     * 查询用户管理列表
     * 
     * @param userInfo 用户管理
     * @return 用户管理集合
     */
    public List<UserInfo> selectUserInfoList(UserInfo userInfo);

    /**
     * 新增用户管理
     * 
     * @param userInfo 用户管理
     * @return 结果
     */
    public int insertUserInfo(UserInfo userInfo);

    /**
     * 修改用户管理
     * 
     * @param userInfo 用户管理
     * @return 结果
     */
    public int updateUserInfo(UserInfo userInfo);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteUserInfoByIds(Long[] ids);

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteUserInfoById(Long id);
}
