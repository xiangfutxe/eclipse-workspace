package com.ssm.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.UidPoolMapper;
import com.ssm.pojo.UidPool;
import com.ssm.utils.SqlSessionFactoryUtils;

public class UidPoolDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    UidPoolMapper uidPoolMapper = sqlSession.getMapper(UidPoolMapper.class);
    
   public Integer[] getUidList(){
	   Integer[] uid = {0,0,0};
	   try {
	    		List<UidPool> uplist = uidPoolMapper.getUidList();
	    		if(uplist.size()<3){
	    			uplist = null;
	    			int max = uidPoolMapper.maxId();
	    			if(max<600000){
	    				max = 600001;
	    			}else{
	    				max = max+1;
	    			}
	    			uplist = new ArrayList<UidPool>();
	    			List<UidPool> list = new ArrayList<UidPool>();
	    			int t=3;
	    			List<Integer> slist = new ArrayList<Integer>();
	    			for (int i = 0; i < 3000; i++) {
	    				slist.add(max + i);
	    			}
	    			int out = 0;
	    			int outIndex = 0;
	    			for(int i=0;i<3000;i++){
	    				UidPool up = new UidPool();
	    				Random ran = new Random();
	    				outIndex = ran.nextInt(slist.size());
	    				out = slist.get(outIndex);
	    				slist.remove(outIndex);
	    				up.setUid(out+1);
	    				if(t==3){
		    				up.setTag(1);
		    				uid[0] = up.getUid();
	    				}else if(t==2){
		    				up.setTag(1);
		    				uid[1] = up.getUid();
	    				}else if(t==1){
		    				up.setTag(1);
		    				uid[2] = up.getUid();
	    				}else {
	    					up.setTag(0);
	    				}
	    				t--;
	    				list.add(up);
	    				if(i%100==0||i==max+99){
	    					Map<String,Object> params = new HashMap<>();
	    	        		params.put("list", list);
	    	    			uidPoolMapper.insertAll(params);
	    	    			list = new ArrayList<UidPool>();
	    				}
	    			}
	    			
	    		}else{
	    			for(int i=0;i<3;i++){
	    				uid[i] = uplist.get(i).getUid();
	    				uplist.get(i).setTag(1);
	    				uidPoolMapper.update(uplist.get(i));
	    			}
	    		}
			 sqlSession.commit();
   	}catch(Exception e){
   		e.printStackTrace();
   		sqlSession.rollback();
   	} finally {
   		sqlSession.close();
       }
	   return uid;
	   
   }
    
   
}
