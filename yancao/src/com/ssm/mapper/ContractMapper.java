package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.ContractDynaSqlProvider;
import com.ssm.pojo.Contract;
import com.ssm.utils.Constants;

public interface ContractMapper {
	
	@SelectProvider(type=ContractDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="contractMap",value={
			@Result(id=true,column="id",property="id",javaType=long.class),
			@Result(column="contract_id", property="contractId"),
			@Result(column="contract_name", property="contractName"),
			@Result(column="admin_id", property="adminId"),
			@Result(column="admin_name", property="adminName"),
			@Result(column="code", property="code"),
			@Result(column="party_a", property="partyA"),
			@Result(column="party_b", property="partyB"),
			@Result(column="level", property="level"),
			@Result(column="rank", property="rank"),
			@Result(column="dept", property="dept"),
			@Result(column="borrow_num", property="borrowNum"),
			@Result(column="borrow_tag", property="borrowTag"),
			@Result(column="rank", property="rank"),
			@Result(column="pdf_url", property="pdfUrl"),
			@Result(column="state", property="state"),
			@Result(column="start_time", property="startTime"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<Contract> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ContractDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	//动态插入用户
	@SelectProvider(type=ContractDynaSqlProvider.class,method="insert")
	void save(Contract contract);
	
	@UpdateProvider(type=ContractDynaSqlProvider.class,method="update")
	Integer update(Contract contract);
	
	@Delete("update "+Constants.CONTRACTTABLE+" set pdf_url=#{pdfUrl} where id=#{id}")
	Integer updatePdf(@Param("id") Long id, @Param("pdfUrl") String pdfUrl);
	
	@Delete("update "+Constants.CONTRACTTABLE+" set state=#{state} where contract_id=#{id} and state=#{state1}")
	Integer updateState(@Param("id") String id, @Param("state") Integer state, @Param("state1") Integer state1);
	
	@Select("select * from "+Constants.CONTRACTTABLE+" where id=#{id}")
	@ResultMap("contractMap")
	Contract selectById(Integer id);
	
	@Select("select * from "+Constants.CONTRACTTABLE+" where contract_id=#{name}")
	@ResultMap("contractMap")
	Contract selectByName(String name);
	
	@Delete("delete from "+Constants.CONTRACTTABLE+" where id=#{id}")
	Integer deleteById(Long id);
	
	@Delete("update "+Constants.CONTRACTTABLE+" set state=0 where id=#{id}")
	Integer deleteByIdForState(Integer id);
}
