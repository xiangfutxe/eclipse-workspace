package com.ssm.mapper;

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
import com.ssm.dao.provider.WRewardDynaSqlProvider;
import com.ssm.pojo.Address;
import com.ssm.pojo.User;
import com.ssm.pojo.WReward;
import com.ssm.utils.Constants;

public interface WRewardMapper {
	
	@SelectProvider(type=WRewardDynaSqlProvider.class,method="selectWhitParam")
	List<WReward> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=WRewardDynaSqlProvider.class,method="selectListWhitParam")
	List<WReward> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=WRewardDynaSqlProvider.class,method="findAll")
	List<WReward> findAll(String tableName,String forUpdate);
	
	@UpdateProvider(type=WRewardDynaSqlProvider.class,method="updateForState")
	Integer updateForState(String tableName,Integer state1,Integer state2);
	
	@SelectProvider(type=WRewardDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@UpdateProvider(type=WRewardDynaSqlProvider.class,method="createWReward")
	Integer createWReward(String tableName);
	
	@UpdateProvider(type=WRewardDynaSqlProvider.class,method="dropWReward")
	Integer dropWReward(String tableName);
	
	@InsertProvider(type = WRewardDynaSqlProvider.class, method = "insertAll")  
	Integer insertAll(Map<String,Object> params);
	
	@SelectProvider(type=WRewardDynaSqlProvider.class,method="sumAward")
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
	WReward  sumAward(String tableName,User user); 
	
	@UpdateProvider(type=WRewardDynaSqlProvider.class,method="updateStateOfWReward")
	Integer updateStateOfWReward(String tableName,Integer id);
	
	@SelectProvider(type=WRewardDynaSqlProvider.class,method="findByUserId")
	WReward  findByUserId(String userId,String tableName); 
	
	@UpdateProvider(type=WRewardDynaSqlProvider.class,method="update")
	Integer update(Map<String,Object> params);

}
