package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import com.ssm.dao.provider.NewsDynaSqlProvider;
import com.ssm.pojo.News;
import com.ssm.utils.Constants;

public interface NewsMapper {
		
	@SelectProvider(type=NewsDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="newsMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="title", property="title"),
			@Result(column="publisher", property="publisher"),
			@Result(column="source", property="source"),
			@Result(column="browse_num", property="browseNum"),
			@Result(column="sort", property="sort"),
			@Result(column="state", property="state"),
			@Result(column="image_url", property="imageUrl"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<News> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=NewsDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+Constants.NEWSTABLE+" where state=1 ")
	@ResultMap("newsMap")
	List<News> selectAllNews();
	
	
	@Select("select * from "+Constants.NEWSTABLE+" where id=#{id}")
	@ResultMap("newsMap")
	News selectById(Integer id);
	
	
	@Delete("delete from "+Constants.NEWSTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@UpdateProvider(type=NewsDynaSqlProvider.class,method="updateNews")
	int update(News adr);
	
	@InsertProvider(type=NewsDynaSqlProvider.class,method="insertNews")
	int save(News adr);

}
