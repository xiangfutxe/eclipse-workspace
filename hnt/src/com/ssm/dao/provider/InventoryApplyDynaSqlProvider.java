package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.InventoryApply;
import com.ssm.utils.Constants;

public class InventoryApplyDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.INVENTORYAPPLYTABLE);
			if(params.get("inventoryApply")!=null){
				InventoryApply inventoryApply = (InventoryApply) params.get("inventoryApply");
				if(inventoryApply.getInventory()!=null && !inventoryApply.getInventory().equals("")){
					WHERE(" inventory LIKE CONCAT ('%',#{inventoryApply.inventory},'%')");
				}
				if(inventoryApply.getState()!=null && !inventoryApply.getState().equals("")){
					WHERE(" state LIKE CONCAT ('%',#{inventoryApply.state},'%')");
				}
				if(inventoryApply.getType()!=null && !inventoryApply.getType().equals("")){
					WHERE(" type LIKE CONCAT ('%',#{inventoryApply.type},'%')");
				}
				if(inventoryApply.getPayType()!=null && !inventoryApply.getPayType().equals("")){
					WHERE(" pay_type LIKE CONCAT ('%',#{inventoryApply.payType},'%')");
				}
				if(inventoryApply.getStartTime()!=null){
					WHERE("  time >= #{inventoryApply.startTime}");
				}
				if(inventoryApply.getEndTime()!=null){
					WHERE("  time <= #{inventoryApply.endTime}");
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
				FROM(Constants.INVENTORYAPPLYTABLE);
				if(params.get("inventoryApply")!=null){
					InventoryApply inventoryApply = (InventoryApply) params.get("inventoryApply");
					if(inventoryApply.getInventory()!=null && !inventoryApply.getInventory().equals("")){
						WHERE(" inventory LIKE CONCAT ('%',#{inventoryApply.inventory},'%')");
					}
					if(inventoryApply.getState()!=null && !inventoryApply.getState().equals("")){
						WHERE(" state LIKE CONCAT ('%',#{inventoryApply.state},'%')");
					}
					if(inventoryApply.getType()!=null && !inventoryApply.getType().equals("")){
						WHERE(" type LIKE CONCAT ('%',#{inventoryApply.type},'%')");
					}
					if(inventoryApply.getPayType()!=null && !inventoryApply.getPayType().equals("")){
						WHERE(" pay_type LIKE CONCAT ('%',#{inventoryApply.payType},'%')");
					}
					if(inventoryApply.getStartTime()!=null){
						WHERE("  time >= #{inventoryApply.startTime}");
					}
					if(inventoryApply.getEndTime()!=null){
						WHERE("  time <= #{inventoryApply.endTime}");
					}
				}
				}
		}.toString();
	}
	
	public String insertInventoryApply(final InventoryApply inventoryApply){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.INVENTORYAPPLYTABLE);
				if(inventoryApply.getInventory()!=null&& !inventoryApply.getInventory().equals("")){
					VALUES("inventory","#{inventory}");
				}
				if(inventoryApply.getPayType()!=null){
					VALUES("pay_type","#{payType}");
				}
				if(inventoryApply.getApplyId()!=null&& !inventoryApply.getApplyId().equals("")){
					VALUES("applyId","#{applyId}");
				}
				if(inventoryApply.getTime()!=null&& !inventoryApply.getTime().equals("")){
					VALUES("time","#{time}");
				}
				if(inventoryApply.getState()!=null){
					VALUES("state","#{state}");
				}
				if(inventoryApply.getType()!=null){
					VALUES("type","#{type}");
				}
				if(inventoryApply.getAdminByOperatorId()!=null){
					VALUES("adminByOperatorId","#{adminByOperatorId}");
				}
			}
		}.toString();
	}
	
	public String updateInventoryApply(final InventoryApply inventoryApply){
		return new SQL(){
			{
				UPDATE(Constants.INVENTORYAPPLYTABLE);
				if(inventoryApply.getInventory()!=null&& !inventoryApply.getInventory().equals("")){
					SET(" inventory=#{inventory}");
				}
				if(inventoryApply.getPayType()!=null){
					SET(" pay_type=#{payType}");
				}
				if(inventoryApply.getApplyId()!=null&& !inventoryApply.getApplyId().equals("")){
					SET(" applyId=#{applyId}");
				}
				if(inventoryApply.getTime()!=null){
					SET(" time=#{time}");
				}
				if(inventoryApply.getReviewTime()!=null){
					SET(" reviewTime=#{reviewTime}");
				}
				if(inventoryApply.getAdminByOperatorId()!=null){
					SET(" adminByOperatorId=#{adminByOperatorId}");
				}
				if(inventoryApply.getAdminByReviewerId()!=null){
					SET(" adminByReviewerId=#{adminByReviewerId}");
				}
				if(inventoryApply.getState()!=null){
					SET(" state=#{state}");
				}
				if(inventoryApply.getType()!=null){
					SET(" type=#{type}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
