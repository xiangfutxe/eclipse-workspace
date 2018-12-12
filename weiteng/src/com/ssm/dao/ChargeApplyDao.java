package com.ssm.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.ChargeApplyMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.AccountSupplement;
import com.ssm.pojo.ChargeApply;
import com.ssm.pojo.User;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class ChargeApplyDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	ChargeApplyMapper chargeApplyMapper = sqlSession.getMapper(ChargeApplyMapper.class);
	UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);

    
    public Pager findByPage(ChargeApply chargeApply,Pager pager){
    	try{
		Map<String,Object> params = new HashMap<>();
		params.put("chargeApply",chargeApply);
		int recordCount = chargeApplyMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<ChargeApply> depts = chargeApplyMapper.selectByPage(params);
		ChargeApply ac_sum =chargeApplyMapper.sumByParam(params);
		double[] sum ={0};
		if(ac_sum!=null){
			sum[0] = ac_sum.getAmount();
		}
		sqlSession.commit();
		pager.setSum(sum);
		pager.setResultList(depts);
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally{
    		sqlSession.close();
    	}
		return pager;
	}
    
    public List<ChargeApply> findByList(ChargeApply chargeApply){
    	List<ChargeApply> clist = new ArrayList<ChargeApply>();
    	try{
		Map<String,Object> params = new HashMap<>();
		params.put("chargeApply",chargeApply);
		clist = chargeApplyMapper.selectByPage(params);
		sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally{
		sqlSession.close();
    	}
		return clist;
	}
    
    public List<ChargeApply> findAllChargeApply(){
    	List<ChargeApply> depts =null;
		try{
			 depts = chargeApplyMapper.selectAllDept();
			sqlSession.commit();
			sqlSession.close();
		}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally{
    		sqlSession.close();
    	}
		return depts;
	}
    
    public ChargeApply findChargeApplyByName(String name){
    	ChargeApply chargeApply = null;
    	try{
	    	chargeApply = chargeApplyMapper.selectByName(name);
	    	sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally{
    		sqlSession.close();
    	}
		return chargeApply;
	}
    
    public ChargeApply findById(Integer id){
    	ChargeApply chargeApply = null;
    	try{
	    	chargeApply = chargeApplyMapper.selectById(id);
			sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally{
    		sqlSession.close();
    	}
		return chargeApply;
	}
    
    public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			chargeApplyMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public int updateChargeApply(ChargeApply chargeApply){
    	int num=0;
    	try {
    		num=chargeApplyMapper.update(chargeApply);
    		if(num>0)
		 sqlSession.commit();

    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
    		sqlSession.close();
        }
    	return num;
	}
    
   
    
    public int updateState(Integer id,Integer state,Integer state1){
    	int num=0;
    	try {
    		num=chargeApplyMapper.UpdateState(id,state,state1);
    		if(num>0)
    			sqlSession.commit();
    	}catch(Exception e){ 
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
    		sqlSession.close();
        }
    	return num;
	}
    
    public String applyYes(ChargeApply chargeApply){
    	String str = "";
    	try {
    		
    			ChargeApply cay = chargeApplyMapper.selectById(chargeApply.getId());
    			if(cay!=null){
    				if(cay.getState()==1){
    					User user = userMapper.selectMoneyByUserIdForUpdate(cay.getUserId());
    		    		if(user!=null){
    					if(chargeApplyMapper.update(chargeApply)>0){
    					User user1 = new User();
    		    		user1.setId(user.getId());
    					AccountDetail ad  = new AccountDetail();
    					ad.setUid(user.getId());
    		    		ad.setUserId(user.getUserId());
    		    		ad.setUserName(user.getUserName());
    		    		ad.setAmount(cay.getAmount());
    		    		ad.setBalance(ArithUtil.add(cay.getAmount(), user.getEmoney()));
    		    		ad.setTradeType("汇款充值");
    		    		ad.setSummary("汇款充值");
    		    		ad.setPayType(1);
    		    		ad.setEntryTime(chargeApply.getReviewTime());
    		    		user1.setEmoney(ad.getBalance());
    		    			Map<String,Object> params = new HashMap<>();
    		    			params.put("account",ad);
    		    			params.put("tableName", Constants.EMONEYDETAIL_TABLE);
    		    		if(moneyMapper.save(params)>0){
    		    		if(userMapper.updateUser(user1)>0){
    			    		str =cay.getUserId()+"充值申请审核通过，申请编号为："+cay.getApplyId();
    			    		sqlSession.commit();
    		    		}else{
    		    			str = "会员报单券资金更新失败。";
    		    			sqlSession.rollback();
    		    		}
    				}else{
		    			str = "会员报单券资金明细更新失败。";
		    			sqlSession.rollback();
		    		}
    			}else{
	    			str = "充值申请状态更新失败。";
	    			sqlSession.rollback();
	    		}
    		    		}else{
    		    			str = "会员信息获取失败。";
    		    			sqlSession.rollback();
    		    		}
    				}else{
    	    			str = "当前状态不能进行审批。";
    	    			sqlSession.rollback();
    	    		}
    			}else{
	    			str = "充值申请信息获取失败。";
	    			sqlSession.rollback();
	    		}
    	
    	}catch(Exception e){ 
    		str = "系统繁忙中，请稍后再试。";
    		e.printStackTrace();
    		sqlSession.rollback();
    	} finally {
    		sqlSession.close();
        }
    	return str;
	}
    
	public String saveQuickChargeApply(ChargeApply chargeApply){
    	String str = "";
    	try {
    		User user = userMapper.selectMoneyByUserIdForUpdate(chargeApply.getUserId());
    		if(user!=null){
    		chargeApply.setUserName(user.getUserName());
    		chargeApply.setState(2);
    		User user1 = new User();
    		user1.setId(user.getId());
	    		if(chargeApplyMapper.save(chargeApply)>0){
	    		AccountDetail ad  = new AccountDetail();
	    		ad.setUid(user.getId());
	    		ad.setUserId(user.getUserId());
	    		ad.setUserName(user.getUserName());
	    		ad.setAmount(chargeApply.getAmount());
	    		ad.setBalance(ArithUtil.add(chargeApply.getAmount(), user.getEmoney()));
	    		ad.setTradeType("现金充值");
	    		ad.setSummary(chargeApply.getRemark());
	    		ad.setPayType(1);
	    		ad.setEntryTime(chargeApply.getApplyTime());
	    		user1.setEmoney(ad.getBalance());
	    			Map<String,Object> params = new HashMap<>();
	    			params.put("account",ad);
	    			params.put("tableName", Constants.EMONEYDETAIL_TABLE);
	    		if(moneyMapper.save(params)>0){
	    		if(userMapper.updateUser(user1)>0){
		    		str =chargeApply.getUserId()+"现金充值信息保存成功。";
		    		 sqlSession.commit();
	    		}else{
	    			str = "报单券充值失败。";
	    			sqlSession.rollback();
	    		}
	    		}else{
	    			str = "会员报单券明细保存失败。";
	    			sqlSession.rollback();
	    		}
	    		}else{
	    			str = "会员充值记录保存失败。";
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
	
	public String saveChargeApply(ChargeApply chargeApply){
    	String str = "";
    	try {
	    		if(chargeApplyMapper.save(chargeApply)>0){
		    		str =chargeApply.getUserId()+"现金充值信息保存成功。";
		    		 sqlSession.commit();
	    		}else{
	    			str = "数据保存异常。";
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
