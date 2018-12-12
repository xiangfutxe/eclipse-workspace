package com.ssm.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.RewardDynaSqlProvider;
import com.ssm.pojo.Reward;
import com.ssm.utils.Constants;

public interface RewardMapper {
	
	@SelectProvider(type=RewardDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="rewardMap",value={
			@Result(id=true,column="id",property="id",javaType=long.class),
			@Result(column="uid", property="uid"),
			@Result(column="user_id", property="userId"),
			@Result(column="user_name", property="userName"),
			@Result(column="group_price", property="groupPrice"),
			@Result(column="area_price", property="areaPrice"),
			@Result(column="amount_1", property="amount_1"),
			@Result(column="amount_2", property="amount_2"),
			@Result(column="amount_3", property="amount_3"),
			@Result(column="month_tag", property="monthTag"),
			@Result(column="pay_tag", property="payTag"),
			@Result(column="agent_tag", property="agentTag"),
			@Result(column="rank_join", property="rankJoin"),
			@Result(column="state", property="state"),
			@Result(column="start_time", property="startTime"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<Reward> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=RewardDynaSqlProvider.class,method="selectListWhitParam")
	@ResultMap("rewardMap")
	List<Reward> selectByList(Map<String,Object> params);
	
	@Update("update "+Constants.REWARDTABLE+" set state=#{state} where month_tag=#{monthTag}")
	Integer updateForState(@Param("state")Integer state, @Param("monthTag")Integer monthTag);
	
	@SelectProvider(type=RewardDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Delete("delete from "+Constants.REWARDTABLE+"  where id=#{id}")
	Integer delete(Integer id);
	
	@Delete("delete from "+Constants.REWARDTABLE+"  where month_tag=#{monthTag}")
	Integer deleteByMonthTag(Integer monthTag);
	
	@InsertProvider(type = RewardDynaSqlProvider.class, method = "insertAll")  
	Integer insertAll(Map<String,Object> params);
	
	@InsertProvider(type = RewardDynaSqlProvider.class, method = "insert")  
	Integer insert(Map<String,Object> params);
	
	@UpdateProvider(type=RewardDynaSqlProvider.class,method="update")
	Integer update(Map<String,Object> params);

}
