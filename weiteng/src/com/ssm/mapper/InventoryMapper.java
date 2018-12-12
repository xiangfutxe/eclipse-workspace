package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.InventoryDynaSqlProvider;
import com.ssm.pojo.Inventory;
import com.ssm.utils.Constants;

public interface InventoryMapper {
		@SelectProvider(type=InventoryDynaSqlProvider.class,method="selectWhitParam")
		@Results(id="invMap",value={
				@Result(id=true,column="id",property="id",javaType=Integer.class),
				@Result(column="name", property="name"),
				@Result(column="address", property="address"),
				@Result(column="linkman", property="linkman"),
				@Result(column="phone", property="phone"),
				@Result(column="state", property="state"),
				@Result(column="entry_time", property="entryTime"),
				@Result(column="end_time", property="endTime")
			})
		List<Inventory> selectByPage(Map<String,Object> params);
		
		@SelectProvider(type=InventoryDynaSqlProvider.class,method="count")
		Integer count(Map<String,Object> params);
		
		@Select("select * from "+Constants.INVENTORYTABLE+" where state=1 ")
		@ResultMap("invMap")
		List<Inventory> selectAllInventory();
		
		
		@Select("select * from "+Constants.INVENTORYTABLE+" where id=#{id}")
		@ResultMap("invMap")
		Inventory selectById(Integer id);
		
		@Select("select * from "+Constants.INVENTORYTABLE+" where name=#{name}")
		@ResultMap("invMap")
		Inventory selectByName(String name);
		
		@Delete("delete from "+Constants.INVENTORYTABLE+" where id=#{id}")
		int deleteById(Integer id);
		
		@UpdateProvider(type=InventoryDynaSqlProvider.class,method="updateInventory")
		int update(Inventory inventory);
		
		@InsertProvider(type=InventoryDynaSqlProvider.class,method="insertInventory")
		int save(Inventory inventory);


}
