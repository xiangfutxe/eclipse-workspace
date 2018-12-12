package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.pojo.OrderDetail;
import com.ssm.utils.Constants;

public class OrderDeliveryDetailDynaSqlProvider {
	
	

	
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO order_delivery_detail");  
        sb.append("(id,order_id,product_id,product_name,pid,specification,product_price,price,num) ");  
        sb.append("VALUES "); 
        if(params.get("list")!=null){
        List<OrderDeliveryDetail> list = (List<OrderDeliveryDetail>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null, #'{'list[{0}].orderId},#'{'list[{0}].productId},#'{'list[{0}].productName},#'{'list[{0}].pid}"
        		+ ",#'{'list[{0}].specification},#'{'list[{0}].productPrice}"
        		+ ",#'{'list[{0}].price},#'{'list[{0}].num})");  
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
