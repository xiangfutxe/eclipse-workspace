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

import com.ssm.pojo.News;
import com.ssm.provider.NewsDynaSqlProvider;
import com.ssm.provider.NewsDynaSqlProvider;
import com.utils.Constants;

@Repository
public interface NewsMapper {
	
	
	
	@InsertProvider(type=NewsDynaSqlProvider.class,method="insert")
	public int insert(News news);
	
	@Update("delete from "+Constants.NEWSTABLE+" where id=#id")
	public int delete(Integer id);
	
	@UpdateProvider(type=NewsDynaSqlProvider.class,method="update")
	public int update(News news);
	
	@UpdateProvider(type=NewsDynaSqlProvider.class,method="returnSql")
	public int updateBySql(String sql);
	
	@UpdateProvider(type=NewsDynaSqlProvider.class,method="returnSql")
	public int deleteBySql(String sql);
	
	@Select("select * from "+Constants.NEWSTABLE+" where id=#{id}")
	@Results(id="newsMap",value={
			@Result(id=true,column="id",property="id",javaType=Long.class),
			@Result(column="title", property="title"),
			@Result(column="sort", property="sort"),
			@Result(column="contents", property="contents"),
			@Result(column="source", property="source"),
			@Result(column="clicks", property="clicks"),
			@Result(column="end_time", property="endTime"),
			@Result(column="entry_time", property="entryTime")
	})
	public News getNews(Long id);
	
	@Select("select * from "+Constants.NEWSTABLE+" where id=#{id} for update")
	@ResultMap("newsMap")
	public News getNewsForUpdate(Long id);
	
	@SelectProvider(type=NewsDynaSqlProvider.class,method="selectWhitParam")
	@ResultMap("newsMap")
	public List<News> findByList(Map<String,Object> params);
	
	@SelectProvider(type=NewsDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
	
	@UpdateProvider(type=NewsDynaSqlProvider.class,method="insertAll")
	public int insertAll(Map<String,Object> params);
	
}
