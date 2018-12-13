package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.NewsMapper;
import com.ssm.pojo.News;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class NewsDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
   
    public Pager findByPage(News news,Pager pager){
    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("news",news);
			int recordCount = newsMapper.count(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<News> newslist = newsMapper.selectByPage(params);
			sqlSession.commit();
			pager.setResultList(newslist);
    	} finally {
			sqlSession.close();
    	}
    	return pager;
	}
    
    public String saveNews(News news){
    	String str = "";
    	try {
	    	newsMapper.save(news);
			 sqlSession.commit();
			str =news.getTitle()+"保存成功。";
	    	
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String updateNews(News news){
    	String str = "";
    	try {
	    	newsMapper.update(news);
			 sqlSession.commit();
			 str =news.getTitle()+"修改成功。";
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
 public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			newsMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
	}
 
 	
 
 	public News findById(Integer id){
 		News news = null;
 		try {
 			news = newsMapper.selectById(id);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return news;
		
	}
 	
 	
}
