package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;



import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.AdminDynaSqlProvider;
import com.ssm.pojo.Admin;
import com.ssm.utils.Constants;

public interface AdminMapper {
	
	@Select("select * from admin where admin_name=#{adminName} and password=#{password}" )
	@Results(id="adminMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="admin_name", property="adminName"),
			@Result(column="real_name", property="realName"),
			@Result(column="dept_id", property="deptId"),
			@Result(column="dept_name", property="deptName"),
			@Result(column="job_id", property="jobId"),
			@Result(column="job_name", property="jobName"),
			@Result(column="tel", property="tel"),
			@Result(column="rank", property="rank"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	public Admin login(@Param("adminName") String adminName,@Param("password") String password);
	
	
	@SelectProvider(type=AdminDynaSqlProvider.class,method="selectWhitParam")
	@ResultMap("adminMap")
	List<Admin> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=AdminDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	//动态插入用户
	@SelectProvider(type=AdminDynaSqlProvider.class,method="insertAdmin")
	void save(Admin admin);
	
	@SelectProvider(type=AdminDynaSqlProvider.class,method="updateAdmin")
	void update(Admin admin);
	
	@Select("select * from "+Constants.ADMINTABLE+" where id=#{id}")
	@ResultMap("adminMap")
	Admin selectById(Integer id);
	
	@Select("select * from "+Constants.ADMINTABLE+" where admin_name=#{name}")
	@ResultMap("adminMap")
	Admin selectByName(String name);
	
	@Delete("delete from "+Constants.ADMINTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@Delete("update "+Constants.ADMINTABLE+" set state=0 where id=#{id}")
	int deleteByIdForState(Integer id);
}
