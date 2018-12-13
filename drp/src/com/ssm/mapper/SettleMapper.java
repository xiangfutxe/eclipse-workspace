package com.ssm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import com.ssm.dao.provider.SettleDynaSqlProvider;
import com.ssm.pojo.Settle;
import com.ssm.utils.Constants;

public interface SettleMapper {
		
	
	@Select("select  COALESCE(max(weekTag),0)  from "+Constants.SETTLETABLE)
	int selectMaxWeek();
	
	@Select("select * from "+Constants.SETTLETABLE+" where id=#{id}")
	Settle selectById(Integer id);
	
	@Select("select * from "+Constants.SETTLETABLE+" where weekTag=#{weekTag}")
	Settle selectByWeekTag(Integer weekTag);
	
	@Select("select * from "+Constants.SETTLETABLE+" where weekTag=#{weekTag} for update")
	Settle selectByWeekTagForUpdate(Integer weekTag);
	
	
	@Select("select * from "+Constants.SETTLETABLE+" where weekTag>0")
	List<Settle> selectByAll();
	
	@Delete("delete from "+Constants.SETTLETABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@Delete("delete from "+Constants.SETTLETABLE+" where weekTag=#{weekTag}")
	int deleteByWeekTag(Integer weekTag);
	
	@UpdateProvider(type=SettleDynaSqlProvider.class,method="updateSettle")
	int update(Settle st);
	
	@InsertProvider(type=SettleDynaSqlProvider.class,method="insertSettle")
	int save(Settle st);
	


}
