package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.pojo.OrderDetail;
import com.ssm.utils.Constants;

public class OrderDeliveryDetailDynaSqlProvider {
	
	
	public String insertOrderDeliveryDetail(final OrderDeliveryDetail OrderDeliveryDetail){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.ORDERDELIVERYDETAILTABLE);
				if(OrderDeliveryDetail.getPid()!=null&& !OrderDeliveryDetail.getPid().equals("")){
					VALUES("pid","#{pid}");
				}
				if(OrderDeliveryDetail.getProductId()!=null&& !OrderDeliveryDetail.getProductId().equals("")){
					VALUES("productId","#{productId}");
				}
				if(OrderDeliveryDetail.getProductName()!=null&& !OrderDeliveryDetail.getProductName().equals("")){
					VALUES("productName","#{productName}");
				}
				if(OrderDeliveryDetail.getProductType()!=null&& !OrderDeliveryDetail.getProductType().equals("")){
					VALUES("productType","#{productType}");
				}
				if(OrderDeliveryDetail.getSpecification()!=null&& !OrderDeliveryDetail.getSpecification().equals("")){
					VALUES("specification","#{specification}");
				}
				if(OrderDeliveryDetail.getType()!=null){
					VALUES("type","#{type}");
				}
				if(OrderDeliveryDetail.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(OrderDeliveryDetail.getPv()!=null){
					VALUES("pv","#{pv}");
				}
				if(OrderDeliveryDetail.getProductPrice()!=null){
					VALUES("productPrice","#{productPrice}");
				}
				if(OrderDeliveryDetail.getProductPv()!=null){
					VALUES("productPv","#{productPv}");
				}
				if(OrderDeliveryDetail.getNum()!=null){
					VALUES("num","#{num}");
				}
			}
		}.toString();
	}
	
public String update(final OrderDeliveryDetail OrderDetail){
		
		return new SQL(){
			{
				UPDATE(Constants.ORDERDELIVERYDETAILTABLE);
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
        sb.append("INSERT INTO order_delivery_detail");  
        sb.append("(id,oid,orderId,productId,productName,pid,productType,specification,type,productPrice,productPv,price,pv,num) ");  
        sb.append("VALUES "); 
        if(params.get("list")!=null){
        List<OrderDeliveryDetail> list = (List<OrderDeliveryDetail>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null, #'{'list[{0}].oid}, #'{'list[{0}].orderId},#'{'list[{0}].productId},#'{'list[{0}].productName},#'{'list[{0}].pid},#'{'list[{0}].productType}"
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
