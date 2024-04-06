package com.jyb.user.controller;

import com.jyb.common.Result;
import com.jyb.user.domain.User;
import com.jyb.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
//登录
	@PostMapping("/login")
	public Result login(@RequestBody User user) {
		List<User> list = userService.Login(user);
		return Result.success(list);
	}
	//登录
	@PostMapping("/reg")
	public Result reg(@RequestBody User user) {
		System.out.println(user);
		Result list = userService.reg(user);
		return list;
	}
}
