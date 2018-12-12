package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.OrderDetail;
import com.ssm.utils.Constants;

public class OrderDetailDynaSqlProvider {

	
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO "+Constants.ORDERDETAILTABLE);  
        sb.append("(id,order_id,pid,product_id,product_name,product_type,product_sort,specification,"
        		+ "product_price,product_cash,product_cash_num,product_integral,product_retail_price,"
        		+ "price,cash,cash_num,integral,retail_price,num) ");  
        sb.append("VALUES "); 
        if(params.get("list")!=null){
        List<OrderDetail> list = (List<OrderDetail>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null,#'{'list[{0}].orderId},"
        		+ "#'{'list[{0}].pid},#'{'list[{0}].productId},#'{'list[{0}].productName},#'{'list[{0}].productType},#'{'list[{0}].productSort},#'{'list[{0}].specification},"
        		+ "#'{'list[{0}].productPrice},#'{'list[{0}].productCash},#'{'list[{0}].productCashNum},#'{'list[{0}].productIntegral},#'{'list[{0}].productRetailPrice}"
        		+ ",#'{'list[{0}].price},#'{'list[{0}].cash},#'{'list[{0}].cashNum},#'{'list[{0}].integral},#'{'list[{0}].retailPrice},#'{'list[{0}].num})");  
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
