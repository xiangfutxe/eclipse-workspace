package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.OrderDeliveryDetailDynaSqlProvider;
import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.utils.Constants;

public interface OrderDeliveryDetailMapper {

	
	@Select("select * from "+Constants.ORDERDELIVERYDETAILTABLE+" where order_id=#{orderId}")
	@Results(id="detailMap",value={
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="order_id", property="orderId"),
		@Result(column="pid", property="pid"),
		@Result(column="product_id", property="productId"),
		@Result(column="product_name", property="productName"),
		@Result(column="specification", property="specification"),
		@Result(column="product_price", property="productPrice"),
		@Result(column="price", property="price"),
		@Result(column="num", property="num")
	})
	List<OrderDeliveryDetail> selectByOrderId(String orderId);
	
	
	
	@Select("select * from "+Constants.ORDERDELIVERYDETAILTABLE+" where order_id=#{orderId} for update")
	@ResultMap("detailMap")
	List<OrderDeliveryDetail> selectByOrderIdForUpdate(String orderId);
	
	@Select("select * from "+Constants.ORDERDELIVERYDETAILTABLE+" where id=#{id}")
	@ResultMap("detailMap")
	OrderDeliveryDetail selectById(Integer id);
	
	@Delete("delete from "+Constants.ORDERDELIVERYDETAILTABLE+" where id=#{id}")
	Integer deleteById(Integer id);
	
	@Delete("delete from "+Constants.ORDERDELIVERYDETAILTABLE+" where order_id=#{orderId}")
	Integer deleteByOrderId(String orderId);
	
	@InsertProvider(type = OrderDeliveryDetailDynaSqlProvider.class, method = "insertAll")  
	Integer insertAll(Map<String,Object> params);
	

}
