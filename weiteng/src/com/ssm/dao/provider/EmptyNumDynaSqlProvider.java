package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.EmptyNum;
import com.ssm.utils.Constants;

public class EmptyNumDynaSqlProvider {
	
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.EMPTYNUMTABLE);
			if(params.get("emptyNum")!=null){
				EmptyNum emptyNum = (EmptyNum) params.get("emptyNum");
				if(emptyNum.getUserId()!=null && !emptyNum.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{emptyNum.userId},'%'");
				}
				if(emptyNum.getType()!=null){
					WHERE("  type >= #{emptyNum.type}");
				}
				if(emptyNum.getStartTime()!=null){
					WHERE("  entry_time >= #{emptyNum.startTime}");
				}
				if(emptyNum.getEndTime()!=null){
					WHERE("  entry_time <= #{emptyNum.endTime}");
				}
			
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by entry_time desc limit #{pageModel.startIndex},#{pageModel.pageSize} ";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.EMPTYNUMTABLE);
				if(params.get("emptyNum")!=null){
					EmptyNum emptyNum = (EmptyNum) params.get("emptyNum");
					if(emptyNum.getUserId()!=null && !emptyNum.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{emptyNum.userId},'%'");
					}
					if(emptyNum.getType()!=null){
						WHERE("  type >= #{emptyNum.type}");
					}
					if(emptyNum.getStartTime()!=null){
						WHERE("  entry_time >= #{emptyNum.startTime}");
					}
					if(emptyNum.getEndTime()!=null){
						WHERE("  entry_time <= #{emptyNum.endTime}");
					}
				
				}
				
			}
		}.toString();
	}
	
	
	public String insertEmptyNum(final EmptyNum emptyNum){
		return new SQL(){
			{
				INSERT_INTO(Constants.EMPTYNUMTABLE);
				if(emptyNum.getUid()!=null){
					VALUES("uid","#{uid}");
				}
				if(emptyNum.getUserId()!=null&& !emptyNum.getUserId().equals("")){
					VALUES("user_id","#{userId}");
				}
				if(emptyNum.getUserName()!=null&& !emptyNum.getUserName().equals("")){
					VALUES("user_name","#{userName}");
				}
				if(emptyNum.getSummary()!=null&& !emptyNum.getSummary().equals("")){
					VALUES("summary","#{summary}");
				}
				if(emptyNum.getAmount()!=null){
					VALUES("amount","#{amount}");
				}
				if(emptyNum.getBalance()!=null){
					VALUES("balance","#{balance}");
				}
				if(emptyNum.getType()!=null){
					VALUES("type","#{type}");
				}
				if(emptyNum.getState()!=null){
					VALUES("state","#{state}");
				}
				if(emptyNum.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
			}
		}.toString();
	}

}
