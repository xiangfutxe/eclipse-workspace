package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.BankAccountMapper;
import com.ssm.pojo.BankAccount;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class BankAccountDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    BankAccountMapper bankMapper = sqlSession.getMapper(BankAccountMapper.class);
   
    public Pager findByPage(BankAccount bank,Pager pager){
    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("bank",bank);
			int recordCount = bankMapper.count(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<BankAccount> adrs = bankMapper.selectByPage(params);
			sqlSession.commit();
			pager.setResultList(adrs);
    	} finally {
			sqlSession.close();
    	}
    	return pager;
	}
    
    public String saveBankAccount(BankAccount bank){
    	String str = "";
    	try {
	    	bankMapper.save(bank);
			 sqlSession.commit();
			str ="收款账户信息保存成功。";
	    	
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String updateBankAccount(BankAccount bank){
    	String str = "";
    	try {
	    	bankMapper.update(bank);
			 sqlSession.commit();
			 str ="收款账户信息修改成功。";
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
 public int deleteAll(String ids){
    	int num = 0;
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			bankMapper.deleteById(Integer.valueOf(id));
    			num++;
    		}
    		if(num-ids.length()==0){
    		sqlSession.commit();
    		}
    	}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
    	return num;
	}
 
 	
 
 	public BankAccount findById(Integer id){
 		BankAccount adr = null;
 		try {
		 adr = bankMapper.selectById(id);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return adr;
		
	}
 	
 	public List<BankAccount> findByAll(){
 		List<BankAccount> bank = null;
 		try {
		 bank = bankMapper.selectAllBankAccount();
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return bank;
		
	}
}
