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
					WHERE(" userId LIKE CONCAT ('%',#{with_drew.userId},'%')");
				}
				if(with_drew.getState()!=null){
					WHERE("  state LIKE CONCAT ('%',#{with_drew.state},'%')");
				}
				if(with_drew.getStartTime()!=null){
					WHERE("  applyTime >= #{with_drew.startTime}");
				}
				if(with_drew.getEndTime()!=null){
					WHERE("  applyTime <= #{with_drew.endTime}");
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
						WHERE(" userId LIKE CONCAT ('%',#{with_drew.userId},'%')");
					}
					if(with_drew.getState()!=null){
						WHERE("  state LIKE CONCAT ('%',#{with_drew.state},'%')");
					}
					if(with_drew.getStartTime()!=null){
						WHERE("  applyTime >= #{with_drew.startTime}");
					}
					if(with_drew.getEndTime()!=null){
						WHERE("  applyTime <= #{with_drew.endTime}");
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
					WHERE(" userId LIKE CONCAT ('%',#{with_drew.userId},'%')");
				}
				if(with_drew.getState()!=null){
					WHERE("  state LIKE CONCAT ('%',#{with_drew.state},'%')");
				}
				if(with_drew.getStartTime()!=null){
					WHERE("  applyTime >= #{with_drew.startTime}");
				}
				if(with_drew.getEndTime()!=null){
					WHERE("  applyTime <= #{with_drew.endTime}");
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
				SELECT (" sum(amount) amount,sum(actualAmount) actualAmount,sum(fee) fee ");
			FROM(Constants.WITHDREWTABLE);
			if(params.get("with_drew")!=null){
				WithDrew with_drew = (WithDrew) params.get("with_drew");
				if(with_drew.getUserId()!=null && !with_drew.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{with_drew.userId},'%')");
				}
				if(with_drew.getState()!=null){
					WHERE("  state LIKE CONCAT ('%',#{with_drew.state},'%')");
				}
				if(with_drew.getStartTime()!=null){
					WHERE("  applyTime >= #{with_drew.startTime}");
				}
				if(with_drew.getEndTime()!=null){
					WHERE("  applyTime <= #{with_drew.endTime}");
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
					VALUES("applyId","#{applyId}");
				}
				if(WithDrew.getuId()!=null){
					VALUES("uId","#{uId}");
				}
				if(WithDrew.getUserId()!=null&& !WithDrew.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(WithDrew.getUserName()!=null&& !WithDrew.getUserName().equals("")){
					VALUES("userName","#{userName}");
				}
				if(WithDrew.getAmount()!=null){
					VALUES("amount","#{amount}");
				}
				if(WithDrew.getFee()!=null){
					VALUES("fee","#{fee}");
				}
				if(WithDrew.getActualAmount()!=null){
					VALUES("actualAmount","#{actualAmount}");
				}
				if(WithDrew.getState()!=null){
					VALUES("state","#{state}");
				}
				
				if(WithDrew.getAdmin()!=null&& !WithDrew.getAdmin().equals("")){
					VALUES("admin","#{admin}");
				}
				if(WithDrew.getReviewTime()!=null){
					VALUES("reviewTime","#{reviewTime}");
				}
				if(WithDrew.getApplyTime()!=null){
					VALUES("applyTime","#{applyTime}");
				}
				if(WithDrew.getAccountId()!=null&& !WithDrew.getAccountId().equals("")){
					VALUES("accountId","#{accountId}");
				}
				if(WithDrew.getAccountName()!=null&& !WithDrew.getAccountName().equals("")){
					VALUES("accountName","#{accountName}");
				}
				if(WithDrew.getBankName()!=null&& !WithDrew.getBankName().equals("")){
					VALUES("bankName","#{bankName}");
				}
				if(WithDrew.getBankAdr()!=null&& !WithDrew.getBankAdr().equals("")){
					VALUES("bankAdr","#{bankAdr}");
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
					SET("applyId=#{applyId}");
				}
				if(WithDrew.getUserId()!=null&& !WithDrew.getUserId().equals("")){
					SET("userId=#{userId}");
				}
				if(WithDrew.getUserName()!=null&& !WithDrew.getUserName().equals("")){
					SET("userName=#{userName}");
				}
				if(WithDrew.getAmount()!=null){
					SET("amount=#{amount}");
				}
				if(WithDrew.getFee()!=null){
					SET("fee=#{fee}");
				}
				if(WithDrew.getActualAmount()!=null){
					SET("actualAmount=#{actualAmount}");
				}
				if(WithDrew.getState()!=null){
					SET("state=#{state}");
				}
				
				if(WithDrew.getAdmin()!=null&& !WithDrew.getAdmin().equals("")){
					SET("admin=#{admin}");
				}
				if(WithDrew.getReviewTime()!=null){
					SET("reviewTime=#{reviewTime}");
				}
				if(WithDrew.getApplyTime()!=null){
					SET("applyTime=#{applyTime}");
				}
				if(WithDrew.getAccountId()!=null&& !WithDrew.getAccountId().equals("")){
					SET("accountId=#{accountId}");
				}
				if(WithDrew.getAccountName()!=null&& !WithDrew.getAccountName().equals("")){
					SET("accountName=#{accountName}");
				}
				if(WithDrew.getBankName()!=null&& !WithDrew.getBankName().equals("")){
					SET("bankName=#{bankName}");
				}
				if(WithDrew.getBankAdr()!=null&& !WithDrew.getBankAdr().equals("")){
					SET("bankAdr=#{bankAdr}");
				}
				if(WithDrew.getComments()!=null&& !WithDrew.getComments().equals("")){
					SET("comments=#{comments}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
