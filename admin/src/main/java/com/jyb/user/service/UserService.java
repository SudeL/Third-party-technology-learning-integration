package com.jyb.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyb.common.Result;
import com.jyb.group.domain.Group;
import com.jyb.user.domain.UserParams;
import com.jyb.user.domain.User;
import com.jyb.user.mapper.UserInfoMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	UserInfoMapper userInfoMapper;

	//获取所有用户信息
	public PageInfo<User> getUserList(UserParams params) {
		PageHelper.startPage(params.getPageNum(),params.getPageSize());
		List<User> info = userInfoMapper.getUSerList(params);
		return PageInfo.of(info);
	}

	//添加数据库用户
	public boolean addUserList(User user){
		user.setUser_isadmin("1");
		return userInfoMapper.addUserList(user);
	}

	//修改数据库用户
	public boolean updateUserList(User user){
		return userInfoMapper.updateUserList(user);
	}


	//删除数据库用户
	public boolean deleteUserList(Integer id){
		return userInfoMapper.deleteUserList(id);
	}

//登录方法
	public List<User> Login(User user){
		return userInfoMapper.Login(user);
	};
//注册
	public Result reg(User user) {
		userInfoMapper.reg(user);
		return  Result.success();
	}
}
