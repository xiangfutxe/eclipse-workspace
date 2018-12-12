package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Dept;
import com.ssm.utils.Constants;

public class DeptDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.DEPTTABLE);
			if(params.get("dept")!=null){
				Dept dept = (Dept) params.get("dept");
				if(dept.getName()!=null && !dept.getName().equals("")){
					WHERE(" name LIKE CONCAT ('%',#{dept.name},'%')");
				}
				if(dept.getState()!=null ){
					WHERE(" state LIKE CONCAT ('%',#{dept.state},'%')");
				}
			
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.DEPTTABLE);
				if(params.get("dept")!=null){
					Dept dept = (Dept) params.get("dept");
					if(dept.getName()!=null && !dept.getName().equals("")){
						WHERE(" name LIKE CONCAT ('%',#{dept.name},'%')");
					}
					if(dept.getState()!=null ){
						WHERE(" state LIKE CONCAT ('%',#{dept.state},'%')");
					}
				}
				
			}
		}.toString();
	}
	
	public String insertDept(final Dept dept){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.DEPTTABLE);
				if(dept.getName()!=null&& !dept.getName().equals("")){
					VALUES("name","#{name}");
				}
				if(dept.getRemark()!=null&& !dept.getRemark().equals("")){
					VALUES("remark","#{remark}");
				}
				if(dept.getState()!=null){
					VALUES("state","#{state}");
				}
				if(dept.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
				if(dept.getEndTime()!=null){
					VALUES("end_time","#{endTime}");
				}
			}
		}.toString();
	}
	
	public String updateDept(final Dept dept){
		return new SQL(){
			{
				UPDATE(Constants.DEPTTABLE);
				if(dept.getName()!=null&& !dept.getName().equals("")){
					SET("name=#{name}");
				}
				if(dept.getRemark()!=null&& !dept.getRemark().equals("")){
					SET("remark=#{remark}");
				}
				if(dept.getState()!=null){
					SET("state=#{state}");
				}
				if(dept.getEndTime()!=null){
					SET("end_time=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
