package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.AccountSupplementDynaSqlProvider;
import com.ssm.dao.provider.ChargeApplyDynaSqlProvider;
import com.ssm.pojo.AccountSupplement;
import com.ssm.pojo.ChargeApply;
import com.ssm.utils.Constants;

public interface ChargeApplyMapper {
		
	@SelectProvider(type=ChargeApplyDynaSqlProvider.class,method="selectWhitParam")
	List<ChargeApply> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=ChargeApplyDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.CHARGEAPPLYTABLE+" ")
	List<ChargeApply> selectAllDept();
	
	@SelectProvider(type=ChargeApplyDynaSqlProvider.class,method="sumByParam")
	ChargeApply sumByParam(Map<String,Object> params);
	
	
	@Select("select * from "+Constants.CHARGEAPPLYTABLE+" where id=#{id}")
	ChargeApply selectById(Integer id);
	
	@Select("select * from "+Constants.CHARGEAPPLYTABLE+" where name=#{name}")
	ChargeApply selectByName(String name);
	
	@Delete("delete from "+Constants.CHARGEAPPLYTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@Update("update "+Constants.CHARGEAPPLYTABLE+" set state=#{state} where id=#{id} and state=#{state1}")
	int UpdateState(@Param("id") Integer id,@Param("state")Integer state,@Param("state1")Integer state1);
	
	@UpdateProvider(type=ChargeApplyDynaSqlProvider.class,method="updateChargeApply")
	int update(ChargeApply cy);
	
	@InsertProvider(type=ChargeApplyDynaSqlProvider.class,method="insertChargeApply")
	Integer save(ChargeApply cy);

}
