package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.JoinUpdateDetail;
import com.ssm.utils.Constants;

public class JoinUpdateDetailDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.JOINUPDATEDETAILTABLE);
			if(params.get("joinUpdateDetail")!=null){
				JoinUpdateDetail joinUpdateDetail = (JoinUpdateDetail) params.get("joinUpdateDetail");
				if(joinUpdateDetail.getUserId()!=null && !joinUpdateDetail.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{joinUpdateDetail.userId},'%')");
				}
				if(joinUpdateDetail.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{joinUpdateDetail.state},'%')");
				}
				if(joinUpdateDetail.getOldRankJoin()!=null){
					WHERE(" oldRankJoin LIKE CONCAT ('%',#{joinUpdateDetail.oldRankJoin},'%')");
				}
				if(joinUpdateDetail.getNewRankJoin()!=null){
					WHERE(" newRankJoin LIKE CONCAT ('%',#{joinUpdateDetail.newRankJoin},'%')");
				}
				if(joinUpdateDetail.getTag()!=null) 
					WHERE(" tag LIKE CONCAT ('%',#{joinUpdateDetail.tag},'%')");
				
				if(joinUpdateDetail.getStartTime()!=null){
					WHERE("  entryTime >= #{joinUpdateDetail.startTime}");
				}
				if(joinUpdateDetail.getEndTime()!=null){
					WHERE("  entryTime <= #{joinUpdateDetail.endTime}");
				}
			
			
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by entryTime desc limit #{pageModel.startIndex},#{pageModel.pageSize} ";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.JOINUPDATEDETAILTABLE);
				if(params.get("joinUpdateDetail")!=null){
					JoinUpdateDetail joinUpdateDetail = (JoinUpdateDetail) params.get("joinUpdateDetail");
					if(joinUpdateDetail.getUserId()!=null && !joinUpdateDetail.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{joinUpdateDetail.userId},'%')");
					}
					if(joinUpdateDetail.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{joinUpdateDetail.state},'%')");
					}
					if(joinUpdateDetail.getOldRankJoin()!=null){
						WHERE(" oldRankJoin LIKE CONCAT ('%',#{joinUpdateDetail.oldRankJoin},'%')");
					}
					if(joinUpdateDetail.getNewRankJoin()!=null){
						WHERE(" newRankJoin LIKE CONCAT ('%',#{joinUpdateDetail.newRankJoin},'%')");
					}
					if(joinUpdateDetail.getTag()!=null) 
						WHERE(" tag LIKE CONCAT ('%',#{joinUpdateDetail.tag},'%')");

					if(joinUpdateDetail.getStartTime()!=null){
						WHERE("  entryTime >= #{joinUpdateDetail.startTime}");
					}
					if(joinUpdateDetail.getEndTime()!=null){
						WHERE("  entryTime <= #{joinUpdateDetail.endTime}");
					}
					
				}
			}
		}.toString();
	}
	
	public String selectListWhitParam(final Map<String,Object> params){
		String str = "";
		if(params.get("joinUpdateDetail")!=null){
			JoinUpdateDetail joinUpdateDetail = (JoinUpdateDetail) params.get("joinUpdateDetail");
			if(joinUpdateDetail.getStartTime()!=null) str=str+" and entryTime>='"+joinUpdateDetail.getStartTime()+"'"; 
			if(joinUpdateDetail.getEndTime()!=null) str=str+" and entryTime<='"+joinUpdateDetail.getEndTime()+"'"; 
			if(joinUpdateDetail.getTag()!=null)  str=str+" and tag='"+joinUpdateDetail.getTag()+"'"; 
			if(joinUpdateDetail.getOldRankJoin()!=null){
				str+=" and oldRankJoin like '%"+joinUpdateDetail.getOldRankJoin()+"%'";
			}
			if(joinUpdateDetail.getNewRankJoin()!=null){
				str+=" and newRankJoin like '%"+joinUpdateDetail.getNewRankJoin()+"%'";
			}
		}
		String sql = "select * from "+Constants.JOINUPDATEDETAILTABLE+" where state>0 "+str;
		return sql;
	}
	
	public String selectAllListWhitParam(final Map<String,Object> params){
		String str = "";
		if(params.get("joinUpdateDetail")!=null){
			JoinUpdateDetail joinUpdateDetail = (JoinUpdateDetail) params.get("joinUpdateDetail");
			if(joinUpdateDetail.getStartTime()!=null) str=str+" and entryTime>='"+joinUpdateDetail.getStartTime()+"'"; 
			if(joinUpdateDetail.getEndTime()!=null) str=str+" and entryTime<='"+joinUpdateDetail.getEndTime()+"'"; 
			if(joinUpdateDetail.getTag()!=null)  str=str+" and tag='"+joinUpdateDetail.getTag()+"'"; 
			if(joinUpdateDetail.getOldRankJoin()!=null){
				str+=" and oldRankJoin like '%"+joinUpdateDetail.getOldRankJoin()+"%'";
			}
			if(joinUpdateDetail.getNewRankJoin()!=null){
				str+=" and newRankJoin like '%"+joinUpdateDetail.getNewRankJoin()+"%'";
			}
		}
		String sql = "select * from "+Constants.JOINUPDATEDETAILTABLE+" where state>0 "+str;
		return sql;
	}
	
	public String insertJoinUpdateDetail(final JoinUpdateDetail joinUpdateDetail){
		return new SQL(){
			{
				INSERT_INTO(Constants.JOINUPDATEDETAILTABLE);
				if(joinUpdateDetail.getUserId()!=null&& !joinUpdateDetail.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(joinUpdateDetail.getUid()!=null){
					VALUES("uid","#{uid}");
				}
				if(joinUpdateDetail.getUserName()!=null&& !joinUpdateDetail.getUserName().equals("")){
					VALUES("userName","#{userName}");
				}
				if(joinUpdateDetail.getOldRankJoin()!=null){
					VALUES("oldRankJoin","#{oldRankJoin}");
				}
				if(joinUpdateDetail.getNewRankJoin()!=null){
					VALUES("newRankJoin","#{newRankJoin}");
				}
				if(joinUpdateDetail.getState()!=null){
					VALUES("state","#{state}");
				}
				
				if(joinUpdateDetail.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
				if(joinUpdateDetail.getTag()!=null){
					VALUES("tag","#{tag}");
				}
			}
		}.toString();
	}
	
	public String updateJoinUpdateDetail(final JoinUpdateDetail joinUpdateDetail){
		return new SQL(){
			{
				UPDATE(Constants.JOINUPDATEDETAILTABLE);
				if(joinUpdateDetail.getState()!=null){
					SET("state=#{state}");
				}
			}
		}.toString();
	}
	
	public String updateForUser(final JoinUpdateDetail joinUpdateDetail){
		return new SQL(){
			{
				UPDATE(Constants.JOINUPDATEDETAILTABLE);
				if(joinUpdateDetail.getState()!=null){
					SET("state=#{state}");
				}
			}
		}.toString();
	}
	
	
	

}
