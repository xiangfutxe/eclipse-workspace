package com.ssm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.Token;
import com.ssm.utils.Constants;

public class TokenDynaSqlProvider {
	
	
	public String save(final Token token){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.TOKENTABLE);
				if(token.getAccessToken()!=null&& !token.getAccessToken().equals("")){
					VALUES("access_token","#{accessToken}");
				}
				if(token.getExpiresIn()!=null){
					VALUES("expires_in","#{expiresIn}");
				}
				if(token.getState()!=null){
					VALUES("state","#{state}");
				}
				if(token.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
				if(token.getEndTime()!=null){
					VALUES("end_time","#{endTime}");
				}
			}
		}.toString();
	}
	
	public String update(final Token token){
		return new SQL(){
			{
				INSERT_INTO(Constants.TOKENTABLE);
				if(token.getAccessToken()!=null&& !token.getAccessToken().equals("")){
					SET("access_token=#{accessToken}");
				}
				if(token.getExpiresIn()!=null){
					SET("expires_in=#{expiresIn}");
				}
				if(token.getState()!=null){
					SET("state=#{state}");
				}
				if(token.getEndTime()!=null){
					SET("end_time=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	
	public String returnSql(String sql){
		return sql;
	}
	

}
