package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.User;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;

public class UserDynaSqlProvider {
	
	String head = "select us.id id,us.user_id userId,us.user_name userName,uf.nick_name nickName,us.rank_join rankJoin,us.rank_join_old rankJoinOld,"
			+ "us.rmoney rmoney,us.cash cash,us.cash_num cashNum,us.pre_rmoney preRmoney,us.pre_cash preCash,us.integral integral,us.pre_cash_num preCashNum,us.pre_integral preIntegral,"
			+ "us.referee_id refereeId,us.referee_user_id refereeUserId,us.referee_node refereeNode,us.state state,us.entry_time entryTime,"
			+ "us.referee_num refereeNum,us.referee_num_1 refereeNum_1,us.referee_num_2 refereeNum2,us.referee_num_3 refereeNum3,us.referee_num_4 refereeNum4,"
			+ "us.referee_num_5 refereeNum5,us.referee_num_6 refereeNum6,us.referee_num_7 refereeNum7,us.referee_virtual_num refereeVirtualNum,us.agent_tag agentTag,"
			+ "uf.password password,uf.password2 password2,uf.open_id openId,uf.head_img_url headImgUrl,"
			+ "uf.document_type documentType,uf.num_id numId,uf.sex sex,uf.mobile mobile,us.province province,"
			+ "us.city city,us.area area,uf.address address,uf.account_id accountId,uf.account_name accountName,"
			+ "uf.bank_adr bankAdr,uf.bank_name bankName from users us left join user_info uf on us.user_id=uf.user_id";
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.USERSTABLE);
			if(params.get("user")!=null){
				User user = (User) params.get("user");
				if(user.getUserId()!=null && !user.getUserId().equals("")){
					WHERE("  user_id LIKE CONCAT ('%',#{user.userId},'%')");
				}
				
				if(user.getRefereeUserId()!=null && !user.getRefereeUserId().equals("")){
					WHERE("  referee_user_id LIKE CONCAT ('%',#{user.refereeUserId},'%')");
				}
				if(user.getRankJoin()!=null && user.getRankJoin()!=0){
					WHERE("  rank_join LIKE CONCAT ('%',#{user.rankJoin},'%')");
				}
				if(user.getState()!=null && user.getState()!=0){
					WHERE("  state LIKE CONCAT ('%',#{user.state},'%')");
				}
				if(user.getStartTime()!=null){
					WHERE("  entry_time >= #{user.startTime}");
				}
				if(user.getEndTime()!=null){
					WHERE("  entry_time <= #{user.endTime}");
				}
				WHERE("  state>0 ");
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by entry_time desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM("users");
				if(params.get("user")!=null){
					User user = (User) params.get("user");
					if(user.getUserId()!=null && !user.getUserId().equals("")){
						WHERE("  user_id LIKE CONCAT ('%',#{user.userId},'%')");
					}
					
					if(user.getRefereeUserId()!=null && !user.getRefereeUserId().equals("")){
						WHERE("  referee_user_id LIKE CONCAT ('%',#{user.refereeUserId},'%')");
					}
					if(user.getRankJoin()!=null && user.getRankJoin()!=0){
						WHERE("  rank_join LIKE CONCAT ('%',#{user.rankJoin},'%')");
					}
					if(user.getState()!=null && user.getState()!=0){
						WHERE("  state LIKE CONCAT ('%',#{user.state},'%')");
					}
					if(user.getStartTime()!=null){
						WHERE("  entry_time >= #{user.startTime}");
					}
					if(user.getEndTime()!=null){
						WHERE("  entry_time <= #{user.endTime}");
					}
					WHERE("  state>0 ");
				}
				
			}
		}.toString();
	}
	
	
	public String selectAllListWhitParam(final Map<String,Object> params){
		String str ="";
		if(params.get("user")!=null){
			User user = (User) params.get("user");
			if(user.getUserId()!=null && !user.getUserId().equals("")){
				str = str+" and  us.user_id like '%"+user.getUserId()+"%'";
			}
			if(user.getUserName()!=null && !user.getUserName().equals("")){
				str = str+" and  us.user_name like '%"+user.getUserName()+"%'";
			}
			if(user.getNickName()!=null && !user.getNickName().equals("")){
				str = str+" and  uf.nick_name like '%"+user.getNickName()+"%'";
			}
			if(user.getMobile()!=null && !user.getMobile().equals("")){
				str = str+" and  uf.mobile like '%"+user.getMobile()+"%'";
			}
			if(user.getDocumentType()!=null && !user.getDocumentType().equals("")){
				str = str+" and  uf.document_type like '%"+user.getDocumentType()+"%'";
			}
			if(user.getNumId()!=null && !user.getNumId().equals("")){
				str = str+" and  uf.num_id like '%"+user.getNumId()+"%'";
			}
			if(user.getRefereeUserId()!=null && !user.getRefereeUserId().equals("")){
				str = str+" and  us.referee_user_id like '%"+user.getRefereeUserId()+"%'";
			}
			if(user.getRankJoin()!=null && user.getRankJoin()!=0){
				str = str+" and  us.rank_join like '%"+user.getRankJoin()+"%'";
			}
			if(user.getState()!=null && user.getState()!=0){
				str = str+" and  us.state like '%"+user.getState()+"%'";
			}
			if(user.getStartTime()!=null){
				str = str+" and  us.entry_time >= '"+user.getStartTime()+"'";
			}
			if(user.getEndTime()!=null){
				str = str+" and  us.entry_time <='"+user.getEndTime()+"'";
			}
		}
		String sql =head+" where us.state>0 "+str;
		sql +=" order by entry_time desc";
		return sql;
	}
	
	
	public String selectAllWhitParam(final Map<String,Object> params){
		String str ="";
		if(params.get("user")!=null){
			User user = (User) params.get("user");
			if(user.getUserId()!=null && !user.getUserId().equals("")){
				str = str+" and  us.user_id like '%"+user.getUserId()+"%'";
			}
			if(user.getUserName()!=null && !user.getUserName().equals("")){
				str = str+" and  us.user_name like '%"+user.getUserName()+"%'";
			}
			if(user.getNickName()!=null && !user.getNickName().equals("")){
				str = str+" and  uf.nick_name like '%"+user.getNickName()+"%'";
			}
			if(user.getMobile()!=null && !user.getMobile().equals("")){
				str = str+" and  uf.mobile like '%"+user.getMobile()+"%'";
			}
			if(user.getDocumentType()!=null && !user.getDocumentType().equals("")){
				str = str+" and  uf.document_type like '%"+user.getDocumentType()+"%'";
			}
			if(user.getNumId()!=null && !user.getNumId().equals("")){
				str = str+" and  uf.num_id like '%"+user.getNumId()+"%'";
			}
			if(user.getRefereeUserId()!=null && !user.getRefereeUserId().equals("")){
				str = str+" and  us.referee_user_id like '%"+user.getRefereeUserId()+"%'";
			}
			if(user.getRankJoin()!=null && user.getRankJoin()!=0){
				str = str+" and  us.rank_join like '%"+user.getRankJoin()+"%'";
			}
			if(user.getState()!=null && user.getState()!=0){
				str = str+" and  us.state like '%"+user.getState()+"%'";
			}
			if(user.getStartTime()!=null){
				str = str+" and  us.entry_time >= '"+user.getStartTime()+"'";
			}
			if(user.getEndTime()!=null){
				str = str+" and  us.entry_time <='"+user.getEndTime()+"'";
			}
		}
		String sql = head+ " where us.state>0 "+str;
		sql +=" order by entry_time desc";
		if(params.get("pageModel")!=null){
			Pager pageModel = (Pager)params.get("pageModel");
			sql +=" limit "+pageModel.getStartIndex()+","+pageModel.getPageSize();
		}
		return sql;
	}
	
	public String countAll(final Map<String,Object> params){
		String str ="";
		if(params.get("user")!=null){
			User user = (User) params.get("user");
			if(user.getUserId()!=null && !user.getUserId().equals("")){
				str = str+" and  us.user_id like '%"+user.getUserId()+"%'";
			}
			if(user.getUserName()!=null && !user.getUserName().equals("")){
				str = str+" and  us.user_name like '%"+user.getUserName()+"%'";
			}
			if(user.getNickName()!=null && !user.getNickName().equals("")){
				str = str+" and  uf.nick_name like '%"+user.getNickName()+"%'";
			}
			if(user.getMobile()!=null && !user.getMobile().equals("")){
				str = str+" and  uf.mobile like '%"+user.getMobile()+"%'";
			}
			if(user.getDocumentType()!=null && !user.getDocumentType().equals("")){
				str = str+" and  uf.document_type like '%"+user.getDocumentType()+"%'";
			}
			if(user.getNumId()!=null && !user.getNumId().equals("")){
				str = str+" and  uf.num_id like '%"+user.getNumId()+"%'";
			}
			if(user.getRefereeUserId()!=null && !user.getRefereeUserId().equals("")){
				str = str+" and  us.referee_user_id like '%"+user.getRefereeUserId()+"%'";
			}
			if(user.getRankJoin()!=null && user.getRankJoin()!=0){
				str = str+" and  us.rank_join like '%"+user.getRankJoin()+"%'";
			}
			if(user.getState()!=null && user.getState()!=0){
				str = str+" and  us.state like '%"+user.getState()+"%'";
			}
			if(user.getStartTime()!=null){
				str = str+" and  us.entry_time >= '"+user.getStartTime()+"'";
			}
			if(user.getEndTime()!=null){
				str = str+" and  us.entry_time <='"+user.getEndTime()+"'";
			}
		}
		String sql = "select count(*) from users us left join user_info uf on us.user_id=uf.user_id"
			+ " where us.state>0 "+str;
		sql +=" order by entry_time desc";
		
		return sql;
	}
	
	public String updateUserForField(String field,String value,String id){
		String str = "update users set "+field+"='"+value+"' where userId='"+id+"'";
		return str;
	}
	
	public String updateUserForField2(String field1,String value1,String field2,String value2,String id){
		String str = "update users set "+field1+"='"+value1+"',"+field2+"='"+value2+"' where userId='"+id+"'";
		return str;
	}
	
	public String selectUserByListForUpdate(final Map<String,Object> params){
		String str ="";
		if(params.get("user")!=null){
			User user = (User) params.get("user");
			if(user.getUserId()!=null && !user.getUserId().equals("")){
				str = str+" and  user_id like '%"+user.getUserId()+"%'";
			}
			if(user.getRefereeUserId()!=null && !user.getRefereeUserId().equals("")){
				str = str+" and  referee_user_id like '%"+user.getRefereeUserId()+"%'";
			}
			if(user.getRankJoin()!=null && user.getRankJoin()!=0){
				str = str+" and  rank_join like '%"+user.getRankJoin()+"%'";
			}
			if(user.getState()!=null && user.getState()!=0){
				str = str+" and  state like '%"+user.getState()+"%'";
			}
			
			if(user.getStartTime()!=null){
				str = str+" and  entry_time >= '"+user.getStartTime()+"'";
			}
			if(user.getEndTime()!=null){
				str = str+" and  entry_time <='"+user.getEndTime()+"'";
			}
		}
		String sql = "select * from users  where state>0 "+str;
		
		sql +=" order by entry_time desc for update";
		
		return sql;
	}
	
	
	
	
	public String maxId( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("max(id)");
				FROM("users");
				if(params.get("user")!=null){
					User user = (User) params.get("user");
					if(user.getUserId()!=null && !user.getUserId().equals("")){
						WHERE("  user_id LIKE CONCAT ('%',#{user.userId},'%')");
					}
					if(user.getRefereeUserId()!=null && !user.getRefereeUserId().equals("")){
						WHERE("  referee_user_id LIKE CONCAT ('%',#{user.refereeUserId},'%')");
					}
					if(user.getRankJoin()!=null && user.getRankJoin()!=0){
						WHERE("  rank_join LIKE CONCAT ('%',#{user.rankJoin},'%')");
					}
					if(user.getState()!=null && user.getState()!=0){
						WHERE("  state LIKE CONCAT ('%',#{user.state},'%')");
					}
					if(user.getStartTime()!=null){
						WHERE("  entry_time >= #{user.startTime}");
					}
					if(user.getEndTime()!=null){
						WHERE("  entry_time <= #{user.endTime}");
					}
					WHERE("  state>0 ");
				}
				
			}
		}.toString();
	}
	
	public String selectListWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.USERSTABLE);
			if(params.get("user")!=null){
				User user = (User) params.get("user");
				if(user.getUserId()!=null && !user.getUserId().equals("")){
					WHERE("  user_id LIKE CONCAT ('%',#{user.userId},'%')");
				}
				
				if(user.getRefereeUserId()!=null && !user.getRefereeUserId().equals("")){
					WHERE("  referee_user_id LIKE CONCAT ('%',#{user.refereeUserId},'%')");
				}
				if(user.getRankJoin()!=null && user.getRankJoin()!=0){
					WHERE("  rank_join LIKE CONCAT ('%',#{user.rankJoin},'%')");
				}
				if(user.getState()!=null && user.getState()!=0){
					WHERE("  state LIKE CONCAT ('%',#{user.state},'%')");
				}
				if(user.getStartTime()!=null){
					WHERE("  entry_time >= #{user.startTime}");
				}
				if(user.getEndTime()!=null){
					WHERE("  entry_time <= #{user.endTime}");
				}
				WHERE("  state>0 ");
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by entry_time desc ";
		}
		return sql;
	}
	
	public String insertUserInfo(final User user){
		String sql = new SQL(){
			{
				INSERT_INTO(Constants.USERINFOTABLE);
				if(user.getUserId()!=null&& !user.getUserId().equals("")){
					VALUES("user_id","#{userId}");
				}
				if(user.getUserName()!=null&& !user.getUserName().equals("")){
					VALUES("user_name","#{userName}");
				}
				if(user.getNickName()!=null&& !user.getNickName().equals("")){
					VALUES("nick_name","#{nickName}");
				}
				if(user.getPassword()!=null && !user.getPassword().equals("")){
					VALUES("password","#{password}");
				}
				if(user.getPassword2()!=null && !user.getPassword2().equals("")){
					VALUES("password2","#{password2}");
				}
				if(user.getSex()!=null && !user.getSex().equals("")){
					VALUES("sex","#{sex}");
				}
				if(user.getAge()!=null){
					VALUES("age","#{age}");
				}
				if(user.getDocumentType()!=null && !user.getDocumentType().equals("")){
					VALUES("document_type","#{documentType}");
				}
				if(user.getNumId()!=null && !user.getNumId().equals("")){
					VALUES("num_id","#{numId}");
				}
				if(user.getMobile()!=null && !user.getMobile().equals("")){
					VALUES("moblie","#{moblie}");
				}
				if(user.getOpenId()!=null&& !user.getOpenId().equals("")){
					VALUES("open_id","#{openId}");
				}
				if(user.getProvince()!=null&& !user.getProvince().equals("")){
					VALUES("province","#{province}");
				}
				if(user.getCity()!=null&& !user.getCity().equals("")){
					VALUES("city","#{city}");
				}
				if(user.getArea()!=null && !user.getArea().equals("")){
					VALUES("area","#{area}");
				}
				if(user.getAddress()!=null && !user.getAddress().equals("")){
					VALUES("address","#{address}");
				}
				if(user.getBankName()!=null && !user.getBankName().equals("")){
					VALUES("bank_name","#{bankName}");
				}
				if(user.getAccountId()!=null && !user.getAccountId().equals("")){
					VALUES("account_id","#{accountId}");
				}
				if(user.getAccountName()!=null && !user.getAccountName().equals("")){
					VALUES("account_name","#{accountName}");
				}
				
				if(user.getBankAdr()!=null&& !user.getBankAdr().equals("")){
					VALUES("bank_adr","#{bankAdr}");
				}
				if(user.getHeadImgUrl()!=null&& !user.getHeadImgUrl().equals("")){
					VALUES("head_img_url","#{headImgUrl}");
				}
				if(user.getState()!=null){
					VALUES("state","#{state}");
				}
			}
		}.toString();
		return sql;
	}
	
	public String insertUser(final User user){
		return new SQL(){
			{
				INSERT_INTO(Constants.USERSTABLE);
				if(user.getUserId()!=null&& !user.getUserId().equals("")){
					VALUES("user_id","#{userId}");
				}
				if(user.getUserName()!=null&& !user.getUserId().equals("")){
					VALUES("user_name","#{userName}");
				}
				if(user.getRefereeId()!=null){
					VALUES("referee_id","#{refereeId}");
				}
				if(user.getRefereeUserId()!=null && !user.getRefereeUserId().equals("")){
					VALUES("referee_user_id","#{refereeUserId}");
				}
				if(user.getRefereeAll()!=null && !user.getRefereeAll().equals("")){
					VALUES("referee_all","#{refereeAll}");
				}
				if(user.getRefereeNode()!=null && !user.getRefereeNode().equals("")){
					VALUES("referee_node","#{refereeNode}");
				}
				
				if(user.getRmoney()!=null){
					VALUES("rmoney","#{rmoney}");
				}
				if(user.getCash()!=null){
					VALUES("cash","#{cash}");
				}
				if(user.getCashNum()!=null){
					VALUES("cash_num","#{cashNum}");
				}
				if(user.getIntegral()!=null){
					VALUES("integral","#{integral}");
				}
				if(user.getPreRmoney()!=null){
					VALUES("pre_rmoney","#{preRmoney}");
				}
				if(user.getPreCash()!=null){
					VALUES("pre_cash","#{preCash}");
				}
				if(user.getPreCashNum()!=null){
					VALUES("pre_cash_num","#{preCashNum}");
				}
				if(user.getPreIntegral()!=null){
					VALUES("pre_integral","#{preIntegral}");
				}
				if(user.getRankJoin()!=null){
					VALUES("rank_join","#{rankJoin}");
				}
				if(user.getRankJoinOld()!=null){
					VALUES("rank_join_old","#{rankJoinOld}");
				}
				if(user.getPayTag()!=null){
					VALUES("pay_tag","#{payTag}");
				}
				if(user.getState()!=null){
					VALUES("state","#{state}");
				}
				if(user.getVersion()!=null){
					VALUES("version","#{version}");
				}
				if(user.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
				if(user.getRefereeNum()!=null){
					VALUES("referee_num","#{refereeNum}");
				}
				if(user.getRefereeNum1()!=null){
					VALUES("referee_num_1","#{refereeNum1}");
				}
				if(user.getRefereeNum2()!=null){
					VALUES("referee_num_2","#{refereeNum2}");
				}
				if(user.getRefereeNum3()!=null){
					VALUES("referee_num_3","#{refereeNum3}");
				}
				if(user.getRefereeNum4()!=null){
					VALUES("referee_num_4","#{refereeNum4}");
				}
				if(user.getRefereeNum5()!=null){
					VALUES("referee_num_5","#{refereeNum5}");
				}
				if(user.getRefereeNum6()!=null){
					VALUES("referee_num_6","#{refereeNum6}");
				}
				if(user.getRefereeNum7()!=null){
					VALUES("referee_num_7","#{refereeNum7}");
				}
				if(user.getRefereeVirtualNum()!=null){
					VALUES("referee_virtual_num","#{refereeVirtualNum}");
				}
				if(user.getAgentTag()!=null){
					VALUES("agent_tag","#{agentTag}");
				}
			}
		}.toString();
	}
	
	public String updateUser(final User user){
		String sql = new SQL(){
			{
				UPDATE(Constants.USERSTABLE);
				if(user.getUserName()!=null&& !user.getUserName().equals("")){
					SET(" user_name=#{userName}");
				}
				if(user.getRefereeAll()!=null){
					SET(" referee_all=#{refereeAll}");
				}
				if(user.getRefereeNode()!=null){
					SET(" referee_node=#{refereeNode}");
				}
				if(user.getRmoney()!=null){
					SET("rmoney=#{rmoney}");
				}
				if(user.getCash()!=null){
					SET("cash=#{cash}");
				}
				if(user.getCashNum()!=null){
					SET("cash_num=#{cashNum}");
				}
				if(user.getIntegral()!=null){
					SET("integral=#{integral}");
				}
				if(user.getPreRmoney()!=null){
					SET("pre_rmoney=#{preRmoney}");
				}
				if(user.getPreCash()!=null){
					SET("pre_cash=#{preCash}");
				}
				if(user.getPreCashNum()!=null){
					SET("pre_cash_num=#{preCashNum}");
				}
				if(user.getPreIntegral()!=null){
					SET("pre_integral=#{preIntegral}");
				}
				if(user.getRankJoin()!=null){
					SET(" rank_join=#{rankJoin}");
				}
				if(user.getRankJoinOld()!=null){
					SET(" rank_join_old=#{rankJoinOld}");
				}
				if(user.getTotalIncome()!=null){
					SET("total_income=#{totalIncome}");
				}
				if(user.getPayTag()!=null){
					SET(" pay_tag=#{payTag}");
				}
				if(user.getState()!=null){
					SET(" state=#{state}");
				}
				if(user.getRmoney()!=null){
					SET(" rmoney=#{rmoney}");
				}
				if(user.getEndTime()!=null){
					SET(" end_time=#{entryTime}");
				}
				if(user.getRefereeNum()!=null){
					SET("referee_num=#{refereeNum}");
				}
				if(user.getRefereeNum1()!=null){
					SET("referee_num_1=#{refereeNum1}");
				}
				if(user.getRefereeNum2()!=null){
					SET("referee_num_2=#{refereeNum2}");
				}
				if(user.getRefereeNum3()!=null){
					SET("referee_num_3=#{refereeNum3}");
				}
				if(user.getRefereeNum4()!=null){
					SET("referee_num_4=#{refereeNum4}");
				}
				if(user.getRefereeNum5()!=null){
					SET("referee_num_5=#{refereeNum5}");
				}
				if(user.getRefereeNum6()!=null){
					SET("referee_num_6=#{refereeNum6}");
				}
				if(user.getRefereeNum7()!=null){
					SET("referee_num_7=#{refereeNum7}");
				}
				if(user.getRefereeVirtualNum()!=null){
					SET("referee_virtual_num=#{refereeVirtualNum}");
				}
				if(user.getAgentTag()!=null){
					SET("agent_tag=#{agentTag}");
				}
				if(user.getViewNum()!=null){
					SET("view_num=#{viewNum}");
				}
				if(user.getPreTime()!=null){
					SET("pre_time=#{preTime}");
				}
				if(user.getEntryTime()!=null){
					SET("entry_time=#{entryTime}");
				}
				if(user.getProvince()!=null&& !user.getProvince().equals("")){
					SET("province=#{province}");
				}
				if(user.getCity()!=null&& !user.getCity().equals("")){
					SET("city=#{city}");
				}
				if(user.getArea()!=null && !user.getArea().equals("")){
					SET("area=#{area}");
				}
				if(user.getVersion()!=null){
					SET(" version=#{version}+1");
				}
				WHERE(" id=#{id} and version=#{version}");
			}
		}.toString();
		return sql;
	}
	
	public String updateUserInfo(final User user){
		return new SQL(){
			{
				UPDATE(Constants.USERINFOTABLE);
				if(user.getUserName()!=null&& !user.getUserName().equals("")){
					SET("user_name=#{userName}");
				}
				if(user.getUserId()!=null&& !user.getUserId().equals("")){
					SET("user_id=#{userId}");
				}
				if(user.getNickName()!=null&& !user.getNickName().equals("")){
					SET("nick_name=#{nickName}");
				}
				if(user.getPassword()!=null&& !user.getPassword().equals("")){
					SET("password=#{password}");
				}
				if(user.getPassword2()!=null&& !user.getPassword2().equals("")){
					SET("password2=#{password2}");
				}
				if(user.getSex()!=null && !user.getSex().equals("")){
					SET("sex=#{sex}");
				}
				if(user.getAge()!=null){
					SET("age=#{age}");
				}
				if(user.getDocumentType()!=null && !user.getDocumentType().equals("")){
					SET("document_type=#{documentType}");
				}
				if(user.getNumId()!=null && !user.getNumId().equals("")){
					SET("num_id=#{numId}");
				}
				if(user.getMobile()!=null && !user.getMobile().equals("")){
					SET("mobile=#{mobile}");
				}
				if(user.getOpenId()!=null&& !user.getOpenId().equals("")){
					SET("open_id=#{openId}");
				}
				if(user.getProvince()!=null&& !user.getProvince().equals("")){
					SET("province=#{province}");
				}
				if(user.getCity()!=null&& !user.getCity().equals("")){
					SET("city=#{city}");
				}
				if(user.getArea()!=null && !user.getArea().equals("")){
					SET("area=#{area}");
				}
				if(user.getAddress()!=null && !user.getAddress().equals("")){
					SET("address=#{address}");
				}
				if(user.getBankName()!=null && !user.getBankName().equals("")){
					SET("bank_name=#{bankName}");
				}
				if(user.getAccountId()!=null && !user.getAccountId().equals("")){
					SET("account_id=#{accountId}");
				}
				if(user.getAccountName()!=null && !user.getAccountName().equals("")){
					SET("account_name=#{accountName}");
				}
				if(user.getBankAdr()!=null&& !user.getBankAdr().equals("")){
					SET("bank_adr=#{bankAdr}");
				}
				if(user.getState()!=null){
					VALUES("state","#{state}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
		
	}
	

	public String updatePropertyOfUsers(@Param("property") String property,@Param("value") String value,@Param("id") Integer id,@Param("property1") String property1,@Param("value1") String value1 ){
		String sql ="update users set "+property+"=#{value} where id=#{id} and  "+property1+"=#{value1}";
		return sql;

	}
	
	public String updateOfUsers(String property,String value,String property1,String value1 ){
		String sql ="update users set "+property+"='"+value+"' where "+property1+"='"+value1+"'";
		return sql;

	}
	
	
	public String updatePropertyOfUserInfo(String property,String value,String property1,String value1 ){
		String sql ="update user_info set "+property+"='"+value+"' where "+property1+"='"+value1+"'";
		return sql;
	}
	
	public String selectPropertyOfUsers(@Param("property") String property,@Param("value") Integer value){
		String sql ="select * from users  where  "+property+"=#{value} and state>0";
		return sql;

	}
	
	public String updateUserForUserId(final User user){
		String sql = new SQL(){
			{
				UPDATE(Constants.USERSTABLE);
				if(user.getUserName()!=null&& !user.getUserName().equals("")){
					SET(" user_name=#{userName}");
				}
				if(user.getRefereeAll()!=null){
					SET(" referee_all=#{refereeAll}");
				}
				if(user.getRefereeNode()!=null){
					SET(" referee_node=#{refereeNode}");
				}
				if(user.getRmoney()!=null){
					SET("rmoney=#{rmoney}");
				}
				if(user.getCash()!=null){
					SET("cash=#{cash}");
				}
				if(user.getCashNum()!=null){
					SET("cash_num=#{cashNum}");
				}
				if(user.getIntegral()!=null){
					SET("integral=#{integral}");
				}
				if(user.getPreRmoney()!=null){
					SET("pre_rmoney=#{preRmoney}");
				}
				if(user.getPreCash()!=null){
					SET("pre_cash=#{preCash}");
				}
				if(user.getTotalIncome()!=null){
					SET("total_income=#{totalIncome}");
				}
				if(user.getPreCashNum()!=null){
					SET("pre_cash_num=#{preCashNum}");
				}
				if(user.getPreIntegral()!=null){
					SET("pre_integral=#{preIntegral}");
				}
				if(user.getRankJoin()!=null){
					SET(" rank_join=#{rankJoin}");
				}
				if(user.getRankJoinOld()!=null){
					SET(" rank_join_old=#{rankJoinOld}");
				}
				
				if(user.getPayTag()!=null){
					SET(" pay_tag=#{payTag}");
				}
				if(user.getState()!=null){
					SET(" state=#{state}");
				}
				if(user.getRmoney()!=null){
					SET(" rmoney=#{rmoney}");
				}
				if(user.getEndTime()!=null){
					SET(" end_time=#{entryTime}");
				}
				if(user.getVersion()!=null){
					SET(" version=#{version}+1");
				}
				WHERE(" user_id=#{userId} and version=#{version}");
			}
		}.toString();
		return sql;
	}
	
	public String updateUserInfoForUserId(final User user){
		return new SQL(){
			{
				UPDATE(Constants.USERINFOTABLE);
				if(user.getUserName()!=null&& !user.getUserName().equals("")){
					SET("user_name=#{userName}");
				}
				if(user.getUserId()!=null&& !user.getUserId().equals("")){
					SET("user_id=#{userId}");
				}
				if(user.getPassword()!=null&& !user.getPassword().equals("")){
					SET("password=#{password}");
				}
				if(user.getPassword2()!=null&& !user.getPassword2().equals("")){
					SET("password2=#{password2}");
				}
				if(user.getSex()!=null && !user.getSex().equals("")){
					SET("sex=#{sex}");
				}
				if(user.getAge()!=null){
					SET("age=#{age}");
				}
				if(user.getDocumentType()!=null && !user.getDocumentType().equals("")){
					SET("document_type=#{documentType}");
				}
				if(user.getNumId()!=null && !user.getNumId().equals("")){
					SET("num_id=#{numId}");
				}
				if(user.getMobile()!=null && !user.getMobile().equals("")){
					SET("mobile=#{mobile}");
				}
				if(user.getOpenId()!=null&& !user.getOpenId().equals("")){
					SET("open_id=#{openId}");
				}
				if(user.getProvince()!=null&& !user.getProvince().equals("")){
					SET("province=#{province}");
				}
				if(user.getCity()!=null&& !user.getCity().equals("")){
					SET("city=#{city}");
				}
				if(user.getArea()!=null && !user.getArea().equals("")){
					SET("area=#{area}");
				}
				if(user.getAddress()!=null && !user.getAddress().equals("")){
					SET("address=#{address}");
				}
				if(user.getBankName()!=null && !user.getBankName().equals("")){
					SET("bank_name=#{bankName}");
				}
				if(user.getAccountId()!=null && !user.getAccountId().equals("")){
					SET("account_id=#{accountId}");
				}
				if(user.getAccountName()!=null && !user.getAccountName().equals("")){
					SET("account_name=#{accountName}");
				}
				if(user.getBankAdr()!=null&& !user.getBankAdr().equals("")){
					SET("bank_adr=#{bankAdr}");
				}
				if(user.getState()!=null){
					VALUES("state","#{state}");
				}
				WHERE(" user_id=#{userId}");
			}
		}.toString();
		
	}
	
	public String selectUserBySql(String sql){
		String sql1="select * from users where "+sql;
		return sql1;
	}
	
	
	public String updateForSql(String sql){
		return sql;
	}
}
