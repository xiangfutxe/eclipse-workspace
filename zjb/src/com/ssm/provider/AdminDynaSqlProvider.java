package com.ssm.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Admin;
import com.utils.Constants;

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
				if(admin.getMobile()!=null && !admin.getMobile().equals("")){
					WHERE(" mobile LIKE CONCAT ('%',#{admin.mobile},'%')");
				}
				if(admin.getState()!=null && !admin.getState().equals("")){
					WHERE(" state LIKE CONCAT ('%',#{admin.state},'%')");
				}
				if(admin.getType()!=null && !admin.getType().equals("")){
					WHERE(" type LIKE CONCAT ('%',#{admin.type},'%')");
				}
					
			}
			}
		}.toString();
		if(params.get("orderby")!=null){
			String orderby = (String) params.get("orderby");
			sql  +=" order by "+orderby;
		}
		if(params.get("pageModel")!=null){
			sql +=" limit #{pageModel.startIndex},#{pageModel.pageSize} ";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		String sql = 
		 new SQL(){
			{
				SELECT("ifnull(count(*),0)");
				FROM(Constants.ADMINTABLE);
				if(params.get("admin")!=null){
					Admin admin = (Admin) params.get("admin");
					if(admin.getAdminName()!=null && !admin.getAdminName().equals("")){
						WHERE(" admin_name LIKE CONCAT ('%',#{admin.adminName},'%')");
					}
					if(admin.getRealName()!=null && !admin.getRealName().equals("")){
						WHERE(" real_name LIKE CONCAT ('%',#{admin.realName},'%')");
					}
					if(admin.getMobile()!=null && !admin.getMobile().equals("")){
						WHERE(" mobile LIKE CONCAT ('%',#{admin.mobile},'%')");
					}
					if(admin.getState()!=null && !admin.getState().equals("")){
						WHERE(" state LIKE CONCAT ('%',#{admin.state},'%')");
					}
					if(admin.getType()!=null && !admin.getType().equals("")){
						WHERE(" type LIKE CONCAT ('%',#{admin.type},'%')");
					}
					if(admin.getStartTime()!=null && !admin.getStartTime().equals("")){
						WHERE(" entry_time >=#{admin.startTime}");
					}
					if(admin.getEndTime()!=null && !admin.getEndTime().equals("")){
						WHERE(" entry_time <=#{admin.endTime}");
					}
						
				}
				}
		}.toString();
		if(params.get("orderby")!=null){
			String orderby = (String) params.get("orderby");
			sql  = sql+" order by "+orderby;
		}
		return sql;
	}
	
	public String insert(final Admin admin){
		return new SQL(){
			{
				INSERT_INTO(Constants.ADMINTABLE);
				if(admin.getAdminName()!=null&& !admin.getAdminName().equals("")){
					VALUES("admin_name","#{adminName}");
				}
				if(admin.getRealName()!=null&& !admin.getRealName().equals("")){
					VALUES("real_name","#{realName}");
				}
				if(admin.getState()!=null){
					VALUES("state","#{state}");
				}
				if(admin.getType()!=null){
					VALUES("type","#{type}");
				}
				if(admin.getViewNum()!=null){
					VALUES("view_num","#{view_num}");
				}
				if(admin.getRank()!=null&& !admin.getRank().equals("")){
					VALUES("rank","#{rank}");
				}
				if(admin.getMobile()!=null&& !admin.getMobile().equals("")){
					VALUES("mobile","#{mobile}");
				}
				if(admin.getVersion()!=null&& !admin.getVersion().equals("")){
					VALUES("version","#{version}");
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
	
	public String update(final Admin admin){
		return new SQL(){
			{
				UPDATE(Constants.ADMINTABLE);
				if(admin.getAdminName()!=null&& !admin.getAdminName().equals("")){
					SET("admin_name=#{adminName}");
				}
				if(admin.getRealName()!=null&& !admin.getRealName().equals("")){
					SET("real_name=#{realName}");
				}
				if(admin.getMobile()!=null&& !admin.getMobile().equals("")){
					SET("mobile=#{mobile}");
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
				if(admin.getType()!=null&& !admin.getType().equals("")){
					SET("type=#{type}");
				}
				if(admin.getViewNum()!=null&& !admin.getViewNum().equals("")){
					SET("view_num=#{viewNum}");
				}
				if(admin.getRank()!=null&&!admin.getRank().equals("")){
					SET("rank=#{rank}");
				}
				if(admin.getPassword()!=null&& !admin.getPassword().equals("")){
					SET("password=#{password}");
				}
				if(admin.getPassword2()!=null&& !admin.getPassword2().equals("")){
					SET("password2=#{password2}");
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
	
	public String returnSql(String sql){
		return sql;
	}
	
	
	@SuppressWarnings("unchecked")
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder(); 
        String tableName=(String) params.get("tableName");
        sb.append("INSERT INTO "+tableName);  
        sb.append("(id,admin_name,real_name,password,password2,mobile,dept_id,dept_name,job_id,job_name,type,state,view_num,version,entry_time,end_time)");  
        sb.append(" VALUES "); 
        if(params.get("list")!=null){
        List<Admin> list = (List<Admin>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null,#'{'list[{0}].adminName},#'{'list[{0}].realName},#'{'list[{0}].password},#'{'list[{0}].password2},#'{'list[{0}].mobile},#'{'list[{0}].deptId}"
        		+ ",#'{'list[{0}].deptName},#'{'list[{0}].jobId},#'{'list[{0}].jobName}"
        		+ ",#'{'list[{0}].type},#'{'list[{0}].state},#'{'list[{0}].view_num},#'{'list[{0}].version}"
        		+ ",#'{'list[{0}].entryTime},#'{'list[{0}].endTime})");  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i})); 
          if(i<list.size()-1){
            	 sb.append(",");  
            }
        }  
		}
        return sb.toString();  
    }  
	

}
