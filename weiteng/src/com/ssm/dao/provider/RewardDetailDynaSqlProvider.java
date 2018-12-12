package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.RewardDetail;
import com.ssm.utils.Constants;

public class RewardDetailDynaSqlProvider {
	
	//分页动态查询
		public String selectWhitParam(final Map<String,Object> params){
			String sql = new SQL(){
				{
				SELECT ("*");
				FROM(Constants.REWARDDETAILTABLE);
				if(params.get("reward")!=null){
					 RewardDetail reward = (RewardDetail) params.get("reward");
					 if(reward.getUserId()!=null && !reward.getUserId().equals("")){
							WHERE(" user_id LIKE CONCAT ('%',#{reward.userId},'%')");
						}
						if(reward.getRefereeUserId()!=null && !reward.getRefereeUserId().equals("")){
							WHERE(" referee_user_id LIKE CONCAT ('%',#{reward.refereeUserId},'%')");
						}
						if(reward.getState()!=null){
							WHERE(" state LIKE CONCAT ('%',#{reward.state},'%')");
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
			if(params.get("pageModel")!=null){
				sql +=" limit #{pageModel.startIndex},#{pageModel.pageSize}";
			}
			return sql;
		}
		
		public String selectListWhitParam(final Map<String,Object> params){
			String sql = new SQL(){
				{
				SELECT ("*");
				FROM(Constants.REWARDDETAILTABLE);
				if(params.get("reward")!=null){
					 RewardDetail reward = (RewardDetail) params.get("reward");
					 if(reward.getUserId()!=null && !reward.getUserId().equals("")){
							WHERE(" user_id LIKE CONCAT ('%',#{reward.userId},'%')");
						}
						if(reward.getRefereeUserId()!=null && !reward.getRefereeUserId().equals("")){
							WHERE(" referee_user_id LIKE CONCAT ('%',#{reward.refereeUserId},'%')");
						}
						if(reward.getState()!=null){
							WHERE(" state LIKE CONCAT ('%',#{reward.state},'%')");
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
					FROM(Constants.REWARDDETAILTABLE);
					if(params.get("reward")!=null){
						 RewardDetail reward = (RewardDetail) params.get("reward");
						 if(reward.getUserId()!=null && !reward.getUserId().equals("")){
								WHERE(" user_id LIKE CONCAT ('%',#{reward.userId},'%')");
							}
							if(reward.getRefereeUserId()!=null && !reward.getRefereeUserId().equals("")){
								WHERE(" referee_user_id LIKE CONCAT ('%',#{reward.refereeUserId},'%')");
							}
							if(reward.getState()!=null){
								WHERE(" state LIKE CONCAT ('%',#{reward.state},'%')");
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
		
		public String insert(final RewardDetail reward){
			
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
					if(reward.getRefereeId()!=null){
						VALUES("referee_id","#{refereeId}");
					}
					if(reward.getRefereeUserId()!=null&& !reward.getRefereeUserId().equals("")){
						VALUES("referee_user_id","#{refereeUserId}");
					}
					if(reward.getRefereeUserName()!=null&& !reward.getRefereeUserName().equals("")){
						VALUES("referee_user_name","#{refereeUserName}");
					}
					if(reward.getType()!=null){
						VALUES("type","#{type}");
					}
					if(reward.getAmount()!=null){
						VALUES("amount","#{amount}");
					}
					if(reward.getState()!=null){
						VALUES("state","#{state}");
					}
					if(reward.getAward()!=null){
						VALUES("award","#{award}");
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
		
		public String update(final RewardDetail reward){
			String sql = new SQL(){
				{
					UPDATE(Constants.REWARDDETAILTABLE);
					if(reward.getUid()!=null){
						SET("uid=#{uid}");
					}
					if(reward.getUserId()!=null&& !reward.getUserId().equals("")){
						SET("user_id=#{userId}");
					}
					if(reward.getUserName()!=null&& !reward.getUserName().equals("")){
						SET("user_name=#{user_name}");
					}
					if(reward.getRefereeId()!=null){
						SET("referee_id=#{refereeId}");
					}
					if(reward.getRefereeUserId()!=null&& !reward.getRefereeUserId().equals("")){
						SET("referee_user_id=#{refereeUserId}");
					}
					if(reward.getRefereeUserName()!=null&& !reward.getRefereeUserName().equals("")){
						SET("referee_user_name=#{referee_user_name}");
					}
					if(reward.getType()!=null){
						SET("type=#{type}");
					}
					if(reward.getState()!=null){
						SET("state=#{state}");
					}
					if(reward.getAward()!=null){
						SET("award=#{award}");
					}
					if(reward.getAmount()!=null){
						SET("amount=#{amount}");
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

}
