package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.User;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WSettle;

public class WRewardDynaSqlProvider {
	
	//分页动态查询
		public String selectWhitParam(final Map<String,Object> params){
			final String tableName =(String) params.get("tableName");
			String str = "";
				if(params.get("wrd")!=null){
					WReward wrd = (WReward) params.get("wrd");
					if(wrd.getUserId()!=null && !wrd.getUserId().equals("")){
						str+= " and wr.userId like '%"+wrd.getUserId()+"%'";
					}
					if(wrd.getWeekTag()!=null){
						str+= " and wr.weekTag ='"+wrd.getWeekTag()+"'";
					}
					if(wrd.getState()!=null){
						str+= " and  wr.state='"+wrd.getState()+"'";
					}
					if(wrd.getPayTag()!=null){
						str+= " and  wr.payTag='"+wrd.getPayTag()+"'";
					}
				}
				
				if(params.get("hide")!=null){
					String hide = (String)params.get("hide");
					if(!hide.equals("")){
					str+= " and us.is_hide ='"+hide+"'";
					}
				}
			if(params.get("pageModel")!=null){
				str+=" order by wr.id desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
			}
			
			String sql ="select wr.id id,wr.uId uId,wr.userId userId,wr.userName userName,wr.startTime startTime,wr.endTime endTime,"
					+ "wr.weekTag weekTag,wr.amount_1 amount_1,wr.amount_2 amount_2,wr.amount_3 amount_3,"
					+ "wr.amount_4 amount_4,wr.amount_5 amount_5,wr.amount_6 amount_6,wr.amount_7 amount_7,"
					+ "wr.amount_8 amount_8,wr.amount_9 amount_9,wr.amount_10 amount_10,wr.amount_11 amount_11,"
					+ "wr.amount_12 amount_12,wr.rankJoin rankJoin,wr.rankManage rankManage,wr.payTag payTag,wr.rank rank,wr.state state,wr.entrytime entryTime from "+tableName+" as wr,users as us where us.userId=wr.userId "+str;
			return sql;
		}
		
		public String count( final Map<String,Object> params){
			final String tableName =(String) params.get("tableName");
			String str = "";
				if(params.get("wrd")!=null){
					WReward wrd = (WReward) params.get("wrd");
					if(wrd.getUserId()!=null && !wrd.getUserId().equals("")){
						str+= " and wr.userId like '%"+wrd.getUserId()+"%'";
					}
					if(wrd.getWeekTag()!=null){
						str+= " and wr.weekTag ='"+wrd.getWeekTag()+"'";
					}
					if(wrd.getState()!=null){
						str+= " and  wr.state='"+wrd.getState()+"'";
					}
					if(wrd.getPayTag()!=null){
						str+= " and  wr.payTag='"+wrd.getPayTag()+"'";
					}
				}
				
				if(params.get("hide")!=null){
					String hide = (String)params.get("hide");
					if(!hide.equals("")){
					str+= " and us.is_hide ='"+hide+"'";
					}
				}
			if(params.get("pageModel")!=null){
				str+=" order by wr.id desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
			}
			
			
			String sql ="select count(*) from "+tableName+" as wr,users as us where us.userId=wr.userId "+str;
			return sql;
		}
		
		public String selectListWhitParam(final Map<String,Object> params){
			final String tableName =(String) params.get("tableName");
			String str = "";
				if(params.get("wrd")!=null){
					WReward wrd = (WReward) params.get("wrd");
					if(wrd.getUserId()!=null && !wrd.getUserId().equals("")){
						str+= " and wr.userId like '%"+wrd.getUserId()+"%'";
					}
					if(wrd.getWeekTag()!=null){
						str+= " and wr.weekTag ='"+wrd.getWeekTag()+"'";
					}
					if(wrd.getState()!=null){
						str+= " and  wr.state='"+wrd.getState()+"'";
					}
					if(wrd.getPayTag()!=null){
						str+= " and  wr.payTag='"+wrd.getPayTag()+"'";
					}
				}
				
				if(params.get("hide")!=null){
					String hide = (String)params.get("hide");
					if(!hide.equals("")){
					str+= " and us.is_hide ='"+hide+"'";
					}
				}
				str+=" order by wr.id desc";
			
			String sql ="select wr.id id,wr.uId uId,wr.userId userId,wr.userName userName,wr.startTime startTime,wr.endTime endTime,"
					+ "wr.weekTag weekTag,wr.amount_1 amount_1,wr.amount_2 amount_2,wr.amount_3 amount_3,"
					+ "wr.amount_4 amount_4,wr.amount_5 amount_5,wr.amount_6 amount_6,wr.amount_7 amount_7,"
					+ "wr.amount_8 amount_8,wr.amount_9 amount_9,wr.amount_10 amount_10,wr.amount_11 amount_11,"
					+ "wr.amount_12 amount_12,wr.rankJoin rankJoin,wr.rankManage rankManage,wr.payTag payTag,wr.rank rank,wr.state state,wr.entrytime entryTime from "+tableName+" as wr,users as us where us.userId=wr.userId "+str;
			return sql;
		}
		
