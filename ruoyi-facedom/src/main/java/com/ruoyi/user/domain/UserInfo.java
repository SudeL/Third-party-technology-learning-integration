package com.ruoyi.user.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理对象 user_info
 * 
 * @author ruoyi
 * @date 2024-04-03
 */
public class UserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增 */
    private Long id;

    /** 百度人脸库用户id */
    @Excel(name = "百度人脸库用户id")
    private String userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 用户密码 */
    @Excel(name = "用户密码")
    private String userPassword;

    /** 关联用户组表 */
    @Excel(name = "关联用户组表")
    private String userGruopId;

    /** 用户人脸地址 */
    @Excel(name = "用户人脸地址")
    private String userFacePath;

    /** 默认用户,0是用户，1是管理员 */
    @Excel(name = "默认用户,0是用户，1是管理员")
    private Long userIsadmin;

    /** 用户手机号 */
    @Excel(name = "用户手机号")
    private String userPhone;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserPassword(String userPassword) 
    {
        this.userPassword = userPassword;
    }

    public String getUserPassword() 
    {
        return userPassword;
    }
    public void setUserGruopId(String userGruopId) 
    {
        this.userGruopId = userGruopId;
    }

    public String getUserGruopId() 
    {
        return userGruopId;
    }
    public void setUserFacePath(String userFacePath) 
    {
        this.userFacePath = userFacePath;
    }

    public String getUserFacePath() 
    {
        return userFacePath;
    }
    public void setUserIsadmin(Long userIsadmin) 
    {
        this.userIsadmin = userIsadmin;
    }

    public Long getUserIsadmin() 
    {
        return userIsadmin;
    }
    public void setUserPhone(String userPhone) 
    {
        this.userPhone = userPhone;
    }

    public String getUserPhone() 
    {
        return userPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("userPassword", getUserPassword())
            .append("userGruopId", getUserGruopId())
            .append("userFacePath", getUserFacePath())
            .append("userIsadmin", getUserIsadmin())
            .append("userPhone", getUserPhone())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
