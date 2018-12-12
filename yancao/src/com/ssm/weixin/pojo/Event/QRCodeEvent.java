package com.ssm.weixin.pojo.Event;

/*
 * 扫描二维码事件
 * @author xiangfu
 * @date 2016-11-17
 */
public class QRCodeEvent extends BaseEvent {
	//事件KEY值
	 private String EventKey;  
	 
	 //用于换取二维码图片
	    private String Ticket;

		public String getEventKey() {
			return EventKey;
		}

		public void setEventKey(String eventKey) {
			EventKey = eventKey;
		}

		public String getTicket() {
			return Ticket;
		}

		public void setTicket(String ticket) {
			Ticket = ticket;
		}  
	    
	  
}
