package com.jyb.user.mapper;

import com.jyb.user.domain.UserParams;
import com.jyb.user.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 管理用户组Mapper接口
 *
 * @author lht
 * @date 2024-04-05
 */
@Mapper
public interface UserInfoMapper {
//	获取全部的user信息
	List<User> getUSerList(@Param("params") UserParams params);

	//添加数据库用户
	@Insert("insert into user_info values (null,null,#{user_name},#{user_password},null,null,#{user_isadmin},null)")
	boolean addUserList(User user);

	//	获取个人的user信息
	@Select("select * from user_info where user_name=#{user_name}")
	User getPersonList(User user);

	//修改数据库用户
	@Update("update user_info set user_name = #{user_name},user_password=#{user_password},user_group_id=#{user_group_id},user_isadmin=#{user_isadmin},user_face_path=#{user_face_path},user_phone=#{user_phone} where id = #{id}")
	boolean updateUserList(User user);


	//删除数据库用户
	@Delete("delete from user_info where id = #{id}")
	boolean deleteUserList(Integer id);

//登录
	@Select("select * from  user_info where  user_name = #{user_name} and user_password = #{user_password}")
	User Login(User user);

//注册
	@Insert("insert into user_info (user_name,user_password) values (#{user_name},#{user_password})")
	void reg(User user);
//通过use_id获取数据
	@Select("select * from user_info where user_id = #{userId}")
	List<User> getUserByUserID(String userId);
//通过手机号码查询用户信息
	@Select("select * from  user_info where user_phone = #{userPhone}")
	List<User> queryByPhone(String userPhone);

}
