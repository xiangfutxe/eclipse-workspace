package com.ssm.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.Admin;
import com.ssm.pojo.News;
import com.utils.Constants;

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
				SELECT("ifnull(count(*),0)");
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
	
	public String insert(final News news){
		
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
				if(news.getSort()!=null&& !news.getSort().equals("")){
					VALUES("sort","#{sort}");
				}
				if(news.getContents()!=null&& !news.getContents().equals("")){
					VALUES("contents","#{contents}");
				}
				if(news.getState()!=null){
					VALUES("state","#{state}");
				}
				if(news.getClicks()!=null){
					VALUES("clicks","#{clicks}");
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
	
	public String update(final News news){
		return new SQL(){
			{
				UPDATE(Constants.NEWSTABLE);
				if(news.getTitle()!=null&& !news.getTitle().equals("")){
					SET("title=#{title}");
				}
				if(news.getPublisher()!=null&& !news.getPublisher().equals("")){
					SET("publisher=#{publisher}");
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
				if(news.getClicks()!=null){
					VALUES("clicks","#{clicks}");
				}
				
				if(news.getEndTime()!=null){
					SET("end_time=#{endTime}");
				}
				if(news.getEntryTime()!=null){
					SET("entry_time=#{entryTime}");
				}
				SET("version=#{version}+1");
				WHERE(" id=#{id} and version=#{version}");
			}
		}.toString();
	}
	
	@SuppressWarnings("unchecked")
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder(); 
        String tableName=(String) params.get("tableName");
        sb.append("INSERT INTO "+tableName);  
        sb.append("(id,title,contents,sort,source,publisher,clicks,version,state,entry_time,end_time)");  
        sb.append(" VALUES "); 
        if(params.get("list")!=null){
        List<Admin> list = (List<Admin>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null,#'{'list[{0}].title},#'{'list[{0}].contents}"
        		+ ",#'{'list[{0}].sort},#'{'list[{0}].source},#'{'list[{0}].publisher}"
        		+ ",#'{'list[{0}].clicks},#'{'list[{0}].version},#'{'list[{0}].state}"
        		+ ",#'{'list[{0}].entryTime},#'{'list[{0}].endTime})");  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i})); 
          if(i<list.size()-1){
            	 sb.append(",");  
            }
        }  
		}
        return sb.toString();  
    }  
	
	public String returnSql(String sql){
		return sql;
	}

}
