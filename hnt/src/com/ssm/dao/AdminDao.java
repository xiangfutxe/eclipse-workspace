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
	    		str ="该员工已经存在。";
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
    
 public void deleteAll(String ids){
    	
    	try {
    		String[] idArray = ids.split(",");
    		for(String id:idArray){
    			adminMapper.deleteById(Integer.valueOf(id));
    		}
    		sqlSession.commit();
    	} finally {
    		sqlSession.close();
        }
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
 	
 	  public String saveEmptyUser(List<User> ulist,Timestamp date){
	    	String str = "";
	    	try{
	    		
	    		for(int i=0;i<ulist.size();i++){
	    			User user = ulist.get(i);
	    			Timestamp entryTime= new Timestamp(date.getTime()+1000*(i+1));
	    			user.setEntryTime(entryTime);
	    		User refereeUser = userMapper.selectByUserIdForUpdate(user.getUserByRefereeId());
	    		User belongUser = userMapper.selectByUserIdForUpdate(user.getUserByBelongId());
	    		User declarationUser = userMapper.selectByUserIdForUpdate(user.getUserByDeclarationId());
	    		User user1 = userMapper.selectByUserId(user.getUserId());
	    		if(user1==null){
	    		if(refereeUser!=null&&refereeUser.getState()>=1){
	    			if(belongUser!=null&&belongUser.getState()>=1){
	    				if(declarationUser!=null){
							if (user.getNodeTag() == 1)
								belongUser.setUserByAId(user.getUserId());
							else if (user.getNodeTag() == 2)
								belongUser.setUserByBId(user.getUserId());
							else if (user.getNodeTag() == 3)
								belongUser.setUserByCId(user.getUserId());
							user.setRefereeId(refereeUser.getId());
							user.setBelongId(belongUser.getId());
							user.setDeclarationId(declarationUser.getId());
							user.setNode(cs.getNode(belongUser));
							user.setRefereeNode(cs.getRefereeNode(refereeUser));
							user.setDeclarationNode(cs.getDeclarationNode(declarationUser));
							user.setNodeABC(cs.getNodeABC(belongUser, user.getNodeTag()));
							userMapper.saveUser(user);
							if(user.getId()!=null){
								int new_id = user.getId();
								if(userMapper.saveUserInfo(user)>0){
									WSettle ws = new WSettle();
									ws.setuId(new_id);
									ws.setUserId(user.getUserId());
									ws.setUserName(user.getUserName());
									ws.setTotalPerformance(user.getTotalPerformance());
									ws.setTotalPrice(user.getTotalPrice());
									ws.setAtprice(user.getAtprice());
									ws.setAtpv(user.getAtpv());
									ws.setAcpv(user.getAcpv());
									ws.setBtprice(user.getBtprice());
									ws.setBtpv(user.getBtpv());
									ws.setBcpv(user.getBcpv());
									ws.setTotalNum(user.getRefereeNum());
									ws.setTotalNumReal(user.getRaiseNum());
									ws.setRtprice(user.getRtprice());
									ws.setRtpv(user.getRtpv());
									ws.setRtprice1(user.getRtprice1());
									ws.setRtpv1(user.getRtpv1());
									ws.setEntryTime(user.getEntryTime());
									ws.setState(2);
									if(wsMapper.save(ws)>0){
										JoinInfo jf = new JoinInfo();
										jf.setUid(new_id);
										jf.setUserId(user.getUserId());
										jf.setUserName(user.getUserName());
										jf.setEntryTime(user.getEntryTime());
										jf.setNewRankJoin(user.getRankJoin());
										jf.setDeclarationId(declarationUser.getId());
										jf.setRid(refereeUser.getId());
										jf.setState(2);
									if(joinInfoMapper.save(jf)>0){	
										String refereeAll = StringUtil.notNull(refereeUser.getRefereeAll());
										if (refereeAll.equals(""))
											refereeAll = String
													.valueOf(new_id);
										else
											refereeAll = refereeAll
													+ ","
													+ String.valueOf(new_id);
										refereeUser.setRefereeAll(refereeAll);
										if(userMapper.updateUser(refereeUser)>0){
											if(userMapper.updateUser(belongUser)>0){
												if(userMapper.updateUser(declarationUser)>0){
													if(user.getEmoney()>0){
											
					    							AccountDetail ad  = new AccountDetail();
					    							ad.setUid(user.getId());
					    				    		ad.setUserId(user.getUserId());
					    				    		ad.setUserName(user.getUserName());
					    				    		ad.setAmount(user.getEmoney());
					    				    		ad.setBalance(user.getEmoney());
					    				    		ad.setTradeType("移网导入");
					    				    		ad.setSummary("移网导入");
					    				    		ad.setPayType(1);
					    				    		ad.setEntryTime(user.getEntryTime());
					    				    		Map<String,Object> params = new HashMap<>();
					    			    			params.put("account",ad);
					    			    			params.put("tableName", Constants.EMONEYDETAIL_TABLE);
					    			    			moneyMapper.save(params);
													}
													if(user.getDmoney()>0){
					    			    			AccountDetail ad1  = new AccountDetail();
					    			    			ad1.setUid(user.getId());
					    				    		ad1.setUserId(user.getUserId());
					    				    		ad1.setUserName(user.getUserName());
					    				    		ad1.setAmount(user.getDmoney());
					    				    		ad1.setBalance(user.getDmoney());
					    				    		ad1.setTradeType("移网导入");
					    				    		ad1.setSummary("移网导入");
					    				    		ad1.setPayType(1);
					    				    		ad1.setEntryTime(user.getEntryTime());
					    				    		Map<String,Object> params = new HashMap<>();
					    			    			params.put("account",ad1);
					    			    			params.put("tableName", Constants.DMONEYDETAIL_TABLE);
					    			    			moneyMapper.save(params);
													}
													if(user.getRmoney()>0){
					    			    			AccountDetail ad2  = new AccountDetail();
					    			    			ad2.setUid(user.getId());
					    				    		ad2.setUserId(user.getUserId());
					    				    		ad2.setUserName(user.getUserName());
					    				    		ad2.setAmount(user.getRmoney());
					    				    		ad2.setBalance(user.getRmoney());
					    				    		ad2.setTradeType("移网导入");
					    				    		ad2.setSummary("移网导入");
					    				    		ad2.setPayType(1);
					    				    		ad2.setEntryTime(user.getEntryTime());
					    				    		Map<String,Object> params = new HashMap<>();
					    			    			params.put("account",ad2);
					    			    			params.put("tableName", Constants.RMONEYDETAIL_TABLE);
					    			    			moneyMapper.save(params);
													}
												
												}else{
													str="报单人信息保存失败。";
												}
											}else{
												str="销售商信息保存失败。";
											}
										}else{
											str="服务商信息保存失败。";
										}
									}else{
										str="会员加盟信息保存失败。";
									}
									}else{
										str="会员结算资料保存失败。";
									}
								}else{
									str="会员基本资料保存失败。";
								}
							}else{
								str="会员ID生成失败。";
							}
	    				}else{
			    			str="报单人信息获取失败。";
			    		}
	    				
	    			}else{
		    			str="销售商处于未激活状态，不能进行操作。";
		    		}
	    		}else{
	    			str="服务商处于未激活状态，不能进行操作。";
	    		}
	    		}else{
	    			str="该会员编号已注册。";
	    		}
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
 	 
 	 public String updateUserRefereeNum(){
	    	String str = "";
	    	try{
	    		Timestamp endTime = new Timestamp(StringUtil.parseToDate("2018-03-26 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
	    		User sel_user = new User();
	    		sel_user.setEndTime(endTime);
	    		Map<String,Object> params = new HashMap<>();
	    		params.put("user",sel_user);
	    		List<User> ulist = userMapper.selectMoneyByPage(params);
	    		Integer maxId= userMapper.maxId(params);
	    		int num = 1;
	    		if(maxId!=null) num =maxId+1;
	    		if(num>1){
	    			User[] user = new User[num+1];
	    			DSettle[] wst = new DSettle[num+1];
	    			for(int i=0;i<ulist.size();i++){
	    				int id = ulist.get(i).getId();
	    				if(user[id]==null) user[id] = new User();
	    				user[id].setId(id);
	    				user[id].setUserId(ulist.get(i).getUserId()); 
	    				user[id].setUserName(ulist.get(i).getUserName());
	    				user[id].setIsEmpty(ulist.get(i).getIsEmpty());
	    				user[id].setEntryTime(ulist.get(i).getEntryTime());
	    				user[id].setRefereeId(ulist.get(i).getRefereeId());
	    				user[id].setState(ulist.get(i).getState());
	    				user[id].setRaiseNum(ulist.get(i).getRaiseNum());
	    				user[id].setTotalIncome(ulist.get(i).getTotalIncome());
	    				user[id].setTag(0);
	    			}
	    			DSettle wst_sel = new DSettle();
	    			wst_sel.setEndTime(endTime);

	    			params.put("wst",wst_sel);
	    			List<DSettle> wslist = wstMapper.selectListWithParam(params);
	    			for(int i=0;i<wslist.size();i++){
	    				int id = wslist.get(i).getuId();
	    				if(wst[id]==null) wst[id] = new DSettle();
	    				wst[id].setId(wslist.get(i).getId());
	    				wst[id].setTotalNum(0);
	    				wst[id].setNewNum(wslist.get(i).getTotalNum());
						wst[id].setTotalNumReal(0);
	    				wst[id].setNewNumReal(wslist.get(i).getTotalNumReal());
						wst[id].setTag(0);
	    			}
	    			
	    			for(int i=0;i<user.length;i++){
	    				if(user[i]!=null){
	    					if(wst[i]!=null){
	    						int rid = user[i].getRefereeId();
	    						if(rid>0){
	    							if(user[i].getState()>0){
	    							wst[rid].setTotalNum(wst[rid].getTotalNum()+1);
	    							if(user[i].getIsEmpty()==0){
	    								wst[rid].setTotalNumReal(wst[rid].getTotalNumReal()+1);
	    							}
	    							}
	    						}
	    					}
	    				}
	    			}
	    			int error=0;
	    			for(int i=0;i<wst.length;i++){
	    				if(wst[i]!=null){
	    						if(wstMapper.update(wst[i])==null)
	    							error++;
	    				}
	    			}
	    			if(error>0){
	    				str="系统推荐人数更新有误,请重新确认。";
	    			}else{
	    				str="系统推荐人数更新成功，请重新确认。";
	    				sqlSession.commit();
	    			}
	    		}
	    			
	    			
	    			
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		str ="系统繁忙中，请稍后再试。";
	    		sqlSession.rollback();
	    	}finally {
	    		sqlSession.close();
	        }
			return str;
		}
 	 
 	 public String saveCent(List<Center> ulist,Timestamp date){
	    	String str = "";
	    	try{
	    		
	    		for(int i=0;i<ulist.size();i++){
	    			User user = userMapper.selectMoneyByUserId(ulist.get(i).getUserId());
	    			if(user!=null){
	    			Timestamp entryTime = new Timestamp(date.getTime()+(i+1)*1000);
	    			ulist.get(i).setuId(user.getId());
	    			ulist.get(i).setEntryTime(entryTime);
	    			ulist.get(i).setPassword(MD5.GetMD5Code("12345678"));
		    		Integer num =  centerMapper.save(ulist.get(i));
		    		if(num==null||num==0) str+="1";
	    			}
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
 	 
 	 public String updateTotalIncome(){
	    	String str = "";
	    	try{
	    		Timestamp endTime = new Timestamp(StringUtil.parseToDate("2018-03-25 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
	    		Timestamp as_time = new Timestamp(StringUtil.parseToDate("2018-03-20 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
	    		
	    		Timestamp startTime = new Timestamp(StringUtil.parseToDate("2018-03-20 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());

	    		User sel_user = new User();
	    		sel_user.setEndTime(endTime);
	    		Map<String,Object> params = new HashMap<>();
	    		params.put("user",sel_user);
	    		List<User> ulist = userMapper.selectMoneyByPage(params);
	    		Integer maxId= userMapper.maxId(params);
	    		int num = 1;
	    		if(maxId!=null) num =maxId+1;
	    		if(num>1){
	    			User[] user = new User[num+1];
	    			DSettle[] wst = new DSettle[num+1];
	    			for(int i=0;i<ulist.size();i++){
	    				int id = ulist.get(i).getId();
	    				if(user[id]==null) user[id] = new User();
	    				user[id].setId(id);
	    				user[id].setUserId(ulist.get(i).getUserId()); 
	    				user[id].setUserName(ulist.get(i).getUserName());
	    				user[id].setIsEmpty(ulist.get(i).getIsEmpty());
	    				user[id].setEntryTime(ulist.get(i).getEntryTime());
	    				user[id].setRefereeId(ulist.get(i).getRefereeId());
	    				user[id].setState(ulist.get(i).getState());
	    				user[id].setRaiseNum(ulist.get(i).getRaiseNum());
	    				user[id].setTotalIncome((double) 0);
	    				user[id].setRmoney(ulist.get(i).getTotalIncome());
	    				user[id].setTag(0);
	    			}
	    			
	    			
	    			for(int i=12;i<=20;i++){
	    				WReward wrd_sel = new WReward();
	    				wrd_sel.setPayTag(1);
		    			params.put("wrd",wrd_sel);
		    			params.put("tableName","wreward_"+i);
		    			List<WReward> wlist = wrdMapper.selectByList(params);
		    			for(int j=0;j<wlist.size();j++){
		    				int id=wlist.get(j).getuId();
		    				if(user[id]!=null){
		    					if(wlist.get(j).getUserId().equals("GL00078634"))
		    						System.out.println("GL00078634:"+wlist.get(j).getAmount_9()+";");
		    					if(wlist.get(j).getAmount_9()>0){
		    						user[id].setTotalIncome(ArithUtil.add(user[id].getTotalIncome(), wlist.get(j).getAmount_9()));
		    					}
		    				}
		    			}

	    			}
	    			AccountSupplement asm = new AccountSupplement();
	    			asm.setStartTime(startTime);
					asm.setEndTime(as_time);
					asm.setType(4);
					params.put("accountSupplement",asm);
	    			List<AccountSupplement> slist = asmMapper.selectByList(params);
	    			for(int i=0;i<slist.size();i++){
	    				int id = slist.get(i).getuId();
	    				if(user[id]!=null){
	    					if(slist.get(i).getPayType()==1){
	    						user[id].setTotalIncome(ArithUtil.add(user[id].getTotalIncome(), ArithUtil.div(slist.get(i).getAmount(),0.8)));
	    					}
	    				}
	    			}
	    			int error=0;

	    			for(int i=0;i<user.length;i++){
	    				if(user[i]!=null){
	    							if(user[i].getState()>0){
	    						if(userMapper.updateForSql("update users set totalIncome='"+user[i].getTotalIncome()+"' where id='"+user[i].getId()+"'")==null)
	    							error++;
	    					}
	    				}
	    			}
	    			if(error>0){
	    				str="会员总收入更新有误,请重新确认。";
	    			}else{
	    				str="会员总收入更新成功。";
	    				sqlSession.commit();
	    			}
	    		}
	    			
	    			
	    			
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		str ="系统繁忙中，请稍后再试。";
	    		sqlSession.rollback();
	    	}finally {
	    		sqlSession.close();
	        }
			return str;
		}
 	 
 	public String updateNodeId(){
    	String str = "";
    	try{
    		Timestamp endTime = new Timestamp(StringUtil.parseToDate("2018-03-25 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
    		Timestamp startTime = new Timestamp(StringUtil.parseToDate("2018-03-20 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());

    		User sel_user = new User();
    		sel_user.setEndTime(endTime);
    		Map<String,Object> params = new HashMap<>();
    		params.put("user",sel_user);
    		List<User> ulist = userMapper.selectMoneyByPage(params);
    		Integer maxId= userMapper.maxId(params);
    		int num = 1;
    		if(maxId!=null) num =maxId+1;
    		if(num>1){
    			User[] user = new User[num+1];
    			DSettle[] wst = new DSettle[num+1];
    			for(int i=0;i<ulist.size();i++){
    				int id = ulist.get(i).getId();
    				if(user[id]==null) user[id] = new User();
    				user[id].setId(id);
    				user[id].setUserId(ulist.get(i).getUserId()); 
    				user[id].setUserName(ulist.get(i).getUserName());
    				user[id].setIsEmpty(ulist.get(i).getIsEmpty());
    				user[id].setEntryTime(ulist.get(i).getEntryTime());
    				user[id].setRefereeId(ulist.get(i).getRefereeId());
    				user[id].setState(ulist.get(i).getState());
    				user[id].setNode(StringUtil.notNull(ulist.get(i).getNode()));
    				user[id].setNodeABC(StringUtil.notNull(ulist.get(i).getNodeABC()));
    				user[id].setRaiseNum(ulist.get(i).getRaiseNum());
    				user[id].setTotalIncome((double) 0);
    				user[id].setRmoney(ulist.get(i).getTotalIncome());
    				user[id].setTag(0);
    			}
    			
    			DSettle wst_sel = new DSettle();
    			wst_sel.setEndTime(endTime);
    			params.put("wst",wst_sel);
    			List<DSettle> wslist = wstMapper.selectListWithParam(params);
    			for(int i=0;i<wslist.size();i++){
    				int id = wslist.get(i).getuId();
    				if(wst[id]==null) wst[id] = new DSettle();
    				wst[id].setId(wslist.get(i).getId());
    				wst[id].setTotalNum(0);
    				wst[id].setNewNum(wslist.get(i).getTotalNum());
					wst[id].setTotalNumReal(0);
    				wst[id].setNewNumReal(wslist.get(i).getTotalNumReal());
    				wst[id].setJoinPriceNew((double) 0);
    				wst[id].setJoinPvNew((double) 0);
    				wst[id].setLeftNodeId(StringUtil.notNull(wslist.get(i).getLeftNodeId()));
    				wst[id].setRightNodeId(StringUtil.notNull(wslist.get(i).getRightNodeId()));
    				wst[id].setLeftNodePv(StringUtil.notNull(wslist.get(i).getLeftNodePv()));
    				wst[id].setRightNodePv(StringUtil.notNull(wslist.get(i).getRightNodePv()));
    				wst[id].setLeftNodePrice(StringUtil.notNull(wslist.get(i).getLeftNodePrice()));
    				wst[id].setRightNodePrice(StringUtil.notNull(wslist.get(i).getRightNodePrice()));
    				wst[id].setNodeTag(StringUtil.notNull(wslist.get(i).getNodeTag()));
					wst[id].setTag(0);
					wst[id].setCtag(0);
    			}
    			
    			for(int i=0;i<user.length;i++){
    				if(user[i]!=null){
    					int uid = user[i].getId();
    					if(!StringUtil.notNull(user[i].getNode()).equals("")){
    					 String[] str_node = user[i].getNode().split(",");
						 String[] strarray = user[i].getNodeABC().split(",");
						 double pv = 0;
						 double price = 0;
						 int t=0;
						 for(int j=str_node.length-1;j>=0;j--){
							if(!str_node[j].equals("")){
								 t++;
								int sid = Integer.valueOf(str_node[j]);
								 String[] node_tag = wst[sid].getNodeTag().split(",");
								 int node_num = 0;
								 if(!wst[sid].getNodeTag().equals("")) node_num = node_tag.length;
								 if(strarray[j].equals("A")){
									 String[] node_str_id = wst[sid].getLeftNodeId().split(",");
									 String[] node_str_pv  =wst[sid].getLeftNodePv().split(",");
									 String[] node_str_price  =wst[sid].getLeftNodePrice().split(",");
									 int node_num_l = 0;
									 if(!wst[sid].getLeftNodeId().equals("")) node_num_l = node_str_id.length;
									 if(t>node_num_l){
										 if(t==1){
											 	wst[sid].setLeftNodeId(String.valueOf(uid));
											 	if(t>node_num) wst[sid].setNodeTag("1");
    											 wst[sid].setLeftNodePv(String.valueOf(pv));
    											 wst[sid].setLeftNodePrice(String.valueOf(price));
    											 wst[sid].setCtag(1);
										 }else{
											 wst[sid].setLeftNodeId(wst[sid].getLeftNodeId()+","+String.valueOf(uid));
											 if(t>node_num) wst[sid].setNodeTag(wst[sid].getNodeTag()+","+"1");
											 wst[sid].setLeftNodePv(wst[sid].getLeftNodePv()+","+String.valueOf(pv));
											 wst[sid].setLeftNodePrice(wst[sid].getLeftNodePrice()+","+String.valueOf(price));
											 wst[sid].setCtag(1);
										 }
									 }else{
										if(node_tag[t-1].equals("0")){
    										int node_id = Integer.valueOf(node_str_id[t-1]);
    										double node_pv = Double.valueOf(node_str_pv[t-1]);
    										double node_price = Double.valueOf(node_str_price[t-1]);
    										node_str_pv[t-1] = String.valueOf(ArithUtil.add(node_pv, pv));
    										node_str_price[t-1] = String.valueOf(ArithUtil.add(node_price, price));
    										if(ArithUtil.sub(wst[node_id].getJoinPvNew(), wst[uid].getJoinPvNew())>0){
    											 node_str_id[t-1]=String.valueOf(uid);
    											 node_str_pv[t-1]=String.valueOf(wst[uid].getJoinPvNew());
     											node_str_price[t-1]=String.valueOf(wst[uid].getJoinPriceNew());
    										 }
    										for(int s=0;s<node_str_id.length;s++){
    											if(s==0){
    												 wst[sid].setLeftNodeId(node_str_id[s]);
        											 wst[sid].setLeftNodePv(node_str_pv[s]);
        											 wst[sid].setLeftNodePrice(node_str_price[s]);
    											}else{
    												 wst[sid].setLeftNodeId(wst[sid].getLeftNodeId()+","+node_str_id[s]);
        											 wst[sid].setLeftNodePv(wst[sid].getLeftNodePv()+","+node_str_pv[s]);
        											 wst[sid].setLeftNodePrice(wst[sid].getLeftNodePrice()+","+node_str_price[s]);
    											}
    										}
										}
									 }
								 } else if(strarray[j].equals("B")) {
									 String[] node_str_id = wst[sid].getRightNodeId().split(",");
									 String[] node_str_pv  =wst[sid].getRightNodePv().split(",");
									 String[] node_str_price  =wst[sid].getRightNodePrice().split(",");
									 int node_num_l = 0;
									 if(!wst[sid].getRightNodeId().equals("")) node_num_l = node_str_id.length;
									 if(t>node_num_l){
										 if(t==1){
											 wst[sid].setRightNodeId(String.valueOf(uid));
											 if(t>node_num)  wst[sid].setNodeTag("1");
											 wst[sid].setRightNodePv(String.valueOf(pv));
											 wst[sid].setRightNodePrice(String.valueOf(price));
											 wst[sid].setCtag(1);
										 }else{
											 wst[sid].setRightNodeId(wst[sid].getRightNodeId()+","+String.valueOf(uid));
											 if(t>node_num) wst[sid].setNodeTag(wst[sid].getNodeTag()+","+"1");
												 wst[sid].setRightNodePv(wst[sid].getRightNodePv()+","+String.valueOf(pv));
												 wst[sid].setRightNodePrice(wst[sid].getRightNodePrice()+","+String.valueOf(price));
												 wst[sid].setCtag(1);
										 }
									 }else{
										if(node_tag[t-1].equals("0")){
    										int node_id = Integer.valueOf(node_str_id[t-1]);
    										double node_pv = Double.valueOf(node_str_pv[t-1]);
    										double node_price = Double.valueOf(node_str_price[t-1]);
    										node_str_pv[t-1] = String.valueOf(ArithUtil.add(node_pv, pv));
    										node_str_price[t-1] = String.valueOf(ArithUtil.add(node_price, price));
    										if(ArithUtil.sub(wst[node_id].getJoinPvNew(), wst[uid].getJoinPvNew())>0){
    											node_str_id[t-1]=String.valueOf(uid);
    											node_str_pv[t-1]=String.valueOf(wst[uid].getJoinPvNew());
    											node_str_price[t-1]=String.valueOf(wst[uid].getJoinPriceNew());
    										 }
    										for(int s=0;s<node_str_id.length;s++){
    											if(s==0){
    												 wst[sid].setRightNodeId(node_str_id[s]);
        											 wst[sid].setRightNodePv(node_str_pv[s]);
        											 wst[sid].setRightNodePrice(node_str_price[s]);
    											}else{
    												 wst[sid].setRightNodeId(wst[sid].getRightNodeId()+","+node_str_id[s]);
        											 wst[sid].setRightNodePv(wst[sid].getRightNodePv()+","+node_str_pv[s]);
        											 wst[sid].setRightNodePrice(wst[sid].getRightNodePrice()+","+node_str_price[s]);
    											}
    										}
										}
									 }
								 }
							}
						 }
    				}
    				}
    			}
    			
    		
    			int error=0;

    			for(int i=0;i<wst.length;i++){
    				if(wst[i]!=null){
    						DSettle ds =new DSettle();
    						ds.setId(wst[i].getId());
    						ds.setLeftNodeId(wst[i].getLeftNodeId());
    						ds.setRightNodeId(wst[i].getRightNodeId());
    						ds.setLeftNodePv(wst[i].getLeftNodePv());
    						ds.setRightNodePv(wst[i].getRightNodePv());
    						ds.setLeftNodePrice(wst[i].getLeftNodePrice());
    						ds.setRightNodePrice(wst[i].getRightNodePrice());
    						ds.setNodeTag(wst[i].getNodeTag());
    						if(wstMapper.update(ds)==null)
    							error++;
    				}
    			}
    			if(error>0){
    				str="会员层碰节点更新有误,请重新确认。";
    			}else{
    				str="会员层碰节点更新成功。";
    				sqlSession.commit();
    			}
    		}
    			
    			
    			
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
