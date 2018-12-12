package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.RankManageUpdateDetail;
import com.ssm.utils.Constants;

public class RankManageUpdateDetailDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.RANKMANAGEUPDATEDETAILTABLE);
			if(params.get("rankManageUpdateDetail")!=null){
				RankManageUpdateDetail rankManageUpdateDetail = (RankManageUpdateDetail) params.get("rankManageUpdateDetail");
				if(rankManageUpdateDetail.getUserId()!=null && !rankManageUpdateDetail.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{rankManageUpdateDetail.userId},'%')");
				}
				if(rankManageUpdateDetail.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{rankManageUpdateDetail.state},'%')");
				}
				if(rankManageUpdateDetail.getOldRankManage()!=null){
					WHERE(" oldRankManage LIKE CONCAT ('%',#{rankManageUpdateDetail.oldRankManage},'%')");
				}
				if(rankManageUpdateDetail.getNewRankManage()!=null){
					WHERE(" newRankManage LIKE CONCAT ('%',#{rankManageUpdateDetail.newRankManage},'%')");
				}
				if(rankManageUpdateDetail.getTag()!=null) 
					WHERE(" tag LIKE CONCAT ('%',#{rankManageUpdateDetail.tag},'%')");
				
				if(rankManageUpdateDetail.getStartTime()!=null){
					WHERE("  entryTime >= #{rankManageUpdateDetail.startTime}");
				}
				if(rankManageUpdateDetail.getEndTime()!=null){
					WHERE("  entryTime <= #{rankManageUpdateDetail.endTime}");
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
				FROM(Constants.RANKMANAGEUPDATEDETAILTABLE);
				if(params.get("rankManageUpdateDetail")!=null){
					RankManageUpdateDetail rankManageUpdateDetail = (RankManageUpdateDetail) params.get("rankManageUpdateDetail");
					if(rankManageUpdateDetail.getUserId()!=null && !rankManageUpdateDetail.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{rankManageUpdateDetail.userId},'%')");
					}
					if(rankManageUpdateDetail.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{rankManageUpdateDetail.state},'%')");
					}
					if(rankManageUpdateDetail.getOldRankManage()!=null){
						WHERE(" oldRankManage LIKE CONCAT ('%',#{rankManageUpdateDetail.oldRankManage},'%')");
					}
					if(rankManageUpdateDetail.getNewRankManage()!=null){
						WHERE(" newRankManage LIKE CONCAT ('%',#{rankManageUpdateDetail.newRankManage},'%')");
					}
					if(rankManageUpdateDetail.getTag()!=null) 
						WHERE(" tag LIKE CONCAT ('%',#{rankManageUpdateDetail.tag},'%')");

					if(rankManageUpdateDetail.getStartTime()!=null){
						WHERE("  entryTime >= #{rankManageUpdateDetail.startTime}");
					}
					if(rankManageUpdateDetail.getEndTime()!=null){
						WHERE("  entryTime <= #{rankManageUpdateDetail.endTime}");
					}
					
				}
			}
		}.toString();
	}
	
	public String selectListWhitParam(final Map<String,Object> params){
		String str = "";
		if(params.get("rankManageUpdateDetail")!=null){
			RankManageUpdateDetail rankManageUpdateDetail = (RankManageUpdateDetail) params.get("rankManageUpdateDetail");
			if(rankManageUpdateDetail.getStartTime()!=null) str=str+" and entryTime>='"+rankManageUpdateDetail.getStartTime()+"'"; 
			if(rankManageUpdateDetail.getEndTime()!=null) str=str+" and entryTime<='"+rankManageUpdateDetail.getEndTime()+"'"; 
			if(rankManageUpdateDetail.getTag()!=null)  str=str+" and tag='"+rankManageUpdateDetail.getTag()+"'"; 
			if(rankManageUpdateDetail.getOldRankManage()!=null){
				str+=" and oldRankManage like '%"+rankManageUpdateDetail.getOldRankManage()+"%'";
			}
			if(rankManageUpdateDetail.getNewRankManage()!=null){
				str+=" and newRankManage like '%"+rankManageUpdateDetail.getNewRankManage()+"%'";
			}
		}
		String sql = "select * from "+Constants.RANKMANAGEUPDATEDETAILTABLE+" where state>0 "+str;
		return sql;
	}
	
	public String selectAllListWhitParam(final Map<String,Object> params){
		String str = "";
		if(params.get("rankManageUpdateDetail")!=null){
			RankManageUpdateDetail rankManageUpdateDetail = (RankManageUpdateDetail) params.get("rankManageUpdateDetail");
			if(rankManageUpdateDetail.getStartTime()!=null) str=str+" and entryTime>='"+rankManageUpdateDetail.getStartTime()+"'"; 
			if(rankManageUpdateDetail.getEndTime()!=null) str=str+" and entryTime<='"+rankManageUpdateDetail.getEndTime()+"'"; 
			if(rankManageUpdateDetail.getTag()!=null)  str=str+" and tag='"+rankManageUpdateDetail.getTag()+"'"; 
			if(rankManageUpdateDetail.getOldRankManage()!=null){
				str+=" and oldRankManage like '%"+rankManageUpdateDetail.getOldRankManage()+"%'";
			}
			if(rankManageUpdateDetail.getNewRankManage()!=null){
				str+=" and newRankManage like '%"+rankManageUpdateDetail.getNewRankManage()+"%'";
			}
		}
		String sql = "select * from "+Constants.RANKMANAGEUPDATEDETAILTABLE+" where state>0 "+str;
		return sql;
	}
	
	public String insertRankManageUpdateDetail(final RankManageUpdateDetail RankManageUpdateDetail){
		return new SQL(){
			{
				INSERT_INTO(Constants.RANKMANAGEUPDATEDETAILTABLE);
				if(RankManageUpdateDetail.getUserId()!=null&& !RankManageUpdateDetail.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(RankManageUpdateDetail.getUid()!=null){
					VALUES("uid","#{uid}");
				}
				if(RankManageUpdateDetail.getUserName()!=null&& !RankManageUpdateDetail.getUserName().equals("")){
					VALUES("userName","#{userName}");
				}
				if(RankManageUpdateDetail.getOldRankManage()!=null){
					VALUES("oldRankManage","#{oldRankManage}");
				}
				if(RankManageUpdateDetail.getNewRankManage()!=null){
					VALUES("newRankManage","#{newRankManage}");
				}
				if(RankManageUpdateDetail.getState()!=null){
					VALUES("state","#{state}");
				}
				
				if(RankManageUpdateDetail.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
				if(RankManageUpdateDetail.getTag()!=null){
					VALUES("tag","#{tag}");
				}
			}
		}.toString();
	}
	
	public String updateRankManageUpdateDetail(final RankManageUpdateDetail RankManageUpdateDetail){
		return new SQL(){
			{
				UPDATE(Constants.RANKMANAGEUPDATEDETAILTABLE);
				if(RankManageUpdateDetail.getState()!=null){
					SET("state=#{state}");
				}
			}
		}.toString();
	}
	
	public String updateForUser(final RankManageUpdateDetail RankManageUpdateDetail){
		return new SQL(){
			{
				UPDATE(Constants.RANKMANAGEUPDATEDETAILTABLE);
				if(RankManageUpdateDetail.getState()!=null){
					SET("state=#{state}");
				}
			}
		}.toString();
	}
	
	
	

}
