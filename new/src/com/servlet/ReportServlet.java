package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.db.DBConn;
import com.pojo.Admin;
import com.pojo.User;
import com.pojo.WSettle;
import com.service.CustomService;
import com.service.ICustomService;
import com.utils.ArithUtil;
import com.utils.Pager;
import com.utils.StringUtil;

public class ReportServlet extends HttpServlet {

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
		
		}else if(method.equals("admin_report_wsettle")){
			admin_report_wsettle_list(request,response);
			
		}else if(method.equals("admin_report_wsettle_1")){
			admin_report_wsettle_list_1(request,response);
			
		}else if(method.equals("report_wsettle")){
			report_wsettle_list(request,response);
			
		}else if(method.equals("report_wsettle_1")){
		report_wsettle_list_1(request,response);
		
	}else if(method.equals("admin_report_order")){
		admin_report_order(request,response);
		
	}
}
	
	
	@SuppressWarnings("rawtypes")
	public void admin_report_wsettle_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			conn = db.getConnection();
			if(admin!=null){
				if(db.createConn()){
		String weekTagStr = request.getParameter("weekTag");
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		 pageNo = 1;
		pageSize = 10;
		int weekTag = 0;
		int weekTag1 = 0;
		if(StringUtil.notNull(weekTagStr).equals(""))weekTag = 0;
		else weekTag = Integer.valueOf(weekTagStr);
		
			String sqlu = "select max(weekTag) from settle";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlu);
			if(rs.next()){
				weekTag1 = ((int) rs.getInt(1)/7)+1;
			}
		if(weekTag==0)  weekTag = weekTag1;
		List<Integer> slist = new ArrayList<Integer>();
		for(int i=weekTag1;i>0;i--){
			slist.add(i);
			}
		request.setAttribute("slist", slist);
		request.setAttribute("userId", userId);
		request.setAttribute("weekTag", weekTag);
	if(weekTag>0){
		//	String sql = "select ws.* from wsettle_"+st.getWeekTag()+" where userId in(select userId from users where userByRefereeId='"+id+"'";
		String r_table_name = "wsettle_"+weekTag;
		
		String sqld =  "select * from "+r_table_name+" where userId like '%"+userId+"%' or userName like'%"+userId+"%' order by uId asc";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqld);
		List<WSettle> wlist = new ArrayList<WSettle>();
		while(rs.next()){
			WSettle wst = new WSettle();
			wst.setUserId(rs.getString("userId"));
			wst.setUserName(rs.getString("userName"));
			wst.setTotalPerformance(rs.getDouble("totalPerformance"));
			wst.setNewPerformance(rs.getDouble("newPerformance"));
			wst.setTotalPrice(rs.getDouble("totalPrice"));
			wst.setNewPrice(rs.getDouble("newPrice"));
			wst.setApv(rs.getDouble("apv"));
			wst.setAcpv(rs.getDouble("acpv"));
			wst.setAtpv(rs.getDouble("atpv"));
			wst.setBcpv(rs.getDouble("bcpv"));
			wst.setBpv(rs.getDouble("bpv"));
			wst.setBspv(rs.getDouble("bspv"));
			wst.setRtpv(rs.getDouble("rtpv"));
			wst.setRpv(rs.getDouble("rpv"));
			wst.setRank(rs.getInt("rank"));
			wst.setTNPv(rs.getDouble("tnpv"));
			wst.setTPv(rs.getDouble("tpv"));
			wst.setIsQualify(rs.getInt("is_qualify"));
			wst.setStartTime(rs.getTimestamp("startTime"));
			wst.setEndTime(rs.getTimestamp("endTime"));
			wlist.add(wst);
		}
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
	}
		}
				}else{
					request.setAttribute("message", "数据库连接已断开，请稍后再操作！");
				}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("report_wsettle_list.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	@SuppressWarnings("rawtypes")
	public void admin_report_wsettle_list_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		try {
			conn = db.getConnection();
			if(admin!=null){
				if(db.createConn()){
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String pageNoStr = request.getParameter("pageNoStr");
		String pageSizeStr = request.getParameter("pageSizeStr");
		 pageNo = 1;
		pageSize = 10;
		
		request.setAttribute("userId", userId);
	
		//	String sql = "select ws.* from wsettle_"+st.getWeekTag()+" where userId in(select userId from users where userByRefereeId='"+id+"'";

		String r_table_name = "wsettle";
		
		String sqld =  "select * from "+r_table_name+" where userId like '%"+userId+"%' or userName like'%"+userId+"%' order by uId asc";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqld);
		List<WSettle> wlist = new ArrayList<WSettle>();
		while(rs.next()){
			WSettle wst = new WSettle();
			wst.setUserId(rs.getString("userId"));
			wst.setUserName(rs.getString("userName"));
			wst.setTotalPerformance(rs.getDouble("totalPerformance"));
			wst.setNewPerformance(rs.getDouble("newPerformance"));
			wst.setTotalPrice(rs.getDouble("totalPrice"));
			wst.setNewPrice(rs.getDouble("newPrice"));
			wst.setApv(rs.getDouble("apv"));
			wst.setAcpv(rs.getDouble("acpv"));
			wst.setAtpv(rs.getDouble("atpv"));
			wst.setBcpv(rs.getDouble("bcpv"));
			wst.setBpv(rs.getDouble("bpv"));
			wst.setBspv(rs.getDouble("bspv"));
			wst.setRtpv(rs.getDouble("rtpv"));
			wst.setRpv(rs.getDouble("rpv"));
			wst.setTNPv(rs.getDouble("tnpv"));
			wst.setTPv(rs.getDouble("tpv"));
			wst.setIsQualify(rs.getInt("is_qualify"));
			wst.setStartTime(rs.getTimestamp("startTime"));
			wst.setEndTime(rs.getTimestamp("endTime"));
			wlist.add(wst);
		}
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
		}
				}else{
					request.setAttribute("message", "数据库连接已断开，请稍后再操作！");
				}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("report_wsettle_list_1.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	@SuppressWarnings("rawtypes")
	public void report_wsettle_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		try {
			conn = db.getConnection();
			if(user!=null){
				if(db.createConn()){
					
		 pageNo = 1;
		pageSize = 10;
	
		//	String sql = "select ws.* from wsettle_"+st.getWeekTag()+" where userId in(select userId from users where userByRefereeId='"+id+"'";

		String r_table_name = "wsettle";
		
		String sqld =  "select * from "+r_table_name+" where userId='"+user.getUserId()+"'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqld);
		List<WSettle> wlist = new ArrayList<WSettle>();
		while(rs.next()){
			WSettle wst = new WSettle();
			wst.setUserId(rs.getString("userId"));
			wst.setUserName(rs.getString("userName"));
			wst.setTotalPerformance(rs.getDouble("totalPerformance"));
			wst.setNewPerformance(rs.getDouble("newPerformance"));
			wst.setTotalPrice(rs.getDouble("totalPrice"));
			wst.setNewPrice(rs.getDouble("newPrice"));
			wst.setApv(rs.getDouble("apv"));
			wst.setAcpv(rs.getDouble("acpv"));
			wst.setAtpv(rs.getDouble("atpv"));
			wst.setBcpv(rs.getDouble("bcpv"));
			wst.setBpv(rs.getDouble("bpv"));
			wst.setBspv(rs.getDouble("bspv"));
			wst.setRtpv(rs.getDouble("rtpv"));
			wst.setRpv(rs.getDouble("rpv"));
			wst.setTNPv(rs.getDouble("tnpv"));
			wst.setTPv(rs.getDouble("tpv"));
			wst.setIsQualify(rs.getInt("is_qualify"));
			wst.setStartTime(rs.getTimestamp("startTime"));
			wst.setEndTime(rs.getTimestamp("endTime"));
			wlist.add(wst);
		}
		Collection coll = new ArrayList();
		if(wlist.size()>0){
			int startIndex = pageSize*(pageNo-1);
			int endIndex = pageSize*pageNo;
			if(wlist.size()<endIndex) endIndex = wlist.size();
			coll = wlist.subList(startIndex, endIndex);
			Pager pager = new Pager(pageSize,pageNo,wlist.size(),coll);
			request.setAttribute("pager", pager);
		}
		}
				}else{
					request.setAttribute("message", "数据库连接已断开，请稍后再操作！");
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			request.setAttribute("message", "数据操作有误，请重试！");
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("report_wsettle_list.jsp");
			dispatcher.forward(request, response);
		}
		}
	
	@SuppressWarnings("rawtypes")
	public void report_wsettle_list_1(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		DBConn db = new DBConn();
		try {
			conn = db.getConnection();
			if(user!=null){
				if(db.createConn()){
		 pageNo = 1;
		pageSize = 10;
		int weekTag = 0;
	String sql = "select max(weekTag) from settle";
	stmt = conn.createStatement();
	rs= stmt.executeQuery(sql);
	if(rs.next()){
		weekTag = ((int) rs.getInt(1)/7)+1;
	}
		//	String sql = "select ws.* from wsettle_"+st.getWeekTag()+" where userId in(select userId from users where userByRefereeId='"+id+"'";
if(weekTag>0){
		String r_table_name = "wsettle_"+weekTag;
		
		String sqld =  "select * from "+r_table_name+" where userId='"+user.getUserId()+"'";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sqld);
		List<WSettle> wlist = new ArrayList<WSettle>();
		while(rs.next()){
			WSettle wst = new WSettle();
			wst.setUserId(rs.getString("userId"));
			wst.setUserName(rs.getString("userName"));
			wst.setTotalPerformance(rs.getDouble("totalPerformance"));
			wst.setNewPerformance(rs.getDouble("newPerformance"));
			wst.setTotalPrice(rs.getDouble("totalPrice"));
			wst.setNewPrice(rs.getDouble("newPrice"));
			wst.setApv(rs.getDouble("apv"));
			wst.setAcpv(rs.getDouble("acpv"));
			wst.setAtpv(rs.getDouble("atpv"));
			wst.setBcpv(rs.getDouble("bcpv"));
			wst.setBpv(rs.getDouble("bpv"));
			wst.setBspv(rs.getDouble("bspv"));
			wst.setRtpv(rs.getDouble("rtpv"));
			wst.setRpv(rs.getDouble("rpv"));
			wst.setRank(rs.getInt("rank"));
			wst.setTNPv(rs.getDouble("tnpv"));
			wst.setTPv(rs.getDouble("tpv"));
			wst.setIsQualify(rs.getInt("is_qualify"));
			wst.setStartTime(rs.getTimestamp("startTime"));
			wst.setEndTime(rs.getTimestamp("endTime"));
			wst.setWeekTag(weekTag);
			wlist.add(wst);
		}
		Collection coll = new ArrayList();
		if(wlist.size()>0){
			int startIndex = pageSize*(pageNo-1);
			int endIndex = pageSize*pageNo;
			if(wlist.size()<endIndex) endIndex = wlist.size();
			coll = wlist.subList(startIndex, endIndex);
			Pager pager = new Pager(pageSize,pageNo,wlist.size(),coll);
			request.setAttribute("pager", pager);
		}
	}
		}
				}else{
					request.setAttribute("message", "数据库连接已断开，请稍后再操作！");
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			request.setAttribute("message", "数据操作有误，请重试！");
		}finally{
			db.close();
			RequestDispatcher dispatcher = request.getRequestDispatcher("report_wsettle_list_1.jsp");
			dispatcher.forward(request, response);
		}
		}

	public void admin_report_order(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		DBConn db = new DBConn();
		String message ="";
		try {
			conn = db.getConnection();
			if(admin!=null){
				if(db.createConn()){
					String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
					String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		double[] sum={0,0,0,0,0,0,0,0,0};
		
			String sql1 = "";
			String sql2 = "";
			String sql3 = "";
			String sql4= "";
			if(!startTimeStr.equals("")){
				String startTime = startTimeStr+" 00:00:00";
			if(!endTimeStr.equals("")){
				String  endTime = endTimeStr+" 23:59:59";
				sql1 = "select sum(price),sum(pv) from orders where (orderType='1' or orderType='5') and state>='1' and orderTime>='"+startTime+"' and orderTime<='"+endTime+"'";
				sql2 = "select sum(price),sum(pv) from orders where orderType='2' and state>='1' and orderTime>='"+startTime+"' and orderTime<='"+endTime+"'";
				sql3 = "select sum(price),sum(pv) from orders where orderType='3' and state>='1' and orderTime>='"+startTime+"' and orderTime<='"+endTime+"'";
				sql4 = "select sum(price),sum(pv) from orders where orderType='4' and state>='1' and orderTime>='"+startTime+"' and orderTime<='"+endTime+"'";
			}else{
				sql1 = "select sum(price),sum(pv) from orders where (orderType='1' or orderType='5') and state>='1' and orderTime>='"+startTime+"'";
				sql2 = "select sum(price),sum(pv) from orders where orderType='2' and state>='1' and orderTime>='"+startTime+"'";
				sql3 = "select sum(price),sum(pv) from orders where orderType='3' and state>='1' and orderTime>='"+startTime+"'";
				sql4 = "select sum(price),sum(pv) from orders where orderType='4' and state>='1' and orderTime>='"+startTime+"'";
			}
			}else{
				sql1 = "select sum(price),sum(pv) from orders where (orderType='1' or orderType='5') and state>='1'";
				sql2 = "select sum(price),sum(pv) from orders where orderType='2' and state>='1'";
				sql3 = "select sum(price),sum(pv) from orders where orderType='3' and state>='1'";
				sql4 = "select sum(price),sum(pv) from orders where orderType='4' and state>='1'";
			}
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			if(rs.next()){
				sum[0] = rs.getDouble(1);
				sum[1] = rs.getDouble(2);
			}
			stmt.close();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			if(rs.next()){
				sum[2] = rs.getDouble(1);
				sum[3] = rs.getDouble(2);
			}
			stmt.close();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql3);
			if(rs.next()){
				sum[4] = rs.getDouble(1);
				sum[5] = rs.getDouble(2);
			}
			stmt.close();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql4);
			if(rs.next()){
				sum[6] = rs.getDouble(1);
				sum[7] = rs.getDouble(2);
			}
			sum[8]=ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(ArithUtil.add(sum[0],sum[1]),sum[2]),sum[3]),sum[4]),sum[5]),sum[6]),sum[7]);
			request.setAttribute("startTime",startTimeStr);
			request.setAttribute("endTime",endTimeStr);
			request.setAttribute("sum", sum);
		}else{
			message="数据库连接已断开，请稍后再操作！";
		}
				}else{
					message= "用户未登陆，请重新登陆！";
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			request.setAttribute("message", "数据操作有误，请重试！");
		}finally{
			db.close();
			if(message.equals("")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_order_summary.jsp");
				dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_order_message.jsp");
				dispatcher.forward(request, response);
			}
		}
		}
}
