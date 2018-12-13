package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.News;
import com.ssm.utils.Constants;

public class NewsDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			FROM(Constants.NEWSTABLE);
			if(params.get("news")!=null){
				News news = (News) params.get("news");
				if(news.getSort()!=null && !news.getSort().equals("")){
					WHERE(" sort LIKE CONCAT ('%',#{news.sort},'%')");
				}
				if(news.getState()!=null){
					WHERE(" state LIKE CONCAT ('%',#{news.state},'%')");
				}
				if(news.getStartTime()!=null){
					WHERE("  entryTime >= #{news.startTime}");
				}
				if(news.getEndTime()!=null){
					WHERE("  entryTime <= #{news.endTime}");
				}
			}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql +=" order by id desc limit #{pageModel.startIndex},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String count( final Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(Constants.NEWSTABLE);
				if(params.get("news")!=null){
					News news = (News) params.get("news");
					if(news.getSort()!=null && !news.getSort().equals("")){
						WHERE(" sort LIKE CONCAT ('%',#{news.sort},'%')");
					}
					if(news.getState()!=null){
						WHERE(" state LIKE CONCAT ('%',#{news.state},'%')");
					}
					if(news.getStartTime()!=null){
						WHERE("  entryTime >= #{news.startTime}");
					}
					if(news.getEndTime()!=null){
						WHERE("  entryTime <= #{news.endTime}");
					}
				}
				
			}
		}.toString();
	}
	
	public String insertNews(final News news){
		
		return new SQL(){
			{
				INSERT_INTO(Constants.NEWSTABLE);
				if(news.getTitle()!=null&& !news.getTitle().equals("")){
					VALUES("title","#{title}");
				}
				if(news.getAdmin()!=null&& !news.getAdmin().equals("")){
					VALUES("admin","#{admin}");
				}
				if(news.getSort()!=null&& !news.getSort().equals("")){
					VALUES("sort","#{sort}");
				}
				if(news.getContents()!=null&& !news.getContents().equals("")){
					VALUES("contents","#{contents}");
				}
				if(news.getState()!=null){
					VALUES("state","#{state}");
				}
				if(news.getEndTime()!=null){
					VALUES("endTime","#{endTime}");
				}
				if(news.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
			}
		}.toString();
	}
	
	public String updateNews(final News news){
		return new SQL(){
			{
				UPDATE(Constants.NEWSTABLE);
				if(news.getTitle()!=null&& !news.getTitle().equals("")){
					SET("title=#{title}");
				}
				if(news.getAdmin()!=null&& !news.getAdmin().equals("")){
					SET("admin=#{admin}");
				}
				if(news.getSort()!=null&& !news.getSort().equals("")){
					SET("sort=#{sort}");
				}
				if(news.getContents()!=null&& !news.getContents().equals("")){
					SET("contents=#{contents}");
				}
				if(news.getState()!=null){
					SET("state=#{state}");
				}
				if(news.getEndTime()!=null){
					SET("endTime=#{endTime}");
				}
				if(news.getEntryTime()!=null){
					SET("entryTime=#{entryTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
