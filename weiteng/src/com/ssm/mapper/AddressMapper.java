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
import org.apache.ibatis.annotations.Update;
import com.ssm.dao.provider.AddressDynaSqlProvider;
import com.ssm.pojo.Address;
import com.ssm.utils.Constants;

public interface AddressMapper {
		
	@SelectProvider(type=AddressDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="adrMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="user_id", property="userId"),
			@Result(column="uid", property="uid"),
			@Result(column="province", property="province"),
			@Result(column="city", property="city"),
			@Result(column="area", property="area"),
			@Result(column="address", property="address"),
			@Result(column="reciver", property="reciver"),
			@Result(column="tag", property="tag"),
			@Result(column="phone", property="phone"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<Address> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=AddressDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.ADDRESSTABLE+" where state=1 ")
	@ResultMap("adrMap")
	List<Address> selectAllAddress();
	
	
	@Select("select * from "+Constants.ADDRESSTABLE+" where id=#{id}")
	@ResultMap("adrMap")
	Address selectById(Integer id);
	
	@Select("select * from "+Constants.ADDRESSTABLE+" where user_id=#{userId} and tag=#{id}")
	@ResultMap("adrMap")
	List<Address> selectByTag(@Param("userId") String userId,@Param("id")  Integer id);
	
	
	@Select("select * from "+Constants.ADDRESSTABLE+" where user_id=#{userId} and state=1")
	@ResultMap("adrMap")
	List<Address> selectByUserId(String userId);
	
	@Delete("delete from "+Constants.ADDRESSTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=AddressDynaSqlProvider.class,method="updateAddress")
	void update(Address adr);
	
	@InsertProvider(type=AddressDynaSqlProvider.class,method="insertAddress")
	Integer save(Address adr);
	
	@Update("update address set userId=#{user_id} where user_id=#{id}")
	Integer updateUserIdOdr(@Param("userId") String userId,@Param("id") String id);
	
	@Update("update address set tag=#{tag} where id=#{id}")
	Integer updateTag(@Param("tag") Integer tag,@Param("id") Integer id);
	
	@Update("update address set tag=#{tag} where tag=#{tag1}")
	Integer updateTag1(@Param("tag") Integer tag,@Param("tag1") Integer id);

}
