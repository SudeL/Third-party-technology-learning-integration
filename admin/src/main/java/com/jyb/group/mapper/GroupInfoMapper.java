package com.jyb.group.mapper;

import com.jyb.group.domain.Group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理用户组Mapper接口
 *
 * @author lht
 * @date 2024-04-05
 */
@Mapper
public interface GroupInfoMapper {
//	获取全部的group信息
	@Select("select * from group_info")
	List<Group> getGroupList();


	//添加group
	@Insert("insert into group_info values (null,#{group_id})")
	boolean addGroupList(String group_id);


}
