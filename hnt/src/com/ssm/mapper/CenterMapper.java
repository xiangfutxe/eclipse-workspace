package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.CenterDynaSqlProvider;
import com.ssm.pojo.Center;
import com.ssm.utils.Constants;

public interface CenterMapper {
		
	@SelectProvider(type=CenterDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="centerId", property="centerId"),
		@Result(column="centerName", property="centerName"),
		@Result(column="uId", property="uId"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="province", property="province"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="city", property="city"),
		@Result(column="area", property="area"),
		@Result(column="address", property="address"),
		@Result(column="type", property="type"),
		@Result(column="type_form", property="typeForm"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="measure", property="measure")
	})
	List<Center> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=CenterDynaSqlProvider.class,method="selectListWhitParam")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="centerId", property="centerId"),
		@Result(column="centerName", property="centerName"),
		@Result(column="uId", property="uId"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="province", property="province"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="city", property="city"),
		@Result(column="area", property="area"),
		@Result(column="address", property="address"),
		@Result(column="type", property="type"),
		@Result(column="type_form", property="typeForm"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="measure", property="measure")
	})
	List<Center> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=CenterDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.CENTERTABLE+" ")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="centerId", property="centerId"),
		@Result(column="centerName", property="centerName"),
		@Result(column="uId", property="uId"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="province", property="province"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="city", property="city"),
		@Result(column="area", property="area"),
		@Result(column="address", property="address"),
		@Result(column="type", property="type"),
		@Result(column="type_form", property="typeForm"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="measure", property="measure")
	})
	List<Center> selectAllCenter();
	
	
	@Select("select * from "+Constants.CENTERTABLE+" where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="centerId", property="centerId"),
		@Result(column="centerName", property="centerName"),
		@Result(column="uId", property="uId"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="province", property="province"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="city", property="city"),
		@Result(column="area", property="area"),
		@Result(column="address", property="address"),
		@Result(column="type", property="type"),
		@Result(column="type_form", property="typeForm"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="measure", property="measure")
	})
	Center selectById(Integer id);
	
	@Select("select * from "+Constants.CENTERTABLE+" where centerId=#{centerId}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="centerId", property="centerId"),
		@Result(column="centerName", property="centerName"),
		@Result(column="uId", property="uId"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="province", property="province"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="city", property="city"),
		@Result(column="area", property="area"),
		@Result(column="address", property="address"),
		@Result(column="type", property="type"),
		@Result(column="type_form", property="typeForm"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="measure", property="measure")
	})
	Center selectByCenterId(String centerId);
	
	@Select("select * from "+Constants.CENTERTABLE+" where userId=#{userId}")
	@Results({
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="centerId", property="centerId"),
		@Result(column="centerName", property="centerName"),
		@Result(column="uId", property="uId"),
		@Result(column="userId", property="userId"),
		@Result(column="userName", property="userName"),
		@Result(column="province", property="province"),
		@Result(column="state", property="state"),
		@Result(column="phone", property="phone"),
		@Result(column="city", property="city"),
		@Result(column="area", property="area"),
		@Result(column="address", property="address"),
		@Result(column="type", property="type"),
		@Result(column="type_form", property="typeForm"),
		@Result(column="is_hide", property="isHide"),
		@Result(column="entryTime", property="entryTime"),
		@Result(column="measure", property="measure")
	})
	Center selectByUserId(String userId);
	
	
	
	@Delete("delete from "+Constants.CENTERTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=CenterDynaSqlProvider.class,method="updateCenter")
	Integer update(Center center);
	
	@InsertProvider(type=CenterDynaSqlProvider.class,method="insertCenter")
	Integer save(Center center);
	
	@Update("update "+Constants.CENTERTABLE+" set state=#{state} where userId=#{userId}")
	Integer updateForState(@Param("state") Integer state,@Param("userId") String userId);

}
