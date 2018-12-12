package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Product;
import com.ssm.pojo.ProductStock;
import com.ssm.pojo.ProductStockTransfers;
import com.ssm.utils.Constants;

public class ProductStockTransfersDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.PRODUCTSTOCKTRANSFERSTABLE);
			if(params.get("transfers")!=null){
				 ProductStockTransfers transfers = (ProductStockTransfers) params.get("transfers");
				if(transfers.getProductId()!=null && !transfers.getProductId().equals("")){
					WHERE(" product_id LIKE CONCAT ('%',#{product.productId},'%')");
				}
				if(transfers.getProductName()!=null && !transfers.getProductName().equals("")){
					WHERE(" product_name LIKE CONCAT ('%',#{product.productName},'%')");
				}
				if(transfers.getUserId()!=null && !transfers.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{product.userId},'%')");
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
			FROM(Constants.PRODUCTSTOCKTRANSFERSTABLE);
			if(params.get("transfers")!=null){
				 ProductStockTransfers transfers = (ProductStockTransfers) params.get("transfers");
					if(transfers.getProductId()!=null && !transfers.getProductId().equals("")){
						WHERE(" product_id LIKE CONCAT ('%',#{product.productId},'%')");
					}
					if(transfers.getProductName()!=null && !transfers.getProductName().equals("")){
						WHERE(" product_name LIKE CONCAT ('%',#{product.productName},'%')");
					}
					if(transfers.getUserId()!=null && !transfers.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{product.userId},'%')");
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
				FROM(Constants.PRODUCTSTOCKTRANSFERSTABLE);
				if(params.get("transfers")!=null){
					ProductStockTransfers transfers = (ProductStockTransfers) params.get("transfers");
						if(transfers.getProductId()!=null && !transfers.getProductId().equals("")){
							WHERE(" product_id LIKE CONCAT ('%',#{product.productId},'%')");
						}
						if(transfers.getProductName()!=null && !transfers.getProductName().equals("")){
							WHERE(" product_name LIKE CONCAT ('%',#{product.productName},'%')");
						}
						if(transfers.getUserId()!=null && !transfers.getUserId().equals("")){
							WHERE(" user_id LIKE CONCAT ('%',#{product.userId},'%')");
						}
					
				}
				
			}
		}.toString();
	}
	
	public String insert(final ProductStockTransfers transfers){
		
		String sql = new SQL(){
			{
				INSERT_INTO(Constants.PRODUCTSTOCKTRANSFERSTABLE);
				if(transfers.getPid()!=null){
					VALUES("pid","#{pid}");
				}
				if(transfers.getProductId()!=null&& !transfers.getProductId().equals("")){
					VALUES("product_id","#{productId}");
				}
				if(transfers.getProductName()!=null&& !transfers.getProductName().equals("")){
					VALUES("product_name","#{productName}");
				}
				if(transfers.getUid()!=null){
					VALUES("uid","#{uid}");
				}
				if(transfers.getUserId()!=null&& !transfers.getUserId().equals("")){
					VALUES("user_id","#{userId}");
				}
				if(transfers.getUserName()!=null){
					VALUES("user_name","#{userName}");
				}
				if(transfers.getUid1()!=null){
					VALUES("uid1","#{uid1}");
				}
				if(transfers.getUserId1()!=null&& !transfers.getUserId1().equals("")){
					VALUES("user_id_1","#{userId1}");
				}
				if(transfers.getUserName1()!=null){
					VALUES("user_name_1","#{userName1}");
				}
				if(transfers.getSpecification()!=null&& !transfers.getSpecification().equals("")){
					VALUES("specification","#{specification}");
				}
				if(transfers.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(transfers.getNum()!=null){
					VALUES("num","#{num}");
				}
				if(transfers.getRemark()!=null){
					VALUES("remark","#{remark}");
				}
				if(transfers.getAdmin()!=null){
					VALUES("admin","#{admin}");
				}
				if(transfers.getEntryTime()!=null ){
					VALUES("entry_time","#{entryTime}");
				}
				if(transfers.getEndTime()!=null ){
					VALUES("end_time","#{endTime}");
				}
			}
		}.toString();
		return sql;
	}
	
	public String update(final ProductStockTransfers transfers){
		String sql = new SQL(){
			{
				UPDATE(Constants.PRODUCTSTOCKTRANSFERSTABLE);
				if(transfers.getPid()!=null){
					SET("pid=#{pid}");
				}
				if(transfers.getProductId()!=null&& !transfers.getProductId().equals("")){
					SET("product_id=#{productId}");
				}
				if(transfers.getProductName()!=null&& !transfers.getProductName().equals("")){
					SET("product_name=#{productName}");
				}
				if(transfers.getUid()!=null){
					SET("uid=#{uid}");
				}
				if(transfers.getUserId()!=null&& !transfers.getUserId().equals("")){
					SET("user_id=#{userId}");
				}
				if(transfers.getUserName()!=null){
					SET("user_name=#{userName}");
				}
				if(transfers.getUid1()!=null){
					SET("uid1=#{uid1}");
				}
				if(transfers.getUserId1()!=null&& !transfers.getUserId1().equals("")){
					SET("user_id_1=#{userId1}");
				}
				if(transfers.getUserName1()!=null){
					SET("user_name_1=#{userName1}");
				}
				if(transfers.getSpecification()!=null){
					SET("specification=#{specification}");
				}
				if(transfers.getPrice()!=null){
					SET("price=#{price}");
				}
				if(transfers.getNum()!=null){
					SET("num=#{num}");
				}
				if(transfers.getEntryTime()!=null ){
					SET("entry_time=#{entryTime}");
				}
				if(transfers.getEndTime()!=null ){
					SET("end_time=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
		return sql;
	}

}
