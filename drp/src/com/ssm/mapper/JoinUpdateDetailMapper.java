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

import com.ssm.dao.provider.JoinUpdateDetailDynaSqlProvider;
import com.ssm.pojo.JoinUpdateDetail;
import com.ssm.utils.Constants;

public interface JoinUpdateDetailMapper {
		
	@SelectProvider(type=JoinUpdateDetailDynaSqlProvider.class,method="selectWhitParam")
	List<JoinUpdateDetail> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=JoinUpdateDetailDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=JoinUpdateDetailDynaSqlProvider.class,method="selectListWhitParam")
	List<JoinUpdateDetail> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=JoinUpdateDetailDynaSqlProvider.class,method="selectAllListWhitParam")
	List<JoinUpdateDetail> selectAllByList(Map<String,Object> params);
	
	@Select("select * from "+Constants.JOINUPDATEDETAILTABLE+" ")
	List<JoinUpdateDetail> selectAllJoinUpdateDetail();
	
	@Select("select * from "+Constants.JOINUPDATEDETAILTABLE+" where state=2")
	List<JoinUpdateDetail> selectJoinUpdateDetailByAll();
	
	
	@Select("select * from "+Constants.JOINUPDATEDETAILTABLE+" where id=#{id}")
	JoinUpdateDetail selectById(Integer id);
	
	@Select("select * from "+Constants.JOINUPDATEDETAILTABLE+" where userId=#{userId} and state>0")
	List<JoinUpdateDetail> selectByUserId(String userId);
	
	@Update("update "+Constants.JOINUPDATEDETAILTABLE+" set state=#{state} where id=#{id}")
	Integer updateForState(@Param("state") Integer state,@Param("id") Integer id);
	
	@Delete("delete from "+Constants.JOINUPDATEDETAILTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=JoinUpdateDetailDynaSqlProvider.class,method="updateJoinUpdateDetail")
	Integer update(JoinUpdateDetail JoinUpdateDetail);
	
	@Update("update JoinUpdateDetail set rid=#{id} where userId=#{userId}")
	Integer updateRIdForUserId(@Param("id")Integer id,@Param("userId") String userId);
	
	@InsertProvider(type=JoinUpdateDetailDynaSqlProvider.class,method="insertJoinUpdateDetail")
	Integer save(JoinUpdateDetail JoinUpdateDetail);
	
	@Update("update JoinUpdateDetail set userId=#{userId} where userId=#{id}")
	Integer updateUserIdOfJoinUpdateDetail(@Param("userId") String userId,@Param("id") String id);
	
	

}
