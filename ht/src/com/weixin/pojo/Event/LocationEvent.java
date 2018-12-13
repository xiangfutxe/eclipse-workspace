package com.weixin.pojo.Event;

/*
 * 上报地址位置事件
 * @author xiangfu
 * @date 2016-11-17
 */
public class LocationEvent extends BaseEvent {
	//地理位置纬度
	 private String Latutide;  
	 
	 //地理位置经度
	    private String Longitude;
	    //地理位置经度
	    private String Precision;
		public String getLatutide() {
			return Latutide;
		}
		public void setLatutide(String latutide) {
			Latutide = latutide;
		}
		public String getLongitude() {
			return Longitude;
		}
		public void setLongitude(String longitude) {
			Longitude = longitude;
		}
		public String getPrecision() {
			return Precision;
		}
		public void setPrecision(String precision) {
			Precision = precision;
		}

		
}
