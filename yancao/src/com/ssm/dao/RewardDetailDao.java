package com.ssm.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AdminLogMapper;
import com.ssm.mapper.DRewardMapper;
import com.ssm.mapper.JoinInfoMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.RewardDetailMapper;
import com.ssm.mapper.SettleMapper;
import com.ssm.mapper.UserLogMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Address;
import com.ssm.pojo.AdminLog;
import com.ssm.pojo.DReward;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.Order;
import com.ssm.pojo.Param;
import com.ssm.pojo.RewardDetail;
import com.ssm.pojo.Settle;
import com.ssm.pojo.User;
import com.ssm.pojo.UserLog;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WRewardExtreme;
import com.ssm.pojo.WSettle;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.StringUtil;

public class RewardDetailDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	  SettleMapper stMapper = sqlSession.getMapper(SettleMapper.class);
	    WRewardMapper wrdMapper = sqlSession.getMapper(WRewardMapper.class);
	    DRewardMapper drdMapper = sqlSession.getMapper(DRewardMapper.class);
	    RewardDetailMapper rewardDetailMapper = sqlSession.getMapper(RewardDetailMapper.class);
	    WSettleMapper wstMapper = sqlSession.getMapper(WSettleMapper.class);   
	    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);  
	   UserMapper userMapper = sqlSession.getMapper(UserMapper.class);  
	   JoinInfoMapper jfMapper = sqlSession.getMapper(JoinInfoMapper.class);  
	   ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);  
	   MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);  
	    AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
		  UserLogMapper userLogMapper = sqlSession.getMapper(UserLogMapper.class);
		  ICustomService cs = new CustomService();

    public Pager findByPage(RewardDetail reward,Pager pager){
    	try{
				Map<String,Object> params = new HashMap<>();
				params.put("reward",reward);
				int recordCount = rewardDetailMapper.count(params);
				pager.setRowCount(recordCount);
				if(recordCount>0){
					params.put("pageModel", pager);
				}
				List<RewardDetail> results = rewardDetailMapper.selectByPage(params);
				sqlSession.commit();
				pager.setResultList(results);
    	} finally {
			sqlSession.close();
    	}
    	return pager;
	}
    
    public List<RewardDetail> findByList(RewardDetail wrd){
    	List<RewardDetail> wrds =null;
    	try{
    		if(wrd!=null){
				Map<String,Object> params = new HashMap<>();
				params.put("reward",wrd);
				wrds= rewardDetailMapper.selectByList(params);
				sqlSession.commit();
    		}
    	} finally {
			sqlSession.close();
    	}
    	return wrds;
	}
    
    
   
}
