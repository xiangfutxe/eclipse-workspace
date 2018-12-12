package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.OrderDetail;
import com.ssm.pojo.Reward;
import com.ssm.utils.Constants;

public class RewardDynaSqlProvider {
	
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.REWARDTABLE);
			if(params.get("reward")!=null){
				 Reward reward = (Reward) params.get("reward");
				 if(reward.getUserId()!=null && !reward.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{reward.userId},'%')");
					}
					if(reward.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{reward.state},'%')");
					}
					if(reward.getMonthTag()!=null){
						WHERE(" month_tag =#{reward.monthTag}");
					}
					if(reward.getStartTime()!=null){
						WHERE(" entry_time <= #{reward.startTime}");
					}
					if(reward.getEndTime()!=null){
						WHERE(" entry_time >= #{reward.endTime}");
					}
			}
			}
		}.toString();
		sql+=" order by id desc ";
		if(params.get("pageModel")!=null){
			sql +=" limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String selectListWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.REWARDTABLE);
			if(params.get("reward")!=null){
				 Reward reward = (Reward) params.get("reward");
				 if(reward.getUserId()!=null && !reward.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{reward.userId},'%')");
					}
					if(reward.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{reward.state},'%')");
					}
					if(reward.getMonthTag()!=null){
						WHERE(" month_tag =#{reward.monthTag}");
					}
					if(reward.getStartTime()!=null){
						WHERE(" entry_time <= #{reward.startTime}");
					}
					if(reward.getEndTime()!=null){
						WHERE(" entry_time >= #{reward.endTime}");
					}
			}
			}
		}.toString();
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.REWARDTABLE);
				if(params.get("reward")!=null){
					 Reward reward = (Reward) params.get("reward");
					 if(reward.getUserId()!=null && !reward.getUserId().equals("")){
							WHERE(" user_id LIKE CONCAT ('%',#{reward.userId},'%')");
						}
						if(reward.getState()!=null){
							WHERE(" state LIKE CONCAT ('%',#{reward.state},'%')");
						}
						if(reward.getMonthTag()!=null){
							WHERE(" month_tag LIKE CONCAT ('%',#{reward.monthTag},'%')");
						}
						if(reward.getStartTime()!=null){
							WHERE(" entry_time <= #{reward.startTime}");
						}
						if(reward.getEndTime()!=null){
							WHERE(" entry_time >= #{reward.endTime}");
						}
				}
				
			}
		}.toString();
	}
	
	public String insert(final Reward reward){
		
		String sql = new SQL(){
			{
				INSERT_INTO(Constants.REWARDDETAILTABLE);
				if(reward.getUid()!=null){
					VALUES("uid","#{uid}");
				}
				if(reward.getUserId()!=null&& !reward.getUserId().equals("")){
					VALUES("user_id","#{userId}");
				}
				if(reward.getUserName()!=null&& !reward.getUserName().equals("")){
					VALUES("user_name","#{userName}");
				}
				if(reward.getRankJoin()!=null){
					VALUES("rank_join","#{rankJoin}");
				}
				if(reward.getAgentTag()!=null){
					VALUES("agent_tag","#{agentTag}");
				}
				if(reward.getPayTag()!=null){
					VALUES("pay_tag","#{payTag}");
				}
				if(reward.getMonthTag()!=null){
					VALUES("month_tag","#{monthTag}");
				}
				if(reward.getAmount_1()!=null){
					VALUES("amount_1","#{amount_1}");
				}
				if(reward.getAmount_2()!=null){
					VALUES("amount_2","#{amount_2}");
				}
				if(reward.getState()!=null){
					VALUES("state","#{state}");
				}
				if(reward.getGroupPrice()!=null){
					VALUES("group_price","#{groupPrice}");
				}
				if(reward.getAreaPrice()!=null){
					VALUES("area_price","#{areaPrice}");
				}
				if(reward.getStartTime()!=null ){
					VALUES("start_time","#{startTime}");
				}
				if(reward.getEntryTime()!=null ){
					VALUES("entry_time","#{entryTime}");
				}
				if(reward.getEndTime()!=null ){
					VALUES("end_time","#{endTime}");
				}
			}
		}.toString();
		return sql;
	}
	
	public String update(final Reward reward){
		String sql = new SQL(){
			{
				UPDATE(Constants.REWARDTABLE);
				if(reward.getUid()!=null){
					SET("uid=#{uid}");
				}
				if(reward.getUserId()!=null&& !reward.getUserId().equals("")){
					SET("user_id=#{userId}");
				}
				if(reward.getUserName()!=null&& !reward.getUserName().equals("")){
					SET("user_name=#{user_name}");
				}
				
				if(reward.getState()!=null){
					SET("state=#{state}");
				}
				
				if(reward.getEntryTime()!=null ){
					SET("entry_time=#{entryTime}");
				}
				if(reward.getEndTime()!=null ){
					SET("end_time=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
		return sql;
	}
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO "+Constants.REWARDTABLE);  
        sb.append("(id,uid,user_id,user_name,group_price,area_price,rank_join,agent_tag,pay_tag,month_tag,"
        		+ "amount_1,amount_2,amount_3,state,start_time,end_time,entry_time) ");  
        sb.append("VALUES "); 
        if(params.get("list")!=null){
        List<Reward> list = (List<Reward>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null,#'{'list[{0}].uid},"
        		+ "#'{'list[{0}].userId},#'{'list[{0}].userName},#'{'list[{0}].groupPrice},#'{'list[{0}].areaPrice},#'{'list[{0}].rankJoin},#'{'list[{0}].agentTag},#'{'list[{0}].payTag},"
        		+ "#'{'list[{0}].monthTag},#'{'list[{0}].amount_1},#'{'list[{0}].amount_2},#'{'list[{0}].amount_3},#'{'list[{0}].state}"
        		+ ",#'{'list[{0}].startTime},#'{'list[{0}].endTime},#'{'list[{0}].entryTime})");  
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
