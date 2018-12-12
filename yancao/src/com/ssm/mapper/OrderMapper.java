package com.ssm.mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.Update;

import com.ssm.dao.provider.OrderDynaSqlProvider;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.pojo.OrderDetail;
import com.ssm.pojo.OrderDetailProduct;
import com.ssm.utils.Constants;

public interface OrderMapper {
		
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectListWhitParam")
	@Results(id="orderMap",value={
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="order_id", property="orderId"),
		@Result(column="user_id", property="userId"),
		@Result(column="user_name", property="userName"),
		@Result(column="uid", property="uid"),
		@Result(column="price", property="price"),
		@Result(column="cash", property="cash"),
		@Result(column="cash_num", property="cashNum"),
		@Result(column="integral", property="integral"),
		@Result(column="retail_price", property="retailPrice"),
		@Result(column="give_cash", property="giveCash"),
		@Result(column="give_cash_num", property="giveCashNum"),
		@Result(column="give_integral", property="giveIntegral"),
		@Result(column="order_type", property="orderType"),
		@Result(column="reviewer_id", property="reviewerId"),
		@Result(column="referee_id", property="refereeId"),
		@Result(column="pay_type", property="payType"),
		@Result(column="order_time", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="review_time", property="reviewTime"),
		@Result(column="image_url", property="imageUrl"),
		@Result(column="province", property="province"),
		@Result(column="city", property="city"),
		@Result(column="area", property="area"),
		@Result(column="address", property="address"),
		@Result(column="rank", property="rank")
	})
	List<Order> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectDetailListWhitParam")
	@ResultMap("orderMap")
	List<OrderDetail> selectOrderDetailByList(Map<String,Object> params);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectDetailProductListWhitParam")
	@ResultMap("orderMap")
	List<OrderDetailProduct> selectOrderDetailProductByList(Map<String,Object> params);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectWhitParam")
	@ResultMap("orderMap")
	List<Order> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectTagWhitParam")
	@ResultMap("orderMap")
	List<Order> selectTagByPage(Map<String,Object> params);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="sumByParam")
	Order sumByParam(Map<String,Object> params);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="sumTagByParam")
	Order sumTagByParam(Map<String,Object> params);
	
	@Select("select * from "+Constants.ORDERTABLE+" where total_num=0 and state>0 for update")	
	@ResultMap("orderMap")
	List<Order> selectByTotal();
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.ORDERTABLE+" ")
	@ResultMap("orderMap")
	List<Order> selectAllOrder();
	
	
	
	@Select("select * from "+Constants.ORDERTABLE+" where id=#{id}")
	@ResultMap("orderMap")
	Order selectById(Integer id);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectDetailBySql")
	@ResultMap("orderMap")
	List<Order> selectBySql(String sql);
	

	@Select("select * from "+Constants.ORDERTABLE+" where id=#{id} for update")
	@ResultMap("orderMap")
	Order selectByIdForUpdate(Integer id);
	
	@Select("select * from "+Constants.ORDERTABLE+" where order_id=#{orderId} for update")
	@ResultMap("orderMap")
	Order selectByOrderIdForUpdate(String orderId);
	
	@Select("select * from "+Constants.ORDERTABLE+" where user_id=#{userId} and order_type=#{orderType} and state=#{state} ")
	@ResultMap("orderMap")
	List<Order> selectByParam(@Param("userId") String userId,@Param("orderType") Integer orderType,@Param("state") Integer state);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectDetailBySql")
	List<OrderDetailProduct> selectDetailBySql(String sql);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectDetailBySql")
	List<OrderDetailProduct> selectDetailAllBySql(String sql);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectDetailBySql")
	List<OrderDeliveryDetail> selectDeliveryDetailBySql(String sql);
	
	@Select("select * from "+Constants.ORDERTABLE+" where user_id=#{userId} and order_type=#{orderType} and state=#{state} and order_time=#{time} ")
	@ResultMap("orderMap")
	List<Order> selectTimeByParam(@Param("userId") String userId,@Param("orderType") Integer orderType,@Param("state") Integer state,@Param("time") Timestamp time);
	
	@Select("select * from "+Constants.ORDERTABLE+" where order_id=#{orderId}")
	@ResultMap("orderMap")
	Order selectByOrderId(String orderId);
	
	@Delete("delete from "+Constants.ORDERTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=OrderDynaSqlProvider.class,method="updateOrder")
	Integer update(Order order);
	
	@InsertProvider(type=OrderDynaSqlProvider.class,method="insertOrder")
	Integer save(Order order);
	
	@Update("update "+Constants.ORDERTABLE+" set state=#{state} where id=#{id}")
	Integer updateForState(@Param("state") Integer state,@Param("id") Integer id);
	
	@Update("update "+Constants.ORDERTABLE+" set state=#{state} where order_id=#{id} and state=#{state1}")
	Integer updateStateForOrderId(@Param("state") Integer state,@Param("id") String id,@Param("state1") Integer state1);
	
	@Update("update "+Constants.ORDERTABLE+" set total_num=#{totalNum} where id=#{id} and total_num=0")
	int updateForTotalNum(@Param("totalNum") Integer totalNum,@Param("id") Integer id);
	
	@Update("update "+Constants.ORDERTABLE+" set user_id=#{userId} where user_id=#{id}")
	Integer updateUserIdOfOrder(@Param("userId") String userId,@Param("id") String id);
	

}
