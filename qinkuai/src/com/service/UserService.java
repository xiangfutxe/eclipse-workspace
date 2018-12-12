package com.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.pojo.Settle;
import com.pojo.User;
import com.utils.ArithUtil;
import com.utils.StringUtil;


public class UserService implements IUserService {
	
	private Statement stmt = null;
	
	private ResultSet rs = null;
	
	public User findById(Connection conn,String userId){
		User user = new User();
		try {
		String sql = "select * from users as us,userinfo as uo where us.userId = uo.userId and us.userId ='"+userId+"'";
			stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			user.setId(rs.getInt("id"));
			user.setUserId(rs.getString("userId"));
			user.setUserName(rs.getString("userName"));
			user.setDeclarationNode(rs.getString("declarationNode"));
			user.setRefereeNode(rs.getString("refereeNode"));
			user.setNode(rs.getString("node"));
			user.setNodeABC(rs.getString("nodeABC"));
			user.setRefereeAll(rs.getString("refereeAll"));
			user.setNodeTag(rs.getInt("nodeTag"));
			user.setDmoney(rs.getDouble("dmoney"));
			user.setSmoney(rs.getDouble("smoney"));
			user.setEmoney(rs.getDouble("emoney"));
			user.setRmoney(rs.getDouble("rmoney"));
			user.setPmoney(rs.getDouble("pmoney"));
			user.setIntegral(rs.getDouble("integral"));
			user.setFund(rs.getDouble("fund"));
			user.setDocumentType(rs.getString("documentType"));
			user.setSex(rs.getString("sex"));
			user.setAge(rs.getInt("age"));
			user.setTel(rs.getString("tel"));
			user.setNumId(rs.getString("numId"));
			user.setPhone(rs.getString("phone"));
			user.setProvince(rs.getString("province"));
			user.setCity(rs.getString("city"));
			user.setArea(rs.getString("area"));
			user.setPassword(rs.getString("password"));
			user.setPassword2(rs.getString("password2"));
			user.setWeixin(rs.getString("weixin"));
			user.setEmail(rs.getString("email"));
			user.setQq(rs.getString("qq"));
			user.setUserByAId(rs.getString("userByAId"));
			user.setUserByBId(rs.getString("userByBId"));
			user.setUserByCId(rs.getString("userByCId"));
			user.setUserByRefereeId(rs.getString("userByRefereeId"));
			user.setUserByBelongId(rs.getString("userByBelongId"));
			user.setUserByDeclarationId(rs.getString("userByDeclarationId"));
			user.setRankJoin(rs.getInt("rankJoin"));
			user.setRankManage(rs.getInt("rankManage"));
			user.setTotalIncome(rs.getDouble("totalIncome"));
			user.setRefereeNum(rs.getInt("refereeNum"));
			user.setAddress(rs.getString("address"));
			user.setBankName(rs.getString("bankName"));
			user.setBankAdr(rs.getString("bankAdr"));
			user.setUserName(rs.getString("userName"));
			user.setAccountId(rs.getString("accountId"));
			user.setAccountName(rs.getString("accountName"));
			user.setEntryTime(rs.getTimestamp("entryTime"));
			user.setValidtyTime(rs.getTimestamp("validtyTime"));
			user.setState(rs.getInt("state"));
			user.setPayTag(rs.getInt("payTag"));
			user.setIsUp(rs.getInt("isUp"));
			user.setIsEmpty(rs.getInt("isEmpty"));
			user.setIntegral(rs.getDouble("integral"));
			user.setTeam(rs.getString("team"));
			user.setTeamName(rs.getString("team_name"));
			user.setAddNum(rs.getInt("addNum"));
			user.setEmptyNum(rs.getInt("emptyNum"));
			user.setShopNum(rs.getInt("shopNum"));
			user.setCenterType(rs.getInt("centerType"));
			user.setCenterId(rs.getInt("centerId"));
			user.setViewNum(rs.getInt("view_num"));
			user.setViewTime(rs.getTimestamp("view_time"));
			user.setListTag(rs.getInt("list_tag"));
			user.setImgTag(rs.getInt("img_tag"));
			user.setBackFill(rs.getDouble("back_fill"));
			user.setBackFillReach(rs.getDouble("back_fill_reach"));
			user.setBackFillTag(rs.getInt("back_fill_tag"));
			user.setIsBelongList(rs.getInt("is_belong_list"));
			user.setIsRefereeList(rs.getInt("is_referee_list"));
			user.setIsDeclaration(rs.getInt("is_declaration"));
			user.setIsUserList(rs.getInt("is_user_list"));
		}else user= null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			user=null;
			e.printStackTrace();
		}
		return user;
	}
	
	public User getUserById(Connection conn,int userId){
		User user = new User();
		try {
		String sql = "select * from users as us,userinfo as uo where us.userId = uo.userId and us.id ='"+userId+"'";
			stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			user.setId(rs.getInt("id"));
			user.setUserId(rs.getString("userId"));
			user.setUserName(rs.getString("userName"));
			user.setDeclarationNode(rs.getString("declarationNode"));
			user.setRefereeNode(rs.getString("refereeNode"));
			user.setNode(rs.getString("node"));
			user.setNodeABC(rs.getString("nodeABC"));
			user.setRefereeAll(rs.getString("refereeAll"));
			user.setNodeTag(rs.getInt("nodeTag"));
			user.setDmoney(rs.getDouble("dmoney"));
			user.setSmoney(rs.getDouble("smoney"));
			user.setEmoney(rs.getDouble("emoney"));
			user.setRmoney(rs.getDouble("rmoney"));
			user.setPmoney(rs.getDouble("pmoney"));
			user.setDocumentType(rs.getString("documentType"));
			user.setSex(rs.getString("sex"));
			user.setAge(rs.getInt("age"));
			user.setTel(rs.getString("tel"));
			user.setNumId(rs.getString("numId"));
			user.setPhone(rs.getString("phone"));
			user.setProvince(rs.getString("province"));
			user.setCity(rs.getString("city"));
			user.setArea(rs.getString("area"));
			user.setPassword(rs.getString("password"));
			user.setPassword2(rs.getString("password2"));
			user.setWeixin(rs.getString("weixin"));
			user.setEmail(rs.getString("email"));
			user.setQq(rs.getString("qq"));
			user.setUserByAId(rs.getString("userByAId"));
			user.setUserByBId(rs.getString("userByBId"));
			user.setUserByCId(rs.getString("userByCId"));
			user.setUserByRefereeId(rs.getString("userByRefereeId"));
			user.setUserByBelongId(rs.getString("userByBelongId"));
			user.setUserByDeclarationId(rs.getString("userByDeclarationId"));
			user.setRankJoin(rs.getInt("rankJoin"));
			user.setRankManage(rs.getInt("rankManage"));
			user.setTotalIncome(rs.getDouble("totalIncome"));
			user.setRefereeNum(rs.getInt("refereeNum"));
			user.setAddress(rs.getString("address"));
			user.setBankName(rs.getString("bankName"));
			user.setBankAdr(rs.getString("bankAdr"));
			user.setUserName(rs.getString("userName"));
			user.setAccountId(rs.getString("accountId"));
			user.setAccountName(rs.getString("accountName"));
			user.setEntryTime(rs.getTimestamp("entryTime"));
			user.setValidtyTime(rs.getTimestamp("validtyTime"));
			user.setState(rs.getInt("state"));
			user.setPayTag(rs.getInt("payTag"));
			user.setIsUp(rs.getInt("isUp"));
			user.setIsEmpty(rs.getInt("isEmpty"));
			user.setIntegral(rs.getDouble("integral"));
			user.setTeam(rs.getString("team"));
			user.setTeamName(rs.getString("team_name"));
			user.setCenterId(rs.getInt("centerId"));
			user.setCenterType(rs.getInt("centerType"));
			user.setViewNum(rs.getInt("view_num"));
			user.setViewTime(rs.getTimestamp("view_time"));
			user.setListTag(rs.getInt("list_tag"));
			user.setImgTag(rs.getInt("img_tag"));
			user.setBackFill(rs.getDouble("back_fill"));
			user.setBackFillReach(rs.getDouble("back_fill_reach"));
			user.setBackFillTag(rs.getInt("back_fill_tag"));
			user.setIsBelongList(rs.getInt("is_belong_list"));
			user.setIsRefereeList(rs.getInt("is_referee_list"));
			user.setIsDeclaration(rs.getInt("is_declaration"));
			user.setIsUserList(rs.getInt("is_user_list"));
		}else user= null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			user=null;
			e.printStackTrace();
		}
		return user;
	}


	public synchronized boolean checkWSettle(Connection conn,String table_name,int weekTag,String refereeNode,String node){
		  List<String> uList = new ArrayList<String>();  
		  boolean b = false;
		  try {
			  rs = conn.getMetaData().getTables(null, null, table_name, null );
				if(!rs.next()){
					createWSettle(conn,table_name);
				}
		    String[] strarray1 = node.split(",");
		    String[] strarray2 = refereeNode.split(",");
		    Set<String> set = new HashSet<String>();  
		    for(int i=0;i<strarray1.length;i++){
		    	set.add(strarray1[i]);
		    }
		    for(int i=0;i<strarray2.length;i++){
		    	set.add(strarray2[i]);
		    }
		    Iterator<String> it = set.iterator();  
		    while(it.hasNext()){  
		    	String strarray = it.next();
		    	if(!strarray.equals("")){
		    String sql = "select * from users where id='"+strarray+"'";
		    stmt = conn.createStatement();
		    rs=stmt.executeQuery(sql);
		    if(rs.next()){
		    	 int uId = rs.getInt("id");
			   String userId = rs.getString("userId");
			   String userName = rs.getString("userName");
			   String sql2 = "select * from "+table_name+" where userId='"+userId+"'";
			    stmt = conn.createStatement();
			    rs=stmt.executeQuery(sql2);
			    if(!rs.next()){
						double totalPerformance = 0;
						int totalNum = 0;
						double totalPrice = 0;
						double atpv = 0;
						double btpv = 0;
						double ctpv = 0;
						double rtpv = 0;
						if(weekTag-1>0){
								String table_name_1 = "wsettle_"+String.valueOf(weekTag-1);
								String sqls = "select * from "+table_name_1+" where userId='"+userId+"'";
								Statement stmt1 = conn.createStatement();
								ResultSet rs1 =stmt1.executeQuery(sqls);
								if(rs1.next()){
									totalPerformance = ArithUtil.add(rs1.getDouble("totalPerformance"),rs1.getDouble("newPerformance"));
									totalNum = rs1.getInt("totalNum")+rs1.getInt("newNum");
									totalPrice = ArithUtil.add(rs1.getDouble("totalPrice"),rs1.getDouble("newPrice"));
									atpv = ArithUtil.add(rs1.getDouble("atpv"),rs1.getDouble("apv"));
									btpv = ArithUtil.add(rs1.getDouble("btpv"),rs1.getDouble("bpv"));
									ctpv = ArithUtil.add(rs1.getDouble("ctpv"),rs1.getDouble("cpv"));
									rtpv = ArithUtil.add(rs1.getDouble("rtpv"),rs1.getDouble("rpv"));
								}
							}
						String sqli = "insert into "+table_name+"(uId,userId,userName,totalPerformance,totalNum,totalPrice,atpv,btpv,ctpv,rtpv) values('"
						+ uId+"','"+ userId+"','"+userName+"','"+totalPerformance+"','"+totalNum+"','"+totalPrice+"','"+atpv+"','"+btpv+"','"+ctpv+"','"+rtpv+"')"; 
						uList.add(sqli);
			    	}  
		    	}
		    	}
		    }
		    
		    if(uList.size()>0){
		    stmt = conn.createStatement();
		    for(int i=0;i<uList.size();i++){
		    	stmt.addBatch(uList.get(i));
		    	 if ((i % 50000 == 0 && i != 0)
							|| i == (uList.size() - 1)) {
						stmt.executeBatch();
						stmt.close();
						stmt = conn.createStatement();
					}
		    	}
		    
		    }
		    b=true;
		    	} catch (SQLException e) {
					// TODO Auto-generated catch block
					try {
						conn.rollback();
						b=false;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						b=false;
					}
					e.printStackTrace();
				}
		  return b;
	}
	

	public synchronized boolean updatePv(Connection conn,String table_name,double pv,double emoney,int id,String refereeNode,String node,String nodeABC){
		List<String> slist = new ArrayList<String>();
		boolean b =false;
			try {
			String sql = "select * from "+table_name+" where uId in("+node+") order by field(uId,"+node+") for update";
			stmt = conn.createStatement();
		 rs =stmt.executeQuery(sql);
		 String[] strarray = nodeABC.split(",");
		 int i=0;
		 while(rs.next()){
			 String property = "";
			 double upv = 0;
			 if(strarray[i].equals("A")){
				 property="apv";
				 upv = ArithUtil.add(rs.getDouble("apv"),pv);
			 }
			 else if(strarray[i].equals("B")) {
				 property="bpv";
				 upv = ArithUtil.add(rs.getDouble("bpv"),pv);
			 }
			 else if(strarray[i].equals("C")) {
				 property="cpv";
				 upv = ArithUtil.add(rs.getDouble("cpv"),pv);
			 }
			 String sqlu  = "update "+table_name+" set newPerformance='"+ArithUtil.add(rs.getDouble("newPerformance"),pv)+"',newPrice='"
					 +ArithUtil.add(rs.getDouble("newPrice"),emoney)+"',"+property+"='"+upv+"' where id='"+rs.getInt("id")+"'";
			 slist.add(sqlu);
			 i++;
		 }
		 String sqlr = "select * from "+table_name+" where uId='"+id+"' for update";
		 stmt = conn.createStatement();
		 rs =stmt.executeQuery(sqlr);
		 if(rs.next()){
			 String sqlu  = "update "+table_name+" set rpv='"+ArithUtil.add(rs.getDouble("rpv"),pv)+"' where id='"+rs.getInt("id")+"'";
			slist.add(sqlu);
		 }
		 sqlr = "select * from "+table_name+" where uId in("+refereeNode+")  order by field(uId,"+refereeNode+") for update";
		 stmt = conn.createStatement();
		 rs =stmt.executeQuery(sqlr);
		 while(rs.next()){
			 String sqlu  = "update "+table_name+" set rpv='"+ArithUtil.add(rs.getDouble("rpv"),pv)+"' where id='"+rs.getInt("id")+"'";
			slist.add(sqlu);
		 }
		 if(slist.size()>0){
			 stmt =conn.createStatement();
			 for(int j=0;j<slist.size();j++){
				 stmt.addBatch(slist.get(j));
				 if ((j % 50000 == 0 && j != 0)
							|| j == (slist.size() - 1)) {
						stmt.executeBatch();
						stmt.close();
						stmt = conn.createStatement();
					}
			}
			 }
		 b=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					conn.rollback();
					b=false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					b=false;
				}
				e.printStackTrace();
			}
		return b;
	}
	
	public synchronized boolean updateBelongPv(Connection conn,String table_name,double pv,double emoney,String node,String nodeABC){
		List<String> slist = new ArrayList<String>();
		boolean b =false;
			try {
				if(!node.equals("")){
			String sql = "select * from "+table_name+" where uId in("+node+") order by field(uId,"+node+") for update";
			stmt = conn.createStatement();
		 rs =stmt.executeQuery(sql);
		 String[] strarray = nodeABC.split(",");
		 int i=0;
		 while(rs.next()){
			 String property = "";
			 double upv = 0;
			 if(strarray[i].equals("A")){
				 property="apv";
				 upv = ArithUtil.add(rs.getDouble("apv"),pv);
			 }
			 else if(strarray[i].equals("B")) {
				 property="bpv";
				 upv = ArithUtil.add(rs.getDouble("bpv"),pv);
			 }
			 else if(strarray[i].equals("C")) {
				 property="cpv";
				 upv = ArithUtil.add(rs.getDouble("cpv"),pv);
			 }
			 String sqlu  = "update "+table_name+" set newPerformance='"+ArithUtil.add(rs.getDouble("newPerformance"),pv)+"',newPrice='"
					 +ArithUtil.add(rs.getDouble("newPrice"),emoney)+"',"+property+"='"+upv+"' where id='"+rs.getInt("id")+"'";
			 slist.add(sqlu);
			 i++;
		 }
		
		 if(slist.size()>0){
			 stmt =conn.createStatement();
			 for(int j=0;j<slist.size();j++){
				 stmt.addBatch(slist.get(j));
				 if ((j % 50000 == 0 && j != 0)
							|| j == (slist.size() - 1)) {
						stmt.executeBatch();
						stmt.close();
						stmt = conn.createStatement();
					}
			}
			 }
				}
		 b=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					conn.rollback();
					b=false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					b=false;
				}
				e.printStackTrace();
			}
		return b;
	}
	
	public synchronized boolean updateRefereePv(Connection conn,String table_name,double pv,int id,String refereeNode){
		List<String> slist = new ArrayList<String>();
		boolean b =false;
			try {
			
				 String sqlr = "select * from "+table_name+" where uId='"+id+"' for update";
				 stmt = conn.createStatement();
				 rs =stmt.executeQuery(sqlr);
				 if(rs.next()){
					 String sqlu  = "update "+table_name+" set rpv='"+ArithUtil.add(rs.getDouble("rpv"),pv)+"' where id='"+rs.getInt("id")+"'";
					slist.add(sqlu);
				 }
				 if(!refereeNode.equals("")){
				 sqlr = "select * from "+table_name+" where uId in("+refereeNode+")  order by field(uId,"+refereeNode+") for update";
				 stmt = conn.createStatement();
				 rs =stmt.executeQuery(sqlr);
				 while(rs.next()){
					 String sqlu  = "update "+table_name+" set rpv='"+ArithUtil.add(rs.getDouble("rpv"),pv)+"' where id='"+rs.getInt("id")+"'";
					slist.add(sqlu);
				 }
				 }
				 if(slist.size()>0){
					 stmt =conn.createStatement();
					 for(int j=0;j<slist.size();j++){
						 stmt.addBatch(slist.get(j));
						 if ((j % 50000 == 0 && j != 0)
									|| j == (slist.size() - 1)) {
								stmt.executeBatch();
								stmt.close();
								stmt = conn.createStatement();
							}
					}
					 }
				 b=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					conn.rollback();
					b=false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					b=false;
				}
				e.printStackTrace();
			}
		return b;
	}
	
	public synchronized boolean updateRefereePv_SUB(Connection conn,String table_name,double pv,String refereeNode){
		List<String> slist = new ArrayList<String>();
		boolean b =false;
			try {
			
		 String sqlr = "select * from "+table_name+" where uId in("+refereeNode+")  order by field(uId,"+refereeNode+") for update";
		 stmt = conn.createStatement();
		 rs =stmt.executeQuery(sqlr);
		 while(rs.next()){
			 String sqlu  = "update "+table_name+" set rpv='"+ArithUtil.add(rs.getDouble("rpv"),pv)+"' where id='"+rs.getInt("id")+"'";
			slist.add(sqlu);
		 }
		 if(slist.size()>0){
			 stmt =conn.createStatement();
			 for(int j=0;j<slist.size();j++){
				 stmt.addBatch(slist.get(j));
				 if ((j % 50000 == 0 && j != 0)
							|| j == (slist.size() - 1)) {
						stmt.executeBatch();
						stmt.close();
						stmt = conn.createStatement();
					}
			}
			 }
		 b=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					conn.rollback();
					b=false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					b=false;
				}
				e.printStackTrace();
			}
		return b;
	}
	
	public synchronized boolean updateNode(Connection conn,String table_name,double pv,double emoney,int id,String refereeNode,String node,String nodeABC){
		List<String> slist = new ArrayList<String>();
		boolean b =false;
			try {
			String sql = "select * from "+table_name+" where uId in("+node+") order by field(uId,"+node+") for update";
			stmt = conn.createStatement();
		 rs =stmt.executeQuery(sql);
		 String[] strarray = nodeABC.split(",");
		 int i=0;
		 while(rs.next()){
			 String property = "";
			 double upv = 0;
			 if(strarray[i].equals("A")){
				 property="apv";
				 upv = ArithUtil.add(rs.getDouble("apv"),pv);
			 }
			 else if(strarray[i].equals("B")) {
				 property="bpv";
				 upv = ArithUtil.add(rs.getDouble("bpv"),pv);
			 }
			 else if(strarray[i].equals("C")) {
				 property="cpv";
				 upv = ArithUtil.add(rs.getDouble("cpv"),pv);
			 }
			 String sqlu  = "update "+table_name+" set newPerformance='"+ArithUtil.add(rs.getDouble("newPerformance"),pv)+"',newPrice='"
					 +ArithUtil.add(rs.getDouble("newPrice"),emoney)+"',"+property+"='"+upv+"' where id='"+rs.getInt("id")+"'";
			 slist.add(sqlu);
			 i++;
		 }
		 String sqlr = "select * from "+table_name+" where uId in("+refereeNode+") or uId='"+id+"' order by field(uId,"+refereeNode+") for update";
		 stmt = conn.createStatement();
		 rs =stmt.executeQuery(sqlr);
		 while(rs.next()){
			 String sqlu  = "update "+table_name+" set rpv='"+ArithUtil.add(rs.getDouble("rpv"),pv)+"' where id='"+rs.getInt("id")+"'";
			slist.add(sqlu);
		 }
		 if(slist.size()>0){
			 stmt =conn.createStatement();
			 for(int j=0;j<slist.size();j++){
				 stmt.addBatch(slist.get(j));
				 if ((j % 50000 == 0 && j != 0)
							|| j == (slist.size() - 1)) {
						stmt.executeBatch();
						stmt.close();
						stmt = conn.createStatement();
					}
			}
			 }
		 b=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					conn.rollback();
					b=false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					b=false;
				}
				e.printStackTrace();
			}
		return b;
	}
	
	public synchronized boolean checkUserVIP(Connection conn,String node,Settle st){
		List<String> slist = new ArrayList<String>();
		boolean b =false;
		String table_name = "user_vip_"+st.getWeekTag();
			try {
				boolean a = true;
				 rs = conn.getMetaData().getTables(null, null, table_name, null );
					if(!rs.next()){
						if(!createUserVIP(conn,table_name,st)) a = false;
					}
					if(a){
				Timestamp date1 = new Timestamp(new Date().getTime());
				java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		  String[] strarray1 = node.split(",");
		 for(int i=0;i<strarray1.length;i++){
			 String sql2="select * from users where id='"+strarray1[i]+"'";
			 stmt = conn.createStatement();
			 rs =stmt.executeQuery(sql2);
			 if(rs.next()){
				 if(rs.getInt("rankJoin")==5){
					 int  uId = rs.getInt("id");
					 String userId = rs.getString("userId");
					 String userName = rs.getString("userName");
					 String sql = "select * from "+table_name+" where uId ='"+strarray1[i]+"' and floor='"+(strarray1.length-i)+"'";
					stmt = conn.createStatement();
					 rs =stmt.executeQuery(sql);
				 if(!rs.next()){
						 String sql3 = "insert into "+table_name+"(uId,userId,userName,floor,entryTime) values('"+uId+"','"+userId
								 +"','"+userName+"','"+(strarray1.length-i)+"','"+date+"')";
						 slist.add(sql3);
					 }
				 }
				 }
		 }
		 if(slist.size()>0){
			 stmt =conn.createStatement();
			 for(int j=0;j<slist.size();j++){
				 stmt.addBatch(slist.get(j));
				 if ((j % 50000 == 0 && j != 0)
							|| j == (slist.size() - 1)) {
						stmt.executeBatch();
						stmt.close();
						stmt = conn.createStatement();
					}
			}
			 }
		
		 b=true;
					}else b=false;
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
				b=false;
			}
		return b;
	}
	
	public synchronized boolean updateVIP(Connection conn,String node,String nodeABC,Settle st){
		List<String> slist = new ArrayList<String>();
		boolean b =false;
		String table_name = "user_vip";
		try {
			 rs = conn.getMetaData().getTables(null, null, table_name, null );
				if(!rs.next()){
					createUserVIP(conn,table_name,st);
				}
		 String[] strarray1 = StringUtil.notNull(node).split(",");
		 String[] strarray = StringUtil.notNull(nodeABC).split(",");
		 for(int i=0;i<strarray1.length;i++){
		 if(!strarray1[i].equals("")){
		 Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			String sql = "select * from "+table_name+" where uId ='"+strarray1[i]+"'and floor='"+(strarray1.length-i)+"' for update";
			stmt = conn.createStatement();
			rs =stmt.executeQuery(sql);
			 if(rs.next()){
				 String property = "";
				 int num = 0;
				 if(strarray[i].equals("A")){
					 property="ANum";
					 num = rs.getInt("ANum")+1;
				 }
				 else if(strarray[i].equals("B")) {
					 property="BNum";
					 num = rs.getInt("BNum")+1;
				 }
				 String sqlu  = "update "+table_name+"  set "+property+"='"+num+"',time='"+date+"' where id='"+rs.getInt("id")+"'";
				 slist.add(sqlu);
			 }else{
				 	String sql3= "select * from users where id='"+strarray1[i]+"'";
				 	stmt = conn.createStatement();
					rs =stmt.executeQuery(sql3);
					if(rs.next()){
						int anum = 0;
						int bnum =0;
						 if(strarray[i].equals("A")){
							 anum=1;
						 }
						 else if(strarray[i].equals("B")) {
							 bnum=1;
						 }
						sql3 = "insert into "+table_name+"(uId,userId,userName,floor,ANum,BNum,entryTime) values('"+rs.getInt("id")+"','"+rs.getString("userId")
						 +"','"+rs.getString("userName")+"','"+(strarray1.length-i)+"','"+anum+"','"+bnum+"','"+date+"')";
						slist.add(sql3);
					}
			 }
		 }
		 }
		 if(slist.size()>0){
			 stmt =conn.createStatement();
			 for(int j=0;j<slist.size();j++){
				 stmt.addBatch(slist.get(j));
				 if ((j % 50000 == 0 && j != 0)
							|| j == (slist.size() - 1)) {
						stmt.executeBatch();
						stmt.close();
						stmt = conn.createStatement();
					}
			}
			 }
		 b=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					conn.rollback();
					b=false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					b=false;
				}
				e.printStackTrace();
				b=false;
			}
		return b;
	}
	
	 public void createWSettle(Connection conn,String table_name){
		 try {
		 String sql =" CREATE TABLE "+table_name+" (id int(11) NOT NULL AUTO_INCREMENT, uId int(11) NOT NULL,userId varchar(10) not null,userName varchar(50) not null,totalPerformance decimal(16,3) DEFAULT '0', "
					+ "newPerformance decimal(16,3) DEFAULT '0',totalPrice decimal(16,3) DEFAULT '0', newPrice decimal(16,3) DEFAULT '0',"
					+ "totalNum int(11) DEFAULT '0',newNum int(11) DEFAULT '0',apv decimal(16,3) DEFAULT '0',acpv decimal(16,3) DEFAULT '0',aspv decimal(16,3) DEFAULT '0',"
					+ "atpv decimal(16,3) DEFAULT '0',bpv decimal(16,3) DEFAULT '0',bcpv decimal(16,3) DEFAULT '0',btpv decimal(16,3) DEFAULT '0',bspv decimal(16,3) DEFAULT '0',cpv decimal(16,3) DEFAULT '0',"
					+ "ccpv decimal(16,3) DEFAULT '0',ctpv decimal(16,3) DEFAULT '0',cspv decimal(16,3) DEFAULT '0',rpv decimal(16,3) DEFAULT '0',rtpv decimal(16,3) DEFAULT '0',maxRank TINYINT(2) DEFAULT '0',"
					+ "maxRankNew TINYINT(2) DEFAULT '0',rank TINYINT(2) DEFAULT '0',groupPerformance decimal(16,3) DEFAULT '0',"
					+ " weekTag int(5) DEFAULT '0', state varchar(2) DEFAULT '0',startTime datetime DEFAULT NULL,"
					+ "endTime datetime DEFAULT NULL,entryTime datetime DEFAULT NULL,PRIMARY KEY (userId),UNIQUE KEY userId(userId), UNIQUE KEY id(id),index(rank)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		stmt = conn.createStatement();
     	stmt.execute(sql);
		 } catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
				}
	 
	 public boolean createUserVIP(Connection conn,String table_name,Settle st){
			boolean b =false;
		 try {
		 String sql =" CREATE TABLE If Not Exists "+table_name+"(id int(11) NOT NULL AUTO_INCREMENT, "
		 		+ "uId int(11) NOT NULL,userId char(10) NOT NULL, userName varchar(50) DEFAULT '', "
		 		+ "floor int(11) DEFAULT '0',ANum int(11) DEFAULT '0',BNum int(11) DEFAULT '0',state tinyint default '0',"
		 		+ "time datetime DEFAULT NULL, entryTime datetime DEFAULT NULL, PRIMARY KEY (id), UNIQUE KEY id(id),"
		 		+ "index(userId),index(floor)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		stmt = conn.createStatement();
     	stmt.executeUpdate(sql);
     	
     	b=true;
		 } catch (SQLException e) {
				try {
					conn.rollback();
					b =false;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					b =false;
				}
				e.printStackTrace();
				b =false;
			}
		 return b;
				}
	 
	 public synchronized User getSaveUser(Connection conn, String userId) {
			User user = new User();
			try {
				String sql = "select * from users where userId ='" + userId
						+ "' for update";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					user.setId(rs.getInt("id"));
					user.setUserId(StringUtil.notNull(rs.getString("userId")));
					user.setUserName(StringUtil.notNull(rs.getString("userName")));
					user.setNode(StringUtil.notNull(rs.getString("node")));
					user.setRefereeNode(StringUtil.notNull(rs
							.getString("refereeNode")));
					user.setRefereeAll(StringUtil.notNull(rs
							.getString("refereeAll")));
					user.setDeclarationNode(StringUtil.notNull(rs
							.getString("declarationNode")));
					user.setRankJoin(rs.getInt("rankJoin"));
					user.setRankJoinOld(rs.getInt("rankJoinOld"));
					user.setNodeABC(StringUtil.notNull(rs.getString("nodeABC")));
					user.setUserByAId(StringUtil.notNull(rs.getString("userByAId")));
					user.setUserByBId(StringUtil.notNull(rs.getString("userByBId")));
					user.setUserByCId(StringUtil.notNull(rs.getString("userByCId")));
					user.setEmoney(rs.getDouble("emoney"));
					user.setSmoney(rs.getDouble("smoney"));
					user.setDmoney(rs.getDouble("dmoney"));
					user.setRmoney(rs.getDouble("rmoney"));
					user.setPmoney(rs.getDouble("pmoney"));
					user.setFund(rs.getDouble("fund"));
					user.setIntegral(rs.getDouble("integral"));
					user.setValidtyTime(rs.getTimestamp("validtyTime"));
					user.setRefereeNum(rs.getInt("refereeNum"));
					user.setTeam(rs.getString("team"));
					user.setUserByBelongId(rs.getString("userByBelongId"));
					user.setTeamName(rs.getString("team_name"));
					user.setUserByRefereeId(rs.getString("userByRefereeId"));
					user.setUserByDeclarationId(rs.getString("userByDeclarationId"));
					user.setNodeTag(rs.getInt("nodeTag"));
					user.setState(rs.getInt("state"));
					user.setAddNum(rs.getInt("addNum"));
					user.setEmptyNum(rs.getInt("emptyNum"));
					user.setShopNum(rs.getInt("shopNum"));
					user.setCenterType(rs.getInt("centerType"));
					user.setCenterId(rs.getInt("centerId"));
					user.setEntryTime(rs.getTimestamp("entryTime"));
					user.setIsEmpty(rs.getInt("isEmpty"));
					user.setViewNum(rs.getInt("view_num"));
					user.setViewTime(rs.getTimestamp("view_time"));
					user.setListTag(rs.getInt("list_tag"));
					user.setImgTag(rs.getInt("img_tag"));
					user.setBackFill(rs.getDouble("back_fill"));
					user.setBackFillReach(rs.getDouble("back_fill_reach"));
					user.setBackFillTag(rs.getInt("back_fill_tag"));
					user.setIsBelongList(rs.getInt("is_belong_list"));
					user.setIsRefereeList(rs.getInt("is_referee_list"));
					user.setIsDeclaration(rs.getInt("is_declaration"));
					user.setIsUserList(rs.getInt("is_user_list"));
				} else
					user = null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			return user;
		}
	 
	 public boolean checkNodeTag(int nodeTag, User user) {
			boolean b = false;
			if (nodeTag == 1 && user.getUserByAId().equals(""))
				b = true;
			else if (nodeTag == 2 && user.getUserByBId().equals(""))
				b = true;
			else if (nodeTag == 3 && user.getUserByCId().equals(""))
				b = true;
			return b;
		}
	 
	 public int getUId(Connection conn) throws SQLException {
			int uid = 0;
			if (check_card_pool(conn)) {
				uid = get_card_id(conn);
			} else {
				add_card_pool(conn);
				uid = get_card_id(conn);
			}
			return uid;
		}
		
		public Integer[] getUIdList(Connection conn) throws SQLException {
			Integer[] uid = {0,0,0};
			if (check_card_pool(conn)) {
				uid = get_card_id_list(conn);
			} else {
				add_card_pool(conn);
				uid = get_card_id_list(conn);
			}
			return uid;
		}

		public boolean check_card_pool(Connection conn) throws SQLException {
			String sql = "select count(*) from uidpool";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int num = 0;
			if (rs.next()) {
				num = rs.getInt(1);
			}
			if (num < 3)
				return false;
			else
				return true;
		}

		public int get_card_id(Connection conn) throws SQLException {
			String sql = "select * from uidpool where tag='0' order by id asc for update";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int num = 0;
			int id = 0;
			if (rs.next()) {
				num = rs.getInt("uid");
				id = rs.getInt("id");
				String sqld = "update uidpool set tag ='1' where id='" + id + "'";
				stmt = conn.createStatement();
				stmt.executeUpdate(sqld);
			} else {
				add_card_pool(conn);
				num = get_card_id(conn);
			}
			return num;
		}
		
		public Integer[] get_card_id_list(Connection conn) throws SQLException {
			String sql = "select * from uidpool where tag='0' order by id asc limit 3 for update";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			Integer[] num = {0,0,0};
			int id = 0;
			int i=0;
			while (rs.next()) {
				num[i] = rs.getInt("uid");
				id = rs.getInt("id");
				i++;
				System.out.println("i:"+i);
				String sqld = "update uidpool set tag ='1' where id='" + id + "'";
				stmt = conn.createStatement();
				stmt.executeUpdate(sqld);
			}
			return num;
		}

		public synchronized void add_card_pool(Connection conn) throws SQLException {
			int maxId = 0;
			String sqld1 = "select max(uid) from uidpool";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqld1);
			if (rs.next()) {
				maxId = rs.getInt(1);
			} else {
				maxId = 10001;
			}
			if (maxId == 0)
				maxId = 10001;
			stmt = conn.createStatement();
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < 3000; i++) {
				list.add(maxId + i);
			}
			int out = 0;
			int outIndex = 0;
			for (int i = 0; i < 3000; i++) {
				Random ran = new Random();
				outIndex = ran.nextInt(list.size());
				out = list.get(outIndex);
				list.remove(outIndex);
				String sqli = "insert into uidpool(uid) values('" + (out + 1)
						+ "')";
				stmt.addBatch(sqli);
			}
			String sqld = "delete from uidpool where tag='1'";
			stmt.addBatch(sqld);
			stmt.executeBatch();
		}
		
		public String getRefereeNode(User user) {
			String node = "";
			if (user.getRefereeNode().equals(""))
				node = String.valueOf(user.getId());
			else
				node = user.getRefereeNode() + "," + String.valueOf(user.getId());
			return node;
		}

		public String getDeclarationNode(User user) {
			String node = "";
			if (user.getDeclarationNode().equals(""))
				node = String.valueOf(user.getId());
			else
				node = user.getDeclarationNode() + ","
						+ String.valueOf(user.getId());
			return node;
		}

		public String getNode(User user) {
			String node = "";
			if (user.getNode().equals(""))
				node = String.valueOf(user.getId());
			else
				node = user.getNode() + "," + String.valueOf(user.getId());
			return node;
		}

		public  String getNodeABC(User user, int nodeTag) {
			String node = "";
			String nodeStr = "";
			if (nodeTag == 1)
				nodeStr = "A";
			else if (nodeTag == 2)
				nodeStr = "B";
			else if (nodeTag == 3)
				nodeStr = "C";
			if (user.getNodeABC().equals(""))
				node = nodeStr;
			else
				node = user.getNodeABC() + "," + nodeStr;
			return node;
		}
}
