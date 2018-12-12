package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.session.SqlSession;

import com.ssm.dao.provider.DeptDynaSqlProvider;
import com.ssm.mapper.DeptMapper;
import com.ssm.mapper.EmptyNumMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.Dept;
import com.ssm.pojo.EmptyNum;
import com.ssm.pojo.User;
import com.ssm.utils.Constants;
import com.ssm.utils.PageModel;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class EmptyNumDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    EmptyNumMapper emptyNumMapper = sqlSession.getMapper(EmptyNumMapper.class);
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

   
    public Pager findByPage(EmptyNum emptyNum,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("emptyNum",emptyNum);
		int recordCount = emptyNumMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<EmptyNum> emptyNums = emptyNumMapper.selectByPage(params);
		pager.setResultList(emptyNums);
		sqlSession.close();
		return pager;
	}
    
    
    public EmptyNum findById(Integer id){
    	EmptyNum emptyNum = emptyNumMapper.selectById(id);
		sqlSession.close();
		return emptyNum;
	}
    
    public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			emptyNumMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
  
    
	public String saveEmptyNum(EmptyNum emptyNum){
    	String str = "";
    	try {
	    	User user = userMapper.selectByUserIdForUpdate(emptyNum.getUserId());
	    	if(user!=null){
	    	emptyNum.setUid(user.getId());
	    	emptyNum.setUserName(user.getUserName());
	    	if(emptyNum.getType()==1){
		    	emptyNum.setBalance(emptyNum.getAmount()+user.getEmptyNum());
		    	user.setEmptyNumTol(user.getEmptyNumTol()+emptyNum.getAmount());
	    	}else{
	    		emptyNum.setBalance(user.getEmptyNum()-emptyNum.getAmount());
	    		user.setEmptyNumTol(user.getEmptyNumTol()-emptyNum.getAmount());
	    	}
	    	if(emptyNum.getBalance()>=0){
	    	user.setEmptyNum(emptyNum.getBalance());
    		emptyNumMapper.save(emptyNum);
    		userMapper.updateUser(user);
			 sqlSession.commit();
			str =emptyNum.getUserId()+"空点位操作保存成功。";
	    	}else{
	    		str = "空点位余额不足。";
	    	}
	    	}else{
	    		str = "会员信息获取失败。";
	    	}
    	}catch(Exception e){
    		str = "系统繁忙，请稍后再试。";
    		sqlSession.rollback();
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
}
