package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Admin;
import com.ssm.utils.Constants;

public class AdminDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.ADMINTABLE);
			if(params.get("admin")!=null){
				Admin admin = (Admin) params.get("admin");
				if(admin.getAdminName()!=null && !admin.getAdminName().equals("")){
					WHERE(" admin_name LIKE CONCAT ('%',#{admin.adminName},'%')");
				}
				if(admin.getRealName()!=null && !admin.getRealName().equals("")){
					WHERE(" real_name LIKE CONCAT ('%',#{admin.realName},'%')");
				}
				if(admin.getState()!=null && !admin.getState().equals("")){
					WHERE(" state LIKE CONCAT ('%',#{admin.state},'%')");
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
				FROM(Constants.ADMINTABLE);
				if(params.get("admin")!=null){
					Admin admin = (Admin) params.get("admin");
					if(admin.getAdminName()!=null && !admin.getAdminName().equals("")){
						WHERE(" admin_name LIKE CONCAT ('%',#{admin.adminName},'%')");
					}

					if(admin.getRealName()!=null && !admin.getRealName().equals("")){
						WHERE(" real_name LIKE CONCAT ('%',#{admin.realName},'%')");
					}
					if(admin.getState()!=null && !admin.getState().equals("")){
						WHERE(" state LIKE CONCAT ('%',#{admin.state},'%')");
					}
				}
				
			}
		}.toString();
	}
	
	public String insertAdmin(final Admin admin){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.ADMINTABLE);
				if(admin.getAdminName()!=null&& !admin.getAdminName().equals("")){
					VALUES("admin_name","#{adminName}");
				}
				if(admin.getRealName()!=null&& !admin.getRealName().equals("")){
					VALUES("real_name","#{realName}");
				}
				if(admin.getState()!=null&& !admin.getState().equals("")){
					VALUES("state","#{state}");
				}
				if(admin.getRank()!=null&& !admin.getRank().equals("")){
					VALUES("rank","#{rank}");
				}
				if(admin.getTel()!=null&& !admin.getTel().equals("")){
					VALUES("tel","#{tel}");
				}
				if(admin.getDeptId()!=null){
					VALUES("dept_id","#{deptId}");
				}
				if(admin.getDeptName()!=null&& !admin.getDeptName().equals("")){
					VALUES("dept_name","#{deptName}");
				}
				if(admin.getJobId()!=null){
					VALUES("job_id","#{jobId}");
				}
				if(admin.getJobName()!=null&&!admin.getJobName().equals("")){
					VALUES("job_name","#{jobName}");
				}
				if(admin.getPassword()!=null&& !admin.getPassword().equals("")){
					VALUES("password","#{password}");
				}
				if(admin.getPassword2()!=null&& !admin.getPassword2().equals("")){
					VALUES("password2","#{password2}");
				}
				if(admin.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
				if(admin.getEndTime()!=null){
					VALUES("end_time","#{endTime}");
				}
			}
		}.toString();
	}
	
	public String updateAdmin(final Admin admin){
		return new SQL(){
			{
				UPDATE(Constants.ADMINTABLE);
				if(admin.getAdminName()!=null&& !admin.getAdminName().equals("")){
					SET("admin_name=#{adminName}");
				}
				if(admin.getRealName()!=null&& !admin.getRealName().equals("")){
					SET("real_name=#{realName}");
				}
				if(admin.getTel()!=null&& !admin.getTel().equals("")){
					SET("tel=#{tel}");
				}
				if(admin.getDeptId()!=null){
					SET("dept_id=#{deptId}");
				}
				if(admin.getDeptName()!=null&& !admin.getDeptName().equals("")){
					SET("dept_name=#{deptName}");
				}
				if(admin.getJobId()!=null){
					SET("job_id=#{jobId}");
				}
				if(admin.getJobName()!=null&& !admin.getJobName().equals("")){
					SET("job_name=#{jobName}");
				}
				if(admin.getState()!=null&& !admin.getState().equals("")){
					SET("state=#{state}");
				}
				if(admin.getRank()!=null&&!admin.getRank().equals("")){
					SET("rank=#{rank}");
				}
				if(admin.getPassword()!=null&& !admin.getPassword().equals("")){
					SET("password=#{password}");
				}
				if(admin.getEntryTime()!=null){
					SET("entry_time = #{entryTime}");
				}
				if(admin.getEndTime()!=null){
					SET("end_time = #{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
