package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.Center;
import com.ssm.utils.Constants;

public class CenterDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.CENTERTABLE);
			if(params.get("center")!=null){
				Center center = (Center) params.get("center");
				if(center.getUserId()!=null && !center.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{center.userId},'%')");
				}
				if(center.getType()!=null){
					WHERE(" type LIKE CONCAT ('%',#{center.type},'%')");
				}
				if(center.getIsHide()!=null){
					WHERE(" is_hide LIKE CONCAT ('%',#{center.isHide},'%')");
				}
				if(center.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{center.state},'%')");
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
				FROM(Constants.CENTERTABLE);
				if(params.get("center")!=null){
					Center center = (Center) params.get("center");
					if(center.getUserId()!=null && !center.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{center.userId},'%')");
					}
					if(center.getType()!=null){
						WHERE(" type LIKE CONCAT ('%',#{center.type},'%')");
					}
					if(center.getIsHide()!=null){
						WHERE(" is_hide LIKE CONCAT ('%',#{center.isHide},'%')");
					}
					if(center.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{center.state},'%')");
					}
				}
				
			}
		}.toString();
	}
	
	public String selectListWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.CENTERTABLE);
			if(params.get("center")!=null){
				Center center = (Center) params.get("center");
				if(center.getUserId()!=null && !center.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{center.userId},'%')");
				}
				if(center.getType()!=null){
					WHERE(" type LIKE CONCAT ('%',#{center.type},'%')");
				}
				if(center.getIsHide()!=null){
					WHERE(" is_hide LIKE CONCAT ('%',#{center.isHide},'%')");
				}
				if(center.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{center.state},'%')");
				}
			}
			}
		}.toString();
			sql +=" order by id desc";
	
		return sql;
	}
	
	
	public String insertCenter(final Center center){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.CENTERTABLE);
				
				if(center.getCenterId()!=null&& !center.getCenterId().equals("")){
					VALUES("centerId","#{centerId}");
				}
				if(center.getCenterName()!=null&& !center.getCenterName().equals("")){
					VALUES("centerName","#{centerName}");
				}
				if(center.getuId()!=null){
					VALUES("uId","#{uId}");
				}
				if(center.getUserId()!=null&& !center.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(center.getUserName()!=null&& !center.getUserName().equals("")){
					VALUES("userName","#{userName}");
				}
				if(center.getPassword()!=null&& !center.getPassword().equals("")){
					VALUES("password","#{password}");
				}
				if(center.getProvince()!=null&& !center.getProvince().equals("")){
					VALUES("province","#{province}");
				}
				if(center.getCity()!=null&& !center.getCity().equals("")){
					VALUES("city","#{city}");
				}
				if(center.getArea()!=null&& !center.getArea().equals("")){
					VALUES("area","#{area}");
				}
				if(center.getAddress()!=null&& !center.getAddress().equals("")){
					SET("address=#{address}");
					VALUES("address","#{address}");
				}
				if(center.getLicense()!=null&& !center.getLicense().equals("")){
			
					VALUES("license","#{license}");
				}
				if(center.getMeasure()!=null&& !center.getMeasure().equals("")){
					VALUES("measure","#{measure}");
				}
				if(center.getPhone()!=null&& !center.getPhone().equals("")){
					VALUES("phone","#{phone}");
				}
				if(center.getType()!=null){
					VALUES("type","#{type}");
				}
				if(center.getIsHide()!=null){
					VALUES("is_hide","#{isHide}");
				}
				if(center.getTypeNew()!=null){
					VALUES("type_new","#{typeNew}");
				}
				if(center.getStateNew()!=null){
					VALUES("state_new","#{stateNew}");
				}
				if(center.getState()!=null){
					VALUES("state","#{state}");
				}
				if(center.getTypeForm()!=null){
					VALUES("type_form","#{typeForm}");
				}
				if(center.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
			}
		}.toString();
	}
	
	public String updateCenter(final Center center){
		return new SQL(){
			{
				UPDATE(Constants.CENTERTABLE);
				if(center.getCenterId()!=null&& !center.getCenterId().equals("")){
					SET("centerId=#{centerId}");
				}
				if(center.getCenterName()!=null&& !center.getCenterName().equals("")){
					SET("centerName=#{centerName}");
				}
				if(center.getPassword()!=null&& !center.getPassword().equals("")){
					SET("password=#{password}");
				}
				if(center.getProvince()!=null&& !center.getProvince().equals("")){
					SET("province=#{province}");
				}
				if(center.getCity()!=null&& !center.getCity().equals("")){
					SET("city=#{city}");
				}
				if(center.getArea()!=null&& !center.getArea().equals("")){
					SET("area=#{area}");
				}
				if(center.getAddress()!=null&& !center.getAddress().equals("")){
					SET("address=#{address}");
				}
				if(center.getLicense()!=null&& !center.getLicense().equals("")){
					SET("license=#{license}");
				}
				if(center.getMeasure()!=null&& !center.getMeasure().equals("")){
					SET("measure=#{measure}");
				}
				if(center.getPhone()!=null&& !center.getPhone().equals("")){
					SET("phone=#{phone}");
				}
				if(center.getType()!=null){
					SET("type=#{type}");
				}
				if(center.getIsHide()!=null){
					SET("is_hide=#{isHide}");
				}
				if(center.getTypeNew()!=null){
					SET("type_new=#{typeNew}");
				}
				if(center.getStateNew()!=null){
					SET("state_new=#{stateNew}");
				}
				if(center.getState()!=null){
					SET("state=#{state}");
				}
				if(center.getTypeForm()!=null){
					SET("type_form=#{typeForm}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
