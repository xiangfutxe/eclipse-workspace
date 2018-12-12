package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AddressMapper;
import com.ssm.pojo.Address;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class AddressDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    AddressMapper adrMapper = sqlSession.getMapper(AddressMapper.class);
   
    public Pager findByPage(Address adr,Pager pager){
    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("adr",adr);
			int recordCount = adrMapper.count(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<Address> adrs = adrMapper.selectByPage(params);
			sqlSession.commit();
			pager.setResultList(adrs);
    	} finally {
			sqlSession.close();
    	}
    	return pager;
	}
    
    public String saveAdr(Address adr){
    	String str = "";
    	try {
	    	adrMapper.save(adr);
			 sqlSession.commit();
			str =adr.getUserId()+"收货地址信息保存成功。";
	    	
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String updateAdr(Address adr){
    	String str = "";
    	try {
	    	adrMapper.update(adr);
			 sqlSession.commit();
			 str =adr.getUserId()+"地址信息修改成功。";
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public void updateTag(Integer id){
   	try {
	    	Integer up1 = adrMapper.updateTag1(0,1);
	    	Integer up2 = adrMapper.updateTag(1, id);
			 sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
	}
    
 public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			adrMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
	}
 
 public void delete(Integer id){
 	
 	try {
 		
 			adrMapper.deleteById(id);
 		sqlSession.commit();
 	}catch(Exception e){ 
 		e.printStackTrace();
 	} finally {
 		sqlSession.close();
     }
	}
 
 	
 
 	public Address findById(Integer id){
 		Address adr = null;
 		try {
		 adr = adrMapper.selectById(id);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return adr;
		
	}
 	
 	public Address findByTag(String userId){
 		Address adr = null;
 		try {
 			List<Address> result = adrMapper.selectByTag(userId,1);
 			if(result!=null&&result.size()>0) {
 				for(int i=0;i<1;i++) {
 					adr= new Address();
 					adr = result.get(i);
 				}
 			}else {
 				result = adrMapper.selectByTag(userId,0);
 				if(result!=null&&result.size()>0) {
 	 				for(int i=0;i<1;i++) {
 	 					adr= new Address();
 	 					adr = result.get(i);
 	 				}
 				}
 			}
 			sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return adr;
		
	}
 	
 	public List<Address> findByUserId(String userId){
 		List<Address> adr = null;
 		try {
		 adr = adrMapper.selectByUserId(userId);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return adr;
		
	}
 	
	public List<Address> findByAll(){
 		List<Address> adr = null;
 		try {
		 adr = adrMapper.selectAllAddress();
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return adr;
		
	}
}
