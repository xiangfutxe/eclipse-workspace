package com.ssm.weixin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.ssm.weixin.pojo.menu.Menu;

public class MenuUtil {
	private static Logger log = LoggerFactory.getLogger(MenuUtil.class);
	//菜单创建（POST）
	public final static String menu_create_url = "http://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	//菜单查询（GET）
	public final static String menu_get_url = "http://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	//菜单删除（GET）
	public final static String menu_delete_url = "http://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/*
	 * 创建菜单
	 */
	public static boolean createMenu(Menu menu,String accessToken){
		boolean result = false;
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		String jsonMenu = JSONObject.fromObject(menu).toString();
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMenu);
		if(null!=jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if(0==errorCode){
				result = true;
			}else{
				result = false;
				log.error("创建菜单失败errcode:{}errmsg:{}",errorCode,errorMsg);
			}
		}
		return result;
	}
	

	/*
	 *查询菜单
	 */
	public static String getMenu(String accessToken){
		String result = null;
		String requestUrl = menu_get_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if(null!=jsonObject){
			
			result=  jsonObject.toString();
			
		}
		return result;
	}
	
	/*
	 * 删除菜单
	 */
	public static boolean deleteMenu(String accessToken){
		boolean result = false;
		String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "GET", null);
		if(null!=jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if(0==errorCode){
				result = true;
			}else{
				result = false;
				log.error("删除菜单失败errcode:{}errmsg:{}",errorCode,errorMsg);
			}
		}
		return result;
	}
}
