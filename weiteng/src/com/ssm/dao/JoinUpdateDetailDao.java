package com.ssm.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AddressMapper;
import com.ssm.mapper.JoinUpdateDetailMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.JoinUpdateDetail;
import com.ssm.pojo.User;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class JoinUpdateDetailDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	JoinUpdateDetailMapper JoinUpdateDetailMapper = sqlSession.getMapper(JoinUpdateDetailMapper.class);
	  ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);
	    MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);
	    AddressMapper adrMapper = sqlSession.getMapper(AddressMapper.class);
	    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

    
    public Pager findByPage(JoinUpdateDetail JoinUpdateDetail,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("joinUpdateDetail",JoinUpdateDetail);
		int recordCount = JoinUpdateDetailMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<JoinUpdateDetail> JoinUpdateDetails = JoinUpdateDetailMapper.selectByPage(params);
		pager.setResultList(JoinUpdateDetails);
		sqlSession.close();
		return pager;
	}
    
    public List<JoinUpdateDetail> findAllJoinUpdateDetail(){
		
		List<JoinUpdateDetail> JoinUpdateDetails = JoinUpdateDetailMapper.selectAllJoinUpdateDetail();
		sqlSession.close();
		return JoinUpdateDetails;
	}
    
 public List<JoinUpdateDetail> findByList(JoinUpdateDetail JoinUpdateDetail){
		Map<String,Object> params = new HashMap<>();
		params.put("joinUpdateDetail",JoinUpdateDetail);
		List<JoinUpdateDetail> JoinUpdateDetails = JoinUpdateDetailMapper.selectByList(params);
		sqlSession.close();
		return JoinUpdateDetails;
	}
    
    public List<JoinUpdateDetail> findByUser(String userId){
    	List<JoinUpdateDetail> js = null;
    	try{
    	 js = JoinUpdateDetailMapper.selectByUserId(userId);
    	sqlSession.commit();
		
		
    	}catch(Exception e){
    		
    	}finally{
    		sqlSession.close();
    	}
    	return js;
	}
    
    public JoinUpdateDetail findById(Integer id){
    	JoinUpdateDetail JoinUpdateDetail = JoinUpdateDetailMapper.selectById(id);
		sqlSession.close();
		return JoinUpdateDetail;
	}
    
    public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			JoinUpdateDetailMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public void updateJoinUpdateDetail(JoinUpdateDetail JoinUpdateDetail){
    	try {
		 JoinUpdateDetailMapper.update(JoinUpdateDetail);
		 sqlSession.commit();

    	} finally {
    		sqlSession.close();
        }
	}
    
	public String saveJoinUpdateDetail(JoinUpdateDetail JoinUpdateDetail){
    	String str = "";
    	try {
			 JoinUpdateDetailMapper.save(JoinUpdateDetail);
			 sqlSession.commit();
			str =JoinUpdateDetail.getUserId()+"信息保存成功。";
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
	
	 public String user_rankJoin_update(String userId,Integer rankJoin,Timestamp date){
		 String str ="";
		 try{
	    			User user = userMapper.selectByUserId(userId);
	    			if(user!=null){
	    				User user1= new User();
	    				user1.setId(user.getId());
	    				if(rankJoin-user.getRankJoin()!=0){
	    					int tag=0;
	    					if(rankJoin>user.getRankJoin())tag=1;
	    					
	    				user1.setRankJoin(rankJoin);
	    				user1.setRankJoinOld(rankJoin);
	    				JoinUpdateDetail joinInfo = new JoinUpdateDetail();
	    				joinInfo.setUid(user.getId());
	    				joinInfo.setUserId(user.getUserId());
	    				joinInfo.setUserName(user.getUserName());
	    				joinInfo.setState(1);
	    				joinInfo.setOldRankJoin(user.getRankJoin());
	    				joinInfo.setNewRankJoin(rankJoin);
	    				joinInfo.setTag(tag);
	    				joinInfo.setEntryTime(date);
	    				if(userMapper.updateUser(user1)>0){
	    					if(JoinUpdateDetailMapper.save(joinInfo)>0){
	    						sqlSession.commit();
	    						str="yes";
	    					}else{
	    						str="会员加盟信息保存失败。";
	    					}
	    				}else{
	    					str="会员等级信息调整失败。";
	    				}
	    				}else{
			    			str="当前会员等级不符合要求变更为新的等级，请核对。";
			    		}
	    			}else{
	    				str ="会员信息获取失败。";
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
