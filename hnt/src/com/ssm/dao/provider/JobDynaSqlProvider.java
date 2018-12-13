package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Job;
import com.ssm.utils.Constants;

public class JobDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.JOBTABLE);
			if(params.get("job")!=null){
				Job job = (Job) params.get("job");
				if(job.getName()!=null && !job.getName().equals("")){
					WHERE(" name LIKE CONCAT ('%',#{job.name},'%')");
				}
			
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" limit #{pageModel.startIndex},#{pageModel.pageSize} ";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.JOBTABLE);
				if(params.get("job")!=null){
					Job job = (Job) params.get("job");
					if(job.getName()!=null && !job.getName().equals("")){
						WHERE(" name LIKE CONCAT ('%',#{job.name},'%'");
					}
					
				}
				
			}
		}.toString();
	}
	
	public String insertJob(final Job job){
		return new SQL(){
			{
				INSERT_INTO(Constants.JOBTABLE);
				if(job.getName()!=null&& !job.getName().equals("")){
					VALUES("name","#{name}");
				}
				if(job.getRemark()!=null&& !job.getRemark().equals("")){
					VALUES("remark","#{remark}");
				}
			}
		}.toString();
	}
	
	public String updateJob(final Job job){
		return new SQL(){
			{
				UPDATE(Constants.JOBTABLE);
				if(job.getName()!=null&& !job.getName().equals("")){
					SET("name=#{name}");
				}
				if(job.getRemark()!=null&& !job.getRemark().equals("")){
					SET("remark=#{remark}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
