package com.ssm.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Settle;
import com.ssm.utils.Constants;

public class SettleDynaSqlProvider {
	
	
	
	public String insertSettle(final Settle st){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.SETTLETABLE);
				if(st.getMonthTag()!=null){
					VALUES("month_tag","#{monthTag}");
				}
			
				if(st.getTotalPrice()!=null){
					VALUES("total_price","#{totalPrice}");
				}
				
				if(st.getNewPrice()!=null){
					VALUES("new_price","#{newPrice}");
				}
				
				if(st.getState()!=null){
					VALUES("state","#{state}");
				}
				if(st.getEndTime()!=null){
					VALUES("start_time","#{startTime}");
				}
				if(st.getEndTime()!=null){
					VALUES("end_time","#{endTime}");
				}
				if(st.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
			}
		}.toString();
	}
	
	public String updateSettle(final Settle st){
		return new SQL(){
			{
				UPDATE(Constants.SETTLETABLE);
				
				if(st.getNewPrice()!=null){
					SET("new_price=#{newPrice}");
				}
			
				if(st.getState()!=null){
					SET("state=#{state}");
				}
				
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
