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

import com.ssm.dao.provider.OrderDeliveryDynaSqlProvider;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDelivery;
import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.utils.Constants;

public interface OrderDeliveryMapper {
		
	@SelectProvider(type=OrderDeliveryDynaSqlProvider.class,method="selectListWhitParam")
	@Results(id="orderMap",value={
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="order_id", property="orderId"),
		@Result(column="user_id", property="userId"),
		@Result(column="user_name", property="userName"),
		@Result(column="uid", property="uid"),
		@Result(column="order_type", property="orderType"),
		@Result(column="order_time", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="province", property="province"),
		@Result(column="city", property="city"),
		@Result(column="area", property="area"),
		@Result(column="address", property="address"),
		@Result(column="price", property="price"),
		@Result(column="fee", property="fee"),
		@Result(column="express", property="express"),
		@Result(column="express_code", property="expressCode"),
		@Result(column="express_num", property="expressNum"),
		@Result(column="fee", property="fee")
	})
	List<OrderDelivery> selectByList(Map<String,Object> params);
	
	
	
	
	
	@SelectProvider(type=OrderDeliveryDynaSqlProvider.class,method="selectWhitParam")
	@ResultMap("orderMap")
	List<OrderDelivery> selectByPage(Map<String,Object> params);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where total_num=0 and state>0 for update")	
	@ResultMap("orderMap")
	List<OrderDelivery> selectByTotal();
	
	@SelectProvider(type=OrderDeliveryDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" ")
	@ResultMap("orderMap")
	List<OrderDelivery> selectAllOrder();
	
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where id=#{id}")
	@ResultMap("orderMap")
	OrderDelivery selectById(Integer id);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where id=#{id}")
	@ResultMap("orderMap")
	OrderDelivery selectByIdForUpdate(Integer id);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where order_id=#{orderId}")
	@ResultMap("orderMap")
	OrderDelivery selectByOrderIdForUpdate(String orderId);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where order_id=#{orderId} and  state=#{state} ")
	@ResultMap("orderMap")
	List<OrderDelivery> selectByToOrderId(@Param("orderId") String orderId,@Param("state") Integer state);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where order_id=#{orderId} and state>0 ")
	@ResultMap("orderMap")
	List<OrderDelivery> selectListByToOrderId(@Param("orderId") String orderId);
	
	@SelectProvider(type=OrderDeliveryDynaSqlProvider.class,method="selectListBySql")
	@ResultMap("orderMap")
	List<OrderDelivery> selectListBySql(String sql);
	
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where user_id=#{userId} and order_type=#{orderType} and state=#{state} ")
	@ResultMap("orderMap")
	List<OrderDelivery> selectByParam(@Param("userId") String user_id,@Param("orderType") Integer orderType,@Param("state") Integer state);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where user_id=#{user_id} and order_type=#{order_type} and state=#{state} and order_time=#{time} ")
	@ResultMap("orderMap")
	List<OrderDelivery> selectTimeByParam(@Param("userId") String userId,@Param("orderType") Integer orderType,@Param("state") Integer state,@Param("time") Timestamp time);
	
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where order_id=#{orderId}")
	@ResultMap("orderMap")
	OrderDelivery selectByOrderId(String orderId);
	
	
	@Delete("delete from "+Constants.ORDERDELIVERYTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@Delete("delete from "+Constants.ORDERDELIVERYTABLE+" where order_id=#{id}")
	Integer deleteByOrderId(String id);
	
	@UpdateProvider(type=OrderDeliveryDynaSqlProvider.class,method="updateOrderDelivery")
	Integer update(OrderDelivery order);
	
	@InsertProvider(type=OrderDeliveryDynaSqlProvider.class,method="insertOrderDelivery")
	Integer save(OrderDelivery order);
	
	@Update("update "+Constants.ORDERDELIVERYTABLE+" set state=#{state} where id=#{id}")
	Integer updateForState(@Param("state") Integer state,@Param("id") Integer id);
	
	@Update("update "+Constants.ORDERDELIVERYTABLE+" set total_num=#{totalNum} where id=#{id} and total_num=0")
	int updateForTotalNum(@Param("totalNum") Integer totalNum,@Param("id") Integer id);
	
	@Update("update "+Constants.ORDERDELIVERYTABLE+" set userId=#{user_id} where user_id=#{id}")
	Integer updateUserIdOfOrder(@Param("userId") String userId,@Param("id") String id);
	

}
