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

import com.ssm.dao.provider.RewardDetailDynaSqlProvider;
import com.ssm.pojo.Dept;
import com.ssm.pojo.RewardDetail;
import com.ssm.utils.Constants;

public interface RewardDetailMapper {
		
	@SelectProvider(type=RewardDetailDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="rewardMap",value={
			@Result(id=true,column="id",property="id",javaType=long.class),
			@Result(column="uid", property="uid"),
			@Result(column="user_id", property="userId"),
			@Result(column="user_name", property="userName"),
			@Result(column="referee_id", property="refereeId"),
			@Result(column="referee_user_id", property="refereeUserId"),
			@Result(column="referee_user_name", property="refereeUserName"),
			@Result(column="award", property="award"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<RewardDetail> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=RewardDetailDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=RewardDetailDynaSqlProvider.class,method="selectListWhitParam")
	@ResultMap("rewardMap")
	List<RewardDetail> selectByList(Map<String,Object> params);
	
	@Select("select * from "+Constants.REWARDDETAILTABLE+" where state=1 ")
	@ResultMap("rewardMap")
	List<RewardDetail> selectAllDept();
	
	
	@Select("select * from "+Constants.REWARDDETAILTABLE+" where id=#{id}")
	@ResultMap("rewardMap")
	RewardDetail selectById(Integer id);
	
	@Select("select * from "+Constants.REWARDDETAILTABLE+" where name=#{name}")
	@ResultMap("rewardMap")
	Dept selectByName(String name);
	
	@Delete("delete from "+Constants.REWARDDETAILTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=RewardDetailDynaSqlProvider.class,method="update")
	int update(RewardDetail reward);
	
	@InsertProvider(type=RewardDetailDynaSqlProvider.class,method="insert")
	int save(RewardDetail reward);

}
