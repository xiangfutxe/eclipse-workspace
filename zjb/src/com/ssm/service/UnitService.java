package com.ssm.service;
import com.ssm.pojo.Unit;
import com.utils.Pager;

public interface UnitService {
	
	public int insert(Unit unit);
	
	public int update(Unit unit);
	
	public int delete(String str);
	
	
	public int remove(String str);
	
	
	public Unit findById(Long id);
	
	public Unit findByName(String name);
	
	public Pager findByPager(Unit unit,Pager pager);

}
