package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.AccountSupplement;
import com.ssm.utils.Constants;

public class AccountSupplementDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.ACCOUNTSUPPLEMENTTABLE);
			if(params.get("accountSupplement")!=null){
				AccountSupplement accountSupplement = (AccountSupplement) params.get("accountSupplement");
				if(accountSupplement.getUserId()!=null && !accountSupplement.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{accountSupplement.userId},'%')");
				}
				if(accountSupplement.getType()!=null){
					WHERE("  type LIKE CONCAT ('%',#{accountSupplement.type},'%')");
				}
				if(accountSupplement.getPayType()!=null){
					WHERE("  pay_type LIKE CONCAT ('%',#{accountSupplement.payType},'%')");
				}
				if(accountSupplement.getStartTime()!=null){
					WHERE("  entry_time >= #{accountSupplement.startTime}");
				}
				if(accountSupplement.getEndTime()!=null){
					WHERE("  entry_time <= #{accountSupplement.endTime}");
				}
			
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String sumByParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
				SELECT (" sum(amount) amount ");
			FROM(Constants.ACCOUNTSUPPLEMENTTABLE);
			if(params.get("accountSupplement")!=null){
				AccountSupplement accountSupplement = (AccountSupplement) params.get("accountSupplement");
				if(accountSupplement.getUserId()!=null && !accountSupplement.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{accountSupplement.userId},'%')");
				}
				if(accountSupplement.getType()!=null){
					WHERE("  type LIKE CONCAT ('%',#{accountSupplement.type},'%')");
				}
				if(accountSupplement.getPayType()!=null){
					WHERE("  pay_type LIKE CONCAT ('%',#{accountSupplement.payType},'%')");
				}
				if(accountSupplement.getStartTime()!=null){
					WHERE("  entry_time >= #{accountSupplement.startTime}");
				}
				if(accountSupplement.getEndTime()!=null){
					WHERE("  entry_time <= #{accountSupplement.endTime}");
				}
			
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc";
		}
		return sql;
	}
	
	public String selectListWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.ACCOUNTSUPPLEMENTTABLE);
			if(params.get("accountSupplement")!=null){
				AccountSupplement accountSupplement = (AccountSupplement) params.get("accountSupplement");
				if(accountSupplement.getUserId()!=null && !accountSupplement.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{accountSupplement.userId},'%')");
				}
				if(accountSupplement.getType()!=null){
					WHERE("  type LIKE CONCAT ('%',#{accountSupplement.type},'%')");
				}
				if(accountSupplement.getPayType()!=null){
					WHERE("  pay_type LIKE CONCAT ('%',#{accountSupplement.payType},'%')");
				}
				if(accountSupplement.getStartTime()!=null){
					WHERE("  entry_time >= #{accountSupplement.startTime}");
				}
				if(accountSupplement.getEndTime()!=null){
					WHERE("  entry_time <= #{accountSupplement.endTime}");
				}
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.ACCOUNTSUPPLEMENTTABLE);
				if(params.get("accountSupplement")!=null){
					AccountSupplement accountSupplement = (AccountSupplement) params.get("accountSupplement");
					if(accountSupplement.getUserId()!=null && !accountSupplement.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{accountSupplement.userId},'%')");
					}
					if(accountSupplement.getType()!=null){
						WHERE("  type LIKE CONCAT ('%',#{accountSupplement.type},'%')");
					}
					if(accountSupplement.getPayType()!=null){
						WHERE("  pay_type LIKE CONCAT ('%',#{accountSupplement.payType},'%')");
					}
					if(accountSupplement.getStartTime()!=null){
						WHERE("  entry_time >= #{accountSupplement.startTime}");
					}
					if(accountSupplement.getEndTime()!=null){
						WHERE("  entry_time <= #{accountSupplement.endTime}");
					}
					
				}
				
			}
		}.toString();
	}
	
	public String insertAccountSupplement(final AccountSupplement accountSupplement){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.ACCOUNTSUPPLEMENTTABLE);
				if(accountSupplement.getUid()!=null){
					VALUES("uid","#{uid}");
				}
				if(accountSupplement.getUserId()!=null&& !accountSupplement.getUserId().equals("")){
					VALUES("user_id","#{userId}");
				}
				if(accountSupplement.getUserName()!=null&& !accountSupplement.getUserName().equals("")){
					VALUES("user_name","#{userName}");
				}
				if(accountSupplement.getAmount()!=null){
					VALUES("amount","#{amount}");
				}
				if(accountSupplement.getPayType()!=null){
					VALUES("pay_type","#{payType}");
				}
				if(accountSupplement.getType()!=null){
					VALUES("type","#{type}");
				}
			
				if(accountSupplement.getAdmin()!=null&& !accountSupplement.getAdmin().equals("")){
					VALUES("admin","#{admin}");
				}
				if(accountSupplement.getSummary()!=null&& !accountSupplement.getSummary().equals("")){
					VALUES("summary","#{summary}");
				}
				if(accountSupplement.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
				
			}
		}.toString();
	}
	
	public String updateAccountSupplement(final AccountSupplement accountSupplement){
		return new SQL(){
			{
				UPDATE(Constants.ACCOUNTSUPPLEMENTTABLE);
				if(accountSupplement.getUid()!=null){
					SET("uid=#{uid}");
				}
				if(accountSupplement.getUserId()!=null&& !accountSupplement.getUserId().equals("")){
					SET("user_id=#{userId}");
				}
				if(accountSupplement.getUserName()!=null&& !accountSupplement.getUserName().equals("")){
					SET("user_name=#{userName}");
				}
				if(accountSupplement.getAmount()!=null){
					SET("amount=#{amount}");
				}
				if(accountSupplement.getPayType()!=null){
					SET("pay_type=#{payType}");
				}
				if(accountSupplement.getType()!=null){
					SET("type=#{type}");
				}
			
				if(accountSupplement.getAdmin()!=null&& !accountSupplement.getAdmin().equals("")){
					SET("admin=#{admin}");
				}
				if(accountSupplement.getSummary()!=null&& !accountSupplement.getSummary().equals("")){
					SET("summary=#{summary}");
				}
				if(accountSupplement.getEntryTime()!=null){
					SET("entry_time=#{entryTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
