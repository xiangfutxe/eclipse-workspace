package com.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import net.sf.json.JSONObject;

import com.db.DBConn;
import com.pojo.Admin;
import com.pojo.ChargeApply;
import com.pojo.Param;
import com.pojo.Settle;
import com.pojo.User;
import com.pojo.WReward;
import com.pojo.WSettle;
import com.service.CustomService;
import com.service.ICustomService;
import com.utils.ArithUtil;
import com.utils.Constants;
import com.utils.DateUtil;
import com.utils.MD5;
import com.utils.Pager;
import com.utils.StringUtil;

public class RewardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	private int pageNo = 1;
	
	private int pageSize = 10;
	
	ICustomService cs = new CustomService();

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doGet(request,response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String method = (String) request.getParameter("method");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if(method==null){
			PrintWriter out = response.getWriter();
			out.println("invalid request");
		
		}else if(method.equals("admin_list")){
			admin_list(request,response);
			
		}else if(method.equals("admin_reward_week")){
			admin_reward_week(request,response);
			
		}else if(method.equals("admin_reward_week_0")){
			admin_reward_week_0(request,response);
			
		}else if(method.equals("admin_reward_week_confirm")){
			admin_reward_week_confirm(request,response);
			
		}else if(method.equals("admin_reward_week_reset")){
			try {
				admin_reward_week_reset(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}else if(method.equals("admin_reward_week_summary")){
			admin_reward_week_summary(request,response);
			
		}else if(method.equals("reward_week_summary")){
			reward_week_summary(request,response);
			
		}else if(method.equals("exportExcel")){
			exportExcel(request,response);
		}else if(method.equals("admin_reward_week_payoff")){
			admin_reward_week_payoff(request,response);
			
		}
}
	
	
	@SuppressWarnings("rawtypes")
	public void admin_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr =  StringUtil.notNull(request.getParameter("endTime"));
		String type =  StringUtil.notNull(request.getParameter("type"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		 int  pageNo = 1;
		int  pageSize = 20;
		String sql ="";
		request.setAttribute("userId", userId);
		request.setAttribute("startTime", startTimeStr);
		request.setAttribute("endTime", endTimeStr);
		request.setAttribute("type", type);
		if(!(startTimeStr.equals("")||endTimeStr.equals(""))){
			String startTime = startTimeStr+" 00:00:00";
			String  endTime = endTimeStr+" 23:59:59";
			sql = "select * from wreward where type like'%"+type+"%' and entry_time>='"+startTime+"' and entry_time<='"+endTime+"' and state>'0' and user_id like'%"+userId+"' order by id desc";
			
		}else{
			sql = "select * from wreward where  type like'%"+type+"%' and state>'0' and user_id like'%"+userId+"' order by id desc";
		}
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		List<WReward> wlist = new ArrayList<WReward>();
		double sum = 0;
		while(rs.next()){
			WReward wrd = new WReward();
			wrd.setUid(rs.getInt("u_id"));
			wrd.setUserId(rs.getString("user_id"));
			wrd.setUserName(rs.getString("user_name"));
			wrd.setUserById(rs.getInt("user_by_id"));
			wrd.setUserByUserId(rs.getString("user_by_user_id"));
			wrd.setUserByUserName(rs.getString("user_by_user_name"));
			wrd.setAmount(rs.getDouble("amount"));
			wrd.setType(rs.getInt("type"));
			wrd.setState(rs.getInt("state"));
			wrd.setRemark(rs.getString("remark"));
			wrd.setEntryTime(rs.getTimestamp("entry_time"));
			wlist.add(wrd);
			sum = ArithUtil.add(sum, wrd.getAmount());
		}
		request.setAttribute("wrd_sum", sum);
		Collection coll = new ArrayList();
		if(wlist.size()>0){
			if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
			if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
			int startIndex = pageSize*(pageNo-1);
			int endIndex = pageSize*pageNo;
			if(wlist.size()<endIndex) endIndex = wlist.size();
			coll = wlist.subList(startIndex, endIndex);
			Pager pager = new Pager(pageSize,pageNo,wlist.size(),coll);
			request.setAttribute("pager", pager);
		
		}
				}else{
					request.setAttribute("message", "数据库连接已断开，请稍后再操作！");
				}
			}else{
				request.setAttribute("message", "用户未登录，请重新登陆！");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_week_list.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	
	public synchronized void admin_reward_week(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		int stag = 0;
			try {
				if(admin!=null){
				if(db.createConn()){
				conn = db.getConnection();
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			String sql ="select * from settle order by week_tag desc";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				int weekTag = rs.getInt("week_tag");
				Timestamp startTime = rs.getTimestamp("start_time");
				Timestamp endTime = rs.getTimestamp("end_time");
				int state = rs.getInt("state");
				if(weekTag==0){
					stag=2;
					request.setAttribute("tag", stag);
					request.setAttribute("startTime", StringUtil.parseToString(startTime, DateUtil.yyyyMMddHHmmss));
					request.setAttribute("state", String.valueOf(state));
					request.setAttribute("weekTag", weekTag);
				}else{
					if(state==2){
						stag=2;
						request.setAttribute("tag", stag);
						request.setAttribute("startTime", StringUtil.parseToString(endTime, DateUtil.yyyyMMddHHmmss));
						request.setAttribute("state", String.valueOf(state));
						request.setAttribute("weekTag", weekTag);
					}else{
						stag=1;
						request.setAttribute("startTime", StringUtil.parseToString(startTime, DateUtil.yyyyMMddHHmmss));
						request.setAttribute("endTime", StringUtil.parseToString(endTime, DateUtil.yyyyMMddHHmmss));
						request.setAttribute("message","当前结算周期：" + StringUtil.parseToString(startTime, DateUtil.yyyyMMddHHmmss)+ "至"
								+ StringUtil.parseToString(endTime, DateUtil.yyyyMMddHHmmss));
						request.setAttribute("state", String.valueOf(state));
						request.setAttribute("tag", stag);
						request.setAttribute("weekTag", weekTag);
					}
				}
			}
			
				}else{
					request.setAttribute("message1", "数据库连接已断开，请稍后再操作！");
				}
		}else{
			request.setAttribute("message1", "管理员用户未登陆，请重新登陆！");
		}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("message1", "数据操作有误，请核实！");
		}finally{
			db.close();
				RequestDispatcher dispatcher = request.getRequestDispatcher("reward_week.jsp");
				dispatcher.forward(request, response);
			
		}
		}
	
	public Settle getST(Settle st, Settle st1) throws SQLException{
		if (st1 == null)
			return st;
		else if (st1.getState()==0){
			Settle st2 = cs.getSettle(conn, "end_time",st1.getStartTime(),"");
			return getST(st1, st2);
		}else if (st1.getState()<=1)
			return st1;
		else return st;
		
	}
	
	public synchronized void admin_reward_week_0(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String token_old = (String) request.getSession().getAttribute("token");
		String token = (String) request.getParameter("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
		
		try {
			if (StringUtil.notNull(token_old).equals(token)) {
				Timestamp date1 = new Timestamp(new Date().getTime());
				java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
					Param p = cs.getParam(conn, "结算参数");
					if(p!=null){
					 String weekstr = request.getParameter("weekTag");
					 int weekTag =Integer.valueOf(weekstr);
					 String startTimeStr = request.getParameter("startTime");
					 String endTimeStr = request.getParameter("endTime");
					 
				
		String r_table_name = "wreward_"+String.valueOf(weekTag+1);
		System.out.println("r_table_name:"+r_table_name);
		rs = conn.getMetaData().getTables(null, null, r_table_name, null );
		boolean b = true;
		if(!rs.next()){
			b=cs.createWReward(conn,r_table_name);
		}
		if(b){
		String sqlm = "select max(id) from userinfo where entry_time<'"+endTimeStr+"'";	
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqlm);
		int num = 0;
		if(rs.next()){
			num = rs.getInt(1);
		}
		User[] user = new User[num+1];
		WReward[] wrd = new WReward[num+1];

	String sqlu = "select * from userinfo where entry_time<'"+endTimeStr+"'  and state='2' order by id desc";
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sqlu);
	while(rs.next()){
		int id= rs.getInt("id");
		if(user[id]==null) user[id] = new User();
		user[id].setId(id);
		user[id].setUserId(rs.getString("user_id")); 
		user[id].setUserName(rs.getString("user_name"));
		user[id].setRankJoin(rs.getInt("rank_join"));
		user[id].setEntryTime(rs.getTimestamp("entry_time"));
		user[id].setTag(0);
		if(wrd[id]==null) wrd[id] = new WReward();
		wrd[id].setUid(id);
		wrd[id].setUserId(rs.getString("user_id"));
		wrd[id].setUserName(rs.getString("user_name"));
		wrd[id].setAmount(0);
		wrd[id].setAmount1(0);
		wrd[id].setAmount2(0);
		wrd[id].setAmount3(0);
		wrd[id].setAmount4(0);
		wrd[id].setTotalAmount(0);
		wrd[id].setTax(0);
		wrd[id].setTag(0);

	}
	double totalPrice = 0;
	double totalAward = 0;
		List<String> slist = new ArrayList<String>();
		String sqlw = "select * from  orders where confirm_time>='"+startTimeStr+"' and confirm_time<'"+endTimeStr+"' and state>='2'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqlw);
		System.out.println(sqlw);	
		while(rs.next()){
			totalPrice = ArithUtil.add(totalPrice, rs.getDouble("price"));
		}
		String sqlj = "select * from wreward where entry_time<'"+endTimeStr+"' and entry_time>='"+startTimeStr+"' and state='1' order by id asc";
		System.out.println(sqlj);		
		stmt = conn.createStatement();
				 rs = stmt.executeQuery(sqlj);
				while(rs.next()){
					int uid = rs.getInt("u_id");
					System.out.println("userId:"+uid);		

					double amount =rs.getDouble("amount"); 
					int type=rs.getInt("type");
					if(wrd[uid]!=null){
						wrd[uid].setTotalAmount(ArithUtil.add(wrd[uid].getTotalAmount(), amount));
						
						if(type==1){
							wrd[uid].setAmount1(ArithUtil.add(wrd[uid].getAmount1(), amount));
							
						}else if(type==2){
							wrd[uid].setAmount2(ArithUtil.add(wrd[uid].getAmount2(), amount));
							
						}
					wrd[uid].setTag(1);
					totalAward = ArithUtil.add(totalAward,amount);
					}
				}
					
				
				String sql2 = "insert into settle(total_price,total_award,week_tag,state,start_time,"
						+ "end_time,entry_time) values('"
						+totalPrice+"','"+totalAward+"','"+(weekTag+1)+"','1','"
						+startTimeStr+"','"+endTimeStr+"','"+date+"');";
				slist.add(sql2);
				for(int i=1;i<wrd.length;i++){
					if(wrd[i]!=null){
						if(wrd[i].getTag()==1){
							wrd[i].setTax(ArithUtil.mul(wrd[i].getTotalAmount(), p.getAmount_1()));
							wrd[i].setAmount(ArithUtil.sub(wrd[i].getTotalAmount(), wrd[i].getTax()));
							String sql3="insert into "+r_table_name+"(u_id,user_id,user_name,amount,amount_1,amount_2,total_amount,tax,state,start_time,end_time,entry_time) values('"
									+wrd[i].getUid()+"','"+wrd[i].getUserId()+"','"+wrd[i].getUserName()+"','"+wrd[i].getAmount()
									+"','"+wrd[i].getAmount1()+"','"+wrd[i].getAmount2()+"','"+wrd[i].getTotalAmount()+"','"+wrd[i].getTax()+"','1','"+startTimeStr+"','"+endTimeStr+"','"+date+"');";
							slist.add(sql3);
						}
					}
				}
				
				if(slist.size()>0){
					 stmt = conn.createStatement();
						for(int i=0;i<slist.size();i++){
							stmt.addBatch(slist.get(i));
							 if ((i % 50000 == 0 && i != 0) || i == (slist.size() - 1)) { 
								 stmt.executeBatch();
								 stmt = conn.createStatement();
							 }
						}
				}
				
					
		request.setAttribute("message", "开始进行结算，请从按顺序进行结算！");
		}else{
			request.setAttribute("message", "最新结算周数据表获取失败。");
			
		}}else{
			request.setAttribute("message", "结算参数获取失败。");
			
		}
			 conn.commit();
			 conn.setAutoCommit(autoCommit);
				}else{
					request.setAttribute("message", "数据库连接已断开，请稍后再操作！");
					
				}
		
		}else{
			request.setAttribute("message", "管理员用户未登陆，请重新登陆！");
		}
			} else {
				request.setAttribute("message", "请勿重复提交数据！");
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
			}
			e.printStackTrace();
			request.setAttribute("message", "数据有误，请重试！");
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_week_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	public void admin_reward_week_confirm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
		String weekTag = StringUtil.notNull(request.getParameter("weekTag"));
		List<String> slist = new ArrayList<String>();
		String table_name = "wreward_"+weekTag;
		String sql = "update settle set state='2' where week_tag='"+weekTag+"'";
		slist.add(sql);
		 sql = "update "+table_name+" set state='2'";
		slist.add(sql);
					if(slist.size()>0){
							stmt = conn.createStatement();
							for (int i = 0; i < slist.size(); i++) {
								stmt.addBatch(slist.get(i));
								if ((i % 50000 == 0 && i != 0)
										|| i == (slist.size() - 1)) {
									stmt.executeBatch();
									stmt.close();
									stmt = conn.createStatement();
								}
							}
					}
					request.setAttribute("message", "结算确认成功，请在工资明细中查看。");
				
				
		 conn.commit();
			conn.setAutoCommit(autoCommit);
		
				}else{
					request.setAttribute("message", "数据库连接已断开，请稍后再操作！");
				}
			}else{
				request.setAttribute("message", "用户未登录，请重新登陆！");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_week_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	public void admin_reward_week_reset(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
		String weekTag = StringUtil.notNull(request.getParameter("weekTag"));
		List<String> slist = new ArrayList<String>();
		String table_name = "wreward_"+weekTag;
		String sql = "delete from settle  where week_tag='"+weekTag+"'";
		slist.add(sql);
		 sql = "drop table "+table_name+"";
		slist.add(sql);
		System.out.println(sql);
					if(slist.size()>0){
							stmt = conn.createStatement();
							for (int i = 0; i < slist.size(); i++) {
								stmt.addBatch(slist.get(i));
								if ((i % 50000 == 0 && i != 0)
										|| i == (slist.size() - 1)) {
									stmt.executeBatch();
									stmt.close();
									stmt = conn.createStatement();
								}
							}
					}
					request.setAttribute("message", "结算重置成功，请重新结算。");
				
				
		 conn.commit();
			conn.setAutoCommit(autoCommit);
		
				}else{
					request.setAttribute("message", "数据库连接已断开，请稍后再操作！");
				}
			}else{
				request.setAttribute("message", "用户未登录，请重新登陆！");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_week_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	@SuppressWarnings("rawtypes")
	public void admin_reward_week_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
		String weekTag =  StringUtil.notNull(request.getParameter("weekTag"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		 int  pageNo = 1;
		int  pageSize = 20;
		request.setAttribute("userId", userId);
		
		int week_tag=0;
		
		List<Integer> slist = new ArrayList<Integer>();
		String sqls = "select max(week_tag) from settle";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqls);
		if(rs.next()){
			week_tag=rs.getInt(1);
		}
		for(int i=0;i<week_tag;i++){
			slist.add(i+1);
		}
		if(weekTag.equals(""))weekTag=String.valueOf(week_tag);
		request.setAttribute("weekTag", weekTag);
		request.setAttribute("slist", slist);

		String table_name = "wreward_"+weekTag;
		String sql1 = "select * from "+table_name+" where user_id like'%"+userId+"%'  order by u_id asc";
			
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql1);
		List<WReward> wlist = new ArrayList<WReward>();
		double sum = 0;
		while(rs.next()){
			WReward wrd = new WReward();
			wrd.setId(rs.getInt("id"));
			wrd.setUid(rs.getInt("u_id"));
			wrd.setUserId(rs.getString("user_id"));
			wrd.setUserName(rs.getString("user_name"));
			wrd.setAmount(rs.getDouble("amount"));
			wrd.setAmount1(rs.getDouble("amount_1"));
			wrd.setAmount2(rs.getDouble("amount_2"));
			wrd.setAmount3(rs.getDouble("amount_3"));
			wrd.setAmount4(rs.getDouble("amount_4"));
			wrd.setTotalAmount(rs.getDouble("total_amount"));
			wrd.setTax(rs.getDouble("tax"));
			wrd.setState(rs.getInt("state"));
			wrd.setStartTime(rs.getTimestamp("start_time"));
			wrd.setEndTime(rs.getTimestamp("end_time"));
			wlist.add(wrd);
			sum = ArithUtil.add(sum, wrd.getAmount());
		}
		request.setAttribute("wrd_sum", sum);
		Collection coll = new ArrayList();
		if(wlist.size()>0){
			if(!StringUtil.notNull(pageNoStr).equals("")) pageNo = Integer.valueOf(pageNoStr);
			if(!StringUtil.notNull(pageSizeStr).equals("")) pageSize = Integer.valueOf(pageSizeStr);
			int startIndex = pageSize*(pageNo-1);
			int endIndex = pageSize*pageNo;
			if(wlist.size()<endIndex) endIndex = wlist.size();
			coll = wlist.subList(startIndex, endIndex);
			Pager pager = new Pager(pageSize,pageNo,wlist.size(),coll);
			request.setAttribute("pager", pager);
		
		}
				}else{
					request.setAttribute("message", "数据库连接已断开，请稍后再操作！");
				}
			}else{
				request.setAttribute("message", "用户未登录，请重新登陆！");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_week_summary.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	public void admin_reward_week_payoff(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			
			if(admin!=null){
				if(db.createConn()){
					conn = db.getConnection();
					 boolean autoCommit= conn.getAutoCommit();
					 conn.setAutoCommit(false);
		String[] ids = request.getParameterValues("ids");
		String weekTag = request.getParameter("weekTag");
		if(ids.length>0){
		List<String> slist = new ArrayList<String>();
		String table_name = "wreward_"+weekTag;
		for(int i=0;i<ids.length;i++){
			String sql = "update "+table_name+" set state='3' where id='"+ids[i]+"'";
			slist.add(sql);
		}
		if(slist.size()>0){
			stmt = conn.createStatement();
			for (int i = 0; i < slist.size(); i++) {
				stmt.addBatch(slist.get(i));
				if ((i % 50000 == 0 && i != 0)
						|| i == (slist.size() - 1)) {
					stmt.executeBatch();
					stmt.close();
					stmt = conn.createStatement();
				}
			}
		}
		request.setAttribute("message", "已经发放工资的会员标示成功！");
		 conn.commit();
			conn.setAutoCommit(autoCommit);
		}else{
			request.setAttribute("message", "为选择已经发放工资的会员！");
		}
				}else{
					request.setAttribute("message", "数据库连接已断开，请稍后再操作！");
				}
			}else{
				request.setAttribute("message", "用户未登录，请重新登陆！");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_week_payoff_message.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 
	    DBConn db = new DBConn();
	    String weekTag =  StringUtil.notNull(request.getParameter("weekTag"));
		String userId = StringUtil.notNull(request.getParameter("userId"));
		 String filename = "奖金列表（第"+weekTag+"周）.xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
		try {
			if(db.createConn()){
				conn = db.getConnection();
				
				request.setAttribute("userId", userId);
				int week_tag=0;
				List<Integer> slist = new ArrayList<Integer>();
				String sqls = "select max(week_tag) from settle";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sqls);
				if(rs.next()){
					week_tag=rs.getInt(1);
				}
				for(int i=0;i<week_tag;i++){
					slist.add(i+1);
				}
				if(weekTag.equals(""))weekTag=String.valueOf(week_tag);
				request.setAttribute("weekTag", weekTag);
				request.setAttribute("slist", slist);

				String table_name = "wreward_"+weekTag;
				String sql1 = "select * from "+table_name+" as ws,userinfo as us where ws.user_id=us.user_id and ws.user_id like'%"+userId+"%'  order by ws.u_id asc";
				System.out.println(sql1);
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql1);
				List<WReward> result = new ArrayList<WReward>();
				double sum = 0;
				while(rs.next()){
					WReward wrd = new WReward();
					wrd.setUid(rs.getInt("ws.u_id"));
					wrd.setUserId(rs.getString("ws.user_id"));
					wrd.setUserName(rs.getString("ws.user_name"));
					wrd.setAmount(rs.getDouble("ws.amount"));
					wrd.setAmount1(rs.getDouble("ws.amount_1"));
					wrd.setAmount2(rs.getDouble("ws.amount_2"));
					wrd.setAmount3(rs.getDouble("ws.amount_3"));
					wrd.setAmount4(rs.getDouble("ws.amount_4"));
					wrd.setTotalAmount(rs.getDouble("ws.total_amount"));
					wrd.setTax(rs.getDouble("ws.tax"));
					wrd.setState(rs.getInt("ws.state"));
					wrd.setAccountName(rs.getString("us.account_name"));
					wrd.setAccountId(rs.getString("us.account_id"));
					wrd.setBankName(StringUtil.notNull(rs.getString("us.bank_name"))+StringUtil.notNull(rs.getString("us.bank_adr")));

					wrd.setStartTime(rs.getTimestamp("ws.start_time"));
					wrd.setEndTime(rs.getTimestamp("ws.end_time"));
					
					result.add(wrd);
					sum = ArithUtil.add(sum, wrd.getAmount());
				}
				String[][] content = new String[result.size()+1][14];
				content[0][0]="序号";
				content[0][1]="开始时间";
				content[0][2]="结束时间";
				content[0][3]="会员编号";
				content[0][4]="会员姓名";
				content[0][5]="市场推广费";
				content[0][6]="销售补贴";
				content[0][7]="奖金小计";
				content[0][8]="综合管理费";
				content[0][9]="实发工资";
				content[0][10]="持卡人姓名";
				content[0][11]="银行卡号";
				content[0][12]="开户银行";
				content[0][13]="当前状态";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.parseToString(result.get(i).getStartTime(),DateUtil.yyyyMMddHHmmss);
					content[i+1][2]  = StringUtil.parseToString(result.get(i).getEndTime(),DateUtil.yyyyMMddHHmmss);
					content[i+1][3]  = StringUtil.notNull(result.get(i).getUserId());
					content[i+1][4]  = StringUtil.notNull(result.get(i).getUserName());
					content[i+1][5]  =  StringUtil.decimalFormat(result.get(i).getAmount1());
					content[i+1][6]  =  StringUtil.decimalFormat(result.get(i).getAmount2());
					content[i+1][7]  =  StringUtil.decimalFormat(result.get(i).getTotalAmount());
					content[i+1][8]  =  StringUtil.decimalFormat(result.get(i).getTax());
					content[i+1][9]  =  StringUtil.decimalFormat(result.get(i).getAmount());
					content[i+1][10]  = StringUtil.notNull(result.get(i).getAccountName());
					content[i+1][11]  = StringUtil.notNull(result.get(i).getAccountId());
					content[i+1][12]  = StringUtil.notNull(result.get(i).getBankName());

					String stateStr ="";
					if(result.get(i).getState()==1) stateStr = "结算中";
					else if(result.get(i).getState()==2) stateStr = "待发工资";
					else if(result.get(i).getState()==3) stateStr = "已发工资";
					content[i+1][13] = stateStr;
				}
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("奖金列表");  
				    for(int i=0; i<content.length; i++){  
				        Row row = sheet.createRow(i);  
				        for (int j=0; j<content[i].length; j++) {  
				            row.createCell(j).setCellValue(content[i][j]);  
				        }  
				    }  
				    OutputStream out = response.getOutputStream();  
				    wb.write(out);  
				    out.close();  
			}else {
				request.setAttribute("message", "数据库连接已断开！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			db.close();
		}
	}
	
	public void reward_week_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		try {
			if(user!=null){
				if(db.createConn()){
					conn = db.getConnection();
		String weekTag =  StringUtil.notNull(request.getParameter("weekTag"));
		int week_tag=0;
		List<Integer> slist = new ArrayList<Integer>();
		String sqls = "select max(week_tag) from settle";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqls);
		if(rs.next()){
			week_tag=rs.getInt(1);
		}
		for(int i=0;i<week_tag;i++){
			slist.add(i+1);
		}
		if(weekTag.equals("")) weekTag=String.valueOf(week_tag);
		request.setAttribute("weekTag", weekTag);
		request.setAttribute("slist", slist); 

		String table_name = "wreward_"+weekTag;
		String sql1 = "select * from "+table_name+" where user_id ='"+user.getUserId()+"' and state>'0'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql1);
		WReward wrd = null;
		if(rs.next()){
			 wrd = new WReward();
			 wrd.setId(rs.getInt("id"));
			wrd.setUid(rs.getInt("u_id"));
			wrd.setUserId(rs.getString("user_id"));
			wrd.setUserName(rs.getString("user_name"));
			wrd.setAmount(rs.getDouble("amount"));
			wrd.setAmount1(rs.getDouble("amount_1"));
			wrd.setAmount2(rs.getDouble("amount_2"));
			wrd.setAmount3(rs.getDouble("amount_3"));
			wrd.setAmount4(rs.getDouble("amount_4"));
			wrd.setTotalAmount(rs.getDouble("total_amount"));
			wrd.setTax(rs.getDouble("tax"));
			wrd.setState(rs.getInt("state"));
			wrd.setStartTime(rs.getTimestamp("start_time"));
			wrd.setEndTime(rs.getTimestamp("end_time"));
			
		}
			request.setAttribute("sys_wrd", wrd);
		
				}else{
					request.setAttribute("message", "数据库连接已断开，请稍后再操作！");
				}
			}else{
				request.setAttribute("message", "用户未登录，请重新登陆！");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_week_summary.jsp");
			dispatcher.forward(request, response);
		}
		}
	
}
