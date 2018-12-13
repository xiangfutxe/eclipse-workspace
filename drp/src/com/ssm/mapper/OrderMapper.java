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

import com.ssm.dao.provider.OrderDynaSqlProvider;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDeliveryDetail;
import com.ssm.pojo.OrderDetail;
import com.ssm.pojo.OrderDetailProduct;
import com.ssm.utils.Constants;

public interface OrderMapper {
		
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectListWhitParam")
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
		@Result(column="total_num", property="totalNum"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<Order> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectDetailListWhitParam")
	List<OrderDetail> selectOrderDetailByList(Map<String,Object> params);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectDetailProductListWhitParam")
	List<OrderDetailProduct> selectOrderDetailProductByList(Map<String,Object> params);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectWhitParam")
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
		@Result(column="total_num", property="totalNum"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<Order> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="sumByParam")
	Order sumByParam(Map<String,Object> params);
	
	@Select("select * from "+Constants.ORDERTABLE+" where total_num=0 and state>0 for update")	
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
		@Result(column="total_num", property="totalNum"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<Order> selectByTotal();
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.ORDERTABLE+" ")
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
		@Result(column="total_num", property="totalNum"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<Order> selectAllOrder();
	
	
	
	@Select("select * from "+Constants.ORDERTABLE+" where id=#{id}")
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
		@Result(column="total_num", property="totalNum"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	Order selectById(Integer id);
	

	@Select("select * from "+Constants.ORDERTABLE+" where id=#{id} for update")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="total_num", property="totalNum"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	Order selectByIdForUpdate(Integer id);
	
	@Select("select * from "+Constants.ORDERTABLE+" where orderId=#{orderId} for update")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="orderId", property="orderId"),
		@Result(column="userId", property="userId"),
		@Result(column="uId", property="uId"),
		@Result(column="address", property="address"),
		@Result(column="orderType", property="orderType"),
		@Result(column="userByCenterId", property="userByCenterId"),
		@Result(column="userByDeclarationId", property="userByDeclarationId"),
		@Result(column="centerId", property="centerId"),
		@Result(column="orderTime", property="orderTime"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="receiver", property="receiver"),
		@Result(column="price", property="price"),
		@Result(column="pv", property="pv"),
		@Result(column="discount", property="discount"),
		@Result(column="total_num", property="totalNum"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	Order selectByOrderIdForUpdate(String orderId);
	
	@Select("select * from "+Constants.ORDERTABLE+" where userId=#{userId} and orderType=#{orderType} and state=#{state} ")
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
		@Result(column="total_num", property="totalNum"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<Order> selectByParam(@Param("userId") String userId,@Param("orderType") Integer orderType,@Param("state") Integer state);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectDetailBySql")
	List<OrderDetailProduct> selectDetailBySql(String sql);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectDetailBySql")
	List<OrderDetailProduct> selectDetailAllBySql(String sql);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectDetailBySql")
	List<OrderDeliveryDetail> selectDeliveryDetailBySql(String sql);
	
	@Select("select * from "+Constants.ORDERTABLE+" where userId=#{userId} and orderType=#{orderType} and state=#{state} and orderTime=#{time} ")
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
		@Result(column="total_num", property="totalNum"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	List<Order> selectTimeByParam(@Param("userId") String userId,@Param("orderType") Integer orderType,@Param("state") Integer state,@Param("time") Timestamp time);
	
	@Select("select * from "+Constants.ORDERTABLE+" where orderId=#{orderId}")
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
		@Result(column="total_num", property="totalNum"),
		@Result(column="delivery_num", property="deliveryNum")
	})
	Order selectByOrderId(String orderId);
	
	@Delete("delete from "+Constants.ORDERTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=OrderDynaSqlProvider.class,method="updateOrder")
	Integer update(Order order);
	
	@InsertProvider(type=OrderDynaSqlProvider.class,method="insertOrder")
	Integer save(Order order);
	
	@Update("update "+Constants.ORDERTABLE+" set state=#{state} where id=#{id}")
	Integer updateForState(@Param("state") Integer state,@Param("id") Integer id);
	
	@Update("update "+Constants.ORDERTABLE+" set state=#{state} where orderId=#{id} and state=#{state1}")
	Integer updateStateForOrderId(@Param("state") Integer state,@Param("id") String id,@Param("state1") Integer state1);
	
	@Update("update "+Constants.ORDERTABLE+" set total_num=#{totalNum} where id=#{id} and total_num=0")
	int updateForTotalNum(@Param("totalNum") Integer totalNum,@Param("id") Integer id);
	
	@Update("update "+Constants.ORDERTABLE+" set userId=#{userId} where userId=#{id}")
	Integer updateUserIdOfOrder(@Param("userId") String userId,@Param("id") String id);
	

}
