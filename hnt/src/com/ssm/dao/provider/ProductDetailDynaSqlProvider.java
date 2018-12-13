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
					WHERE(" productId LIKE CONCAT ('%',#{productDetail.productId},'%')");
				}
				if(productDetail.getProductName()!=null && !productDetail.getProductName().equals("")){
					WHERE(" productName LIKE CONCAT ('%',#{productDetail.productName},'%')");
				}
				if(productDetail.getP_id()!=null && productDetail.getP_id()!=0){
					WHERE(" p_id LIKE CONCAT ('%',#{productDetail.p_id},'%')");
				}
				if(productDetail.getpId()!=null &&!productDetail.getpId().equals("")){
					WHERE(" pId LIKE CONCAT ('%',#{productDetail.pId},'%')");
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
					WHERE(" productId =#{productDetail.productId}=");
				}
				if(productDetail.getProductName()!=null && !productDetail.getProductName().equals("")){
					WHERE(" productName =#{productDetail.productName}");
				}
				if(productDetail.getP_id()!=null && productDetail.getP_id()!=0){
					WHERE(" p_id =#{productDetail.p_id}");
				}
				if(productDetail.getpId()!=null &&!productDetail.getpId().equals("")){
					WHERE(" pId =#{productDetail.pId}");
				}
				if(productDetail.getProductType()!=null &&!productDetail.getProductType().equals("")){
					WHERE(" productType LIKE CONCAT ('%',#{productDetail.productType},'%')");
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
						WHERE(" productId LIKE CONCAT ('%',#{productDetail.productId},'%')");
					}
					if(productDetail.getProductName()!=null && !productDetail.getProductName().equals("")){
						WHERE(" productName LIKE CONCAT ('%',#{productDetail.productName},'%')");
					}
					if(productDetail.getP_id()!=null && productDetail.getP_id()!=0){
						WHERE(" p_id LIKE CONCAT ('%',#{productDetail.p_id},'%')");
					}
					if(productDetail.getpId()!=null &&!productDetail.getpId().equals("")){
						WHERE(" pId LIKE CONCAT ('%',#{productDetail.pId},'%')");
					}
				}
				
			}
		}.toString();
	}
	
	public String insertProductDetail(final ProductDetail ProductDetail){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.PRODUCTDETAILTABLE);
				if(ProductDetail.getProductId()!=null&& !ProductDetail.getProductId().equals("")){
					VALUES("productId","#{productId}");
				}
				if(ProductDetail.getProductName()!=null&& !ProductDetail.getProductName().equals("")){
					VALUES("productName","#{productName}");
				}
				if(ProductDetail.getProductType()!=null&& !ProductDetail.getProductType().equals("")){
					VALUES("productType","#{productType}");
				}
				if(ProductDetail.getSpecification()!=null&& !ProductDetail.getSpecification().equals("")){
					VALUES("specification","#{specification}");
				}
				if(ProductDetail.getP_id()!=null&& ProductDetail.getP_id()!=0){
					VALUES("p_id","#{p_id}");
				}
				if(ProductDetail.getpId()!=null&& !ProductDetail.getpId().equals("")){
					VALUES("pId","#{pId}");
				}
				if(ProductDetail.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(ProductDetail.getPv()!=null){
					VALUES("pv","#{pv}");
				}
				if(ProductDetail.getPriceCy()!=null){
					VALUES("price_cy","#{priceCy}");
				}
				if(ProductDetail.getPv()!=null){
					VALUES("pv_cy","#{pvCy}");
				}
				if(ProductDetail.getProductPriceCy()!=null){
					VALUES("productPriceCy","#{productPriceCy}");
				}
				if(ProductDetail.getProductPvCy()!=null){
					VALUES("productPvCy","#{productPvCy}");
				}
				if(ProductDetail.getProductPrice()!=null){
					VALUES("productPrice","#{productPrice}");
				}
				if(ProductDetail.getProductPv()!=null){
					VALUES("productPv","#{productPv}");
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
					SET("productId=#{productDetailId}");
				}
				if(ProductDetail.getProductName()!=null&& !ProductDetail.getProductName().equals("")){
					SET("productName=#{productName}");
				}
				if(ProductDetail.getpId()!=null&& !ProductDetail.getpId().equals("")){
					SET("pId=#{pId}");
				}
				if(ProductDetail.getP_id()!=null&& ProductDetail.getP_id()!=0){
					SET("p_id=#{p_id}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
		return sql;
	}
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO productDetail");  
        sb.append("(id,productId,productName,pId,p_id,productType,specification,productPrice,productPv,price,pv,productPriceCy,productPvCy,price_cy,pv_cy,num,imageUrl) ");  
        sb.append("VALUES "); 
        if(params.get("list")!=null){
        List<ProductDetail> list = (List<ProductDetail>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null, #'{'list[{0}].productId},#'{'list[{0}].productName},#'{'list[{0}].pId},#'{'list[{0}].p_id},#'{'list[{0}].productType}"
        		+ ",#'{'list[{0}].specification},#'{'list[{0}].productPrice}"
        		+ ",#'{'list[{0}].productPv},#'{'list[{0}].price},#'{'list[{0}].pv}"
        		+ ",#'{'list[{0}].productPriceCy},#'{'list[{0}].productPvCy}"
        		+ ",#'{'list[{0}].priceCy},#'{'list[{0}].pvCy},#'{'list[{0}].num}"
        		+ ",#'{'list[{0}].imageUrl})");  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < list.size() - 1) {  
                sb.append(",");  
            }  
        }  
		}
        System.out.println(sb.toString());
        return sb.toString();  
    }  

}
