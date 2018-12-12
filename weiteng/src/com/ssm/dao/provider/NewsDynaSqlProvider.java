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
					WHERE("  entry_time >= #{news.startTime}");
				}
				if(news.getEndTime()!=null){
					WHERE("  entry_time <= #{news.endTime}");
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
						WHERE("  entry_time >= #{news.startTime}");
					}
					if(news.getEndTime()!=null){
						WHERE("  entry_time <= #{news.endTime}");
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
				if(news.getPublisher()!=null&& !news.getPublisher().equals("")){
					VALUES("publisher","#{publisher}");
				}
				if(news.getSource()!=null&& !news.getSource().equals("")){
					VALUES("source","#{source}");
				}
				if(news.getBrowseNum()!=null){
					VALUES("browse_num","#{browseNum}");
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
					VALUES("end_time","#{endTime}");
				}
				if(news.getEntryTime()!=null){
					VALUES("entry_time","#{entryTime}");
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
				if(news.getPublisher()!=null&& !news.getPublisher().equals("")){
					SET("publisher=#{publisher}");
				}
				if(news.getSource()!=null&& !news.getSource().equals("")){
					SET("source=#{source}");
				}
				if(news.getSort()!=null&& !news.getSort().equals("")){
					SET("sort=#{sort}");
				}
				if(news.getContents()!=null&& !news.getContents().equals("")){
					SET("contents=#{contents}");
				}
				if(news.getBrowseNum()!=null){
					SET("browse_num=#{browseNum}");
				}
				if(news.getState()!=null){
					SET("state=#{state}");
				}
				if(news.getEndTime()!=null){
					SET("end_time=#{endTime}");
				}
				if(news.getEntryTime()!=null){
					SET("entry_time=#{entryTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	

}
