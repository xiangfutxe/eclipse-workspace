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
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.AccountSupplementDynaSqlProvider;
import com.ssm.dao.provider.WithDrewDynaSqlProvider;
import com.ssm.pojo.AccountSupplement;
import com.ssm.pojo.WithDrew;
import com.ssm.utils.Constants;

public interface WithDrewMapper {
		
	@SelectProvider(type=WithDrewDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="applyMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="uid", property="uid"),
			@Result(column="user_id", property="userId"),
			@Result(column="user_name", property="userName"),
			@Result(column="apply_id", property="applyId"),
			@Result(column="reviewer_id", property="reviewerId"),
			@Result(column="review_time", property="reviewTime"),
			@Result(column="apply_time", property="applyTime"),
			@Result(column="amount", property="amount"),
			@Result(column="actual_amount", property="actualAmount"),
			@Result(column="account_id", property="accountId"),
			@Result(column="account_name", property="accountName"),
			@Result(column="bank_name", property="bankName"),
			@Result(column="bank_adr", property="bankAdr"),
			@Result(column="state", property="state"),
			@Result(column="comments", property="comments")
		})
	List<WithDrew> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=WithDrewDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=WithDrewDynaSqlProvider.class,method="selectListWhitParam")
	@ResultMap("applyMap")
	List<WithDrew> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=WithDrewDynaSqlProvider.class,method="sumByParam")
	WithDrew sumByParam(Map<String,Object> params);
	
	@Select("select * from "+Constants.WITHDREWTABLE+" where state>0 ")
	@ResultMap("applyMap")
	List<WithDrew> selectAllWithDrew();
	
	@Select("select * from "+Constants.WITHDREWTABLE+" where id=#{id}")
	@ResultMap("applyMap")
	WithDrew selectById(Integer id);
	
	@Select("select * from "+Constants.WITHDREWTABLE+" where apply_id=#{applyId} for update")
	@ResultMap("applyMap")
	WithDrew selectByApplyIdForUpdate(String applyId);
	
	
	@Select("select * from "+Constants.WITHDREWTABLE+" where apply_id=#{applyId}")
	@ResultMap("applyMap")
	WithDrew selectByApplyId(String applyId);
	
	
	@Select("select * from "+Constants.WITHDREWTABLE+" where user_id=#{userId}")
	@ResultMap("applyMap")
	List<WithDrew> selectByUserId(String userId);
	
	
	
	@Delete("delete from "+Constants.WITHDREWTABLE+" where id=#{id}")
	Integer deleteById(Integer id);
	
	@UpdateProvider(type=WithDrewDynaSqlProvider.class,method="updateWithDrew")
	Integer update(WithDrew adr);
	
	@InsertProvider(type=WithDrewDynaSqlProvider.class,method="insertWithDrew")
	Integer save(WithDrew adr);


}
