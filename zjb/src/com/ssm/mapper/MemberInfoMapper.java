package com.ssm.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.springframework.stereotype.Repository;
import com.ssm.pojo.Member;
import com.ssm.pojo.MemberInfo;
import com.ssm.provider.MemberInfoDynaSqlProvider;
import com.utils.Constants;

@Repository
public interface MemberInfoMapper {
	
	@InsertProvider(type=MemberInfoDynaSqlProvider.class,method="insert")
	public int insert(MemberInfo info);	
	
	@UpdateProvider(type=MemberInfoDynaSqlProvider.class,method="returnSql")
	public int deleteBySql(String sql);
	
	@Update("delete from "+Constants.MEMBERINFOTABLE+" where id=#id")
	public int delete(Long id);
	
	@UpdateProvider(type=MemberInfoDynaSqlProvider.class,method="update")
	public int update(MemberInfo Member);
	
	@UpdateProvider(type=MemberInfoDynaSqlProvider.class,method="updateForMid")
	public int updateForMid(MemberInfo Member);
	
	@UpdateProvider(type=MemberInfoDynaSqlProvider.class,method="returnSql")
	public int updateBySql(String sql);
	
	@Select("select * from "+Constants.MEMBERINFOTABLE+" where id=#{id}")
	@Results(id="memberInfoMap",value={
			@Result(id=true,column="id",property="id",javaType=Long.class),
			@Result(column="real_name", property="realName"),
			@Result(column="sex", property="sex"),
			@Result(column="birth", property="birth"),
			@Result(column="education", property="education"),
			@Result(column="marital_status", property="marital_status"),
			@Result(column="version", property="version"),
			@Result(column="mobile", property="mobile"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime"),
			@Result(column="state", property="state"),
			@Result(column="version", property="version"),
			@Result(column="member_id", property="memberId")
	})
	public MemberInfo getById(Integer id);
	
	@Select("select * from "+Constants.MEMBERINFOTABLE+" where id=#{id} for update")
	@ResultMap("memberInfoMap")
	public MemberInfo getByIdForUpdate(Long id);
	
	@Select("select * from "+Constants.MEMBERINFOTABLE+" where member_id=#{id}")
	@ResultMap("memberInfoMap")
	public MemberInfo getByMemberId(Long id);
	
	@Select("select * from "+Constants.MEMBERINFOTABLE+" where member_id=#{id} for update")
	@ResultMap("memberInfoMap")
	public MemberInfo getByMemberIdForUpdate(Long id);
	
	
}
