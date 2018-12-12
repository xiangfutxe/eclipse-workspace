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
					WHERE(" product_id LIKE CONCAT ('%',#{product.productId},'%')");
				}
				if(product.getProductName()!=null && !product.getProductName().equals("")){
					WHERE(" product_name LIKE CONCAT ('%',#{product.productName},'%')");
				}
				if(product.getProductType()!=null && !product.getProductType().equals("")){
					WHERE(" product_type LIKE CONCAT ('%',#{product.productType},'%')");
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
						WHERE(" product_id LIKE CONCAT ('%',#{product.productId},'%')");
					}
					if(product.getProductName()!=null && !product.getProductName().equals("")){
						WHERE(" product_name LIKE CONCAT ('%',#{product.productName},'%')");
					}
					if(product.getProductType()!=null && !product.getProductType().equals("")){
						WHERE(" product_type LIKE CONCAT ('%',#{product.productType},'%')");
					}
					if(product.getProductSort()!=null && !product.getProductSort().equals("")){
						WHERE(" product_sort LIKE CONCAT ('%',#{product.productSort},'%')");
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
							WHERE(" product_id LIKE CONCAT ('%',#{product.productId},'%')");
						}
						if(product.getProductName()!=null && !product.getProductName().equals("")){
							WHERE(" product_name LIKE CONCAT ('%',#{product.productName},'%')");
						}
						if(product.getProductType()!=null && !product.getProductType().equals("")){
							WHERE(" product_type LIKE CONCAT ('%',#{product.productType},'%')");
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
					VALUES("product_id","#{productId}");
				}
				if(product.getProductName()!=null&& !product.getProductName().equals("")){
					VALUES("product_name","#{productName}");
				}
				if(product.getProductType()!=null&& !product.getProductType().equals("")){
					VALUES("product_type","#{productType}");
				}
				if(product.getProductSort()!=null&& !product.getProductSort().equals("")){
					VALUES("product_sort","#{productSort}");
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
				if(product.getType()!=null){
					VALUES("type","#{type}");
				}
				if(product.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(product.getPrice1()!=null){
					VALUES("price1","#{price1}");
				}
				if(product.getCash()!=null){
					VALUES("cash","#{cash}");
				}
				if(product.getCashNum()!=null){
					VALUES("cash_num","#{cashNum}");
				}
				if(product.getIntegral()!=null){
					VALUES("integral","#{integral}");
				}
				if(product.getNum()!=null){
					VALUES("num","#{num}");
				}
				if(product.getTotalNum()!=null){
					VALUES("total_num","#{totalNum}");
				}
				if(product.getIsHide()!=null){
					VALUES("is_hide","#{isHide}");
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
	
	public String updateProduct(final Product product){
		String sql = new SQL(){
			{
				UPDATE(Constants.PRODUCTTABLE);
				if(product.getProductId()!=null&& !product.getProductId().equals("")){
					SET("product_id=#{productId}");
				}
				if(product.getProductName()!=null&& !product.getProductName().equals("")){
					SET("product_name=#{productName}");
				}
				if(product.getProductType()!=null&& !product.getProductType().equals("")){
					SET("product_type=#{productType}");
				}
				if(product.getProductSort()!=null&& !product.getProductSort().equals("")){
					SET("product_sort=#{productSort}");
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
				if(product.getPrice1()!=null){
					SET("price1=#{price1}");
				}
				if(product.getCash()!=null){
					SET("cash=#{cash}");
				}
				if(product.getCashNum()!=null){
					SET("cash_num=#{cashNum}");
				}
				if(product.getIntegral()!=null){
					SET("integral=#{integral}");
				}
				if(product.getNum()!=null){
					SET("num=#{num}");
				}
				if(product.getTotalNum()!=null){
					SET("total_num=#{totalNum}");
				}
				if(product.getIsHide()!=null){
					SET("is_hide=#{isHide}");
				}
				if(product.getEndTime()!=null ){
					SET("end_time=#{endTime}");
				}
				if(product.getIsHide()!=null){
					SET("is_hide=#{isHide}");
				}
				if(product.getImageUrl()!=null){
					SET("image_url=#{imageUrl}");
				}
				if(product.getImageUrl1()!=null){
					SET("image_url_1=#{imageUrl1}");
				}
				if(product.getImageUrl2()!=null){
					SET("image_url_2=#{imageUrl2}");
				}
				if(product.getImageUrl3()!=null){
					SET("image_url_3=#{imageUrl3}");
				}
				if(product.getImageUrl4()!=null){
					SET("image_url_4=#{imageUrl4}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
		return sql;
	}
	
	public String updateNum(String productId,Integer num){
		String sql ="update product set num=num+'"+num+"',total_num=total_num+'"+num+"' where product_id='"+productId+"'";
		return sql;
	}
	
	public String updateSubNum(String productId,Integer num){
		String sql ="update product set num=num-'"+num+"' where product_id='"+productId+"' and num>="+num;
		return sql;
	}


}
