package com.jyb.group.controller;

import com.jyb.common.Result;
import com.jyb.group.domain.Group;
import com.jyb.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/group")
public class GroupController {
	@Autowired
	GroupService groupService;

	//获取全部group表数据
	@GetMapping("/getGroupList")
	public Result getGroupList() {
		//开启分页查询
		List<Group> groupList = groupService.getGroupList();
		return Result.success(groupList);
	}

	//添加或修改group
	@PostMapping("/changeGroupList")
	public Result changeGroupList(@RequestBody Group group){
		if (group.getId()==null){
			//添加数据库用户组
			groupService.addGroupList(group.getGroup_id());
		}else {
			//修改数据库用户组
			groupService.updateGroupList(group);
		}
		return Result.success();
	}

	//删除group
	@PostMapping("/deleteGroupList/{id}")
	public Result deleteGroupList(@PathVariable Integer id){
		//删除数据库用户组
		boolean b = groupService.deleteGroupList(id);
		return Result.success(b);
	}
}
