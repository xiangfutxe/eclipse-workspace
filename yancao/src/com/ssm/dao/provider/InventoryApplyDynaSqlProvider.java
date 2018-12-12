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
				if(inventoryApply.getApplyId()!=null && !inventoryApply.getApplyId().equals("")){
					WHERE(" apply_id LIKE CONCAT ('%',#{inventoryApply.applyId},'%')");
				}
				if(inventoryApply.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{inventoryApply.state},'%')");
				}
				if(inventoryApply.getType()!=null){
					WHERE(" type LIKE CONCAT ('%',#{inventoryApply.type},'%')");
				}
				if(inventoryApply.getPayType()!=null){
					WHERE(" pay_type LIKE CONCAT ('%',#{inventoryApply.payType},'%')");
				}
				if(inventoryApply.getReviewerId()!=null){
					WHERE(" reviewer_id LIKE CONCAT ('%',#{inventoryApply.reviewerId},'%')");
				}
				if(inventoryApply.getStartTime()!=null){
					WHERE("  entry_time >= #{inventoryApply.startTime}");
				}
				if(inventoryApply.getEndTime()!=null){
					WHERE("  entry_time <= #{inventoryApply.endTime}");
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
					if(inventoryApply.getApplyId()!=null && !inventoryApply.getApplyId().equals("")){
						WHERE(" apply_id LIKE CONCAT ('%',#{inventoryApply.applyId},'%')");
					}
					if(inventoryApply.getState()!=null ){
						WHERE(" state LIKE CONCAT ('%',#{inventoryApply.state},'%')");
					}
					if(inventoryApply.getType()!=null ){
						WHERE(" type LIKE CONCAT ('%',#{inventoryApply.type},'%')");
					}
					if(inventoryApply.getPayType()!=null){
						WHERE(" pay_type LIKE CONCAT ('%',#{inventoryApply.payType},'%')");
					}
					if(inventoryApply.getReviewerId()!=null){
						WHERE(" reviewer_id LIKE CONCAT ('%',#{inventoryApply.reviewerId},'%')");
					}
					if(inventoryApply.getStartTime()!=null){
						WHERE("  entry_time >= #{inventoryApply.startTime}");
					}
					if(inventoryApply.getEndTime()!=null){
						WHERE("  entry_time <= #{inventoryApply.endTime}");
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
					VALUES("apply_id","#{applyId}");
				}
				if(inventoryApply.getRemark()!=null&& !inventoryApply.getRemark().equals("")){
					VALUES("remark","#{remark}");
				}
				if(inventoryApply.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
				if(inventoryApply.getState()!=null){
					VALUES("state","#{state}");
				}
				if(inventoryApply.getType()!=null){
					VALUES("type","#{type}");
				}
				if(inventoryApply.getOperatorId()!=null){
					VALUES("operator_id","#{operatorId}");
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
					SET(" apply_id=#{applyId}");
				}
				if(inventoryApply.getEntryTime()!=null){
					SET(" entry_time=#{entryTime}");
				}
				if(inventoryApply.getReviewTime()!=null){
					SET(" review_time=#{reviewTime}");
				}
				if(inventoryApply.getOperatorId()!=null){
					SET(" operator_id=#{operatorId}");
				}
				if(inventoryApply.getReviewerId()!=null){
					SET(" reviewer_id=#{reviewerId}");
				}
				if(inventoryApply.getState()!=null){
					SET(" state=#{state}");
				}
				if(inventoryApply.getRemark()!=null&& !inventoryApply.getRemark().equals("")){
					SET(" remark=#{remark}");
				}
				if(inventoryApply.getType()!=null){
					SET(" type=#{type}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
