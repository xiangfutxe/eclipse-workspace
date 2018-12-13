package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.JoinInfo;
import com.ssm.utils.Constants;

public class JoinInfoDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.JOININFOTABLE);
			if(params.get("joinInfo")!=null){
				JoinInfo joinInfo = (JoinInfo) params.get("joinInfo");
				if(joinInfo.getUserId()!=null && !joinInfo.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{joinInfo.userId},'%')");
				}
				if(joinInfo.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{joinInfo.state},'%')");
				}
				if(joinInfo.getOldRankJoin()!=null){
					WHERE(" oldRankJoin LIKE CONCAT ('%',#{joinInfo.oldRankJoin},'%')");
				}
				if(joinInfo.getNewRankJoin()!=null){
					WHERE(" newRankJoin LIKE CONCAT ('%',#{joinInfo.newRankJoin},'%')");
				}
				if(joinInfo.getPrice()!=null){
					if(joinInfo.getTag()==1)
					WHERE(" price<=0 ");
					else
						WHERE(" price>0 ");
				}
				
				if(joinInfo.getStartTime()!=null){
					WHERE("  entryTime >= #{joinInfo.startTime}");
				}
				if(joinInfo.getEndTime()!=null){
					WHERE("  entryTime <= #{joinInfo.endTime}");
				}
				WHERE("  oldRankJoin >0");
			
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
				FROM(Constants.JOININFOTABLE);
				if(params.get("joinInfo")!=null){
					JoinInfo joinInfo = (JoinInfo) params.get("joinInfo");
					if(joinInfo.getUserId()!=null && !joinInfo.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{joinInfo.userId},'%')");
					}
					if(joinInfo.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{joinInfo.state},'%')");
					}
					if(joinInfo.getOldRankJoin()!=null){
						WHERE(" oldRankJoin LIKE CONCAT ('%',#{joinInfo.oldRankJoin},'%')");
					}
					if(joinInfo.getNewRankJoin()!=null){
						WHERE(" newRankJoin LIKE CONCAT ('%',#{joinInfo.newRankJoin},'%')");
					}
					if(joinInfo.getPrice()!=null){
						if(joinInfo.getTag()==1)
						WHERE(" price<=0 ");
						else
							WHERE(" price>0 ");
					}
					if(joinInfo.getStartTime()!=null){
						WHERE("  entryTime >= #{joinInfo.startTime}");
					}
					if(joinInfo.getEndTime()!=null){
						WHERE("  entryTime <= #{joinInfo.endTime}");
					}
					WHERE("  oldRankJoin >0");
				}
			}
		}.toString();
	}
	
	public String selectListWhitParam(final Map<String,Object> params){
		String str = "";
		if(params.get("joinInfo")!=null){
			JoinInfo joinInfo = (JoinInfo) params.get("joinInfo");
			if(joinInfo.getStartTime()!=null) str=str+" and entryTime>='"+joinInfo.getStartTime()+"'"; 
			if(joinInfo.getEndTime()!=null) str=str+" and entryTime<='"+joinInfo.getEndTime()+"'"; 
			if(joinInfo.getPrice()!=null){
				if(joinInfo.getTag()==1)
					str=str+" and price<=0 ";
				else
						str=str+" and price>0 ";
			}
			if(joinInfo.getOldRankJoin()!=null){
				str+=" and oldRankJoin like '%"+joinInfo.getOldRankJoin()+"%'";
			}
			if(joinInfo.getNewRankJoin()!=null){
				str+=" and newRankJoin like '%"+joinInfo.getNewRankJoin()+"%'";
			}
		}
		String sql = "select * from joinInfo where state=2 and oldRankJoin>0 "+str;
		return sql;
	}
	
	public String selectAllListWhitParam(final Map<String,Object> params){
		String str = "";
		if(params.get("joinInfo")!=null){
			JoinInfo joinInfo = (JoinInfo) params.get("joinInfo");
			if(joinInfo.getStartTime()!=null) str=str+" and entryTime>='"+joinInfo.getStartTime()+"'"; 
			if(joinInfo.getEndTime()!=null) str=str+" and entryTime<='"+joinInfo.getEndTime()+"'"; 
			if(joinInfo.getPrice()!=null){
				if(joinInfo.getTag()==1)
					str=str+" and price<=0 ";
				else
						str=str+" and price>0 ";
			}
			if(joinInfo.getOldRankJoin()!=null){
				str+=" and oldRankJoin like '%"+joinInfo.getOldRankJoin()+"%'";
			}
			if(joinInfo.getNewRankJoin()!=null){
				str+=" and newRankJoin like '%"+joinInfo.getNewRankJoin()+"%'";
			}
		}
		String sql = "select * from joinInfo where state=2 "+str;
		System.out.println(sql);
		return sql;
	}
	
	public String insertJoinInfo(final JoinInfo joinInfo){
		return new SQL(){
			{
				INSERT_INTO(Constants.JOININFOTABLE);
				if(joinInfo.getUserId()!=null&& !joinInfo.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(joinInfo.getUid()!=null){
					VALUES("uId","#{uid}");
				}
				if(joinInfo.getUserName()!=null&& !joinInfo.getUserName().equals("")){
					VALUES("userName","#{userName}");
				}
				if(joinInfo.getOldRankJoin()!=null){
					VALUES("oldRankJoin","#{oldRankJoin}");
				}
				if(joinInfo.getNewRankJoin()!=null){
					VALUES("newRankJoin","#{newRankJoin}");
				}
				if(joinInfo.getState()!=null){
					VALUES("state","#{state}");
				}
				if(joinInfo.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(joinInfo.getPv()!=null){
					VALUES("pv","#{pv}");
				}
				if(joinInfo.getPmoney()!=null){
					VALUES("pmoney","#{pmoney}");
				}
				if(joinInfo.getRid()!=null){
					VALUES("rid","#{rid}");
				}
				if(joinInfo.getDeclarationId()!=null){
					VALUES("declarationId","#{declarationId}");
				}
				if(joinInfo.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
				if(joinInfo.getTag()!=null){
					VALUES("tag","#{tag}");
				}
			}
		}.toString();
	}
	
	public String updateJoinInfo(final JoinInfo joinInfo){
		return new SQL(){
			{
				UPDATE(Constants.JOININFOTABLE);
				if(joinInfo.getState()!=null){
					SET("state=#{state}");
				}
			}
		}.toString();
	}
	
	public String updateForUser(final JoinInfo joinInfo){
		return new SQL(){
			{
				UPDATE(Constants.JOININFOTABLE);
				if(joinInfo.getState()!=null){
					SET("state=#{state}");
				}
			}
		}.toString();
	}
	
	
	

}
