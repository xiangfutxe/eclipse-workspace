package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.User;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;

public class UserDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM("users");
			if(params.get("user")!=null){
				User user = (User) params.get("user");
				if(user.getUserId()!=null && !user.getUserId().equals("")){
					WHERE("  userId LIKE CONCAT ('%',#{user.userId},'%')");
				}
				if(user.getUserByRefereeId()!=null && !user.getUserByRefereeId().equals("")){
					WHERE("  userByRefereeId LIKE CONCAT ('%',#{user.userByRefereeId},'%')");
				}
				if(user.getUserByBelongId()!=null && !user.getUserByBelongId().equals("")){
					WHERE("  userByBelongId LIKE CONCAT ('%',#{user.userByBelongId},'%')");
				}
				if(user.getUserByDeclarationId()!=null && !user.getUserByDeclarationId().equals("")){
					WHERE("  userByDeclarationId LIKE CONCAT ('%',#{user.userByDeclarationId},'%')");
				}
				if(user.getRankJoin()!=null && user.getRankJoin()!=0){
					WHERE("  rankJoin LIKE CONCAT ('%',#{user.rankJoin},'%')");
				}
				if(user.getRankManage()!=null && user.getRankManage()!=0){
					WHERE("  rankManage LIKE CONCAT ('%',#{user.rankManage},'%')");
				}
				if(user.getRank()!=null && user.getRank()!=0){
					WHERE("  rank LIKE CONCAT ('%',#{user.rank},'%')");
				}
				if(user.getState()!=null && user.getState()!=0){
					WHERE("  state LIKE CONCAT ('%',#{user.state},'%')");
				}
				if(user.getIsHide()!=null ){
					WHERE("  is_hide =#{user.isHide}");
				}
				if(user.getStartTime()!=null){
					WHERE("  entryTime >= #{user.startTime}");
				}
				if(user.getEndTime()!=null){
					WHERE("  entryTime <= #{user.endTime}");
				}
				WHERE("  state>0 ");
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by entryTime desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String selectAllListWhitParam(final Map<String,Object> params){
		String str ="";
		if(params.get("user")!=null){
			User user = (User) params.get("user");
			if(user.getUserId()!=null && !user.getUserId().equals("")){
				str = str+" and  us.userId like '%"+user.getUserId()+"%'";
			}
			if(user.getUserByRefereeId()!=null && !user.getUserByRefereeId().equals("")){
				str = str+" and  us.userByRefereeId like '%"+user.getUserByRefereeId()+"%'";
			}
			if(user.getUserByBelongId()!=null && !user.getUserByBelongId().equals("")){
				str = str+" and  us.userByBelongId like '%"+user.getUserByBelongId()+"%'";
			}
			if(user.getUserByDeclarationId()!=null && !user.getUserByDeclarationId().equals("")){
				str = str+" and  us.userByDeclarationId like '%"+user.getUserByDeclarationId()+"%'";
			}
			if(user.getRankJoin()!=null && user.getRankJoin()!=0){
				str = str+" and  us.rankJoin like '%"+user.getRankJoin()+"%'";
			}
			if(user.getRankManage()!=null && user.getRankManage()!=0){
				str = str+" and  us.rankManage like '%"+user.getRankManage()+"%'";
			}
			if(user.getState()!=null && user.getState()!=0){
				str = str+" and  us.state like '%"+user.getState()+"%'";
			}
			if(user.getIsHide()!=null ){
				str = str+" and  us.is_hide like '%"+user.getIsHide()+"%'";
			}
			if(user.getStartTime()!=null){
				str = str+" and  us.entryTime >= '"+user.getStartTime()+"'";
			}
			if(user.getEndTime()!=null){
				str = str+" and  us.entryTime <='"+user.getEndTime()+"'";
			}
		}
		String sql = "select us.id id,us.userId userId,us.userName userName,us.rankJoin rankJoin,us.visit_time visitTime,us.visit_num visitNum,"
			+ "us.rankJoinOld rankJoinOld,us.rankManage rankManage,us.rank rank,"
			+ "us.authority authority,us.is_max isMax,us.emoney emoney,us.dmoney dmoney,us.rmoney rmoney,us.pmoney pmoney,"
			+ "us.userByBelongId userByBelongId,us.userByRefereeId userByRefereeId,us.userByAId userByAId,us.userByBId userByBId,"
			+ "us.userByDeclarationId userByDeclarationId,us.user_id_by_belong_center userIdByBelongCenter,"
			+ "us.referee_id refereeId,us.belong_id belongId,us.declaration_id declarationId,"
			+ "us.id_by_belong_center idByBelongCenter,us.node node,us.nodeABC nodeABC,us.refereeNode refereeNode,us.refereeAll refereeAll,us.nodeTag nodeTag,"
			+ "us.raise_num raiseNum,us.payTag payTag,us.cap_factor capFactor,us.state state, us.entryTime entryTime,us.isEmpty isEmpty,"
			+ "us.centerId centerId,us.centerType centerType,uf.password password,uf.password2 password2,"
			+ "uf.documentType documentType,uf.numId numId,uf.sex sex,uf.tel tel,uf.province province,"
			+ "uf.city city,uf.area area,uf.address address,uf.accountId accountId,uf.accountName accountName,"
			+ "uf.bankAdr bankAdr,uf.bankName bankName from users us left join userinfo uf on us.userId=uf.userId"
			+ " where us.state>0 "+str;
		sql +=" order by entryTime desc";
		
		return sql;
	}
	
	
	public String selectAllWhitParam(final Map<String,Object> params){
		String str ="";
		if(params.get("user")!=null){
			User user = (User) params.get("user");
			if(user.getUserId()!=null && !user.getUserId().equals("")){
				str = str+" and  us.userId like '%"+user.getUserId()+"%'";
			}
			if(user.getUserName()!=null && !user.getUserName().equals("")){
				str = str+" and  us.userName like '%"+user.getUserName()+"%'";
			}
			if(user.getTel()!=null && !user.getTel().equals("")){
				str = str+" and  uf.tel like '%"+user.getTel()+"%'";
			}
			if(user.getDocumentType()!=null && !user.getDocumentType().equals("")){
				str = str+" and  uf.documentType like '%"+user.getDocumentType()+"%'";
			}
			if(user.getNumId()!=null && !user.getNumId().equals("")){
				str = str+" and  uf.numId like '%"+user.getNumId()+"%'";
			}
			if(user.getUserByRefereeId()!=null && !user.getUserByRefereeId().equals("")){
				str = str+" and  us.userByRefereeId like '%"+user.getUserByRefereeId()+"%'";
			}
			if(user.getUserByBelongId()!=null && !user.getUserByBelongId().equals("")){
				str = str+" and  us.userByBelongId like '%"+user.getUserByBelongId()+"%'";
			}
			if(user.getUserByDeclarationId()!=null && !user.getUserByDeclarationId().equals("")){
				str = str+" and  us.userByDeclarationId like '%"+user.getUserByDeclarationId()+"%'";
			}
			if(user.getRankJoin()!=null && user.getRankJoin()!=0){
				str = str+" and  us.rankJoin like '%"+user.getRankJoin()+"%'";
			}
			if(user.getRankManage()!=null && user.getRankManage()!=0){
				str = str+" and  us.rankManage like '%"+user.getRankManage()+"%'";
			}
			if(user.getState()!=null && user.getState()!=0){
				str = str+" and  us.state like '%"+user.getState()+"%'";
			}
			if(user.getIsHide()!=null ){
				str = str+" and  us.is_hide like '%"+user.getIsHide()+"%'";
			}
			if(user.getStartTime()!=null){
				str = str+" and  us.entryTime >= '"+user.getStartTime()+"'";
			}
			if(user.getEndTime()!=null){
				str = str+" and  us.entryTime <='"+user.getEndTime()+"'";
			}
		}
		String sql = "select us.id id,us.userId userId,us.userName userName,us.rankJoin rankJoin,us.visit_time visitTime,us.visit_num visitNum,"
			+ "us.rankJoinOld rankJoinOld,us.rankManage rankManage,us.rank rank,"
			+ "us.authority authority,us.is_max isMax,us.emoney emoney,us.dmoney dmoney,us.rmoney rmoney,us.pmoney pmoney,"
			+ "us.userByBelongId userByBelongId,us.userByRefereeId userByRefereeId,us.userByAId userByAId,us.userByBId userByBId,"
			+ "us.userByDeclarationId userByDeclarationId,us.user_id_by_belong_center userIdByBelongCenter,"
			+ "us.referee_id refereeId,us.belong_id belongId,us.declaration_id declarationId,"
			+ "us.id_by_belong_center idByBelongCenter,us.node node,us.nodeABC nodeABC,us.refereeNode refereeNode,us.refereeAll refereeAll,us.nodeTag nodeTag,"
			+ "us.raise_num raiseNum,us.payTag payTag,us.cap_factor capFactor,us.state state, us.entryTime entryTime,us.isEmpty isEmpty,"
			+ "us.centerId centerId,us.centerType centerType,uf.password password,uf.password2 password2,"
			+ "uf.documentType documentType,uf.numId numId,uf.sex sex,uf.tel tel,uf.province province,"
			+ "uf.city city,uf.area area,uf.address address,uf.accountId accountId,uf.accountName accountName,"
			+ "uf.bankAdr bankAdr,uf.bankName bankName from users us left join userinfo uf on us.userId=uf.userId"
			+ " where us.state>0 "+str;
		sql +=" order by entryTime desc";
		if(params.get("pageModel")!=null){
			Pager pageModel = (Pager)params.get("pageModel");
			sql +=" limit "+pageModel.getStartIndex()+","+pageModel.getPageSize();
		}
		return sql;
	}
	
	public String countAll(final Map<String,Object> params){
		String str ="";
		if(params.get("user")!=null){
			User user = (User) params.get("user");
			if(user.getUserId()!=null && !user.getUserId().equals("")){
				str = str+" and  us.userId like '%"+user.getUserId()+"%'";
			}
			if(user.getUserName()!=null && !user.getUserName().equals("")){
				str = str+" and  us.userName like '%"+user.getUserName()+"%'";
			}
			if(user.getTel()!=null && !user.getTel().equals("")){
				str = str+" and  uf.tel like '%"+user.getTel()+"%'";
			}
			if(user.getNumId()!=null && !user.getNumId().equals("")){
				str = str+" and  uf.numId like '%"+user.getNumId()+"%'";
			}
			if(user.getDocumentType()!=null && !user.getDocumentType().equals("")){
				str = str+" and  uf.documentType like '%"+user.getDocumentType()+"%'";
			}
			if(user.getUserByRefereeId()!=null && !user.getUserByRefereeId().equals("")){
				str = str+" and  us.userByRefereeId like '%"+user.getUserByRefereeId()+"%'";
			}
			if(user.getUserByBelongId()!=null && !user.getUserByBelongId().equals("")){
				str = str+" and  us.userByBelongId like '%"+user.getUserByBelongId()+"%'";
			}
			if(user.getUserByDeclarationId()!=null && !user.getUserByDeclarationId().equals("")){
				str = str+" and  us.userByDeclarationId like '%"+user.getUserByDeclarationId()+"%'";
			}
			if(user.getRankJoin()!=null && user.getRankJoin()!=0){
				str = str+" and  us.rankJoin like '%"+user.getRankJoin()+"%'";
			}
			if(user.getRankManage()!=null && user.getRankManage()!=0){
				str = str+" and  us.rankManage like '%"+user.getRankManage()+"%'";
			}
			if(user.getState()!=null && user.getState()!=0){
				str = str+" and  us.state like '%"+user.getState()+"%'";
			}
			if(user.getIsHide()!=null ){
				str = str+" and  us.is_hide like '%"+user.getIsHide()+"%'";
			}
			if(user.getStartTime()!=null){
				str = str+" and  us.entryTime >= '"+user.getStartTime()+"'";
			}
			if(user.getEndTime()!=null){
				str = str+" and  us.entryTime <='"+user.getEndTime()+"'";
			}
		}
		String sql = "select count(*) from users us left join userinfo uf on us.userId=uf.userId"
			+ " where us.state>0 "+str;
		sql +=" order by entryTime desc";
		
		return sql;
	}
	
	public String updateUserForField(String field,String value,String id){
		String str = "update users set "+field+"='"+value+"' where userId='"+id+"'";
		return str;
	}
	
	public String updateUserForField2(String field1,String value1,String field2,String value2,String id){
		String str = "update users set "+field1+"='"+value1+"',"+field2+"='"+value2+"' where userId='"+id+"'";
		return str;
	}
	
	public String selectUserByListForUpdate(final Map<String,Object> params){
		String str ="";
		if(params.get("user")!=null){
			User user = (User) params.get("user");
			if(user.getUserId()!=null && !user.getUserId().equals("")){
				str = str+" and  userId like '%"+user.getUserId()+"%'";
			}
			if(user.getUserByRefereeId()!=null && !user.getUserByRefereeId().equals("")){
				str = str+" and  userByRefereeId like '%"+user.getUserByRefereeId()+"%'";
			}
			if(user.getUserByBelongId()!=null && !user.getUserByBelongId().equals("")){
				str = str+" and  userByBelongId like '%"+user.getUserByBelongId()+"%'";
			}
			if(user.getUserByDeclarationId()!=null && !user.getUserByDeclarationId().equals("")){
				str = str+" and  userByDeclarationId like '%"+user.getUserByDeclarationId()+"%'";
			}
			if(user.getRankJoin()!=null && user.getRankJoin()!=0){
				str = str+" and  rankJoin like '%"+user.getRankJoin()+"%'";
			}
			if(user.getRankManage()!=null && user.getRankManage()!=0){
				str = str+" and  rankManage like '%"+user.getRankManage()+"%'";
			}
			if(user.getRank()!=null && user.getRank()!=0){
				str = str+" and  rank like '%"+user.getRank()+"%'";
			}
			if(user.getState()!=null && user.getState()!=0){
				str = str+" and  state like '%"+user.getState()+"%'";
			}
			if(user.getIsHide()!=null ){
				str = str+" and  is_hide like '%"+user.getIsHide()+"%'";
			}
			if(user.getStartTime()!=null){
				str = str+" and  entryTime >= '"+user.getStartTime()+"'";
			}
			if(user.getEndTime()!=null){
				str = str+" and  entryTime <='"+user.getEndTime()+"'";
			}
		}
		String sql = "select * from users  where state>0 "+str;
		
		sql +=" order by entryTime desc for update";
		
		return sql;
	}
	
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM("users");
				if(params.get("user")!=null){
					User user = (User) params.get("user");
					if(user.getUserId()!=null && !user.getUserId().equals("")){
						WHERE("  userId LIKE CONCAT ('%',#{user.userId},'%')");
					}
					if(user.getUserByRefereeId()!=null && !user.getUserByRefereeId().equals("")){
						WHERE("  userByRefereeId LIKE CONCAT ('%',#{user.userByRefereeId},'%')");
					}
					if(user.getUserByBelongId()!=null && !user.getUserByBelongId().equals("")){
						WHERE("  userByBelongId LIKE CONCAT ('%',#{user.userByBelongId},'%')");
					}
					if(user.getUserByDeclarationId()!=null && !user.getUserByDeclarationId().equals("")){
						WHERE("  userByDeclarationId LIKE CONCAT ('%',#{user.userByDeclarationId},'%')");
					}
					if(user.getRankJoin()!=null && user.getRankJoin()!=0){
						WHERE("  rankJoin LIKE CONCAT ('%',#{user.rankJoin},'%')");
					}
					if(user.getRankManage()!=null && user.getRankManage()!=0){
						WHERE("  rankManage LIKE CONCAT ('%',#{user.rankManage},'%')");
					}
					if(user.getRank()!=null && user.getRank()!=0){
						WHERE("  rank LIKE CONCAT ('%',#{user.rank},'%')");
					}
					if(user.getState()!=null && user.getState()!=0){
						WHERE("  state LIKE CONCAT ('%',#{user.state},'%')");
					}
					if(user.getStartTime()!=null){
						WHERE(" entryTime >= #{user.startTime}");
					}
					if(user.getEndTime()!=null){
						WHERE(" entryTime <= #{user.endTime}");
					}if(user.getIsHide()!=null ){
						WHERE(" is_hide =#{user.isHide}");
					}
					WHERE(" state>0 ");
				}
				
			}
		}.toString();
	}
	
	public String maxId( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("max(id)");
				FROM("users");
				if(params.get("user")!=null){
					User user = (User) params.get("user");
					if(user.getUserId()!=null && !user.getUserId().equals("")){
						WHERE("  userId LIKE CONCAT ('%',#{user.userId},'%')");
					}
					if(user.getUserByRefereeId()!=null && !user.getUserByRefereeId().equals("")){
						WHERE("  userByRefereeId LIKE CONCAT ('%',#{user.userByRefereeId},'%')");
					}
					if(user.getUserByBelongId()!=null && !user.getUserByBelongId().equals("")){
						WHERE("  userByBelongId LIKE CONCAT ('%',#{user.userByBelongId},'%')");
					}
					if(user.getUserByDeclarationId()!=null && !user.getUserByDeclarationId().equals("")){
						WHERE("  userByDeclarationId LIKE CONCAT ('%',#{user.userByDeclarationId},'%')");
					}
					if(user.getRankJoin()!=null && user.getRankJoin()!=0){
						WHERE("  rankJoin LIKE CONCAT ('%',#{user.rankJoin},'%')");
					}
					if(user.getRankManage()!=null && user.getRankManage()!=0){
						WHERE("  rankManage LIKE CONCAT ('%',#{user.rankManage},'%')");
					}
					if(user.getRank()!=null && user.getRank()!=0){
						WHERE("  rank LIKE CONCAT ('%',#{user.rank},'%')");
					}
					if(user.getState()!=null && user.getState()!=0){
						WHERE("  state LIKE CONCAT ('%',#{user.state},'%')");
					}
					if(user.getStartTime()!=null){
						WHERE(" entryTime >= #{user.startTime}");
					}
					if(user.getEndTime()!=null){
						WHERE(" entryTime <= #{user.endTime}");
					}if(user.getIsHide()!=null ){
						WHERE(" is_hide =#{user.isHide}");
					}
					WHERE(" state>0 ");
				}
				
			}
		}.toString();
	}
	
	public String selectListWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM("users");
			if(params.get("user")!=null){
				User user = (User) params.get("user");
				if(user.getUserId()!=null && !user.getUserId().equals("")){
					WHERE("  userId LIKE CONCAT ('%',#{user.userId},'%')");
				}
				if(user.getUserByRefereeId()!=null && !user.getUserByRefereeId().equals("")){
					WHERE("  userByRefereeId LIKE CONCAT ('%',#{user.userByRefereeId},'%')");
				}
				if(user.getUserByBelongId()!=null && !user.getUserByBelongId().equals("")){
					WHERE("  userByBelongId LIKE CONCAT ('%',#{user.userByBelongId},'%')");
				}
				if(user.getUserByDeclarationId()!=null && !user.getUserByDeclarationId().equals("")){
					WHERE("  userByDeclarationId LIKE CONCAT ('%',#{user.userByDeclarationId},'%')");
				}
				if(user.getRankJoin()!=null && user.getRankJoin()!=0){
					WHERE("  rankJoin LIKE CONCAT ('%',#{user.rankJoin},'%')");
				}
				if(user.getRankManage()!=null && user.getRankManage()!=0){
					WHERE("  rankManage LIKE CONCAT ('%',#{user.rankManage},'%')");
				}
				if(user.getRank()!=null && user.getRank()!=0){
					WHERE("  rank LIKE CONCAT ('%',#{user.rank},'%')");
				}
				if(user.getState()!=null && user.getState()!=0){
					WHERE("  state LIKE CONCAT ('%',#{user.state},'%')");
				}
				if(user.getIsHide()!=null ){
					WHERE("  is_hide =#{user.isHide}");
				}
				if(user.getStartTime()!=null){
					WHERE("  entryTime >= #{user.startTime}");
				}
				if(user.getEndTime()!=null){
					WHERE("  entryTime <= #{user.endTime}");
				}
				WHERE("  state>0 ");
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by entryTime desc ";
		}
		return sql;
	}
	
	public String insertUserInfo(final User user){
		String sql = new SQL(){
			{
				INSERT_INTO(Constants.USERINFOTABLE);
				if(user.getUserId()!=null&& !user.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(user.getUserName()!=null&& !user.getUserName().equals("")){
					VALUES("userName","#{userName}");
				}
				if(user.getPassword()!=null && !user.getPassword().equals("")){
					VALUES("password","#{password}");
				}
				if(user.getPassword2()!=null && !user.getPassword2().equals("")){
					VALUES("password2","#{password2}");
				}
				if(user.getSex()!=null && !user.getSex().equals("")){
					VALUES("sex","#{sex}");
				}
				if(user.getAge()!=null){
					VALUES("age","#{age}");
				}
				if(user.getDocumentType()!=null && !user.getDocumentType().equals("")){
					VALUES("documentType","#{documentType}");
				}
				if(user.getNumId()!=null && !user.getNumId().equals("")){
					VALUES("numId","#{numId}");
				}
				if(user.getTel()!=null && !user.getTel().equals("")){
					VALUES("tel","#{tel}");
				}
				if(user.getPhone()!=null&& !user.getPhone().equals("")){
					VALUES("phone","#{phone}");
				}
				if(user.getQq()!=null&& !user.getQq().equals("")){
					VALUES("qq","#{qq}");
				}
				if(user.getWeixin()!=null&& !user.getWeixin().equals("")){
					VALUES("weixin","#{weixin}");
				}
				if(user.getProvince()!=null&& !user.getProvince().equals("")){
					VALUES("province","#{province}");
				}
				if(user.getCity()!=null&& !user.getCity().equals("")){
					VALUES("city","#{city}");
				}
				if(user.getEmail()!=null && !user.getEmail().equals("")){
					VALUES("email","#{email}");
				}
				if(user.getArea()!=null && !user.getArea().equals("")){
					VALUES("area","#{area}");
				}
				if(user.getAddress()!=null && !user.getAddress().equals("")){
					VALUES("address","#{address}");
				}
				if(user.getBankName()!=null && !user.getBankName().equals("")){
					VALUES("bankName","#{bankName}");
				}
				if(user.getAccountId()!=null && !user.getAccountId().equals("")){
					VALUES("accountId","#{accountId}");
				}
				if(user.getAccountName()!=null && !user.getAccountName().equals("")){
					VALUES("accountName","#{accountName}");
				}
				if(user.getBankAdr()!=null&& !user.getBankAdr().equals("")){
					VALUES("bankAdr","#{bankAdr}");
				}
			}
		}.toString();
		return sql;
	}
	
	public String insertUser(final User user){
		return new SQL(){
			{
				INSERT_INTO(Constants.USERSTABLE);
				if(user.getUserId()!=null&& !user.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(user.getUserName()!=null&& !user.getUserId().equals("")){
					VALUES("userName","#{userName}");
				}
				if(user.getUserByAId()!=null){
					VALUES("userByAId","#{userByAId}");
				}
				if(user.getUserByBId()!=null){
					VALUES("userByBId","#{userByBId}");
				}
				if(user.getUserByBelongId()!=null && !user.getUserByBelongId().equals("")){
					VALUES("userByBelongId","#{userByBelongId}");
				}
				if(user.getUserByRefereeId()!=null && !user.getUserByRefereeId().equals("")){
					VALUES("userByRefereeId","#{userByRefereeId}");
				}
				if(user.getUserByDeclarationId()!=null && !user.getUserByDeclarationId().equals("")){
					VALUES("userByDeclarationId","#{userByDeclarationId}");
				}
				if(user.getRefereeAll()!=null && !user.getRefereeAll().equals("")){
					VALUES("refereeAll","#{refereeAll}");
				}
				if(user.getDeclarationNode()!=null && !user.getDeclarationNode().equals("")){
					VALUES("declarationNode","#{declarationNode}");
				}
				if(user.getRefereeNode()!=null && !user.getRefereeNode().equals("")){
					VALUES("refereeNode","#{refereeNode}");
				}
				if(user.getNode()!=null && !user.getNode().equals("")){
					VALUES("node","#{node}");
				}
				if(user.getNode()!=null && !user.getNode().equals("")){
					VALUES("nodeABC","#{nodeABC}");
				}
				if(user.getNodeTag()!=null){
					VALUES("nodeTag","#{nodeTag}");
				}
				if(user.getRefereeId()!=null){
					VALUES("referee_id","#{refereeId}");
				}
				if(user.getBelongId()!=null){
					VALUES("belong_id","#{belongId}");
				}
				if(user.getDeclarationId()!=null){
					VALUES("declaration_id","#{declarationId}");
				}
				if(user.getUserIdByBelongCenter()!=null&& !user.getUserIdByBelongCenter().equals("")){
					VALUES("user_id_by_belong_center","#{userIdByBelongCenter}");
				}
				if(user.getIdByBelongCenter()!=null){
					VALUES("id_by_belong_center","#{idByBelongCenter}");
				}
				if(user.getRankJoin()!=null){
					VALUES("rankJoin","#{rankJoin}");
				}
				if(user.getRankJoinOld()!=null){
					VALUES("rankJoinOld","#{rankJoinOld}");
				}
				if(user.getCenterId()!=null){
					VALUES("centerId","#{centerId}");
				}
				if(user.getCenterType()!=null){
					VALUES("centerType","#{centerType}");
				}
				if(user.getRankJoinTag()!=null){
					VALUES("rankJoinTag","#{rankJoinTag}");
				}
				if(user.getRankJoinOriginal()!=null){
					VALUES("rank_join_original","#{rankJoinOriginal}");
				}
				if(user.getRankManage()!=null){
					VALUES("rankManage","#{rankManage}");
				}
				if(user.getRankManageTag()!=null){
					VALUES("rankManageTag","#{rankManageTag}");
				}
				if(user.getRank()!=null){
					VALUES("rank","#{rank}");
				}
				if(user.getIsEmpty()!=null){
					VALUES("isEmpty","#{isEmpty}");
				}
				if(user.getIsHide()!=null){
					VALUES("is_hide","#{isHide}");
				}
				if(user.getPayTag()!=null){
					VALUES("payTag","#{payTag}");
				}
				if(user.getVisitTime()!=null){
					VALUES("visit_time","#{visitTime}");
				}
				if(user.getVisitNum()!=null){
					VALUES("visit_num","#{visitNum}");
				}
				if(user.getState()!=null){
					VALUES("state","#{state}");
				}
				if(user.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
				if(user.getEmoney()!=null){
					VALUES("emoney","#{emoney}");
				}
				if(user.getSmoney()!=null){
					VALUES("smoney","#{smoney}");
				}
				if(user.getDmoney()!=null){
					VALUES("dmoney","#{dmoney}");
				}
				if(user.getRmoney()!=null){
					VALUES("rmoney","#{rmoney}");
				}
				if(user.getChuangyeAwardPaid()!=null){
					VALUES("chuangye_award_paid","#{chuangyeAwardPaid}");
				}
				if(user.getChuangyeAwardSet()!=null){
					VALUES("chuangye_award_set","#{chuangyeAwardSet}");
				}
				if(user.getChuangyeAward()!=null){
					VALUES("chuangye_award","#{chuangyeAward}");
				}
				if(user.getChuangyeValidtyTime()!=null){
					VALUES("chuangye_validty_time","#{chuangyeValidtyTime}");
				}
				if(user.getAuthority()!=null&&!user.getAuthority().equals("")){
					VALUES("authority","#{authority}");
				}
			}
		}.toString();
	}
	
	public String updateUser(final User user){
		String sql = new SQL(){
			{
				UPDATE(Constants.USERSTABLE);
				if(user.getUserName()!=null&& !user.getUserName().equals("")){
					SET(" userName=#{userName}");
				}
				if(user.getUserId()!=null&& !user.getUserId().equals("")){
					SET(" userId=#{userId}");
				}
				if(user.getUserByBelongId()!=null&& !user.getUserByBelongId().equals("")){
					SET(" userByBelongId=#{userByBelongId}");
				}
				if(user.getUserByRefereeId()!=null&& !user.getUserByRefereeId().equals("")){
					SET(" userByRefereeId=#{userByRefereeId}");
				}
				if(user.getUserByAId()!=null&& !user.getUserByAId().equals("")){
					SET(" userByAId=#{userByAId}");
				}
				if(user.getUserByBId()!=null&& !user.getUserByBId().equals("")){
					SET("userByBId=#{userByBId}");
				}
				if(user.getUserByDeclarationId()!=null&& !user.getUserByDeclarationId().equals("")){
					SET(" userByDeclarationId=#{userByDeclarationId}");
				}
				if(user.getRefereeAll()!=null&& !user.getRefereeAll().equals("")){
					SET(" refereeAll=#{refereeAll}");
				}
				if(user.getDeclarationNode()!=null&& !user.getDeclarationNode().equals("")){
					SET(" declarationNode=#{declarationNode}");
				}
				if(user.getRefereeNode()!=null&& !user.getRefereeNode().equals("")){
				SET(" refereeNode=#{refereeNode}");
				}
				if(user.getNode()!=null&& !user.getNode().equals("") ){
				SET(" node=#{node}");
				}
				if(user.getNodeABC()!=null&& !user.getNodeABC().equals("")){
				SET(" nodeABC=#{nodeABC}");
				}
				if(user.getNodeTag()!=null){
					SET(" nodeTag=#{nodeTag}");
				}
				if(user.getRefereeId()!=null){
				SET(" referee_id=#{refereeId}");
				}
				if(user.getBelongId()!=null){
					SET(" belong_id=#{belongId}");
				}
				if(user.getDeclarationId()!=null){
					SET(" declaration_id=#{declarationId}");
				}
				if(user.getRankJoin()!=null){
					SET(" rankJoin=#{rankJoin}");
				}
				if(user.getRankJoinOld()!=null){
					SET(" rankJoinOld=#{rankJoinOld}");
				}
				if(user.getRankJoinTag()!=null){
					SET(" rankJoinTag=#{rankJoinTag}");
				}
				if(user.getRankJoinOriginal()!=null){
					SET(" rank_join_original=#{rankJoinOriginal}");
				}
				if(user.getRankManage()!=null){
					SET(" rankManage=#{rankManage}");
				}
				if(user.getRankManageTag()!=null){
					SET(" rankManageTag=#{rankManageTag}");
				}
				if(user.getRank()!=null){
					SET(" rank=#{rank}");
				}
				if(user.getIsEmpty()!=null){
					SET(" isEmpty=#{isEmpty}");
				}
				if(user.getPayTag()!=null){
					SET(" payTag=#{payTag}");
				}
				if(user.getState()!=null){
					SET(" state=#{state}");
				}
				if(user.getEmptyNum()!=null){
					SET(" empty_num=#{emptyNum}");
				}
				if(user.getEmptyNumTol()!=null){
					SET(" empty_num_tol=#{emptyNumTol}");
				}
				if(user.getEmoney()!=null){
					SET(" emoney=#{emoney}");
				}
				if(user.getSmoney()!=null){
					SET(" smoney=#{smoney}");
				}
				if(user.getDmoney()!=null){
					SET(" dmoney=#{dmoney}");
				}
				if(user.getRmoney()!=null){
					SET(" rmoney=#{rmoney}");
				}
				if(user.getChuangyeAwardPaid()!=null){
					SET(" chuangye_award_paid=#{chuangyeAwardPaid}");
				}
				if(user.getChuangyeAwardSet()!=null){
					SET(" chuangye_award_set=#{chuangyeAwardSet}");
				}
				if(user.getChuangyeAward()!=null){
					SET(" chuangye_award=#{chuangyeAward}");
				}
				if(user.getChuangyeValidtyTime()!=null){
					SET(" chuangye_validty_time=#{chuangyeValidtyTime}");
				}
				if(user.getVisitTime()!=null){
					SET("visit_tim=#{visitTime}");
				}
				if(user.getVisitNum()!=null){
					SET("visit_num=#{visitNum}");
				}
				if(user.getCenterId()!=null){
					SET(" centerId=#{centerId}");
				}
				if(user.getIdByBelongCenter()!=null){
					SET(" id_by_belong_center=#{idByBelongCenter}");
				}
				if(user.getUserIdByBelongCenter()!=null&&!user.getUserIdByBelongCenter().equals("")){
					SET(" user_id_by_belong_center=#{userIdByBelongCenter}");
				}
				if(user.getAuthority()!=null&&!user.getAuthority().equals("")){
					SET(" authority=#{authority}");
				}if(user.getIsMax()!=null){
					SET(" is_max=#{isMax}");
				}if(user.getRaiseNum()!=null){
					SET(" raise_num=#{raiseNum}");
				}if(user.getIsMax()!=null){
					SET(" cap_factor=#{capFactor}");
				}if(user.getIsHide()!=null){
					SET(" is_hide=#{isHide}");
				}if(user.getEntryTime()!=null){
					SET(" entryTime=#{entryTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
		return sql;
	}
	
	public String updateUserInfo(final User user){
		return new SQL(){
			{
				UPDATE(Constants.USERINFOTABLE);
				if(user.getUserName()!=null&& !user.getUserName().equals("")){
					SET("userName=#{userName}");
				}
				if(user.getUserId()!=null&& !user.getUserId().equals("")){
					SET("userId=#{userId}");
				}
				if(user.getPassword()!=null&& !user.getPassword().equals("")){
					SET("password=#{password}");
				}
				if(user.getPassword2()!=null&& !user.getPassword2().equals("")){
					SET("password2=#{password2}");
				}
				if(user.getSex()!=null && !user.getSex().equals("")){
					SET("sex=#{sex}");
				}
				if(user.getAge()!=null){
					SET("age=#{age}");
				}
				if(user.getDocumentType()!=null && !user.getDocumentType().equals("")){
					SET("documentType=#{documentType}");
				}
				if(user.getNumId()!=null && !user.getNumId().equals("")){
					SET("numId=#{numId}");
				}
				if(user.getTel()!=null && !user.getTel().equals("")){
					SET("tel=#{tel}");
				}
				if(user.getPhone()!=null&& !user.getPhone().equals("")){
					SET("phone=#{phone}");
				}
				if(user.getQq()!=null&& !user.getQq().equals("")){
					SET("qq=#{qq}");
				}
				if(user.getWeixin()!=null&& !user.getWeixin().equals("")){
					SET("weixin=#{weixin}");
				}
				if(user.getProvince()!=null&& !user.getProvince().equals("")){
					SET("province=#{province}");
				}
				if(user.getCity()!=null&& !user.getCity().equals("")){
					SET("city=#{city}");
				}
				if(user.getEmail()!=null && !user.getEmail().equals("")){
					SET("email=#{email}");
				}
				if(user.getArea()!=null && !user.getArea().equals("")){
					SET("area=#{area}");
				}
				if(user.getAddress()!=null && !user.getAddress().equals("")){
					SET("address=#{address}");
				}
				if(user.getBankName()!=null && !user.getBankName().equals("")){
					SET("bankName=#{bankName}");
				}
				if(user.getAccountId()!=null && !user.getAccountId().equals("")){
					SET("accountId=#{accountId}");
				}
				if(user.getAccountName()!=null && !user.getAccountName().equals("")){
					SET("accountName=#{accountName}");
				}
				if(user.getBankAdr()!=null&& !user.getBankAdr().equals("")){
					SET("bankAdr=#{bankAdr}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
		
	}
	

	public String updatePropertyOfUsers(@Param("property") String property,@Param("value") String value,@Param("id") Integer id,@Param("property1") String property1,@Param("value1") String value1 ){
		String sql ="update users set "+property+"=#{value} where id=#{id} and  "+property1+"=#{value1}";
		return sql;

	}
	
	public String updateOfUsers(String property,String value,String property1,String value1 ){
		String sql ="update users set "+property+"='"+value+"' where "+property1+"='"+value1+"'";
		return sql;

	}
	
	
	public String updatePropertyOfUserInfo(String property,String value,String property1,String value1 ){
		String sql ="update userinfo set "+property+"='"+value+"' where "+property1+"='"+value1+"'";
		return sql;
	}
	
	public String selectPropertyOfUsers(@Param("property") String property,@Param("value") Integer value){
		String sql ="select * from users  where  "+property+"=#{value} and state>0";
		return sql;

	}
	
	public String updateUserForUserId(final User user){
		String sql = new SQL(){
			{
				UPDATE(Constants.USERSTABLE);
				if(user.getUserName()!=null&& !user.getUserName().equals("")){
					SET(" userName=#{userName}");
				}
				if(user.getUserId()!=null&& !user.getUserId().equals("")){
					SET(" userId=#{userId}");
				}
				if(user.getUserByBelongId()!=null && !user.getUserByBelongId().equals("")){
					SET(" userByBelongId=#{userByBelongId}");
				}
				if(user.getUserByRefereeId()!=null && !user.getUserByRefereeId().equals("")){
					SET(" userByRefereeId=#{userByRefereeId}");
				}
				if(user.getUserByAId()!=null && !user.getUserByAId().equals("")){
					SET(" userByAId=#{userByAId}");
				}
				if(user.getUserByBId()!=null && !user.getUserByBId().equals("")){
					SET("userByBId=#{userByBId}");
				}
				if(user.getUserByDeclarationId()!=null && !user.getUserByDeclarationId().equals("")){
					SET(" userByDeclarationId=#{userByDeclarationId}");
				}
				if(user.getRefereeAll()!=null && !user.getRefereeAll().equals("")){
					SET(" refereeAll=#{refereeAll}");
				}
				if(user.getDeclarationNode()!=null && !user.getDeclarationNode().equals("")){
					SET(" declarationNode=#{declarationNode}");
				}
				if(user.getRefereeNode()!=null && !user.getRefereeNode().equals("")){
				SET(" refereeNode=#{refereeNode}");
				}
				if(user.getNode()!=null && !user.getNode().equals("")){
				SET(" node=#{node}");
				}
				if(user.getNode()!=null && !user.getNode().equals("")){
				SET(" nodeABC=#{nodeABC}");
				}
				if(user.getNodeTag()!=null){
					SET(" nodeTag=#{nodeTag}");
				}
				if(user.getRefereeId()!=null){
				SET(" referee_id=#{refereeId}");
				}
				if(user.getBelongId()!=null){
					SET(" belong_id=#{belongId}");
				}
				if(user.getDeclarationId()!=null){
					SET(" declaration_id=#{declarationId}");
				}
				if(user.getRankJoin()!=null){
					SET(" rankJoin=#{rankJoin}");
				}
				if(user.getRankJoinOld()!=null){
					SET(" rankJoinOld=#{rankJoinOld}");
				}
				if(user.getRankJoinTag()!=null){
					SET(" rankJoinTag=#{rankJoinTag}");
				}
				if(user.getRankJoinOriginal()!=null){
					SET(" rank_join_original=#{rankJoinOriginal}");
				}
				if(user.getRankManage()!=null){
					SET(" rankManage=#{rankManage}");
				}
				if(user.getRankManageTag()!=null){
					SET(" rankManageTag=#{rankManageTag}");
				}
				if(user.getIsEmpty()!=null){
					SET(" isEmpty=#{isEmpty}");
				}
				if(user.getPayTag()!=null){
					SET(" payTag=#{payTag}");
				}
				if(user.getState()!=null){
					SET(" state=#{state}");
				}
				if(user.getEmptyNum()!=null){
					SET(" empty_num=#{emptyNum}");
				}
				if(user.getEmptyNumTol()!=null){
					SET(" empty_num_tol=#{emptyNumTol}");
				}
				if(user.getEmoney()!=null){
					SET(" emoney=#{emoney}");
				}
				if(user.getSmoney()!=null){
					SET(" smoney=#{smoney}");
				}
				if(user.getDmoney()!=null){
					SET(" dmoney=#{dmoney}");
				}
				if(user.getRmoney()!=null){
					SET(" rmoney=#{rmoney}");
				}
				if(user.getChuangyeAwardPaid()!=null){
					SET(" chuangye_award_paid=#{chuangyeAwardPaid}");
				}
				if(user.getChuangyeAwardSet()!=null){
					SET(" chuangye_award_set=#{chuangyeAwardSet}");
				}
				if(user.getChuangyeAward()!=null){
					SET(" chuangye_award=#{chuangyeAward}");
				}
				if(user.getChuangyeValidtyTime()!=null){
					SET(" chuangye_validty_time=#{chuangyeValidtyTime}");
				}
				if(user.getCenterId()!=null){
					SET(" centerId=#{centerId}");
				}
				if(user.getIdByBelongCenter()!=null){
					SET(" id_by_belong_center=#{idByBelongCenter}");
				}
				if(user.getUserIdByBelongCenter()!=null&&!user.getUserIdByBelongCenter().equals("")){
					SET(" user_id_by_belong_center=#{userIdByBelongCenter}");
				}
				if(user.getAuthority()!=null && !user.getAuthority().equals("")){
					SET(" authority=#{authority}");
				}if(user.getIsMax()!=null){
					SET(" is_max=#{isMax}");
				}if(user.getRaiseNum()!=null){
					SET(" raise_num=#{raiseNum}");
				}if(user.getIsMax()!=null){
					SET(" cap_factor=#{capFactor}");
				}if(user.getIsHide()!=null){
					SET(" is_hide=#{isHide}");
				}
				WHERE(" userId=#{userId}");
			}
		}.toString();
		return sql;
	}
	
	public String updateUserInfoForUserId(final User user){
		return new SQL(){
			{
				UPDATE(Constants.USERINFOTABLE);
				if(user.getUserName()!=null&& !user.getUserName().equals("")){
					SET("userName=#{userName}");
				}
				if(user.getUserId()!=null&& !user.getUserId().equals("")){
					SET("userId=#{userId}");
				}
				if(user.getPassword()!=null&& !user.getPassword().equals("")){
					SET("password=#{password}");
				}
				if(user.getPassword2()!=null&& !user.getPassword2().equals("")){
					SET("password2=#{password2}");
				}
				if(user.getSex()!=null && !user.getSex().equals("")){
					SET("sex=#{sex}");
				}
				if(user.getAge()!=null){
					SET("age=#{age}");
				}
				if(user.getDocumentType()!=null && !user.getDocumentType().equals("")){
					SET("documentType=#{documentType}");
				}
				if(user.getNumId()!=null && !user.getNumId().equals("")){
					SET("numId=#{numId}");
				}
				if(user.getTel()!=null && !user.getTel().equals("")){
					SET("tel=#{tel}");
				}
				if(user.getPhone()!=null&& !user.getPhone().equals("")){
					SET("phone=#{phone}");
				}
				if(user.getQq()!=null&& !user.getQq().equals("")){
					SET("qq=#{qq}");
				}
				if(user.getWeixin()!=null&& !user.getWeixin().equals("")){
					SET("weixin=#{weixin}");
				}
				if(user.getProvince()!=null&& !user.getProvince().equals("")){
					SET("province=#{province}");
				}
				if(user.getCity()!=null&& !user.getCity().equals("")){
					SET("city=#{city}");
				}
				if(user.getEmail()!=null && !user.getEmail().equals("")){
					SET("email=#{email}");
				}
				if(user.getArea()!=null && !user.getArea().equals("")){
					SET("area=#{area}");
				}
				if(user.getAddress()!=null && !user.getAddress().equals("")){
					SET("address=#{address}");
				}
				if(user.getBankName()!=null && !user.getBankName().equals("")){
					SET("bankName=#{bankName}");
				}
				if(user.getAccountId()!=null && !user.getAccountId().equals("")){
					SET("accountId=#{accountId}");
				}
				if(user.getAccountName()!=null && !user.getAccountName().equals("")){
					SET("accountName=#{accountName}");
				}
				if(user.getBankAdr()!=null&& !user.getBankAdr().equals("")){
					SET("bankAdr=#{bankAdr}");
				}
				WHERE(" userId=#{userId}");
			}
		}.toString();
		
	}
	
	public String selectUserBySql(String sql){
		String sql1="select * from users where "+sql;
		return sql1;
	}
	
	
	public String updateForSql(String sql){
		return sql;
	}
}
