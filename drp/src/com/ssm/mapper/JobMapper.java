package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.JobDynaSqlProvider;
import com.ssm.pojo.Job;
import com.ssm.utils.Constants;

public interface JobMapper {
		
	@SelectProvider(type=JobDynaSqlProvider.class,method="selectWhitParam")
	List<Job> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=JobDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.JOBTABLE+" ")
	List<Job> selectAllJOB();
	
	
	@Select("select * from "+Constants.JOBTABLE+" where id=#{id}")
	Job selectById(Integer id);
	
	@Select("select * from "+Constants.JOBTABLE+" where name=#{name}")
	Job selectByName(String name);
	
	@Delete("delete from "+Constants.JOBTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=JobDynaSqlProvider.class,method="updateJob")
	void update(Job job);
	
	@SelectProvider(type=JobDynaSqlProvider.class,method="insertJob")
	void save(Job job);

}
