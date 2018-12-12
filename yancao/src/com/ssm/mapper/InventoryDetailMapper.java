package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.InventoryDetailDynaSqlProvider;
import com.ssm.pojo.InventoryDetail;
import com.ssm.utils.Constants;

public interface InventoryDetailMapper {
		
	@SelectProvider(type=InventoryDetailDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="detailMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="pid", property="pid"),
			@Result(column="apply_id", property="applyId"),
			@Result(column="product_id", property="productId"),
			@Result(column="product_name", property="productName"),
			@Result(column="specification", property="specification"),
			@Result(column="price", property="price"),
			@Result(column="num", property="num"),
			@Result(column="remark", property="remark"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<InventoryDetail> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=InventoryDetailDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.INVENTORYDETAILTABLE+" where state>=1 ")
	@ResultMap("detailMap")
	List<InventoryDetail> selectAllInventoryDetail();
	
	@Select("select * from "+Constants.INVENTORYDETAILTABLE+" where apply_id=#{applyId} ")
	@ResultMap("detailMap")
	List<InventoryDetail> selectByApplyId(String applyId);
	
	@Select("select * from "+Constants.INVENTORYDETAILTABLE+" where id=#{id}")
	@ResultMap("detailMap")
	InventoryDetail selectById(Integer id);
	
	@Delete("delete from "+Constants.INVENTORYDETAILTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=InventoryDetailDynaSqlProvider.class,method="updateInventoryDetail")
	int update(InventoryDetail InventoryDetail);
	
	@InsertProvider(type=InventoryDetailDynaSqlProvider.class,method="insertInventoryDetail")
	int save(InventoryDetail InventoryDetail);

}
