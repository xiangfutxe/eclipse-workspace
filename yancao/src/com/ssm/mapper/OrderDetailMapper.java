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

import com.ssm.dao.provider.OrderDetailDynaSqlProvider;
import com.ssm.pojo.OrderDetail;
import com.ssm.utils.Constants;

public interface OrderDetailMapper {

	
	@Select("select * from "+Constants.ORDERDETAILTABLE+" where order_id=#{orderId}")
	@Results(id="orderMap",value={
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="order_id", property="orderId"),
		@Result(column="pid", property="pid"),
		@Result(column="product_id", property="productId"),
		@Result(column="product_name", property="productName"),
		@Result(column="product_type", property="productType"),
		@Result(column="product_sort", property="productSort"),
		@Result(column="specification", property="specification"),
		@Result(column="product_price", property="productPrice"),
		@Result(column="product_cash", property="productCash"),
		@Result(column="product_cash_num", property="productCashNum"),
		@Result(column="product_integral", property="productIntegral"),
		@Result(column="product_retail_price", property="productRetailPrice"),
		@Result(column="num", property="num"),
		@Result(column="price", property="price"),
		@Result(column="cash", property="cash"),
		@Result(column="cash_num", property="cashNum"),
		@Result(column="integral", property="integral"),
		@Result(column="retail_price", property="retailPrice")
	})
	List<OrderDetail> selectByOrderId(String orderId);
	
	@Select("select * from "+Constants.ORDERDETAILTABLE+" where id=#{id}")
	@ResultMap("orderMap")
	OrderDetail selectById(Integer id);
	
	@Delete("delete from "+Constants.ORDERDETAILTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@Delete("delete from "+Constants.ORDERDETAILTABLE+" where order_id=#{orderId}")
	int deleteByOrderId(String orderId);
	
	
	@InsertProvider(type = OrderDetailDynaSqlProvider.class, method = "insertAll")  
	Integer insertAll(Map<String,Object> params); 

}
