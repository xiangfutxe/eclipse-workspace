package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.ssm.pojo.Unit;
import com.ssm.provider.UnitDynaSqlProvider;
import com.utils.Constants;

@Repository
public interface UnitMapper {
	
	
	@InsertProvider(type=UnitDynaSqlProvider.class,method="insert")
	public int insert(Unit unit);
	
	@Update("delete from "+Constants.UNITTABLE+" where id=#id")
	public int delete(Integer id);
	
	@UpdateProvider(type=UnitDynaSqlProvider.class,method="update")
	public int update(Unit unit);
	
	@UpdateProvider(type=UnitDynaSqlProvider.class,method="returnSql")
	public int updateBySql(String sql);
	
	@UpdateProvider(type=UnitDynaSqlProvider.class,method="returnSql")
	public int deleteBySql(String sql);
	
	@Select("select * from "+Constants.UNITTABLE+" where id=#{id}")
	@Results(id="UnitMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="name", property="name"),
			@Result(column="type", property="type"),
			@Result(column="parent_id", property="parentId"),
			@Result(column="state", property="state"),
			@Result(column="version", property="version"),
			@Result(column="end_time", property="endTime"),
			@Result(column="entry_time", property="entryTime")
	})
	public Unit getUnit(Long id);
	
	@Select("select * from "+Constants.UNITTABLE+" where id=#{id} for update")
	@ResultMap("UnitMap")
	public Unit getUnitForUpdate(Long id);
	
	@Select("select * from "+Constants.UNITTABLE+" where name=#{name}")
	@ResultMap("UnitMap")
	public Unit getUnitByName(String name);
	
	@SelectProvider(type=UnitDynaSqlProvider.class,method="selectWhitParam")
	@ResultMap("UnitMap")
	public List<Unit> findByList(Map<String,Object> params);
	
	@SelectProvider(type=UnitDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
	
	
}
