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

public class AdminDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
    //创建能执行映射文件中sql的sqlSession
	AccountSupplementMapper asmMapper = sqlSession.getMapper(AccountSupplementMapper.class);

    AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    JoinInfoMapper joinInfoMapper = sqlSession.getMapper(JoinInfoMapper.class);
    WSettleMapper wsMapper = sqlSession.getMapper(WSettleMapper.class);
    DSettleMapper wstMapper = sqlSession.getMapper(DSettleMapper.class);
    WRewardMapper wrdMapper = sqlSession.getMapper(WRewardMapper.class);

    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
    OrderDetailMapper orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
    ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
    ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);
    MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);
    AddressMapper adrMapper = sqlSession.getMapper(AddressMapper.class);
    CenterMapper centerMapper = sqlSession.getMapper(CenterMapper.class);
    AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
    
    ICustomService cs = new CustomService();
    Admin admin= new Admin();
    
    public Admin login(String adminName,String password){
    		admin = adminMapper.login(adminName, password);
    		sqlSession.close();
            return admin;
    }
    
    public Pager findAdmin(Admin admin,Pager pager){
		Map<String,Object> params = new HashMap<>();
		params.put("admin",admin);
		int recordCount = adminMapper.count(params);
		pager.setRowCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pager);
		}
		List<Admin> admins = adminMapper.selectByPage(params);
		pager.setResultList(admins);
		sqlSession.close();
		return pager;
	}
    
    public String saveAdmin(Admin admin){
    	String str = "";
    	try {
	    	if(adminMapper.selectByName(admin.getAdminName())==null){
	    		adminMapper.save(admin);
			 sqlSession.commit();
			str =admin.getAdminName()+"信息保存成功。";
	    	}else{
	    		str ="该用户已经存在。";
	    	}
    	}finally {
    		sqlSession.close();
        }
    	return str;
	}
    
    public String updateAdmin(Admin admin){
    	String str = "";
    	try {
    	adminMapper.update(admin);
		 sqlSession.commit();
		 str =admin.getAdminName()+"信息修改成功。";
    	} finally {
    		sqlSession.close();
        }
    	return str;
	}
    
 public int deleteAll(String[] ids){
    	int  count=0;
    	try {
    		for(String id:ids){
    			count = count+adminMapper.deleteByIdForState(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
    	return count;
	}
 
 	public Admin findAdminByName(String name){
 		Admin admin = adminMapper.selectByName(name);
		sqlSession.close();
		return admin;
	}
 
 	public Admin findAdminById(Integer id){
		Admin admin = adminMapper.selectById(id);
		sqlSession.close();
		return admin;
	}
 	
 	  
 	 public String saveAdr(List<Address> ulist,Timestamp date){
	    	String str = "";
	    	try{
	    		
	    		for(int i=0;i<ulist.size();i++){
		    		Integer num =  adrMapper.save(ulist.get(i));
		    		if(num==null||num==0) str+="1";
		    		if(str!="") i =ulist.size();
	    		}
	    		if(str!="") sqlSession.rollback();
	    		else sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		str ="系统繁忙中，请稍后再试。";
	    		sqlSession.rollback();
	    	}finally {
	    		sqlSession.close();
	        }
			return str;
		}
 	 
}
