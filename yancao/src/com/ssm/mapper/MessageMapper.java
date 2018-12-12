package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import com.ssm.dao.provider.MessageDynaSqlProvider;
import com.ssm.pojo.Message;
import com.ssm.utils.Constants;

public interface MessageMapper {
		
	@SelectProvider(type=MessageDynaSqlProvider.class,method="selectWhitParam")
	List<Message> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=MessageDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.MESSAGETABLE+" where state=1 ")
	List<Message> selectAllMessage();
	
	
	@Select("select * from "+Constants.MESSAGETABLE+" where id=#{id}")
	Message selectById(Integer id);
	
	
	@Delete("delete from "+Constants.MESSAGETABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=MessageDynaSqlProvider.class,method="updateMessage")
	Integer update(Message msg);
	
	@InsertProvider(type=MessageDynaSqlProvider.class,method="insertMessage")
	int save(Message adr);

}
