package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.InventoryDetailDynaSqlProvider;
import com.ssm.pojo.InventoryDetail;
import com.ssm.utils.Constants;

public interface InventoryDetailMapper {
		
	@SelectProvider(type=InventoryDetailDynaSqlProvider.class,method="selectWhitParam")
	List<InventoryDetail> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=InventoryDetailDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.INVENTORYDETAILTABLE+" where state>=1 ")
	List<InventoryDetail> selectAllInventoryDetail();
	
	@Select("select * from "+Constants.INVENTORYDETAILTABLE+" where applyId=#{applyId} ")
	List<InventoryDetail> selectByApplyId(String applyId);
	
	@Select("select * from "+Constants.INVENTORYDETAILTABLE+" where id=#{id}")
	InventoryDetail selectById(Integer id);
	
	@Delete("delete from "+Constants.INVENTORYDETAILTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=InventoryDetailDynaSqlProvider.class,method="updateInventoryDetail")
	int update(InventoryDetail InventoryDetail);
	
	@InsertProvider(type=InventoryDetailDynaSqlProvider.class,method="insertInventoryDetail")
	int save(InventoryDetail InventoryDetail);

}
