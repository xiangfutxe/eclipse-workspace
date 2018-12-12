package com.ssm.weixin.pojo.message;

public class BaseMessage {
	//开发者微信号
	 private String ToUserName;  
	 
	 //发送方账号
	    private String FromUserName;  
	    
	    //消息创建时间（整型类）
	    private Long CreateTime;  
	    
	    //消息类型(text/image/location/link/voice)
	    private String MsgType ;  
	    
	   
	  
	    public String getToUserName() {  
	        return ToUserName;  
	    }  
	  
	    public void setToUserName(String toUserName) {  
	        ToUserName = toUserName;  
	    }  
	  
	    public String getFromUserName() {  
	        return FromUserName;  
	    }  
	  
	    public void setFromUserName(String fromUserName) {  
	        FromUserName = fromUserName;  
	    }  
	  
	    public Long getCreateTime() {  
	        return CreateTime;  
	    }  
	  
	    public void setCreateTime(Long createTime) {  
	        CreateTime = createTime;  
	    }  
	  
	    public String getMsgType() {  
	        return MsgType;  
	    }  
	  
	    public void setMsgType(String msgType) {  
	        MsgType = msgType;  
	    }  
	  
	 
}
