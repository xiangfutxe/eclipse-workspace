package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.ModuleDynaSqlProvider;
import com.ssm.dao.provider.NewsDynaSqlProvider;
import com.ssm.pojo.Module;
import com.ssm.utils.Constants;

public interface ModuleMapper {
		
	@SelectProvider(type=ModuleDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="moduleMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="code", property="code"),
			@Result(column="title", property="title"),
			@Result(column="contents", property="contents"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<Module> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ModuleDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	
	
	
	@Select("select * from "+Constants.MODULETABLE+" where id=#{id}")
	@ResultMap("moduleMap")
	Module selectById(Integer id);
	
	@Select("select * from "+Constants.MODULETABLE+" where code=#{code}")
	@ResultMap("moduleMap")
	Module selectByCode(Integer title);
	
	
	@Delete("delete from "+Constants.MODULETABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=ModuleDynaSqlProvider.class,method="update")
	int update(Module module);
	
	@InsertProvider(type=ModuleDynaSqlProvider.class,method="insert")
	int save(Module module);

}
