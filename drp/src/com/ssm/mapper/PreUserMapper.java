package com.ssm.mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;


import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;






import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.UserDynaSqlProvider;
import com.ssm.pojo.PreUser;
import com.ssm.pojo.User;
import com.ssm.utils.Constants;
public interface PreUserMapper {
	
	
	@Select("select * from pre_users  where id=#{id}" )
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="state", property="state"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="rankJoin", property="rankJoin"),
		@Result(column="rankJoinOld", property="rankJoinOld"),
		@Result(column="rankManage", property="rankManage"),
		@Result(column="rank", property="rank"),
		@Result(column="userByBelongId", property="userByBelongId"),
		@Result(column="userByRefereeId", property="userByRefereeId"),
		@Result(column="userByAId", property="userByAId"),
		@Result(column="userByBId", property="userByBId"),
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="node", property="node"),
		@Result(column="nodeABC", property="nodeABC"),
		@Result(column="referee_id", property="refereeId"),
		@Result(column="belong_id", property="belongId"),
		@Result(column="declaration_id", property="declarationId"),
		@Result(column="refereeNode", property="refereeNode"),
		@Result(column="refereeAll", property="refereeAll"),
		@Result(column="nodeTag", property="nodeTag"),
		@Result(column="isEmpty", property="isEmpty"),
		@Result(column="payTag", property="payTag"),
		@Result(column="atpv", property="atpv"),
		@Result(column="btpv", property="btpv"),
		@Result(column="rtpv", property="trpv"),
		@Result(column="rtpv1", property="rtpv1"),
		@Result(column="totalPerformance", property="totalPerformance"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="totalNumReal", property="totalNumReal"),
		@Result(column="JoinPvTal", property="JoinPvTal"),
		@Result(column="JoinPvNew", property="JoinPvNew")

	})
	public PreUser selectUsersById(@Param("id") Integer id);
	
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectAllListWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="state", property="state"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="rankJoin", property="rankJoin"),
		@Result(column="rankJoinOld", property="rankJoinOld"),
		@Result(column="rankManage", property="rankManage"),
		@Result(column="rank", property="rank"),
		@Result(column="userByBelongId", property="userByBelongId"),
		@Result(column="userByRefereeId", property="userByRefereeId"),
		@Result(column="userByAId", property="userByAId"),
		@Result(column="userByBId", property="userByBId"),
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="node", property="node"),
		@Result(column="nodeABC", property="nodeABC"),
		@Result(column="referee_id", property="refereeId"),
		@Result(column="belong_id", property="belongId"),
		@Result(column="declaration_id", property="declarationId"),
		@Result(column="refereeNode", property="refereeNode"),
		@Result(column="refereeAll", property="refereeAll"),
		@Result(column="nodeTag", property="nodeTag"),
		@Result(column="isEmpty", property="isEmpty"),
		@Result(column="payTag", property="payTag"),
		@Result(column="atpv", property="atpv"),
		@Result(column="btpv", property="btpv"),
		@Result(column="rtpv", property="trpv"),
		@Result(column="rtpv1", property="rtpv1"),
		@Result(column="totalPerformance", property="totalPerformance"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="totalNumReal", property="totalNumReal"),
		@Result(column="JoinPvTal", property="JoinPvTal"),
		@Result(column="JoinPvNew", property="JoinPvNew")

	})
	public List<PreUser> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectAllWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="state", property="state"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="rankJoin", property="rankJoin"),
		@Result(column="rankJoinOld", property="rankJoinOld"),
		@Result(column="rankManage", property="rankManage"),
		@Result(column="rank", property="rank"),
		@Result(column="userByBelongId", property="userByBelongId"),
		@Result(column="userByRefereeId", property="userByRefereeId"),
		@Result(column="userByAId", property="userByAId"),
		@Result(column="userByBId", property="userByBId"),
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="node", property="node"),
		@Result(column="nodeABC", property="nodeABC"),
		@Result(column="referee_id", property="refereeId"),
		@Result(column="belong_id", property="belongId"),
		@Result(column="declaration_id", property="declarationId"),
		@Result(column="refereeNode", property="refereeNode"),
		@Result(column="refereeAll", property="refereeAll"),
		@Result(column="nodeTag", property="nodeTag"),
		@Result(column="isEmpty", property="isEmpty"),
		@Result(column="payTag", property="payTag"),
		@Result(column="atpv", property="atpv"),
		@Result(column="btpv", property="btpv"),
		@Result(column="rtpv", property="trpv"),
		@Result(column="rtpv1", property="rtpv1"),
		@Result(column="totalPerformance", property="totalPerformance"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="totalNumReal", property="totalNumReal"),
		@Result(column="JoinPvTal", property="JoinPvTal"),
		@Result(column="JoinPvNew", property="JoinPvNew")
	})
	public List<PreUser> selectUsersByPage(Map<String,Object> params);
	

	@Select("select * from pre_users where userId=#{userId}" )
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="state", property="state"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="rankJoin", property="rankJoin"),
		@Result(column="rankJoinOld", property="rankJoinOld"),
		@Result(column="rankManage", property="rankManage"),
		@Result(column="rank", property="rank"),
		@Result(column="userByBelongId", property="userByBelongId"),
		@Result(column="userByRefereeId", property="userByRefereeId"),
		@Result(column="userByAId", property="userByAId"),
		@Result(column="userByBId", property="userByBId"),
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="node", property="node"),
		@Result(column="nodeABC", property="nodeABC"),
		@Result(column="referee_id", property="refereeId"),
		@Result(column="belong_id", property="belongId"),
		@Result(column="declaration_id", property="declarationId"),
		@Result(column="refereeNode", property="refereeNode"),
		@Result(column="refereeAll", property="refereeAll"),
		@Result(column="nodeTag", property="nodeTag"),
		@Result(column="isEmpty", property="isEmpty"),
		@Result(column="payTag", property="payTag"),
		@Result(column="atpv", property="atpv"),
		@Result(column="btpv", property="btpv"),
		@Result(column="rtpv", property="trpv"),
		@Result(column="rtpv1", property="rtpv1"),
		@Result(column="totalPerformance", property="totalPerformance"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="totalNumReal", property="totalNumReal"),
		@Result(column="JoinPvTal", property="JoinPvTal"),
		@Result(column="JoinPvNew", property="JoinPvNew")
	})
	public PreUser selectByUserId(@Param("userId") String userId);
	
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectUserByListForUpdate")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="state", property="state"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="rankJoin", property="rankJoin"),
		@Result(column="rankJoinOld", property="rankJoinOld"),
		@Result(column="rankManage", property="rankManage"),
		@Result(column="rank", property="rank"),
		@Result(column="userByBelongId", property="userByBelongId"),
		@Result(column="userByRefereeId", property="userByRefereeId"),
		@Result(column="userByAId", property="userByAId"),
		@Result(column="userByBId", property="userByBId"),
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="node", property="node"),
		@Result(column="nodeABC", property="nodeABC"),
		@Result(column="referee_id", property="refereeId"),
		@Result(column="belong_id", property="belongId"),
		@Result(column="declaration_id", property="declarationId"),
		@Result(column="refereeNode", property="refereeNode"),
		@Result(column="refereeAll", property="refereeAll"),
		@Result(column="nodeTag", property="nodeTag"),
		@Result(column="isEmpty", property="isEmpty"),
		@Result(column="payTag", property="payTag"),
		@Result(column="atpv", property="atpv"),
		@Result(column="btpv", property="btpv"),
		@Result(column="rtpv", property="trpv"),
		@Result(column="rtpv1", property="rtpv1"),
		@Result(column="totalPerformance", property="totalPerformance"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="totalNumReal", property="totalNumReal"),
		@Result(column="JoinPvTal", property="JoinPvTal"),
		@Result(column="JoinPvNew", property="JoinPvNew")
	})
	public List<User> selectUserByListForUpdate(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectUserBySql")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="state", property="state"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="rankJoin", property="rankJoin"),
		@Result(column="rankJoinOld", property="rankJoinOld"),
		@Result(column="rankManage", property="rankManage"),
		@Result(column="rank", property="rank"),
		@Result(column="userByBelongId", property="userByBelongId"),
		@Result(column="userByRefereeId", property="userByRefereeId"),
		@Result(column="userByAId", property="userByAId"),
		@Result(column="userByBId", property="userByBId"),
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="node", property="node"),
		@Result(column="nodeABC", property="nodeABC"),
		@Result(column="referee_id", property="refereeId"),
		@Result(column="belong_id", property="belongId"),
		@Result(column="declaration_id", property="declarationId"),
		@Result(column="refereeNode", property="refereeNode"),
		@Result(column="refereeAll", property="refereeAll"),
		@Result(column="nodeTag", property="nodeTag"),
		@Result(column="isEmpty", property="isEmpty"),
		@Result(column="payTag", property="payTag"),
		@Result(column="atpv", property="atpv"),
		@Result(column="btpv", property="btpv"),
		@Result(column="rtpv", property="trpv"),
		@Result(column="rtpv1", property="rtpv1"),
		@Result(column="totalPerformance", property="totalPerformance"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="totalNumReal", property="totalNumReal"),
		@Result(column="JoinPvTal", property="JoinPvTal"),
		@Result(column="JoinPvNew", property="JoinPvNew")
	})
	public List<PreUser> selectUserBySql(String sql);
	
	@Select("select * from users  where userId=#{userId} for update" )
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="state", property="state"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="rankJoin", property="rankJoin"),
		@Result(column="rankJoinOld", property="rankJoinOld"),
		@Result(column="rankManage", property="rankManage"),
		@Result(column="rank", property="rank"),
		@Result(column="userByBelongId", property="userByBelongId"),
		@Result(column="userByRefereeId", property="userByRefereeId"),
		@Result(column="userByAId", property="userByAId"),
		@Result(column="userByBId", property="userByBId"),
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="node", property="node"),
		@Result(column="nodeABC", property="nodeABC"),
		@Result(column="referee_id", property="refereeId"),
		@Result(column="belong_id", property="belongId"),
		@Result(column="declaration_id", property="declarationId"),
		@Result(column="refereeNode", property="refereeNode"),
		@Result(column="refereeAll", property="refereeAll"),
		@Result(column="nodeTag", property="nodeTag"),
		@Result(column="isEmpty", property="isEmpty"),
		@Result(column="payTag", property="payTag"),
		@Result(column="atpv", property="atpv"),
		@Result(column="btpv", property="btpv"),
		@Result(column="rtpv", property="trpv"),
		@Result(column="rtpv1", property="rtpv1"),
		@Result(column="totalPerformance", property="totalPerformance"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="totalNumReal", property="totalNumReal"),
		@Result(column="JoinPvTal", property="JoinPvTal"),
		@Result(column="JoinPvNew", property="JoinPvNew")
	})
	public PreUser selectByUserIdForUpdate(@Param("userId") String userId);

	
	
	
	
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="state", property="state"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="rankJoin", property="rankJoin"),
		@Result(column="rankJoinOld", property="rankJoinOld"),
		@Result(column="rankManage", property="rankManage"),
		@Result(column="rank", property="rank"),
		@Result(column="userByBelongId", property="userByBelongId"),
		@Result(column="userByRefereeId", property="userByRefereeId"),
		@Result(column="userByAId", property="userByAId"),
		@Result(column="userByBId", property="userByBId"),
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="node", property="node"),
		@Result(column="nodeABC", property="nodeABC"),
		@Result(column="referee_id", property="refereeId"),
		@Result(column="belong_id", property="belongId"),
		@Result(column="declaration_id", property="declarationId"),
		@Result(column="refereeNode", property="refereeNode"),
		@Result(column="refereeAll", property="refereeAll"),
		@Result(column="nodeTag", property="nodeTag"),
		@Result(column="isEmpty", property="isEmpty"),
		@Result(column="payTag", property="payTag"),
		@Result(column="atpv", property="atpv"),
		@Result(column="btpv", property="btpv"),
		@Result(column="rtpv", property="trpv"),
		@Result(column="rtpv1", property="rtpv1"),
		@Result(column="totalPerformance", property="totalPerformance"),
		@Result(column="totalNum", property="totalNum"),
		@Result(column="totalNumReal", property="totalNumReal"),
		@Result(column="JoinPvTal", property="JoinPvTal"),
		@Result(column="JoinPvNew", property="JoinPvNew")
	})
	List<PreUser> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="countAll")
	Integer countAll(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="maxId")
	Integer maxId(Map<String,Object> params);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateForSql")
	Integer updateForSql( String sql);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="InsertAll")
	Integer InsertAll(Map<String,Object> params);
	
	


}

