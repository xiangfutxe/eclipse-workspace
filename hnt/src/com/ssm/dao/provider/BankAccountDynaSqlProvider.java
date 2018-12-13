package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.BankAccount;
import com.ssm.utils.Constants;

public class BankAccountDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.BANKACCOUNTTABLE);
			if(params.get("bank")!=null){
				BankAccount bank = (BankAccount) params.get("bank");
				if(bank.getAccountId()!=null){
					WHERE(" accountId LIKE CONCAT ('%',#{bank.accountId},'%')");
				}
				if(bank.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{bank.state},'%')");
				}
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.BANKACCOUNTTABLE);
				if(params.get("bank")!=null){
					BankAccount bank = (BankAccount) params.get("bank");
					if(bank.getAccountId()!=null){
						WHERE(" accountId LIKE CONCAT ('%',#{bank.accountId},'%')");
					}
					if(bank.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{bank.state},'%')");
					}
				}
				
			}
		}.toString();
	}
	
	public String insertBankAccount(final BankAccount bank){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.BANKACCOUNTTABLE);
				if(bank.getAccountId()!=null&& !bank.getAccountId().equals("")){
					VALUES("accountId","#{accountId}");
				}
				if(bank.getAccountName()!=null&& !bank.getAccountName().equals("")){
					VALUES("accountName","#{accountName}");
				}
				if(bank.getBankName()!=null&& !bank.getBankName().equals("")){
					VALUES("bankName","#{bankName}");
				}
				if(bank.getState()!=null){
					VALUES("state","#{state}");
				}
				if(bank.getEndTime()!=null){
					VALUES("endTime","#{endTime}");
				}
				if(bank.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
			}
		}.toString();
	}
	
	public String updateBankAccount(final BankAccount bank){
		return new SQL(){
			{
				UPDATE(Constants.BANKACCOUNTTABLE);
				if(bank.getAccountId()!=null&& !bank.getAccountId().equals("")){
					SET("accountId=#{accountId}");
				}
				if(bank.getAccountName()!=null&& !bank.getAccountName().equals("")){
					SET("accountName=#{accountName}");
				}
				if(bank.getBankName()!=null&& !bank.getBankName().equals("")){
					SET("bankName=#{bankName}");
				}
				if(bank.getState()!=null){
					SET("state=#{state}");
				}
				if(bank.getEndTime()!=null){
					SET("endTime=#{endTime}");
				}
				if(bank.getEntryTime()!=null){
					SET("entryTime=#{entryTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
