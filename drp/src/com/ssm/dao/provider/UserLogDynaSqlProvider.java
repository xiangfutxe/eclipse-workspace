package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.UserLog;
import com.ssm.utils.Constants;

public class UserLogDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.USERLOGTABLE);
			if(params.get("userLog")!=null){
				UserLog userLog = (UserLog) params.get("userLog");
				if(userLog.getUserId()!=null && !userLog.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{userLog.userId},'%')");
				}
				if(userLog.getUserName()!=null && !userLog.getUserName().equals("")){
					WHERE(" userName LIKE CONCAT ('%',#{userLog.userName},'%')");
				}
				if(userLog.getType()!=null  && !userLog.getType().equals("")){
					WHERE(" type LIKE CONCAT ('%',#{userLog.type},'%')");
				}
				if(userLog.getStartTime()!=null){
					WHERE("  entryTime >= #{userLog.startTime}");
				}
				if(userLog.getEndTime()!=null){
					WHERE("  entryTime <= #{userLog.endTime}");
				}
					
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc limit #{pageModel.startIndex},#{pageModel.pageSize} ";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.USERLOGTABLE);
				if(params.get("userLog")!=null){
					UserLog userLog = (UserLog) params.get("userLog");
					if(userLog.getUserId()!=null && !userLog.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{userLog.userId},'%')");
					}
					if(userLog.getUserName()!=null && !userLog.getUserName().equals("")){
						WHERE(" userName LIKE CONCAT ('%',#{userLog.userName},'%')");
					}
					if(userLog.getType()!=null && !userLog.getType().equals("")){
						WHERE(" type LIKE CONCAT ('%',#{userLog.type},'%')");
					}
					if(userLog.getStartTime()!=null){
						WHERE("  entryTime >= #{userLog.startTime}");
					}
					if(userLog.getEndTime()!=null){
						WHERE("  entryTime <= #{userLog.endTime}");
					}
				}
				
			}
		}.toString();
	}
	
	public String selectListWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.USERLOGTABLE);
			if(params.get("userLog")!=null){
				UserLog userLog = (UserLog) params.get("userLog");
				if(userLog.getUserId()!=null && !userLog.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{userLog.userId},'%')");
				}
				if(userLog.getUserName()!=null && !userLog.getUserName().equals("")){
					WHERE(" userName LIKE CONCAT ('%',#{userLog.userName},'%')");
				}
				if(userLog.getType()!=null  && !userLog.getType().equals("")){
					WHERE(" type LIKE CONCAT ('%',#{userLog.type},'%')");
				}
				if(userLog.getStartTime()!=null){
					WHERE("  entryTime >= #{userLog.startTime}");
				}
				if(userLog.getEndTime()!=null){
					WHERE("  entryTime <= #{userLog.endTime}");
				}
					
			}
			}
		}.toString();
		sql +=" order by id desc";
		return sql;
	}
	
	public String insertUserLog(final UserLog userLog){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.USERLOGTABLE);
				if(userLog.getUid()!=null&& !userLog.getUid().equals("")){
					VALUES("uid","#{uid}");
				}
				if(userLog.getUserId()!=null&& !userLog.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(userLog.getUserName()!=null&& !userLog.getUserName().equals("")){
					VALUES("userName","#{userName}");
				}
				if(userLog.getType()!=null&& !userLog.getType().equals("")){
					VALUES("type","#{type}");
				}
				if(userLog.getState()!=null){
					VALUES("state","#{state}");
				}
				if(userLog.getContents()!=null&& !userLog.getContents().equals("")){
					VALUES("contents","#{contents}");
				}
				if(userLog.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
				
			}
		}.toString();
	}
	
	public String updateUserLog(final UserLog userLog){
		return new SQL(){
			{
				UPDATE(Constants.USERLOGTABLE);
				if(userLog.getUserId()!=null&& !userLog.getUserId().equals("")){
					SET("userId=#{userId}");
				}
				if(userLog.getUserName()!=null&& !userLog.getUserName().equals("")){
					SET("userName=#{userName}");
				}
				if(userLog.getType()!=null&& !userLog.getType().equals("")){
					SET("type=#{type}");
				}
				if(userLog.getContents()!=null&& !userLog.getContents().equals("")){
					SET("contents=#{contents}");
				}
				
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
