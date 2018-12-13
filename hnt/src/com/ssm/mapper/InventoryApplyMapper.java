package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.InventoryApplyDynaSqlProvider;
import com.ssm.pojo.InventoryApply;
import com.ssm.utils.Constants;

public interface InventoryApplyMapper {
		
	@SelectProvider(type=InventoryApplyDynaSqlProvider.class,method="selectWhitParam")
	List<InventoryApply> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=InventoryApplyDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.INVENTORYAPPLYTABLE+" where state>1 ")
	List<InventoryApply> selectAllInventoryApply();
	
	@Select("select * from "+Constants.INVENTORYAPPLYTABLE+" where id=#{id}")
	InventoryApply selectById(Integer id);
	
	@Select("select * from "+Constants.INVENTORYAPPLYTABLE+" where applyId=#{applyId}")
	InventoryApply selectByApplyId(String applyId);
	
	@Select("select * from "+Constants.INVENTORYAPPLYTABLE+" where applyId=#{applyId} for update")
	InventoryApply selectByApplyIdForUpdate(String applyId);
	
	@Delete("delete from "+Constants.INVENTORYAPPLYTABLE+" where id=#{id}")
	Integer deleteById(Integer id);
	
	@UpdateProvider(type=InventoryApplyDynaSqlProvider.class,method="updateInventoryApply")
	Integer update(InventoryApply inventoryApply);
	
	@InsertProvider(type=InventoryApplyDynaSqlProvider.class,method="insertInventoryApply")
	Integer save(InventoryApply inventoryApply);

}
