package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.User;
import com.ssm.pojo.WRewardExtreme;

public class WRewardExtremeDynaSqlProvider {
	
	//分页动态查询
		public String selectWhitParam(final Map<String,Object> params){
			final String tableName =(String) params.get("tableName");
			String sql = new SQL(){
				{
				SELECT ("*");
				FROM(tableName);
				if(params.get("wrd")!=null){
					WRewardExtreme wrd = (WRewardExtreme) params.get("wrd");
					if(wrd.getUserId()!=null && !wrd.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{wrd.userId},'%')");
					}
					if(wrd.getWeekTag()!=null){
						WHERE(" weekTag LIKE CONCAT ('%',#{wrd.weekTag},'%')");
					}
					if(wrd.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{wrd.state},'%')");
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
			final String tableName =(String) params.get("tableName");
			return new SQL(){
				{
					SELECT("count(*)");
					FROM(tableName);
					if(params.get("wrd")!=null){
						WRewardExtreme wrd = (WRewardExtreme) params.get("wrd");
						if(wrd.getUserId()!=null && !wrd.getUserId().equals("")){
							WHERE(" userId LIKE CONCAT ('%',#{wrd.userId},'%')");
						}
						if(wrd.getWeekTag()!=null){
							WHERE(" weekTag LIKE CONCAT ('%',#{wrd.weekTag},'%')");
						}
						if(wrd.getState()!=null){
							WHERE(" state LIKE CONCAT ('%',#{wrd.state},'%')");
						}
						
					}
					
				}
			}.toString();
		}
		
		public String selectListWhitParam(final Map<String,Object> params){
			final String tableName =(String) params.get("tableName");
			String sql = new SQL(){
				{
				SELECT ("*");
				FROM(tableName);
				if(params.get("wrd")!=null){
					WRewardExtreme wrd = (WRewardExtreme) params.get("wrd");
					if(wrd.getUserId()!=null && !wrd.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{wrd.userId},'%')");
					}
					if(wrd.getWeekTag()!=null){
						WHERE(" weekTag LIKE CONCAT ('%',#{wrd.weekTag},'%')");
					}
					if(wrd.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{wrd.state},'%')");
					}
				}
				}
			}.toString();
			return sql;
		}
		
	public String createWRewardExtreme(String tableName){
		String sql ="create table if not exists "+tableName+" (id int(11) NOT NULL AUTO_INCREMENT,uId int(11) NOT NULL,userId varchar(10) not null,userName varchar(50) not null,amount decimal(16,3) DEFAULT '0',"
		 		+ "amount_1 decimal(16,3) DEFAULT '0',amount_2 decimal(16,3) DEFAULT '0',amount_3 decimal(16,3) DEFAULT '0',"
		 		+ "amount_4 decimal(16,3)," 
					+ "payTag tinyint(2) DEFAULT '1',rankJoin tinyint(2) DEFAULT '0',rankManage tinyint(2) DEFAULT '0',weekTag int(11) DEFAULT '0',state TINYINT(2) DEFAULT '0',entryTime datetime DEFAULT NULL,startTime datetime DEFAULT NULL,endTime datetime DEFAULT NULL,PRIMARY KEY (userId), UNIQUE KEY id(id),UNIQUE KEY(userId)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		return sql;
	}
	
	public String dropWRewardExtreme(String tableName){
		String sql ="drop table if exists "+tableName+" ";
		return sql;
	}
	
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder(); 
        String tableName=(String) params.get("tableName");
        sb.append("INSERT INTO "+tableName);  
        sb.append("(id,uid,userId,userName,amount_1,amount_2,amount_3,amount_4,payTag,weekTag,state,startTime,endTime,entryTime)");  
        sb.append(" VALUES "); 
        if(params.get("list")!=null){
        List<WRewardExtreme> list = (List<WRewardExtreme>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null,#'{'list[{0}].uid},#'{'list[{0}].userId},#'{'list[{0}].userName},#'{'list[{0}].amount_1},#'{'list[{0}].amount_2},#'{'list[{0}].amount_3}"
        		+ ",#'{'list[{0}].amount_4},#'{'list[{0}].payTag},#'{'list[{0}].weekTag},#'{'list[{0}].state},#'{'list[{0}].startTime},#'{'list[{0}].endTime},#'{'list[{0}].entryTime})");  
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
				str+= " and isEmpty='"+user.getIsEmpty()+"'";
		}
		String sql ="select sum(amount_1),sum(amount_2),sum(amount_3),sum(amount_4),sum(amount_5),sum(amount_6),"
			+ "sum(amount_7),sum(amount_8),sum(amount_9),sum(amount_10) ,sum(amount_11),sum(amount_12) from "
				+tableName+" as wr,users as us where wr.userId=us.userId" +str;
		return sql;
	}
	public String updateStateOfWRewardExtreme(String tableName,Integer id){
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
	

}
