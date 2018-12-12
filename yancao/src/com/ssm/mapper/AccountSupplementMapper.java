package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.AccountSupplementDynaSqlProvider;
import com.ssm.dao.provider.OrderDynaSqlProvider;
import com.ssm.pojo.AccountSupplement;
import com.ssm.pojo.Order;
import com.ssm.utils.Constants;

public interface AccountSupplementMapper {
		
	@SelectProvider(type=AccountSupplementDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="detailMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="uid", property="uid"),
			@Result(column="user_id", property="userId"),
			@Result(column="user_name", property="userName"),
			@Result(column="amount", property="amount"),
			@Result(column="pay_type", property="payType"),
			@Result(column="type", property="type"),
			@Result(column="admin", property="admin"),
			@Result(column="summary", property="summary"),
			@Result(column="entry_time", property="entryTime")
		})
	List<AccountSupplement> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=AccountSupplementDynaSqlProvider.class,method="selectListWhitParam")
	@ResultMap("detailMap")
	List<AccountSupplement> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=AccountSupplementDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=AccountSupplementDynaSqlProvider.class,method="sumByParam")
	AccountSupplement sumByParam(Map<String,Object> params);
	
	@Select("select * from "+Constants.ACCOUNTSUPPLEMENTTABLE+" ")
	List<AccountSupplement> selectAll();
	
	
	@Select("select * from "+Constants.ACCOUNTSUPPLEMENTTABLE+" where id=#{id}")
	@ResultMap("detailMap")
	AccountSupplement selectById(Integer id);
	
	
	@Delete("delete from "+Constants.ACCOUNTSUPPLEMENTTABLE+" where id=#{id}")
	int deleteById(Integer id);

	@UpdateProvider(type=AccountSupplementDynaSqlProvider.class,method="updateAccountSupplement")
	int update(AccountSupplement cy);
	
	@InsertProvider(type=AccountSupplementDynaSqlProvider.class,method="insertAccountSupplement")
	Integer save(AccountSupplement cy);

}
