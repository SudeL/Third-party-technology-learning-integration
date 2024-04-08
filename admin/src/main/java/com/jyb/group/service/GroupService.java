package com.jyb.group.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyb.group.domain.Group;
import com.jyb.group.domain.GroupParams;
import com.jyb.group.mapper.GroupInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
	@Autowired
	GroupInfoMapper groupInfoMapper;

//获取所有用户组信息
	public PageInfo<Group> getGroupList(GroupParams params) {
		//开启分页查询
		PageHelper.startPage(params.getPageNum(),params.getPageSize());
		List<Group> list = groupInfoMapper.getGroupList(params);
		return PageInfo.of(list);
	}

	//添加用户组
	public boolean addGroupList(String group_id){
		//添加数据库用户组
		return  groupInfoMapper.addGroupList(group_id);
	}

	//修改用户组
	public boolean updateGroupList(Group group){
		//修改数据库用户组
		return groupInfoMapper.updateGroupList(group);
	}

	//删除用户组
	public boolean deleteGroupList(String group_id){
		//删除数据库用户组
		return  groupInfoMapper.deleteGroupList(group_id);
	}


}
