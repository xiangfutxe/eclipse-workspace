package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.BorrowApplyDynaSqlProvider;
import com.ssm.pojo.BorrowApply;
import com.ssm.utils.Constants;

public interface BorrowApplyMapper {
	
	@SelectProvider(type=BorrowApplyDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="applyMap",value={
			@Result(id=true,column="id",property="id",javaType=long.class),
			@Result(column="apply_id", property="applyId"),
			@Result(column="contract_id", property="contractId"),
			@Result(column="contract_name", property="contractName"),
			@Result(column="admin_name", property="adminName"),
			@Result(column="apply_name", property="applyName"),
			@Result(column="confirm_name", property="confirmName"),
			@Result(column="review_name", property="reviewName"),
			@Result(column="end_name", property="endName"),
			@Result(column="state", property="state"),
			@Result(column="confirm_time", property="confirmTime"),
			@Result(column="start_time", property="startTime"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<BorrowApply> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=BorrowApplyDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	//动态插入用户
	@SelectProvider(type=BorrowApplyDynaSqlProvider.class,method="insert")
	void save(BorrowApply contract);
	
	@UpdateProvider(type=BorrowApplyDynaSqlProvider.class,method="update")
	Integer update(BorrowApply contract);
	
	@Select("select * from "+Constants.BORROWAPPLYTABLE+" where id=#{id}")
	@ResultMap("applyMap")
	BorrowApply selectById(Long id);
	
	@Select("select * from "+Constants.BORROWAPPLYTABLE+" where id=#{id} for update")
	@ResultMap("applyMap")
	BorrowApply selectByIdForUpdate(Long id);
	
	@Select("select * from "+Constants.BORROWAPPLYTABLE+" where contract_id=#{id} and state='1'")
	@ResultMap("applyMap")
	BorrowApply selectByCId(String id);
	
	@Delete("delete from "+Constants.BORROWAPPLYTABLE+" where id=#{id}")
	Integer deleteById(Long id);
	
	@Delete("update "+Constants.CONTRACTTABLE+" set state=0 where id=#{id}")
	Integer deleteByIdForState(Integer id);
}
