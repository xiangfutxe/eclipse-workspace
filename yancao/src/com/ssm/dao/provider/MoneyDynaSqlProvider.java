package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.AccountDetail;

public class MoneyDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		final String tableName =  (String) params.get("tableName");
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(tableName);
			if(params.get("account")!=null){
				AccountDetail account = (AccountDetail) params.get("account");
				if(account.getUserId()!=null && !account.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{account.userId},'%')");
				}
				if(account.getTradeType()!=null && !account.getTradeType().equals("")){
					WHERE(" trade_type LIKE CONCAT ('%',#{account.tradeType},'%')");
				}
				if(account.getStartTime()!=null){
					WHERE("  entry_time >= #{account.startTime}");
				}
				if(account.getEndTime()!=null){
					WHERE("  entry_time <= #{account.endTime}");
				}
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by entry_time desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String selectAllWhitParam(final Map<String,Object> params){
		final String tableName =  (String) params.get("tableName");
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(tableName);
			if(params.get("account")!=null){
				AccountDetail account = (AccountDetail) params.get("account");
				if(account.getUserId()!=null && !account.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{account.userId},'%')");
				}
				if(account.getTradeType()!=null && !account.getTradeType().equals("")){
					WHERE(" trade_type LIKE CONCAT ('%',#{account.tradeType},'%')");
				}
				if(account.getStartTime()!=null){
					WHERE("  entry_time >= #{account.startTime}");
				}
				if(account.getEndTime()!=null){
					WHERE("  entry_time <= #{account.endTime}");
				}
			}
			}
		}.toString();
			sql +=" order by entry_time desc";
		return sql;
	}
	
	public String selectListWhitParam(String sql){
	
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		final String tableName =  (String) params.get("tableName");
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(tableName);
				if(params.get("account")!=null){
					AccountDetail account = (AccountDetail) params.get("account");
					if(account.getUserId()!=null && !account.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{account.userId},'%')");
					}
					if(account.getTradeType()!=null && !account.getTradeType().equals("")){
						WHERE(" trade_type LIKE CONCAT ('%',#{account.tradeType},'%')");
					}
					if(account.getStartTime()!=null){
						WHERE("  entry_time >= #{account.startTime}");
					}
					if(account.getEndTime()!=null){
						WHERE("  entry_time <= #{account.endTime}");
					}
				}
			}
		}.toString();
	}
	
	public String sum1( final Map<String,Object> params){
		final String tableName =  (String) params.get("tableName");
		return new SQL(){
			{
				SELECT("sum(amount)");
				FROM(tableName);
				if(params.get("account")!=null){
					AccountDetail account = (AccountDetail) params.get("account");
					if(account.getUserId()!=null && !account.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{account.userId},'%')");
					}
					if(account.getTradeType()!=null && !account.getTradeType().equals("")){
						WHERE(" trade_type LIKE CONCAT ('%',#{account.tradeType},'%')");
					}
					if(account.getStartTime()!=null){
						WHERE("  entry_time >= #{account.startTime}");
					}
					if(account.getEndTime()!=null){
						WHERE("  entry_time <= #{account.endTime}");
					}
					WHERE(" pay_type = '1' ");
				}
			}
		}.toString();
	}
	
	
	
	public String sum2( final Map<String,Object> params){
		final String tableName =  (String) params.get("tableName");
		return new SQL(){
			{
				SELECT("sum(amount)");
				FROM(tableName);
				if(params.get("account")!=null){
					AccountDetail account = (AccountDetail) params.get("account");
					if(account.getUserId()!=null && !account.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{account.userId},'%')");
					}
					if(account.getTradeType()!=null && !account.getTradeType().equals("")){
						WHERE(" trade_type LIKE CONCAT ('%',#{account.tradeType},'%')");
					}
					if(account.getStartTime()!=null){
						WHERE("  entry_time >= #{account.startTime}");
					}
					if(account.getEndTime()!=null){
						WHERE("  entry_time <= #{account.endTime}");
					}
					WHERE("  pay_type = '2' ");
				}
			}
		}.toString();
	}
	
	public String sum( final Map<String,Object> params){
		final String tableName =  (String) params.get("tableName");
		return new SQL(){
			{
				SELECT("sum(amount)");
				FROM(tableName);
				AccountDetail account = (AccountDetail) params.get("account");
				Integer payType = (Integer) params.get("payType");
				
				if(account.getUserId()!=null && !account.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{account.userId},'%')");
				}
				if(account.getTradeType()!=null && !account.getTradeType().equals("")){
					WHERE(" trade_type LIKE CONCAT ('%',#{account.tradeType},'%')");
				}
				if(account.getStartTime()!=null){
					WHERE("  entry_time >= #{account.startTime}");
				}
				if(account.getEndTime()!=null){
					WHERE("  entry_time <= #{account.endTime}");
				}
				if(payType!=null){
					WHERE("  pay_type = #{payType}");
				}
				
			}
		}.toString();
	}
	
	public String insertMoney(final Map<String,Object> params){
		final String tableName =  (String) params.get("tableName");
		String sql = new SQL(){
			{
				INSERT_INTO(tableName);
				if(params.get("account")!=null){
				AccountDetail account = (AccountDetail) params.get("account");
				if(account.getUid()!=null){
					VALUES("uid","#{account.uid}");
				}
				if(account.getUserId()!=null&&!account.getUserId().equals("")){
					VALUES("user_id","#{account.userId}");
				}
				if(account.getUserName()!=null&& !account.getUserName().equals("")){
					VALUES("user_name","#{account.userName}");
				}
				if(account.getAmount()!=null){
					VALUES("amount","#{account.amount}");
				}
				if(account.getBalance()!=null){
					VALUES("balance","#{account.balance}");
				}
				if(account.getPayType()!=null){
					VALUES("pay_type","#{account.payType}");
				}
				if(account.getEntryTime()!=null){
					VALUES("entry_time","#{account.entryTime}");
				}
				if(account.getTradeType()!=null&& !account.getTradeType().equals("")){
					VALUES("trade_type","#{account.tradeType}");
				}
				if(account.getSummary()!=null&& !account.getSummary().equals("")){
					VALUES("summary","#{account.summary}");
				}
				}
			}
		}.toString();
		return sql;
	}
	
	public String updateMoney(final Map<String,Object> params){
		final String tableName =  (String) params.get("tableName");
		return new SQL(){
			{
				UPDATE(tableName);
				if(params.get("account")!=null){
					AccountDetail account = (AccountDetail) params.get("account");
					if(account.getUid()!=null){
						SET("uid=#{account.uid}");
					}
				if(account.getUserId()!=null&& !account.getUserId().equals("")){
					SET("user_id=#{account.userId}");
				}
				if(account.getUserName()!=null&& !account.getUserName().equals("")){
					SET("user_name=#{account.userName}");
				}
				if(account.getAmount()!=null){
					SET("amount=#{account.amount}");
				}
				if(account.getBalance()!=null){
					SET("balance=#{account.balance}");
				}
				if(account.getPayType()!=null){
					SET("pay_type=#{account.payTime}");
				}
				if(account.getEntryTime()!=null){
					SET("entry_time=#{account.entryTime}");
				}
				if(account.getTradeType()!=null&& !account.getTradeType().equals("")){
					SET("trade_type=#{account.tradeType}");
				}
				if(account.getSummary()!=null&& !account.getSummary().equals("")){
					SET("summary=#{account.summary}");
				}
				WHERE(" id=#{account.id}");
				}
			}
				
		}.toString();
	}
	
	public String updateForSql(String sql){
		return sql;
	}
	
	/*
	public String updateMoney(final AccountDetail ad,final String tableName){
		String str = "";
		if(ad.getUserId()!=null&& !ad.getUserId().equals("")){
			if(str.equals("")) str= "userId='"+ad.getUserId()+"'";
			else  str= ",userId='"+ad.getUserId()+"'";
		}
		if(ad.getUserName()!=null&& !ad.getUserName().equals("")){
			if(str.equals("")) str= "userName='"+ad.getUserName()+"'";
			else  str= ",userName='"+ad.getUserName()+"'";
		}
		if(ad.getAmount()!=null){
			if(str.equals("")) str= "amount='"+ad.getAmount()+"'";
			else  str= ",amount='"+ad.getAmount()+"'";
		}
		if(ad.getBalance()!=null){
			if(str.equals("")) str= "balance='"+ad.getAmount()+"'";
			else  str= ",balance='"+ad.getAmount()+"'";
		}
		if(ad.getEntryTime()!=null){
			if(str.equals("")) str= "entryTime='"+ad.getEntryTime()+"'";
			else  str= ",entryTime='"+ad.getEntryTime()+"'";
		}
		if(ad.getTradeType()!=null&& !ad.getTradeType().equals("")){
			if(str.equals("")) str= "tradeType='"+ad.getTradeType()+"'";
			else  str= ",tradeType='"+ad.getTradeType()+"'";
		}
		if(ad.getSummary()!=null&& !ad.getSummary().equals("")){
			if(str.equals("")) str= "summary='"+ad.getSummary()+"'";
			else  str= ",summary='"+ad.getSummary()+"'";
		}
		str = "update "+tableName+" set "+str+" where id='"+ad.getId()+"'";
		System.out.println(str);
		return str;
	}
	*/

}
