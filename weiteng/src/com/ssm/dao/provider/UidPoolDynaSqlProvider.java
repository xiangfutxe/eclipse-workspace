package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.UidPool;
import com.ssm.pojo.User;
import com.ssm.utils.Constants;

public class UidPoolDynaSqlProvider {
	
	public String insertUidPool(final UidPool up){
		return new SQL(){
			{
			INSERT_INTO(Constants.UIDPOOLTABLE);
			if(up.getUid()!=null){
				VALUES("uid","#{uid}");
			}
			if(up.getTag()!=null){
				VALUES("tag","#{tag}");
			}
		}
		}.toString();
	}
	
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO uid_pool");  
        sb.append("(id,uid,tag,entry_time,end_time) ");  
        sb.append("VALUES "); 
        if(params.get("list")!=null){
        List<UidPool> list = (List<UidPool>) params.get("list");  
        MessageFormat mf = new MessageFormat("(null, #'{'list[{0}].uid},#'{'list[{0}].tag},#'{'list[{0}].entryTime},#'{'list[{0}].endTime})");  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < list.size() - 1) {  
                sb.append(",");  
            }  
        }  
		}
        return sb.toString();  
    }  
	
	public String updateUidPool(final UidPool up){
		return new SQL(){
			{
			UPDATE (Constants.UIDPOOLTABLE);
			if(up.getTag()!=null){
				SET("tag=#{tag}");
			}
			if(up.getEndTime()!=null){
				SET("end_time=#{endTime}");
			}
			WHERE(" id=#{id}");
		}
		}.toString();
	}

}
