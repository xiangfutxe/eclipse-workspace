package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.ParamDynaSqlProvider;
import com.ssm.pojo.Param;
import com.ssm.utils.Constants;

public interface ParamMapper {
		
	@SelectProvider(type=ParamDynaSqlProvider.class,method="selectWhitParam")
	List<Param> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ParamDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.PARAMTABLE+" ")
	List<Param> selectAllParam();
	
	
	@Select("select * from "+Constants.PARAMTABLE+" where id=#{id}")
	Param selectById(Integer id);
	
	@Select("select * from "+Constants.PARAMTABLE+" where paramName=#{name}")
	Param selectByName(String name);
	
	@Delete("delete from "+Constants.PARAMTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=ParamDynaSqlProvider.class,method="updateParam")
	void update(Param param);
	
	@SelectProvider(type=ParamDynaSqlProvider.class,method="insertParam")
	void save(Param param);

}
