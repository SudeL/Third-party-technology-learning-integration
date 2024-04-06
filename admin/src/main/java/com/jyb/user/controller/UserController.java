package com.jyb.user.controller;

import com.jyb.common.Result;
import com.jyb.user.domain.User;
import com.jyb.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	//获取全部group表数据
	@GetMapping("/getUserList")
	public Result getUserList() {
		List<User> groupList = userService.getUserList();
		return Result.success(groupList);
	}
}
