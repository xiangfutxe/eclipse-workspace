package com.pojo;

import java.util.List;

@SuppressWarnings("serial")
public class PicAndTextMsg  extends OutputMessage{
		
		  private String MsgType ="Image";
		  private int ArticleCount;
		  private List<Article> Articles;
		public String getMsgType() {
			return MsgType;
		}
		public void setMsgType(String msgType) {
			MsgType = msgType;
		}
		public int getArticleCount() {
			return ArticleCount;
		}
		public void setArticleCount(int articleCount) {
			ArticleCount = articleCount;
		}
		public List<Article> getArticles() {
			return Articles;
		}
		public void setArticles(List<Article> articles) {
			Articles = articles;
		}
		 
	
}
