package com.ssm.mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
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
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="address", property="address"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="to_oid", property="toOid"),
		@Result(column="to_order_id", property="toOrderId"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<OrderDelivery> selectByList(Map<String,Object> params);
	
	
	
	@SelectProvider(type=OrderDeliveryDynaSqlProvider.class,method="selectDetailListWhitParam")
	@Results({
		@Result(id=true,column="od.id",property="od.id",javaType=Integer.class),
		@Result(column="ord.orderId", property="orderId"),
		@Result(column="ord.userId", property="userId"),
		@Result(column="ord.state", property="state"),
		@Result(column="od.pid", property="pid"),
		@Result(column="od.productId", property="productId"),
		@Result(column="od.productName", property="productName"),
		@Result(column="od.productType", property="productType"),
		@Result(column="od.specification", property="specification"),
		@Result(column="od.type", property="type"),
		@Result(column="od.productPrice", property="productPrice"),
		@Result(column="od.productPv", property="productPv"),
		@Result(column="od.price", property="price"),
		@Result(column="od.pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="od.num", property="num")
	})
	List<OrderDeliveryDetail> selectOrderDetailByList(Map<String,Object> params);
	
	@SelectProvider(type=OrderDeliveryDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="to_order_id", property="toOrderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="adminByReviewerId", property="adminByReviewerId"),
		@Result(column="adminByConfirmId", property="adminByConfirmId"),
		@Result(column="confirmTime", property="confirmTime"),
		@Result(column="reviewerTime", property="reviewerTime"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<OrderDelivery> selectByPage(Map<String,Object> params);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where total_num=0 and state>0 for update")	
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="adminByReviewerId", property="adminByReviewerId"),
		@Result(column="adminByConfirmId", property="adminByConfirmId"),
		@Result(column="confirmTime", property="confirmTime"),
		@Result(column="reviewerTime", property="reviewerTime"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<OrderDelivery> selectByTotal();
	
	@SelectProvider(type=OrderDeliveryDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" ")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="to_order_id", property="toOrderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="adminByReviewerId", property="adminByReviewerId"),
		@Result(column="adminByConfirmId", property="adminByConfirmId"),
		@Result(column="confirmTime", property="confirmTime"),
		@Result(column="reviewerTime", property="reviewerTime"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<OrderDelivery> selectAllOrder();
	
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="to_order_id", property="toOrderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="adminByReviewerId", property="adminByReviewerId"),
		@Result(column="adminByConfirmId", property="adminByConfirmId"),
		@Result(column="confirmTime", property="confirmTime"),
		@Result(column="reviewerTime", property="reviewerTime"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	Order selectById(Integer id);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="to_order_id", property="toOrderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="adminByReviewerId", property="adminByReviewerId"),
		@Result(column="adminByConfirmId", property="adminByConfirmId"),
		@Result(column="confirmTime", property="confirmTime"),
		@Result(column="reviewerTime", property="reviewerTime"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	OrderDelivery selectByIdForUpdate(Integer id);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where orderId=#{orderId}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="to_order_id", property="toOrderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="adminByReviewerId", property="adminByReviewerId"),
		@Result(column="adminByConfirmId", property="adminByConfirmId"),
		@Result(column="confirmTime", property="confirmTime"),
		@Result(column="reviewerTime", property="reviewerTime"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	OrderDelivery selectByOrderIdForUpdate(String orderId);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where to_order_id=#{orderId} and  state=#{state} ")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="to_order_id", property="toOrderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="adminByReviewerId", property="adminByReviewerId"),
		@Result(column="adminByConfirmId", property="adminByConfirmId"),
		@Result(column="confirmTime", property="confirmTime"),
		@Result(column="reviewerTime", property="reviewerTime"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<OrderDelivery> selectByToOrderId(@Param("orderId") String orderId,@Param("state") Integer state);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where to_order_id=#{orderId} and state>0 ")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="to_order_id", property="toOrderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="adminByReviewerId", property="adminByReviewerId"),
		@Result(column="adminByConfirmId", property="adminByConfirmId"),
		@Result(column="confirmTime", property="confirmTime"),
		@Result(column="reviewerTime", property="reviewerTime"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<OrderDelivery> selectListByToOrderId(@Param("orderId") String orderId);
	
	@SelectProvider(type=OrderDeliveryDynaSqlProvider.class,method="selectListBySql")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="to_order_id", property="toOrderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="adminByReviewerId", property="adminByReviewerId"),
		@Result(column="adminByConfirmId", property="adminByConfirmId"),
		@Result(column="confirmTime", property="confirmTime"),
		@Result(column="reviewerTime", property="reviewerTime"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<OrderDelivery> selectListBySql(String sql);
	
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where userId=#{userId} and orderType=#{orderType} and state=#{state} ")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="userId", property="userId"),
		@Result(column="to_order_id", property="toOrderId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="adminByReviewerId", property="adminByReviewerId"),
		@Result(column="adminByConfirmId", property="adminByConfirmId"),
		@Result(column="confirmTime", property="confirmTime"),
		@Result(column="reviewerTime", property="reviewerTime"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<OrderDelivery> selectByParam(@Param("userId") String userId,@Param("orderType") Integer orderType,@Param("state") Integer state);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where userId=#{userId} and orderType=#{orderType} and state=#{state} and orderTime=#{time} ")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="to_order_id", property="toOrderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="adminByReviewerId", property="adminByReviewerId"),
		@Result(column="adminByConfirmId", property="adminByConfirmId"),
		@Result(column="confirmTime", property="confirmTime"),
		@Result(column="reviewerTime", property="reviewerTime"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<OrderDelivery> selectTimeByParam(@Param("userId") String userId,@Param("orderType") Integer orderType,@Param("state") Integer state,@Param("time") Timestamp time);
	
	@Select("select * from "+Constants.ORDERDELIVERYTABLE+" where orderId=#{orderId}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="to_order_id", property="toOrderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="adminByReviewerId", property="adminByReviewerId"),
		@Result(column="adminByConfirmId", property="adminByConfirmId"),
		@Result(column="confirmTime", property="confirmTime"),
		@Result(column="reviewerTime", property="reviewerTime"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	OrderDelivery selectByOrderId(String orderId);
	
	@Delete("delete from "+Constants.ORDERDELIVERYTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@Delete("delete from "+Constants.ORDERDELIVERYTABLE+" where orderId=#{id}")
	Integer deleteByOrderId(String id);
	
	@UpdateProvider(type=OrderDeliveryDynaSqlProvider.class,method="updateOrderDelivery")
	Integer update(OrderDelivery order);
	
	@InsertProvider(type=OrderDeliveryDynaSqlProvider.class,method="insertOrderDelivery")
	Integer save(OrderDelivery order);
	
	@Update("update "+Constants.ORDERDELIVERYTABLE+" set state=#{state} where id=#{id}")
	Integer updateForState(@Param("state") Integer state,@Param("id") Integer id);
	
	@Update("update "+Constants.ORDERDELIVERYTABLE+" set total_num=#{totalNum} where id=#{id} and total_num=0")
	int updateForTotalNum(@Param("totalNum") Integer totalNum,@Param("id") Integer id);
	
	@Update("update "+Constants.ORDERDELIVERYTABLE+" set userId=#{userId} where userId=#{id}")
	Integer updateUserIdOfOrder(@Param("userId") String userId,@Param("id") String id);
	

}
