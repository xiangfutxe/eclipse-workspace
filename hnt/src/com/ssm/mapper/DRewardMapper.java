package com.ssm.mapper;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.AddressDynaSqlProvider;
import com.ssm.dao.provider.OrderDetailDynaSqlProvider;
import com.ssm.dao.provider.DRewardDynaSqlProvider;
import com.ssm.pojo.Address;
import com.ssm.pojo.User;
import com.ssm.pojo.DReward;
import com.ssm.utils.Constants;

public interface DRewardMapper {
	
	@SelectProvider(type=DRewardDynaSqlProvider.class,method="selectWhitParam")
	List<DReward> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=DRewardDynaSqlProvider.class,method="selectListWhitParam")
	List<DReward> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=DRewardDynaSqlProvider.class,method="findAll")
	List<DReward> findAll(String tableName,Timestamp dayTime,String forUpdate);
	
	@UpdateProvider(type=DRewardDynaSqlProvider.class,method="updateForState")
	Integer updateForState(String tableName,Timestamp dayTime,Integer state1,Integer state2);
	
	@SelectProvider(type=DRewardDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@UpdateProvider(type=DRewardDynaSqlProvider.class,method="createDReward")
	Integer createDReward(String tableName);
	
	@UpdateProvider(type=DRewardDynaSqlProvider.class,method="isExistTable")
	Integer isExistTable(String tableName);
	
	@UpdateProvider(type=DRewardDynaSqlProvider.class,method="returnSql")
	Integer delete(String sql);
	
	@UpdateProvider(type=DRewardDynaSqlProvider.class,method="dropDReward")
	Integer dropDReward(String tableName);
	
	@InsertProvider(type = DRewardDynaSqlProvider.class, method = "insertAll")  
	Integer insertAll(Map<String,Object> params);
	
	@SelectProvider(type=DRewardDynaSqlProvider.class,method="sumAward")
	@Results({
		@Result(column="sum(amount_1)", property="amount_1"),
		@Result(column="sum(amount_2)", property="amount_2"),
		@Result(column="sum(amount_3)", property="amount_3"),
		@Result(column="sum(amount_4)", property="amount_4"),
		@Result(column="sum(amount_5)", property="amount_5"),
		@Result(column="sum(amount_6)", property="amount_6"),
		@Result(column="sum(amount_7)", property="amount_7"),
		@Result(column="sum(amount_8)", property="amount_8"),
		@Result(column="sum(amount_9)", property="amount_9"),
		@Result(column="sum(amount_10)", property="amount_10"),
		@Result(column="sum(amount_11)", property="amount_11"),
		@Result(column="sum(amount_12)", property="amount_12")
	})
	DReward  sumAward(String tableName,User user); 
	
	@UpdateProvider(type=DRewardDynaSqlProvider.class,method="updateStateOfDReward")
	Integer updateStateOfDReward(String tableName,Timestamp dayTime,Integer id);
	
	@SelectProvider(type=DRewardDynaSqlProvider.class,method="findByUserId")
	DReward  findByUserId(String userId,String tableName); 
	
	@UpdateProvider(type=DRewardDynaSqlProvider.class,method="update")
	Integer update(Map<String,Object> params);

}
