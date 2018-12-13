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
import com.ssm.mapper.UserMapper;
import com.ssm.pojo.Dept;
import com.ssm.pojo.User;
import com.ssm.utils.Constants;
import com.ssm.utils.PageModel;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class DeptDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
    Dept dept= new Dept();
    
    public Pager findDept(Dept dept,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("dept",dept);
		int recordCount = deptMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<Dept> depts = deptMapper.selectByPage(params);
		pager.setResultList(depts);
		sqlSession.close();
		return pager;
	}
    
    public List<Dept> findAllDept(){
		
		List<Dept> depts = deptMapper.selectAllDept();
	
		sqlSession.close();
		return depts;
	}
    
    public Dept findDeptByName(String name){
		Dept dept = deptMapper.selectByName(name);
		sqlSession.close();
		return dept;
	}
    
    public Dept findDeptById(Integer id){
		Dept dept = deptMapper.selectById(id);
		sqlSession.close();
		return dept;
	}
    
    public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			System.out.println(deptMapper.deleteById(Integer.valueOf(id)));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public void updateDept(Dept dept){
    	try {
		 deptMapper.update(dept);
		 sqlSession.commit();

    	} finally {
    		sqlSession.close();
        }
	}
    
	public String saveDept(Dept dept){
    	String str = "";
    	try {
	    	if(deptMapper.selectByName(dept.getName())==null){
			 deptMapper.save(dept);
			 sqlSession.commit();
			str =dept.getName()+"信息保存成功。";
	    	}else{
	    		str ="该部门已经存在。";
	    	}
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
}