	public String createWReward(String tableName){
		String sql ="create table if not exists "+tableName+" (id int(11) NOT NULL AUTO_INCREMENT,uId int(11) NOT NULL,userId varchar(10) not null,userName varchar(50) not null,amount decimal(16,3) DEFAULT '0',"
		 		+ "amount_1 decimal(16,3) DEFAULT '0',amount_2 decimal(16,3) DEFAULT '0',amount_3 decimal(16,3) DEFAULT '0',"
		 		+ "amount_4 decimal(16,3) DEFAULT '0',amount_5 decimal(16,3) DEFAULT '0',amount_6 decimal(16,3) DEFAULT '0',"
		 		+ "amount_7 decimal(16,3) DEFAULT '0',amount_8 decimal(16,3) DEFAULT '0',amount_9 decimal(16,3) DEFAULT '0',"
		 		+ "amount_10 decimal(16,3) DEFAULT '0',amount_11 decimal(16,3) DEFAULT '0',amount_12 decimal(16,3) DEFAULT '0'," 
					+ "payTag tinyint(2) DEFAULT '1',rankJoin tinyint(2) DEFAULT '0',rankManage tinyint(2) DEFAULT '0',rank tinyint(2) DEFAULT '0',weekTag int(11) DEFAULT '0',state TINYINT(2) DEFAULT '0',entryTime datetime DEFAULT NULL,startTime datetime DEFAULT NULL,endTime datetime DEFAULT NULL,PRIMARY KEY (userId), UNIQUE KEY id(id),UNIQUE KEY(userId)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		return sql;
	}
	
