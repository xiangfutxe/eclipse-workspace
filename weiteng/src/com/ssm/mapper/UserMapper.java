package com.ssm.mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;


import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.UserDynaSqlProvider;
import com.ssm.pojo.User;
import com.ssm.utils.Constants;
public interface UserMapper {
	
	String headstr = "select us.id id,us.user_id userId,us.user_name userName,uf.nick_name nickName,us.rank_join rankJoin,us.rank_join_old rankJoinOld,"
			+ "us.rmoney rmoney,us.cash cash,us.cash_num cashNum,us.pre_rmoney preRmoney,us.integral integral,us.pre_cash preCash,us.pre_cash_num preCashNum,us.pre_integral preIntegral,"
			+ "us.referee_id refereeId,us.referee_user_id refereeUserId,us.referee_node refereeNode,us.state state,us.entry_time entryTime,"
			+ "us.referee_num refereeNum,us.referee_num_1 refereeNum_1,us.referee_num_2 refereeNum2,us.referee_num_3 refereeNum3,us.referee_num_4 refereeNum4,"
			+ "us.referee_num_5 refereeNum5,us.referee_num_6 refereeNum6,us.referee_num_7 refereeNum7,us.referee_virtual_num refereeVirtualNum,us.agent_tag agentTag,"
			+ "uf.password password,uf.password2 password2,uf.open_id openId,uf.head_img_url headImgUrl,"
			+ "uf.document_type documentType,uf.num_id numId,uf.sex sex,uf.mobile mobile,uf.province province,"
			+ "uf.city city,uf.area area,uf.address address,uf.account_id accountId,uf.account_name accountName,"
			+ "uf.bank_adr bankAdr,uf.bank_name bankName from users us left join user_info uf on us.user_id=uf.user_id";
	
