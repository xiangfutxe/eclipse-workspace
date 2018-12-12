package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Message;
import com.ssm.utils.Constants;

public class MessageDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.MESSAGETABLE);
			if(params.get("msg")!=null){
				Message msg = (Message) params.get("msg");
				if(msg.getUserId()!=null && !msg.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{msg.userId},'%')");
				}
				if(msg.getUserName()!=null && !msg.getUserName().equals("")){
					WHERE(" userName LIKE CONCAT ('%',#{msg.userName},'%')");
				}
				if(msg.getAdmin()!=null && !msg.getAdmin().equals("")){
					WHERE(" admin LIKE CONCAT ('%',#{msg.admin},'%')");
				}
				if(msg.getUserMsg()!=null && !msg.getUserMsg().equals("")){
					WHERE(" admin LIKE CONCAT ('%',#{msg.admin},'%')");
				}
				if(msg.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{msg.state},'%')");
				}
				if(msg.getStartTime()!=null){
					WHERE("  entryTime >= #{news.startTime}");
				}
				if(msg.getEndTime()!=null){
					WHERE("  entryTime <= #{news.endTime}");
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
				FROM(Constants.MESSAGETABLE);
				if(params.get("msg")!=null){
					Message msg = (Message) params.get("msg");
					if(msg.getUserId()!=null && !msg.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{msg.userId},'%')");
					}
					if(msg.getUserName()!=null && !msg.getUserName().equals("")){
						WHERE(" userName LIKE CONCAT ('%',#{msg.userName},'%')");
					}
					if(msg.getAdmin()!=null && !msg.getAdmin().equals("")){
						WHERE(" admin LIKE CONCAT ('%',#{msg.admin},'%')");
					}
					if(msg.getUserMsg()!=null && !msg.getUserMsg().equals("")){
						WHERE(" admin LIKE CONCAT ('%',#{msg.admin},'%')");
					}
					if(msg.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{msg.state},'%')");
					}
					if(msg.getStartTime()!=null){
						WHERE("  entryTime >= #{news.startTime}");
					}
					if(msg.getEndTime()!=null){
						WHERE("  entryTime <= #{news.endTime}");
					}
				}
				
			}
		}.toString();
	}
	
	public String insertMessage(final Message msg){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.MESSAGETABLE);
				if(msg.getUserId()!=null&& !msg.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(msg.getAdmin()!=null&& !msg.getAdmin().equals("")){
					VALUES("admin","#{admin}");
				}
				if(msg.getUserName()!=null&& !msg.getUserName().equals("")){
					VALUES("userName","#{userName}");
				}
				if(msg.getUserMsg()!=null&& !msg.getUserMsg().equals("")){
					VALUES("userMsg","#{userMsg}");
				}
				if(msg.getState()!=null){
					VALUES("state","#{state}");
				}
				if(msg.getReplyTime()!=null){
					VALUES("replyTime","#{replyTime}");
				}
				if(msg.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
			}
		}.toString();
	}
	
	public String updateMessage(Message msg){
		String str = "";
				if(msg.getUserId()!=null&& !msg.getUserId().equals("")){
					if(str.equals("")) str=" userId='"+msg.getUserId()+"'";
					else str +=",userId='"+msg.getUserId()+"'";
				}
				if(msg.getAdmin()!=null&& !msg.getAdmin().equals("")){
					if(str.equals("")) str=" admin='"+msg.getAdmin()+"'";
					else str +=",admin='"+msg.getAdmin()+"'";
				}
				if(msg.getUserName()!=null&& !msg.getUserName().equals("")){
					if(str.equals("")) str=" userName='"+msg.getUserName()+"'";
					else str +=",userName='"+msg.getUserName()+"'";
				}
				if(msg.getUserMsg()!=null&& !msg.getUserMsg().equals("")){
					if(str.equals("")) str=" userMsg='"+msg.getUserMsg()+"'";
					else str +=",userMsg='"+msg.getUserMsg()+"'";
				}
				if(msg.getAdminMsg()!=null&& !msg.getAdminMsg().equals("")){
					if(str.equals("")) str=" adminMsg='"+msg.getAdminMsg()+"'";
					else str +=",adminMsg='"+msg.getAdminMsg()+"'";
				}
				if(msg.getState()!=null){
					if(str.equals("")) str=" state='"+msg.getState()+"'";
					else str +=",state='"+msg.getState()+"'";
				}
				if(msg.getReplyTime()!=null){
					if(str.equals("")) str=" replyTime='"+msg.getReplyTime()+"'";
					else str +=",replyTime='"+msg.getReplyTime()+"'";
				}
				if(msg.getEntryTime()!=null){
					if(str.equals("")) str=" entryTime='"+msg.getEntryTime()+"'";
					else str +=",entryTime='"+msg.getEntryTime()+"'";
				}
			String sql ="update message set "+str+" where id='"+msg.getId()+"'";
		return sql;
	}
}
