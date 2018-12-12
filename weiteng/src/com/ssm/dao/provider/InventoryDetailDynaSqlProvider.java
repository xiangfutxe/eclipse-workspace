package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.InventoryDetail;
import com.ssm.utils.Constants;

public class InventoryDetailDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.INVENTORYDETAILTABLE);
			if(params.get("inventoryDetail")!=null){
				InventoryDetail inventoryDetail = (InventoryDetail) params.get("inventoryDetail");
				if(inventoryDetail.getApplyId()!=null && !inventoryDetail.getApplyId().equals("")){
					WHERE(" apply_id LIKE CONCAT ('%',#{InventoryDetail.applyId},'%')");
				}
				if(inventoryDetail.getProductId()!=null && !inventoryDetail.getProductId().equals("")){
					WHERE(" product_id LIKE CONCAT ('%',#{InventoryDetail.productId},'%')");
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
				FROM(Constants.INVENTORYDETAILTABLE);
				if(params.get("inventoryDetail")!=null){
					InventoryDetail inventoryDetail = (InventoryDetail) params.get("inventoryDetail");
					if(inventoryDetail.getApplyId()!=null && !inventoryDetail.getApplyId().equals("")){
						WHERE(" apply_id LIKE CONCAT ('%',#{InventoryDetail.applyId},'%')");
					}
					if(inventoryDetail.getProductId()!=null && !inventoryDetail.getProductId().equals("")){
						WHERE(" product_id LIKE CONCAT ('%',#{InventoryDetail.productId},'%')");
					}
				}
				}
		}.toString();
	}
	
	public String insertInventoryDetail(final InventoryDetail inventoryDetail){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.INVENTORYDETAILTABLE);
				if(inventoryDetail.getApplyId()!=null&& !inventoryDetail.getApplyId().equals("")){
					VALUES("apply_id","#{applyId}");
				}
				if(inventoryDetail.getType()!=null){
					VALUES("type","#{type}");
				}
				if(inventoryDetail.getProductId()!=null&& !inventoryDetail.getProductId().equals("")){
					VALUES("product_id","#{productId}");
				}
				if(inventoryDetail.getProductName()!=null&& !inventoryDetail.getProductName().equals("")){
					VALUES("product_name","#{productName}");
				}
				if(inventoryDetail.getSpecification()!=null&& !inventoryDetail.getSpecification().equals("")){
					VALUES("specification","#{specification}");
				}
				if(inventoryDetail.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(inventoryDetail.getNum()!=null){
					VALUES("num","#{num}");
				}
				if(inventoryDetail.getTotalPrice()!=null){
					VALUES("total_price","#{totalPrice}");
				}
			}
		}.toString();
	}
	
	public String updateInventoryDetail(final InventoryDetail inventoryDetail){
		return new SQL(){
			{
				UPDATE(Constants.INVENTORYDETAILTABLE);
				if(inventoryDetail.getApplyId()!=null&& !inventoryDetail.getApplyId().equals("")){
					SET("apply_id=#{applyId}");
				}
				if(inventoryDetail.getType()!=null){
					SET("type=#{type}");
				}
				if(inventoryDetail.getProductId()!=null&& !inventoryDetail.getProductId().equals("")){
					SET("product_id=#{productId}");
				}
				if(inventoryDetail.getProductName()!=null&& !inventoryDetail.getProductName().equals("")){
					SET("product_name=#{productName}");
				}
				if(inventoryDetail.getPrice()!=null){
					SET("price=#{price}");
				}
				if(inventoryDetail.getNum()!=null){
					SET("num=#{num}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
