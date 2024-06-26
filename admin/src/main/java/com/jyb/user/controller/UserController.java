package com.jyb.user.controller;

import com.github.pagehelper.PageInfo;
import com.jyb.common.Result;
import com.jyb.files.Files;
import com.jyb.user.domain.User;
import com.jyb.user.domain.UserParams;
import com.jyb.user.domain.Vo.PhoneLoginVo;
import com.jyb.user.service.UserService;
import com.jyb.util.FaceUtil;
import com.jyb.util.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	//获取全部user表数据
	@GetMapping("/getUserList")
	public Result getUserList(UserParams params) {
		PageInfo<User> userList = userService.getUserList(params);
		return Result.success(userList);
	}

	//查询个人信息
	@GetMapping("/getPersonList")
	public Result getPersonList(@RequestBody User user) {
		User list = userService.getPersonList(user);
		return Result.success(list);
	}


	//添加或修改user
	@PostMapping("/changeUserList")
	public Result changeUserList(@RequestBody User user) {
		if (user.getId() == null) {
			//添加数据库用户
			userService.addUserList(user);
		} else {
			//修改数据库用户
			userService.updateUserList(user);
			if (user.getUser_face_path()!=null){
				//人脸注册
				String user_id = System.currentTimeMillis() + "";
				String filePath = System.getProperty("user.dir") + "/file/" + user.getUser_face_path();
				FaceUtil.addFace(user.getUser_group_id(),filePath , user_id);
			}
		}
		return Result.success();
	}


	//删除数据库用户
	@PostMapping("/deleteUserList/{id}")
	public Result deleteUserList(@PathVariable Integer id) {
		boolean b = userService.deleteUserList(id);
		return Result.success(b);
	}

	//登录
	@PostMapping("/login")
	public Result login(@RequestBody User user) {
		User u = userService.Login(user);
		if (u == null){
//			登录失败
			return Result.error(" 登录失败");
		}else {
			return Result.success(u);
		}
	}

	//注册
	@PostMapping("/reg")
	public Result reg(@RequestBody User user) {
		Result list = userService.reg(user);
		return list;
	}

	//	获取验证码
	@PostMapping("/getPhoneCode")
	public Result getPhoneCode(@RequestBody PhoneLoginVo phoneLoginVo) {
//判断手机号是否注册
		List<User> user = userService.queryByPhone(phoneLoginVo.getUser_phone());
		if (user.isEmpty()) {
			return Result.error("手机号未注册");
		}

		//		生成验证码
		String code = SMSUtil.getCode();
//		发送验证码
		String s = SMSUtil.sendSMS(phoneLoginVo.getUser_phone(), code);
		System.out.println(phoneLoginVo);
		System.out.println(code);
		System.out.println(s);
		PhoneLoginVo phoneLoginVo1 = new PhoneLoginVo(phoneLoginVo.getUser_phone(), code, s,user);
		System.out.println(phoneLoginVo1);
		return Result.success(phoneLoginVo1);
	}

	//通过user_id获取数据
	@PostMapping("/getUserByUserID")
	public Result getUserByUserID(@RequestBody String user_id) {
		user_id = user_id.replaceAll("^\"|\"$", "");
		System.out.println(user_id);
		List<User> userByUserID = userService.getUserByUserID(user_id);
		System.out.println(userByUserID);
		return Result.success(userByUserID);
	}
}
