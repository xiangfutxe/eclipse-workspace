package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Product;
import com.ssm.pojo.ProductStock;
import com.ssm.utils.Constants;

public class ProductStockDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.PRODUCTSTOCKTABLE);
			if(params.get("product")!=null){
				 ProductStock product = (ProductStock) params.get("product");
				if(product.getProductId()!=null && !product.getProductId().equals("")){
					WHERE(" product_id LIKE CONCAT ('%',#{product.productId},'%')");
				}
				if(product.getProductName()!=null && !product.getProductName().equals("")){
					WHERE(" product_name LIKE CONCAT ('%',#{product.productName},'%')");
				}
				if(product.getUserId()!=null && !product.getUserId().equals("")){
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
			FROM(Constants.PRODUCTSTOCKTABLE);
			if(params.get("product")!=null){
				 ProductStock product = (ProductStock) params.get("product");
					if(product.getProductId()!=null && !product.getProductId().equals("")){
						WHERE(" product_id LIKE CONCAT ('%',#{product.productId},'%')");
					}
					if(product.getProductName()!=null && !product.getProductName().equals("")){
						WHERE(" product_name LIKE CONCAT ('%',#{product.productName},'%')");
					}
					if(product.getUserId()!=null && !product.getUserId().equals("")){
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
				FROM(Constants.PRODUCTSTOCKTABLE);
				if(params.get("product")!=null){
					 ProductStock product = (ProductStock) params.get("product");
						if(product.getProductId()!=null && !product.getProductId().equals("")){
							WHERE(" product_id LIKE CONCAT ('%',#{product.productId},'%')");
						}
						if(product.getProductName()!=null && !product.getProductName().equals("")){
							WHERE(" product_name LIKE CONCAT ('%',#{product.productName},'%')");
						}
						if(product.getUserId()!=null && !product.getUserId().equals("")){
							WHERE(" user_id LIKE CONCAT ('%',#{product.userId},'%')");
						}
					
				}
				
			}
		}.toString();
	}
	
	public String insert(final ProductStock product){
		
		String sql = new SQL(){
			{
				INSERT_INTO(Constants.PRODUCTSTOCKTABLE);
				if(product.getPid()!=null){
					VALUES("pid","#{pid}");
				}
				if(product.getProductId()!=null&& !product.getProductId().equals("")){
					VALUES("product_id","#{productId}");
				}
				if(product.getProductName()!=null&& !product.getProductName().equals("")){
					VALUES("product_name","#{productName}");
				}
				if(product.getUid()!=null){
					VALUES("uid","#{uid}");
				}
				if(product.getUserId()!=null&& !product.getUserId().equals("")){
					VALUES("user_id","#{userId}");
				}
				if(product.getUserName()!=null){
					VALUES("user_name","#{userName}");
				}
				if(product.getSpecification()!=null&& !product.getSpecification().equals("")){
					VALUES("specification","#{specification}");
				}
				if(product.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(product.getNum()!=null){
					VALUES("num","#{num}");
				}
				if(product.getTotalNum()!=null){
					VALUES("total_num","#{totalNum}");
				}
				if(product.getImageUrl()!=null&& !product.getImageUrl().equals("")){
					VALUES("image_url","#{imageUrl}");
				}
				if(product.getEntryTime()!=null ){
					VALUES("entry_time","#{entryTime}");
				}
				if(product.getEndTime()!=null ){
					VALUES("end_time","#{endTime}");
				}
			}
		}.toString();
		return sql;
	}
	
	public String update(final ProductStock product){
		String sql = new SQL(){
			{
				UPDATE(Constants.PRODUCTSTOCKTABLE);
				if(product.getProductId()!=null&& !product.getProductId().equals("")){
					SET("product_id=#{productId}");
				}
				if(product.getProductName()!=null&& !product.getProductName().equals("")){
					SET("product_name=#{productName}");
				}
				if(product.getUserId()!=null&& !product.getUserId().equals("")){
					SET("user_id=#{userId}");
				}
				if(product.getUserName()!=null&& !product.getUserName().equals("")){
					SET("user_name=#{userName}");
				}
				if(product.getSpecification()!=null&& !product.getSpecification().equals("")){
					SET("specification=#{specification}");
				}
				if(product.getNum()!=null){
					SET("num=#{num}");
				}
				if(product.getTotalNum()!=null){
					SET("total_num=#{totalNum}");
				}
				
				if(product.getEndTime()!=null ){
					SET("end_time=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
		return sql;
	}
	
	public String updateNum(String productId,String userId,Integer num){
		String sql ="update product_stock set num=num+'"+num+"',total_num=total_num+'"+num+"' where product_id='"+productId+"' and user_id='"+userId+"'";
		return sql;
	}
	
	public String updateSubNum(String productId,String userId,Integer num){
		String sql ="update product_stock set num=num-'"+num+"' where product_id='"+productId+"' and user_id='"+userId+"' and num>="+num;
		return sql;
	}


}
