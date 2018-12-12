package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.JobDynaSqlProvider;
import com.ssm.pojo.Job;
import com.ssm.utils.Constants;

public interface JobMapper {
		
	@SelectProvider(type=JobDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="jobMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="name", property="name"),
			@Result(column="remark", property="remark"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<Job> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=JobDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.JOBTABLE+" where state=1 ")
	@ResultMap("jobMap")
	List<Job> selectAllJOB();
	
	
	@Select("select * from "+Constants.JOBTABLE+" where id=#{id}")
	@ResultMap("jobMap")
	Job selectById(Integer id);
	
	@Select("select * from "+Constants.JOBTABLE+" where name=#{name}")
	@ResultMap("jobMap")
	Job selectByName(String name);
	
	@Delete("delete from "+Constants.JOBTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=JobDynaSqlProvider.class,method="updateJob")
	void update(Job job);
	
	@SelectProvider(type=JobDynaSqlProvider.class,method="insertJob")
	void save(Job job);

}
