package com.ssm.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AccountSupplementMapper;
import com.ssm.mapper.AddressMapper;
import com.ssm.mapper.AdminLogMapper;
import com.ssm.mapper.AdminMapper;
import com.ssm.mapper.CenterMapper;
import com.ssm.mapper.ContractMapper;
import com.ssm.mapper.DSettleMapper;
import com.ssm.mapper.JoinInfoMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderDetailMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.AccountSupplement;
import com.ssm.pojo.Address;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Center;
import com.ssm.pojo.Contract;
import com.ssm.pojo.DSettle;
import com.ssm.pojo.Dept;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.User;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WSettle;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.StringUtil;

import oracle.net.aso.e;

public class ContractDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	AccountSupplementMapper asmMapper = sqlSession.getMapper(AccountSupplementMapper.class);

    AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    ContractMapper contractMapper = sqlSession.getMapper(ContractMapper.class);
    AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
    
    ICustomService cs = new CustomService();
    Admin admin= new Admin();
    
    public Admin login(String adminName,String password){
    		admin = adminMapper.login(adminName, password);
    		sqlSession.close();
            return admin;
    }
    
    public Pager findByPage(Contract contract,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("contract",contract);
		int recordCount = contractMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<Contract> result = contractMapper.selectByPage(params);
		pager.setResultList(result);
		sqlSession.close();
		return pager;
	}
    
    public String save(Contract contract){
    	String str = "";
    	try {
	    	if(contractMapper.selectByName(contract.getContractId())==null){
	    		contractMapper.save(contract);
				 sqlSession.commit();
				str ="yes";
	    	}else{
	    		str ="该合同编号已经存在。";
	    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    			str="数据保存异常。";
    			sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String update(Contract contract){
    	String str = "";
    	try {
    		contractMapper.update(contract);
    		sqlSession.commit();
    		str ="yes";
    	}catch(Exception e) {
    		e.printStackTrace();
    			str="数据更新异常。";
    			sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String del(String[] ids){
    	String str = "";
    	try {
    		for(int i=0;i<ids.length;i++) {
    			contractMapper.deleteById(new Long(ids[i]));
    		}
    		sqlSession.commit();
    		str ="yes";
    	}catch(Exception e) {
    		e.printStackTrace();
    		str="数据更新异常。";
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
   
    public String updatePdf(Long id,String url){
    	String str = "";
    	try {
    		contractMapper.updatePdf(id,url);
    		sqlSession.commit();
    		str ="yes";
    	}catch(Exception e) {
    		e.printStackTrace();
    			str="数据更新异常。";
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
 	public Contract findByName(String contractId){
 		Contract contract = contractMapper.selectByName(contractId);
		sqlSession.close();
		return contract;
	}
 	 
}
