package com.ssm.provider;


import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.MemberInfo;
import com.utils.Constants;

public class MemberInfoDynaSqlProvider {
	//分页动态查询
	
	
	public String insert(final MemberInfo member){
		return new SQL(){
			{
				INSERT_INTO(Constants.MEMBERINFOTABLE);
				if(member.getRealName()!=null&& !member.getRealName().equals("")){
					VALUES("real_name","#{realName}");
				}
				if(member.getMemberId()!=null){
					VALUES("member_id","#{memberId}");
				}
				if(member.getBirth()!=null){
					VALUES("birth","#{birth}");
				}
				if(member.getSex()!=null&& !member.getSex().equals("")){
					VALUES("sex","#{sex}");
				}
				if(member.getEducation()!=null&& !member.getEducation().equals("")){
					VALUES("education","#{education}");
				}
				if(member.getMaritalStatus()!=null&& !member.getMaritalStatus().equals("")){
					VALUES("marital_status","#{maritalStatus}");
				}
				if(member.getMobile()!=null&& !member.getMobile().equals("")){
					VALUES("mobile","#{mobile}");
				}
				if(member.getVersion()!=null){
					VALUES("version","#{version}");
				}
				if(member.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
				if(member.getEndTime()!=null){
					VALUES("end_time","#{endTime}");
				}
			}
		}.toString();
	}
	
	public String update(final MemberInfo member){
		return new SQL(){
			{
				UPDATE(Constants.MEMBERINFOTABLE);
				if(member.getRealName()!=null&& !member.getRealName().equals("")){
					SET("real_name=#{realName}");
				}
		
				if(member.getSex()!=null&& !member.getSex().equals("")){
					SET("sex=#{sex}");
				}
				if(member.getEndTime()!=null){
					SET("end_time = #{endTime}");
				}
				if(member.getBirth()!=null){
					SET("birth=#{birth}");
				}
				
				if(member.getEducation()!=null&& !member.getEducation().equals("")){
					SET("education=#{education}");
				}
				if(member.getMaritalStatus()!=null&& !member.getMaritalStatus().equals("")){
					SET("marital_status=#{maritalStatus}");
				}
				if(member.getMobile()!=null&& !member.getMobile().equals("")){
					SET("mobile=#{mobile}");
				}
				SET("version = #{version}+1");
				WHERE(" id=#{id} and version=#{version}");
			}
		}.toString();
	}
	
	public String updateForMid(final MemberInfo member){
		return new SQL(){
			{
				UPDATE(Constants.MEMBERINFOTABLE);
				if(member.getRealName()!=null&& !member.getRealName().equals("")){
					SET("real_name=#{realName}");
				}
				if(member.getSex()!=null&& !member.getSex().equals("")){
					SET("sex=#{sex}");
				}
				if(member.getEndTime()!=null){
					SET("end_time = #{endTime}");
				}
				if(member.getBirth()!=null){
					SET("birth=#{birth}");
				}
				
				if(member.getEducation()!=null&& !member.getEducation().equals("")){
					SET("education=#{education}");
				}
				if(member.getMaritalStatus()!=null&& !member.getMaritalStatus().equals("")){
					SET("marital_status=#{maritalStatus}");
				}
				if(member.getMobile()!=null&& !member.getMobile().equals("")){
					SET("mobile=#{mobile}");
				}
				SET("version = #{version}+1");
				WHERE(" member_id=#{memberId} and version=#{version}");
			}
		}.toString();
	}
	
	public String returnSql(String sql){
		return sql;
	}
	

}
