package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;

import com.ssm.dao.provider.UserLogDynaSqlProvider;
import com.ssm.pojo.UserLog;

public interface UserLogMapper {
	
	
	@SelectProvider(type=UserLogDynaSqlProvider.class,method="selectWhitParam")
	List<UserLog> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=UserLogDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=UserLogDynaSqlProvider.class,method="selectListWhitParam")
	List<UserLog> selectByList(Map<String,Object> params);
	
	//动态插入用户
	@UpdateProvider(type=UserLogDynaSqlProvider.class,method="insertUserLog")
	Integer save(UserLog UserLog);
	
	@InsertProvider(type=UserLogDynaSqlProvider.class,method="updateUserLog")
	Integer update(UserLog UserLog);
	
	
}
