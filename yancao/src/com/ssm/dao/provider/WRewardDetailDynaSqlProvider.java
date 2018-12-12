package com.ssm.dao.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.pojo.InventoryDetail;
import com.ssm.pojo.OrderDetail;
import com.ssm.pojo.WRewardDetail;
import com.ssm.utils.Constants;

public class WRewardDetailDynaSqlProvider {
	
	//分页动态查询
	public String selectWhitParam(final Map<String,Object> params){
		String sql = new SQL(){
			{
			SELECT ("*");
			if(params.get("tableName")!=null){
				String tableName = (String) params.get("tableName");
				FROM(tableName);
			}
			if(params.get("wrewardDetail")!=null){
				WRewardDetail wrewardDetail = (WRewardDetail) params.get("wrewardDetail");
				if(wrewardDetail.getUserId()!=null && !wrewardDetail.getUserId().equals("")){
					WHERE(" user_id LIKE CONCAT ('%',#{wrewardDetail.userId},'%')");
				}
				if(wrewardDetail.getUserName()!=null && !wrewardDetail.getUserName().equals("")){
					WHERE(" user_name LIKE CONCAT ('%',#{wrewardDetail.userName},'%')");
				}
				if(wrewardDetail.getUserByUserId()!=null && !wrewardDetail.getUserByUserId().equals("")){
					WHERE(" user_by_user_id LIKE CONCAT ('%',#{wrewardDetail.userByUserId},'%')");
				}
				if(wrewardDetail.getUserByUserName()!=null && !wrewardDetail.getUserByUserName().equals("")){
					WHERE(" user_by_user_name LIKE CONCAT ('%',#{wrewardDetail.userByUserName},'%')");
				}
				if(wrewardDetail.getWeekTag()!=null){
					WHERE(" weekTag LIKE CONCAT ('%',#{wrewardDetail.weekTag},'%')");
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
				if(params.get("tableName")!=null){
					String tableName = (String) params.get("tableName");
					FROM(tableName);
				}
				if(params.get("wrewardDetail")!=null){
					WRewardDetail wrewardDetail = (WRewardDetail) params.get("wrewardDetail");
					if(wrewardDetail.getUserId()!=null && !wrewardDetail.getUserId().equals("")){
						WHERE(" user_id LIKE CONCAT ('%',#{wrewardDetail.userId},'%')");
					}
					if(wrewardDetail.getUserName()!=null && !wrewardDetail.getUserName().equals("")){
						WHERE(" user_name LIKE CONCAT ('%',#{wrewardDetail.userName},'%')");
					}
					if(wrewardDetail.getUserByUserId()!=null && !wrewardDetail.getUserByUserId().equals("")){
						WHERE(" user_by_user_id LIKE CONCAT ('%',#{wrewardDetail.userByUserId},'%')");
					}
					if(wrewardDetail.getUserByUserName()!=null && !wrewardDetail.getUserByUserName().equals("")){
						WHERE(" user_by_user_name LIKE CONCAT ('%',#{wrewardDetail.userByUserName},'%')");
					}
					if(wrewardDetail.getWeekTag()!=null){
						WHERE(" weekTag LIKE CONCAT ('%',#{wrewardDetail.weekTag},'%')");
					}
				}
				}
		}.toString();
	}
	
	public String insertWRewardDetail(final WRewardDetail wrewardDetail,final String tableName){
		
		return new SQL(){
			{
				INSERT_INTO(tableName);
				if(wrewardDetail.getuId()!=null&& !wrewardDetail.getuId().equals("")){
					VALUES("uid","#{uid}");
				}
				if(wrewardDetail.getUserId()!=null&& !wrewardDetail.getUserId().equals("")){
					VALUES("user_id","#{userId}");
				}
				if(wrewardDetail.getUserName()!=null&& !wrewardDetail.getUserName().equals("")){
					VALUES("user_name","#{userName}");
				}
				if(wrewardDetail.getUserById()!=null){
					VALUES("user_by_id","#{userById}");
				}
				if(wrewardDetail.getUserByUserId()!=null&& !wrewardDetail.getUserByUserId().equals("")){
					VALUES("user_by_user_id","#{userByUserId}");
				}
				if(wrewardDetail.getUserByUserName()!=null&& !wrewardDetail.getUserByUserName().equals("")){
					VALUES("user_by_user_name","#{userByUserName}");
				}
				if(wrewardDetail.getType()!=null){
					VALUES("type","#{type}");
				}
				if(wrewardDetail.getWeekTag()!=null){
					VALUES("weekTag","#{weekTag}");
				}
				if(wrewardDetail.getRemark()!=null&& !wrewardDetail.getRemark().equals("")){
					VALUES("remark","#{remark}");
				}
				if(wrewardDetail.getEntryTime()!=null){
					VALUES("entryTime","#{entryTime}");
				}
			}
		}.toString();
	}
	
	public String updateWRewardDetail(final WRewardDetail wrewardDetail,final String tableName){
		return new SQL(){
			{
				UPDATE(tableName);
				if(wrewardDetail.getuId()!=null&& !wrewardDetail.getuId().equals("")){
					SET("uid=#{uid}");
				}
				if(wrewardDetail.getUserId()!=null&& !wrewardDetail.getUserId().equals("")){
					SET("user_id=#{userId}");
				}
				if(wrewardDetail.getUserName()!=null&& !wrewardDetail.getUserName().equals("")){
					SET("user_name=#{userName}");
				}
				if(wrewardDetail.getUserById()!=null){
					SET("user_by_id=#{userById}");
				}
				if(wrewardDetail.getUserByUserId()!=null&& !wrewardDetail.getUserByUserId().equals("")){
					SET("user_by_user_id=#{userByUserId}");
				}
				if(wrewardDetail.getUserByUserName()!=null&& !wrewardDetail.getUserByUserName().equals("")){
					SET("user_by_user_name=#{userByUserName}");
				}
				if(wrewardDetail.getAmount()!=null){
					SET("amount=#{amount}");
				}
				if(wrewardDetail.getType()!=null){
					SET("type=#{type}");
				}
				if(wrewardDetail.getWeekTag()!=null){
					SET("weekTag=#{weekTag}");
				}
				if(wrewardDetail.getRemark()!=null&& !wrewardDetail.getRemark().equals("")){
					SET("remark=#{remark}");
				}
				if(wrewardDetail.getEntryTime()!=null){
					SET("entryTime=#{entryTime}");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
	
	
	public String insertAll(Map<String,Object> params) {  
        StringBuilder sb = new StringBuilder();  
        String tableName = (String) params.get("tableName"); 
        sb.append("INSERT INTO "+tableName);  
        sb.append("(id,uid,user_id,user_name,user_by_id,user_by_user_id,user_by_user_name,type,amount,remark,entryTime) ");  
        sb.append("VALUES "); 
        if(params.get("wdlist")!=null){
        List<OrderDetail> list = (List<OrderDetail>) params.get("wdlist");  
        MessageFormat mf = new MessageFormat("(null, #'{'list[{0}].uid},#'{'list[{0}].userId},#'{'list[{0}].userName},#'{'list[{0}].userById},#'{'list[{0}].userByUserId}"
        		+ ",#'{'list[{0}].userByUserName},#'{'list[{0}].type},#'{'list[{0}].amount}"
        		+ ",#'{'list[{0}].remark},#'{'list[{0}].entryTime})");  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
          if(i<list.size()-1){
            	 sb.append(",");  
            }
        }  
		}
        return sb.toString();  
    }  

}
