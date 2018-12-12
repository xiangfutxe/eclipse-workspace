package com.ssm.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.session.SqlSession;

import com.ssm.dao.provider.DeptDynaSqlProvider;
import com.ssm.mapper.DeptMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Dept;
import com.ssm.pojo.User;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.PageModel;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class MoneyDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);
    
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    
    public Pager findByPage(AccountDetail ad,String type,Pager pager){
		Map<String,Object> params = new HashMap<>();
		try {
		params.put("account",ad);
		if(type.equals("1")){
			params.put("tableName", "rmoney_detail");
		}else if(type.equals("2")){
			params.put("tableName", "cash_num_detail");
		}else if(type.equals("3")){
			params.put("tableName", "cash_detail");
		}else if(type.equals("4")){
			params.put("tableName", "integral_detail");
		}
		if(!type.equals("")) {
			int recordCount = moneyMapper.count(params);
			double[] sum ={0,0};
			Double s1 = moneyMapper.sum1(params);
			Double s2 = moneyMapper.sum2(params);
			if(s1!=null) sum[0]= s1;
			if(s2!=null) sum[1]= s2;
			pager.setSum(sum);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<AccountDetail> ads = moneyMapper.selectByPage(params);
			pager.setResultList(ads);
		}
		sqlSession.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
    		sqlSession.close();
        }
		return pager;
	}
    
    public List<AccountDetail> findAllByList(AccountDetail ad,String type){
  		Map<String,Object> params = new HashMap<>();
  		List<AccountDetail> result = new ArrayList<AccountDetail>();
  		try {
  		params.put("account",ad);
  		if(type.equals("1")){
			params.put("tableName", "rmoney_detail");
		}else if(type.equals("2")){
			params.put("tableName", "cash_detail");
		}else if(type.equals("3")){
			params.put("tableName", "cash_num_detail");
		}else if(type.equals("4")){
			params.put("tableName", "integral_detail");
		}
  		result = moneyMapper.selectAll(params);
  		sqlSession.commit();
  		}catch(Exception e){
  			e.printStackTrace();
  		}finally {
      		sqlSession.close();
          }
  		return result;
  		
  	}
    
    public List<AccountDetail>  findByUserId(String tableName,String userId){
    	List<AccountDetail> alist = new ArrayList<AccountDetail>();
    	try {
    		String sql = "select * from "+tableName+" where user_id='"+userId+"' order by entry_time asc";
    		alist = moneyMapper.selectByList(sql);
    		sqlSession.commit();
    	}catch(Exception e){
			e.printStackTrace();
			sqlSession.rollback();
		}finally {
    		sqlSession.close();
        }
    	return alist;
    }
    
	public String saveMoney(AccountDetail ad,String type){
    	String str = "";
    	try {
    		Map<String,Object> params = new HashMap<>();
    		if(type.equals("1")){
    			params.put("tableName", "rmoney_detail");
    		}else if(type.equals("2")){
    			params.put("tableName", "cash_detail");
    		}else if(type.equals("3")){
    			params.put("tableName", "cash_num_detail");
    		}else if(type.equals("4")){
    			params.put("tableName", "integral_detail");
    		}
    		if(!type.equals("")){
    			
    			params.put("account",ad);
    			moneyMapper.save(params);
			 sqlSession.commit();
			str =ad.getUserId()+"账户明细保存成功。";
	    	}else{
	    		str ="该账户已经存在。";
	    	}
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
	
	public User findAllMoney(){
    	User ad = null; 
    	try {
    			ad = moneyMapper.selectAllMoney();
			
    	}finally {
    		sqlSession.close();
        }
    	return ad;
	}
	
	public int updateForSql(String sql){
    	int value =0;
    	try {
    		Integer update = moneyMapper.updateForSql(sql);
    		if(update!=null&&update>0){
    			value= update;
			 sqlSession.commit();
    		}
    	}finally {
    		sqlSession.close();
        }
    	return value;
	}
	
	public String updateMoney(AccountDetail ad,String type){
    	String str = "";
    	try {
    	
    		if(!type.equals("")){
    			Map<String,Object> params = new HashMap<>();
    			if(type.equals("1")){
        			params.put("tableName", "rmoney_detail");
        		}else if(type.equals("2")){
        			params.put("tableName", "cash_detail");
        		}else if(type.equals("3")){
        			params.put("tableName", "cash_num_detail");
        		}else if(type.equals("4")){
        			params.put("tableName", "integral_detail");
        		}
    			params.put("account",ad);
    			moneyMapper.update(params);
    			sqlSession.commit();
			str =ad.getUserId()+"账户明细修改成功。";
	    	}else{
	    		str ="该账户已经存在。";
	    	}
    	}finally {
    		str="系统繁忙。";
    		sqlSession.close();
        }
    	return str;
	}
	
	public String saveEMoneyTransfer(String userId1,String userId2,double amount,Timestamp date){
    	String str = "";
    	try {
    			User user1 = userMapper.selectByUserIdForUpdate(userId1);
    			User user2 = userMapper.selectByUserIdForUpdate(userId2);
    			if(user1!=null){
    				if(user2!=null){
    			if(ArithUtil.sub(user1.getEmoney(), amount)>=0){
    				AccountDetail ad  = new AccountDetail();
    				AccountDetail ad1  = new AccountDetail();
    				 ad.setId(user1.getId());
		    		ad.setUserId(user1.getUserId());
		    		ad.setUserName(user1.getUserName());
		    		ad.setAmount(amount);
		    		ad.setBalance(ArithUtil.sub(user1.getEmoney(), amount));
		    		ad.setTradeType("电子币互转");
		    		ad.setSummary("转给"+userId2);
		    		ad.setPayType(2);
		    		ad.setEntryTime(date);
		    		 ad1.setId(user2.getId());
		    		ad1.setUserId(user2.getUserId());
		    		ad1.setUserName(user2.getUserName());
		    		ad1.setAmount(amount);
		    		ad1.setBalance(ArithUtil.add(user2.getEmoney(), amount));
		    		ad1.setTradeType("电子币互转");
		    		ad1.setSummary(userId1+"转入");
		    		ad1.setPayType(1);
		    		ad1.setEntryTime(date);
		    		User up_user_1 = new User();
		    		User up_user_2 = new User();
		    		up_user_1.setId(user1.getId());
		    		up_user_1.setEmoney(ArithUtil.sub(user1.getEmoney(), amount));
		    		up_user_2.setId(user2.getId());
		    		up_user_2.setEmoney(ArithUtil.add(user2.getEmoney(), amount));
		    		Map<String,Object> params = new HashMap<>();
	    			params.put("account",ad);
	    			params.put("tableName", "emoneyDetail");
	    			if(moneyMapper.save(params)>0){
	    				params.put("account",ad1);
		    			params.put("tableName", "emoneyDetail");
		    			if(moneyMapper.save(params)>0){
				    		if(userMapper.updateUser(up_user_1)>0){
				    			if(userMapper.updateUser(up_user_2)>0){
				    				str = "yes";
				    				 sqlSession.commit();
				    			}else{
				    				str = "收款人账户余额更新失败。";
				    				sqlSession.rollback();
				    			}
				    		}else{
			    				str = "转账人账户余额更新失败。";
			    				sqlSession.rollback();
			    			}
		    			}else{
		    				str = "收款人账户明细更新失败。";
		    				sqlSession.rollback();
		    			}
	    			}else{
	    				str = "转账人账户明细更新失败。";
	    				sqlSession.rollback();
	    			}
		    			
    				
    			}else{
    				str = "报单券账户余额不足。";
    				sqlSession.rollback();
    			}
    				}else{
        				str="收款人信息获取失败。";
        				sqlSession.rollback();
        			}
    			}else{
    				str="转账人信息获取失败。";
    				sqlSession.rollback();
    			}
    	
    	}catch(Exception e){
    		str="数据操作有误。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
	
	public String saveToEmoney(String userId1,double amount,Timestamp date){
    	String str = "";
    	try {
    			User user1 = userMapper.selectByUserIdForUpdate(userId1);
    			if(user1!=null){
    			if(ArithUtil.sub(user1.getRmoney(), amount)>=0){
    				AccountDetail ad  = new AccountDetail();
    				AccountDetail ad1  = new AccountDetail();
    				 ad.setId(user1.getId());
		    		ad.setUserId(user1.getUserId());
		    		ad.setUserName(user1.getUserName());
		    		ad.setAmount(amount);
		    		ad.setBalance(ArithUtil.sub(user1.getRmoney(), amount));
		    		ad.setTradeType("奖金转报单");
		    		ad.setSummary("转为报单券");
		    		ad.setPayType(2);
		    		ad.setEntryTime(date);
		    		 ad1.setId(user1.getId());
		    		ad1.setUserId(user1.getUserId());
		    		ad1.setUserName(user1.getUserName());
		    		ad1.setAmount(amount);
		    		ad1.setBalance(ArithUtil.add(user1.getEmoney(), amount));
		    		ad1.setTradeType("奖金转报单");
		    		ad1.setSummary("奖金券转入");
		    		ad1.setPayType(1);
		    		ad1.setEntryTime(date);
		    		User up_user_1 = new User();
		    		up_user_1.setId(user1.getId());
		    		up_user_1.setRmoney(ArithUtil.sub(user1.getRmoney(), amount));
		    		up_user_1.setEmoney(ArithUtil.add(user1.getEmoney(), amount));
		    		Map<String,Object> params = new HashMap<>();
	    			params.put("account",ad);
	    			params.put("tableName", "rmoneyDetail");
	    			if(moneyMapper.save(params)>0){
	    				params.put("account",ad1);
		    			params.put("tableName", "emoneyDetail");
		    			if(moneyMapper.save(params)>0){
				    		if(userMapper.updateUser(up_user_1)>0){
				    				str = "yes";
				    				 sqlSession.commit();
				    			}else{
				    				str = "会员账户余额更新失败。";
				    				sqlSession.rollback();
				    			}
				    		}else{
			    				str = "报单券账户明细更新失败。";
			    				sqlSession.rollback();
			    			}
		    			}else{
		    				str = "奖金券账户明细更新失败。";
		    				sqlSession.rollback();
		    			}
	    		
    				
    			}else{
    				str = "奖金券券账户余额不足。";
    				sqlSession.rollback();
    			}
    				
    			}else{
    				str="会员信息获取失败。";
    				sqlSession.rollback();
    			}
    	
    	}catch(Exception e){
    		str="数据操作有误。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
	
}
