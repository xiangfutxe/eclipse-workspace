package com.ssm.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.AccountSupplementMapper;
import com.ssm.mapper.AdminLogMapper;
import com.ssm.mapper.AdminMapper;
import com.ssm.mapper.ContractMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.BorrowApplyMapper;

import com.ssm.pojo.Admin;
import com.ssm.pojo.BorrowApply;
import com.ssm.pojo.Contract;

import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;


public class BorrowApplyDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	AccountSupplementMapper asmMapper = sqlSession.getMapper(AccountSupplementMapper.class);

    AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    ContractMapper contractMapper = sqlSession.getMapper(ContractMapper.class);
    AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
    BorrowApplyMapper borrowApplyMapper = sqlSession.getMapper(BorrowApplyMapper.class);
    ICustomService cs = new CustomService();
    Admin admin= new Admin();
    
    public Pager findByPage(BorrowApply contract,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("contract",contract);
		int recordCount = borrowApplyMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<BorrowApply> result = borrowApplyMapper.selectByPage(params);
		pager.setResultList(result);
		sqlSession.close();
		return pager;
	}
    
    public String save(BorrowApply contract){
    	String str = "";
    	try {
    		if(borrowApplyMapper.selectByCId(contract.getContractId())==null) {
	    			borrowApplyMapper.save(contract);
	    			sqlSession.commit();
	    			str ="yes";
    		}else str= "请勿重复提交申请。";
    	}catch(Exception e) {
    		e.printStackTrace();
    		str="数据保存异常。";
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String update(BorrowApply contract){
    	String str = "";
    	try {
    		borrowApplyMapper.update(contract);
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
    
    public String reviewYes(BorrowApply apply){
    	String str = "";
    	try {
    		BorrowApply apply1 = borrowApplyMapper.selectByIdForUpdate(apply.getId());
    		if(apply1!=null&&apply1.getState()==1) {
	    		borrowApplyMapper.update(apply);
	    		Integer up = contractMapper.updateState(apply1.getContractId(),2,1);
	    		if(up!=null&&up>0) {
	    			sqlSession.commit();
	    			str ="yes";
	    		}else {
	    			str="合同的状态有误。";
	    		}
    		}else str="申请信息获取失败或当前状态不能审批。";
    	}catch(Exception e) {
    		e.printStackTrace();
    			str="数据更新异常。";
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String confirmYes(BorrowApply apply){
    	String str = "";
    	try {
    		BorrowApply apply1 = borrowApplyMapper.selectByIdForUpdate(apply.getId());
    		if(apply1!=null&&apply1.getState()-2==0) {
	    		borrowApplyMapper.update(apply);
	    		sqlSession.commit();
	    		str ="yes";
    		}else str="申请信息获取失败或当前状态不能审批。";
    	}catch(Exception e) {
    		e.printStackTrace();
    			str="数据更新异常。";
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String endYes(BorrowApply apply){
    	String str = "";
    	try {
    		BorrowApply apply1 = borrowApplyMapper.selectByIdForUpdate(apply.getId());
    		if(apply1!=null&&apply1.getState()-3==0) {
	    		borrowApplyMapper.update(apply);
	    		Integer up = contractMapper.updateState(apply1.getContractId(),1,2);
	    		if(up!=null&&up>0) {
	    			sqlSession.commit();
	    			str ="yes";
	    		}else {
	    			str="合同的状态有误。";
	    		}
    		}else str="申请信息获取失败或当前状态不能审批。";
    	}catch(Exception e) {
    		e.printStackTrace();
    			str="数据更新异常。";
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String del(Long  id){
    	String str = "";
    	try {
    		BorrowApply apply = borrowApplyMapper.selectById(id);
    		if(apply!=null&&apply.getState()<=1) {
    			borrowApplyMapper.deleteById(id);
				 sqlSession.commit();
				str ="yes";
    		}else str="记录不存在或者当前状态不能删除。";
    	}catch(Exception e) {
    		e.printStackTrace();
    		str="数据保存异常。";
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
