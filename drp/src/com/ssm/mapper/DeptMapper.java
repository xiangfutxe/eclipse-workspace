package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.DeptDynaSqlProvider;
import com.ssm.pojo.Dept;
import com.ssm.utils.Constants;

public interface DeptMapper {
		
	@SelectProvider(type=DeptDynaSqlProvider.class,method="selectWhitParam")
	List<Dept> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=DeptDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.DEPTTABLE+" ")
	List<Dept> selectAllDept();
	
	
	@Select("select * from "+Constants.DEPTTABLE+" where id=#{id}")
	Dept selectById(Integer id);
	
	@Select("select * from "+Constants.DEPTTABLE+" where name=#{name}")
	Dept selectByName(String name);
	
	@Delete("delete from "+Constants.DEPTTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=DeptDynaSqlProvider.class,method="updateDept")
	void update(Dept dept);
	
	@SelectProvider(type=DeptDynaSqlProvider.class,method="insertDept")
	void save(Dept dept);

}
