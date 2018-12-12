package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.AdminDynaSqlProvider;
import com.ssm.dao.provider.AgentApplyDynaSqlProvider;
import com.ssm.pojo.AgentApply;
import com.ssm.utils.Constants;

public interface AgentApplyMapper {
	
	
	@SelectProvider(type=AgentApplyDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="agentMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="apply_id", property="applyId"),
			@Result(column="user_id", property="userId"),
			@Result(column="user_name", property="userName"),
			@Result(column="province", property="province"),
			@Result(column="city", property="city"),
			@Result(column="area", property="area"),
			@Result(column="mobile", property="mobile"),
			@Result(column="type", property="type"),
			@Result(column="review_time", property="reviewTime"),
			@Result(column="reviewer_id", property="reviewerId"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<AgentApply> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=AgentApplyDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	//动态插入用户
	@InsertProvider(type=AgentApplyDynaSqlProvider.class,method="insert")
	Integer save(AgentApply agent);
	
	@UpdateProvider(type=AgentApplyDynaSqlProvider.class,method="update")
	Integer update(AgentApply agent);
	
	@Select("select * from "+Constants.AGENTAPPLYTABLE+" where id=#{id}")
	@ResultMap("agentMap")
	AgentApply selectById(Integer id);
	
	@Select("select * from "+Constants.AGENTAPPLYTABLE+" where id=#{id} for update")
	@ResultMap("agentMap")
	AgentApply selectByIdForUpdate(Integer id);
	
	@Select("select * from "+Constants.AGENTAPPLYTABLE+" where user_id=#{userId} and type=#{type} and state>0")
	@ResultMap("agentMap")
	AgentApply selectByUserId(@Param("userId") String userId,@Param("type") Integer type);
	
	@Select("select * from "+Constants.AGENTAPPLYTABLE+" where user_id=#{userId} and state=#{state}")
	@ResultMap("agentMap")
	List<AgentApply> selectListByUserId(@Param("userId") String userId,@Param("state") Integer state);
	
	@Delete("delete from "+Constants.AGENTAPPLYTABLE+" where id=#{id}")
	Integer deleteById(Integer id);
	
}
