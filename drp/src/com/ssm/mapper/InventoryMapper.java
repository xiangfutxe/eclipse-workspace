package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.InventoryDynaSqlProvider;
import com.ssm.pojo.Inventory;
import com.ssm.utils.Constants;

public interface InventoryMapper {
		@SelectProvider(type=InventoryDynaSqlProvider.class,method="selectWhitParam")
		List<Inventory> selectByPage(Map<String,Object> params);
		
		@SelectProvider(type=InventoryDynaSqlProvider.class,method="count")
		Integer count(Map<String,Object> params);
		
		@Select("select * from "+Constants.INVENTORYTABLE+" where state=1 ")
		List<Inventory> selectAllInventory();
		
		
		@Select("select * from "+Constants.INVENTORYTABLE+" where id=#{id}")
		Inventory selectById(Integer id);
		
		@Select("select * from "+Constants.INVENTORYTABLE+" where inventoryName=#{name}")
		Inventory selectByName(String name);
		
		@Delete("delete from "+Constants.INVENTORYTABLE+" where id=#{id}")
		int deleteById(Integer id);
		
		@UpdateProvider(type=InventoryDynaSqlProvider.class,method="updateInventory")
		int update(Inventory inventory);
		
		@InsertProvider(type=InventoryDynaSqlProvider.class,method="insertInventory")
		int save(Inventory inventory);


}
