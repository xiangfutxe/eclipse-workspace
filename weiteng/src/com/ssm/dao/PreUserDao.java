package com.ssm.dao;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;

import com.ssm.pojo.Settle;
import com.ssm.pojo.OrderDetail;
import com.ssm.mapper.AddressMapper;
import com.ssm.mapper.AdminLogMapper;
import com.ssm.mapper.CenterMapper;
import com.ssm.mapper.JoinInfoMapper;
import com.ssm.mapper.MoneyMapper;
import com.ssm.mapper.OrderDetailMapper;
import com.ssm.mapper.OrderMapper;
import com.ssm.mapper.ParamMapper;
import com.ssm.mapper.ProductMapper;
import com.ssm.mapper.PromotionMapper;
import com.ssm.mapper.SettleMapper;
import com.ssm.mapper.UserLogMapper;
import com.ssm.mapper.UserMapper;
import com.ssm.mapper.WRewardMapper;
import com.ssm.mapper.WSettleMapper;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Address;
import com.ssm.pojo.AdminLog;
import com.ssm.pojo.Center;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.Order;
import com.ssm.pojo.Param;
import com.ssm.pojo.Product;
import com.ssm.pojo.Promotion;
import com.ssm.pojo.User;
import com.ssm.pojo.UserLog;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WSettle;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.Constants;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.SqlSessionFactoryUtils;
import com.ssm.utils.StringUtil;
import com.ssm.utils.ArithUtil;
public class PreUserDao {
	SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
	    //创建能执行映射文件中sql的sqlSession
	    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	    JoinInfoMapper joinInfoMapper = sqlSession.getMapper(JoinInfoMapper.class);
	    WSettleMapper wsMapper = sqlSession.getMapper(WSettleMapper.class);
	    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
	    OrderDetailMapper orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
	    ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
	    ParamMapper paramMapper = sqlSession.getMapper(ParamMapper.class);
	    MoneyMapper moneyMapper = sqlSession.getMapper(MoneyMapper.class);
	    AddressMapper adrMapper = sqlSession.getMapper(AddressMapper.class);
	    CenterMapper centerMapper = sqlSession.getMapper(CenterMapper.class);
	    AdminLogMapper adminLogMapper = sqlSession.getMapper(AdminLogMapper.class);
	    PromotionMapper proMapper = sqlSession.getMapper(PromotionMapper.class);
	    SettleMapper stMapper = sqlSession.getMapper(SettleMapper.class);
	    WRewardMapper wrdMapper = sqlSession.getMapper(WRewardMapper.class);
	    UserLogMapper userLogMapper = sqlSession.getMapper(UserLogMapper.class);




	    ICustomService cs = new CustomService();
	    public User login(String userId,String password,String ip){
		    User user= null;
	    	try{
	    		user = userMapper.login(userId, password);
	    		Timestamp date = new Timestamp(new Date().getTime());
	    		if(user!=null){
	    			UserLog userlog = new UserLog();
	    			userlog.setUid(user.getId());
	    			userlog.setUserId(user.getUserId());
	    			userlog.setUserName(user.getUserName());
	    			userlog.setType(ConstantsLog.USERLOGTYPE_1);
	    			String adr = StringUtil.getUrlContent(ip);
	    			String address="不详";
	    			if(adr!=null){
	    			 JSONObject jsonObject = JSONObject.fromObject(adr);
	    				 if(jsonObject.get("data")!=null){
	    					 JSONObject json =  JSONObject.fromObject(jsonObject.get("data"));
	    					 if(json.get("country")!=null&&!json.get("country").equals("XX")&&!json.get("region").equals("")) address = (String) json.get("country");
	    					 if(json.get("region")!=null&&!json.get("region").equals("XX")&&!json.get("region").equals("")) address+= "-"+(String) json.get("region");
	    					 if(json.get("city")!=null&&!json.get("city").equals("XX")&&!json.get("city").equals("")) address+= "-"+(String) json.get("city");
	    					 if(json.get("county")!=null&&!json.get("county").equals("XX")&&!json.get("county").equals("")) address+= "-"+(String) json.get("county");
	    					 if(json.get("isp")!=null&&!json.get("isp").equals("XX")&&!json.get("isp").equals("")) address+= "，网络服务商:"+(String) json.get("isp");
	    				 }
	    			 }
	    			userlog.setContents("会员登陆成功，主机IP:"+ip+"，所在区域:"+address+"。");
	    			userlog.setEntryTime(date);
	    			if(userLogMapper.save(userlog)!=null){
	    				String sql = "update users set visit_time='"+date+"',visit_num=visit_num+1 where id='"+user.getId()+"'";
	    				if(userMapper.updateForSql(sql)!=null){
	    					sqlSession.commit();
	    				}
	    			}
	    		}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	    	}
	            return user;
	    }
	    
