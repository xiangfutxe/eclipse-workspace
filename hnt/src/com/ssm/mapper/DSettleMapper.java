package com.ssm.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.DRewardDynaSqlProvider;
import com.ssm.dao.provider.DSettleDynaSqlProvider;
import com.ssm.pojo.DSettle;
import com.ssm.utils.Constants;

public interface DSettleMapper {
		
	@SelectProvider(type=DSettleDynaSqlProvider.class,method="selectListWhitParam")
	public  List<DSettle> selectListWithParam(Map<String,Object> params);
	
	@SelectProvider(type=DSettleDynaSqlProvider.class,method="selectAll")
	public  List<DSettle> selectAll(Map<String,Object> params);
	
	@SelectProvider(type=DSettleDynaSqlProvider.class,method="selectWhitParam")
	public  List<DSettle> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=DSettleDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);

	
	@SelectProvider(type=DSettleDynaSqlProvider.class,method="selectByList")
	public  List<DSettle> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=DSettleDynaSqlProvider.class,method="findByUserId")
	DSettle findByUserId(String userId,String tableName);
	
	@Select("select * from "+Constants.DSETTLETABLE+"  where userId=#{userId}" )
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="state", property="state")
	})
	public DSettle selectByUserId(@Param("userId") String userId);
	
	@Delete("delete from "+Constants.DSETTLETABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=DSettleDynaSqlProvider.class,method="updateDSettle")
	Integer update(DSettle ws);
	
	@UpdateProvider(type=DSettleDynaSqlProvider.class,method="updateUserId")
	Integer updateUserId(DSettle ws);
	
	@InsertProvider(type=DSettleDynaSqlProvider.class,method="insertDSettle")
	Integer save(DSettle ws);
	
	@UpdateProvider(type=DSettleDynaSqlProvider.class,method="returnSql")
	Integer delete(String sql);
	
	@Update("update wsettle set state=#{state} where id=#{id}")
	Integer updateUsersForState(@Param("state") Integer state,@Param("id") Integer id);
	
	@Update("update wsettle set userId=#{userId} where userId=#{id}")
	Integer updateUserIdOfWst(@Param("userId") String userId,@Param("id") String id);
	
	@Update("update wsettle set userName=#{userName} where userId=#{id}")
	Integer updateUserNameOfWst(@Param("userName") String userName,@Param("id") String id);
	
	@UpdateProvider(type=DSettleDynaSqlProvider.class,method="createDSettle")
	Integer createDSettle(String tableName);
	
	@UpdateProvider(type=DSettleDynaSqlProvider.class,method="dropDSettle")
	Integer dropDSettle(String tableName);
	
	@InsertProvider(type = DSettleDynaSqlProvider.class, method = "insertAll")  
	Integer insertAll(Map<String,Object> params); 
	
	@UpdateProvider(type = DSettleDynaSqlProvider.class, method = "updateAll")  
	Integer updateAll(Map<String,Object> params); 
	
	@UpdateProvider(type = DSettleDynaSqlProvider.class, method = "returnSql")  
	Integer updateForSql(String sql); 
	

}
