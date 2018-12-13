package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.springframework.stereotype.Repository;

import com.ssm.pojo.Member;
import com.ssm.provider.MemberDynaSqlProvider;
import com.utils.Constants;

@Repository
public interface MemberMapper {
	
	@InsertProvider(type=MemberDynaSqlProvider.class,method="insert")
	@Options(useGeneratedKeys=true, keyProperty="id")
	public int insert(Member Member);	
	
	@UpdateProvider(type=MemberDynaSqlProvider.class,method="returnSql")
	public int deleteBySql(String sql);
	
	@Update("delete from "+Constants.MEMBERTABLE+" where id=#id")
	public int delete(Long id);
	
	@UpdateProvider(type=MemberDynaSqlProvider.class,method="update")
	public int update(Member Member);
	
	@UpdateProvider(type=MemberDynaSqlProvider.class,method="returnSql")
	public int updateBySql(String sql);
	
	@Select("select * from "+Constants.MEMBERTABLE+" where id=#{id}")
	@Results(id="memberMap",value={
			@Result(id=true,column="id",property="id",javaType=Long.class),
			@Result(column="nick_name", property="nickName"),
			@Result(column="password", property="password"),
			@Result(column="password2", property="password2"),
			@Result(column="rank", property="rank"),
			@Result(column="emoney", property="emoney"),
			@Result(column="integral", property="integral"),
			@Result(column="state", property="state"),
			@Result(column="head", property="head"),
			@Result(column="version", property="version"),
			@Result(column="view_num", property="viewNum"),
			@Result(column="end_time", property="endTime"),
			@Result(column="entry_time", property="entryTime")
	})
	public Member getById(Long id);
	
	@Select("select * from "+Constants.MEMBERTABLE+" where id=#{id} for update")
	@ResultMap("memberMap")
	public Member getByIdForUpdate(Long id);
	
	@Select("select * from "+Constants.MEMBERTABLE+" where nick_name=#{name}")
	@ResultMap("memberMap")
	public Member getMemberByName(String name);
	
	@Select("select * from "+Constants.MEMBERTABLE+" where nick_name=#{name} for update")
	@ResultMap("memberMap")
	public Member getMemberByNameForUpdate(String name);
	
	@SelectProvider(type=MemberDynaSqlProvider.class,method="findAllById")
	public Member findAllById(Long id);
	
	@SelectProvider(type=MemberDynaSqlProvider.class,method="findAllByIdForInfo")
	public Member findAllByIdForInfo(Long id);
	
	@SelectProvider(type=MemberDynaSqlProvider.class,method="selectWhitParam")
	public List<Member> findByPager(Map<String,Object> params);
	
	@SelectProvider(type=MemberDynaSqlProvider.class,method="count")
	public int count(Map<String,Object> params);
	
}
