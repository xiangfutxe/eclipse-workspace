package com.ssm.service;

import java.util.List;

import com.ssm.pojo.News;
import com.utils.Pager;

public interface NewsService {
	
	public int insertNews(News news);
	
	public int updateNews(News news);
	
	public int deleteAll(String str);
	
	
	public int removeAll(String str);
	
	public int insertNewsList(List<News> newsList);
	
	public News findById(Long id);
	
	public Pager findByPager(News news,Pager pager);

}
