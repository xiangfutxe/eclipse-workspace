package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.OrderDetailProductDynaSqlProvider;
import com.ssm.pojo.OrderDetail;
import com.ssm.pojo.OrderDetailProduct;
import com.ssm.utils.Constants;
public interface OrderDetailProductMapper {
	
	@Select("select * from "+Constants.ORDERDETAILPRODUCTTABLE+" where orderId=#{orderId}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="od_id", property="odId"),
		@Result(column="orderId", property="orderId"),
		@Result(column="pid", property="pid"),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="type", property="type"),
		@Result(column="productPrice", property="productPrice"),
		@Result(column="productPv", property="productPv"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="num", property="num"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<OrderDetailProduct> selectByOrderId(String orderId);
	
	@Select("select * from "+Constants.ORDERDETAILPRODUCTTABLE+" where orderId=#{orderId} for update")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="od_id", property="odId"),
		@Result(column="orderId", property="orderId"),
		@Result(column="pid", property="pid"),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="type", property="type"),
		@Result(column="productPrice", property="productPrice"),
		@Result(column="productPv", property="productPv"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="num", property="num"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<OrderDetailProduct> selectByOrderIdForUpdate(String orderId);
	
	@Select("select * from "+Constants.ORDERDETAILPRODUCTTABLE+" where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="od_id", property="odId"),
		@Result(column="orderId", property="orderId"),
		@Result(column="pid", property="pid"),
		@Result(column="productId", property="productId"),
		@Result(column="productName", property="productName"),
		@Result(column="productType", property="productType"),
		@Result(column="specification", property="specification"),
		@Result(column="type", property="type"),
		@Result(column="productPrice", property="productPrice"),
		@Result(column="productPv", property="productPv"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="num", property="num"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	OrderDetailProduct selectById(Integer id);
	
	@Delete("delete from "+Constants.ORDERDETAILPRODUCTTABLE+" where id=#{id}")
	Integer deleteById(Integer id);
	
	@Delete("delete from "+Constants.ORDERDETAILPRODUCTTABLE+" where orderId=#{orderId}")
	Integer deleteByOrderId(String orderId);
	
	@InsertProvider(type=OrderDetailProductDynaSqlProvider.class,method="updateNumByOrderId")
	Integer updateNumByOrderId(Integer num,Integer orderId);
	
	@InsertProvider(type=OrderDetailProductDynaSqlProvider.class,method="insertOrderDetailProduct")
	void save(OrderDetail orderDetail);
	

	
	@InsertProvider(type = OrderDetailProductDynaSqlProvider.class, method = "insertAll")  
	Integer insertAll(Map<String,Object> params); 
	
	@InsertProvider(type=OrderDetailProductDynaSqlProvider.class,method="update")
	Integer update(OrderDetailProduct OrderDetailProduct);

}
