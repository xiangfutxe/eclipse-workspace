package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.springframework.stereotype.Repository;

import com.ssm.pojo.Admin;
import com.ssm.provider.AdminDynaSqlProvider;
import com.utils.Constants;

@Repository
public interface AdminMapper {
	

	
	@InsertProvider(type=AdminDynaSqlProvider.class,method="insert")
	public int insert(Admin admin);	
	
	@UpdateProvider(type=AdminDynaSqlProvider.class,method="returnSql")
	public int deleteBySql(String sql);
	
	@Update("delete from "+Constants.ADMINTABLE+" where id=#id")
	public int delete(Integer id);
	
	@UpdateProvider(type=AdminDynaSqlProvider.class,method="update")
	public int update(Admin admin);
	
	@UpdateProvider(type=AdminDynaSqlProvider.class,method="returnSql")
	public int updateBySql(String sql);
	
	@Select("select * from "+Constants.ADMINTABLE+" where id=#{id}")
	@Results(id="adminMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="admin_name", property="adminName"),
			@Result(column="real_name", property="realName"),
			@Result(column="dept_id", property="deptId"),
			@Result(column="dept_name", property="deptName"),
			@Result(column="job_id", property="jobId"),
			@Result(column="job_name", property="jobName"),
			@Result(column="mobile", property="mobile"),
			@Result(column="rank", property="rank"),
			@Result(column="type", property="type"),
			@Result(column="state", property="state"),
			@Result(column="version", property="version"),
			@Result(column="view_num", property="viewNum"),
			@Result(column="end_time", property="endTime"),
			@Result(column="entry_time", property="entryTime")
	})
	public Admin getById(Integer id);
	
	@Select("select * from "+Constants.ADMINTABLE+" where id=#{id} for update")
	@ResultMap("adminMap")
	public Admin getByIdForUpdate(Integer id);
	
	@Select("select * from "+Constants.ADMINTABLE+" where admin_name=#{adminName}")
	@ResultMap("adminMap")
	public Admin getAdminByName(String adminName);
	
	@Select("select * from "+Constants.ADMINTABLE+" where admin_name=#{adminName} for update")
	@ResultMap("adminMap")
	public Admin getAdminByNameForUpdaet(String adminName);
	
	@SelectProvider(type=AdminDynaSqlProvider.class,method="selectWhitParam")
	@ResultMap("adminMap")
	public List<Admin> findByPager(Map<String,Object> params);
	
	@SelectProvider(type=AdminDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
	
	@UpdateProvider(type=AdminDynaSqlProvider.class,method="insertAll")
	public int insertAll(Map<String,Object> params);
	
}
