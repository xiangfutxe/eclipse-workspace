package com.pojo;

@SuppressWarnings("serial")
public class ItemOutputMessage  extends OutputMessage{
	/** 
     * 消息类型:图片消息 
     */  
    private String MsgType = "image";  
    /** 
     * 图片消息 
     */  
    private String MediaId;  
    
    private String PicUrl;
    
    public ItemOutputMessage() {  
    }  
  
    /** 
     * 创建一个自定义文本内容content的Output Message. 
     * 
     * @param content 文本内容 
     */  
    public ItemOutputMessage(String media_id,String pic_url) {  
    	MediaId = media_id;  
    	PicUrl = pic_url;
    }  

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}  
  
}
