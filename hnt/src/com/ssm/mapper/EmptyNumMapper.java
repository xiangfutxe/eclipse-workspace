package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Update;

import com.ssm.dao.provider.CenterDynaSqlProvider;
import com.ssm.dao.provider.EmptyNumDynaSqlProvider;
import com.ssm.pojo.EmptyNum;
import com.ssm.utils.Constants;

public interface EmptyNumMapper {
	
	@SelectProvider(type=EmptyNumDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="uid", property="uid"),
		@Result(column="user_id", property="userId"),
		@Result(column="user_name", property="userName"),
		@Result(column="summary", property="summary"),
		@Result(column="state", property="state"),
		@Result(column="amount", property="amount"),
		@Result(column="balance", property="balance"),
		@Result(column="type", property="type"),
		@Result(column="entry_time", property="entryTime"),
	})
	List<EmptyNum> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=CenterDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.EMPTYNUMTABLE+" where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="uid", property="uid"),
		@Result(column="user_id", property="userId"),
		@Result(column="user_name", property="userName"),
		@Result(column="summary", property="summary"),
		@Result(column="state", property="state"),
		@Result(column="amount", property="amount"),
		@Result(column="balance", property="balance"),
		@Result(column="type", property="type"),
		@Result(column="entry_time", property="entryTime"),
	})
	EmptyNum selectById(Integer id);
	
	@Delete("delete from "+Constants.EMPTYNUMTABLE+" where id=#{id}")
	Integer deleteById(Integer id);
	
	@InsertProvider(type=EmptyNumDynaSqlProvider.class,method="insertEmptyNum")
	Integer save(EmptyNum emptyNum);
	


}
