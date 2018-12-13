package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import com.ssm.dao.provider.AddressDynaSqlProvider;
import com.ssm.pojo.Address;
import com.ssm.utils.Constants;

public interface AddressMapper {
		
	@SelectProvider(type=AddressDynaSqlProvider.class,method="selectWhitParam")
	List<Address> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=AddressDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.ADDRESSTABLE+" where state=1 ")
	List<Address> selectAllAddress();
	
	
	@Select("select * from "+Constants.ADDRESSTABLE+" where id=#{id}")
	Address selectById(Integer id);
	
	
	@Select("select * from "+Constants.ADDRESSTABLE+" where userId=#{userId} and state=1")
	List<Address> selectByUserId(String userId);
	
	@Delete("delete from "+Constants.ADDRESSTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@SelectProvider(type=AddressDynaSqlProvider.class,method="updateAddress")
	void update(Address adr);
	
	@InsertProvider(type=AddressDynaSqlProvider.class,method="insertAddress")
	Integer save(Address adr);
	
	@Update("update address set userId=#{userId} where userId=#{id}")
	Integer updateUserIdOdr(@Param("userId") String userId,@Param("id") String id);

}
