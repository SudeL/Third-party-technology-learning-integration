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
		List<Group> groupList = groupService.getGroupList();
		return Result.success(groupList);
	}

	//添加group
	@PostMapping("/addGroupList")
	public Result addGroupList(@RequestBody Group group){
		System.out.println(group.getGroup_id());
		boolean b = groupService.addGroupList(group.getGroup_id());
		return Result.success(b);
	}
}
