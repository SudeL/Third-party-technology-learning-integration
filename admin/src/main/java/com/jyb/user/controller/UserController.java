package com.jyb.user.controller;

import com.github.pagehelper.PageInfo;
import com.jyb.common.Result;
import com.jyb.user.domain.UserParams;
import com.jyb.user.domain.User;
import com.jyb.user.domain.Vo.PhoneLoginVo;
import com.jyb.user.service.UserService;
import com.jyb.util.SMSUtil;
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
	public Result getUserList(UserParams params) {
		PageInfo<User> userList = userService.getUserList(params);
		return Result.success(userList);
	}

	//添加或修改user
	@PostMapping("/changeUserList")
	public Result changeUserList(@RequestBody User user){
		if (user.getId()==null){
			//添加数据库用户
			userService.addUserList(user);
		}else {
			//修改数据库用户
			userService.updateUserList(user);
		}
		return Result.success();
	}


	//删除数据库用户
	@PostMapping("/deleteUserList/{id}")
	public Result deleteUserList(@PathVariable Integer id){
		boolean b = userService.deleteUserList(id);
		return Result.success(b);
	}

//登录
	@PostMapping("/login")
	public Result login(@RequestBody User user) {
		List<User> list = userService.Login(user);
		if (list.size() == 0){
//			登录失败
			return Result.error(" 登录失败");
		}else {
			return Result.success(list);
		}

	}
	//注册
	@PostMapping("/reg")
	public Result reg(@RequestBody User user) {
		System.out.println(user);
		Result list = userService.reg(user);
		return list;
	}
	//	获取验证码
	@PostMapping("/getPhoneCode")
	public Result getPhoneCode(@RequestBody PhoneLoginVo phoneLoginVo) {
		//		生成验证码
		String code = SMSUtil.getCode();
//		发送验证码
		String s = SMSUtil.sendSMS(phoneLoginVo.getUser_phone(), code);
		System.out.println(phoneLoginVo);
		System.out.println(code);
		System.out.println(s);
		PhoneLoginVo phoneLoginVo1 = new PhoneLoginVo(phoneLoginVo.getUser_phone(), code,s);
		return Result.success(phoneLoginVo1);
	}

}
