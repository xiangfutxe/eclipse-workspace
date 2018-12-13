package com.ssm.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.SettleDay;
import com.ssm.pojo.Settle;
import com.ssm.utils.Constants;

public class SettleDayDynaSqlProvider {
	
	
	
	public String insertSettle(final SettleDay st){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.SETTLEDAYTABLE);
				if(st.getWeekTag()!=null){
					VALUES("weekTag","#{weekTag}");
				}
				if(st.getTotalPerformance()!=null){
					VALUES("totalPerformance","#{totalPerformance}");
				}
				if(st.getTotalPerformance_1()!=null){
					VALUES("totalPerformance_1","#{totalPerformance_1}");
				}
				if(st.getTotalPerformance_2()!=null){
					VALUES("totalPerformance_2","#{totalPerformance_2}");
				}
				if(st.getNewPerformance()!=null){
					VALUES("newPerformance","#{newPerformance}");
				}
				if(st.getNewPerformance_1()!=null){
					VALUES("NewPerformance_1","#{newPerformance_1}");
				}
				if(st.getNewPerformance_2()!=null){
					VALUES("newPerformance_2","#{newPerformance_2}");
				}
				if(st.getTotalPrice()!=null){
					VALUES("totalPrice","#{totalPrice}");
				}
				if(st.getTotalPrice_1()!=null){
					VALUES("totalPrice_1","#{totalPrice_1}");
				}
				if(st.getTotalPrice_2()!=null){
					VALUES("totalPrice_2","#{totalPrice_2}");
				}
				if(st.getNewPrice()!=null){
					VALUES("newPrice","#{newPrice}");
				}
				if(st.getNewPrice_1()!=null){
					VALUES("newPrice_1","#{newPrice_1}");
				}
				if(st.getNewPrice_2()!=null){
					VALUES("newPrice_2","#{newPrice_2}");
				}
				if(st.getTotalNum()!=null){
					VALUES("totalNum","#{totalNum}");
				}
				if(st.getNewNum()!=null){
					VALUES("newNum","#{newNum}");
				}
				if(st.getTotalNum()!=null){
					VALUES("totalNumReal","#{totalNumReal}");
				}
				if(st.getNewNum()!=null){
					VALUES("newNumReal","#{newNumReal}");
				}
				if(st.getTag()!=null){
					VALUES("tag","#{tag}");
				}
				if(st.getFlag()!=null){
					VALUES("flag","#{flag}");
				}
				if(st.getState()!=null){
					VALUES("state","#{state}");
				}
				if(st.getDayTime()!=null){
					VALUES("dayTime","#{dayTime}");
				}
				if(st.getEndTime()!=null){
					VALUES("startTime","#{startTime}");
				}
				if(st.getEndTime()!=null){
					VALUES("endTime","#{endTime}");
				}
				if(st.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
			}
		}.toString();
	}
	
	public String updateSettle(final SettleDay st){
		return new SQL(){
			{
				UPDATE(Constants.SETTLEDAYTABLE);
				if(st.getWeekTag()!=null){
					SET("weekTag=#{weekTag}");
				}
				if(st.getTotalPerformance()!=null){
					SET("totalPerformance=#{totalPerformance}");
				}
				if(st.getTotalPerformance_1()!=null){
					SET("totalPerformance_1=#{totalPerformance_1}");
				}
				if(st.getTotalPerformance_2()!=null){
					SET("totalPerformance_2=#{totalPerformance_2}");
				}
				if(st.getNewPerformance()!=null){
					SET("newPerformance=#{newPerformance}");
				}
				if(st.getNewPerformance_1()!=null){
					SET("NewPerformance_1=#{newPerformance_1}");
				}
				if(st.getNewPerformance_2()!=null){
					SET("newPerformance_2=#{newPerformance_2}");
				}
				if(st.getTotalPrice()!=null){
					SET("totalPrice=#{totalPrice}");
				}
				if(st.getTotalPrice_1()!=null){
					SET("totalPrice_1=#{totalPrice_1}");
				}
				if(st.getTotalPrice_2()!=null){
					SET("totalPrice_2=#{totalPrice_2}");
				}
				if(st.getNewPrice()!=null){
					SET("newPrice=#{newPrice}");
				}
				if(st.getNewPrice_1()!=null){
					SET("newPrice_1=#{newPrice_1}");
				}
				if(st.getNewPrice_2()!=null){
					SET("newPrice_2=#{newPrice_2}");
				}
				if(st.getTotalNum()!=null){
					SET("totalNum=#{totalNum}");
				}
				if(st.getNewNum()!=null){
					SET("newNum=#{newNum}");
				}
				if(st.getTotalNum()!=null){
					SET("totalNumReal=#{totalNumReal}");
				}
				if(st.getNewNum()!=null){
					SET("newNumReal=#{newNumReal}");
				}
				if(st.getTag()!=null){
					SET("tag=#{tag}");
				}
				if(st.getFlag()!=null){
					SET("flag=#{flag}");
				}
				if(st.getState()!=null){
					SET("state=#{state}");
				}
				if(st.getEndTime()!=null){
					SET("startTime=#{startTime}");
				}
				if(st.getEndTime()!=null){
					SET("endTime=#{endTime}");
				}
				if(st.getEntryTime()!=null){
					SET("entryTime=#{entryTime}");
				}
				if(st.getDayTime()!=null){
					SET("dayTime=#{dayTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	
	public String returnSql(String sql){
		return sql;
	}


}
