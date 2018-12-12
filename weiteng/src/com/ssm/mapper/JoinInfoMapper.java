package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.JoinInfoDynaSqlProvider;
import com.ssm.pojo.JoinInfo;
import com.ssm.utils.Constants;

public interface JoinInfoMapper {
		
	@SelectProvider(type=JoinInfoDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="uId", property="uid"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="oldRankJoin", property="oldRankJoin"),
		@Result(column="newRankJoin", property="newRankJoin"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="rid", property="rid"),
		@Result(column="declarationId", property="declarationId"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="tag", property="tag")
	})
	List<JoinInfo> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=JoinInfoDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=JoinInfoDynaSqlProvider.class,method="selectListWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="uId", property="uid"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="oldRankJoin", property="oldRankJoin"),
		@Result(column="newRankJoin", property="newRankJoin"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="rid", property="rid"),
		@Result(column="declarationId", property="declarationId"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="tag", property="tag")
	})
	List<JoinInfo> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=JoinInfoDynaSqlProvider.class,method="selectAllListWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="uId", property="uid"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="oldRankJoin", property="oldRankJoin"),
		@Result(column="newRankJoin", property="newRankJoin"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="rid", property="rid"),
		@Result(column="declarationId", property="declarationId"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="tag", property="tag")
	})
	List<JoinInfo> selectAllByList(Map<String,Object> params);
	
	@Select("select * from "+Constants.JOININFOTABLE+" ")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="uId", property="uid"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="oldRankJoin", property="oldRankJoin"),
		@Result(column="newRankJoin", property="newRankJoin"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="rid", property="rid"),
		@Result(column="declarationId", property="declarationId"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="tag", property="tag")

	})
	List<JoinInfo> selectAllJoinInfo();
	
	@Select("select * from "+Constants.JOININFOTABLE+" where state=2")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="uId", property="uid"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="oldRankJoin", property="oldRankJoin"),
		@Result(column="newRankJoin", property="newRankJoin"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="rid", property="rid"),
		@Result(column="declarationId", property="declarationId"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="tag", property="tag")

	})
	List<JoinInfo> selectJoinInfoByAll();
	
	
	@Select("select * from "+Constants.JOININFOTABLE+" where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="uId", property="uid"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="oldRankJoin", property="oldRankJoin"),
		@Result(column="newRankJoin", property="newRankJoin"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="rid", property="rid"),
		@Result(column="declarationId", property="declarationId"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="tag", property="tag")
	})
	JoinInfo selectById(Integer id);
	
	@Select("select * from "+Constants.JOININFOTABLE+" where userId=#{userId} and state>0")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="uId", property="uid"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="oldRankJoin", property="oldRankJoin"),
		@Result(column="newRankJoin", property="newRankJoin"),
		@Result(column="state", property="state"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="rid", property="rid"),
		@Result(column="declarationId", property="declarationId"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="tag", property="tag")
	})
	List<JoinInfo> selectByUserId(String userId);
	
	@Update("update "+Constants.JOININFOTABLE+" set state=#{state} where id=#{id}")
	Integer updateForState(@Param("state") Integer state,@Param("id") Integer id);
	
	@Delete("delete from "+Constants.JOININFOTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=JoinInfoDynaSqlProvider.class,method="updateJoinInfo")
	Integer update(JoinInfo joinInfo);
	
	@Update("update joinInfo set rid=#{id} where userId=#{userId}")
	Integer updateRIdForUserId(@Param("id")Integer id,@Param("userId") String userId);
	
	@InsertProvider(type=JoinInfoDynaSqlProvider.class,method="insertJoinInfo")
	Integer save(JoinInfo joinInfo);
	
	@Update("update joinInfo set userId=#{userId} where userId=#{id}")
	Integer updateUserIdOfJoinInfo(@Param("userId") String userId,@Param("id") String id);
	
	

}
