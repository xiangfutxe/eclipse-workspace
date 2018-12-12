package com.ssm.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.SettleDayDynaSqlProvider;
import com.ssm.dao.provider.SettleDynaSqlProvider;
import com.ssm.pojo.SettleDay;
import com.ssm.pojo.Settle;
import com.ssm.utils.Constants;

public interface SettleDayMapper {
		
	
	@Select("select  COALESCE(max(weekTag),0)  from "+Constants.SETTLEDAYTABLE)
	int selectMaxWeek();
	
	@Select("select * from "+Constants.SETTLEDAYTABLE+" where id=#{id}")
	SettleDay selectById(Integer id);
	
	@Select("select * from "+Constants.SETTLEDAYTABLE+" where dayTime=#{date}")
	SettleDay selectByDayTime(Timestamp date);
	
	@Select("select * from "+Constants.SETTLEDAYTABLE+" where dayTime=#{date} for update")
	SettleDay selectByDayTimeForUpdate(Timestamp date);
	
	@Select("select * from "+Constants.SETTLEDAYTABLE+" where weekTag=#{weekTag} for update")
	SettleDay selectByWeekTagForUpdate(Integer weekTag);
	
	@Select("select * from "+Constants.SETTLEDAYTABLE+" where weekTag=#{weekTag}")
	List<SettleDay> selectByWeekTag(Integer weekTag);
	
	
	@Select("select * from "+Constants.SETTLEDAYTABLE+" where weekTag>0 order by id desc")
	List<SettleDay> selectByAll();
	
	@SelectProvider(type=SettleDayDynaSqlProvider.class,method="returnSql")
	List<SettleDay> selectBySql(String sql);
	
	@Delete("delete from "+Constants.SETTLEDAYTABLE+" where id=#{id}")
	Integer deleteById(Integer id);
	
	@Delete("delete from "+Constants.SETTLEDAYTABLE+" where dayTime=#{date}")
	Integer deleteByDayTime(Timestamp date);
	
	@UpdateProvider(type=SettleDayDynaSqlProvider.class,method="updateSettle")
	Integer update(SettleDay st);
	
	@InsertProvider(type=SettleDayDynaSqlProvider.class,method="insertSettle")
	Integer save(SettleDay st);
	
	@Select("select *  from "+Constants.SETTLEDAYTABLE+" order by id desc limit 1")
	SettleDay selectMaxDay();
	
	@Select("select *  from "+Constants.SETTLEDAYTABLE+" where tag=#{tag} order by id desc limit 1")
	SettleDay selectMaxDayForTag(Integer tag);
	


}
