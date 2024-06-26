package com.jyb.group.controller;
import com.github.pagehelper.PageInfo;
import com.jyb.common.Result;
import com.jyb.group.domain.Group;
import com.jyb.group.domain.GroupParams;
import com.jyb.group.service.GroupService;
import com.jyb.group.domain.Vo.FaceLoginVo;
import com.jyb.util.FaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/group")
public class GroupController {
	@Autowired
	GroupService groupService;

	//获取全部group表数据
	@GetMapping("/getGroupList")
	public Result getGroupList(GroupParams params) {
		PageInfo<Group> info = groupService.getGroupList(params);
		return Result.success(info);
	}

	//添加或修改group
	@PostMapping("/changeGroupList")
	public Result changeGroupList(@RequestBody Group group) {
		if (group.getId() == null) {
			//添加数据库用户组
			groupService.addGroupList(group.getGroup_id());
			//添加百度用户组
			FaceUtil.addUserList(group.getGroup_id());
		} else {
			//修改数据库用户组
			groupService.updateGroupList(group);

		}
		return Result.success();
	}

	//删除group
	@PostMapping("/deleteGroupList/{group_id}")
	public Result deleteGroupList(@PathVariable String group_id) {
		//删除数据库用户组
		boolean b = groupService.deleteGroupList(group_id);
		FaceUtil.deleteUserList(group_id);
		return Result.success(b);
	}

	//获取用户组
	@PostMapping("/getGroupID")
	public Result getGroupID() {
		String s = FaceUtil.queryGroup();
		return Result.success(s);
	}

	//	人脸认证登录
	@PostMapping("/FaceLogin")
	public Result FaceLogin(@RequestBody FaceLoginVo faceLoginVo) {
//		System.out.println(faceLoginVo);
		String s = FaceUtil.compareFace(faceLoginVo.getGroup_id(), faceLoginVo.getBase64());
		return Result.success(s);
//	{   //调用结果如下
//		"error_code": 0,
//			"error_msg": "SUCCESS",
//			"log_id": 2120731112,
//			"timestamp": 1712471720,
//			"cached": 0,
//			"result": {
//		        "face_token": "e818335f15714594bf790152bbb08135",
//				"user_list": [{
//			    "group_id": "dome1",
//				"user_id": "lht",
//				"user_info": "",
//				"score": 99.999893188477
//		}]
//	}
//	}
	}
}
