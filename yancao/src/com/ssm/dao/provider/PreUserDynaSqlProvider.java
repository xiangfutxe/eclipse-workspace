package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.PreUser;
import com.ssm.pojo.User;
import com.ssm.pojo.WReward;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;

public class PreUserDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM("pre_users");
			if(params.get("user")!=null){
				PreUser user = (PreUser) params.get("user");
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
			PreUser user = (PreUser) params.get("user");
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
			if(user.getState()!=null && user.getState()!=0){
				str = str+" and  state like '%"+user.getState()+"%'";
			}
			if(user.getStartTime()!=null){
				str = str+" and  entryTime >= '"+user.getStartTime()+"'";
			}
			if(user.getEndTime()!=null){
				str = str+" and  entryTime <='"+user.getEndTime()+"'";
			}
		}
		String sql = "select * from pre_users  where state>0 "+str;
		sql +=" order by entryTime desc";
		
		return sql;
	}
	
	
	public String selectAllWhitParam(final Map<String,Object> params){
		String str ="";
		if(params.get("user")!=null){
			PreUser user = (PreUser) params.get("user");
			if(user.getUserId()!=null && !user.getUserId().equals("")){
				str = str+" and  userId like '%"+user.getUserId()+"%'";
			}
			if(user.getUserName()!=null && !user.getUserName().equals("")){
				str = str+" and  userName like '%"+user.getUserName()+"%'";
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
			if(user.getState()!=null && user.getState()!=0){
				str = str+" and  state like '%"+user.getState()+"%'";
			}
			
			if(user.getStartTime()!=null){
				str = str+" and  entryTime >= '"+user.getStartTime()+"'";
			}
			if(user.getEndTime()!=null){
				str = str+" and  entryTime <='"+user.getEndTime()+"'";
			}
		}
		String sql = "select * from pre_users  where state>0 "+str;
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
			PreUser user = (PreUser) params.get("user");
			if(user.getUserId()!=null && !user.getUserId().equals("")){
				str = str+" and  userId like '%"+user.getUserId()+"%'";
			}
			if(user.getUserName()!=null && !user.getUserName().equals("")){
				str = str+" and  userName like '%"+user.getUserName()+"%'";
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
			if(user.getState()!=null && user.getState()!=0){
				str = str+" and  state like '%"+user.getState()+"%'";
			}
			
			if(user.getStartTime()!=null){
				str = str+" and  entryTime >= '"+user.getStartTime()+"'";
			}
			if(user.getEndTime()!=null){
				str = str+" and  entryTime <='"+user.getEndTime()+"'";
			}
		}
		String sql = "select count(*) from pre_users  where state>0 "+str;
		sql +=" order by entryTime desc";
		
		return sql;
	}
	

	
	public String selectUserByListForUpdate(final Map<String,Object> params){
		String str ="";
		if(params.get("user")!=null){
			PreUser user = (PreUser) params.get("user");
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
					PreUser user = (PreUser) params.get("user");
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
					PreUser user = (PreUser) params.get("user");
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
				PreUser user = (PreUser) params.get("user");
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
	
	
	public String updateUser(final PreUser user){
		String sql = new SQL(){
			{
				UPDATE(Constants.PREUSERSTABLE);
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
				}if(user.getState()!=null){
					SET(" state=#{state}");
				}if(user.getCenterId()!=null){
					SET(" centerId=#{centerId}");
				}
				if(user.getIdByBelongCenter()!=null){
					SET(" id_by_belong_center=#{idByBelongCenter}");
				}
				if(user.getUserIdByBelongCenter()!=null&&!user.getUserIdByBelongCenter().equals("")){
					SET(" user_id_by_belong_center=#{userIdByBelongCenter}");
				}if(user.getEntryTime()!=null){
					SET(" entryTime=#{entryTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
		return sql;
	}
	
	public String updateForSql(String sql){
		return sql;
	}
	
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder(); 
        sb.append("INSERT INTO "+Constants.PREUSERSTABLE);  
        sb.append("(id,userId,userName,rankJoin,rankJoinOld,rank,"
        		+ "userByBelongId,userByRefereeId,userByDeclarationId,belongId,refereeId,declarationId,"
        		+ "userByAId,userByBId,node,nodeABC,refereeNode,declarationNode,refereeAll,"
        		+ "idByBelongCenter,userIdByBelongCenter,centerId,centerType,"
        		+ "totalPerformance,atpv,acpv,btpv,bcpv,rtpv,rtpv1,joinPvTal,joinPvNew,totalNum,totalNumReal,"
        		+ "payTag,isEmpty,refereeNum,totalIncome,state,entryTime)");  
        sb.append(" VALUES "); 
        if(params.get("list")!=null){
        List<PreUser> list = (List<PreUser>) params.get("list");  
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].userId},#'{'list[{0}].userName},#'{'list[{0}].rankJoin},#'{'list[{0}].rankJoinOld},#'{'list[{0}].rank}"
        		+ ",#'{'list[{0}].userByBelongId},#'{'list[{0}].userByRefereeId},#'{'list[{0}].userByDeclarationId},#'{'list[{0}].belongId},#'{'list[{0}].refereeId},#'{'list[{0}].declarationId}"
        		+ ",#'{'list[{0}].userByAId},#'{'list[{0}].userByBId},#'{'list[{0}].node},#'{'list[{0}].nodeABC},#'{'list[{0}].refereeNode},#'{'list[{0}].declarationNode},#'{'list[{0}].refereeAll}"
        		+ ",#'{'list[{0}].idByBelongCenter},#'{'list[{0}].userIdByBelongCenter},#'{'list[{0}].centerId},#'{'list[{0}].centerType}"
        		+ ",#'{'list[{0}].totalPerformance},#'{'list[{0}].atpv},#'{'list[{0}].acpv},#'{'list[{0}].btpv},#'{'list[{0}].bcpv},#'{'list[{0}].rtpv},#'{'list[{0}].rtpv1},#'{'list[{0}].joinPvTal},#'{'list[{0}].joinPvNew},#'{'list[{0}].totalNum},#'{'list[{0}].totalNumReal}"
        		+ ",#'{'list[{0}].payTag},#'{'list[{0}].isEmpty},#'{'list[{0}].refereeNum},#'{'list[{0}].totalIncome},#'{'list[{0}].state},#'{'list[{0}].entryTime})");  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i})); 
          if(i<list.size()-1){
            	 sb.append(",");  
            }
           
        }  
		}
        return sb.toString();  
    }  
}
