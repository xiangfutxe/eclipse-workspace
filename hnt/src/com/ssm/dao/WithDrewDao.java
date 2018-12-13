package com.ssm.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.WithDrewMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.AccountSupplement;
import com.ssm.pojo.Param;
import com.ssm.pojo.User;
import com.ssm.pojo.WithDrew;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.DateUtil;
import com.ssm.utils.StringUtil;

public class WithDrewDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	WithDrewMapper withDrewMapper = sqlSession.getMapper(WithDrewMapper.class);
	ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);
	UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);

	ICustomService cs = new CustomService();
   
    public Pager findByPage(WithDrew with_drew,Pager pager){
    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("with_drew",with_drew);
			int recordCount = withDrewMapper.count(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<WithDrew> adrs = withDrewMapper.selectByPage(params);
			WithDrew ac_sum =withDrewMapper.sumByParam(params);
			double[] sum ={0,0,0};
			if(ac_sum!=null){
				sum[0] = ac_sum.getAmount();
				sum[2] = ac_sum.getActualAmount();
				sum[1] = ac_sum.getFee();
			}	
			sqlSession.commit();
			pager.setResultList(adrs);
			pager.setSum(sum);

    	} finally {
			sqlSession.close();
    	}
    	return pager;
	}
    
    public String saveWithDrew(WithDrew WithDrew){
    	String str = "";
    	try {
    		withDrewMapper.save(WithDrew);
			 sqlSession.commit();
			str =WithDrew.getUserId()+"提现申请保存成功，请在申请列表中查看进度。";
	    	
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String updateWithDrew(WithDrew WithDrew){
    	String str = "";
    	try {
    		Integer up1 = withDrewMapper.update(WithDrew);
    		if(up1!=null&&up1>0){
			 sqlSession.commit();
			 str ="yes";
    		}else{
    			str="提现信息更新失败。";
    		}
    	}catch(Exception e){ 
    		str="系统繁忙中，请稍后再试。";
    		e.printStackTrace();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
 public String  delete(Integer id){
    	String msg = "";
    	try {
    		if(withDrewMapper.deleteById(Integer.valueOf(id))>0){
    			msg = "yes";
    		sqlSession.commit();
    		}else{
    			msg ="删除记录失败，请谨慎使用删除功能。";
    		}
    	}catch(Exception e){ 
    		msg="系统繁忙中，请稍后再试。";
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
    	return msg;
	}
 
 	
 
 	public WithDrew findById(Integer id){
 		WithDrew adr = null;
 		try {
		 adr = withDrewMapper.selectById(id);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return adr;
		
	}
 	
 	public List<WithDrew> findByUserId(String userId){
 		List<WithDrew> adr = null;
 		try {
		 adr = withDrewMapper.selectByUserId(userId);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return adr;
		
	}
 	
 	public WithDrew findByApplyId(String applyId){
 		WithDrew wd = null;
 		try {
 			wd = withDrewMapper.selectByApplyId(applyId);
		 sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return wd;
		
	}
 	
	public List<WithDrew> findByList(WithDrew with_drew){
 		List<WithDrew> wds = null;
 		try {
 			Map<String,Object> params = new HashMap<>();
			params.put("with_drew",with_drew);
			wds = withDrewMapper.selectByList(params);
			sqlSession.commit();
 		}catch(Exception e){ 
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return wds;
		
	}
 	
 	public String apply_save(double amount,User user){
 		
 		String msg="";
 		try {
		 Param p = paramMapper.selectByName("提现申请");
		 
		 Timestamp date = new Timestamp(new Date().getTime());
		String applyId = "W"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(cs.getRandom(10,99));

		 if(p!=null){
			 User user1 = userMapper.selectMoneyByUserIdForUpdate(user.getUserId());
			 if(user1!=null){
			 double fee = ArithUtil.mul(amount, p.getAmount_1());
			 double actualAmount = ArithUtil.sub(amount, fee);
			 WithDrew wd = new WithDrew();
			 wd.setuId(user1.getId());
			 wd.setUserId(user1.getUserId());
			 wd.setUserName(user1.getUserName());
			 wd.setAccountId(user.getAccountId());
			 wd.setAccountName(user.getAccountName());
			 wd.setBankAdr(user.getBankAdr());
			 wd.setBankName(user.getBankName());
			 wd.setAmount(amount);
			 wd.setActualAmount(actualAmount);
			 wd.setFee(fee);
			 wd.setState(1);
			 wd.setApplyId(applyId);
			 wd.setApplyTime(date);
			 AccountDetail ad  = new AccountDetail();
			 ad.setUid(user1.getId());
	    		ad.setUserId(user1.getUserId());
	    		ad.setUserName(user1.getUserName());
	    		ad.setAmount(amount);
	    		ad.setBalance(ArithUtil.sub(user1.getRmoney(), amount));
	    		ad.setTradeType("奖金提现");
	    		ad.setSummary("奖金提现");
	    		ad.setPayType(2);
	    		ad.setEntryTime(date);
	    		Map<String,Object> params = new HashMap<>();
    			params.put("account",ad);
    			params.put("tableName", "rmoneyDetail");
    			if(ad.getBalance()>=0){
			 User update_user = new User();
			 update_user.setId(user1.getId());
			 update_user.setRmoney(ad.getBalance());
			 if(userMapper.updateUser(update_user)>0){
				 if(moneyMapper.save(params)>0){
					 if(withDrewMapper.save(wd)>0){
					 msg="提现申请成功，请在提现申请中查看";
					 sqlSession.commit();

					 }else{
						 msg="申请信息保存失败。";
						 sqlSession.rollback();
					 }
				 }else{
						msg="会员资金明细保存失败。" ;
						sqlSession.rollback();
					 }
				 
			 }else{
					msg="会员信息资金更新失败。" ;
					sqlSession.rollback();
				 }
			 }else{
				msg="账户余额不足。" ;
				sqlSession.rollback();
			 }
			 
			 }else{
				 msg="会员信息获取失败";
				 sqlSession.rollback();
			 }
		 }else{
			 msg="提现申请参数获取失败";
		 }
 		}catch(Exception e){ 
 			msg="提现申请操作失败。";
 					sqlSession.rollback();
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return msg;
		
	}
 	
public String apply_no(String adminName,String applyId,Timestamp date){
 		
 		String msg="";
 		try {
 			WithDrew wd = withDrewMapper.selectByApplyIdForUpdate(applyId);
 			if(wd!=null){
 				if(wd.getState()==1){
			 User user1 = userMapper.selectMoneyByUserIdForUpdate(wd.getUserId());
			 if(user1!=null){
			
			 double amount = wd.getAmount();
			 WithDrew wd1 = new WithDrew();
			 
			 wd1.setId(wd.getId());
			 wd1.setState(3);
			 wd1.setAdmin(adminName);
			wd1.setReviewTime(date);
			 AccountDetail ad  = new AccountDetail();
			 ad.setUid(user1.getId());
	    		ad.setUserId(user1.getUserId());
	    		ad.setUserName(user1.getUserName());
	    		ad.setAmount(amount);
	    		ad.setBalance(ArithUtil.add(user1.getRmoney(), amount));
	    		ad.setTradeType("提现退回");
	    		ad.setSummary(applyId+"提现退回");
	    		ad.setPayType(1);
	    		ad.setEntryTime(date);
	    		Map<String,Object> params = new HashMap<>();
    			params.put("account",ad);
    			params.put("tableName", "rmoneyDetail");
    			if(ad.getBalance()>=0){
			 User update_user = new User();
			 update_user.setId(user1.getId());
			 update_user.setRmoney(ad.getBalance());
			 if(userMapper.updateUser(update_user)>0){
				 if(moneyMapper.save(params)>0){
					 if(withDrewMapper.update(wd1)>0){
						 msg=applyId+"提现审批不通过成功，请在提现申请中查看";
						 sqlSession.commit();

					 }else{
						 msg="申请信息保存失败。";
						 sqlSession.rollback();
					 }
				 }else{
						msg="会员资金明细保存失败。" ;
						sqlSession.rollback();
					 }
				 
			 }else{
					msg="会员信息资金更新失败。" ;
					sqlSession.rollback();
				 }
			 }else{
				msg="账户余额不足。" ;
				sqlSession.rollback();
			 }
			 
			 }else{
				 msg="会员信息获取失败";
				 sqlSession.rollback();
			 }
 			 }else{
 				 msg="当前状态不能进行审批。";
 			 }
		 }else{
			 msg="提现申请记录获取失败";
		 }
 		}catch(Exception e){ 
 			msg="提现申请操作失败。";
 					sqlSession.rollback();
    		e.printStackTrace();
    	} finally {
    		sqlSession.close();
        }
 		return msg;
		
	}

public String apply_yes(String adminName,String applyId,Timestamp date){
		
		String msg="";
		try {
			WithDrew wd = withDrewMapper.selectByApplyIdForUpdate(applyId);
			if(wd!=null){
				if(wd.getState()==1){
		 User user1 = userMapper.selectMoneyByUserIdForUpdate(wd.getUserId());
		 WithDrew wd1 = new WithDrew();
		 
		 wd1.setId(wd.getId());
		 wd1.setState(2);
		 wd1.setAdmin(adminName);
		wd1.setReviewTime(date);
				 if(withDrewMapper.update(wd1)>0){
				 msg=applyId+"提现审批审批通过，请在提现申请中查看";
				 sqlSession.commit();

				 }else{
					 msg="申请信息保存失败。";
					 sqlSession.rollback();
				 }
		
			 }else{
				 msg="当前状态不能进行审批。";
			 }
	 }else{
		 msg="提现申请记录获取失败";
	 }
		}catch(Exception e){ 
			msg="提现申请操作失败。";
					sqlSession.rollback();
		e.printStackTrace();
	} finally {
		sqlSession.close();
    }
		return msg;
	
}

public String apply_yes_reback(String adminName,String applyId,Timestamp date){
	
	String msg="";
	try {
		WithDrew wd = withDrewMapper.selectByApplyIdForUpdate(applyId);
		if(wd!=null){
			if(wd.getState()==2){
	 WithDrew wd1 = new WithDrew();
	 
	 wd1.setId(wd.getId());
	 wd1.setState(1);
	 wd1.setAdmin(adminName);
	wd1.setReviewTime(date);
			 if(withDrewMapper.update(wd1)>0){
			 msg=applyId+"提现审批审批通过流程已撤回，请在提现申请中查看";
			 sqlSession.commit();

			 }else{
				 msg="申请信息保存失败。";
				 sqlSession.rollback();
			 }
	
		 }else{
			 msg="当前状态不能进行审批。";
		 }
 }else{
	 msg="提现申请记录获取失败";
 }
	}catch(Exception e){ 
		msg="提现申请操作失败。";
				sqlSession.rollback();
	e.printStackTrace();
} finally {
	sqlSession.close();
}
	return msg;

}
 	
}
