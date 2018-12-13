package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
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
	List<AccountDetail> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=MoneyDynaSqlProvider.class,method="selectAllWhitParam")
	List<AccountDetail> selectAll(Map<String,Object> params);
	
	@SelectProvider(type=MoneyDynaSqlProvider.class,method="selectListWhitParam")
	List<AccountDetail> selectByList(String sql);
	
	@SelectProvider(type=MoneyDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=MoneyDynaSqlProvider.class,method="sum1")
	Double sum1(Map<String,Object> params);
	
	@SelectProvider(type=MoneyDynaSqlProvider.class,method="sum2")
	Double sum2(Map<String,Object> params);
	
	
	@Select("select * from "+Constants.DEPTTABLE+" ")
	List<AccountDetail> selectAllDept();
	
	@Select("select sum(emoney) as emoney, sum(dmoney) as dmoney,sum(smoney) as smoney,sum(rmoney) as rmoney,sum(pmoney) as pmoney,sum(chuangye_award) as chuangyeAward,sum(chuangye_award_paid) as chuangyeAwardPaid from users  where state>0" )
	public User moneyBalanceByAll();
	
	
	
	@InsertProvider(type=MoneyDynaSqlProvider.class,method="insertMoney")
	Integer save( Map<String,Object> params);
	
	@Update("update emoneyDetail set summary=REPLACE(summary,#{id},#{userId})  where summary LIKE CONCAT ('%',#{uid},'%')")
	Integer updateEmoneyForUserId(@Param("id") String id,@Param("userId") String userId,@Param("uid") String uid);

	@UpdateProvider(type=MoneyDynaSqlProvider.class,method="updateMoney")
	Integer update( Map<String,Object> params);
	
	@UpdateProvider(type=MoneyDynaSqlProvider.class,method="updateForSql")
	Integer updateForSql(String sql);
}
