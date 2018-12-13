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

import com.ssm.dao.provider.WRewardExtremeDynaSqlProvider;
import com.ssm.pojo.User;
import com.ssm.pojo.WRewardExtreme;

public interface WRewardExtremeMapper {
	
	@SelectProvider(type=WRewardExtremeDynaSqlProvider.class,method="selectWhitParam")
	List<WRewardExtreme> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=WRewardExtremeDynaSqlProvider.class,method="selectListWhitParam")
	List<WRewardExtreme> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=WRewardExtremeDynaSqlProvider.class,method="findAll")
	List<WRewardExtreme> findAll(String tableName,String forUpdate);
	
	@UpdateProvider(type=WRewardExtremeDynaSqlProvider.class,method="updateForState")
	Integer updateForState(String tableName,Integer state1,Integer state2);
	
	@SelectProvider(type=WRewardExtremeDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@UpdateProvider(type=WRewardExtremeDynaSqlProvider.class,method="createWRewardExtreme")
	Integer createWRewardExtreme(String tableName);
	
	@UpdateProvider(type=WRewardExtremeDynaSqlProvider.class,method="dropWRewardExtreme")
	Integer dropWRewardExtreme(String tableName);
	
	@InsertProvider(type = WRewardExtremeDynaSqlProvider.class, method = "insertAll")  
	Integer insertAll(Map<String,Object> params);
	
	@SelectProvider(type=WRewardExtremeDynaSqlProvider.class,method="sumAward")
	@Results({
		@Result(column="sum(amount_1)", property="amount_1"),
		@Result(column="sum(amount_2)", property="amount_2"),
		@Result(column="sum(amount_3)", property="amount_3"),
		@Result(column="sum(amount_4)", property="amount_4")
	})
	WRewardExtreme  sumAward(String tableName,User user); 
	
	@UpdateProvider(type=WRewardExtremeDynaSqlProvider.class,method="updateStateOfWRewardExtreme")
	Integer updateStateOfWRewardExtreme(String tableName,Integer id);
	
	@SelectProvider(type=WRewardExtremeDynaSqlProvider.class,method="findByUserId")
	WRewardExtreme  findByUserId(String userId,String tableName); 

}
