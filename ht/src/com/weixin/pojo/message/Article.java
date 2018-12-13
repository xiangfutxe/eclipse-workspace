package com.weixin.pojo.message;

public class Article {
		//图文标题
		  private String Title;
		  //图文消息描述
		  private String Description;
		  //图片链接，支持JPG、PNG格式，较好的效果图为大图630*320，小图80*80
		  private String PicUrl;
		  //点击图文跳转链接
		  private String Url;
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getPicUrl() {
			return PicUrl;
		}
		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}
		public String getUrl() {
			return Url;
		}
		public void setUrl(String url) {
			Url = url;
		}
		 
		
}
