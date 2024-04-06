package com.jyb.user.mapper;

import com.jyb.group.domain.Group;
import com.jyb.user.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 管理用户组Mapper接口
 *
 * @author lht
 * @date 2024-04-05
 */
@Mapper
public interface UserInfoMapper
{
//	获取全部的user信息
	@Select("select * from user_info")
	List<User> getUSerList();
//登录
	@Select("select * from  user_info where  user_name = #{user_name} and user_password = #{user_password}")
	List<User> Login(User user);

//注册
	@Insert("insert into user_info (user_name,user_password) values (#{user_name},#{user_password})")
	List<User> reg(User user);
}
