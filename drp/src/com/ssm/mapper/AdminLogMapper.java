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

import com.ssm.dao.provider.AdminLogDynaSqlProvider;
import com.ssm.pojo.AdminLog;

public interface AdminLogMapper {
	
	
	@SelectProvider(type=AdminLogDynaSqlProvider.class,method="selectWhitParam")
	List<AdminLog> selectByPage(Map<String,Object> params);
	
	@UpdateProvider(type=AdminLogDynaSqlProvider.class,method="returnSql")
	Integer del(String sql);
	
	@SelectProvider(type=AdminLogDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	//动态插入用户
	@UpdateProvider(type=AdminLogDynaSqlProvider.class,method="insertAdminLog")
	int save(AdminLog adminLog);
	
	@InsertProvider(type=AdminLogDynaSqlProvider.class,method="updateAdminLog")
	int update(AdminLog adminLog);
	
	
}
