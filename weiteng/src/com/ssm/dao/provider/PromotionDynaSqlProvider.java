package com.ssm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.Promotion;
import com.ssm.utils.Constants;

public class PromotionDynaSqlProvider {
	
	
	public String insertPromotion(final Promotion pro){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.PROMOTIONTABLE);
				if(pro.getName()!=null&& !pro.getName().equals("")){
					VALUES("name","#{name}");
				}
				if(pro.getSummary()!=null&& !pro.getSummary().equals("")){
					VALUES("summary","#{summary}");
				}
				if(pro.getNum1()!=null){
					VALUES("num1","#{num1}");
				}
				if(pro.getNum2()!=null){
					VALUES("num2","#{num2}");
				}
				if(pro.getNum3()!=null){
					VALUES("num3","#{num3}");
				}
				if(pro.getNum4()!=null){
					VALUES("num4","#{num4}");
				}
				if(pro.getNum5()!=null){
					VALUES("num5","#{num5}");
				}
				if(pro.getState()!=null){
					VALUES("state","#{state}");
				}
				if(pro.getStartTime()!=null){
					VALUES("startTime","#{startTime}");
				}
				if(pro.getEndTime()!=null){
					VALUES("endTime","#{endTime}");
				}
				if(pro.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
			}
		}.toString();
	}
	
	public String updatePromotion(final Promotion pro){
		return new SQL(){
			{
				UPDATE(Constants.PROMOTIONTABLE);
				if(pro.getName()!=null&& !pro.getName().equals("")){
					SET("name=#{name}");
				}
				if(pro.getSummary()!=null&& !pro.getSummary().equals("")){
					SET("summary=#{summary}");
				}
				if(pro.getNum1()!=null){
					SET("num1=#{num1}");
				}
				if(pro.getNum2()!=null){
					SET("num2=#{num2}");
				}
				if(pro.getNum3()!=null){
					SET("num3=#{num3}");
				}
				if(pro.getNum4()!=null){
					SET("num4=#{num4}");
				}
				if(pro.getNum5()!=null){
					SET("num5=#{num5}");
				}
				if(pro.getState()!=null){
					SET("state=#{state}");
				}
				if(pro.getStartTime()!=null){
					SET("startTime=#{startTime}");
				}
				if(pro.getEndTime()!=null){
					SET("endTime=#{endTime}");
				}
				if(pro.getEntryTime()!=null){
					SET("entryTime=#{entryTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
