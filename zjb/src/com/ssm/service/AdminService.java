package com.ssm.service;

import java.util.List;

import com.ssm.pojo.Admin;
import com.utils.Pager;

public interface AdminService {
	
	public int insert(Admin admin);
	
	public int insertList(List<Admin> adminList);
	
	public Admin findById(Integer id);
	
	public Admin findByAdminName(String name);
	
	public int update(Admin admin);
	
	public int update(String String);
	
	public Pager findByPager(Admin admin,Pager pager);
	
	public Admin login(Admin admin);


}
