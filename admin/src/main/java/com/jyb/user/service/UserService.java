package com.jyb.user.service;

import com.jyb.group.domain.Group;
import com.jyb.user.domain.User;
import com.jyb.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	UserInfoMapper userInfoMapper;

	//获取所有用户信息
	public List<User> getUserList() {
		return userInfoMapper.getUSerList();
	}
//登录方法
	public List<User> Login(User user){
		return userInfoMapper.Login(user);
	};
}
