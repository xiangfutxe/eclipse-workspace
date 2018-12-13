package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.OrderDetailProduct;
import com.ssm.utils.Constants;

public class OrderDetailProductDynaSqlProvider {
	
	public String insertOrderDetailProduct(final OrderDetailProduct OrderDetailProduct){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.ORDERDETAILPRODUCTTABLE);
				if(OrderDetailProduct.getPid()!=null){
					VALUES("pid","#{pid}");
				}
				if(OrderDetailProduct.getOdId()!=null){
					VALUES("od_id","#{odId}");
				}
				if(OrderDetailProduct.getOrderId()!=null&& !OrderDetailProduct.getOrderId().equals("")){
					VALUES("orderId","#{orderId}");
				}
				if(OrderDetailProduct.getProductId()!=null&& !OrderDetailProduct.getProductId().equals("")){
					VALUES("productId","#{productId}");
				}
				if(OrderDetailProduct.getProductName()!=null&& !OrderDetailProduct.getProductName().equals("")){
					VALUES("productName","#{productName}");
				}
				if(OrderDetailProduct.getProductType()!=null&& !OrderDetailProduct.getProductType().equals("")){
					VALUES("productType","#{productType}");
				}
				if(OrderDetailProduct.getSpecification()!=null&& !OrderDetailProduct.getSpecification().equals("")){
					VALUES("specification","#{specification}");
				}
				if(OrderDetailProduct.getType()!=null){
					VALUES("type","#{type}");
				}
				if(OrderDetailProduct.getPrice()!=null){
					VALUES("price","#{price}");
				}
				if(OrderDetailProduct.getPv()!=null){
					VALUES("pv","#{pv}");
				}
				if(OrderDetailProduct.getPriceCy()!=null){
					VALUES("price_cy","#{priceCy}");
				}
				if(OrderDetailProduct.getPv()!=null){
					VALUES("pv_cy","#{pvCy}");
				}
				if(OrderDetailProduct.getTotalPriceCy()!=null){
					VALUES("total_price_cy","#{totalPriceCy}");
				}
				if(OrderDetailProduct.getTotalPvCy()!=null){
					VALUES("total_pv_cy","#{totalPvCy}");
				}
				if(OrderDetailProduct.getProductPrice()!=null){
					VALUES("productPrice","#{productPrice}");
				}
				if(OrderDetailProduct.getProductPv()!=null){
					VALUES("productPv","#{productPv}");
				}
				if(OrderDetailProduct.getNum()!=null){
					VALUES("num","#{num}");
				}
				if(OrderDetailProduct.getDeliveryNum()!=null){
					VALUES("delivery_num","#{deliveryNum}");
				}
			}
		}.toString();
	}
	
public String update(final OrderDetailProduct OrderDetailProduct){
		
		return new SQL(){
			{
				UPDATE(Constants.ORDERDETAILPRODUCTTABLE);
				if(OrderDetailProduct.getPid()!=null){
					SET("pid=#{pid}");
				}
				if(OrderDetailProduct.getOdId()!=null){
					SET("od_id=#{odId}");
				}
				if(OrderDetailProduct.getOrderId()!=null&& !OrderDetailProduct.getOrderId().equals("")){
					SET("orderId=#{orderId}");
				}
				if(OrderDetailProduct.getProductId()!=null&& !OrderDetailProduct.getProductId().equals("")){
					SET("productId=#{productId}");
				}
				if(OrderDetailProduct.getProductName()!=null&& !OrderDetailProduct.getProductName().equals("")){
					SET("productName=#{productName}");
				}
				if(OrderDetailProduct.getProductType()!=null&& !OrderDetailProduct.getProductType().equals("")){
					SET("productType=#{productType}");
				}
				if(OrderDetailProduct.getSpecification()!=null&& !OrderDetailProduct.getSpecification().equals("")){
					SET("specification=#{specification}");
				}
				if(OrderDetailProduct.getType()!=null){
					SET("type=#{type}");
				}
				if(OrderDetailProduct.getPrice()!=null){
					SET("price=#{price}");
				}
				if(OrderDetailProduct.getPv()!=null){
					SET("pv=#{pv}");
				}
				
				if(OrderDetailProduct.getProductPrice()!=null){
					SET("productPrice=#{productPrice}");
				}
				if(OrderDetailProduct.getProductPv()!=null){
					SET("productPv=#{productPv}");
				}
				if(OrderDetailProduct.getNum()!=null){
					SET("num=#{num}");
				}
				if(OrderDetailProduct.getDeliveryNum()!=null){
					SET("delivery_num=#{deliveryNum}");
				}
				WHERE (" id = #{id}");
			}
		}.toString();
	}
	
	@SuppressWarnings("unchecked")
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO "+Constants.ORDERDETAILPRODUCTTABLE);  
        sb.append("(id,od_id,orderId,productId,productName,pid,productType,specification,type,productPrice,productPv,price,pv,num,delivery_num) ");  
        sb.append("VALUES "); 
        if(params.get("list")!=null){
        List<OrderDetailProduct> list = (List<OrderDetailProduct>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null, #'{'list[{0}].odId}, #'{'list[{0}].orderId},#'{'list[{0}].productId},#'{'list[{0}].productName},#'{'list[{0}].pid},#'{'list[{0}].productType}"
        		+ ",#'{'list[{0}].specification},#'{'list[{0}].type},#'{'list[{0}].productPrice}"
        		+ ",#'{'list[{0}].productPv},#'{'list[{0}].price},#'{'list[{0}].pv}"
        		+ ",#'{'list[{0}].num},#'{'list[{0}].deliveryNum})");  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < list.size() - 1) {  
                sb.append(",");  
            }  
        }  
		}
        return sb.toString();  
    }  

	
	public String updateNumByOrderId(Integer num,Integer orderId) {  
       String sql = "update order_detail_product  set delivery_num = delivery_num - "+num+" where delivery_num>="+num+" and id='"+orderId+"'";
       return sql;
    }  
}
