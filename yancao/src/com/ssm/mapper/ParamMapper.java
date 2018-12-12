package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.ParamDynaSqlProvider;
import com.ssm.pojo.Param;
import com.ssm.utils.Constants;

public interface ParamMapper {
		
	@SelectProvider(type=ParamDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="paramMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="param_name", property="paramName"),
			@Result(column="amount_1", property="amount_1"),
			@Result(column="amount_2", property="amount_2"),
			@Result(column="amount_3", property="amount_3"),
			@Result(column="amount_4", property="amount_4"),
			@Result(column="amount_5", property="amount_5"),
			@Result(column="amount_6", property="amount_6"),
			@Result(column="amount_7", property="amount_7"),
			@Result(column="amount_8", property="amount_8"),
			@Result(column="amount_9", property="amount_9"),
			@Result(column="amount_10", property="amount_10"),
			@Result(column="unit", property="unit"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<Param> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ParamDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.PARAMTABLE+" ")
	@ResultMap("paramMap")
	List<Param> selectAllParam();
	
	
	@Select("select * from "+Constants.PARAMTABLE+" where id=#{id}")
	@ResultMap("paramMap")
	Param selectById(Integer id);
	
	@Select("select * from "+Constants.PARAMTABLE+" where param_name=#{name}")
	@ResultMap("paramMap")
	Param selectByName(String name);
	
	@Delete("delete from "+Constants.PARAMTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=ParamDynaSqlProvider.class,method="updateParam")
	void update(Param param);
	
	@SelectProvider(type=ParamDynaSqlProvider.class,method="insertParam")
	void save(Param param);

}
