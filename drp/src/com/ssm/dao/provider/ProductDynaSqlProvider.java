package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Product;
import com.ssm.utils.Constants;

public class ProductDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.PRODUCTTABLE);
			if(params.get("product")!=null){
				 Product product = (Product) params.get("product");
				if(product.getProductId()!=null && !product.getProductId().equals("")){
					WHERE(" productId LIKE CONCAT ('%',#{product.productId},'%')");
				}
				if(product.getProductName()!=null && !product.getProductName().equals("")){
					WHERE(" productName LIKE CONCAT ('%',#{product.productName},'%')");
				}
				if(product.getProductType()!=null && !product.getProductType().equals("")){
					WHERE(" productType LIKE CONCAT ('%',#{product.productType},'%')");
				}
				if(product.getType()!=null &&product.getType()!=0){
					WHERE(" type LIKE CONCAT ('%',#{product.type},'%')");
				}
				if(product.getIsHide()!=null){
					WHERE(" is_hide LIKE CONCAT ('%',#{product.isHide},'%')");
				}
				if(product.getState()!=null&&!product.getState().equals("")){
					WHERE(" state LIKE CONCAT ('%',#{product.state},'%')");
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
			FROM(Constants.PRODUCTTABLE);
			if(params.get("product")!=null){
				 Product product = (Product) params.get("product");
				if(product.getProductId()!=null && !product.getProductId().equals("")){
					WHERE(" productId LIKE CONCAT ('%',#{product.productId},'%')");
				}
				if(product.getProductName()!=null && !product.getProductName().equals("")){
					WHERE(" productName LIKE CONCAT ('%',#{product.productName},'%')");
				}
				if(product.getProductType()!=null && !product.getProductType().equals("")){
					WHERE(" productType LIKE CONCAT ('%',#{product.productType},'%')");
				}
				if(product.getType()!=null){
					WHERE(" type LIKE CONCAT ('%',#{product.type},'%')");
				}
				if(product.getIsHide()!=null){
					WHERE(" is_hide LIKE CONCAT ('%',#{product.isHide},'%')");
				}
				if(product.getState()!=null&&!product.getState().equals("")){
					WHERE(" state LIKE CONCAT ('%',#{product.state},'%')");
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
				FROM(Constants.PRODUCTTABLE);
				if(params.get("product")!=null){
					 Product product = (Product) params.get("product");
					if(product.getProductId()!=null && !product.getProductId().equals("")){
						WHERE(" productId LIKE CONCAT ('%',#{product.productId},'%')");
					}
					if(product.getProductName()!=null && !product.getProductName().equals("")){
						WHERE(" productName LIKE CONCAT ('%',#{product.productName},'%')");
					}
					if(product.getProductType()!=null && !product.getProductType().equals("")){
						WHERE(" productType LIKE CONCAT ('%',#{product.productType},'%')");
					}
					if(product.getType()!=null &&product.getType()!=0){
						WHERE(" type LIKE CONCAT ('%',#{product.type},'%')");
					}
					if(product.getIsHide()!=null){
						WHERE(" is_hide LIKE CONCAT ('%',#{product.isHide},'%')");
					}
					if(product.getState()!=null&&!product.getState().equals("")){
						WHERE(" state LIKE CONCAT ('%',#{product.state},'%')");
					}
					
				}
				
			}
		}.toString();
	}
	
	public String insertProduct(final Product product){
		
		String sql = new SQL(){
			{
				INSERT_INTO(Constants.PRODUCTTABLE);
				if(product.getProductId()!=null&& !product.getProductId().equals("")){
					VALUES("productId","#{productId}");
				}
				if(product.getProductName()!=null&& !product.getProductName().equals("")){
					VALUES("productName","#{productName}");
				}
				if(product.getProductType()!=null&& !product.getProductType().equals("")){
					VALUES("productType","#{productType}");
				}
				if(product.getSpecification()!=null&& !product.getSpecification().equals("")){
					VALUES("specification","#{specification}");
				}
				if(product.getFeatures()!=null&& !product.getFeatures().equals("")){
					VALUES("features","#{features}");
				}
				if(product.getState()!=null&& !product.getState().equals("")){
					VALUES("state","#{state}");
				}
				if(product.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(product.getPv()!=null){
					VALUES("pv","#{pv}");
				}
				if(product.getNum()!=null){
					VALUES("num","#{num}");
				}
				if(product.getTotalNum()!=null){
					VALUES("totalNum","#{totalNum}");
				}
				if(product.getIsHide()!=null){
					VALUES("is_hide","#{isHide}");
				}
				if(product.getPriceCy()!=null){
					VALUES("price_cy","#{priceCy}");
				}
				if(product.getPvCy()!=null){
					VALUES("pv_cy","#{pvCy}");
				}
				if(product.getGiveNum()!=null){
					VALUES("give_num","#{giveNum}");
				}
				if(product.getMaxNum()!=null){
					VALUES("max_num","#{maxNum}");
				}
				if(product.getGiveType()!=null){
					VALUES("give_type","#{giveType}");
				}
				if(product.getType()!=null){
					VALUES("type","#{type}");
				}
				if(product.getEntryTime()!=null ){
					VALUES("entryTime","#{entryTime}");
				}
				if(product.getEndTime()!=null ){
					VALUES("endTime","#{endTime}");
				}
			}
		}.toString();
		
		System.out.println(sql);
		return sql;
	}
	
	public String updateProduct(final Product product){
		String sql = new SQL(){
			{
				UPDATE(Constants.PRODUCTTABLE);
				if(product.getProductId()!=null&& !product.getProductId().equals("")){
					SET("productId=#{productId}");
				}
				if(product.getProductName()!=null&& !product.getProductName().equals("")){
					SET("productName=#{productName}");
				}
				if(product.getProductType()!=null&& !product.getProductType().equals("")){
					SET("productType=#{productType}");
				}
				if(product.getSpecification()!=null&& !product.getSpecification().equals("")){
					SET("specification=#{specification}");
				}
				if(product.getFeatures()!=null&& !product.getFeatures().equals("")){
					SET("features=#{features}");
				}
				if(product.getState()!=null&& !product.getState().equals("")){
					SET("state=#{state}");
				}
				if(product.getPrice()!=null){
					SET("price=#{price}");
				}
				if(product.getPv()!=null){
					SET("pv=#{pv}");
				}
				if(product.getNum()!=null){
					SET("num=#{num}");
				}
				if(product.getType()!=null){
					SET("type=#{type}");
				}
				if(product.getTotalNum()!=null){
					SET("totalNum=#{totalNum}");
				}
				if(product.getIsHide()!=null){
					SET("is_hide=#{isHide}");
				}
				if(product.getPriceCy()!=null){
					SET("price_cy=#{priceCy}");
				}
				if(product.getPvCy()!=null){
					SET("pv_cy=#{pvCy}");
				}
				if(product.getGiveNum()!=null){
					SET("give_num=#{giveNum}");
				}
				if(product.getMaxNum()!=null){
					SET("max_num=#{maxNum}");
				}
				if(product.getGiveType()!=null){
					SET("give_type=#{giveType}");
				}
				if(product.getGiveId()!=null){
					SET("give_id=#{giveId}");
				}
				if(product.getGiveProductId()!=null&& !product.getGiveProductId().equals("")){
					SET("give_product_id=#{giveProductId}");
				}
				if(product.getGiveId()!=null){
					SET("give_id=#{giveId}");
				}
				if(product.getGiveProductName()!=null&& !product.getGiveProductName().equals("")){
					SET("give_product_name=#{giveProductName}");
				}
				if(product.getEndTime()!=null ){
					SET("endTime=#{endTime}");
				}
				if(product.getIsHide()!=null){
					SET("is_hide=#{isHide}");
				}
				if(product.getImageUrl()!=null){
					SET("imageUrl=#{imageUrl}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
		return sql;
	}
	
	public String updateNum(String productId,Integer num){
		String sql ="update product set num=num+'"+num+"',totalNum=totalNum+'"+num+"' where productId='"+productId+"'";
		return sql;
	}
	
	public String updateSubNum(String productId,Integer num){
		String sql ="update product set num=num-'"+num+"' where productId='"+productId+"' and num>="+num;
		return sql;
	}


}
