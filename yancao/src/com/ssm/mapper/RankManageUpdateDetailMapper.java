package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.RankManageUpdateDetailDynaSqlProvider;
import com.ssm.pojo.RankManageUpdateDetail;
import com.ssm.utils.Constants;

public interface RankManageUpdateDetailMapper {
		
	@SelectProvider(type=RankManageUpdateDetailDynaSqlProvider.class,method="selectWhitParam")
	List<RankManageUpdateDetail> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=RankManageUpdateDetailDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=RankManageUpdateDetailDynaSqlProvider.class,method="selectListWhitParam")
	List<RankManageUpdateDetail> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=RankManageUpdateDetailDynaSqlProvider.class,method="selectAllListWhitParam")
	List<RankManageUpdateDetail> selectAllByList(Map<String,Object> params);
	
	@Select("select * from "+Constants.RANKMANAGEUPDATEDETAILTABLE+" ")
	List<RankManageUpdateDetail> selectAllRankManageUpdateDetail();
	
	@Select("select * from "+Constants.RANKMANAGEUPDATEDETAILTABLE+" where state=2")
	List<RankManageUpdateDetail> selectRankManageUpdateDetailByAll();
	
	
	@Select("select * from "+Constants.RANKMANAGEUPDATEDETAILTABLE+" where id=#{id}")
	RankManageUpdateDetail selectById(Integer id);
	
	@Select("select * from "+Constants.RANKMANAGEUPDATEDETAILTABLE+" where userId=#{userId} and state>0")
	List<RankManageUpdateDetail> selectByUserId(String userId);
	
	@Update("update "+Constants.RANKMANAGEUPDATEDETAILTABLE+" set state=#{state} where id=#{id}")
	Integer updateForState(@Param("state") Integer state,@Param("id") Integer id);
	
	@Delete("delete from "+Constants.RANKMANAGEUPDATEDETAILTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=RankManageUpdateDetailDynaSqlProvider.class,method="updateRankManageUpdateDetail")
	Integer update(RankManageUpdateDetail RankManageUpdateDetail);
	
	@Update("update "+Constants.RANKMANAGEUPDATEDETAILTABLE+"  set rid=#{id} where userId=#{userId}")
	Integer updateRIdForUserId(@Param("id")Integer id,@Param("userId") String userId);
	
	@InsertProvider(type=RankManageUpdateDetailDynaSqlProvider.class,method="insertRankManageUpdateDetail")
	Integer save(RankManageUpdateDetail RankManageUpdateDetail);
	
	@Update("update "+Constants.RANKMANAGEUPDATEDETAILTABLE+"  set userId=#{userId} where userId=#{id}")
	Integer updateUserIdOfRankManageUpdateDetail(@Param("userId") String userId,@Param("id") String id);
	
	

}
