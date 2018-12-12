package com.ssm.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AddressMapper;
import com.ssm.mapper.RankManageUpdateDetailMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.RankManageUpdateDetail;
import com.ssm.pojo.User;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class RankManageUpdateDetailDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	RankManageUpdateDetailMapper RankManageUpdateDetailMapper = sqlSession.getMapper(RankManageUpdateDetailMapper.class);
	  ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);
	    MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);
	    AddressMapper adrMapper = sqlSession.getMapper(AddressMapper.class);
	    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

    
    public Pager findByPage(RankManageUpdateDetail RankManageUpdateDetail,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("rankManageUpdateDetail",RankManageUpdateDetail);
		int recordCount = RankManageUpdateDetailMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<RankManageUpdateDetail> RankManageUpdateDetails = RankManageUpdateDetailMapper.selectByPage(params);
		pager.setResultList(RankManageUpdateDetails);
		sqlSession.close();
		return pager;
	}
    
    public List<RankManageUpdateDetail> findAllRankManageUpdateDetail(){
		
		List<RankManageUpdateDetail> RankManageUpdateDetails = RankManageUpdateDetailMapper.selectAllRankManageUpdateDetail();
		sqlSession.close();
		return RankManageUpdateDetails;
	}
    
 public List<RankManageUpdateDetail> findByList(RankManageUpdateDetail RankManageUpdateDetail){
		Map<String,Object> params = new HashMap<>();
		params.put("RankManageUpdateDetail",RankManageUpdateDetail);
		List<RankManageUpdateDetail> RankManageUpdateDetails = RankManageUpdateDetailMapper.selectByList(params);
		sqlSession.close();
		return RankManageUpdateDetails;
	}
    
    public List<RankManageUpdateDetail> findByUser(String userId){
    	List<RankManageUpdateDetail> js = null;
    	try{
    	 js = RankManageUpdateDetailMapper.selectByUserId(userId);
    	 sqlSession.commit();
    	}catch(Exception e){
    		
    	}finally{
    		sqlSession.close();
    	}
    	return js;
	}
    
    public RankManageUpdateDetail findById(Integer id){
    	RankManageUpdateDetail RankManageUpdateDetail = RankManageUpdateDetailMapper.selectById(id);
		sqlSession.close();
		return RankManageUpdateDetail;
	}
    
    public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			RankManageUpdateDetailMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public void updateRankManageUpdateDetail(RankManageUpdateDetail RankManageUpdateDetail){
    	try {
		 RankManageUpdateDetailMapper.update(RankManageUpdateDetail);
		 sqlSession.commit();

    	} finally {
    		sqlSession.close();
        }
	}
    
	public String saveRankManageUpdateDetail(RankManageUpdateDetail RankManageUpdateDetail){
    	String str = "";
    	try {
			 RankManageUpdateDetailMapper.save(RankManageUpdateDetail);
			 sqlSession.commit();
			str =RankManageUpdateDetail.getUserId()+"信息保存成功。";
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
	
	 public String user_rankManage_update(String userId,Integer RankManage,Timestamp date){
		 String str ="";
		 try{
	    			User user = userMapper.selectByUserId(userId);
	    			if(user!=null){
	    				User user1= new User();
	    				user1.setId(user.getId());
	    				if(RankManage-user.getRank()!=0){
	    					int tag=0;
	    					if(RankManage>user.getRank())tag=1;
	    				user1.setRank(RankManage);
	    				RankManageUpdateDetail joinInfo = new RankManageUpdateDetail();
	    				joinInfo.setUid(user.getId());
	    				joinInfo.setUserId(user.getUserId());
	    				joinInfo.setUserName(user.getUserName());
	    				joinInfo.setState(1);
	    				joinInfo.setOldRankManage(user.getRank());
	    				joinInfo.setNewRankManage(RankManage);
	    				joinInfo.setTag(tag);
	    				joinInfo.setEntryTime(date);
	    				if(userMapper.updateUser(user1)>0){
	    					if(RankManageUpdateDetailMapper.save(joinInfo)>0){
	    						sqlSession.commit();
	    						str="yes";
	    					}else{
	    						str="会员管理级别变更信息保存失败。";
	    					}
	    				}else{
	    					str="会员管理级别信息调整失败。";
	    				}
	    				}else{
			    			str="当前会员管理级别不符合要求变更为新的管理级别，请核对。";
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
