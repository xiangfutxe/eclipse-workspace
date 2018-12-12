package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.WRewardDetailDynaSqlProvider;
import com.ssm.pojo.WRewardDetail;

public interface WRewardDetailMapper {
		
	@SelectProvider(type=WRewardDetailDynaSqlProvider.class,method="selectWhitParam")
	List<WRewardDetail> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=WRewardDetailDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@UpdateProvider(type=WRewardDetailDynaSqlProvider.class,method="updateWRewardDetail")
	Integer update(WRewardDetail WRewardDetail,String tableName);
	
	@InsertProvider(type=WRewardDetailDynaSqlProvider.class,method="insertWRewardDetail")
	Integer save(WRewardDetail WRewardDetail,String tableName);
	
	@InsertProvider(type=WRewardDetailDynaSqlProvider.class,method="insertAll")
	Integer saveAll(Map<String,Object> params);

}
