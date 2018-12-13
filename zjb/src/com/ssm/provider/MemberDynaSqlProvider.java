package com.ssm.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Member;
import com.utils.Constants;

public class MemberDynaSqlProvider {
	//分页动态查询
	String re_sql = " mb.id as id,mb.nick_name as nickName,mb.password as password,mb.password2 as password2"
				+ ",mb.rank as rank,mb.emoney as emoney,mb.integral as integral,mb.version as version,mb.view_num as viewNum"
				+ ",mb.state as state,mb.entry_time as entryTime,mb.end_time as endTime,inf.id as infoId,inf.real_name as realName"
				+ ",inf.birth as birth,inf.mobile as mobile,inf.sex as sex,inf.education as education,inf.marital_status as maritalStatus ";
	String sqls = "select "+re_sql+" from "+Constants.MEMBERTABLE+" mb LEFT JOIN "+Constants.MEMBERINFOTABLE+" inf ON mb.id=inf.member_id ";

	String re_sql_1 = " mb.id as id,mb.nick_name as nickName,mb.password as password,mb.password2 as password2"
			+ ",mb.rank as rank,mb.emoney as emoney,mb.integral as integral,inf.version as version,mb.view_num as viewNum"
			+ ",mb.state as state,inf.entry_time as entryTime,inf.end_time as endTime,inf.id as infoId,inf.real_name as realName"
			+ ",inf.birth as birth,inf.mobile as mobile,inf.sex as sex,inf.education as education,inf.marital_status as maritalStatus ";
String sqls1 = "select "+re_sql_1+" from "+Constants.MEMBERTABLE+" mb LEFT JOIN "+Constants.MEMBERINFOTABLE+" inf ON mb.id=inf.member_id ";

	public String selectWhitParam(final Map<String,Object> params){
		String str = "";
			if(params.get("member")!=null){
				Member member = (Member) params.get("member");
				if(member.getNickName()!=null && !member.getNickName().equals("")){
					if(str.equals(""))
						str = " where mb.nick_name like '%"+member.getNickName()+"%' ";
					else str =str+" and mb.nick_name like '%"+member.getNickName()+"%' ";
				}
				if(member.getRealName()!=null && !member.getRealName().equals("")){
					if(str.equals(""))
						str = " where inf.real_name like '%"+member.getRealName()+"%' ";
					else str =str+" and inf.real_name like '%"+member.getRealName()+"%' ";
				}
				if(member.getRank()!=null){
					if(str.equals(""))
						str = " where rank like '%"+member.getRank()+"%' ";
					else str =str+" and rank like '%"+member.getRank()+"%' ";	
				}
				if(member.getMobile()!=null && !member.getMobile().equals("")){
					if(str.equals(""))
						str = " where inf.mobile like '%"+member.getMobile()+"%' ";
					else str =str+" and inf.mobile like '%"+member.getMobile()+"%' ";			
				}
				if(member.getStartTime()!=null){
					if(str.equals(""))
						str = " where mb.entry_time >='"+member.getStartTime()+"'";
					else str =str+" and mb.entry_time >='"+member.getStartTime()+"'";			
				}
			
				if(member.getEndTime()!=null){
					if(str.equals(""))
						str = " where mb.entry_time <='"+member.getEndTime()+"'";
					else str =str+" and mb.entry_time <='"+member.getEndTime()+"'";			
				}
					
			}
		if(params.get("orderby")!=null){
			String orderby = (String) params.get("orderby");
			str  +=" order by "+orderby;
		}
		if(params.get("pageModel")!=null){
			str +=" limit #{pageModel.startIndex},#{pageModel.pageSize} ";
		}
		return sqls+str;
	}
	
