package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.ChargeApply;
import com.ssm.utils.Constants;

public class ChargeApplyDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.CHARGEAPPLYTABLE);
			if(params.get("chargeApply")!=null){
				ChargeApply chargeApply = (ChargeApply) params.get("chargeApply");
				if(chargeApply.getUserId()!=null && !chargeApply.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{chargeApply.userId},'%')");
				}
				if(chargeApply.getType()!=null){
					WHERE("  type LIKE CONCAT ('%',#{chargeApply.type},'%')");
				}
				if(chargeApply.getState()!=null){
					WHERE("  state LIKE CONCAT ('%',#{chargeApply.state},'%')");
				}
				if(chargeApply.getStartTime()!=null){
					WHERE("  applyTime >= #{chargeApply.startTime}");
				}
				if(chargeApply.getEndTime()!=null){
					WHERE("  applyTime <= #{chargeApply.endTime}");
				}
			
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}else{
			sql +=" order by id desc";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.CHARGEAPPLYTABLE);
				if(params.get("chargeApply")!=null){
					ChargeApply chargeApply = (ChargeApply) params.get("chargeApply");
					if(chargeApply.getUserId()!=null && !chargeApply.getUserId().equals("")){
						WHERE(" userId LIKE CONCAT ('%',#{chargeApply.userId},'%')");
					}
					if(chargeApply.getType()!=null){
						WHERE("  type LIKE CONCAT ('%',#{chargeApply.type},'%')");
					}
					if(chargeApply.getState()!=null){
						WHERE("  state LIKE CONCAT ('%',#{chargeApply.state},'%')");
					}
					if(chargeApply.getStartTime()!=null){
						WHERE("  applyTime >= #{chargeApply.startTime}");
					}
					if(chargeApply.getEndTime()!=null){
						WHERE("  applyTime <= #{chargeApply.endTime}");
					}
					
				}
				
			}
		}.toString();
	}
	
	public String sumByParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
				SELECT (" sum(amount) amount ");
				FROM(Constants.CHARGEAPPLYTABLE);
			if(params.get("chargeApply")!=null){
				ChargeApply chargeApply = (ChargeApply) params.get("chargeApply");
				if(chargeApply.getUserId()!=null && !chargeApply.getUserId().equals("")){
					WHERE(" userId LIKE CONCAT ('%',#{chargeApply.userId},'%')");
				}
				if(chargeApply.getType()!=null){
					WHERE("  type LIKE CONCAT ('%',#{chargeApply.type},'%')");
				}
				if(chargeApply.getState()!=null){
					WHERE("  state LIKE CONCAT ('%',#{chargeApply.state},'%')");
				}
				if(chargeApply.getStartTime()!=null){
					WHERE("  applyTime >= #{chargeApply.startTime}");
				}
				if(chargeApply.getEndTime()!=null){
					WHERE("  applyTime <= #{chargeApply.endTime}");
				}
			
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}else{
			sql +=" order by id desc";
		}
		return sql;
	}
	
	public String insertChargeApply(final ChargeApply chargeApply){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.CHARGEAPPLYTABLE);
				if(chargeApply.getApplyId()!=null&& !chargeApply.getApplyId().equals("")){
					VALUES("applyId","#{applyId}");
				}
				if(chargeApply.getUserId()!=null&& !chargeApply.getUserId().equals("")){
					VALUES("userId","#{userId}");
				}
				if(chargeApply.getUserName()!=null&& !chargeApply.getUserName().equals("")){
					VALUES("userName","#{userName}");
				}
				if(chargeApply.getAmount()!=null){
					VALUES("amount","#{amount}");
				}
				if(chargeApply.getType()!=null){
					VALUES("type","#{type}");
				}
				if(chargeApply.getState()!=null){
					VALUES("state","#{state}");
				}
				if(chargeApply.getAccountId()!=null&& !chargeApply.getAccountId().equals("")){
					VALUES("accountId","#{accountId}");
				}
				if(chargeApply.getAdmin()!=null&& !chargeApply.getAdmin().equals("")){
					VALUES("admin","#{admin}");
				}
				if(chargeApply.getReviewTime()!=null){
					VALUES("reviewTime","#{reviewTime}");
				}
				if(chargeApply.getApplyTime()!=null){
					VALUES("applyTime","#{applyTime}");
				}
				if(chargeApply.getAccountName()!=null&& !chargeApply.getAccountName().equals("")){
					VALUES("accountName","#{accountName}");
				}
				if(chargeApply.getBankName()!=null&& !chargeApply.getBankName().equals("")){
					VALUES("bankName","#{bankName}");
				}
				if(chargeApply.getRemark()!=null&& !chargeApply.getRemark().equals("")){
					VALUES("remark","#{remark}");
				}
			}
		}.toString();
	}
	
	public String updateChargeApply(final ChargeApply chargeApply){
		return new SQL(){
			{
				UPDATE(Constants.CHARGEAPPLYTABLE);
				if(chargeApply.getApplyId()!=null&& !chargeApply.getApplyId().equals("")){
					SET("applyId=#{applyId}");
				}
				if(chargeApply.getUserId()!=null&& !chargeApply.getUserId().equals("")){
					SET("userId=#{userId}");
				}
				if(chargeApply.getUserName()!=null&& !chargeApply.getUserName().equals("")){
					SET("userName=#{userName}");
				}
				if(chargeApply.getAmount()!=null){
					SET("amount=#{amount}");
				}
				if(chargeApply.getType()!=null){
					SET("type=#{type}");
				}
				if(chargeApply.getState()!=null){
					SET("state=#{state}");
				}
				if(chargeApply.getAccountId()!=null&& !chargeApply.getAccountId().equals("")){
					SET("accountId=#{accountId}");
				}
				if(chargeApply.getAdmin()!=null&& !chargeApply.getAdmin().equals("")){
					SET("admin=#{admin}");
				}
				if(chargeApply.getReviewTime()!=null){
					SET("reviewTime=#{reviewTime}");
				}
				if(chargeApply.getApplyTime()!=null){
					SET("applyTime=#{applyTime}");
				}
				if(chargeApply.getAccountName()!=null&& !chargeApply.getAccountName().equals("")){
					SET("accountName=#{accountName}");
				}
				if(chargeApply.getBankName()!=null&& !chargeApply.getBankName().equals("")){
					SET("bankName=#{bankName}");
				}
				if(chargeApply.getRemark()!=null&& !chargeApply.getRemark().equals("")){
					SET("remark=#{remark}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
