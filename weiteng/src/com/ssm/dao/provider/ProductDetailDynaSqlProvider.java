package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.ProductDetail;
import com.ssm.pojo.User;
import com.ssm.utils.Constants;

public class ProductDetailDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.PRODUCTDETAILTABLE);
			if(params.get("productDetail")!=null){
				 ProductDetail productDetail = (ProductDetail) params.get("productDetail");
				if(productDetail.getProductId()!=null && !productDetail.getProductId().equals("")){
					WHERE(" product_id LIKE CONCAT ('%',#{productDetail.productId},'%')");
				}
				if(productDetail.getPid()!=null && !productDetail.getPid().equals("")){
					WHERE(" pid LIKE CONCAT ('%',#{productDetail.pid},'%')");
				}
				if(productDetail.getProductName()!=null && !productDetail.getProductName().equals("")){
					WHERE(" product_name LIKE CONCAT ('%',#{productDetail.productName},'%')");
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
			FROM(Constants.PRODUCTDETAILTABLE);
			if(params.get("productDetail")!=null){
				 ProductDetail productDetail = (ProductDetail) params.get("productDetail");
				if(productDetail.getProductId()!=null && !productDetail.getProductId().equals("")){
					WHERE(" product_id =#{productDetail.productId}=");
				}
				if(productDetail.getPid()!=null && !productDetail.getPid().equals("")){
					WHERE(" pid LIKE CONCAT ('%',#{productDetail.pid},'%')");
				}
				if(productDetail.getProductName()!=null && !productDetail.getProductName().equals("")){
					WHERE(" product_name =#{productDetail.productName}");
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
				FROM(Constants.PRODUCTDETAILTABLE);
				if(params.get("productDetail")!=null){
					 ProductDetail productDetail = (ProductDetail) params.get("productDetail");
					if(productDetail.getProductId()!=null && !productDetail.getProductId().equals("")){
						WHERE(" product_id LIKE CONCAT ('%',#{productDetail.productId},'%')");
					}
					if(productDetail.getPid()!=null && !productDetail.getPid().equals("")){
						WHERE(" pid LIKE CONCAT ('%',#{productDetail.pid},'%')");
					}
					if(productDetail.getProductName()!=null && !productDetail.getProductName().equals("")){
						WHERE(" product_name LIKE CONCAT ('%',#{productDetail.productName},'%')");
					}
				}
				
			}
		}.toString();
	}
	
	public String insertProductDetail(final ProductDetail ProductDetail){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.PRODUCTDETAILTABLE);
				if(ProductDetail.getPid()!=null&& !ProductDetail.getPid().equals("")){
					VALUES("pid","#{pid}");
				}
				if(ProductDetail.getProductId()!=null&& !ProductDetail.getProductId().equals("")){
					VALUES("product_id","#{productId}");
				}
				if(ProductDetail.getProductName()!=null&& !ProductDetail.getProductName().equals("")){
					VALUES("product_name","#{productName}");
				}
				if(ProductDetail.getSpecification()!=null&& !ProductDetail.getSpecification().equals("")){
					VALUES("specification","#{specification}");
				}
				
				if(ProductDetail.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(ProductDetail.getProductPrice()!=null){
					VALUES("product_price","#{productPrice}");
				}
				if(ProductDetail.getNum()!=null){
					VALUES("num","#{num}");
				}
			}
		}.toString();
	}
	
	public String updateProductDetail(final ProductDetail ProductDetail){
		String sql = new SQL(){
			{
				UPDATE(Constants.PRODUCTDETAILTABLE);
				if(ProductDetail.getProductId()!=null&& !ProductDetail.getProductId().equals("")){
					SET("product_id=#{productDetailId}");
				}
				if(ProductDetail.getProductName()!=null&& !ProductDetail.getProductName().equals("")){
					SET("product_name=#{productName}");
				}
				if(ProductDetail.getNum()!=null){
					SET("num=#{num}");
				}
				
				WHERE(" id=#{id}");
			}
		}.toString();
		return sql;
	}
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO product_detail");  
        sb.append("(id,pid,product_id,product_name,specification,product_price,price,num) ");  
        sb.append("VALUES "); 
        if(params.get("list")!=null){
        List<ProductDetail> list = (List<ProductDetail>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null,#'{'list[{0}].pid}, #'{'list[{0}].productId},#'{'list[{0}].productName},"
        		+ "#'{'list[{0}].specification},#'{'list[{0}].productPrice}"
        		+ ",#'{'list[{0}].price},#'{'list[{0}].num})");  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < list.size() - 1) {  
                sb.append(",");  
            }  
        }  
		}
        return sb.toString();  
    }  

}
