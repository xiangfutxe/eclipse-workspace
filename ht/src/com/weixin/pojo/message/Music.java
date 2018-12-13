package com.weixin.pojo.message;


public class Music{
	//音乐标题
		  private String title;
		// 音乐描述
		  private String Description;
		  //音乐链接
		  private String MusicUrl;
		  //高质量音乐链接
		  private String HQMusicUrl;
		  //缩略图的媒体ID
		  private String ThumbMediaId;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getMusicUrl() {
			return MusicUrl;
		}
		public void setMusicUrl(String musicUrl) {
			MusicUrl = musicUrl;
		}
		public String getHQMusicUrl() {
			return HQMusicUrl;
		}
		public void setHQMusicUrl(String hQMusicUrl) {
			HQMusicUrl = hQMusicUrl;
		}
		public String getThumbMediaId() {
			return ThumbMediaId;
		}
		public void setThumbMediaId(String thumbMediaId) {
			ThumbMediaId = thumbMediaId;
		}
	
		 
}
