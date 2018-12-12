package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.InventoryApplyDynaSqlProvider;
import com.ssm.pojo.InventoryApply;
import com.ssm.utils.Constants;

public interface InventoryApplyMapper {
		
	@SelectProvider(type=InventoryApplyDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="applyMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="apply_id", property="applyId"),
			@Result(column="inventory", property="inventory"),
			@Result(column="inventory_id", property="inventoryId"),
			@Result(column="operator_id", property="operatorId"),
			@Result(column="reviewer_id", property="reviewerId"),
			@Result(column="review_time", property="reviewTime"),
			@Result(column="pay_type", property="payType"),
			@Result(column="remark", property="remark"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime")
		})
	List<InventoryApply> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=InventoryApplyDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.INVENTORYAPPLYTABLE+" where state>1 ")
	@ResultMap("applyMap")
	List<InventoryApply> selectAllInventoryApply();
	
	@Select("select * from "+Constants.INVENTORYAPPLYTABLE+" where id=#{id}")
	@ResultMap("applyMap")
	InventoryApply selectById(Integer id);
	
	@Select("select * from "+Constants.INVENTORYAPPLYTABLE+" where apply_id=#{apply_id}")
	@ResultMap("applyMap")
	InventoryApply selectByApplyId(String applyId);
	
	@Select("select * from "+Constants.INVENTORYAPPLYTABLE+" where apply_id=#{applyId} for update")
	InventoryApply selectByApplyIdForUpdate(String applyId);
	
	@Delete("delete from "+Constants.INVENTORYAPPLYTABLE+" where id=#{id}")
	Integer deleteById(Integer id);
	
	@UpdateProvider(type=InventoryApplyDynaSqlProvider.class,method="updateInventoryApply")
	Integer update(InventoryApply inventoryApply);
	
	@InsertProvider(type=InventoryApplyDynaSqlProvider.class,method="insertInventoryApply")
	Integer save(InventoryApply inventoryApply);

}
