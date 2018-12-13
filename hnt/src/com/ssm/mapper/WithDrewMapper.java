package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
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
	List<WithDrew> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=WithDrewDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=WithDrewDynaSqlProvider.class,method="selectListWhitParam")
	List<WithDrew> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=WithDrewDynaSqlProvider.class,method="sumByParam")
	WithDrew sumByParam(Map<String,Object> params);
	
	@Select("select * from "+Constants.WITHDREWTABLE+" where state>0 ")
	List<WithDrew> selectAllWithDrew();
	
	@Select("select * from "+Constants.WITHDREWTABLE+" where id=#{id}")
	WithDrew selectById(Integer id);
	
	@Select("select * from "+Constants.WITHDREWTABLE+" where applyId=#{applyId} for update")
	WithDrew selectByApplyIdForUpdate(String applyId);
	
	
	@Select("select * from "+Constants.WITHDREWTABLE+" where applyId=#{applyId}")
	WithDrew selectByApplyId(String applyId);
	
	
	@Select("select * from "+Constants.WITHDREWTABLE+" where userId=#{userId}")
	List<WithDrew> selectByUserId(String userId);
	
	
	
	@Delete("delete from "+Constants.WITHDREWTABLE+" where id=#{id}")
	Integer deleteById(Integer id);
	
	@UpdateProvider(type=WithDrewDynaSqlProvider.class,method="updateWithDrew")
	Integer update(WithDrew adr);
	
	@InsertProvider(type=WithDrewDynaSqlProvider.class,method="insertWithDrew")
	Integer save(WithDrew adr);


}
