package com.jyb.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyb.common.Result;
import com.jyb.user.domain.User;
import com.jyb.user.domain.UserParams;
import com.jyb.user.mapper.UserInfoMapper;
import com.jyb.util.FaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	UserInfoMapper userInfoMapper;

	//获取所有用户信息
	public PageInfo<User> getUserList(UserParams params) {
		PageHelper.startPage(params.getPageNum(), params.getPageSize());
		List<User> info = userInfoMapper.getUSerList(params);
		return PageInfo.of(info);
	}

	//查询个人信息
	public User getPersonList(User user){
		return userInfoMapper.getPersonList(user);
	}

	//添加数据库用户
	public boolean addUserList(User user){
		user.setUser_isadmin("0");
		return userInfoMapper.addUserList(user);
	}

	//修改数据库用户
	public boolean updateUserList(User user) {
		//修改百度用户
//		图片存储地址
		String filePath = System.getProperty("user.dir") + "/file/";
		filePath = filePath + user.getUser_phone();
		FaceUtil.updateFace(user.getUser_group_id(),filePath,user.getUser_id());
		return userInfoMapper.updateUserList(user);
	}


	//删除数据库用户
	public boolean deleteUserList(Integer id) {
		return userInfoMapper.deleteUserList(id);
	}

	//登录方法
	public User Login(User user){
		return userInfoMapper.Login(user);
	}

	//注册
	public Result reg(User user) {
		userInfoMapper.reg(user);
		return Result.success();
	}

	//通过id返回个人信息
	public List<User> getUserByUserID(String userId) {
		return userInfoMapper.getUserByUserID(userId);
	}
	//通过手机号返回个人信息
	public List<User> queryByPhone(String userPhone) {
	return userInfoMapper.queryByPhone(userPhone);
	}
}
