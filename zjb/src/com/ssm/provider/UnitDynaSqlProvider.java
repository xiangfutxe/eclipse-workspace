package com.ssm.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Unit;
import com.utils.Constants;

public class UnitDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.UNITTABLE);
			if(params.get("unit")!=null){
				Unit unit = (Unit) params.get("unit");
				if(unit.getName()!=null && !unit.getName().equals("")){
					WHERE(" name LIKE CONCAT ('%',#{name.sort},'%')");
				}
				if(unit.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{unit.state},'%')");
				}
				if(unit.getStartTime()!=null){
					WHERE("  entry_time >= #{unit.startTime}");
				}
				if(unit.getEndTime()!=null){
					WHERE("  entry_time <= #{unit.endTime}");
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
				SELECT("ifnull(count(*),0)");
				FROM(Constants.UNITTABLE);
				if(params.get("unit")!=null){
					Unit unit = (Unit) params.get("unit");
					if(unit.getName()!=null && !unit.getName().equals("")){
						WHERE(" name LIKE CONCAT ('%',#{unit.name},'%')");
					}
					if(unit.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{unit.state},'%')");
					}
					if(unit.getStartTime()!=null){
						WHERE("  entry_time >= #{unit.startTime}");
					}
					if(unit.getEndTime()!=null){
						WHERE("  entry_time <= #{unit.endTime}");
					}
				}
				
			}
		}.toString();
	}
	
	public String insert(final Unit unit){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.UNITTABLE);
				if(unit.getName()!=null&& !unit.getName().equals("")){
					VALUES("name","#{name}");
				}
				if(unit.getType()!=null){
					VALUES("type","#{type}");
				}
				if(unit.getState()!=null){
					VALUES("state","#{state}");
				}
				if(unit.getParentId()!=null){
					VALUES("parent_id","#{parentId}");
				}
				if(unit.getVersion()!=null){
					VALUES("version","#{version}");
				}
				if(unit.getEndTime()!=null){
					VALUES("end_time","#{endTime}");
				}
				if(unit.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
			}
		}.toString();
	}
	
	public String update(final Unit unit){
		return new SQL(){
			{
				UPDATE(Constants.UNITTABLE);
				if(unit.getName()!=null&& !unit.getName().equals("")){
					SET("name=#{name}");
				}
				
				if(unit.getState()!=null){
					SET("state=#{state}");
				}
				if(unit.getType()!=null){
					VALUES("type","#{type}");
				}
				
				if(unit.getEndTime()!=null){
					SET("end_time=#{endTime}");
				}
				if(unit.getEntryTime()!=null){
					SET("entry_time=#{entryTime}");
				}
				SET("version=#{version}+1");
				WHERE(" id=#{id} and version=#{version}");
			}
		}.toString();
	}
	
	public String returnSql(String sql){
		return sql;
	}

}
