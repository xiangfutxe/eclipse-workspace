package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.OrderDetail;
import com.ssm.utils.Constants;

public class OrderDetailDynaSqlProvider {
	
	
	public String insertOrderDetail(final OrderDetail OrderDetail){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.ORDERDETAILTABLE);
				if(OrderDetail.getPid()!=null&& !OrderDetail.getPid().equals("")){
					VALUES("pid","#{pid}");
				}
				if(OrderDetail.getProductId()!=null&& !OrderDetail.getProductId().equals("")){
					VALUES("productId","#{productId}");
				}
				if(OrderDetail.getProductName()!=null&& !OrderDetail.getProductName().equals("")){
					VALUES("productName","#{productName}");
				}
				if(OrderDetail.getProductType()!=null&& !OrderDetail.getProductType().equals("")){
					VALUES("productType","#{productType}");
				}
				if(OrderDetail.getSpecification()!=null&& !OrderDetail.getSpecification().equals("")){
					VALUES("specification","#{specification}");
				}
				if(OrderDetail.getType()!=null){
					VALUES("type","#{type}");
				}
				if(OrderDetail.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(OrderDetail.getPv()!=null){
					VALUES("pv","#{pv}");
				}
				if(OrderDetail.getPriceCy()!=null){
					VALUES("price_cy","#{priceCy}");
				}
				if(OrderDetail.getPv()!=null){
					VALUES("pv_cy","#{pvCy}");
				}
				if(OrderDetail.getTotalPriceCy()!=null){
					VALUES("total_price_cy","#{totalPriceCy}");
				}
				if(OrderDetail.getTotalPvCy()!=null){
					VALUES("total_pv_cy","#{totalPvCy}");
				}
				if(OrderDetail.getProductPrice()!=null){
					VALUES("productPrice","#{productPrice}");
				}
				if(OrderDetail.getProductPv()!=null){
					VALUES("productPv","#{productPv}");
				}
				if(OrderDetail.getNum()!=null){
					VALUES("num","#{num}");
				}
			}
		}.toString();
	}
	
public String UpdateOrderDetail(final OrderDetail OrderDetail){
		
		return new SQL(){
			{
				UPDATE(Constants.ORDERDETAILTABLE);
				if(OrderDetail.getPid()!=null&& !OrderDetail.getPid().equals("")){
					SET("pid=#{pid}");
				}
				if(OrderDetail.getProductId()!=null&& !OrderDetail.getProductId().equals("")){
					SET("productId=#{productId}");
				}
				if(OrderDetail.getProductName()!=null&& !OrderDetail.getProductName().equals("")){
					SET("productName=#{productName}");
				}
				if(OrderDetail.getProductType()!=null&& !OrderDetail.getProductType().equals("")){
					SET("productType=#{productType}");
				}
				if(OrderDetail.getSpecification()!=null&& !OrderDetail.getSpecification().equals("")){
					SET("specification=#{specification}");
				}
				if(OrderDetail.getType()!=null){
					SET("type=#{type}");
				}
				if(OrderDetail.getPrice()!=null){
					SET("price=#{price}");
				}
				if(OrderDetail.getPv()!=null){
					SET("pv=#{pv}");
				}
				if(OrderDetail.getProductPrice()!=null){
					SET("productPrice=#{productPrice}");
				}
				if(OrderDetail.getProductPv()!=null){
					SET("productPv=#{productPv}");
				}
				if(OrderDetail.getNum()!=null){
					SET("num=#{num}");
				}
				WHERE( " id=#{id}");
			}
		}.toString();
	}
	
	
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO orderDetail");  
        sb.append("(id,orderId,productId,productName,pid,productType,specification,type,productPrice,productPv,price,pv,num) ");  
        sb.append("VALUES "); 
        if(params.get("list")!=null){
        List<OrderDetail> list = (List<OrderDetail>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null, #'{'list[{0}].orderId},#'{'list[{0}].productId},#'{'list[{0}].productName},#'{'list[{0}].pid},#'{'list[{0}].productType}"
        		+ ",#'{'list[{0}].specification},#'{'list[{0}].type},#'{'list[{0}].productPrice}"
        		+ ",#'{'list[{0}].productPv},#'{'list[{0}].price},#'{'list[{0}].pv},#'{'list[{0}].num})");  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
          if(i<list.size()-1){
            	 sb.append(",");  
            }
        }  
		}
        return sb.toString();  
    }  

}
