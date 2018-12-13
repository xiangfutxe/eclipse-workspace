package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.Address;
import com.ssm.utils.Constants;

public class AddressDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.ADDRESSTABLE);
			if(params.get("adr")!=null){
				Address adr = (Address) params.get("adr");
				if(adr.getUserId()!=null && !adr.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{adr.userId},'%')");
				}
				if(adr.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{adr.state},'%')");
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
				FROM(Constants.ADDRESSTABLE);
				if(params.get("adr")!=null){
					Address adr = (Address) params.get("adr");
					if(adr.getUserId()!=null && !adr.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{adr.userId},'%')");
					}
					if(adr.getState()!=null){
						WHERE("state LIKE CONCAT ('%',#{adr.state},'%')");
					}
					
				}
				
			}
		}.toString();
	}
	
	public String insertAddress(final Address adr){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.ADDRESSTABLE);
				if(adr.getUserId()!=null&& !adr.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(adr.getReceiver()!=null&& !adr.getReceiver().equals("")){
					VALUES("receiver","#{receiver}");
				}
				if(adr.getProvince()!=null&& !adr.getProvince().equals("")){
					VALUES("province","#{province}");
				}
				if(adr.getCity()!=null&& !adr.getCity().equals("")){
					VALUES("city","#{city}");
				}
				if(adr.getArea()!=null&& !adr.getArea().equals("")){
					VALUES("area","#{area}");
				}
				if(adr.getAddress()!=null&& !adr.getAddress().equals("")){
					VALUES("address","#{address}");
				}
				if(adr.getPhone()!=null&& !adr.getPhone().equals("")){
					VALUES("phone","#{phone}");
				}
				if(adr.getTag()!=null){
					VALUES("tag","#{tag}");
				}
				
				if(adr.getState()!=null){
					VALUES("state","#{state}");
				}
				if(adr.getEndTime()!=null){
					VALUES("endTime","#{endTime}");
				}
				if(adr.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
			}
		}.toString();
	}
	
	public String updateAddress(final Address adr){
		return new SQL(){
			{
				UPDATE(Constants.ADDRESSTABLE);
				if(adr.getUserId()!=null&& !adr.getUserId().equals("")){
					SET("userId=#{userId}");
				}
				if(adr.getProvince()!=null&& !adr.getProvince().equals("")){
					SET("province=#{province}");
				}
				if(adr.getCity()!=null&& !adr.getCity().equals("")){
					SET("city=#{city}");
				}
				if(adr.getArea()!=null&& !adr.getArea().equals("")){
					SET("area=#{area}");
				}
				if(adr.getAddress()!=null&& !adr.getAddress().equals("")){
					SET("address=#{address}");
				}
				if(adr.getPhone()!=null&& !adr.getPhone().equals("")){
					SET("phone=#{phone}");
				}
				if(adr.getTag()!=null){
					SET("tag=#{tag}");
				}
				
				if(adr.getState()!=null){
					SET("state=#{state}");
				}
				if(adr.getEndTime()!=null){
					SET("endTime=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
