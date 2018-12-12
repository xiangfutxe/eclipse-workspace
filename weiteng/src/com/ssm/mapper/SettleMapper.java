package com.ssm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import com.ssm.dao.provider.SettleDynaSqlProvider;
import com.ssm.pojo.Settle;
import com.ssm.utils.Constants;

public interface SettleMapper {
		
	
	@Select("select  COALESCE(max(month_tag),0)  from "+Constants.SETTLETABLE)
	int selectMaxWeek();
	
	@Select("select * from "+Constants.SETTLETABLE+" where id=#{id}")
	@Results(id="settleMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="total_price", property="totalPrice"),
			@Result(column="new_price", property="newPrice"),
			@Result(column="start_time", property="startTime"),
			@Result(column="month_tag", property="monthTag"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	Settle selectById(Integer id);
	
	@Select("select * from "+Constants.SETTLETABLE+" where month_tag=#{monthTag}")
	@ResultMap("settleMap")
	Settle selectByMonthTag(Integer monthTag);
	
	@Select("select * from "+Constants.SETTLETABLE+" where month_tag=#{monthTag} for update")
	@ResultMap("settleMap")
	Settle selectByMonthTagForUpdate(Integer monthTag);
	
	
	@Select("select * from "+Constants.SETTLETABLE+" where month_tag>0")
	@ResultMap("settleMap")
	List<Settle> selectByAll();
	
	@Delete("delete from "+Constants.SETTLETABLE+" where id=#{id}")
	Integer deleteById(Integer id);
	
	@Delete("delete from "+Constants.SETTLETABLE+" where month_tag=#{monthTag}")
	Integer deleteByMonthTag(Integer monthTag);
	
	@UpdateProvider(type=SettleDynaSqlProvider.class,method="updateSettle")
	int update(Settle st);
	
	@InsertProvider(type=SettleDynaSqlProvider.class,method="insertSettle")
	Integer save(Settle st);

}
