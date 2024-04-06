package com.jyb.group.mapper;

import com.jyb.group.domain.Group;
import org.apache.ibatis.annotations.*;
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


	//添加数据库用户组
	@Insert("insert into group_info values (null,#{group_id})")
	boolean addGroupList(String group_id);

	//修改数据库用户组
	@Update("update group_info set group_id = #{group_id} where id = #{id}")
	boolean updateGroupList(Group group);


	//删除数据库用户组
	@Delete("delete from group_info where id = #{id}")
	boolean deleteGroupList(Integer id);
}
