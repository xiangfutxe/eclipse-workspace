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

import com.ssm.dao.provider.UserDynaSqlProvider;
import com.ssm.dao.provider.WRewardDynaSqlProvider;
import com.ssm.dao.provider.WSettleDynaSqlProvider;
import com.ssm.pojo.WSettle;
import com.ssm.utils.Constants;

public interface WSettleMapper {
		
	@SelectProvider(type=WSettleDynaSqlProvider.class,method="selectListWhitParam")
	public  List<WSettle> selectListWithParam(Map<String,Object> params);
	
	@SelectProvider(type=WSettleDynaSqlProvider.class,method="selectAll")
	public  List<WSettle> selectAll(Map<String,Object> params);
	
	@SelectProvider(type=WSettleDynaSqlProvider.class,method="selectWhitParam")
	public  List<WSettle> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=WSettleDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);

	
	@SelectProvider(type=WSettleDynaSqlProvider.class,method="selectByList")
	public  List<WSettle> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=WSettleDynaSqlProvider.class,method="findByUserId")
	WSettle findByUserId(String userId,String tableName);
	
	@Select("select * from "+Constants.WSETTLETABLE+"  where userId=#{userId}" )
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="state", property="state")
	})
	public WSettle selectByUserId(@Param("userId") String userId);
	
	@Delete("delete from "+Constants.WSETTLETABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=WSettleDynaSqlProvider.class,method="updateWSettle")
	void update(WSettle ws);
	
	@UpdateProvider(type=WSettleDynaSqlProvider.class,method="updateUserId")
	Integer updateUserId(WSettle ws);
	
	@InsertProvider(type=WSettleDynaSqlProvider.class,method="insertWSettle")
	Integer save(WSettle ws);
	
	@Update("update wsettle set state=#{state} where id=#{id}")
	Integer updateUsersForState(@Param("state") Integer state,@Param("id") Integer id);
	
	@Update("update wsettle set userId=#{userId} where userId=#{id}")
	Integer updateUserIdOfWst(@Param("userId") String userId,@Param("id") String id);
	
	@Update("update wsettle set userName=#{userName} where userId=#{id}")
	Integer updateUserNameOfWst(@Param("userName") String userName,@Param("id") String id);
	
	@UpdateProvider(type=WSettleDynaSqlProvider.class,method="createWSettle")
	Integer createWSettle(String tableName);
	
	@UpdateProvider(type=WSettleDynaSqlProvider.class,method="dropWSettle")
	Integer dropWSettle(String tableName);
	
	@InsertProvider(type = WSettleDynaSqlProvider.class, method = "insertAll")  
	Integer insertAll(Map<String,Object> params); 
	
	@UpdateProvider(type = WSettleDynaSqlProvider.class, method = "updateAll")  
	Integer updateAll(Map<String,Object> params); 
	

}
