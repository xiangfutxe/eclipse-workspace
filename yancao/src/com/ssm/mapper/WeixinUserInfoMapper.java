package com.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.ssm.dao.provider.WeixinUserInfoDynaSqlProvider;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.utils.Constants;

public interface WeixinUserInfoMapper {

	@SelectProvider(type=WeixinUserInfoDynaSqlProvider.class,method="selectWhitParam")
	@Results(id="userMap",value={
			@Result(id=true,column="id",property="id",javaType=Integer.class),
			@Result(column="open_id", property="openId"),
			@Result(column="user_id", property="userId"),
			@Result(column="subscribe", property="subscribe"),
			@Result(column="subscribe_time", property="subscribeTime"),
			@Result(column="nick_name", property="nickName"),
			@Result(column="sex", property="sex"),
			@Result(column="country", property="country"),
			@Result(column="province", property="province"),
			@Result(column="city", property="city"),
			@Result(column="language", property="language"),
			@Result(column="head_img_url", property="headImgUrl"),
			@Result(column="qr_id", property="qrId"),
			@Result(column="qr_img", property="qrImg"),
			@Result(column="state", property="state"),
			@Result(column="entry_time", property="entryTime"),
			@Result(column="end_time", property="endTime")
		})
	List<WeixinUserInfo> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type=WeixinUserInfoDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	//动态插入用户
	@UpdateProvider(type=WeixinUserInfoDynaSqlProvider.class,method="insert")
	int save(WeixinUserInfo user);
	
	@UpdateProvider(type=WeixinUserInfoDynaSqlProvider.class,method="update")
	int update(WeixinUserInfo user);
	
	@UpdateProvider(type=WeixinUserInfoDynaSqlProvider.class,method="updateForOpenId")
	int updateForOpenId(WeixinUserInfo user);
	
	@Select("select * from "+Constants.WEIXINUSERINFOTABLE+" where id=#{id}")
	@ResultMap("userMap")
	WeixinUserInfo selectById(Integer id);
	
	@Select("select * from "+Constants.WEIXINUSERINFOTABLE+" where user_id=#{userId}")
	@ResultMap("userMap")
	WeixinUserInfo selectByUserId(String userId);
	
	@Select("select * from "+Constants.WEIXINUSERINFOTABLE+" where qr_id=#{qrId}")
	@ResultMap("userMap")
	WeixinUserInfo selectByQrId(Integer qrId);
	
	@Select("select * from "+Constants.WEIXINUSERINFOTABLE+" where open_id=#{openId}")
	@ResultMap("userMap")
	WeixinUserInfo selectByOpenId(String openId);
	
	@Delete("delete from "+Constants.WEIXINUSERINFOTABLE+" where id=#{id}")
	int deleteById(Integer id);
	
	@Delete("update "+Constants.WEIXINUSERINFOTABLE+" set state=0 where id=#{id}")
	int deleteByIdForState(Integer id);
}
