package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.utils.Constants;

public class WeixinUserInfoDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.WEIXINUSERINFOTABLE);
			if(params.get("user")!=null){
				WeixinUserInfo user = (WeixinUserInfo) params.get("user");
				if(user.getNickName()!=null && !user.getNickName().equals("")){
					WHERE(" nick_name LIKE CONCAT ('%',#{user.nickName},'%')");
				}
				if(user.getUserId()!=null && !user.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{user.userId},'%')");
				}
				if(user.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{user.state},'%')");
				}
					
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" limit #{pageModel.startIndex},#{pageModel.pageSize} ";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.WEIXINUSERINFOTABLE);
				if(params.get("user")!=null){
					WeixinUserInfo user = (WeixinUserInfo) params.get("user");
					if(user.getNickName()!=null && !user.getNickName().equals("")){
						WHERE(" nick_name LIKE CONCAT ('%',#{user.nickName},'%')");
					}
					if(user.getUserId()!=null && !user.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{user.userId},'%')");
					}
					if(user.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{user.state},'%')");
					}
						
				}
				}
		}.toString();
	}
	
	public String insert(final WeixinUserInfo user){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.WEIXINUSERINFOTABLE);
				if(user.getOpenId()!=null&& !user.getOpenId().equals("")){
					VALUES("open_id","#{openId}");
				}
				if(user.getNickName()!=null&& !user.getNickName().equals("")){
					VALUES("nick_name","#{nickName}");
				}
				if(user.getCountry()!=null&& !user.getCountry().equals("")){
					VALUES("country","#{country}");
				}
				if(user.getProvince()!=null&& !user.getProvince().equals("")){
					VALUES("province","#{province}");
				}
				if(user.getCity()!=null&& !user.getCity().equals("")){
					VALUES("city","#{city}");
				}
				if(user.getHeadImgUrl()!=null&& !user.getHeadImgUrl().equals("")){
					VALUES("head_img_url","#{headImgUrl}");
				}
				if(user.getSubscribeTime()!=null&& !user.getSubscribeTime().equals("")){
					VALUES("subscribe_time","#{subscribeTime}");
				}
				if(user.getQrImg()!=null&& !user.getQrImg().equals("")){
					VALUES("qr_img","#{qrImg}");
				}
				if(user.getLanguage()!=null&& !user.getLanguage().equals("")){
					VALUES("language","#{language}");
				}
				if(user.getSubscribe()!=null){
					VALUES("subscribe","#{subscribe}");
				}
				if(user.getUserId()!=null){
					VALUES("user_id","#{userId}");
				}
				if(user.getQrId()!=null){
					VALUES("qr_id","#{qrId}");
				}
				if(user.getSex()!=null){
					VALUES("sex","#{sex}");
				}
				if(user.getState()!=null){
					VALUES("state","#{state}");
				}
				if(user.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
				}
				if(user.getEndTime()!=null){
					VALUES("end_time","#{endTime}");
				}
			}
		}.toString();
	}
	
	public String update(final WeixinUserInfo user){
		return new SQL(){
			{
				UPDATE(Constants.WEIXINUSERINFOTABLE);
				if(user.getNickName()!=null&& !user.getNickName().equals("")){
					SET("nick_name=#{nickName}");
				}
				if(user.getCountry()!=null&& !user.getCountry().equals("")){
					SET("country=#{country}");
				}
				if(user.getProvince()!=null&& !user.getProvince().equals("")){
					SET("province=#{province}");
				}
				if(user.getCity()!=null&& !user.getCity().equals("")){
					SET("city=#{city}");
				}
				if(user.getHeadImgUrl()!=null&& !user.getHeadImgUrl().equals("")){
					SET("head_img_url=#{headImgUrl}");
				}
				if(user.getSubscribeTime()!=null&& !user.getSubscribeTime().equals("")){
					SET("subscribe_time=#{subscribeTime}");
				}
				if(user.getQrImg()!=null&& !user.getQrImg().equals("")){
					SET("qr_img=#{qrImg}");
				}
				if(user.getLanguage()!=null&& !user.getLanguage().equals("")){
					SET("language=#{language}");
				}
				if(user.getSubscribe()!=null){
					SET("subscribe=#{subscribe}");
				}
				if(user.getUserId()!=null){
					SET("user_id=#{userId}");
				}
				if(user.getQrId()!=null){
					SET("qr_id=#{qrId}");
				}
				if(user.getSex()!=null){
					SET("sex=#{sex}");
				}
				if(user.getState()!=null){
					SET("state=#{state}");
				}
				if(user.getEndTime()!=null){
					SET("end_time=#{endTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	
	public String updateForOpenId(final WeixinUserInfo user){
		return new SQL(){
			{
				UPDATE(Constants.WEIXINUSERINFOTABLE);
				if(user.getNickName()!=null&& !user.getNickName().equals("")){
					SET("nick_name=#{nickName}");
				}
				if(user.getCountry()!=null&& !user.getCountry().equals("")){
					SET("country=#{country}");
				}
				if(user.getProvince()!=null&& !user.getProvince().equals("")){
					SET("province=#{province}");
				}
				if(user.getCity()!=null&& !user.getCity().equals("")){
					SET("city=#{city}");
				}
				if(user.getHeadImgUrl()!=null&& !user.getHeadImgUrl().equals("")){
					SET("head_img_url=#{headImgUrl}");
				}
				if(user.getSubscribeTime()!=null&& !user.getSubscribeTime().equals("")){
					SET("subscribe_time=#{subscribeTime}");
				}
				if(user.getQrImg()!=null&& !user.getQrImg().equals("")){
					SET("qr_img=#{qrImg}");
				}
				if(user.getLanguage()!=null&& !user.getLanguage().equals("")){
					SET("language=#{language}");
				}
				if(user.getSubscribe()!=null){
					SET("subscribe=#{subscribe}");
				}
				if(user.getUserId()!=null){
					SET("user_id=#{userId}");
				}
				if(user.getQrId()!=null){
					SET("qr_id=#{qrId}");
				}
				if(user.getSex()!=null){
					SET("sex=#{sex}");
				}
				if(user.getState()!=null){
					SET("state=#{state}");
				}
				if(user.getEndTime()!=null){
					SET("end_time=#{endTime}");
				}
				WHERE(" open_id=#{openId}");
			}
		}.toString();
	}
	

}
