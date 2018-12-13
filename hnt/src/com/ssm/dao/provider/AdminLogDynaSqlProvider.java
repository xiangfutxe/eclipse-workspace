package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.AdminLog;
import com.ssm.utils.Constants;

public class AdminLogDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.ADMINLOGTABLE);
			if(params.get("adminLog")!=null){
				AdminLog adminLog = (AdminLog) params.get("adminLog");
				if(adminLog.getAdminName()!=null && !adminLog.getAdminName().equals("")){
					WHERE(" adminName LIKE CONCAT ('%',#{adminLog.adminName},'%')");
				}
				if(adminLog.getType()!=null  && !adminLog.getType().equals("")){
					WHERE(" type LIKE CONCAT ('%',#{adminLog.type},'%')");
				}
				if(adminLog.getStartTime()!=null){
					WHERE("  entryTime >= #{adminLog.startTime}");
				}
				if(adminLog.getEndTime()!=null){
					WHERE("  entryTime <= #{adminLog.endTime}");
				}
					
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc limit #{pageModel.startIndex},#{pageModel.pageSize} ";
		}else{
			sql +=" order by id desc";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.ADMINLOGTABLE);
				if(params.get("adminLog")!=null){
					AdminLog adminLog = (AdminLog) params.get("adminLog");
					if(adminLog.getAdminName()!=null && !adminLog.getAdminName().equals("")){
						WHERE(" adminName LIKE CONCAT ('%',#{adminLog.adminName},'%')");
					}
					if(adminLog.getType()!=null && !adminLog.getType().equals("")){
						WHERE(" type LIKE CONCAT ('%',#{adminLog.type},'%')");
					}
					if(adminLog.getStartTime()!=null){
						WHERE("  entryTime >= #{adminLog.startTime}");
					}
					if(adminLog.getEndTime()!=null){
						WHERE("  entryTime <= #{adminLog.endTime}");
					}
				}
				
			}
		}.toString();
	}
	
	public String insertAdminLog(final AdminLog adminLog){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.ADMINLOGTABLE);
				if(adminLog.getAdminName()!=null&& !adminLog.getAdminName().equals("")){
					VALUES("adminName","#{adminName}");
				}
				if(adminLog.getType()!=null&& !adminLog.getType().equals("")){
					VALUES("type","#{type}");
				}
				if(adminLog.getContents()!=null&& !adminLog.getContents().equals("")){
					VALUES("contents","#{contents}");
				}
				
				if(adminLog.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
				
			}
		}.toString();
	}
	
	public String updateAdminLog(final AdminLog adminLog){
		return new SQL(){
			{
				UPDATE(Constants.ADMINLOGTABLE);
				if(adminLog.getAdminName()!=null&& !adminLog.getAdminName().equals("")){
					SET("adminName=#{adminName}");
				}
				if(adminLog.getType()!=null&& !adminLog.getType().equals("")){
					SET("type=#{type}");
				}
				if(adminLog.getContents()!=null&& !adminLog.getContents().equals("")){
					SET("contents=#{contents}");
				}
				
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	
	public String returnSql(String sql){
		return sql;
	}

}
