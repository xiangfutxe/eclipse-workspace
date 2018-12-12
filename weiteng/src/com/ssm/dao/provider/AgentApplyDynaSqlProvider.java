package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.AgentApply;
import com.ssm.utils.Constants;

public class AgentApplyDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.AGENTAPPLYTABLE);
			if(params.get("agent")!=null){
				AgentApply agent = (AgentApply) params.get("agent");
				if(agent.getUserId()!=null && !agent.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{agent.userId},'%')");
				}
				if(agent.getState()!=null ){
					WHERE(" state LIKE CONCAT ('%',#{agent.state},'%')");
				}
				if(agent.getStartTime()!=null){
					WHERE("  entry_time >= #{agent.startTime}");
				}
				if(agent.getEndTime()!=null){
					WHERE("  entry_time <= #{agent.endTime}");
				}
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc limit #{pageModel.startIndex},#{pageModel.pageSize} ";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.AGENTAPPLYTABLE);
				if(params.get("agent")!=null){
					AgentApply agent = (AgentApply) params.get("agent");
					if(agent.getUserId()!=null && !agent.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{agent.userId},'%')");
					}
					if(agent.getState()!=null ){
						WHERE(" state LIKE CONCAT ('%',#{agent.state},'%')");
					}
					if(agent.getStartTime()!=null){
						WHERE("  entry_time >= #{agent.startTime}");
					}
					if(agent.getEndTime()!=null){
						WHERE("  entry_time <= #{agent.endTime}");
					}
				}
				
			}
		}.toString();
	}
	
	public String insert(final AgentApply agent){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.AGENTAPPLYTABLE);
				if(agent.getApplyId()!=null && !agent.getApplyId().equals("")){
					VALUES("apply_id","#{userId}");
				}
				if(agent.getUid()!=null){
					VALUES("uid","#{uid}");
				}
				if(agent.getUserId()!=null && !agent.getUserId().equals("")){
					VALUES("user_id","#{userId}");
				}
				if(agent.getUserName()!=null && !agent.getUserName().equals("")){
					VALUES("user_name","#{userName}");
				}
				if(agent.getProvince()!=null && !agent.getProvince().equals("")){
					VALUES("province","#{province}");
				}
				if(agent.getCity()!=null && !agent.getCity().equals("")){
					VALUES("city","#{city}");
				}
				if(agent.getArea()!=null && !agent.getArea().equals("")){
					VALUES("area","#{area}");
				}
				if(agent.getWeixin()!=null && !agent.getWeixin().equals("")){
					VALUES("weixin","#{weixin}");
				}
				if(agent.getRankJoin()!=null){
					VALUES("rank_join","#{rankJoin}");
				}
				if(agent.getState()!=null){
					VALUES("state","#{state}");
				}
				if(agent.getType()!=null){
					VALUES("type","#{type}");
				}
				if(agent.getMobile()!=null&& !agent.getMobile().equals("")){
					VALUES("mobile","#{mobile}");
					
				}
				if(agent.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
				if(agent.getEndTime()!=null){
					VALUES("end_time","#{endTime}");
				}
			}
		}.toString();
	}
	
	public String update(final AgentApply agent){
		return new SQL(){
			{
				UPDATE(Constants.AGENTAPPLYTABLE);
				if(agent.getUid()!=null){
					SET("uid=#{uid}");
				}
				if(agent.getUserId()!=null && !agent.getUserId().equals("")){
					SET("user_id=#{userId}");
				}
				if(agent.getUserName()!=null && !agent.getUserName().equals("")){
					SET("user_name=#{userName}");
				}
				if(agent.getProvince()!=null && !agent.getProvince().equals("")){
					SET("province=#{province}");
				}
				if(agent.getCity()!=null && !agent.getCity().equals("")){
					SET("city=#{city}");
				}
				if(agent.getArea()!=null && !agent.getArea().equals("")){
					SET("area=#{area}");
				}
				if(agent.getWeixin()!=null && !agent.getWeixin().equals("")){
					SET("weixin=#{weixin}");
				}
				if(agent.getRankJoin()!=null){
					SET("rank_join=#{rankJoin}");
				}
				if(agent.getState()!=null){
					SET("state=#{state}");
				}
				if(agent.getType()!=null){
					SET("type=#{type}");
				}
				if(agent.getMobile()!=null&& !agent.getMobile().equals("")){
					SET("mobile=#{mobile}");
				}
				if(agent.getReviewerId()!=null&& !agent.getReviewerId().equals("")){
					SET("reviewer_id=#{reviewerId}");
				}
				if(agent.getReviewTime()!=null){
					SET("review_time=#{reviewTime}");
				}
				if(agent.getEntryTime()!=null){
					SET("entry_time=#{entryTime}");
				}
				if(agent.getEndTime()!=null){
					SET("end_time=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	
	public String returnSql(String sql){
		return sql;
	}

}