	@Select("select * from  "+Constants.USERINFOTABLE+" where user_id=#{userId} and password=#{password}" )
	@Results(id="infoMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="user_id", property="userId"),
			@Result(column="user_name", property="userName"),
			@Result(column="nick_name", property="nickName"),
			@Result(column="password", property="password"),
			@Result(column="password2", property="password2"),
			@Result(column="sex", property="sex"),
			@Result(column="age", property="age"),
			@Result(column="mobile", property="mobile"),
			@Result(column="document_ype", property="documentType"),
			@Result(column="num_id", property="numId"),
			@Result(column="open_id", property="openId"),
			@Result(column="province", property="province"),
			@Result(column="city", property="city"),
			@Result(column="area", property="area"),
			@Result(column="pay_tag", property="payTag"),
			@Result(column="address", property="address"),
			@Result(column="account_id", property="accountId"),
			@Result(column="account_name", property="accountName"),
			@Result(column="bank_name", property="bankName"),
			@Result(column="bank_adr", property="bankAdr"),
			@Result(column="state", property="state")
		})
	public User login(@Param("userId") String userId,@Param("password") String password);
	
	@Select(headstr+" where us.user_id=#{userId}" )
	public User selectAllByUserId(@Param("userId") String userId);
	
	@Select("select * from "+Constants.USERINFOTABLE+"  where user_id=#{userId}" )
	@ResultMap("infoMap")
	public User selectUserInfoByUserId(@Param("userId") String userId);
	
	@Select("select * from "+Constants.USERINFOTABLE+"  where id=#{id}" )
	@ResultMap("infoMap")
	public User selectUserInfoById(@Param("id") Integer id);
	
	
	@Select("select * from users  where id=#{id}" )
	@Results(id="userMap",value={
		@Result(id=true,column="id",property="id",javaType=Integer.class),
		@Result(column="user_id", property="userId"),
		@Result(column="user_name", property="userName"),
		@Result(column="rmoney", property="rmoney"),
		@Result(column="pre_rmoney", property="preRmoney"),
		@Result(column="cash", property="cash"),
		@Result(column="pre_cash", property="preCash"),
		@Result(column="cash_num", property="cashNum"),
		@Result(column="pre_cash_num", property="preCashNum"),
		@Result(column="integral", property="integral"),
		@Result(column="pre_integral", property="preIntegral"),
		@Result(column="referee_id", property="refereeId"),
		@Result(column="referee_user_id", property="refereeUserId"),
		@Result(column="referee_node", property="refereeNode"),
		@Result(column="referee_all", property="refereeAll"),
		@Result(column="state", property="state"),
		@Result(column="entry_time", property="entryTime"),
		@Result(column="rank_join", property="rankJoin"),
		@Result(column="rank_join_old", property="rankJoinOld"),
		@Result(column="referee_num", property="refereeNum"),
		@Result(column="referee_num_1", property="refereeNum1"),
		@Result(column="referee_num_2", property="refereeNum2"),
		@Result(column="referee_num_3", property="refereeNum3"),
		@Result(column="referee_num_4", property="refereeNum4"),
		@Result(column="referee_num_5", property="refereeNum5"),
		@Result(column="referee_num_6", property="refereeNum6"),
		@Result(column="referee_num_7", property="refereeNum7"),
		@Result(column="province", property="province"),
		@Result(column="city", property="city"),
		@Result(column="area", property="area"),
		@Result(column="referee_virtual_num", property="refereeVirtualNum"),
		@Result(column="agent_tag", property="agentTag"),
		@Result(column="pay_tag", property="payTag"),
		@Result(column="pre_time", property="preTime"),
		@Result(column="view_num", property="viewNum"),
		@Result(column="version", property="version")
	})
	public User selectUsersById(@Param("id") Integer id);
	
	
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectAllListWhitParam")
	public List<User> selectByList(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectAllWhitParam")
	public List<User> selectByPage(Map<String,Object> params);
	

	@Select(headstr+" where us.user_id=#{userId}" )
	public User selectByUserId(@Param("userId") String userId);
	
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectUserByListForUpdate")
	@ResultMap("userMap")
	public List<User> selectUserByListForUpdate(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectUserBySql")
	@ResultMap("userMap")
	public List<User> selectUserBySql(String sql);
	
	
	@Select("select * from users  where user_id=#{userId} for update" )
	@ResultMap("userMap")
	public User selectByUserIdForUpdate(@Param("userId") String userId);
	
	@Select("select * from users  where id=#{id} for update" )
	@ResultMap("userMap")
	public User selectUsersByIdForUpdate(@Param("id") Integer id);
	
	@Select("select * from users  where user_id=#{userId}" )
	@ResultMap("userMap")
	public User selectUsersByUserId(@Param("userId") String userId);


	
	
	@Select(headstr+" where us.id=#{id}" )
	public User selectById(@Param("id") Integer id);
	
	
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWhitParam")
	@ResultMap("userMap")
	List<User> selectUsersByPage(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectListWhitParam")
	@ResultMap("userMap")
	List<User> selectUsersByList(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="countAll")
	Integer countAll(Map<String,Object> params);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="maxId")
	Integer maxId(Map<String,Object> params);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateUser")
	Integer updateUser(User user);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateUserInfo")
	Integer updateUserInfo(User user);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateUserForField")
	Integer updateUserForField(String field,String value,String id);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateUserForField2")
	Integer updateUserForField2(String field1,String value1,String field2,String value2,String id);
	
	@Update("update "+Constants.USERINFOTABLE+" set user_id=#{userId} where user_id=#{id}")
	Integer updateUserIdOfUserInfo(@Param("userId") String userId,@Param("id") String id);
	
	@Update("update "+Constants.USERSTABLE+" set user_id=#{userId} where user_id=#{id}")
	Integer updateUserIdOfUsers(@Param("userId") String userId,@Param("id") String id);
	
	@Update("update "+Constants.USERSTABLE+" set state=#{state} where user_id=#{userId}")
	Integer updateUsersForState(@Param("state") Integer state,@Param("userId") String userId);
	
	@Update("update "+Constants.USERINFOTABLE+" set state=#{state} where user_id=#{userId}")
	Integer updateUserInfoForState(@Param("state") Integer state,@Param("userId") String userId);
	
	@InsertProvider(type=UserDynaSqlProvider.class,method="insertUser")
	@Options(useGeneratedKeys=true, keyProperty="id")
	Integer saveUser(User user);
	
	@InsertProvider(type=UserDynaSqlProvider.class,method="insertUserInfo")
	Integer saveUserInfo(User user);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updatePropertyOfUsers")
	Integer updateUsersForProperty(@Param("property") String property,@Param("value") String value,@Param("id") Integer id,@Param("property1") String property1,@Param("value1") String value1);

	@UpdateProvider(type=UserDynaSqlProvider.class,method="updatePropertyOfUserInfo")
	Integer updateUserInfoForProperty( String property,String value,String property1,String value1);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateOfUsers")
	Integer updateOfUsers( String property,String value,String property1,String value1);
	
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateForSql")
	Integer updateForSql( String sql);
	
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectPropertyOfUsers")
	@ResultMap("userMap")
	List<User> selectUsersForProperty(@Param("property") String property,@Param("value") Integer value);

}

