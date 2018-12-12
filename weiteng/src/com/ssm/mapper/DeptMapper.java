package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.DeptDynaSqlProvider;
import com.ssm.pojo.Dept;
import com.ssm.utils.Constants;

public interface DeptMapper {
		
	@SelectProvider(type=DeptDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="deptMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="name", property="name"),
			@Result(column="remark", property="remark"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<Dept> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=DeptDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.DEPTTABLE+" where state=1 ")
	@ResultMap("deptMap")
	List<Dept> selectAllDept();
	
	
	@Select("select * from "+Constants.DEPTTABLE+" where id=#{id}")
	@ResultMap("deptMap")
	Dept selectById(Integer id);
	
	@Select("select * from "+Constants.DEPTTABLE+" where name=#{name}")
	@ResultMap("deptMap")
	Dept selectByName(String name);
	
	@Delete("delete from "+Constants.DEPTTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=DeptDynaSqlProvider.class,method="updateDept")
	void update(Dept dept);
	
	@SelectProvider(type=DeptDynaSqlProvider.class,method="insertDept")
	void save(Dept dept);

}