	public String dropWReward(String tableName){
		String sql ="drop table if exists "+tableName+" ";
		return sql;
	}
	
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder(); 
        String tableName=(String) params.get("tableName");
        sb.append("INSERT INTO "+tableName);  
        sb.append("(id,uId,userId,userName,amount_1,amount_2,amount_3,amount_4,amount_5,amount_6,amount_7,amount_8,amount_9,amount_10,amount_11,amount_12,rankJoin,rank,payTag,weekTag,startTime,endTime,entryTime)");  
        sb.append(" VALUES "); 
        if(params.get("list")!=null){
        List<WReward> list = (List<WReward>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null,#'{'list[{0}].uId},#'{'list[{0}].userId},#'{'list[{0}].userName},#'{'list[{0}].amount_1},#'{'list[{0}].amount_2},#'{'list[{0}].amount_3}"
        		+ ",#'{'list[{0}].amount_4},#'{'list[{0}].amount_5},#'{'list[{0}].amount_6}"
        		+ ",#'{'list[{0}].amount_7},#'{'list[{0}].amount_8},#'{'list[{0}].amount_9}"
        		+ ",#'{'list[{0}].amount_10},#'{'list[{0}].amount_11},#'{'list[{0}].amount_12}"
        		+ ",#'{'list[{0}].rankJoin},#'{'list[{0}].rank},#'{'list[{0}].payTag},#'{'list[{0}].weekTag},#'{'list[{0}].startTime},#'{'list[{0}].endTime},#'{'list[{0}].entryTime})");  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i})); 
          if(i<list.size()-1){
            	 sb.append(",");  
            }
           
        }  
		}
        return sb.toString();  
    }  
	
	public String sumAward(String tableName,User user){
		String str ="";
		if(user!=null){
			if(user.getIsHide()!=null)
				str+= " and us.is_hide='"+user.getIsHide()+"'";
			if(user.getIsEmpty()!=null)
				str+= " and us.isEmpty='"+user.getIsEmpty()+"'";
			if(user.getPayTag()!=null)
				str+= " and wr.payTag='"+user.getPayTag()+"'";
		}
		String sql ="select sum(amount_1),sum(amount_2),sum(amount_3),sum(amount_4),sum(amount_5),sum(amount_6),"
			+ "sum(amount_7),sum(amount_8),sum(amount_9),sum(amount_10) ,sum(amount_11),sum(amount_12) from "
				+tableName+" as wr,users as us where wr.userId=us.userId" +str;
		return sql;
	}
	public String updateStateOfWReward(String tableName,Integer id){
		String str ="update "+ tableName+" set state = '"+id+"'";
		return str;
	}
	
	
	public String findByUserId(String userId,String tableName){
		String str ="select * from  "+ tableName+" where userId = '"+userId+"'";
		return str;
	}
	
	public String findAll(String tableName,String forUpdate){
		String str ="select * from  "+ tableName+" order by id asc  "+forUpdate;
		return str;
	}
	
	public String updateForState(String tableName,Integer state1,Integer state2){
		String str ="update  "+ tableName+" set state='"+state1+"' where state='"+state2+"'";
		return str;
	}
	
	public String update(final Map<String,Object> params){
		final String tableName =(String) params.get("tableName");
		String str = "";
			
				if(params.get("wrd")!=null){
					WReward wrd = (WReward) params.get("wrd");
			
				if(wrd.getAmount_1()!=null){
					if(str.equals("")) str ="amount_1='"+wrd.getAmount_1()+"'";
					else str+=",amount_1='"+wrd.getAmount_1()+"' ";
				}
				if(wrd.getAmount_2()!=null){
					if(str.equals("")) str ="amount_2='"+wrd.getAmount_2()+"'";
					else str+=",amount_2='"+wrd.getAmount_2()+"' ";
				}
				if(wrd.getAmount_3()!=null){
					if(str.equals("")) str ="amount_3='"+wrd.getAmount_3()+"'";
					else str+=",amount_3='"+wrd.getAmount_3()+"' ";
				}
				if(wrd.getAmount_4()!=null){
					if(str.equals("")) str ="amount_4='"+wrd.getAmount_4()+"'";
					else str+=",amount_4='"+wrd.getAmount_4()+"' ";
				}
				if(wrd.getAmount_5()!=null){
					if(str.equals("")) str ="amount_5='"+wrd.getAmount_5()+"'";
					else str+=",amount_5='"+wrd.getAmount_5()+"' ";
				}
				if(wrd.getAmount_6()!=null){
					if(str.equals("")) str ="amount_6='"+wrd.getAmount_6()+"'";
					else str+=",amount_6='"+wrd.getAmount_6()+"' ";
				}
				if(wrd.getAmount_7()!=null){
					if(str.equals("")) str ="amount_7='"+wrd.getAmount_7()+"'";
					else str+=",amount_7='"+wrd.getAmount_7()+"' ";
				}
				if(wrd.getAmount_8()!=null){
					if(str.equals("")) str ="amount_8='"+wrd.getAmount_8()+"'";
					else str+=",amount_8='"+wrd.getAmount_8()+"' ";
				}
				if(wrd.getAmount_9()!=null){
					if(str.equals("")) str ="amount_9='"+wrd.getAmount_9()+"'";
					else str+=",amount_9='"+wrd.getAmount_9()+"' ";
				}
				if(wrd.getAmount_10()!=null){
					if(str.equals("")) str ="amount_10='"+wrd.getAmount_10()+"'";
					else str+=",amount_10='"+wrd.getAmount_10()+"' ";
				}
				if(wrd.getAmount_11()!=null){
					if(str.equals("")) str ="amount_1='"+wrd.getAmount_11()+"'";
					else str+=",amount_11='"+wrd.getAmount_11()+"' ";
				}
				if(wrd.getAmount_12()!=null){
					if(str.equals("")) str ="amount_12='"+wrd.getAmount_12()+"'";
					else str+=",amount_12='"+wrd.getAmount_12()+"' ";
				}
				
				if(wrd.getId()!=null){
					str+=" where id='"+wrd.getId()+"' ";
				}
				}
		
		String sql= "update "+tableName+" set "+str;
		return sql;
		
	}

}
