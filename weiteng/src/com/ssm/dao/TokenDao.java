package com.ssm.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.TokenMapper;
import com.ssm.pojo.Token;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.weixin.utils.CommonUtil;
import com.ssm.weixin.utils.SignUtil;

public class TokenDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    TokenMapper tokenMapper = sqlSession.getMapper(TokenMapper.class);
   
    public String getToken(Timestamp date){
    	 String token ="";
	      boolean b = true;
	        	try {
	        		List<Token> tlist = tokenMapper.selectByState(1);
	        		if(tlist!=null&&tlist.size()>0) {
		        		for(int i=0;i<1;i++) {
		        			 int  id =tlist.get(i).getId();
			      	    	   token = tlist.get(i).getAccessToken();
			      	    	   int expiresIn = tlist.get(i).getExpiresIn();
			      	    	   Timestamp entryTime= tlist.get(i).getEntryTime();
			      	    	if(date.getTime()-entryTime.getTime()-expiresIn*1000L>0) {
			      	    		b=false;
				      	    	String sql1 = "update token set state='0',end_time='"+date+"' where id='"+id+"'";
				      	    	tokenMapper.updateBySql(sql1);
			      	    	}
			      	    }
	        		}else b = false;	
	        		
	        		if(!b) {
		      	    	 Token AccessToken= CommonUtil.getToken(SignUtil.APPID, SignUtil.APPSECRET);
		      	    	 token = AccessToken.getAccessToken();
		      	    	 Token tk = new Token();
		      	    	 tk.setAccessToken(token);
		      	    	 tk.setExpiresIn(AccessToken.getExpiresIn());
		      	    	 tk.setState(1);
		      	    	 tk.setEntryTime(date);
		      	    	 tk.setEndTime(date);
			      	    tokenMapper.save(tk);
	        		}
	        		 sqlSession.commit();
	        	}catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}finally {
    	    		sqlSession.close();
    	        }
	        	return token;
    }
    
   
}
