package com.jyb.group.service;

import com.jyb.group.domain.Group;
import com.jyb.group.mapper.GroupInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class GroupService {
	@Autowired
	GroupInfoMapper groupInfoMapper;

//获取所有用户组信息
	public List<Group> getGroupList() {
		return groupInfoMapper.getGroupList();
	}

	//添加用户组
	public boolean addGroupList(String group_id){
		return  groupInfoMapper.addGroupList(group_id);
	}
}
