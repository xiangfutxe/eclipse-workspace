package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.WithDrew;
import com.ssm.utils.Constants;

public class WithDrewDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.WITHDREWTABLE);
			if(params.get("with_drew")!=null){
				WithDrew with_drew = (WithDrew) params.get("with_drew");
				if(with_drew.getUserId()!=null && !with_drew.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{with_drew.userId},'%')");
				}
				if(with_drew.getState()!=null){
					WHERE("  state LIKE CONCAT ('%',#{with_drew.state},'%')");
				}
				if(with_drew.getStartTime()!=null){
					WHERE("  apply_time >= #{with_drew.startTime}");
				}
				if(with_drew.getEndTime()!=null){
					WHERE("  apply_time <= #{with_drew.endTime}");
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
				FROM(Constants.WITHDREWTABLE);
				if(params.get("with_drew")!=null){
					WithDrew with_drew = (WithDrew) params.get("with_drew");
					if(with_drew.getUserId()!=null && !with_drew.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{with_drew.userId},'%')");
					}
					if(with_drew.getState()!=null){
						WHERE("  state LIKE CONCAT ('%',#{with_drew.state},'%')");
					}
					if(with_drew.getStartTime()!=null){
						WHERE("  apply_time >= #{with_drew.startTime}");
					}
					if(with_drew.getEndTime()!=null){
						WHERE("  apply_time <= #{with_drew.endTime}");
					}
				}
			}
		}.toString();
	}
	
	public String selectListWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.WITHDREWTABLE);
			if(params.get("with_drew")!=null){
				WithDrew with_drew = (WithDrew) params.get("with_drew");
				if(with_drew.getUserId()!=null && !with_drew.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{with_drew.userId},'%')");
				}
				if(with_drew.getState()!=null){
					WHERE("  state LIKE CONCAT ('%',#{with_drew.state},'%')");
				}
				if(with_drew.getStartTime()!=null){
					WHERE("  apply_time >= #{with_drew.startTime}");
				}
				if(with_drew.getEndTime()!=null){
					WHERE("  apply_time <= #{with_drew.endTime}");
				}
			}
			}
		}.toString();
			sql +=" order by id desc ";
		return sql;
	}
	
	public String sumByParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
				SELECT (" sum(amount) amount,sum(actual_amount) actualAmount,sum(fee) fee ");
			FROM(Constants.WITHDREWTABLE);
			if(params.get("with_drew")!=null){
				WithDrew with_drew = (WithDrew) params.get("with_drew");
				if(with_drew.getUserId()!=null && !with_drew.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{with_drew.userId},'%')");
				}
				if(with_drew.getState()!=null){
					WHERE("  state LIKE CONCAT ('%',#{with_drew.state},'%')");
				}
				if(with_drew.getStartTime()!=null){
					WHERE("  apply_time >= #{with_drew.startTime}");
				}
				if(with_drew.getEndTime()!=null){
					WHERE("  apply_time <= #{with_drew.endTime}");
				}
			}
			}
		}.toString();
			sql +=" order by id desc ";
		return sql;
	}
	
	public String insertWithDrew(final WithDrew WithDrew){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.WITHDREWTABLE);
				if(WithDrew.getApplyId()!=null&& !WithDrew.getApplyId().equals("")){
					VALUES("apply_id","#{applyId}");
				}
				if(WithDrew.getUid()!=null){
					VALUES("uid","#{uid}");
				}
				if(WithDrew.getUserId()!=null&& !WithDrew.getUserId().equals("")){
					VALUES("user_id","#{userId}");
				}
				if(WithDrew.getUserName()!=null&& !WithDrew.getUserName().equals("")){
					VALUES("user_name","#{userName}");
				}
				if(WithDrew.getAmount()!=null){
					VALUES("amount","#{amount}");
				}
				if(WithDrew.getFee()!=null){
					VALUES("fee","#{fee}");
				}
				if(WithDrew.getActualAmount()!=null){
					VALUES("actual_amount","#{actualAmount}");
				}
				if(WithDrew.getState()!=null){
					VALUES("state","#{state}");
				}
				
				if(WithDrew.getReviewerId()!=null&& !WithDrew.getReviewerId().equals("")){
					VALUES("reviewer_id","#{reviewerId}");
				}
				if(WithDrew.getReviewTime()!=null){
					VALUES("review_time","#{reviewTime}");
				}
				if(WithDrew.getApplyTime()!=null){
					VALUES("apply_time","#{applyTime}");
				}
				if(WithDrew.getAccountId()!=null&& !WithDrew.getAccountId().equals("")){
					VALUES("account_id","#{accountId}");
				}
				if(WithDrew.getAccountName()!=null&& !WithDrew.getAccountName().equals("")){
					VALUES("account_name","#{accountName}");
				}
				if(WithDrew.getBankName()!=null&& !WithDrew.getBankName().equals("")){
					VALUES("bank_name","#{bankName}");
				}
				if(WithDrew.getBankAdr()!=null&& !WithDrew.getBankAdr().equals("")){
					VALUES("bank_adr","#{bankAdr}");
				}
				if(WithDrew.getComments()!=null&& !WithDrew.getComments().equals("")){
					VALUES("remark","#{remark}");
				}
			}
		}.toString();
	}
	
	public String updateWithDrew(final WithDrew WithDrew){
		return new SQL(){
			{
				UPDATE(Constants.WITHDREWTABLE);
				if(WithDrew.getApplyId()!=null&& !WithDrew.getApplyId().equals("")){
					SET("apply_id=#{applyId}");
				}
				if(WithDrew.getUserId()!=null&& !WithDrew.getUserId().equals("")){
					SET("user_id=#{userId}");
				}
				if(WithDrew.getUserName()!=null&& !WithDrew.getUserName().equals("")){
					SET("user_name=#{userName}");
				}
				if(WithDrew.getAmount()!=null){
					SET("amount=#{amount}");
				}
				if(WithDrew.getFee()!=null){
					SET("fee=#{fee}");
				}
				if(WithDrew.getActualAmount()!=null){
					SET("actual_amount=#{actualAmount}");
				}
				if(WithDrew.getState()!=null){
					SET("state=#{state}");
				}
				
				if(WithDrew.getReviewerId()!=null&& !WithDrew.getReviewerId().equals("")){
					SET("reviewer_id=#{reviewerId}");
				}
				if(WithDrew.getReviewTime()!=null){
					SET("review_time=#{reviewTime}");
				}
				if(WithDrew.getApplyTime()!=null){
					SET("apply_time=#{applyTime}");
				}
				if(WithDrew.getAccountId()!=null&& !WithDrew.getAccountId().equals("")){
					SET("account_id=#{accountId}");
				}
				if(WithDrew.getAccountName()!=null&& !WithDrew.getAccountName().equals("")){
					SET("account_name=#{accountName}");
				}
				if(WithDrew.getBankName()!=null&& !WithDrew.getBankName().equals("")){
					SET("bank_name=#{bankName}");
				}
				if(WithDrew.getBankAdr()!=null&& !WithDrew.getBankAdr().equals("")){
					SET("bank_adr=#{bankAdr}");
				}
				if(WithDrew.getComments()!=null&& !WithDrew.getComments().equals("")){
					SET("comments=#{comments}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
