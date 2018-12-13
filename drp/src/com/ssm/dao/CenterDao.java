package com.ssm.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AdminLogMapper;
import com.ssm.mapper.CenterMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.AdminLog;
import com.ssm.pojo.Center;
import com.ssm.pojo.User;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class CenterDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    CenterMapper centerMapper = sqlSession.getMapper(CenterMapper.class);
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    AdminLogMapper logMapper = sqlSession.getMapper(AdminLogMapper.class);


   
    public Pager findCenterByPage(Center center,Pager pager){
    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("center",center);
			int recordCount = centerMapper.count(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<Center> centers = centerMapper.selectByPage(params);
			sqlSession.commit();
			pager.setResultList(centers);
    	}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
			sqlSession.close();
    	}
    	return pager;
	}
    
    public List<Center> findByList(Center center){
    	List<Center> centers = null;
    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("center",center);
			centers = centerMapper.selectByPage(params);
			sqlSession.commit();
			
    	}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
			sqlSession.close();
    	}
    	return centers;
	}
    
    public String saveCenter(Center center,User user){
    	String str = "";
    	try {
	    	if(centerMapper.selectByUserId(center.getUserId())==null){
	    		
	    		if(centerMapper.save(center)>0){
	    			if(userMapper.updateUser(user)>0){
				 sqlSession.commit();
				 str =center.getCenterId()+"信息保存成功。";
	    			}else{
	    				str ="会员店铺归属信息保存失败。";
	    				sqlSession.rollback();
	    			}
			 }else{
				 str ="店铺信息保存失败。";
				 sqlSession.rollback();
			 }
			
	    	}else{
	    		str ="该店铺已经存在。";
	    		 sqlSession.rollback();
	    	}
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String updateCenter(Center center){
    	String str = "";
    	try {
	    	centerMapper.update(center);
			 sqlSession.commit();
			 str =center.getCenterId()+"信息修改成功。";
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String updateCenterClose(Integer id,String adminName,Timestamp date){
    	String str = "";
    	try {
    		Center center = centerMapper.selectById(id);
    		if(center!=null){
    			User user =userMapper.selectByUserIdForUpdate(center.getUserId());
    			if(user!=null&&user.getState()>0){
    				Integer up1 = userMapper.updateUserForField("centerId", "0", user.getUserId());
    				if(up1!=null&&up1>0){
	    				Center cnt = new Center();
	    				cnt.setId(center.getId());
	    				cnt.setState(0);
	    				Integer up2 = centerMapper.update(cnt);
	    				if(up2!=null&&up2>0){
	    					str=center.getCenterId()+"店铺关闭成功，请及时通知相关店铺。";
	    					AdminLog log = new AdminLog();
	    					log.setAdminName(adminName);
	    					log.setContents(str);
	    					log.setState(1);
	    					log.setType(ConstantsLog.LOGTYPE_6);
	    					log.setEntryTime(date);
	    					log.setEndTime(date);
	    					Integer up3 = logMapper.save(log);
	    					if(up3==null||up3==0){
	    						str = "系统日志保存失败。";
	    						sqlSession.rollback();
	    					}else{
	    						 sqlSession.commit();
	    					}
	    				}else{
	    					str = "店铺对应会员状态更新失败。";
	    					sqlSession.rollback();
	    				}
    				}else{
    					str = "店铺状态更新失败。";
    					sqlSession.rollback();
    				}
    			}else{
					str = "会员信息获取失败。";
					sqlSession.rollback();
				}
    		}else{
				str = "店铺信息获取失败。";
				sqlSession.rollback();
			}
    	}catch(Exception e){ 
    		str = "数据操作有误。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
    		sqlSession.close();
        }
    	return str;
	}
    
 public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			System.out.println(centerMapper.deleteById(Integer.valueOf(id)));
    		}
    		sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
    		sqlSession.close();
        }
	}
 
 	
 
 	public Center findCenterById(Integer id){
 		Center center = null;
 		try {
		 center = centerMapper.selectById(id);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
    		sqlSession.close();
        }
 		return center;
		
	}
 	
 	public Center findCenterByUserId(String userId){
 		Center center = null;
 		try {
		 center = centerMapper.selectByUserId(userId);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
    		sqlSession.close();
        }
 		return center;
		
	}
 	
 	public Center findByCenterId(String centerId){
 		Center center = null;
 		try {
		 center = centerMapper.selectByCenterId(centerId);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
    		sqlSession.close();
        }
 		return center;
		
	}
 	
public int updateHide(String[] ids,int isHide){
    	int num =0;
    	try {
    		if(ids.length>0){
    		for(String id:ids){
    			Center center = new Center();
    			center.setId(Integer.valueOf(id));
    			center.setIsHide(isHide);
    			num=num+centerMapper.update(center);
    		}
    		if(num-ids.length==0){
    			sqlSession.commit();
    		}else{
    			num=0;
    			sqlSession.rollback();
    		}
    	}
    		
    	}catch(Exception e){ 
    		num =0;
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
    		sqlSession.close();
        }
    	return num;
	}
}
