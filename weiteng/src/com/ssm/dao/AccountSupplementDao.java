package com.ssm.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AccountSupplementMapper;
import com.ssm.mapper.AccountSupplementMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.AccountSupplement;
import com.ssm.pojo.ChargeApply;
import com.ssm.pojo.Order;
import com.ssm.pojo.User;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.StringUtil;

public class AccountSupplementDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	AccountSupplementMapper asmMapper = sqlSession.getMapper(AccountSupplementMapper.class);
	UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);

    
    public Pager findByPage(AccountSupplement accountSupplement,Pager pager){
    	try{
		Map<String,Object> params = new HashMap<>();
		params.put("accountSupplement",accountSupplement);
		int recordCount = asmMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<AccountSupplement> list = asmMapper.selectByPage(params);
		AccountSupplement ac_sum =asmMapper.sumByParam(params);
		double[] sum ={0};
		if(ac_sum!=null){
			sum[0] = ac_sum.getAmount();
		}
		sqlSession.commit();
		pager.setResultList(list);
		pager.setSum(sum);
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally{
		sqlSession.close();
    	}
		return pager;
	}
    
    public List<AccountSupplement> findByList(AccountSupplement accountSupplement){
    	List<AccountSupplement> list = new ArrayList<AccountSupplement>();
    	try{
		Map<String,Object> params = new HashMap<>();
		params.put("accountSupplement",accountSupplement);
		list = asmMapper.selectByPage(params);
		sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally{
		sqlSession.close();
    	}
		return list;
	}
    
    public List<AccountSupplement> findAll(){
    	List<AccountSupplement> list =null;
		try{
			 list =asmMapper.selectAll();
			sqlSession.commit();
		}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally{
    		sqlSession.close();
    	}
		return list;
	}
    
  
    public AccountSupplement findById(Integer id){
    	AccountSupplement asm = null;
    	try{
	    	asm= asmMapper.selectById(id);
			sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally{
    		sqlSession.close();
    	}
		return asm;
	}
    
    public String saveAsm(String userId,String adminName,double amount,int type,int payType,String summary,Timestamp date){
    	String str = "";
    	String str1 = "";
    	String msg = "";
    	try {
    		User user = userMapper.selectByUserIdForUpdate(userId);
    		if(user!=null){
    		AccountSupplement asm = new AccountSupplement();
    		asm.setUid(user.getId());
    		asm.setUserId(user.getUserId());
    		asm.setUserName(user.getUserName());
    		asm.setAmount(amount);
    		asm.setAdmin(adminName);
    		asm.setType(type);
    		asm.setPayType(payType);
    		asm.setSummary(summary);
    		asm.setEntryTime(date);
    		User user1 = new User();
    		user1.setId(user.getId());
    		user1.setVersion(user.getVersion());
    		Integer up1 = asmMapper.save(asm);
	    		if(up1!=null&&up1>0){
	    			 if(payType==1){
						 str ="补增";
						 str1 ="补增";
					 }else if(payType==2){
						 str ="补扣";
						 str1 ="补扣";
						 amount = -amount;
					 }
	    			 double balance = 0;
	    			String tableName ="";
	    			 if(type==1){
						 balance = ArithUtil.add(user.getRmoney(),amount);
						 tableName = Constants.RMONEYDETAIL_TABLE;
						 if(balance<0) msg="账户余额不足，请重新确认。";
						 else{
							 user1.setRmoney(balance);
							 str= "佣金账户"+str;
						 }
	    			 }else  if(type==2){
						 balance = ArithUtil.add(user.getCashNum(),amount);
						 tableName = Constants.CASHNUMDETAIL_TABLE;
						 if(balance<0) msg="账户余额不足，请重新确认。";
						 else{
						 user1.setCashNum((int) balance);
						 str= "特卖权账户"+str;
						 }
	    			 }else  if(type==3){
						 balance = ArithUtil.add(user.getCash(),amount);
						 tableName = Constants.CASHDETAIL_TABLE;
						 if(balance<0) msg="账户余额不足，请重新确认。";
						 else{
						 user1.setCash(balance);
						 str= "代金券券账户"+str;
						 }
	    			 }else  if(type==4){
						 balance = ArithUtil.add(user.getIntegral(),amount);
						 tableName = Constants.INTEGRALDETAIL_TABLE;
						 if(balance<0) msg="账户余额不足，请重新确认。";
						 else{
						 user1.setIntegral(balance);
						 str= "积分账户"+str;
						 }
	    			 }else{
	    				 msg="未选择相应的账户类型。";
	    			 }
	    			if(msg.equals("")){
			    		AccountDetail ad  = new AccountDetail();
			    		ad.setUid(user.getId());
			    		ad.setUserId(user.getUserId());
			    		ad.setUserName(user.getUserName());
			    		ad.setAmount(Math.abs(amount));
			    		ad.setBalance(balance);
			    		ad.setTradeType("账户"+str1);
			    		ad.setSummary(summary);
			    		ad.setPayType(payType);
			    		ad.setEntryTime(date);
			    		Map<String,Object> params = new HashMap<>();
			    		params.put("account",ad);
			    		params.put("tableName", tableName);
			    		Integer up2 =moneyMapper.save(params);
			    		if(up2!=null&&up2>0){
				    		Integer up3 =userMapper.updateUser(user1);
				    		if(up3!=null&&up3>0){
				    		str +="成功，金额为："+StringUtil.decimalFormat(Math.abs(amount));
				    		 sqlSession.commit();
				    		}else{
				    			str = "会员账户余额更新失败。";
				    			sqlSession.rollback();
				    		}
			    		}else{
			    			str = "会员账户明细保存失败。";
			    			sqlSession.rollback();
			    		}
		    		}else{
		    			str = msg;
		    			sqlSession.rollback();
		    		}
	    		}else{
	    			str = "增补记录保存失败。";
	    			sqlSession.rollback();
	    		}
    		}else{
    			str = "会员信息获取失败。";
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		str = "数据保存异常。";
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
  
	
}