	public String count( final Map<String,Object> params){
		String sql = "select ifnull(count(*),0) from "+Constants.MEMBERTABLE+" mb LEFT JOIN "+Constants.MEMBERINFOTABLE+" inf ON mb.id=inf.member_id ";
		String str = "";
			if(params.get("member")!=null){
				Member member = (Member) params.get("member");
				if(member.getNickName()!=null && !member.getNickName().equals("")){
					if(str.equals(""))
						str = " where mb.nick_name like '%"+member.getNickName()+"%' ";
					else str =str+" and mb.nick_name like '%"+member.getNickName()+"%' ";
				}
				if(member.getRealName()!=null && !member.getRealName().equals("")){
					if(str.equals(""))
						str = " where inf.real_name like '%"+member.getRealName()+"%' ";
					else str =str+" and inf.real_name like '%"+member.getRealName()+"%' ";
				}
				if(member.getRank()!=null){
					if(str.equals(""))
						str = " where rank like '%"+member.getRank()+"%' ";
					else str =str+" and rank like '%"+member.getRank()+"%' ";	
				}
				if(member.getMobile()!=null && !member.getMobile().equals("")){
					if(str.equals(""))
						str = " where inf.mobile like '%"+member.getMobile()+"%' ";
					else str =str+" and inf.mobile like '%"+member.getMobile()+"%' ";			
				}
				if(member.getStartTime()!=null){
					if(str.equals(""))
						str = " where mb.entry_time >='"+member.getStartTime()+"'";
					else str =str+" and mb.entry_time >='"+member.getStartTime()+"'";			
				}
			
				if(member.getEndTime()!=null){
					if(str.equals(""))
						str = " where mb.entry_time <='"+member.getEndTime()+"'";
					else str =str+" and mb.entry_time <='"+member.getEndTime()+"'";			
				}
					
			}
		
		return sql+str;
	}
	
	public String insert(final Member member){
		return new SQL(){
			{
				INSERT_INTO(Constants.MEMBERTABLE);
				if(member.getNickName()!=null&& !member.getNickName().equals("")){
					VALUES("nick_name","#{nickName}");
				}
				if(member.getState()!=null){
					VALUES("state","#{state}");
				}
				if(member.getRank()!=null){
					VALUES("rank","#{rank}");
				}
				if(member.getViewNum()!=null){
					VALUES("view_num","#{viewNum}");
				}
				if(member.getVersion()!=null){
					VALUES("version","#{version}");
				}
				if(member.getPassword()!=null&& !member.getPassword().equals("")){
					VALUES("password","#{password}");
				}
				if(member.getPassword2()!=null&& !member.getPassword2().equals("")){
					VALUES("password2","#{password2}");
				}
				if(member.getEmoney()!=null){
					VALUES("emoney","#{emoney}");
				}
				if(member.getIntegral()!=null){
					VALUES("integral","#{integral}");
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
	
	public String update(final Member member){
		return new SQL(){
			{
				UPDATE(Constants.MEMBERTABLE);
				if(member.getNickName()!=null&& !member.getNickName().equals("")){
					SET("nick_name=#{nickName}");
				}
				if(member.getRank()!=null){
					SET("rank=#{rank}");
				}
				if(member.getState()!=null){
					SET("state=#{state}");
				}
				if(member.getPassword()!=null&& !member.getPassword().equals("")){
					SET("password=#{password}");
				}
				if(member.getPassword2()!=null&& !member.getPassword2().equals("")){
					SET("password2=#{password2}");
				}
				if(member.getEntryTime()!=null){
					SET("entry_time = #{entryTime}");
				}
				if(member.getEndTime()!=null){
					SET("end_time = #{endTime}");
				}
				SET("version = #{version}+1");
				WHERE(" id=#{id} and version=#{version}");
			}
		}.toString();
	}
	
	public String findAllById(Long id){
		String sql = sqls +" where mb.id='"+id+"'";
		return sql;
	}
	
	public String findAllByIdForInfo(Long id){
		String sql = sqls1 +" where mb.id='"+id+"'";
		return sql;
	}
	
	
	public String returnSql(String sql){
		return sql;
	}
	

}
