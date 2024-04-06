package com.jyb.group.controller;

import com.jyb.common.Result;
import com.jyb.group.domain.Group;
import com.jyb.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		System.out.println(Result.seccess(groupList));
		return Result.seccess(groupList);
	}
}
