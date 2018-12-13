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
import com.ssm.pojo.User;
import com.ssm.utils.Constants;
public interface UserMapper {
	@Select("select us.id id,us.userId userId,us.userName userName,us.rankJoin rankJoin,us.visit_time visitTime,us.visit_num visitNum,"
			+ "us.rankJoinOld rankJoinOld,us.rankManage rankManage,us.rank rank,us.empty_num emptyNum,us.empty_num_tol emptyNumTol,"
			+ "us.authority authority,us.is_max isMax,us.emoney emoney,us.dmoney dmoney,us.rmoney rmoney,us.pmoney pmoney,"
			+ "us.userByBelongId userByBelongId,us.userByRefereeId userByRefereeId,us.userByAId userByAId,us.userByBId userByBId,"
			+ "us.userByDeclarationId userByDeclarationId,us.user_id_by_belong_center userIdByBelongCenter,"
			+ "us.referee_id refereeId,us.belong_id belongId,us.declaration_id declarationId,"
			+ "us.id_by_belong_center idByBelongCenter,us.node node,us.nodeABC nodeABC,us.refereeNode refereeNode,us.refereeAll refereeAll,us.nodeTag nodeTag,"
			+ "us.raise_num raiseNum,us.payTag payTag,us.cap_factor capFactor,us.state state, us.entryTime entryTime,"
			+ "us.centerId centerId,us.centerType centerType,uf.password password,uf.password2 password2,"
			+ "uf.documentType documentType,uf.numId numId,uf.sex sex,uf.tel tel,uf.province province,"
			+ "uf.city city,uf.area area,uf.address address,uf.accountId accountId,uf.accountName accountName,"
			+ "uf.bankAdr bankAdr,uf.bankName bankName from users us left join userinfo uf on us.userId=uf.userId "
			+ " where us.userId=#{userId} and uf.password=#{password}" )
	public User login(@Param("userId") String userId,@Param("password") String password);
	
	@Select("select us.id id,us.userId userId,us.userName userName,us.rankJoin rankJoin,us.visit_time visitTime,us.visit_num visitNum,"
			+ "us.rankJoinOld rankJoinOld,us.rankManage rankManage,us.rank rank,us.empty_num emptyNum,us.empty_num_tol emptyNumTol,"
			+ "us.authority authority,us.is_max isMax,us.emoney emoney,us.dmoney dmoney,us.rmoney rmoney,us.pmoney pmoney,"
			+ "us.userByBelongId userByBelongId,us.userByRefereeId userByRefereeId,us.userByAId userByAId,us.userByBId userByBId,"
			+ "us.userByDeclarationId userByDeclarationId,us.user_id_by_belong_center userIdByBelongCenter,"
			+ "us.referee_id refereeId,us.belong_id belongId,us.declaration_id declarationId,"
			+ "us.id_by_belong_center idByBelongCenter,us.node node,us.nodeABC nodeABC,us.refereeNode refereeNode,us.refereeAll refereeAll,us.nodeTag nodeTag,"
			+ "us.raise_num raiseNum,us.payTag payTag,us.cap_factor capFactor,us.state state, us.entryTime entryTime,"
			+ "us.centerId centerId,us.centerType centerType,uf.password password,uf.password2 password2,"
			+ "uf.documentType documentType,uf.numId numId,uf.sex sex,uf.tel tel,uf.province province,"
			+ "uf.city city,uf.area area,uf.address address,uf.accountId accountId,uf.accountName accountName,"
			+ "uf.bankAdr bankAdr,uf.bankName bankName from users us left join userinfo uf on us.userId=uf.userId where us.userId=#{userId}" )
	public User selectAllByUserId(@Param("userId") String userId);
	
