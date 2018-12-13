package com.ssm.service;

import com.ssm.pojo.Member;
import com.ssm.pojo.MemberInfo;
import com.utils.Pager;

public interface MemberService {
	
	public int insert(Member member,MemberInfo info);
	
	public Member findById(Long id);
	
	public Member findByNickName(String name);
	
	public Member findAllById(Long id);
	
	public Member findAllByIdForInfo(Long id);
	
	public int update(MemberInfo Member);
	
	public int update(Member Member);

	public int update(String String);
	
	public Pager findByPager(Member Member,Pager pager);
	
	public Member login(Member Member);


}
