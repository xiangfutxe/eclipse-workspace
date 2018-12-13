package com.ssm.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AddressMapper;
import com.ssm.mapper.JoinInfoMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.Order;
import com.ssm.pojo.User;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class JoinInfoDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	JoinInfoMapper joinInfoMapper = sqlSession.getMapper(JoinInfoMapper.class);
	  ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);
	    MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);
	    AddressMapper adrMapper = sqlSession.getMapper(AddressMapper.class);
	    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

    
    public Pager findByPage(JoinInfo joinInfo,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("joinInfo",joinInfo);
		int recordCount = joinInfoMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<JoinInfo> joinInfos = joinInfoMapper.selectByPage(params);
		pager.setResultList(joinInfos);
		sqlSession.close();
		return pager;
	}
    
    public List<JoinInfo> findAlljoinInfo(){
		
		List<JoinInfo> joinInfos = joinInfoMapper.selectAllJoinInfo();
	
		sqlSession.close();
		return joinInfos;
	}
    
 public List<JoinInfo> findByList(JoinInfo joinInfo){
		Map<String,Object> params = new HashMap<>();
		params.put("joinInfo",joinInfo);
		List<JoinInfo> joinInfos = joinInfoMapper.selectByList(params);
		sqlSession.close();
		return joinInfos;
	}
    
    public List<JoinInfo> findByUser(String userId){
    	List<JoinInfo> js = null;
    	try{
    	 js = joinInfoMapper.selectByUserId(userId);
    	sqlSession.commit();
		
		
    	}catch(Exception e){
    		
    	}finally{
    		sqlSession.close();
    	}
    	return js;
	}
    
    public JoinInfo findById(Integer id){
    	JoinInfo joinInfo = joinInfoMapper.selectById(id);
    	sqlSession.commit();
		sqlSession.close();
		return joinInfo;
	}
    
    public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			joinInfoMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public void updateJoinInfo(JoinInfo joinInfo){
    	try {
		 joinInfoMapper.update(joinInfo);
		 sqlSession.commit();

    	} finally {
    		sqlSession.close();
        }
	}
    
	public String saveJoinInfo(JoinInfo joinInfo){
    	String str = "";
    	try {
			 joinInfoMapper.save(joinInfo);
			 sqlSession.commit();
			str =joinInfo.getUserId()+"信息保存成功。";
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
	
	 public String rankJoin_up_back(JoinInfo joinInfo,User user){
		 String str ="";
		 int error= 0;
		 try{
	    		Timestamp date = new Timestamp(new Date().getTime());
				 double emoney = 0;
				if( joinInfoMapper.updateForState(0, joinInfo.getId())>0){
					emoney = joinInfo.getPrice();
					User user_up = new User();
					user_up.setId(user.getId());
					user_up.setRankJoin(joinInfo.getOldRankJoin());
					 if(emoney>0){
						 User declarationUser = userMapper.selectMoneyByIdForUpdate(joinInfo.getDeclarationId());
						 if(declarationUser!=null){
							 List<Order> orders = orderMapper.selectTimeByParam(user.getUserId(), 5, 1,joinInfo.getEntryTime());
							 if(orders!=null&&orders.size()==1){
								 User user_d = new User();
								 user_d.setId(declarationUser.getId());
								 AccountDetail ad  = new AccountDetail();
								 ad.setUid(declarationUser.getId());
	    				    		ad.setUserId(declarationUser.getUserId());
	    				    		ad.setUserName(declarationUser.getUserName());
	    				    		ad.setAmount(emoney);
	    				    		ad.setBalance(ArithUtil.add(declarationUser.getEmoney(), emoney));
	    				    		ad.setTradeType("升级退单");
	    				    		ad.setSummary(user.getUserId()+"升级退单");
	    				    		ad.setPayType(1);
	    				    		ad.setEntryTime(date);
								 user_d.setEmoney(ad.getBalance());
								 Map<String,Object> params = new HashMap<>();
	    			    			params.put("account",ad);
	    			    			params.put("tableName", Constants.EMONEYDETAIL_TABLE);
								 if(userMapper.updateUser(user_d)>0){
									 if(moneyMapper.save(params)>0){
										 for(int i=0;i<orders.size();i++){
											if( orderMapper.updateForState(0, orders.get(i).getId())==0)
												error++;
												
										 }
										 if(error>0){
											 str="会员订单删除失败。";
											 sqlSession.rollback();
										 }
									 }else{
										 error++;
										 str="报单人资金明细保存失败。";
										 sqlSession.rollback();
									 }
								 }else{
									 error++;
									 str="报单人资金退回失败。";
									 sqlSession.rollback();
								 } 
							 }else{
								 error++;
								 str="该会员报单订单不存在或者是订单状态已处于发货过程中。";
								 sqlSession.rollback();
							 }
						 }else{
							 error++;
							 str = "无法获取报单人的信息。";
							 sqlSession.rollback();
						 }	
					 }
					
					//通用模块 
					 if(error==0){
						 
										 if(userMapper.updateUser(user_up)>0){
											 sqlSession.commit();
											 str="yes";
										 }else{
												 str="会员等级更新失败。";
												 sqlSession.rollback();
										 }
							
					 }
				//通用结束
					 
				 }else{
					 str= "会员加盟信息删除失败.";
					 sqlSession.rollback();
				 }
			
		 }catch(Exception e){
	    		str="系统繁忙中，请稍后再试。";
	    		e.printStackTrace();
	    		sqlSession.rollback();
	    	}finally {
	    		sqlSession.close();
	        }
		 return str;
		 }
}
