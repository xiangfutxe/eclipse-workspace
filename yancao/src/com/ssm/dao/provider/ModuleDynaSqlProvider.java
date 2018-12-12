package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Module;
import com.ssm.pojo.News;
import com.ssm.utils.Constants;

public class ModuleDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.MODULETABLE);
			if(params.get("module")!=null){
				Module module = (Module) params.get("module");
				if(module.getTitle()!=null && !module.getTitle().equals("")){
					WHERE(" title LIKE CONCAT ('%',#{module.title},'%')");
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
				FROM(Constants.MODULETABLE);
				if(params.get("module")!=null){
					Module module = (Module) params.get("module");
					if(module.getTitle()!=null && !module.getTitle().equals("")){
						WHERE(" title LIKE CONCAT ('%',#{module.title},'%')");
					}
				}
				
			}
		}.toString();
	}
	
	public String insert(final Module module){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.MODULETABLE);
				if(module.getTitle()!=null&& !module.getTitle().equals("")){
					VALUES("title","#{title}");
				}
				
				if(module.getContents()!=null&& !module.getContents().equals("")){
					VALUES("contents","#{contents}");
				}
				if(module.getCode()!=null){
					VALUES("code","#{code}");
				}
				if(module.getEndTime()!=null){
					VALUES("end_time","#{endTime}");
				}
				if(module.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
			}
		}.toString();
	}
	
	public String update(final Module module){
		return new SQL(){
			{
				UPDATE(Constants.MODULETABLE);
				if(module.getCode()!=null){
					SET("code=#{code}");
				}
				if(module.getTitle()!=null&& !module.getTitle().equals("")){
					SET("title=#{title}");
				}
				if(module.getContents()!=null&& !module.getContents().equals("")){
					SET("contents=#{contents}");
				}
				if(module.getEndTime()!=null){
					SET("end_time=#{endTime}");
				}
				if(module.getEntryTime()!=null){
					SET("entry_time=#{entryTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
