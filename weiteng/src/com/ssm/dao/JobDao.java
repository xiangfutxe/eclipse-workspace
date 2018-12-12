package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ssm.mapper.JobMapper;
import com.ssm.pojo.Dept;
import com.ssm.pojo.Job;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;

public class JobDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
    JobMapper jobMapper = sqlSession.getMapper(JobMapper.class);
    Job Job= new Job();
    
    public Pager findJob(Job job,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("Job",job);
		int recordCount = jobMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<Job> Jobs = jobMapper.selectByPage(params);
		pager.setResultList(Jobs);
		sqlSession.close();
		return pager;
	}
    
 public List<Job> findAllJob(){
		
		List<Job> jobs = jobMapper.selectAllJOB();
	
		sqlSession.close();
		return jobs;
	}
    
    public Job findJobByName(String name){
		Job Job = jobMapper.selectByName(name);
		sqlSession.close();
		return Job;
	}
    
    public Job findJobById(Integer id){
		Job Job = jobMapper.selectById(id);
		sqlSession.close();
		return Job;
	}
    
    public void deleteAll(String[] idArray){
    	
    	try {
    		for(String id:idArray){
    			jobMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
	}
    
    public void updateJob(Job job){
    	try {
    		jobMapper.update(job);
		 sqlSession.commit();

    	} finally {
    		sqlSession.close();
        }
	}
    
	public String saveJob(Job job){
    	String str = "";
    	try {
	    	if(jobMapper.selectByName(Job.getName())==null){
	    		jobMapper.save(job);
			 sqlSession.commit();
			str =job.getName()+"信息保存成功。";
	    	}else{
	    		str ="该职务已经存在。";
	    	}
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
}
