package com.ssm.utils;

public class WeChatConstant {
	 /**Token*/
    public static final String TOKEN = "";
    /**EncodingAESKey*/
    public static final String AES_KEY = "";
    /**消息类型:文本消息*/
    public static final String MESSAGE_TYPE_TEXT = "text";
    /**消息类型:音乐*/
    public static final String MESSAGE_TYPE_MUSIC = "music";
    /**消息类型:图文*/
    public static final String MESSAGE_TYPE_NEWS = "news";
    /**消息类型:图片*/
    public static final String MESSAGE_TYPE_IMAGE = "image";
    /**消息类型:视频*/
    public static final String MESSAGE_TYPE_VIDEO = "video";
    /**消息类型:小视频*/
    public static final String MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
    /**消息类型:链接*/
    public static final String MESSAGE_TYPE_LINK = "link";
    /**消息类型:地理位置*/
    public static final String MESSAGE_TYPE_LOCATION = "location";
    /**消息类型:音频*/
    public static final String MESSAGE_TYPE_VOICE = "voice";
    /**消息类型:事件推送*/
    public static final String MESSAGE_TYPE_EVENT = "event";
    /**事件类型:subscribe(订阅)*/
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    /**事件类型：unsubscribe(取消订阅)*/
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    /**事件类型：CLICK(自定义菜单点击事件)*/
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /**返回消息类型:转发客服*/
    public static final String TRANSFER_CUSTOMER_SERVICE="transfer_customer_service";
    /**ACCESS_TOKEN*/
    public static final String ACCESS_TOKEN_ENAME = "access_token";
    /**返回成功字符串*/
    public static final String RETURN_SUCCESS = "SUCCESS";
    /**主动发送消息url*/
    public static final String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
    /**通过code获取授权access_token的URL*/
    public static final String GET_AUTHTOKEN_URL = " https://api.weixin.qq.com/sns/oauth2/access_token?";
}