	    public User user_detail(Integer id){
	    	User user= null;
	    	try{
    		user = userMapper.selectById(id);
    		sqlSession.commit();
		    }catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
    		sqlSession.close();
	    	}
            return user;
	    }
	    
	    public int maxId(User user){
	    	int maxId=0;
	    	try{
	    		Map<String,Object> params = new HashMap<>();
	    		params.put("user",user);
    		Integer max =  userMapper.maxId(params);
    		if(max==null) maxId=0;
    		else maxId = max;
    		sqlSession.commit();
		    }catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
    		sqlSession.close();
	    	}
            return maxId;
	    }
	    
	    public User findUserInfoByUseId(String userId){
	    	User user= null;
	    	try{
    		user = userMapper.selectUserInfoByUserId(userId);
    		sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
    		sqlSession.close();
	    	}
            return user;
	    }
	    
	    public Pager findUserByPage(User user,Pager pager){
	    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("user",user);
			int recordCount = userMapper.count(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<User> users = userMapper.selectByPage(params);
			pager.setResultList(users);
			sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		sqlSession.close();
	    	}finally{
	        }
			return pager;
		}
	    
	    public Pager findUsersByPage(User user,Pager pager){
	    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("user",user);
			int recordCount = userMapper.countAll(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<User> users = userMapper.selectUsersByPage(params);
			pager.setResultList(users);
			sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		sqlSession.close();
	    	}finally{
	        }
			return pager;
		}
	    
	    public List<User> findUserByList(User user){
	    	List<User> users = null;
	    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("user",user);
			 users = userMapper.selectByList(params);
			sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		sqlSession.close();
	    	}finally{
	        }
			return users;
		}
	    
	    public List<User> findUserBySql(String sql){
	    	List<User> users = null;
	    	try{
			
			 users = userMapper.selectUserBySql(sql);
			sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		sqlSession.close();
	    	}finally{
	        }
			return users;
		}
	    
	    public Pager findUserMoneyByPage(User user,Pager pager){
	    	try{
			Map<String,Object> params = new HashMap<>();
			params.put("user",user);
			int recordCount = userMapper.count(params);
			pager.setRowCount(recordCount);
			if(recordCount>0){
				params.put("pageModel", pager);
			}
			List<User> users = userMapper.selectMoneyByPage(params);
			pager.setResultList(users);
			sqlSession.commit();;
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
			return pager;
		}
	    
	    public User findByUserId(String userId){
	    	User users = null;
	    	try{
			 users = userMapper.selectAllByUserId(userId);
			 sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	 
	    
	    public User findMoneyByUserId(String userId){
	    	User users = null;
	    	try{
			 users = userMapper.selectMoneyByUserId(userId);
			 sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	    public User findUsersById(Integer id){
	    	User users = null;
	    	try{
			 users = userMapper.selectUsersById(id);
			 sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	    public User findById(Integer id){
	    	User users = null;
	    	try{
			 users = userMapper.selectById(id);
			 sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	    public User findByEntryTime(Timestamp entryTime){
	    	User users = null;
	    	try{
			 users = userMapper.selectByEntryTime(entryTime);
			 sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	    public List<User> findUsersByProperty(String property,Integer id){
	    	List<User> users = null;
	    	try{
			 users = userMapper.selectUsersForProperty(property, id);
			 sqlSession.commit();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
			return users;
		}
	    
	    public void initUser(){
	    	try{
	    	Timestamp date = new Timestamp(new Date().getTime());
	    	User user = new User();
			user.setUserId("AA000000");
			user.setUserName("user");
			user.setSex("男");
			user.setNumId("1111111111111111111");
			user.setRankJoin(5);
			user.setPassword(MD5.GetMD5Code("111111"));
			user.setPassword2(MD5.GetMD5Code("111111"));
			user.setState(2);
			user.setEntryTime(date);
			int id = userMapper.saveUser(user);
			if(userMapper.saveUserInfo(user)!=null){
				WSettle wst = new WSettle();
				wst.setuId(id);
				wst.setUserId(user.getUserId());
				wst.setUserName(user.getUserName());
				wst.setState(2);
				wst.setEntryTime(date);
				if(wsMapper.save(wst)!=null)
					sqlSession.commit();
			}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}finally{
	    		sqlSession.close();
	        }
	    } 
	    
	    public String saveUserForAdmin(User user,User userinfo){
	    	String str = "";
	    	try{
	    		User belongUser = null;
	    		User declarationUser = null;
	    		User refereeUser = userMapper.selectByUserIdForUpdate(user.getUserByRefereeId());
	    		if(user.getUserByBelongId().equals(refereeUser.getUserId())){
	    			belongUser = refereeUser;
	    		}else
	    			belongUser = userMapper.selectByUserIdForUpdate(user.getUserByBelongId());
	    		if(user.getUserByDeclarationId().equals(refereeUser.getUserId())){
	    			declarationUser = refereeUser;
	    		}else if(user.getUserByDeclarationId().equals(belongUser.getUserId())){
	    			declarationUser = belongUser;
	    		}else 
	    		declarationUser = userMapper.selectByUserIdForUpdate(user.getUserByDeclarationId());
	    		User user1 = userMapper.selectByUserId(user.getUserId());
	    		if(user1==null){
	    		if(refereeUser.getState()==2){
	    			if(belongUser.getState()==2){
	    				List<User> nodeList = userMapper.selectByNodeTag(belongUser.getUserId(), user.getNodeTag());
	    				user.setUserIdByBelongCenter(declarationUser.getUserId());
	    				user.setIdByBelongCenter(declarationUser.getId());
	    				if(nodeList!=null&&nodeList.size()>0){
	    					str = "销售商所在区域已经存在会员。";
	    				}else{
							if (user.getNodeTag() == 1)
								belongUser.setUserByAId(user.getUserId());
							else if (user.getNodeTag() == 2)
								belongUser.setUserByBId(user.getUserId());
							else if (user.getNodeTag() == 3)
								belongUser.setUserByCId(user.getUserId());
							user.setRefereeId(refereeUser.getId());
							user.setBelongId(belongUser.getId());
							user.setDeclarationId(declarationUser.getId());
							user.setNode(getNode(belongUser));
							user.setRefereeNode(getRefereeNode(refereeUser));
							user.setDeclarationNode(getDeclarationNode(declarationUser));
							user.setNodeABC(getNodeABC(belongUser, user.getNodeTag()));
							userMapper.saveUser(user);
							if(user.getId()!=null){
								int new_id = user.getId();
								if(userMapper.saveUserInfo(userinfo)>0){
									WSettle ws = new WSettle();
									ws.setuId(new_id);
									ws.setUserId(user.getUserId());
									ws.setUserName(user.getUserName());
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
												str=user.getUserName()+"信息保存成功，会员编号为："+user.getUserId()+";";
												sqlSession.commit();
											}else{
												str="销售商信息保存失败。";
											sqlSession.rollback();
											}
										}else{
											str="服务商信息保存失败。";
										sqlSession.rollback();
										}
									}else{
										str="会员加盟信息保存失败。";
										sqlSession.rollback();
									}
									}else{
										str="会员结算资料保存失败。";
										sqlSession.rollback();
									}
								}else{
									str="会员基本资料保存失败。";
									sqlSession.rollback();
								}
	    				}else{
			    			str="会员信息保存失败，获取会员ID失败。";
			    			sqlSession.rollback();
			    		}
	    				}//end nodeTagList
	    				
	    			}else{
		    			str="销售商处于未激活状态，不能进行操作。";
		    		}
	    		}else{
	    			str="服务商处于未激活状态，不能进行操作。";
	    		}
	    		}else{
	    			str="该会员编号已注册。";
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
	    
	    public String saveEmptyUser(User user,User userinfo){
	    	String str = "";
	    	try{
	    		User refereeUser = userMapper.selectByUserIdForUpdate(user.getUserByRefereeId());
	    		User belongUser = userMapper.selectByUserIdForUpdate(user.getUserByBelongId());
	    		User declarationUser = userMapper.selectByUserIdForUpdate(user.getUserByDeclarationId());
	    		User user1 = userMapper.selectByUserId(user.getUserId());
	    		if(user1==null){
	    		if(refereeUser!=null&&refereeUser.getState()>=1){
	    			if(belongUser!=null&&belongUser.getState()>=1){
	    				if(declarationUser!=null&&declarationUser.getEmptyNum()-1>0){
	    					declarationUser.setEmptyNum(declarationUser.getEmptyNum()-1);
	    				List<User> nodeList = userMapper.selectByNodeTag(belongUser.getUserId(), user.getNodeTag());
	    				if(nodeList!=null&&nodeList.size()>0){
	    					str = "销售商所在区域已经存在会员。";
	    				}else{
							if (user.getNodeTag() == 1)
								belongUser.setUserByAId(user.getUserId());
							else if (user.getNodeTag() == 2)
								belongUser.setUserByBId(user.getUserId());
							else if (user.getNodeTag() == 3)
								belongUser.setUserByCId(user.getUserId());
							user.setRefereeId(refereeUser.getId());
							user.setBelongId(belongUser.getId());
							user.setDeclarationId(declarationUser.getId());
							user.setNode(getNode(belongUser));
							user.setRefereeNode(getRefereeNode(refereeUser));
							user.setDeclarationNode(getDeclarationNode(declarationUser));
							user.setNodeABC(getNodeABC(belongUser, user.getNodeTag()));
							userMapper.saveUser(user);
							if(user.getId()!=null){
								int new_id = user.getId();
								if(userMapper.saveUserInfo(userinfo)>0){
									WSettle ws = new WSettle();
									ws.setuId(new_id);
									ws.setUserId(user.getUserId());
									ws.setUserName(user.getUserName());
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
												str=user.getUserName()+"信息保存成功，会员编号为："+user.getUserId()+";";
												sqlSession.commit();
												}else{
													str="报单人信息保存失败。";
												sqlSession.rollback();
												}
											}else{
												str="销售商信息保存失败。";
											sqlSession.rollback();
											}
										}else{
											str="服务商信息保存失败。";
										sqlSession.rollback();
										}
									}else{
										str="会员加盟信息保存失败。";
										sqlSession.rollback();
									}
									}else{
										str="会员结算资料保存失败。";
										sqlSession.rollback();
									}
								}else{
									str="会员基本资料保存失败。";
									sqlSession.rollback();
								}
	    				}else{
			    			str="会员信息保存失败，获取会员ID失败。";
			    			sqlSession.rollback();
			    		}
	    				}//end nodeTagList
	    				}else{
			    			str="报单人空单资格已使用完毕。";
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
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		str ="系统繁忙中，请稍后再试。";
	    		sqlSession.rollback();
	    	}finally {
	    		sqlSession.close();
	        }
			return str;
		}
	    public String saveUser(User user,User userinfo,Order orders,Address adr,String[] pid,String[] numstr){
	    	String str = "";
	    	try{
	    		Param p2 = paramMapper.selectByName("报单金额");
	    			if(p2!=null){
	    				//double chuangye = 0;
	    				double emoney = 0;
						//Timestamp chuangyeValidtyTime=cs.getNextYearTime(user.getEndTime());
						if(user.getRankJoin()==1){
							emoney = p2.getAmount_1();
						}
						else if(user.getRankJoin()==2){
							emoney = p2.getAmount_2();
						}
						else if(user.getRankJoin()==3){
							emoney = p2.getAmount_3();
						}
						else if(user.getRankJoin()==4){
							emoney = p2.getAmount_4();
						}
						else if(user.getRankJoin()==5){
							emoney = p2.getAmount_5();
						}
					Center center = centerMapper.selectByCenterId(user.getUserIdByBelongCenter());
					if(center!=null){
						user.setUserIdByBelongCenter(center.getUserId());
						user.setIdByBelongCenter(center.getuId());
						orders.setUserByCenterId(center.getUserId());
						orders.setCenterId(center.getuId());
	    		User belongUser = null;
	    		User declarationUser = null;
	    		User refereeUser = userMapper.selectByUserIdForUpdate(user.getUserByRefereeId());
	    		if(user.getUserByBelongId().equals(refereeUser.getUserId())){
	    			belongUser = refereeUser;
	    		}else{
	    			belongUser = userMapper.selectByUserIdForUpdate(user.getUserByBelongId());
	    		}
	    		if(user.getUserByDeclarationId().equals(refereeUser.getUserId())){
	    			declarationUser = refereeUser;
	    		}else if(user.getUserByDeclarationId().equals(belongUser.getUserId())){
	    			declarationUser = belongUser;
	    		}else {
	    			declarationUser = userMapper.selectByUserIdForUpdate(user.getUserByDeclarationId());
	    		}
	    		User user1 = userMapper.selectByUserId(user.getUserId());
	    		if(user1==null){
	    		if(refereeUser!=null&&refereeUser.getState()>=2){
	    			if(belongUser!=null&&belongUser.getState()>=2){
	    				if(declarationUser!=null&&declarationUser.getState()>=2){
	    					User user_r = new User();
	    					User user_b = new User();
	    					User user_d = new User();
	    					user_r.setId(refereeUser.getId());
	    					user_b.setId(belongUser.getId());
	    					user_d.setId(declarationUser.getId());
	    				List<User> nodeList = userMapper.selectByNodeTag(belongUser.getUserId(), user.getNodeTag());
	    				if(nodeList!=null&&nodeList.size()>0){
	    					str = "销售商所在区域已经存在会员。";
	    					sqlSession.rollback();
	    				}else{
	    					
	    					//计算订单
							List<OrderDetail> olist = new ArrayList<OrderDetail>();
							double totalprice = 0;
							double totalpv = 0;
							int z_num = 0;
							for (int i = 0; i < pid.length; i++) {
								if (!(numstr.equals("") || numstr == null)) {
									if (Integer.valueOf(numstr[i]) > 0) {
									Product pd = productMapper.selectById(Integer.valueOf(pid[i]));
									if(pd!=null){
											OrderDetail od = new OrderDetail();
											od.setPid(pd.getId());
											od.setOrderId(orders.getOrderId());
											od.setNum(Integer.valueOf(numstr[i]));
											od.setProductId(pd.getProductId());
											od.setProductName(pd.getProductName());
											od.setProductPrice(pd.getPrice());
											od.setProductPv(pd.getPv());
											od.setProductType(pd.getProductType());
											od.setType(pd.getType());
											od.setSpecification(pd.getSpecification());
											od.setPrice(ArithUtil.mul(od.getProductPrice()
													, od.getNum()));
											od.setPv(ArithUtil.mul(od.getProductPv()
													, od.getNum()));
											olist.add(od);
											totalprice = ArithUtil.add(totalprice
													,od.getPrice());
											totalpv = ArithUtil.add(totalpv , od.getPv());
											if(pd.getProductId().equals("RYP0010")) z_num=od.getNum();
										}
									}
								}
							}
	    					
	    					if(ArithUtil.sub(totalprice, emoney)>=0){
	    						if(ArithUtil.sub(declarationUser.getEmoney(), totalprice)>=0){
	    							Promotion pro = proMapper.selectByName("护肤品促销(五月)");
	    							if(pro!=null){
	    								if(user.getEntryTime().getTime()-pro.getStartTime().getTime()>=0&&user.getEntryTime().getTime()-pro.getEndTime().getTime()<0){
		    							if(z_num>=2){
		    								int pnum = (int) Math.floor(ArithUtil.div(z_num, 2));
		    								Product p = productMapper.selectByProductId("zp0013");
		    								if(p!=null){
		    									OrderDetail od = new OrderDetail();
												od.setPid(p.getId());
												od.setOrderId(orders.getOrderId());
												od.setNum(pnum);
												od.setProductId(p.getProductId());
												od.setProductName(p.getProductName());
												od.setProductPrice((double) 0);
												od.setProductPv((double) 0);
												od.setProductType(p.getProductType());
												od.setType(p.getType());
												od.setSpecification(p.getSpecification());
												od.setPrice(ArithUtil.mul(od.getProductPrice()
														, od.getNum()));
												od.setPv(ArithUtil.mul(od.getProductPv()
														, od.getNum()));
												olist.add(od);
		    								}
		    							}
	    								}
	    							}
	    							
	    							pro = proMapper.selectByName("母亲节促销");
	    							if(pro!=null){
	    								if(user.getEntryTime().getTime()-pro.getStartTime().getTime()>=0&&user.getEntryTime().getTime()-pro.getEndTime().getTime()<0){
		    							if(totalprice>=10000){
		    								Product p = productMapper.selectByProductId("zp0011");
		    								if(p!=null){
		    									OrderDetail od = new OrderDetail();
												od.setPid(p.getId());
												od.setOrderId(orders.getOrderId());
												od.setNum(1);
												od.setProductId(p.getProductId());
												od.setProductName(p.getProductName());
												od.setProductPrice((double) 0);
												od.setProductPv((double) 0);
												od.setProductType(p.getProductType());
												od.setType(p.getType());
												od.setSpecification(p.getSpecification());
												od.setPrice(ArithUtil.mul(od.getProductPrice()
														, od.getNum()));
												od.setPv(ArithUtil.mul(od.getProductPv()
														, od.getNum()));
												olist.add(od);
		    								}
		    							}else if(totalprice>=5000){
		    								Product p = productMapper.selectByProductId("zp0002");
		    								if(p!=null){
		    									OrderDetail od = new OrderDetail();
												od.setPid(p.getId());
												od.setOrderId(orders.getOrderId());
												od.setNum(1);
												od.setProductId(p.getProductId());
												od.setProductName(p.getProductName());
												od.setProductPrice((double) 0);
												od.setProductPv((double) 0);
												od.setProductType(p.getProductType());
												od.setType(p.getType());
												od.setSpecification(p.getSpecification());
												od.setPrice(ArithUtil.mul(od.getProductPrice()
														, od.getNum()));
												od.setPv(ArithUtil.mul(od.getProductPv()
														, od.getNum()));
												olist.add(od);
		    								}
		    							}else if(totalprice>=1500){
		    								Product p = productMapper.selectByProductId("zp0008");
		    								if(p!=null){
		    									OrderDetail od = new OrderDetail();
												od.setPid(p.getId());
												od.setOrderId(orders.getOrderId());
												od.setNum(1);
												od.setProductId(p.getProductId());
												od.setProductName(p.getProductName());
												od.setProductPrice((double) 0);
												od.setProductPv((double) 0);
												od.setProductType(p.getProductType());
												od.setType(p.getType());
												od.setSpecification(p.getSpecification());
												od.setPrice(ArithUtil.mul(od.getProductPrice()
														, od.getNum()));
												od.setPv(ArithUtil.mul(od.getProductPv()
														, od.getNum()));
												olist.add(od);
		    								}
		    							}else if(totalprice>=1000){
		    								Product p = productMapper.selectByProductId("zp0007");
		    								if(p!=null){
		    									OrderDetail od = new OrderDetail();
												od.setPid(p.getId());
												od.setOrderId(orders.getOrderId());
												od.setNum(1);
												od.setProductId(p.getProductId());
												od.setProductName(p.getProductName());
												od.setProductPrice((double) 0);
												od.setProductPv((double) 0);
												od.setProductType(p.getProductType());
												od.setType(p.getType());
												od.setSpecification(p.getSpecification());
												od.setPrice(ArithUtil.mul(od.getProductPrice()
														, od.getNum()));
												od.setPv(ArithUtil.mul(od.getProductPv()
														, od.getNum()));
												olist.add(od);
		    								}
		    								p = productMapper.selectByProductId("zp0006");
		    								if(p!=null){
		    									OrderDetail od = new OrderDetail();
												od.setPid(p.getId());
												od.setOrderId(orders.getOrderId());
												od.setNum(1);
												od.setProductId(p.getProductId());
												od.setProductName(p.getProductName());
												od.setProductPrice((double) 0);
												od.setProductPv((double) 0);
												od.setProductType(p.getProductType());
												od.setType(p.getType());
												od.setSpecification(p.getSpecification());
												od.setPrice(ArithUtil.mul(od.getProductPrice()
														, od.getNum()));
												od.setPv(ArithUtil.mul(od.getProductPv()
														, od.getNum()));
												olist.add(od);
		    								}
		    							}else if(totalprice>=500){
		    								Product p = productMapper.selectByProductId("zp0007");
		    								if(p!=null){
		    									OrderDetail od = new OrderDetail();
												od.setPid(p.getId());
												od.setOrderId(orders.getOrderId());
												od.setNum(2);
												od.setProductId(p.getProductId());
												od.setProductName(p.getProductName());
												od.setProductPrice((double) 0);
												od.setProductPv((double) 0);
												od.setProductType(p.getProductType());
												od.setType(p.getType());
												od.setSpecification(p.getSpecification());
												od.setPrice(ArithUtil.mul(od.getProductPrice()
														, od.getNum()));
												od.setPv(ArithUtil.mul(od.getProductPv()
														, od.getNum()));
												olist.add(od);
		    								}
		    							}
		    							
	    								}
	    							}
	    							
	    							pro = proMapper.selectByName("5.20活动促销");
	    							if(pro!=null){
	    								if(user.getEntryTime().getTime()-pro.getStartTime().getTime()>=0&&user.getEntryTime().getTime()-pro.getEndTime().getTime()<0){
		    							if(totalprice>=520){
		    								Product p = productMapper.selectByProductId("zp0012");
		    								if(p!=null){
		    									OrderDetail od = new OrderDetail();
												od.setPid(p.getId());
												od.setOrderId(orders.getOrderId());
												od.setNum(1);
												od.setProductId(p.getProductId());
												od.setProductName(p.getProductName());
												od.setProductPrice((double) 0);
												od.setProductPv((double) 0);
												od.setProductType(p.getProductType());
												od.setType(p.getType());
												od.setSpecification(p.getSpecification());
												od.setPrice(ArithUtil.mul(od.getProductPrice()
														, od.getNum()));
												od.setPv(ArithUtil.mul(od.getProductPv()
														, od.getNum()));
												olist.add(od);
		    								}
		    								p = productMapper.selectByProductId("zp0006");
		    								if(p!=null){
		    									OrderDetail od = new OrderDetail();
												od.setPid(p.getId());
												od.setOrderId(orders.getOrderId());
												od.setNum(1);
												od.setProductId(p.getProductId());
												od.setProductName(p.getProductName());
												od.setProductPrice((double) 0);
												od.setProductPv((double) 0);
												od.setProductType(p.getProductType());
												od.setType(p.getType());
												od.setSpecification(p.getSpecification());
												od.setPrice(ArithUtil.mul(od.getProductPrice()
														, od.getNum()));
												od.setPv(ArithUtil.mul(od.getProductPv()
														, od.getNum()));
												olist.add(od);
		    								}
		    							}
	    								}
	    							}
												
	    							orders.setPrice(totalprice);
	    							orders.setPv(totalpv);
	    							AccountDetail ad  = new AccountDetail();
	    							ad.setUid(declarationUser.getId());
	    				    		ad.setUserId(declarationUser.getUserId());
	    				    		ad.setUserName(declarationUser.getUserName());
	    				    		ad.setAmount(totalprice);
	    				    		ad.setBalance(ArithUtil.sub(declarationUser.getEmoney(), totalprice));
	    				    		ad.setTradeType("报单购物");
	    				    		ad.setSummary(user.getUserId()+"加盟注册");
	    				    		ad.setPayType(2);
	    				    		ad.setEntryTime(user.getEntryTime());
	    				    		user_d.setEmoney(ad.getBalance());
	    				    		Map<String,Object> params = new HashMap<>();
	    			    			params.put("account",ad);
	    			    			params.put("tableName", Constants.EMONEYDETAIL_TABLE);
	    			    	if(userMapper.updateUser(user_d)>0){
	    			    		if(moneyMapper.save(params)>0){
	    			    			
							if (user.getNodeTag() == 1){
								belongUser.setUserByAId(user.getUserId());
								user_b.setUserByAId(user.getUserId());
							}else if (user.getNodeTag() == 2){
								belongUser.setUserByBId(user.getUserId());
								user_b.setUserByBId(user.getUserId());
	    			    	}else if (user.getNodeTag() == 3){
								belongUser.setUserByCId(user.getUserId());
								user_b.setUserByCId(user.getUserId());
							}
							user.setRefereeId(refereeUser.getId());
							user.setBelongId(belongUser.getId());
							user.setDeclarationId(declarationUser.getId());
							user.setNode(getNode(belongUser));
							user.setRefereeNode(getRefereeNode(refereeUser));
							user.setDeclarationNode(getDeclarationNode(declarationUser));
							user.setNodeABC(getNodeABC(belongUser, user.getNodeTag()));
							userMapper.saveUser(user);
							if(user.getId()!=null){
								int new_id = user.getId();
								orders.setuId(new_id);
								params.put("list",olist);
								if(userMapper.saveUserInfo(userinfo)>0){
									if(orderMapper.save(orders)>0){
		    			    			if(orderDetailMapper.insertAll(params)>0){
									WSettle ws = new WSettle();
									ws.setuId(new_id);
									ws.setUserId(user.getUserId());
									ws.setUserName(user.getUserName());
									ws.setEntryTime(user.getEntryTime());
									ws.setState(2);
									if(wsMapper.save(ws)>0){
										JoinInfo jf = new JoinInfo();
										jf.setUid(new_id);
										jf.setPrice(totalprice);
										jf.setPv(totalpv);
										jf.setDeclarationId(declarationUser.getId());
										jf.setRid(refereeUser.getId());
										jf.setUserId(user.getUserId());
										jf.setUserName(user.getUserName());
										jf.setEntryTime(user.getEntryTime());
										jf.setNewRankJoin(user.getRankJoin());
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
										user_r.setRefereeAll(refereeAll);
										if(userMapper.updateUser(user_r)>0){
											if(userMapper.updateUser(user_b)>0){
												if(adrMapper.save(adr)>0){
												sqlSession.commit();
												str="yes";
												user_r=null;
												user_d=null;
												user_b=null;
												}else{
													str="收获地址信息保存失败。";
												sqlSession.rollback();
												}
											}else{
												str="销售商信息保存失败。";
											sqlSession.rollback();
											}
										}else{
											str="服务商信息保存失败。";
										sqlSession.rollback();
										}
									}else{
										str="会员加盟信息保存失败。";
										sqlSession.rollback();
									}
									}else{
										str="会员结算资料保存失败。";
										sqlSession.rollback();
									}
								}else{
									str="订单明细保存失败。";
									sqlSession.rollback();
								}
    			    			}else{
									str="订单信息保存失败。";
									sqlSession.rollback();
								}
								}else{
									str="会员基本资料保存失败。";
									sqlSession.rollback();
								}
	    				}else{
			    			str="会员信息保存失败，获取会员ID失败。";
			    			sqlSession.rollback();
			    		}
	    			    	}else{
				    			str="报单人账户明细保存失败。";
				    			sqlSession.rollback();
				    		}
	    						}else{
	    			    			str="报单人报单券扣减失败。";
	    			    			sqlSession.rollback();
	    			    		}
	    					}else{
    			    			str="报单人报单券账户余额不足。";
    			    			sqlSession.rollback();
    			    		}
	    				}else{
			    			str="所选购产品未达到等级标准。";
			    			sqlSession.rollback();
			    		}	
	    				}//end nodeTagList
	    				}else{
			    			str="报单人处于未激活状态，不能进行操作。";
			    			sqlSession.rollback();
			    		}
	    			}else{
		    			str="销售商处于未激活状态，不能进行操作。";
		    			sqlSession.rollback();
		    		}
	    		}else{
	    			str="服务商处于未激活状态，不能进行操作。";
	    			sqlSession.rollback();
	    		}
	    		}else{
	    			str="该会员编号已注册。";
	    			sqlSession.rollback();
	    		}
					}else{
		    			str="服务中心信息获取失败，请填写正确的服务中心编号。";
		    			sqlSession.rollback();
		    		}
	    	}else{
    			str="报单金额参数获取失败。";
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
	    
	    
		public  String getRefereeNode(User user) {
			String node = "";
			if (StringUtil.notNull(user.getRefereeNode()).equals(""))
				node = String.valueOf(user.getId());
			else
				node = user.getRefereeNode() + "," + String.valueOf(user.getId());
			return node;
		}

		public  String getDeclarationNode(User user) {
			String node = "";
			if (StringUtil.notNull(user.getDeclarationNode()).equals(""))
				node = String.valueOf(user.getId());
			else
				node = user.getDeclarationNode() + ","
						+ String.valueOf(user.getId());
			return node;
		}

		public  String getNode(User user) {
			String node = "";
			if (StringUtil.notNull(user.getNode()).equals(""))
				node = String.valueOf(user.getId());
			else
				node = user.getNode() + "," + String.valueOf(user.getId());
			return node;
		}

		public String getNodeABC(User user, int nodeTag) {
			String node = "";
			String nodeStr = "";
			if (nodeTag == 1)
				nodeStr = "A";
			else if (nodeTag == 2)
				nodeStr = "B";
			else if (nodeTag == 3)
				nodeStr = "C";
			if (StringUtil.notNull(user.getNodeABC()).equals(""))
				node = nodeStr;
			else
				node = user.getNodeABC() + "," + nodeStr;
			return node;
		}
		
		 public int updateUsers(User user){
			 int num =0;
			 try{
				 num = userMapper.updateUser(user);
				 sqlSession.commit();
			 }catch(Exception e){
				 sqlSession.rollback();
			 }finally{
				 sqlSession.close();
			 }
			 return num;
			 
		 }
		 
		 public int updateUserInfo(User user){
			 int num =0;
			 try{
				 num = userMapper.updateUserInfo(user);
				 sqlSession.commit();
			 }catch(Exception e){
				 sqlSession.rollback();
			 }finally{
				 sqlSession.close();
			 }
			 return num;
			 
		 }
		 
		 public int updatePsw(String userId,String password,int type){
			 int num =0;
			 try{
				 String pre = "";
				 if(type==1) pre ="password";
				 else  if(type==2) pre ="password2";
				 num = userMapper.updateUserInfoForProperty(pre, password, "userId", userId);
				 if(num>0)
				 sqlSession.commit();
			 }catch(Exception e){
				 sqlSession.rollback();
			 }finally{
				 sqlSession.close();
			 }
			 return num;
			 
		 }
		 
		 public String updateUser(User user,User userinfo){
			 String str ="";
			 try{
				 User user1 = userMapper.selectByUserId(user.getUserId());
				 User user2 = userMapper.selectUserInfoByUserId(user.getUserId());
				 userinfo.setId(user2.getId());
				 user.setId(user1.getId());
				if(userMapper.updateUserInfo(userinfo)>0){
					if(userMapper.updateUser(user)>0){
						 if(!user1.getUserName().equals(user.getUserName()))
							 wsMapper.updateUserNameOfWst(user.getUserName(), user.getUserId());
						 str= "yes";
						 sqlSession.commit();
					}else{
						 str="会员信息更新失败。";
						sqlSession.rollback();
					}
				}else{
					
					 str="会员基本信息更新失败。";
					 sqlSession.rollback();
				}
				 
			 }catch(Exception e){
				 str="系统繁忙，请稍后再试。";
				 sqlSession.rollback();
				 e.printStackTrace();
			 }finally{
				 sqlSession.close();
			 }
			 return str;
			 
		 }
		
		 public String updateUserId(String id,String userId){
		    	String str="";
		    	try{
				 User user1 = userMapper.selectByUserId(id);
				 if(user1!=null){
				 User user_b = new User();
				 user_b.setId(user1.getBelongId());
				 int nodeTag  = user1.getNodeTag();
				if(nodeTag==1)  user_b.setUserByAId(userId);
				else if(nodeTag==2) user_b.setUserByBId(userId);
				if(userMapper.updateUser(user_b)>0){
					if(userMapper.updateUserIdOfUsers(userId, id)>0){
						if(userMapper.updateUserIdOfUserInfo(userId, id)>0){
							if(wsMapper.updateUserIdOfWst(userId, id)>0){
								if(joinInfoMapper.updateUserIdOfJoinInfo(userId, id)>0){
									if(moneyMapper.updateEmoneyForUserId(id,userId,id)>0){
										if(orderMapper.updateUserIdOfOrder(userId,id)>0){
											if(adrMapper.updateUserIdOdr(userId, id)>0){
											str="yes";
											 sqlSession.commit();
											}else{
												str = "地址信息更新更新失败";
												sqlSession.rollback();
											}
										}else{
											str = "订单信息更新更新失败";
											sqlSession.rollback();
										}
									}else{
										str = "报单券账户更新失败";
										sqlSession.rollback();
									}
								}else{
									str = "加盟信息更新失败";
									sqlSession.rollback();
								}
								}else{
									str = "结算信息更新失败";
									sqlSession.rollback();
								}
							}else{
								str = "会员资料更新失败";
								sqlSession.rollback();
							}
						}else{
							str = "会员信息更新失败";
							sqlSession.rollback();
						}
					}else{
						str = "销售商信息更新失败";
						sqlSession.rollback();
					}
				 }else{
						str = "会员信息获取失败";
						sqlSession.rollback();
					}
				
		    	}catch(Exception e){
		    		str="会员编号更新失败。";
		    		e.printStackTrace();
		    		sqlSession.rollback();
		    	}finally {
		    		sqlSession.close();
		        }
				return str;
			}

		 public String user_back(User user){
		 String str ="";
		 int error= 0;
		 try{
	    		Timestamp date = new Timestamp(new Date().getTime());
			 List<JoinInfo> jlist = joinInfoMapper.selectByUserId(user.getUserId());
			 if(jlist!=null&&jlist.size()==1){
				 double emoney = 0;
				 System.out.println(jlist.size());
				 for(int i=0;i<jlist.size();i++){
					 emoney = ArithUtil.add(emoney, jlist.get(i).getPrice());
					if( joinInfoMapper.updateForState(0, jlist.get(i).getId())==0)
						error++;
				 }
				 if(error==0){
					 if(emoney>0){
						 User declarationUser = userMapper.selectMoneyByUserIdForUpdate(user.getUserByDeclarationId());
						 if(declarationUser!=null){
							 List<Order> orders = orderMapper.selectByParam(user.getUserId(), 1, 1);
							 
							 if(orders!=null&&orders.size()==1){
								 User user_d = new User();
								 user_d.setId(declarationUser.getId());
								 AccountDetail ad  = new AccountDetail();
								 ad.setUid(declarationUser.getId());
	    				    		ad.setUserId(declarationUser.getUserId());
	    				    		ad.setUserName(declarationUser.getUserName());
	    				    		ad.setAmount(emoney);
	    				    		ad.setBalance(ArithUtil.add(declarationUser.getEmoney(), emoney));
	    				    		ad.setTradeType("会员退单");
	    				    		ad.setSummary(user.getUserId()+"退单");
	    				    		ad.setPayType(1);
	    				    		ad.setEntryTime(date);
								 user_d.setEmoney(ad.getBalance());
								 Map<String,Object> params = new HashMap<>();
	    			    			params.put("account",ad);
	    			    			params.put("tableName", Constants.EMONEYDETAIL_TABLE);
								 if(userMapper.updateUser(user_d)>0){
									 if(moneyMapper.save(params)>0){
										 for(int i=0;i<orders.size();i++){
											if( orderMapper.updateForState(0, orders.get(i).getId())==0)
												error++;
												
										 }
										 if(error>0){
											 str="会员订单删除失败。";
											 sqlSession.rollback();
										 }
									 }else{
										 error++;
										 str="报单人资金明细保存失败。";
										 sqlSession.rollback();
									 }
								 }else{
									 error++;
									 str="报单人资金退回失败。";
									 sqlSession.rollback();
								 } 
							 }else{
								 error++;
								 str="该会员报单订单不存在或者是订单状态已处于发货过程中。";
								 sqlSession.rollback();
							 }
						 }else{
							 error++;
							 str = "无法获取报单人的信息。";
							 sqlSession.rollback();
						 }	
					 }
					//通用模块 
					 if(error==0){
						 WSettle wst = wsMapper.selectByUserId(user.getUserId());
							if(wst!=null){
								 if(wsMapper.updateUsersForState(0, wst.getId())>0){
									 String property = "";
									 if(user.getNodeTag()==1) property="userByAId";
									 if(user.getNodeTag()==2) property="userByBId";
									 if(userMapper.updateUsersForProperty(property, "", user.getBelongId(), property, user.getUserId())>0){
										User refereeUser = userMapper.selectByUserId(user.getUserByRefereeId());
										String[] strr = StringUtil.notNull(refereeUser.getRefereeAll()).split(",");
										List<String> ulist = new ArrayList<String>();
										for(int i=0;i<strr.length;i++){
											if(!strr[i].equals("")){
												if(!strr[i].equals(String.valueOf(user.getId())))
												ulist.add(strr[i]);
											}
										}
										String refereeAll = "";
										for(int i=0;i<ulist.size();i++){
											if(i==0) refereeAll = ulist.get(i);
											else refereeAll = refereeAll + ","+ulist.get(i);
										}
										property = "refereeAll";
										 if(userMapper.updateUsersForProperty(property, "",refereeUser.getId(), property, refereeUser.getRefereeAll())>0){
											 if(userMapper.updateUserInfoForState(0, user.getUserId())>0){
												 if(userMapper.updateUsersForState(0, user.getUserId())>0){
														
											 sqlSession.commit();
											 str="yes";
											 }else{
												 str="会员信息删除失败。";
												 sqlSession.rollback();
											 }
											 }else{
												 str="会员资料删除失败。";
												 sqlSession.rollback();
											 }
												 
										 }else{
											 str="服务商信息更新失败。";
											 sqlSession.rollback();
										 }
									 }else{
										 str="更新销售商的信息失败。";
										 sqlSession.rollback();
									 }
												 
									 }else{
										 str="会员结算表退单失败。";
										 sqlSession.rollback();
									 }
							 }else{
								 str= "会员结算信息表获取失败.";
								 sqlSession.rollback();
							 }
					 }
				//通用结束
					 
				 }else{
					 str= "会员加盟信息删除失败.";
					 sqlSession.rollback();
				 }
			 }else{
				 str= "会员加盟信息不存在或者还存在其它升级信息.";
				 sqlSession.rollback();
			 }
		 }catch(Exception e){
	    		str="系统繁忙中，请稍后再试。";
	    		e.printStackTrace();
	    		sqlSession.rollback();
	    	}finally {
	    		sqlSession.close();
	        }
		 return str;
		 }
		 
		 public String user_rankJoin(String userId,Integer rankJoin,Timestamp date){
			 String str ="";
			 try{
		    			User user = userMapper.selectByUserId(userId);
		    			if(user!=null){
		    				User user1= new User();
		    				user1.setId(user.getId());
		    				if(rankJoin>user.getRankJoin()){
		    				user1.setRankJoin(rankJoin);
		    				JoinInfo joinInfo = new JoinInfo();
		    				joinInfo.setUid(user.getId());
		    				joinInfo.setUserId(user.getUserId());
		    				joinInfo.setUserName(user.getUserName());
		    				joinInfo.setState(user.getState());
		    				joinInfo.setOldRankJoin(user.getRankJoin());
		    				joinInfo.setNewRankJoin(rankJoin);
		    				joinInfo.setRid(user.getRefereeId());
		    				joinInfo.setTag(0);
		    				joinInfo.setEntryTime(date);
		    				if(userMapper.updateUser(user1)>0){
		    					if(joinInfoMapper.save(joinInfo)>0){
		    						sqlSession.commit();
		    						str="yes";
		    					}else{
		    						str="会员加盟信息保存失败。";
		    					}
		    				}else{
		    					str="会员等级信息变更失败。";
		    				}
		    				}else{
				    			str="当前会员等级不符合要求变更为新的等级，请核对。";
				    		}
		    			}else{
		    				str ="会员信息获取失败。";
		    			}

			 }catch(Exception e){
		    		str="系统繁忙中，请稍后再试。";
		    		e.printStackTrace();
		    		sqlSession.rollback();
		    	}finally {
		    		sqlSession.close();
		        }
			 return str;
			 }
		 
		
		 
		 public int updatePsw(String[] ids,String password,Integer type){
			 int num=0;
			 try{
				 for(int i=0;i<ids.length;i++){
					 String property="";
					 if(type==1)
						 property="password";
					 else  if(type==2)
						 property="password2";
					 User user1 = userMapper.selectById(Integer.valueOf(ids[i]));
					 if(user1!=null){
						 if(userMapper.updateUserInfoForProperty(property, password, "userId", user1.getUserId())!=null) num++;
					 }
				 }
				 if(num-ids.length==0) {
					 
				 }else{
					 num = 0;
					 sqlSession.rollback();
				 }
				 sqlSession.commit();
			 }catch(Exception e){
					num=0;
		    		e.printStackTrace();
		    		sqlSession.rollback();
			 }finally{
				 
			 }
			 return num;
		 }
		 
		 public String saveRankJoinUp(User user,User user1,Order orders,JoinInfo jf,String[] pid, String[] numstr){
			 String str ="";
			 try{
		    	
		        		user = userMapper.selectByUserIdForUpdate(user.getUserId());
		    			if(user!=null){
		    				Center center = centerMapper.selectByCenterId(orders.getUserByCenterId());
		    				if(center!=null){
		    					orders.setUserByCenterId(center.getUserId());
		    					orders.setCenterId(center.getuId());
		    				List<OrderDetail> olist = new ArrayList<OrderDetail>();
		    				double totalprice = 0;
		    				double totalpv = 0;
		    				int z_num = 0;
		    				for (int i = 0; i < pid.length; i++) {
		    					if (!(numstr.equals("") || numstr == null)) {
		    						if (Integer.valueOf(numstr[i]) > 0) {
		    						Product pd = productMapper.selectById(Integer.valueOf(pid[i]));
		    						if(pd!=null){
		    								OrderDetail od = new OrderDetail();
		    								od.setPid(pd.getId());
		    								od.setOrderId(orders.getOrderId());
		    								od.setNum(Integer.valueOf(numstr[i]));
		    								od.setProductId(pd.getProductId());
		    								od.setProductName(pd.getProductName());
		    								od.setProductPrice(pd.getPrice());
		    								od.setProductPv(pd.getPv());
		    								od.setProductType(pd.getProductType());
		    								od.setType(pd.getType());
		    								od.setSpecification(pd.getSpecification());
		    								od.setPrice(ArithUtil.mul(od.getProductPrice()
		    										, od.getNum()));
		    								od.setPv(ArithUtil.mul(od.getProductPv()
		    										, od.getNum()));
		    								olist.add(od);
		    								totalprice = ArithUtil.add(totalprice
		    										,od.getPrice());
		    								totalpv = ArithUtil.add(totalpv , od.getPv());
		    								if(pd.getProductId().equals("RYP0010")) z_num=od.getNum();
		    							}
		    						}
		    					}
		    				}
		    				orders.setPrice(totalprice);
		    				orders.setPv(totalpv);
		    				if(ArithUtil.sub(user.getEmoney(), totalprice)>=0){
		    					Promotion pro = proMapper.selectByName("护肤品促销(五月)");
    							if(pro!=null){
    								if(jf.getEntryTime().getTime()-pro.getStartTime().getTime()>=0&&jf.getEntryTime().getTime()-pro.getEndTime().getTime()<0){
	    							if(z_num>=2){
	    								int pnum = (int) Math.floor(ArithUtil.div(z_num, 2));
	    								Product p = productMapper.selectByProductId("zp0013");
	    								if(p!=null){
	    									OrderDetail od = new OrderDetail();
											od.setPid(p.getId());
											od.setOrderId(orders.getOrderId());
											od.setNum(pnum);
											od.setProductId(p.getProductId());
											od.setProductName(p.getProductName());
											od.setProductPrice((double) 0);
											od.setProductPv((double) 0);
											od.setProductType(p.getProductType());
											od.setType(p.getType());
											od.setSpecification(p.getSpecification());
											od.setPrice(ArithUtil.mul(od.getProductPrice()
													, od.getNum()));
											od.setPv(ArithUtil.mul(od.getProductPv()
													, od.getNum()));
											olist.add(od);
	    								}
	    							}
    								}
    							}
    							
    							pro = proMapper.selectByName("母亲节促销");
    							if(pro!=null){
    								if(jf.getEntryTime().getTime()-pro.getStartTime().getTime()>=0&&jf.getEntryTime().getTime()-pro.getEndTime().getTime()<0){
	    							if(totalprice>=10000){
	    								Product p = productMapper.selectByProductId("zp0011");
	    								if(p!=null){
	    									OrderDetail od = new OrderDetail();
											od.setPid(p.getId());
											od.setOrderId(orders.getOrderId());
											od.setNum(1);
											od.setProductId(p.getProductId());
											od.setProductName(p.getProductName());
											od.setProductPrice((double) 0);
											od.setProductPv((double) 0);
											od.setProductType(p.getProductType());
											od.setType(p.getType());
											od.setSpecification(p.getSpecification());
											od.setPrice(ArithUtil.mul(od.getProductPrice()
													, od.getNum()));
											od.setPv(ArithUtil.mul(od.getProductPv()
													, od.getNum()));
											olist.add(od);
	    								}
	    							}else if(totalprice>=5000){
	    								Product p = productMapper.selectByProductId("zp0002");
	    								if(p!=null){
	    									OrderDetail od = new OrderDetail();
											od.setPid(p.getId());
											od.setOrderId(orders.getOrderId());
											od.setNum(1);
											od.setProductId(p.getProductId());
											od.setProductName(p.getProductName());
											od.setProductPrice((double) 0);
											od.setProductPv((double) 0);
											od.setProductType(p.getProductType());
											od.setType(p.getType());
											od.setSpecification(p.getSpecification());
											od.setPrice(ArithUtil.mul(od.getProductPrice()
													, od.getNum()));
											od.setPv(ArithUtil.mul(od.getProductPv()
													, od.getNum()));
											olist.add(od);
	    								}
	    							}else if(totalprice>=1500){
	    								Product p = productMapper.selectByProductId("zp0008");
	    								if(p!=null){
	    									OrderDetail od = new OrderDetail();
											od.setPid(p.getId());
											od.setOrderId(orders.getOrderId());
											od.setNum(1);
											od.setProductId(p.getProductId());
											od.setProductName(p.getProductName());
											od.setProductPrice((double) 0);
											od.setProductPv((double) 0);
											od.setProductType(p.getProductType());
											od.setType(p.getType());
											od.setSpecification(p.getSpecification());
											od.setPrice(ArithUtil.mul(od.getProductPrice()
													, od.getNum()));
											od.setPv(ArithUtil.mul(od.getProductPv()
													, od.getNum()));
											olist.add(od);
	    								}
	    							}else if(totalprice>=1000){
	    								Product p = productMapper.selectByProductId("zp0007");
	    								if(p!=null){
	    									OrderDetail od = new OrderDetail();
											od.setPid(p.getId());
											od.setOrderId(orders.getOrderId());
											od.setNum(1);
											od.setProductId(p.getProductId());
											od.setProductName(p.getProductName());
											od.setProductPrice((double) 0);
											od.setProductPv((double) 0);
											od.setProductType(p.getProductType());
											od.setType(p.getType());
											od.setSpecification(p.getSpecification());
											od.setPrice(ArithUtil.mul(od.getProductPrice()
													, od.getNum()));
											od.setPv(ArithUtil.mul(od.getProductPv()
													, od.getNum()));
											olist.add(od);
	    								}
	    								p = productMapper.selectByProductId("zp0006");
	    								if(p!=null){
	    									OrderDetail od = new OrderDetail();
											od.setPid(p.getId());
											od.setOrderId(orders.getOrderId());
											od.setNum(1);
											od.setProductId(p.getProductId());
											od.setProductName(p.getProductName());
											od.setProductPrice((double) 0);
											od.setProductPv((double) 0);
											od.setProductType(p.getProductType());
											od.setType(p.getType());
											od.setSpecification(p.getSpecification());
											od.setPrice(ArithUtil.mul(od.getProductPrice()
													, od.getNum()));
											od.setPv(ArithUtil.mul(od.getProductPv()
													, od.getNum()));
											olist.add(od);
	    								}
	    							}else if(totalprice>=500){
	    								Product p = productMapper.selectByProductId("zp0007");
	    								if(p!=null){
	    									OrderDetail od = new OrderDetail();
											od.setPid(p.getId());
											od.setOrderId(orders.getOrderId());
											od.setNum(2);
											od.setProductId(p.getProductId());
											od.setProductName(p.getProductName());
											od.setProductPrice((double) 0);
											od.setProductPv((double) 0);
											od.setProductType(p.getProductType());
											od.setType(p.getType());
											od.setSpecification(p.getSpecification());
											od.setPrice(ArithUtil.mul(od.getProductPrice()
													, od.getNum()));
											od.setPv(ArithUtil.mul(od.getProductPv()
													, od.getNum()));
											olist.add(od);
	    								}
	    							}
	    							
    								}
    							}
    							
    							pro = proMapper.selectByName("5.20活动促销");
    							if(pro!=null){
    								if(jf.getEntryTime().getTime()-pro.getStartTime().getTime()>=0&&jf.getEntryTime().getTime()-pro.getEndTime().getTime()<0){
	    							if(totalprice>=520){
	    								Product p = productMapper.selectByProductId("zp0012");
	    								if(p!=null){
	    									OrderDetail od = new OrderDetail();
											od.setPid(p.getId());
											od.setOrderId(orders.getOrderId());
											od.setNum(1);
											od.setProductId(p.getProductId());
											od.setProductName(p.getProductName());
											od.setProductPrice((double) 0);
											od.setProductPv((double) 0);
											od.setProductType(p.getProductType());
											od.setType(p.getType());
											od.setSpecification(p.getSpecification());
											od.setPrice(ArithUtil.mul(od.getProductPrice()
													, od.getNum()));
											od.setPv(ArithUtil.mul(od.getProductPv()
													, od.getNum()));
											olist.add(od);
	    								}
	    								p = productMapper.selectByProductId("zp0006");
	    								if(p!=null){
	    									OrderDetail od = new OrderDetail();
											od.setPid(p.getId());
											od.setOrderId(orders.getOrderId());
											od.setNum(1);
											od.setProductId(p.getProductId());
											od.setProductName(p.getProductName());
											od.setProductPrice((double) 0);
											od.setProductPv((double) 0);
											od.setProductType(p.getProductType());
											od.setType(p.getType());
											od.setSpecification(p.getSpecification());
											od.setPrice(ArithUtil.mul(od.getProductPrice()
													, od.getNum()));
											od.setPv(ArithUtil.mul(od.getProductPv()
													, od.getNum()));
											olist.add(od);
	    								}
	    							}
    								}
    							}
		    					jf.setPrice(totalprice);
		    					jf.setPv(totalpv);
		    					AccountDetail ad  = new AccountDetail();
		    					ad.setUid(user.getId());
		    		    		ad.setUserId(user.getUserId());
		    		    		ad.setUserName(user.getUserName());
		    		    		ad.setAmount(totalprice);
		    		    		ad.setBalance(ArithUtil.sub(user.getEmoney(), totalprice));
		    		    		ad.setTradeType("升级购物");
		    		    		ad.setSummary(user1.getUserId()+"升级购物");
		    		    		ad.setPayType(2);
		    		    		ad.setEntryTime(orders.getOrderTime());
		    		    		User user_d = new User();
		    		    		user_d.setId(user.getId());
		    		    		user_d.setEmoney(ad.getBalance());
		    		    		User user_2 = new User();
		    		    		user_2.setId(user1.getId());
		    		    		user_2.setRankJoin(user1.getRankJoin());
		    		    		Map<String,Object> params = new HashMap<>();
		    	    			params.put("account",ad);
		    	    			params.put("tableName", Constants.EMONEYDETAIL_TABLE);
		    	    			if(userMapper.updateUser(user_2)>0){
		    	    	if(userMapper.updateUser(user_d)>0){
		    	    		if(moneyMapper.save(params)>0){
		    	    			if(joinInfoMapper.save(jf)>0){
		    		    		if(orderMapper.save(orders)>0){
		    		    			params.put("list",olist);
		    		    			if(orderDetailMapper.insertAll(params)>0){
		    		    			str="yes";
		    		    			sqlSession.commit();
		    		    			}else{
		    			    			str="升级订单详情保存失败。";
		    			    			sqlSession.rollback();
		    			    		}
		    		    		}else{
		    		    			str="升级订单信息保存失败。";
		    		    			sqlSession.rollback();
		    		    		}
		    	    			}else{
			    		    		str="升级记录保存失败。";
			    		    		sqlSession.rollback();
			    				}
		    		    	}else{
		    		    		str="资金明细保存失败。";
		    		    		sqlSession.rollback();
		    				}
		    	    	}else{
		    	    		str="会员等级信息更新失败。";
		    	    		sqlSession.rollback();
		    			}
		    	    	}else{
		    	    		str="会员账户信息更新失败。";
		    	    		sqlSession.rollback();
		    			}
		    				}else{
		    		    		str="会员账户余额不足。";
		    		    		sqlSession.rollback();
		    				}
		    				  }else{
		  		    	    	str ="服务中心信息获取失败。";
		  		    	    }
		    	    }else{
		    	    	str ="会员信息获取失败。";
		    	    }
		    		
			 }catch(Exception e){
		    		str="系统繁忙中，请稍后再试。";
		    		e.printStackTrace();
		    		sqlSession.rollback();
		    	}finally {
		    		sqlSession.close();
		        }
			 return str;
			 }
		 
		 public String updateUsers(String[] ids,Integer isHide){
			 String str="";
			 int num=0;
			 try{
				 for(int i=0;i<ids.length;i++){
					 User user = new User();
					 user.setId(Integer.valueOf(ids[i]));
					 user.setIsHide(isHide);
					num= num+ userMapper.updateUser(user);
				 }
				 if(num-ids.length==0) str = "yes";
				 else{
					 str = "数据更新有误。";
					 sqlSession.rollback();
				 }
				 sqlSession.commit();
			 }catch(Exception e){
					str="系统繁忙中，请稍后再试。";
		    		e.printStackTrace();
		    		sqlSession.rollback();
			 }finally{
				 
			 }
			 return str;
		 }
		 
		 public String updateRefereeId(String userId,String refereeId,String adminName){
		    	String str = "";
		    	try{
		    		User updateUser = null;
		    		User refereeUserNew = null;
		    		User refereeUserOld = null;
		    		updateUser = userMapper.selectByUserIdForUpdate(userId);
		    		if(updateUser!=null){
		    			
		    			SettleDao stDao=new SettleDao();
		    			Settle st = stDao.findByWeekTag(0);
		    			if(st!=null){
		    				if(updateUser.getEntryTime().getTime()-st.getEndTime().getTime()>0){
				    			if(!updateUser.getUserByRefereeId().equals(refereeId)){
				    				refereeUserNew = userMapper.selectByUserIdForUpdate(refereeId);
		    		if(refereeUserNew!=null&&refereeUserNew.getState()>0&&refereeUserNew.getRankJoin()>0){
		    			if(refereeUserNew.getEntryTime().getTime()-updateUser.getEntryTime().getTime()<0){
		    			refereeUserOld = userMapper.selectByUserIdForUpdate(updateUser.getUserByRefereeId());
		    			if(refereeUserOld!=null&&refereeUserOld.getState()>0&&refereeUserOld.getRankJoin()>0){
		    				
		    			String refereeNode ="";
						if(StringUtil.notNull(refereeUserNew.getRefereeNode()).equals("")) refereeNode = String.valueOf(refereeUserNew.getId());
						else refereeNode =refereeUserNew.getRefereeNode()+","+String.valueOf(refereeUserNew.getId());
						
						String[] str1 = StringUtil.notNull(refereeUserOld.getRefereeAll()).split(",");
						List<String> ulist = new ArrayList<String>();
						for(int i=0;i<str1.length;i++){
							if(!str1[i].equals("")){
								if(!str1[i].equals(String.valueOf(updateUser.getId())))
								ulist.add(str1[i]);
							}
						}
						String refereeAllOld = "";
						String refereeAllNew = "";
						for(int i=0;i<ulist.size();i++){
							if(i==0) refereeAllOld = ulist.get(i);
							else refereeAllOld = refereeAllOld + ","+ulist.get(i);
						}
						if(StringUtil.notNull(refereeUserNew.getRefereeAll()).equals(""))
							refereeAllNew = String.valueOf(updateUser.getId());
						else refereeAllNew = refereeUserNew.getRefereeAll()+","+String.valueOf(updateUser.getId());
						
					
						User user_1 = new User();
						user_1.setId(updateUser.getId());
						user_1.setRefereeNode(refereeNode);
						user_1.setUserByRefereeId(refereeUserNew.getUserId());
						user_1.setRefereeId(refereeUserNew.getId());
						
						JoinInfo jf = new JoinInfo();
						jf.setUserId(updateUser.getUserId());
						jf.setRid(refereeUserNew.getId());
						Integer up1 =userMapper.updateUser(user_1);
						if(up1!=null && up1>0){
							Integer up2 = userMapper.updateUserForField("refereeAll", refereeAllNew, refereeUserNew.getUserId());
						
							if(up2!=null && up2>0){
								Integer up3 = userMapper.updateUserForField("refereeAll", refereeAllOld, refereeUserOld.getUserId());
								if(up3!=null && up3>0){
									Integer up4 =joinInfoMapper.updateRIdForUserId(refereeUserNew.getId(), updateUser.getUserId());
									if(up4!=null && up4>0){
										Map<String,Object> params = new HashMap<String,Object>();
										List<User> userlist = userMapper.selectUserByListForUpdate(params);
										
										Integer maxId = userMapper.maxId(params);
										if(maxId!=null&&maxId>0){
											User[] user = new User[maxId+1];
											for(int i=0;i<userlist.size();i++){
												int id= userlist.get(i).getId();
												if(user[id]==null) user[id] = new User();
												user[id].setId(id);
												user[id].setUserId(userlist.get(i).getUserId()); 
												user[id].setUserName(userlist.get(i).getUserName());
												user[id].setUserByRefereeId(StringUtil.notNull(userlist.get(i).getUserByRefereeId()));
												user[id].setRefereeId(userlist.get(i).getRefereeId());
												user[id].setRefereeNode("");
												user[id].setRefereeAll(userlist.get(i).getRefereeNode());
												if(user[id].getUserId().equals(updateUser.getUserId())){
													user[id].setUserByRefereeId(refereeUserNew.getUserId());
												}
												
											}
											String error="";
											 for(int i=1;i<user.length;i++){
												    if(user[i]!=null){
												    	if(!user[i].getUserId().equals(Constants.TOP_NODE)){
												    		int rid = user[i].getRefereeId();
																String node = "";
														    	if(StringUtil.notNull(user[rid].getRefereeNode()).equals("")) node = String.valueOf(rid);
														    	else node = user[rid].getRefereeNode()+","+String.valueOf(rid);
														    	user[i].setRefereeNode(node);
														    	User user_node = new User();
														    	user_node.setId(user[i].getId());
														    	user_node.setRefereeNode(user[i].getRefereeNode());
														    	if(!user[i].getRefereeNode().equals(user[i].getRefereeAll())){
															    	if(userMapper.updateUserForField("refereeNode", user[i].getRefereeNode(), user[i].getUserId())==null){
															    		error=user[i].getUserId()+"节点信息更新失败。<br><br>";
															    	}
														    	}
														 }
												    }
												}
										if(error.equals("")){
											str = userId+"会员服务商变更成功，原服务商为"+refereeUserOld.getUserName()+"("+refereeUserOld.getUserId()+")，最新服务商："+refereeUserNew.getUserName()+"("+refereeUserNew.getUserId()+")！";
											Timestamp date = new Timestamp(new Date().getTime());
											AdminLog log = new AdminLog();
											log.setAdminName(adminName);
											log.setContents(str);
											log.setEntryTime(date);
											log.setType(ConstantsLog.LOGTYPE_4);
											adminLogMapper.save(log);
											sqlSession.commit();
										}else{
											str= "所有会员节点更新失败。";
											sqlSession.rollback();
										}
										}else{
											str= "获取所有会员ID信息失败。";
											sqlSession.rollback();
										}
									}else{
										str= "等级信息更新失败。";
										sqlSession.rollback();
									}
									}else{
										str= "原服务商信息更新失败。";
										sqlSession.rollback();
									}
									
								}else{
									str= "新服务商信息更新失败。";
									sqlSession.rollback();
								}
							}else{
								str= "变更会员信息更新失败。";
								sqlSession.rollback();
							}
						}else{
							str= "原服务商信息获取失败。";
							sqlSession.rollback();
						}
		    			}else{
							str= "新服务商加盟时间必须比会员加盟时间早。";
							sqlSession.rollback();
						}
		    		}else{
						str= "新服务商信息获取失败。";
						sqlSession.rollback();
					}
		    		}else{
						str= "原服务商与新服务商不能一样。";
						sqlSession.rollback();
					}
		    				}else{
								str= "此会员已结算，不能进行变更。";
								sqlSession.rollback();
							}
		    			}else{
							str= "结算信息获取失败。";
							sqlSession.rollback();
						}
		    			}else{
							str= "变更会员信息获取失败。";
							sqlSession.rollback();
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
		 
		 public String updateBelongId(String userId,String belongId,Integer nodeTag, String adminName){
		    	String str = "";
		    	try{
		    		User updateUser = null;
		    		User belongUserNew = null;
		    		User belongUserOld = null;
		    		updateUser = userMapper.selectByUserIdForUpdate(userId);
		    		if(updateUser!=null){
		    			
		    			SettleDao stDao=new SettleDao();
		    			Settle st = stDao.findByWeekTag(0);
		    			if(st!=null){
		    				if(updateUser.getEntryTime().getTime()-st.getEndTime().getTime()>0){
				    			if(!updateUser.getUserByBelongId().equals(belongId)){
				    				belongUserNew = userMapper.selectByUserIdForUpdate(belongId);
		    		if(belongUserNew!=null&&belongUserNew.getState()>0&&belongUserNew.getRankJoin()>0){
		    			if(belongUserNew.getEntryTime().getTime()-updateUser.getEntryTime().getTime()<0){
		    				belongUserOld = userMapper.selectByUserIdForUpdate(updateUser.getUserByBelongId());
		    			if(belongUserOld!=null&&belongUserOld.getState()>0&&belongUserOld.getRankJoin()>0){
		    				String property ="";
							String property1 ="";
							if(updateUser.getNodeTag()==1) {
								property="userByAId";
							}
							else if(updateUser.getNodeTag()==2) {
								property="userByBId";
							}
							
							String belongNode ="";
							String belongNodeABC ="";
							String abc = "";
							boolean  b = false;
							if(nodeTag==1){
								abc="A";
								property1="userByAId";
								if(belongUserNew.getUserByAId()==null ||belongUserNew.getUserByAId().equals(""))
									b = true;
							}
							else if(nodeTag==2) {
								abc="B";
								property1="userByBId";
								if(belongUserNew.getUserByBId()==null ||belongUserNew.getUserByBId().equals(""))
									b = true;
							}
							if(b){
							if(StringUtil.notNull(belongUserNew.getNode()).equals("")) {
								belongNode = String.valueOf(belongUserNew.getId());
								
								belongNodeABC = abc;
							}else{
								belongNode = belongUserNew.getNode()+","+String.valueOf(belongUserNew.getId());
								belongNodeABC = belongUserNew.getNodeABC()+","+abc;
							}
							User user_1  = new User();
							user_1.setId(updateUser.getId());
							user_1.setNode(belongNode);
							user_1.setNodeABC(belongNodeABC);
							user_1.setNodeTag(nodeTag);
							user_1.setUserByBelongId(belongUserNew.getUserId());
							user_1.setBelongId(belongUserNew.getId());
		    			Integer up1 = userMapper.updateUser(user_1);
		    			if(up1!=null &&up1>0){
							Integer up2 = userMapper.updateUserForField(property, "", belongUserOld.getUserId());
						
							if(up2!=null && up2>0){
								Integer up3 = userMapper.updateUserForField(property1, updateUser.getUserId(), belongUserNew.getUserId());
								if(up3!=null && up3>0){
									
										Map<String,Object> params = new HashMap<String,Object>();
										List<User> userlist = userMapper.selectUserByListForUpdate(params);
										Integer maxId = userMapper.maxId(params);
										if(maxId!=null&&maxId>0){
											User[] user = new User[maxId+1];
											for(int i=0;i<userlist.size();i++){
												int id= userlist.get(i).getId();
												if(user[id]==null) user[id] = new User();
												user[id].setId(id);
												user[id].setUserId(userlist.get(i).getUserId()); 
												user[id].setUserName(userlist.get(i).getUserName());
												user[id].setUserByBelongId(StringUtil.notNull(userlist.get(i).getUserByBelongId()));
												user[id].setBelongId(userlist.get(i).getBelongId());
												user[id].setNodeTag(userlist.get(i).getNodeTag());
												user[id].setNode("");
												user[id].setNodeABC("");
												user[id].setRefereeAll(userlist.get(i).getRefereeNode());
												if(user[id].getUserId().equals(updateUser.getUserId())){
													user[id].setUserByBelongId(belongUserNew.getUserId());
												}
												
											}
											String error="";
											 for(int i=1;i<user.length;i++){
												    if(user[i]!=null){
												    	if(!user[i].getUserId().equals(Constants.TOP_NODE)){
												    		int rid = user[i].getBelongId();
												    		String node = "";
													    	String nodeABC="";
													    	String nodeStr = "";
													    	if(user[i].getNodeTag()==1) nodeStr = "A";
													    	else if(user[i].getNodeTag()==2) nodeStr = "B";
													    	else if(user[i].getNodeTag()==3) nodeStr = "C";
													    	if(user[rid].getNode().equals("")){
													    		node = String.valueOf(rid);
													    		nodeABC= nodeStr;
													    	}
													    	else{
													    		node = user[rid].getNode()+","+String.valueOf(rid);
													    		nodeABC = user[rid].getNodeABC()+","+nodeStr;
													    	}
													    	user[i].setNode(node);
													    	user[i].setNodeABC(nodeABC);
														    	if(!user[i].getNode().equals(user[i].getRefereeAll())){
															    	if(userMapper.updateUserForField2("node", user[i].getNode(),"nodeABC", user[i].getNodeABC(), user[i].getUserId())==null){
															    		error=user[i].getUserId()+"节点信息更新失败。<br><br>";
															    	}
														    	}
														 }
												    }
												}
										if(error.equals("")){
											str = userId+"会员销售商变更成功，原销售商为"+belongUserOld.getUserName()+"("+belongUserOld.getUserId()+")，最新销售商："+belongUserNew.getUserName()+"("+belongUserNew.getUserId()+")！";
											Timestamp date = new Timestamp(new Date().getTime());
											AdminLog log = new AdminLog();
											log.setAdminName(adminName);
											log.setContents(str);
											log.setEntryTime(date);
											log.setType(ConstantsLog.LOGTYPE_4);
											adminLogMapper.save(log);
											sqlSession.commit();
										}else{
											str= "所有会员节点更新失败。";
											sqlSession.rollback();
										}
										}else{
											str= "获取所有会员ID信息失败。";
											sqlSession.rollback();
										}
								
									}else{
										str= "原销售信息更新失败。";
										sqlSession.rollback();
									}
									
								}else{
									str= "新销售商信息更新失败。";
									sqlSession.rollback();
								}
							}else{
								str= "变更会员信息更新失败。";
								sqlSession.rollback();
							}
							}else{
								str= "销售商所在区域已被使用或者获取区域信息失败。";
								sqlSession.rollback();
							}
						}else{
							str= "原销售商信息获取失败。";
							sqlSession.rollback();
						}
		    			}else{
							str= "新销售商加盟时间必须比会员加盟时间早。";
							sqlSession.rollback();
						}
		    		}else{
						str= "新销售商信息获取失败。";
						sqlSession.rollback();
					}
		    		}else{
						str= "原销售商与新销售商不能一样。";
						sqlSession.rollback();
					}
		    				}else{
								str= "此会员已结算，不能进行变更。";
								sqlSession.rollback();
							}
		    			}else{
							str= "结算信息获取失败。";
							sqlSession.rollback();
						}
		    			}else{
							str= "变更会员信息获取失败。";
							sqlSession.rollback();
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
		 
		 public String updateLock(String[] ids,String payTag){
			 String msg = "";
			 int num=0;
			 try{
				 for(int i=0;i<ids.length;i++){
					 String property="";
					 User user1 = userMapper.selectById(Integer.valueOf(ids[i]));
					 if(user1!=null){
						 if(userMapper.updateOfUsers("payTag", payTag, "userId", user1.getUserId())!=null){
							 num++;
							 if(msg.equals("")) msg = user1.getUserId();
							 else msg = msg +","+user1.getUserId();
						 }
					 }
				 }
				 if(num-ids.length==0) {
					 if(payTag.equals("0"))
						 msg = msg+"等用户冻结成功，请及时通知会员！";
					 	else if(payTag.equals("1"))
						 msg = msg+"等用户解锁成功，请及时通知会员！";
					 	sqlSession.commit();
				 }else{
					 num = 0;
					 msg ="";
					 sqlSession.rollback();
				 }
				
			 }catch(Exception e){
					num=0;
					 msg ="数据操作有误";
		    		e.printStackTrace();
		    		sqlSession.rollback();
			 }finally{
				 sqlSession.close();
			 }
			 return msg;
		 }
		 
		 public String importPriceExcel(List<User> ulist,String adminName){
			 String msg = "";
		    	try{
		    		String error = "";
		    		for(int i=0;i<ulist.size();i++){
		    			String sql1 ="update users set totalIncome = totalIncome +'"+ulist.get(i).getTotalIncome()+"' where userId='"+ulist.get(i).getUserId()+"'";
		    			Integer up1 = userMapper.updateForSql(sql1);
		    			if(up1==null) error=ulist.get(i).getUserId()+"收入更新失败；<br>";
		    			String sql2 ="update wsettle set joinPriceTal = joinPriceTal +'"+ulist.get(i).getJoinPrice()+"',joinPvTal = joinPvTal +'"+ulist.get(i).getJoinPv()+"' where userId='"+ulist.get(i).getUserId()+"'";
		    			Integer up2=userMapper.updateForSql(sql2);
		    			if(up2==null) error=ulist.get(i).getUserId()+"加盟信息更新失败；<br>";
		    		}
		    		if(error.equals("")){
		    			sqlSession.commit();
		    			msg = "数据导入成功，请核对！";
		    		}else{
		    			msg=error;
		    		}
		    	}catch(Exception e){
		    		msg ="数据导入异常，请核对。";
		    		e.printStackTrace();
		    		sqlSession.rollback();
		    	} finally {
					sqlSession.close();
		    	}
		    	return msg;
		 }
		
		 
		 public String priceSummary(){
			 String msg = "";
		    	try{
		    		String error = "";
		    		double[] sum = {0,0,0};
		    		int max = stMapper.selectMaxWeek();
		    		if(max>0){
		    			for(int i=1;i<=max;i++){
		    				Map<String,Object> params = new HashMap<>();
		    	    		params.put("tableName","wreward_"+i);
		    	    		List<WReward> wlist = 	wrdMapper.selectByList(params);
		    	    		for(int j=0;j<wlist.size();j++){
		    	    			if(wlist.get(i).getAmount_9()>0){
		    	    				String sql1 = "update users set totalIncome = totalIncome+'"+wlist.get(i).getAmount_9()+"' where userId='"+wlist.get(i).getUserId()+"'";
		    	    				Integer up1 = userMapper.updateForSql(sql1);
		    		    			if(up1==null) error=wlist.get(i).getUserId()+"收入更新失败；<br>";
		    	    			}
		    	    		}

		    			}
		    		}
    				Map<String,Object> params1 = new HashMap<>();

    				JoinInfo jf = new JoinInfo();
    				jf.setState(2);
    				Timestamp startTime = new Timestamp(StringUtil.parseToDate("2017-10-01 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
    				Timestamp endTime = new Timestamp(StringUtil.parseToDate("2018-01-08 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());

    				jf.setStartTime(startTime);
    				jf.setEndTime(endTime);
    				params1.put("joinInfo", jf);
		    		List<JoinInfo> ulist = joinInfoMapper.selectAllByList(params1);
		    		for(int i=0;i<ulist.size();i++){
		    			if(ulist.get(i).getPrice()>0){
		    			String sql2 ="update wsettle set joinPriceTal = joinPriceTal +'"+ulist.get(i).getPrice()+"',joinPvTal = joinPvTal +'"+ulist.get(i).getPv()+"' where userId='"+ulist.get(i).getUserId()+"'";
		    			Integer up2=userMapper.updateForSql(sql2);
		    			if(up2==null) error=ulist.get(i).getUserId()+"加盟信息更新失败；<br>";
		    			}
		    		}
		    		if(error.equals("")){
		    			sqlSession.commit();
		    			msg = "数据导入成功，请核对！";
		    		}else{
		    			msg=error;
		    		}
		    	}catch(Exception e){
		    		msg ="数据导入异常，请核对。";
		    		e.printStackTrace();
		    		sqlSession.rollback();
		    	} finally {
					sqlSession.close();
		    	}
		    	return msg;
		 }
		 
		   public List<User> findRefereeListByUserId(String userId){
		    	List<User> users = new ArrayList<User>();
		    	try{
			    		if(!StringUtil.notNull(userId).equals("")){
			    			User user = userMapper.selectAllByUserId(userId);
			    			if(user!=null){
			    				users.add(user);
			    			Map<String,Object> params = new HashMap<>();
			    			List<User> ulist = userMapper.selectByList(params);
			    			for(int i=ulist.size()-1;i>=0;i--){
			    				String refereeNode = ulist.get(i).getRefereeNode();
			    				if(!StringUtil.notNull(refereeNode).equals("")){
			    					String[] str = refereeNode.split(",");
			    					for(int j=0;j<str.length;j++){
			    						if(!str[j].equals("")){
			    							if(str[j].equals(String.valueOf(user.getId()))){
			    								users.add(ulist.get(i));
			    								break;
			    							}
			    						}
			    					}
			    				}
			    			}
					sqlSession.commit();
			    			}else{
				    			users=null;
				    		}
		    		}else{
		    			users=null;
		    		}
		    	}catch(Exception e){
		    		users=null;
		    		e.printStackTrace();
		    		sqlSession.rollback();
		    	}finally{
		    		sqlSession.close();
		        }
				return users;
			}
		   
		   public List<User> findBelongListByUserId(String userId){
		    	List<User> users = new ArrayList<User>();
		    	try{
			    		if(!StringUtil.notNull(userId).equals("")){
			    			User user = userMapper.selectAllByUserId(userId);
			    			if(user!=null){
			    				users.add(user);
			    			Map<String,Object> params = new HashMap<>();
			    			List<User> ulist = userMapper.selectByList(params);
			    			for(int i=ulist.size()-1;i>=0;i--){
			    				String node = ulist.get(i).getNode();
			    				if(!StringUtil.notNull(node).equals("")){
			    					String[] str = node.split(",");
			    					for(int j=0;j<str.length;j++){
			    						if(!str[j].equals("")){
			    							if(str[j].equals(String.valueOf(user.getId()))){
			    								users.add(ulist.get(i));
			    								break;
			    							}
			    						}
			    					}
			    				}
			    			}
					sqlSession.commit();
			    			}else{
				    			users=null;
				    		}
		    		}else{
		    			users=null;
		    		}
		    	}catch(Exception e){
		    		users=null;
		    		e.printStackTrace();
		    		sqlSession.rollback();
		    	}finally{
		    		sqlSession.close();
		        }
				return users;
			}
		   
		   
		   public String priceSummary_13_15(){
				 String msg = "";
			    	try{
			    		String error = "";
			    		double[] sum = {0,0,0};
			    		int max = 15;
			    		if(max>0){
			    			for(int i=13;i<=max;i++){
			    				Map<String,Object> params = new HashMap<>();
			    	    		params.put("tableName","wreward_"+i);
			    	    		List<WReward> wlist = 	wrdMapper.selectByList(params);
			    	    		System.out.println("wlist.size():"+wlist.size());
			    	    		int t=0;
			    	    		for(int j=0;j<wlist.size();j++){
			    	    			if(wlist.get(j).getAmount_9()>0){
			    	    				t++;
			    	    				String sql1 = "update users set totalIncome = totalIncome+'"+wlist.get(j).getAmount_9()+"' where userId='"+wlist.get(j).getUserId()+"'";
			    	    				Integer up1 = userMapper.updateForSql(sql1);
			    		    			if(up1==null) error=wlist.get(j).getUserId()+"收入更新失败；<br>";
			    	    			}
			    	    		}
			    	    		System.out.println("t:"+t);
			    			}
			    		}else{
			    			error="max获取失败。";
			    		}
	    			
			    		if(error.equals("")){
			    			sqlSession.commit();
			    			msg = "数据更新成功，请核对！";
			    		}else{
			    			msg=error;
			    		}
			    	}catch(Exception e){
			    		msg ="数据导入异常，请核对。";
			    		e.printStackTrace();
			    		sqlSession.rollback();
			    	} finally {
						sqlSession.close();
			    	}
			    	return msg;
			 }
		   
		   
		   public String updateBelongNode(){
		    	String str = "";
		    	try{
		    	
			
										Map<String,Object> params = new HashMap<String,Object>();
										List<User> userlist = userMapper.selectUserByListForUpdate(params);
										Integer maxId = userMapper.maxId(params);
										if(maxId!=null&&maxId>0){
											User[] user = new User[maxId+1];
											for(int i=0;i<userlist.size();i++){
												int id= userlist.get(i).getId();
												if(user[id]==null) user[id] = new User();
												user[id].setId(id);
												user[id].setUserId(userlist.get(i).getUserId()); 
												user[id].setUserName(userlist.get(i).getUserName());
												user[id].setUserByBelongId(StringUtil.notNull(userlist.get(i).getUserByBelongId()));
												user[id].setBelongId(userlist.get(i).getBelongId());
												user[id].setNodeTag(userlist.get(i).getNodeTag());
												user[id].setNode("");
												user[id].setNodeABC("");
												user[id].setRefereeAll(userlist.get(i).getRefereeNode());
											}
											String error="";
											 for(int i=1;i<user.length;i++){
												    if(user[i]!=null){
												    	if(!user[i].getUserId().equals(Constants.TOP_NODE)){
												    		int rid = user[i].getBelongId();
												    		String node = "";
													    	String nodeABC="";
													    	String nodeStr = "";
													    	if(user[i].getNodeTag()==1) nodeStr = "A";
													    	else if(user[i].getNodeTag()==2) nodeStr = "B";
													    	else if(user[i].getNodeTag()==3) nodeStr = "C";
													    	if(user[rid].getNode().equals("")){
													    		node = String.valueOf(rid);
													    		nodeABC= nodeStr;
													    	}
													    	else{
													    		node = user[rid].getNode()+","+String.valueOf(rid);
													    		nodeABC = user[rid].getNodeABC()+","+nodeStr;
													    	}
													    	user[i].setNode(node);
													    	user[i].setNodeABC(nodeABC);
														    	if(!user[i].getNode().equals(user[i].getRefereeAll())){
															    	if(userMapper.updateUserForField2("node", user[i].getNode(),"nodeABC", user[i].getNodeABC(), user[i].getUserId())==null){
															    		error=user[i].getUserId()+"节点信息更新失败。<br><br>";
															    	}
														    	}
												    	}
												    }
												}
										if(error.equals("")){
											str = "会员销售商变更成功！";
											
											sqlSession.commit();
										}else{
											str= "所有会员节点更新失败。";
											sqlSession.rollback();
								
										}
								}else{
									str= "会员节点信息获取失败。";
									sqlSession.rollback();
						
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
