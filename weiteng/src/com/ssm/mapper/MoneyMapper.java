package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.MoneyDynaSqlProvider;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.User;
import com.ssm.utils.Constants;

public interface MoneyMapper {
		
	@SelectProvider(type=MoneyDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="detailMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="uid", property="uid"),
			@Result(column="user_id", property="userId"),
			@Result(column="user_name", property="userName"),
			@Result(column="amount", property="amount"),
			@Result(column="balance", property="balance"),
			@Result(column="pay_type", property="payType"),
			@Result(column="trade_type", property="tradeType"),
			@Result(column="summary", property="summary"),
			@Result(column="entry_time", property="entryTime")
		})
	List<AccountDetail> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=MoneyDynaSqlProvider.class,method="selectAllWhitParam")
	@ResultMap("detailMap")
	List<AccountDetail> selectAll(Map<String,Object> params);
	
	@SelectProvider(type=MoneyDynaSqlProvider.class,method="selectListWhitParam")
	@ResultMap("detailMap")
	List<AccountDetail> selectByList(String sql);
	
	@SelectProvider(type=MoneyDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=MoneyDynaSqlProvider.class,method="sum1")
	Double sum1(Map<String,Object> params);
	
	@SelectProvider(type=MoneyDynaSqlProvider.class,method="sum2")
	Double sum2(Map<String,Object> params);
	
	@Select("select sum(rmoney) as rmoney,sum(pre_rmoney) as preRmoney,sum(cash) as cash,sum(cash_num) as cashNum,sum(integral) as integral from "+Constants.USERSTABLE+" ")
	User selectAllMoney();
	
	
	@Select("select * from "+Constants.DEPTTABLE+" ")
	List<AccountDetail> selectAllDept();
	
	@InsertProvider(type=MoneyDynaSqlProvider.class,method="insertMoney")
	Integer save( Map<String,Object> params);
	
	
	@UpdateProvider(type=MoneyDynaSqlProvider.class,method="updateMoney")
	Integer update( Map<String,Object> params);
	
	@UpdateProvider(type=MoneyDynaSqlProvider.class,method="updateForSql")
	Integer updateForSql(String sql);
}