	@Select("select * from "+Constants.USERINFOTABLE+"  where userId=#{userId}" )
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="password", property="password"),
		@Result(column="password2", property="password2"),
		@Result(column="documentType", property="documentType"),
		@Result(column="sex", property="sex"),
		@Result(column="age", property="age"),
		@Result(column="numId", property="numId"),
		@Result(column="tel", property="tel"),
		@Result(column="province", property="province"),
		@Result(column="city", property="city"),
		@Result(column="area", property="area"),
		@Result(column="address", property="address"),
		@Result(column="accountId", property="accountId"),
		@Result(column="accountName", property="accountName"),
		@Result(column="bankAdr", property="bankAdr"),
		@Result(column="bankName", property="bankName"),
		@Result(column="state", property="state")
	})
	public User selectUserInfoByUserId(@Param("userId") String userId);
	
	
	
	@Select("select id, userId,userName,emoney,smoney,dmoney,rmoney,pmoney,payTag from users  where userId=#{userId}" )
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="emoney", property="emoney"),
		@Result(column="smoney", property="smoney"),
		@Result(column="dmoney", property="dmoney"),
		@Result(column="rmoney", property="rmoney"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="payTag", property="payTag")
	})
	public User selectMoneyByUserId(@Param("userId") String userId);
	
	@Select("select * from users  where id=#{id}" )
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="emoney", property="emoney"),
		@Result(column="smoney", property="smoney"),
		@Result(column="dmoney", property="dmoney"),
		@Result(column="rmoney", property="rmoney"),
		@Result(column="pmoney", property="pmoney"),
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
		@Result(column="authority", property="authority"),
		@Result(column="is_max", property="isMax"),
		@Result(column="payTag", property="payTag"),
		@Result(column="raise_num", property="raiseNum"),
		@Result(column="cap_factor", property="capFactor")
	})
	public User selectUsersById(@Param("id") Integer id);
	
	@Select("select id, userId,userName,emoney,smoney,dmoney,rmoney,pmoney,payTag from users  where userId=#{userId} for update" )
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="emoney", property="emoney"),
		@Result(column="smoney", property="smoney"),
		@Result(column="dmoney", property="dmoney"),
		@Result(column="rmoney", property="rmoney"),
		@Result(column="payTag", property="payTag"),
		@Result(column="pmoney", property="pmoney")
	})
	public User selectMoneyByUserIdForUpdate(@Param("userId") String userId);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectAllListWhitParam")
	public List<User> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectAllWhitParam")
	public List<User> selectUsersByPage(Map<String,Object> params);
	

	@Select("select us.id id,us.userId userId,us.userName userName,us.rankJoin rankJoin,us.visit_time visitTime,us.visit_num visitNum,"
			+ "us.rankJoinOld rankJoinOld,us.rankManage rankManage,us.rank rank,us.empty_num emptyNum,us.empty_num_tol emptyNumTol,"
			+ "us.authority authority,us.is_max isMax,us.emoney emoney,us.dmoney dmoney,us.rmoney rmoney,us.pmoney pmoney,"
			+ "us.userByBelongId userByBelongId,us.userByRefereeId userByRefereeId,us.userByAId userByAId,us.userByBId userByBId,"
			+ "us.userByDeclarationId userByDeclarationId,us.user_id_by_belong_center userIdByBelongCenter,"
			+ "us.referee_id refereeId,us.belong_id belongId,us.declaration_id declarationId,"
			+ "us.id_by_belong_center idByBelongCenter,us.node node,us.nodeABC nodeABC,us.refereeNode refereeNode,us.refereeAll refereeAll,us.nodeTag nodeTag,"
			+ "us.raise_num raiseNum,us.payTag payTag,us.cap_factor capFactor,us.state state, us.entryTime entryTime,"
			+ "us.centerId centerId,us.centerType centerType,uf.password password,uf.password2 password2,"
			+ "uf.documentType documentType,uf.numId numId,uf.sex sex,uf.tel tel,uf.province province,"
			+ "uf.city city,uf.area area,uf.address address,uf.accountId accountId,uf.accountName accountName,"
			+ "uf.bankAdr bankAdr,uf.bankName bankName from users us left join userinfo uf on us.userId=uf.userId where us.userId=#{userId}" )
	public User selectByUserId(@Param("userId") String userId);
	
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectUserByListForUpdate")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="emoney", property="emoney"),
		@Result(column="smoney", property="smoney"),
		@Result(column="dmoney", property="dmoney"),
		@Result(column="rmoney", property="rmoney"),
		@Result(column="pmoney", property="pmoney"),
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
		@Result(column="authority", property="authority"),
		@Result(column="is_max", property="isMax"),
		@Result(column="payTag", property="payTag"),
		@Result(column="raise_num", property="raiseNum"),
		@Result(column="visit_num", property="visitNum"),
		@Result(column="visit_time", property="visitTime"),
		@Result(column="cap_factor", property="capFactor")
	})
	public List<User> selectUserByListForUpdate(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectUserBySql")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="emoney", property="emoney"),
		@Result(column="smoney", property="smoney"),
		@Result(column="dmoney", property="dmoney"),
		@Result(column="rmoney", property="rmoney"),
		@Result(column="pmoney", property="pmoney"),
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
		@Result(column="payTag", property="payTag"),
		@Result(column="authority", property="authority"),
		@Result(column="is_max", property="isMax"),
		@Result(column="raise_num", property="raiseNum"),
		@Result(column="cap_factor", property="capFactor")
	})
	public List<User> selectUserBySql(String sql);
	
	@Select("select id, userId,userName,emoney,smoney,dmoney,rmoney,pmoney,payTag from users  where id=#{id} for update" )
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="emoney", property="emoney"),
		@Result(column="smoney", property="smoney"),
		@Result(column="dmoney", property="dmoney"),
		@Result(column="rmoney", property="rmoney"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="payTag", property="payTag")
	})
	public User selectMoneyByIdForUpdate(@Param("id") Integer id);
	
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
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="userByAId", property="userByAId"),
		@Result(column="userByBId", property="userByBId"),
		@Result(column="node", property="node"),
		@Result(column="nodeABC", property="nodeABC"),
		@Result(column="refereeNode", property="refereeNode"),
		@Result(column="refereeAll", property="refereeAll"),
		@Result(column="nodeTag", property="nodeTag"),
		@Result(column="empty_num", property="emptyNum"),
		@Result(column="empty_num_tol", property="emptyNumTol"),
		@Result(column="authority", property="authority"),
		@Result(column="is_max", property="isMax"),
		@Result(column="payTag", property="payTag"),
		@Result(column="raise_num", property="raiseNum"),
		@Result(column="cap_factor", property="capFactor")
	})
	public User selectByUserIdForUpdate(@Param("userId") String userId);

	
	@Select("select * from users  where userByBelongId=#{userId} and nodeTag=#{nodeTag} and state>0" )
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
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="node", property="node"),
		@Result(column="nodeABC", property="nodeABC"),
		@Result(column="refereeNode", property="refereeNode"),
		@Result(column="refereeAll", property="refereeAll"),
		@Result(column="nodeTag", property="nodeTag"),
		@Result(column="authority", property="authority"),
		@Result(column="is_max", property="isMax"),
		@Result(column="payTag", property="payTag"),
		@Result(column="raise_num", property="raiseNum"),
		@Result(column="cap_factor", property="capFactor")
	})
	public List<User> selectByNodeTag(@Param("userId") String userId,@Param("nodeTag") Integer nodeTag);
	
	@Select("select us.id id,us.userId userId,us.userName userName,us.rankJoin rankJoin,us.visit_time visitTime,us.visit_num visitNum,"
			+ "us.rankJoinOld rankJoinOld,us.rankManage rankManage,us.rank rank,us.empty_num emptyNum,us.empty_num_tol emptyNumTol,"
			+ "us.authority authority,us.is_max isMax,us.emoney emoney,us.dmoney dmoney,us.rmoney rmoney,us.pmoney pmoney,"
			+ "us.userByBelongId userByBelongId,us.userByRefereeId userByRefereeId,us.userByAId userByAId,us.userByBId userByBId,"
			+ "us.userByDeclarationId userByDeclarationId,us.user_id_by_belong_center userIdByBelongCenter,"
			+ "us.referee_id refereeId,us.belong_id belongId,us.declaration_id declarationId,"
			+ "us.id_by_belong_center idByBelongCenter,us.node node,us.nodeABC nodeABC,us.refereeNode refereeNode,us.refereeAll refereeAll,us.nodeTag nodeTag,"
			+ "us.raise_num raiseNum,us.payTag payTag,us.cap_factor capFactor,us.state state, us.entryTime entryTime,"
			+ "us.centerId centerId,us.centerType centerType,uf.password password,uf.password2 password2,"
			+ "uf.documentType documentType,uf.numId numId,uf.sex sex,uf.tel tel,uf.province province,"
			+ "uf.city city,uf.area area,uf.address address,uf.accountId accountId,uf.accountName accountName,"
			+ "uf.bankAdr bankAdr,uf.bankName bankName from users us left join userinfo uf on us.userId=uf.userId where us.id=#{id}" )
	public User selectById(@Param("id") Integer id);
	
	@Select("select * from users where entryTime=#{entryTime}" )
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="emoney", property="emoney"),
		@Result(column="smoney", property="smoney"),
		@Result(column="dmoney", property="dmoney"),
		@Result(column="rmoney", property="rmoney"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="state", property="state"),
		@Result(column="payTag", property="payTag"),
		@Result(column="chuangye_award", property="chuangyeAward"),
		@Result(column="chuangye_award_paid", property="chuangyeAwardPaid"),
		@Result(column="chuangye_award_set", property="chuangyeAwardSet"),
		@Result(column="chuangye_validty_time", property="chuangyeValidtyTime"),
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
		@Result(column="authority", property="authority"),
		@Result(column="is_max", property="isMax"),
		@Result(column="raise_num", property="raiseNum"),
		@Result(column="cap_factor", property="capFactor"),
		@Result(column="entryTime", property="entryTime")
	})
	public User selectByEntryTime(@Param("entryTime") Timestamp entryTime);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="emoney", property="emoney"),
		@Result(column="smoney", property="smoney"),
		@Result(column="dmoney", property="dmoney"),
		@Result(column="rmoney", property="rmoney"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="rankJoin", property="rankJoin"),
		@Result(column="rankJoinOld", property="rankJoinOld"),
		@Result(column="rankManage", property="rankManage"),
		@Result(column="userByBelongId", property="userByBelongId"),
		@Result(column="userByRefereeId", property="userByRefereeId"),
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="node", property="node"),
		@Result(column="nodeTag", property="nodeTag"),
		@Result(column="nodeABC", property="nodeABC"),
		@Result(column="referee_id", property="refereeId"),
		@Result(column="refereeNode", property="refereeNode"),
		@Result(column="refereeAll", property="refereeAll"),
		@Result(column="payTag", property="payTag"),
		@Result(column="raise_num", property="raiseNum"),
		@Result(column="isEmpty", property="isEmpty"),
		@Result(column="totalIncome", property="totalIncome"),
		@Result(column="state", property="state"),
		@Result(column="prov_id", property="provId"),
		@Result(column="empty_num", property="emptyNum"),
		@Result(column="empty_num_tol", property="emptyNumTol"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="authority", property="authority"),
		@Result(column="is_max", property="isMax"),
		@Result(column="raise_num", property="raiseNum"),
		@Result(column="cap_factor", property="capFactor")
	})
	List<User> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="emoney", property="emoney"),
		@Result(column="smoney", property="smoney"),
		@Result(column="dmoney", property="dmoney"),
		@Result(column="rmoney", property="rmoney"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="rankJoin", property="rankJoin"),
		@Result(column="rankJoinOld", property="rankJoinOld"),
		@Result(column="rankManage", property="rankManage"),
		@Result(column="rank", property="rank"),
		@Result(column="userByBelongId", property="userByBelongId"),
		@Result(column="userByRefereeId", property="userByRefereeId"),
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="node", property="node"),
		@Result(column="nodeTag", property="nodeTag"),
		@Result(column="nodeABC", property="nodeABC"),
		@Result(column="referee_id", property="refereeId"),
		@Result(column="refereeNode", property="refereeNode"),
		@Result(column="refereeAll", property="refereeAll"),
		@Result(column="payTag", property="payTag"),
		@Result(column="centerType", property="centerType"),
		@Result(column="centerId", property="centerId"),
		@Result(column="joinPrice", property="joinPrice"),
		@Result(column="raise_num", property="raiseNum"),
		@Result(column="isEmpty", property="isEmpty"),
		@Result(column="state", property="state"),
		@Result(column="entryTime", property="entryTime")
	})
	List<User> selectMoneyByPage(Map<String,Object> params);
	
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="countAll")
	Integer countAll(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="maxId")
	Integer maxId(Map<String,Object> params);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateUser")
	Integer updateUser(User user);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateUserInfo")
	Integer updateUserInfo(User user);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateUserForField")
	Integer updateUserForField(String field,String value,String id);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateUserForField2")
	Integer updateUserForField2(String field1,String value1,String field2,String value2,String id);
	
	@Update("update userinfo set userId=#{userId} where userId=#{id}")
	Integer updateUserIdOfUserInfo(@Param("userId") String userId,@Param("id") String id);
	
	@Update("update users set userId=#{userId} where userId=#{id}")
	Integer updateUserIdOfUsers(@Param("userId") String userId,@Param("id") String id);
	
	@Update("update users set state=#{state} where userId=#{userId}")
	Integer updateUsersForState(@Param("state") Integer state,@Param("userId") String userId);
	
	@Update("update userinfo set state=#{state} where userId=#{userId}")
	Integer updateUserInfoForState(@Param("state") Integer state,@Param("userId") String userId);
	
	@InsertProvider(type=UserDynaSqlProvider.class,method="insertUser")
	@Options(useGeneratedKeys=true, keyProperty="id")
	Integer saveUser(User user);
	
	@InsertProvider(type=UserDynaSqlProvider.class,method="insertUserInfo")
	Integer saveUserInfo(User user);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updatePropertyOfUsers")
	Integer updateUsersForProperty(@Param("property") String property,@Param("value") String value,@Param("id") Integer id,@Param("property1") String property1,@Param("value1") String value1);

	@UpdateProvider(type=UserDynaSqlProvider.class,method="updatePropertyOfUserInfo")
	Integer updateUserInfoForProperty( String property,String value,String property1,String value1);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateOfUsers")
	Integer updateOfUsers( String property,String value,String property1,String value1);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateForSql")
	Integer updateForSql( String sql);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectPropertyOfUsers")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="emoney", property="emoney"),
		@Result(column="smoney", property="smoney"),
		@Result(column="dmoney", property="dmoney"),
		@Result(column="rmoney", property="rmoney"),
		@Result(column="pmoney", property="pmoney"),
		@Result(column="state", property="state"),
		@Result(column="chuangye_award", property="chuangyeAward"),
		@Result(column="chuangye_award_paid", property="chuangyeAwardPaid"),
		@Result(column="chuangye_award_set", property="chuangyeAwardSet"),
		@Result(column="chuangye_validty_time", property="chuangyeValidtyTime"),
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
		@Result(column="nodeTag", property="nodeTag")
		
	})
	List<User> selectUsersForProperty(@Param("property") String property,@Param("value") Integer value);

}

