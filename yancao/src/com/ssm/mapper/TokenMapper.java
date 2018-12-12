package com.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.TokenDynaSqlProvider;
import com.ssm.pojo.Token;
import com.ssm.utils.Constants;

public interface TokenMapper {
		
	
	@UpdateProvider(type=TokenDynaSqlProvider.class,method="save")
	int save(Token token);
	
	@UpdateProvider(type=TokenDynaSqlProvider.class,method="update")
	int update(Token token);
	
	@UpdateProvider(type=TokenDynaSqlProvider.class,method="returnSql")
	int updateBySql(String sql);
	
	@UpdateProvider(type=TokenDynaSqlProvider.class,method="returnSql")
	int delete(String sql);
	
	@Select("select * from "+Constants.TOKENTABLE+" where id=#{id}")
	@Results(id="tokenMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="access_token", property="accessToken"),
			@Result(column="expires_in", property="expiresIn"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	Token selectById(Integer id);
	
	@Select("select * from "+Constants.TOKENTABLE+" where state=#{state}")
	@ResultMap("tokenMap")
	List<Token> selectByState(int state);
	
	
	


}
