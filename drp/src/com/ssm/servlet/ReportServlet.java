package com.ssm.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Row;

import com.ssm.dao.DRewardDao;
import com.ssm.dao.DSettleDao;
import com.ssm.dao.OrderDao;
import com.ssm.dao.ReportDao;
import com.ssm.dao.SettleDao;
import com.ssm.dao.UserDao;
import com.ssm.dao.WRewardDao;
import com.ssm.dao.WSettleDao;
import com.ssm.pojo.AccountDetail;
import com.ssm.pojo.Admin;
import com.ssm.pojo.DReward;
import com.ssm.pojo.DSettle;
import com.ssm.pojo.Order;
import com.ssm.pojo.Settle;
import com.ssm.pojo.User;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WSettle;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;
import com.ssm.utils.ArithUtil;

public class ReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	ICustomService cs = new CustomService();


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String method = (String) request.getParameter("method");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if (method == null) {
			PrintWriter out = response.getWriter();
			out.println("invalid request");

		}else if(method.equals("admin_report_wsettle")){
			admin_report_wsettle_list(request,response);
			
		}else if(method.equals("admin_report_wsettle_1")){
			admin_report_wsettle_list_1(request,response);
			
		}else if(method.equals("report_wsettle")){
			report_wsettle_list(request,response);
		}else if(method.equals("report_wsettle_summary")){
			report_wsettle_summary(request,response);
			
		}else if(method.equals("admin_report_wsettle_summary")){
			admin_report_wsettle_summary(request,response);
			
		}else if(method.equals("exportExcel")){
			exportExcel(request,response);
		}else if(method.equals("admin_report_order")){
			admin_report_order(request,response);
			
		}else if(method.equals("user_team_rankmanage")){
			user_team_rankmanage(request,response);
			
		}else if(method.equals("admin_report_segment_performance")){
			admin_report_segment_performance(request,response);
		}else if(method.equals("admin_report_export_excel_orders")){
			admin_report_export_excel_orders(request,response);
		}else if(method.equals("admin_report_referee_performance")){
			admin_report_referee_performance(request,response);
		}else if(method.equals("admin_report_referee_performance_summary")){
			admin_report_referee_performance_summary(request,response);
		}else if(method.equals("admin_report_export_excel_referee_performance")){
			admin_report_export_excel_referee_performance(request,response);
		}else if(method.equals("admin_report_export_excel_referee_performance_summary")){
			admin_report_export_excel_referee_performance_summary(request,response);
		}else if(method.equals("admin_report_export_referee_user")){
			admin_report_export_referee_user(request,response);
		}else if(method.equals("report_finance_excel")){
			report_finance_excel(request,response);
		}else if(method.equals("admin_reward_week_summary_excel")){
			admin_reward_week_summary_excel(request,response);
		}else if(method.equals("admin_report_money_excel")){
			admin_report_money_excel(request,response);
		}else if(method.equals("admin_report_export_belong_user")){
			admin_report_export_belong_user(request,response);
		}else if(method.equals("admin_report_settle_day")){
			admin_report_settle_day(request,response);
			
		}else if(method.equals("report_settle_day")){
			report_settle_day(request,response);
			
		}else if(method.equals("admin_report_reward_day_excel")){
			admin_report_reward_day_excel(request,response);
			
		}else if(method.equals("admin_report_settle_day_exportExcel")){
			admin_report_settle_day_exportExcel(request,response);
			
		}
	}
	
	public void report_wsettle_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		try {
			if(user!=null){
				
			}
		}finally{
			RequestDispatcher dispatcher = request.getRequestDispatcher("report_wsettle_list_1.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void report_wsettle_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		String message="";
		try {
			if(user!=null){
				String weekTagStr = StringUtil.notNull(request.getParameter("weekTag"));
				int weekTag = 0;
				if(StringUtil.notNull(weekTagStr).equals(""))weekTag = 0;
				else weekTag = Integer.valueOf(weekTagStr);
				SettleDao stDao = new SettleDao();
				int weekTag1 = stDao.maxWeekTag();
				List<Integer> slist = new ArrayList<Integer>();
				for(int i=weekTag1;i>0;i--){
					slist.add(i);
				}
				request.setAttribute("slist", slist);
				if(StringUtil.notNull(weekTagStr).equals(""))weekTag = 0;
				else weekTag = Integer.valueOf(weekTagStr);
				if(weekTag==0){
					weekTag=weekTag1;
				}
				if(weekTag>0){
				request.setAttribute("weekTag",String.valueOf(weekTag));
				String table_name = "wsettle_"+weekTag;
				WSettleDao wstDao = new WSettleDao();
				WSettle wst = wstDao.findByUserId(user.getUserId(), table_name,weekTag);
				if(wst!=null){
					request.setAttribute("wst", wst);
				}else{
					message = "暂无数据。";
				}
				}else{
					message = "暂无数据。";
				}

			}else{
				message = "会员未登录。";
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("report_wsettle_list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_report_wsettle_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message="";
		try {
			if(admin!=null){
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo = 1;
				int pageSize = 20;
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				request.setAttribute("userId",userId);
				String table_name = "wsettle";
				WSettleDao wstDao = new WSettleDao();
				WSettle wst = new WSettle();
				wst.setUserId(userId);
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = wstDao.findByPage(wst, table_name, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId", userId);
			}else{
				message = "用户未登录。";
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("report_wsettle_summary.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void admin_report_wsettle_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String weekTagStr = StringUtil.notNull(request.getParameter("weekTag"));
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			int weekTag = 0;
			if(StringUtil.notNull(weekTagStr).equals(""))weekTag = 0;
			else weekTag = Integer.valueOf(weekTagStr);
			SettleDao stDao = new SettleDao();
			int weekTag1 = stDao.maxWeekTag();
			List<Integer> slist = new ArrayList<Integer>();
			for(int i=weekTag1;i>0;i--){
				slist.add(i);
			}
			request.setAttribute("slist", slist);
			if(StringUtil.notNull(weekTagStr).equals(""))weekTag = 0;
			else weekTag = Integer.valueOf(weekTagStr);
			if(weekTag==0){
				weekTag=weekTag1;
			}
			request.setAttribute("weekTag",String.valueOf(weekTag));
			if(weekTag>0){
				String tableName ="wsettle_"+weekTag;
			
			WSettle wst = new WSettle();
			
			WSettleDao wstDao = new WSettleDao();
			wst.setUserId(userId);
			wst.setWeekTag(weekTag);
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = wstDao.findByPage(wst, tableName, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId", userId);
			}else{
				message = "未获取到响应到结算信息。";
			}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("report_wsettle_list.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	
	public void admin_report_wsettle_list_1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			SettleDao stDao = new SettleDao();
			Settle st = stDao.findByWeekTag(0);
			List<Integer> slist = new ArrayList<Integer>();
			UserDao userDao = new UserDao();
			User user1 = new User();
			int num = userDao.maxId(user1)+1;
			if(num>1){
			userDao = new UserDao();
			List<User> ulist = userDao.findUserByList(user1);
			User[] ustr = new User[num];
			WSettle[] wstr = new WSettle[num];
			for(int i=0;i<ulist.size();i++){
				if(ulist.get(i).getRankJoin()>0){
					int id = ulist.get(i).getId();
					if(ustr[id]==null) ustr[id] = new User();
					ustr[id].setNode(ulist.get(i).getNode());
					ustr[id].setNodeABC(ulist.get(i).getNodeABC());
					ustr[id].setRefereeNode(ulist.get(i).getRefereeNode());
					if(wstr[id]==null) wstr[id] = new WSettle();
					wstr[id].setUserId(ulist.get(i).getUserId());
					wstr[id].setUserName(ulist.get(i).getUserName());
					wstr[id].setTotalPerformance((double) 0);
					wstr[id].setTotalPrice((double) 0);
					wstr[id].setNewPerformance((double) 0);
					wstr[id].setNewPrice((double) 0);
					wstr[id].setApv((double) 0);
					wstr[id].setAcpv((double) 0);
					wstr[id].setAprice((double) 0);
					wstr[id].setAtpv((double) 0);
					wstr[id].setAtprice((double) 0);
					wstr[id].setBpv((double) 0);
					wstr[id].setBcpv((double) 0);
					wstr[id].setBprice((double) 0);
					wstr[id].setBtpv((double) 0);
					wstr[id].setBtprice((double) 0);
					wstr[id].setRpv((double) 0);
					wstr[id].setRprice((double) 0);
					wstr[id].setRtpv((double) 0);
					wstr[id].setRtprice((double) 0);
					wstr[id].setRpv1((double) 0);
					wstr[id].setRprice1((double) 0);
					wstr[id].setRtpv1((double) 0);
					wstr[id].setRtprice1((double) 0);
				}
			}
			WSettleDao wstDao= new WSettleDao();
			WSettle wst1 = new WSettle();
			wst1.setState(2);
			List<WSettle> wlist = wstDao.findWstByList(wst1);
			for(int i=0;i<wlist.size();i++){
				int id =wlist.get(i).getuId();
				if(wstr[id]!=null){
					wstr[id].setId(wlist.get(i).getId());
					wstr[id].setuId(id);
					wstr[id].setTotalPerformance(wlist.get(i).getTotalPerformance());
					wstr[id].setTotalPrice(wlist.get(i).getTotalPrice());
					wstr[id].setAcpv(wlist.get(i).getAcpv());
					wstr[id].setAtpv(wlist.get(i).getAtpv());
					wstr[id].setAtprice(wlist.get(i).getAtprice());
					wstr[id].setBcpv(wlist.get(i).getBcpv());
					wstr[id].setBtpv(wlist.get(i).getBtpv());
					wstr[id].setBtprice(wlist.get(i).getBtprice());
					wstr[id].setRtpv(wlist.get(i).getRtpv());
					wstr[id].setRtprice(wlist.get(i).getRtprice());
					wstr[id].setRtpv1(wlist.get(i).getRtpv1());
					wstr[id].setRtprice1(wlist.get(i).getRtprice1());
				}
			}
			OrderDao orderDao = new OrderDao();
			Order order =new Order();
			order.setStartTime(st.getEndTime());
			List<Order> olist = orderDao.findByList(order);
			for(int i=0;i<olist.size();i++){
				if(olist.get(i).getPrice()>0){
					int orderType = olist.get(i).getOrderType();
					double price = olist.get(i).getPrice();
					double pv = olist.get(i).getPv();
					int id = olist.get(i).getuId();
					if(ustr[id]!=null){
						if(orderType==1||orderType==5){
							String[] str1 = ustr[id].getNode().split(",");
							String[] str2 = ustr[id].getNodeABC().split(",");
							for(int j=0;j<str1.length;j++){
								if(!str1[j].equals("")){
									int sid = Integer.valueOf(str1[j]);
										wstr[sid].setNewPerformance(ArithUtil.add(wstr[sid].getNewPerformance(), pv));
										wstr[sid].setNewPrice(ArithUtil.add(wstr[sid].getNewPrice(), price));
										if(str2[j].equals("A")){
											wstr[sid].setApv(ArithUtil.add(wstr[sid].getApv(),pv));
											wstr[sid].setAprice(ArithUtil.add(wstr[sid].getAprice(),price));
										}else if(str2[j].equals("B")){
											wstr[sid].setBpv(ArithUtil.add(wstr[sid].getBpv(),pv));
											wstr[sid].setBprice(ArithUtil.add(wstr[sid].getBprice(),price));
										}
									
								}
							}
						
						String[] str3 = ustr[id].getRefereeNode().split(",");
						for(int j=0;j<str3.length;j++){
							if(!str3[j].equals("")){
								int sid = Integer.valueOf(str3[j]);
									wstr[sid].setRpv(ArithUtil.add(wstr[sid].getRpv(), pv));
									wstr[sid].setRprice(ArithUtil.add(wstr[sid].getRprice(), price));
							}
						}
						}else if(orderType==2||orderType==3){
							String[] str3 = ustr[id].getRefereeNode().split(",");
							for(int j=0;j<str3.length;j++){
								if(!str3[j].equals("")){
									int sid = Integer.valueOf(str3[j]);
										wstr[sid].setRpv1(ArithUtil.add(wstr[sid].getRpv1(), pv));
										wstr[sid].setRprice1(ArithUtil.add(wstr[sid].getRprice1(), price));
								}
							}
						}
					
					}
				}
			}
			List<WSettle> result = new ArrayList<WSettle>();
			for(int i=0;i<wstr.length;i++){
				if(wstr[i]!=null) result.add(wstr[i]);
			}
			 if (!StringUtil.notNull(pageNoStr).equals(""))
					pageNo = Integer.valueOf(pageNoStr);
				if (!StringUtil.notNull(pageSizeStr).equals(""))
					pageSize = Integer.valueOf(pageSizeStr);
				int startIndex = pageSize * (pageNo - 1);
				int endIndex = pageSize * pageNo;
				   if (result.size() > 0) {
						
						if (result.size() < endIndex)
							endIndex = result.size();
						
						
				 List<WSettle> coll = result.subList(startIndex, endIndex);
				 Pager pager = new Pager();
				 pager.setStartIndex(startIndex);
				 pager.setEndIndex(endIndex);
				 pager.setPageNo(pageNo);
				 pager.setPageSize(pageSize);
				 pager.setRowCount(result.size());
				 pager.setResultList(coll);
				 request.setAttribute("pager", pager);
					request.setAttribute("userId", userId);
				   }else{
						message = "未获取到相应到结算信息。";
					}
			}else{
				message = "未获取到相应到结算信息。";
			}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("report_wsettle_list_1.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
		
		public void exportExcel(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());

			String weekTagStr = request.getParameter("weekTag");
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = "第"+weekTagStr+"期结算明细"+str+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			 List<WSettle> result = new ArrayList<WSettle>();
			String message = "";
				try {
					if(admin!=null){
						
						int weekTag=0;
						if(StringUtil.notNull(weekTagStr).equals(""))weekTag = 0;
						else weekTag = Integer.valueOf(weekTagStr);
						String tableName= "wsettle_"+weekTag;
						WSettleDao wstDao = new WSettleDao();
						WSettle wst= new WSettle();
						wst.setUserId(userId);
						wst.setWeekTag(weekTag);
						result = wstDao.findByList(wst,tableName);
						
			
						String[][] content = new String[result.size()+2][20];
						content[0][0]="序号";
						content[0][1]="日期";
						content[0][2]="会员编号";
						content[0][3]="会员名称";
						content[0][4]="区域总累计";
						content[0][5]="期初累计";
						content[0][6]="区域新增";
						content[0][7]="A区总累计";
						content[0][8]="A区期初累计";
						content[0][9]="A区新增";
						content[0][10]="A区盈余";
						content[0][11]="B区总累计";
						content[0][12]="B区期初累计";
						content[0][13]="B区新增";
						content[0][14]="B区盈余";
						content[0][15]="销售新增业绩";
						content[0][16]="销售期初业绩";
						content[0][17]="销售总业绩";
						content[0][18]="加盟级别";
						content[0][19]="会员奖衔";
						for(int i=0;i<result.size();i++){
							content[i+1][0]  = String.valueOf(i+1);
							content[i+1][1]  = StringUtil.parseToString(result.get(i).getStartTime(), DateUtil.yyyyMMdd)+"至"+StringUtil.parseToString(result.get(i).getEndTime(), DateUtil.yyyyMMdd);;
							content[i+1][2]  = StringUtil.notNull(result.get(i).getUserId());
							content[i+1][3]  = StringUtil.notNull(result.get(i).getUserName());
							content[i+1][4]  = StringUtil.decimalFormat(result.get(i).getTotalPerformance()+result.get(i).getNewPerformance());
							content[i+1][5]  = StringUtil.decimalFormat(result.get(i).getTotalPerformance());
							content[i+1][6]  = StringUtil.decimalFormat(result.get(i).getNewPerformance());
							content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getAtpv()+result.get(i).getApv());
							content[i+1][8]  = StringUtil.decimalFormat(result.get(i).getAtpv());
							content[i+1][9]  = StringUtil.decimalFormat(result.get(i).getApv());
							content[i+1][10]  = StringUtil.decimalFormat(result.get(i).getAcpv());
							content[i+1][11]  = StringUtil.decimalFormat(result.get(i).getBtpv()+result.get(i).getBpv());
							content[i+1][12]  = StringUtil.decimalFormat(result.get(i).getBtpv());
							content[i+1][13]  = StringUtil.decimalFormat(result.get(i).getBpv());
							content[i+1][14]  = StringUtil.decimalFormat(result.get(i).getBcpv());
							content[i+1][15]  = StringUtil.decimalFormat(result.get(i).getRpv()+result.get(i).getRpv1());
							content[i+1][16]  = StringUtil.decimalFormat(result.get(i).getRtpv()+result.get(i).getRtpv1());
							content[i+1][17]  = StringUtil.decimalFormat(ArithUtil.add(result.get(i).getRpv()+result.get(i).getRpv1(), result.get(i).getRtpv()+ result.get(i).getRtpv1()));
							String rankJoinstr = "";
							if(result.get(i).getRankJoin()==1)rankJoinstr="银卡会员";
							else if(result.get(i).getRankJoin()==2)rankJoinstr="金卡会员";
							else if(result.get(i).getRankJoin()==3)rankJoinstr="白金卡会员";
							else if(result.get(i).getRankJoin()==4)rankJoinstr="VIP会员";
							else if(result.get(i).getRankJoin()==5)rankJoinstr="至尊会员";
							content[i+1][18]  =rankJoinstr;
							String rank="";
							int rankManage = result.get(i).getRank();
							if (rankManage == 1)
								rank = rank + "一级经理";
							else if (rankManage == 2)
								rank = rank + "二级经理";
							else if (rankManage == 3)
								rank = rank + "三级经理";
							else if (rankManage== 4)
								rank = rank + "四级经理";
							else if (rankManage == 5)
								rank = rank + "五级经理";
							else if (rankManage == 6)
								rank = rank + "一星钻石";
							else if (rankManage == 7)
								rank = rank + "二星钻石";
							else if (rankManage == 8)
								rank = rank + "三星钻石";
							else if (rankManage == 9)
								rank = rank + "四星钻石";
							else if (rankManage == 10)
								rank = rank + "五星钻石";
							else if (rankManage== 11)
								rank = rank + "董事";
							else
								rank = rank + "-";
							content[i+1][19]  = rank;
						}
						
						 HSSFWorkbook wb = new HSSFWorkbook();  
						 HSSFSheet sheet = wb.createSheet("区域业绩汇总");  
					    for(int i=0; i<content.length; i++){  
					        Row row = sheet.createRow(i);  
					        for (int j=0; j<content[i].length; j++) {  
					            row.createCell(j).setCellValue(content[i][j]);  
					        }  
					    }  
					    OutputStream out = response.getOutputStream();  
					    wb.write(out);  
					    out.close();  
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} 
		}
		
		public void admin_report_order(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
			String message="";
			try {
				if(admin!=null){
					String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
					String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
					Double[] sum= new Double[11];
					for(int i=0;i<sum.length;i++){
						sum[i] = (double) 0;
					}
					Order order1 = new Order();

					if(!startTimeStr.equals("")){
						Timestamp  startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
						order1.setStartTime(startTime);
					}
					if(!endTimeStr.equals("")){
						Timestamp  endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
						order1.setEndTime(endTime);

					}
					OrderDao orderDao = new OrderDao();
					List<Order> olist = orderDao.findByList(order1);
					if(olist!=null){
						for(int i=0;i<olist.size();i++){
							double price =  olist.get(i).getPrice();
							double pv = olist.get(i).getPv();
							int orderType = olist.get(i).getOrderType();
							if(orderType==1||orderType==5){
								sum[0] = ArithUtil.add(sum[0], price);
								sum[1] = ArithUtil.add(sum[1], pv);
								
							}else if(orderType==2){
								sum[2] = ArithUtil.add(sum[2], price);
								sum[3] = ArithUtil.add(sum[3], pv);
							}else if(orderType==3){
								sum[4] = ArithUtil.add(sum[4], price);
								sum[5] = ArithUtil.add(sum[5], pv);
							}else if(orderType==4){
								sum[6] = ArithUtil.add(sum[6], price);
								sum[7] = ArithUtil.add(sum[7], pv);
							}
							sum[8] = ArithUtil.add(sum[8], price);
							sum[9] = ArithUtil.add(sum[9], pv);
						}
					}else{
						message = "暂无数据。";
					}
					request.setAttribute("startTime",startTimeStr);
					request.setAttribute("endTime",endTimeStr);
					request.setAttribute("sum", sum);
				}else{
					message = "会员未登录。";
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_order_summary.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		public void user_team_rankmanage(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
			String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
			int pageNo = 1;
			int pageSize = 20;
			String message = "";
			try {
				if(admin!=null){
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				
				UserDao userDao = new UserDao();
				User user = userDao.findByUserId(userId);
				userDao = null;
				if(user!=null){
					UserDao uDao = new UserDao();
					List<User> ulist = new ArrayList<User>();

					List<User> userlist = uDao.findUserBySql(" state>0 ");
					if(userlist!=null){
						for(int i=0;i<userlist.size();i++){
							if(!StringUtil.notNull(userlist.get(i).getRefereeNode()).equals("")){
							String[] str  = userlist.get(i).getRefereeNode().split(",");
							for(int j=str.length;j>0;j--){
								if(str[j-1].equals(String.valueOf(user.getId()))){
									if(userlist.get(i).getRankManage()>0)
									ulist.add(userlist.get(i));
									j=0;
								}
							}
							}
						}
					}
					Pager pager= new Pager();
					pager.setPageNo(pageNo);
					pager.setPageSize(pageSize);
					pager.setRowCount(ulist.size());
					int startIndex = pageSize*(pageNo-1);
					int endIndex  = pageSize*pageNo;
					if(endIndex>ulist.size()) endIndex = ulist.size();
					List<User> result = ulist.subList(startIndex, endIndex);
					pager.setResultList(result);
					request.setAttribute("pager", pager);
					request.setAttribute("userId", userId);
				}else{
					message = "未获取到响应到结算信息。";
				}
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("user_team_rankmanage.jsp");
					dispatcher.forward(request, response);
				
			}
		}
		
		public synchronized void admin_report_segment_performance(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message="";
			try {
				if(admin!=null){
					String userId = StringUtil.notNull(request.getParameter("userId"));
					String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
					String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
					String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
					String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
					int pageNo = 1;
					int pageSize = 20;
					if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
					if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
					request.setAttribute("userId",userId);
					String table_name = "wsettle";
					ReportDao wstDao = new ReportDao();
					WSettle wst = new WSettle();
					wst.setUserId(userId);
					if(!startTimeStr.equals("")){
						Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
						wst.setStartTime(startTime);
					}
					if(!endTimeStr.equals("")){
						Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
						wst.setEndTime(endTime);
					}
					 wstDao.findPerformanceByWst(request, wst);
					request.setAttribute("userId", userId);
					request.setAttribute("startTime", startTimeStr);
					request.setAttribute("endTime", endTimeStr);
				}else{
					message = "用户未登录。";
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_segment_performance.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		public synchronized void admin_report_referee_performance(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message="";
			try {
				if(admin!=null){
					String userId = StringUtil.notNull(request.getParameter("userId"));
					String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
					String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
					String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
					String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
					int pageNo = 1;
					int pageSize = 20;
					if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
					if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
					request.setAttribute("userId",userId);
					ReportDao wstDao = new ReportDao();
					WSettle wst = new WSettle();
					wst.setUserId(userId);
					if(!startTimeStr.equals("")){
						Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
						wst.setStartTime(startTime);
					}
					if(!endTimeStr.equals("")){
						Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
						wst.setEndTime(endTime);
					}
					List<WSettle> result =  wstDao.findRefereePerformance(request, wst);
					 if (!StringUtil.notNull(pageNoStr).equals(""))
							pageNo = Integer.valueOf(pageNoStr);
						if (!StringUtil.notNull(pageSizeStr).equals(""))
							pageSize = Integer.valueOf(pageSizeStr);
						int startIndex = pageSize * (pageNo - 1);
						int endIndex = pageSize * pageNo;
						   if (result.size() > 0) {
								if (result.size() < endIndex)
									endIndex = result.size();
						 List<WSettle> coll = result.subList(startIndex, endIndex);
						 Pager pager = new Pager();
						 pager.setStartIndex(startIndex);
						 pager.setEndIndex(endIndex);
						 pager.setPageNo(pageNo);
						 pager.setPageSize(pageSize);
						 pager.setRowCount(result.size());
						 pager.setResultList(coll);
						 request.setAttribute("pager", pager);
					}
					request.setAttribute("userId", userId);
					request.setAttribute("startTime", startTimeStr);
					request.setAttribute("endTime", endTimeStr);
				}else{
					message = "用户未登录。";
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_referee_performance.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		public synchronized void admin_report_referee_performance_summary(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String message="";
			try {
				if(admin!=null){
					String userId = StringUtil.notNull(request.getParameter("userId"));
					String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
					String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
					String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
					String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
					int pageNo = 1;
					int pageSize = 20;
					if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
					if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
					ReportDao wstDao = new ReportDao();
					WSettle wst = new WSettle();
					wst.setUserId(userId);
					if(!startTimeStr.equals("")){
						Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
						wst.setStartTime(startTime);
					}
					if(!endTimeStr.equals("")){
						Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
						wst.setEndTime(endTime);
					}
					List<WSettle> result =  wstDao.findRefereePerformanceSummary(request, wst);
					 if (!StringUtil.notNull(pageNoStr).equals(""))
							pageNo = Integer.valueOf(pageNoStr);
						if (!StringUtil.notNull(pageSizeStr).equals(""))
							pageSize = Integer.valueOf(pageSizeStr);
						int startIndex = pageSize * (pageNo - 1);
						int endIndex = pageSize * pageNo;
						   if (result.size() > 0) {
								if (result.size() < endIndex)
									endIndex = result.size();
						 List<WSettle> coll = result.subList(startIndex, endIndex);
						 Pager pager = new Pager();
						 pager.setStartIndex(startIndex);
						 pager.setEndIndex(endIndex);
						 pager.setPageNo(pageNo);
						 pager.setPageSize(pageSize);
						 pager.setRowCount(result.size());
						 pager.setResultList(coll);
						 request.setAttribute("pager", pager);
					}
					request.setAttribute("userId", userId);
					request.setAttribute("startTime", startTimeStr);
					request.setAttribute("endTime", endTimeStr);
				}else{
					message = "用户未登录。";
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_referee_performance_summary.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		
		
		public void admin_report_export_excel_orders(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());

			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = userId+"伞下会员报单订单.xls"
			 		+ "";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			 List<Order> result = new ArrayList<Order>();
			String message = "";
				try {
					if(admin!=null){
						ReportDao redDao = new ReportDao();
						result = redDao.findOrdersByUserId(userId);
						String[][] content = new String[result.size()+2][20];
						content[0][0]="序号";
						content[0][1]="订单日期";
						content[0][2]="订单编号";
						content[0][3]="订单金额";
						content[0][4]="订单PV";
						content[0][5]="订单类型";
						content[0][6]="会员编号";
						content[0][7]="会员名称";
						content[0][8]="所属服务店";
						content[0][9]="当前状态";
							double[] sum = {0,0};
						for(int i=0;i<result.size();i++){
							content[i+1][0]  = String.valueOf(i+1);
							content[i+1][1]  = StringUtil.parseToString(result.get(i).getOrderTime(), DateUtil.yyyyMMddHHmmss);
							content[i+1][2]  = StringUtil.notNull(result.get(i).getOrderId());
							content[i+1][3]  = StringUtil.decimalFormat2(result.get(i).getPrice());
							content[i+1][4]  = StringUtil.decimalFormat2(result.get(i).getPv());
							String type="";
							if(result.get(i).getOrderType()==0) type = "-";
							else if(result.get(i).getOrderType()==1) type = "报单购物";
							else if(result.get(i).getOrderType()==2) type = "零售购物";
							else if(result.get(i).getOrderType()==3) type = "复消购物";
							else if(result.get(i).getOrderType()==4) type = "购物券购物";
							else if(result.get(i).getOrderType()==5) type = "升级购物";
							content[i+1][5]  = StringUtil.notNull(type);
							content[i+1][6]  = StringUtil.notNull(result.get(i).getUserId());
							content[i+1][7]  = StringUtil.notNull(result.get(i).getUserName());
							content[i+1][8]  =  StringUtil.notNull(result.get(i).getUserByCenterId());
							String state_str ="";
							if(result.get(i).getState()==0) state_str = "已退单";
							else if(result.get(i).getState()==1) state_str = "待确认";
							else if(result.get(i).getState()==2) state_str = "待配货";
							else if(result.get(i).getState()==3) state_str = "配货中";
							else if(result.get(i).getState()==4) state_str = "出库中";
							else if(result.get(i).getState()==5) state_str = "已发货";
							else if(result.get(i).getState()==6) state_str = "已收货";
							content[i+1][9] = state_str;
							sum[0] = ArithUtil.add(sum[0],result.get(i).getPrice());
							sum[1] = ArithUtil.add(sum[1],result.get(i).getPv());
						}
						content[result.size()+1][0]  = "-";
						content[result.size()+1][1]  = "-";
						content[result.size()+1][2]  = "合计";
						
						content[result.size()+1][3]  = StringUtil.decimalFormat(sum[0]);
						content[result.size()+1][4]  = StringUtil.decimalFormat(sum[1]);
						content[result.size()+1][5]  = "-";
						content[result.size()+1][6]  = "-";
						content[result.size()+1][7]  = "-";
						content[result.size()+1][8]  = "-";
						content[result.size()+1][9]  = "-";
						 HSSFWorkbook wb = new HSSFWorkbook();  
						 HSSFSheet sheet = wb.createSheet(userId+"伞下会员报单订单");  
					    for(int i=0; i<content.length; i++){  
					        Row row = sheet.createRow(i);  
					        for (int j=0; j<content[i].length; j++) {  
					            row.createCell(j).setCellValue(content[i][j]);  
					        }  
					    }  
					    OutputStream out = response.getOutputStream();  
					    wb.write(out);  
					    out.close();  
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} 
		}
		
		public void admin_report_export_excel_referee_performance(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = "会员直推业绩汇总"+str+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			 List<WSettle> result = new ArrayList<WSettle>();
			String message = "";
				try {
					if(admin!=null){
						String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
						String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
						request.setAttribute("userId",userId);
						ReportDao wstDao = new ReportDao();
						WSettle wst = new WSettle();
						wst.setUserId(userId);
						if(!startTimeStr.equals("")){
							Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
							wst.setStartTime(startTime);
						}
						if(!endTimeStr.equals("")){
							Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
							wst.setEndTime(endTime);
						}
						result =  wstDao.findRefereePerformance(request, wst);
						String[][] content = new String[result.size()+2][20];
						content[0][0]="序号";
						content[0][1]="时间段";
						content[0][2]="会员编号";
						content[0][3]="会员名称";
						content[0][4]="报单金额";
						content[0][5]="报单业绩";
						content[0][6]="零售金额";
						content[0][7]="零售业绩";
						content[0][8]="复消金额";
						content[0][9]="复消业绩";
						content[0][10]="金额小计";
						content[0][11]="业绩小计";
						for(int i=0;i<result.size();i++){
							content[i+1][0]  = String.valueOf(i+1);
							content[i+1][1]  = StringUtil.parseToString(result.get(i).getStartTime(), DateUtil.yyyyMMddHHmmss)+"-"+StringUtil.parseToString(result.get(i).getEndTime(), DateUtil.yyyyMMddHHmmss);
							content[i+1][2]  = StringUtil.notNull(result.get(i).getUserId());
							content[i+1][3]  = StringUtil.notNull(result.get(i).getUserName());

							content[i+1][4]  = StringUtil.decimalFormat2(result.get(i).getRprice());
							content[i+1][5]  = StringUtil.decimalFormat2(result.get(i).getRpv());
							content[i+1][6]  = StringUtil.decimalFormat2(result.get(i).getAprice());
							content[i+1][7]  = StringUtil.decimalFormat2(result.get(i).getApv());
							content[i+1][8]  = StringUtil.decimalFormat2(result.get(i).getBprice());
							content[i+1][9]  = StringUtil.decimalFormat2(result.get(i).getBpv());
							content[i+1][10]  = StringUtil.decimalFormat2(result.get(i).getTotalPrice());
							content[i+1][11]  = StringUtil.decimalFormat2(result.get(i).getTotalPerformance());
						
						}
					
						 HSSFWorkbook wb = new HSSFWorkbook();  
						 HSSFSheet sheet = wb.createSheet("会员直推业绩汇总");  
					    for(int i=0; i<content.length; i++){  
					        Row row = sheet.createRow(i);  
					        for (int j=0; j<content[i].length; j++) {  
					            row.createCell(j).setCellValue(content[i][j]);  
					        }  
					    }  
					    OutputStream out = response.getOutputStream();  
					    wb.write(out);  
					    out.close();  
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} 
		}
		
		public void admin_report_export_excel_referee_performance_summary(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());

			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = "会员伞下推荐业绩汇总"+str+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			 List<WSettle> result = new ArrayList<WSettle>();
			String message = "";
				try {
					if(admin!=null){
						String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
						String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
						request.setAttribute("userId",userId);
						ReportDao wstDao = new ReportDao();
						WSettle wst = new WSettle();
						wst.setUserId(userId);
						if(!startTimeStr.equals("")){
							Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
							wst.setStartTime(startTime);
						}
						if(!endTimeStr.equals("")){
							Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
							wst.setEndTime(endTime);
						}
						result =  wstDao.findRefereePerformanceSummary(request, wst);
						String[][] content = new String[result.size()+2][20];
						content[0][0]="序号";
						content[0][1]="时间段";
						content[0][2]="会员编号";
						content[0][3]="会员名称";
						content[0][4]="报单金额";
						content[0][5]="报单业绩";
						content[0][6]="零售金额";
						content[0][7]="零售业绩";
						content[0][8]="复消金额";
						content[0][9]="复消业绩";
						content[0][10]="金额小计";
						content[0][11]="业绩小计";
						for(int i=0;i<result.size();i++){
							content[i+1][0]  = String.valueOf(i+1);
							content[i+1][1]  = StringUtil.parseToString(result.get(i).getStartTime(), DateUtil.yyyyMMddHHmmss)+"-"+StringUtil.parseToString(result.get(i).getEndTime(), DateUtil.yyyyMMddHHmmss);
							content[i+1][2]  = StringUtil.notNull(result.get(i).getUserId());
							content[i+1][3]  = StringUtil.notNull(result.get(i).getUserName());

							content[i+1][4]  = StringUtil.decimalFormat2(result.get(i).getRprice());
							content[i+1][5]  = StringUtil.decimalFormat2(result.get(i).getRpv());
							content[i+1][6]  = StringUtil.decimalFormat2(result.get(i).getAprice());
							content[i+1][7]  = StringUtil.decimalFormat2(result.get(i).getApv());
							content[i+1][8]  = StringUtil.decimalFormat2(result.get(i).getBprice());
							content[i+1][9]  = StringUtil.decimalFormat2(result.get(i).getBpv());
							content[i+1][10]  = StringUtil.decimalFormat2(result.get(i).getTotalPrice());
							content[i+1][11]  = StringUtil.decimalFormat2(result.get(i).getTotalPerformance());
						
						}
					
						 HSSFWorkbook wb = new HSSFWorkbook();  
						 HSSFSheet sheet = wb.createSheet("会员伞下推荐业绩汇总");  
					    for(int i=0; i<content.length; i++){  
					        Row row = sheet.createRow(i);  
					        for (int j=0; j<content[i].length; j++) {  
					            row.createCell(j).setCellValue(content[i][j]);  
					        }  
					    }  
					    OutputStream out = response.getOutputStream();  
					    wb.write(out);  
					    out.close();  
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} 
		}
		
		
		public void admin_report_export_excel_money(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str_time = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = "电子币余额汇总表"+str_time+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			
			List<User>  result = new ArrayList<User> ();

			String message = "";
			try {
				if(admin!=null){
					 String userId = StringUtil.notNull(request.getParameter("userId"));
					   request.setAttribute("userId", userId);
					 
						UserDao userDao = new UserDao();
						User user1 = userDao.findByUserId(userId);
						if(user1!=null){
						 String[] str = StringUtil.notNull(user1.getRefereeNode()).split(",");
						 String sql="";
						 for(int i=str.length;i>0;i--){
							 if(sql.equals("")) sql = "("+str[i-1];
							 else sql+=","+str[i-1];
						 }
						 if(!sql.equals("")) {
							 sql=" id in "+sql+") order by id desc";
					
								 userDao = new UserDao();
								result = userDao.findUserBySql(sql);
						 }
						}
					
					
						String[][] content = new String[result.size()+1][7];
						content[0][0]="位置";
						content[0][1]="会员编号";
						content[0][2]="会员名称";
						content[0][3]="会员等级";
						content[0][4]="管理级别";
						content[0][5]="注册时间";
						content[0][6]="当前状态";
						
						for(int i=0;i<result.size();i++){
							content[i+1][0]  = String.valueOf(i+1);
							content[i+1][1]  = StringUtil.notNull(result.get(i).getUserId());
							content[i+1][2]  = StringUtil.notNull(result.get(i).getUserName());
							String rankstr="";
							if(result.get(i).getRankJoin()==0) rankstr = "-";
							else if(result.get(i).getRankJoin()==1) rankstr = "银卡会员";
							else if(result.get(i).getRankJoin()==2) rankstr = "金卡会员";
							else if(result.get(i).getRankJoin()==3) rankstr = "白金卡会员";
							else if(result.get(i).getRankJoin()==4) rankstr = "VIP会员";
							else if(result.get(i).getRankJoin()==5) rankstr = "至尊会员";
							content[i+1][3]  = StringUtil.notNull(rankstr);
							String managestr="";
							if(result.get(i).getRankJoin()==0) managestr = "-";
							else if(result.get(i).getRankManage()==1) managestr = "一星经理";
							else if(result.get(i).getRankManage()==2) managestr = "二星经理";
							else if(result.get(i).getRankManage()==3) managestr = "三星经理";
							else if(result.get(i).getRankManage()==4) managestr = "四星经理";
							else if(result.get(i).getRankManage()==5) managestr = "五星经理";
							else if(result.get(i).getRankManage()==6) managestr = "一星钻石";
							else if(result.get(i).getRankManage()==7) managestr = "二星钻石";
							else if(result.get(i).getRankManage()==8) managestr = "三星钻石";
							else if(result.get(i).getRankManage()==9) managestr = "四星钻石";
							else if(result.get(i).getRankManage()==10) managestr = "五星钻石";
							else if(result.get(i).getRankManage()==11) managestr = "董事";
							content[i+1][4]  = StringUtil.notNull(managestr);
							
							content[i+1][5] = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
							
							String stateStr ="";
							if(result.get(i).getState()==0) stateStr = "下线";
							else if(result.get(i).getState()==1) stateStr = "在线";
							content[i+1][6] = stateStr;
							
						}
						 HSSFWorkbook wb = new HSSFWorkbook();  
						 HSSFSheet sheet = wb.createSheet("会员服务关系反向列表");  
					    for(int i=0; i<content.length; i++){  
					        Row row = sheet.createRow(i);  
					        for (int j=0; j<content[i].length; j++) {  
					            row.createCell(j).setCellValue(content[i][j]);  
					        }  
					    }  
					    OutputStream out = response.getOutputStream();  
					    wb.write(out);  
					    out.close();  
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} 
		}
		
		public void admin_report_export_referee_user(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str_time = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String userId = StringUtil.notNull(request.getParameter("userId"));
			 String filename = userId+"伞下会员信息"+str_time+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			
			List<User>  result = new ArrayList<User> ();

			String message = "";
			try {
				if(admin!=null){
					   request.setAttribute("userId", userId);
					 
						UserDao userDao = new UserDao();
						result = userDao.findRefereeListByUserId(userId);
						
						String[][] content = new String[result.size()+1][30];
						content[0][0]="序号";
						content[0][1]="会员编号";
						content[0][2]="会员名称";
						content[0][3]="会员等级";
						content[0][4]="管理级别";
						content[0][5]="销售商";
						content[0][6]="销售区域";
						content[0][7]="服务商";
						content[0][8]="报单人";
						content[0][9]="证件类型";
						content[0][10]="证件号码";
						content[0][11]="联系电话";
						content[0][12]="报单券余额";
						content[0][13]="购物券余额";
						content[0][14]="复消券余额";
						content[0][15]="奖金券余额";
						content[0][16]="电子券余额";
						content[0][17]="电子券有效期";
						content[0][18]="银行账户信息";
						content[0][19]="注册时间";
						content[0][20]="空单标示";
						content[0][21]="奖金状态";
						content[0][22]="当前状态";
						
						for(int i=0;i<result.size();i++){
							content[i+1][0]  = String.valueOf(i+1);
							content[i+1][1]  = StringUtil.notNull(result.get(i).getUserId());
							content[i+1][2]  = StringUtil.notNull(result.get(i).getUserName());
							String rankstr="";
							if(result.get(i).getRankJoin()==0) rankstr = "-";
							else if(result.get(i).getRankJoin()==1) rankstr = "银卡会员";
							else if(result.get(i).getRankJoin()==2) rankstr = "金卡会员";
							else if(result.get(i).getRankJoin()==3) rankstr = "白金会员";
							else if(result.get(i).getRankJoin()==4) rankstr = "VIP会员";
							else if(result.get(i).getRankJoin()==5) rankstr = "至尊会员";
							content[i+1][3]  = StringUtil.notNull(rankstr);
							String managestr="";
							if(result.get(i).getRankManage()==0) managestr = "-";
							else if(result.get(i).getRankManage()==1) managestr = "一星经理";
							else if(result.get(i).getRankManage()==2) managestr = "二星经理";
							else if(result.get(i).getRankManage()==3) managestr = "三星经理";
							else if(result.get(i).getRankManage()==4) managestr = "四星经理";
							else if(result.get(i).getRankManage()==5) managestr = "五星经理";
							else if(result.get(i).getRankManage()==6) managestr = "一星钻石";
							else if(result.get(i).getRankManage()==7) managestr = "二星钻石";
							else if(result.get(i).getRankManage()==8) managestr = "三星钻石";
							else if(result.get(i).getRankManage()==9) managestr = "四星钻石";
							else if(result.get(i).getRankManage()==10) managestr = "五星钻石";
							else if(result.get(i).getRankManage()==11) managestr = "董事";
							content[i+1][4]  = StringUtil.notNull(managestr);
							content[i+1][5]  =  StringUtil.notNull(result.get(i).getUserByBelongId());
							String nodestr = "";
							if(result.get(i).getNodeTag()==1) nodestr="A区";
							else if(result.get(i).getNodeTag()==2) nodestr="B区";
							else if(result.get(i).getNodeTag()==3) nodestr="C区";
							else nodestr="-";
							content[i+1][6]  = StringUtil.notNull(nodestr);
							content[i+1][7]  = StringUtil.notNull(result.get(i).getUserByRefereeId());
							content[i+1][8]  = StringUtil.notNull(result.get(i).getUserByDeclarationId());
							content[i+1][9] = StringUtil.notNull(result.get(i).getDocumentType());
							content[i+1][10] = StringUtil.notNull(result.get(i).getNumId());
							content[i+1][11]  = StringUtil.notNull(result.get(i).getTel());
							content[i+1][12]  = StringUtil.decimalFormat(result.get(i).getEmoney());
							content[i+1][13]  = StringUtil.decimalFormat(0);
							content[i+1][14]  = StringUtil.decimalFormat(result.get(i).getDmoney());
							content[i+1][15]  = StringUtil.decimalFormat(result.get(i).getRmoney());
							content[i+1][16]  = StringUtil.decimalFormat(result.get(i).getPmoney());
							content[i+1][17] = "";
							content[i+1][18]  = StringUtil.notNull(result.get(i).getAccountName())+"|"+StringUtil.notNull(result.get(i).getAccountId())+"|"+StringUtil.notNull(result.get(i).getBankName())+StringUtil.notNull(result.get(i).getBankAdr());
							content[i+1][19] = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
							String emptystr ="";
							content[i+1][20] = emptystr;
							String tagstr ="";
							if(result.get(i).getPayTag()==0) tagstr = "锁定";
							else if(result.get(i).getPayTag()==1) tagstr = "正常";
							content[i+1][21] = tagstr;
							String stateStr ="";
							if(result.get(i).getState()==0) stateStr = "下线";
							else if(result.get(i).getState()>=1) stateStr = "在线";
							content[i+1][22] = stateStr;
							
						}
						 HSSFWorkbook wb = new HSSFWorkbook();  
						 HSSFSheet sheet = wb.createSheet(userId+"伞下会员信息");  
					    for(int i=0; i<content.length; i++){  
					        Row row = sheet.createRow(i);  
					        for (int j=0; j<content[i].length; j++) {  
					            row.createCell(j).setCellValue(content[i][j]);  
					        }  
					    }  
					    OutputStream out = response.getOutputStream();  
					    wb.write(out);  
					    out.close();  
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} 
		}

		
		public void admin_report_export_belong_user(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str_time = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String userId = StringUtil.notNull(request.getParameter("userId"));
			 String filename = userId+"伞下安置会员信息"+str_time+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			
			List<User>  result = new ArrayList<User> ();

			String message = "";
			try {
				if(admin!=null){
					   request.setAttribute("userId", userId);
					 
						UserDao userDao = new UserDao();
						result = userDao.findBelongListByUserId(userId);
						
						String[][] content = new String[result.size()+1][30];
						content[0][0]="序号";
						content[0][1]="会员编号";
						content[0][2]="会员名称";
						content[0][3]="会员等级";
						content[0][4]="管理级别";
						content[0][5]="销售商";
						content[0][6]="销售区域";
						content[0][7]="服务商";
						content[0][8]="报单人";
						content[0][9]="证件类型";
						content[0][10]="证件号码";
						content[0][11]="联系电话";
						content[0][12]="报单券余额";
						content[0][13]="购物券余额";
						content[0][14]="复消券余额";
						content[0][15]="奖金券余额";
						content[0][16]="电子券余额";
						content[0][17]="电子券有效期";
						content[0][18]="银行账户信息";
						content[0][19]="注册时间";
						content[0][20]="空单标示";
						content[0][21]="奖金状态";
						content[0][22]="当前状态";
						
						for(int i=0;i<result.size();i++){
							content[i+1][0]  = String.valueOf(i+1);
							content[i+1][1]  = StringUtil.notNull(result.get(i).getUserId());
							content[i+1][2]  = StringUtil.notNull(result.get(i).getUserName());
							String rankstr="";
							if(result.get(i).getRankJoin()==0) rankstr = "-";
							else if(result.get(i).getRankJoin()==1) rankstr = "银卡会员";
							else if(result.get(i).getRankJoin()==2) rankstr = "金卡会员";
							else if(result.get(i).getRankJoin()==3) rankstr = "白金会员";
							else if(result.get(i).getRankJoin()==4) rankstr = "VIP会员";
							else if(result.get(i).getRankJoin()==5) rankstr = "至尊会员";
							content[i+1][3]  = StringUtil.notNull(rankstr);
							String managestr="";
							if(result.get(i).getRank()==0) managestr = "-";
							else if(result.get(i).getRank()==1) managestr = "一星经理";
							else if(result.get(i).getRank()==2) managestr = "二星经理";
							else if(result.get(i).getRank()==3) managestr = "三星经理";
							else if(result.get(i).getRank()==4) managestr = "四星经理";
							else if(result.get(i).getRank()==5) managestr = "五星经理";
							
							content[i+1][4]  = StringUtil.notNull(managestr);
							content[i+1][5]  =  StringUtil.notNull(result.get(i).getUserByBelongId());
							String nodestr = "";
							if(result.get(i).getNodeTag()==1) nodestr="A区";
							else if(result.get(i).getNodeTag()==2) nodestr="B区";
							else if(result.get(i).getNodeTag()==3) nodestr="C区";
							else nodestr="-";
							content[i+1][6]  = StringUtil.notNull(nodestr);
							content[i+1][7]  = StringUtil.notNull(result.get(i).getUserByRefereeId());
							content[i+1][8]  = StringUtil.notNull(result.get(i).getUserByDeclarationId());
							content[i+1][9] = StringUtil.notNull(result.get(i).getDocumentType());
							content[i+1][10] = StringUtil.notNull(result.get(i).getNumId());
							content[i+1][11]  = StringUtil.notNull(result.get(i).getTel());
							content[i+1][12]  = StringUtil.decimalFormat(result.get(i).getEmoney());
							content[i+1][13]  = StringUtil.decimalFormat(0);
							content[i+1][14]  = StringUtil.decimalFormat(result.get(i).getDmoney());
							content[i+1][15]  = StringUtil.decimalFormat(result.get(i).getRmoney());
							content[i+1][16]  = StringUtil.decimalFormat(result.get(i).getPmoney());
							content[i+1][17] = "";
							content[i+1][18]  = StringUtil.notNull(result.get(i).getAccountName())+"|"+StringUtil.notNull(result.get(i).getAccountId())+"|"+StringUtil.notNull(result.get(i).getBankName())+StringUtil.notNull(result.get(i).getBankAdr());
							content[i+1][19] = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);
							String emptystr ="";
							content[i+1][20] = emptystr;
							String tagstr ="";
							if(result.get(i).getPayTag()==0) tagstr = "锁定";
							else if(result.get(i).getPayTag()==1) tagstr = "正常";
							content[i+1][21] = tagstr;
							String stateStr ="";
							if(result.get(i).getState()==0) stateStr = "下线";
							else if(result.get(i).getState()>=1) stateStr = "在线";
							content[i+1][22] = stateStr;
							
						}
						 HSSFWorkbook wb = new HSSFWorkbook();  
						 HSSFSheet sheet = wb.createSheet(userId+"伞下安置会员信息");  
					    for(int i=0; i<content.length; i++){  
					        Row row = sheet.createRow(i);  
					        for (int j=0; j<content[i].length; j++) {  
					            row.createCell(j).setCellValue(content[i][j]);  
					        }  
					    }  
					    OutputStream out = response.getOutputStream();  
					    wb.write(out);  
					    out.close();  
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} 
		}
		public void report_finance_excel(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
			String message="";
			try {
				if(admin!=null){
					SettleDao stDao = new SettleDao();
					int weekTag1 = stDao.maxWeekTag();
					List<Integer> slist = new ArrayList<Integer>();
					for(int i=weekTag1;i>0;i--){
						slist.add(i);
					}
					request.setAttribute("slist", slist);
				
				}else{
					message = "会员未登录。";
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("report_finance_excel.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		public void admin_reward_week_summary_excel(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str_time = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String weekTagStr1 = request.getParameter("weekTag1");
				String weekTagStr2 = request.getParameter("weekTag2");
			 String filename = weekTagStr1+"-"+weekTagStr2+"周奖金明细汇总"+str_time+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			
			List<WReward>  result = new ArrayList<WReward> ();

			String message = "";
			try {
				if(admin!=null){
					
					if(!(StringUtil.notNull(weekTagStr1).equals("")||StringUtil.notNull(weekTagStr2).equals(""))){
					
					int weekTag1=0;
					int weekTag2=0;
					weekTag1 = Integer.valueOf(weekTagStr1);
					weekTag2 = Integer.valueOf(weekTagStr2);
					if(weekTag2-weekTag1>=0){
					
					WRewardDao wrdDao = new WRewardDao();
					result = wrdDao.awardSummaryAll(weekTag1,weekTag2);
					String[][] content = new String[result.size()+3][21];
					content[1][0]="序号";
					content[1][1]="开始时间";
					content[1][2]="结束时间";
					content[1][3]="会员编号";
					content[1][4]="会员姓名";
					content[1][5]="创业奖";
					content[1][6]="推荐奖";
					content[1][7]="见点奖";
					content[1][8]="拓展奖";
					content[1][9]="培育奖";
					content[1][10]="领导奖";
					content[1][11]="物流补助";
					content[1][12]="消费奖";
					content[1][13]="奖金合计";
					content[1][14]="复消";
					content[1][15]="扣税";
					content[1][16]="应发金额";
					double[] sum={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
				if(result.size()>0){
					for(int i=0;i<result.size();i++){
						content[i+2][0]  = String.valueOf(i+1);
						content[i+2][1]  = StringUtil.parseToString(result.get(i).getStartTime(), DateUtil.yyyyMMddHHmmss);
						content[i+2][2]  = StringUtil.parseToString(result.get(i).getEndTime(), DateUtil.yyyyMMddHHmmss);
						content[i+2][3]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+2][4]  = StringUtil.notNull(result.get(i).getUserName());
						content[i+2][5]  =  StringUtil.decimalFormat(result.get(i).getAmount_1());
						content[i+2][6]  =  StringUtil.decimalFormat(result.get(i).getAmount_7());
						content[i+2][7]  =  StringUtil.decimalFormat(result.get(i).getAmount_8());
						content[i+2][8]  = StringUtil.decimalFormat(result.get(i).getAmount_2());
						content[i+2][9]  = StringUtil.decimalFormat(result.get(i).getAmount_3());
						content[i+2][10] = StringUtil.decimalFormat(result.get(i).getAmount_4());
						content[i+2][11] =StringUtil.decimalFormat(result.get(i).getAmount_5());
						content[i+2][12] = StringUtil.decimalFormat(result.get(i).getAmount_6());
						content[i+2][13] = StringUtil.decimalFormat(result.get(i).getAmount_9());
						content[i+2][14] = StringUtil.decimalFormat(result.get(i).getAmount_10());
						content[i+2][15] = StringUtil.decimalFormat(result.get(i).getAmount_11());
						content[i+2][16] = StringUtil.decimalFormat(result.get(i).getAmount_12());
						
						
						sum[0] = ArithUtil.add(sum[0],result.get(i).getAmount_1());
						sum[1] = ArithUtil.add(sum[1],result.get(i).getAmount_7());
						sum[2] = ArithUtil.add(sum[2],result.get(i).getAmount_8());
						sum[3] = ArithUtil.add(sum[3],result.get(i).getAmount_2());
						sum[4] = ArithUtil.add(sum[4],result.get(i).getAmount_3());
						sum[5] = ArithUtil.add(sum[5],result.get(i).getAmount_4());
						sum[6] = ArithUtil.add(sum[6],result.get(i).getAmount_5());
						sum[7] = ArithUtil.add(sum[7],result.get(i).getAmount_6());
						sum[8] = ArithUtil.add(sum[8],result.get(i).getAmount_9());
						sum[9] = ArithUtil.add(sum[9],result.get(i).getAmount_10());
						sum[10] = ArithUtil.add(sum[10],result.get(i).getAmount_11());
						sum[11] = ArithUtil.add(sum[11],result.get(i).getAmount_12());
					}
					content[result.size()+2][0]  = String.valueOf(result.size()+1);
					content[result.size()+2][1]  = "合计";
					content[result.size()+2][2]  = "-";
					content[result.size()+2][3]  = "-";
					content[result.size()+2][4]  = "-";
					content[result.size()+2][5]  = StringUtil.decimalFormat(sum[0]);
					content[result.size()+2][6]  = StringUtil.decimalFormat(sum[1]);
					content[result.size()+2][7]  = StringUtil.decimalFormat(sum[2]);
					content[result.size()+2][8] = StringUtil.decimalFormat(sum[3]);
					content[result.size()+2][9] = StringUtil.decimalFormat(sum[4]);
					content[result.size()+2][10] = StringUtil.decimalFormat(sum[5]);
					content[result.size()+2][11] = StringUtil.decimalFormat(sum[6]);
					content[result.size()+2][12] = StringUtil.decimalFormat(sum[7]);
					content[result.size()+2][13] = StringUtil.decimalFormat(sum[8]);
					content[result.size()+2][14] = StringUtil.decimalFormat(sum[9]);
					content[result.size()+2][15] = StringUtil.decimalFormat(sum[10]);
					content[result.size()+2][16] = StringUtil.decimalFormat(sum[11]);
				}
				 HSSFWorkbook wb = new HSSFWorkbook();  
						 HSSFSheet sheet = wb.createSheet("好当家海洋大健康奖金汇总表("+weekTag1+"-"+weekTag2+"周)");  
						 HSSFCellStyle style = wb.createCellStyle(); // 样式对象   
						  style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直     
				            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平     
						 sheet.addMergedRegion(new Region(0, (short) (0), 0,(short) (16)));   
						   Row row0 = sheet.createRow(0); 
						   HSSFCell ce =  (HSSFCell) row0.createCell((short) (0)); 
						   ce.setCellValue("好当家海洋大健康奖金汇总表("+weekTag1+"-"+weekTag2+"周)"); // 表格的第一行第一列显示的数据     
						   ce.setCellStyle(style); // 样式，居中 
						 for(int i=1; i<content.length; i++){  
					        Row row = sheet.createRow(i);  
					        for (int j=0; j<content[i].length; j++) {  
					            row.createCell(j).setCellValue(content[i][j]);  
					        }  
					    }  
					    OutputStream out = response.getOutputStream();  
					    wb.write(out);  
					    out.close();  
					}
					}
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} 
		}
		
		public void admin_report_money_excel(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str_time = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String startTimeStr = request.getParameter("startTime");
				String endTimeStr = request.getParameter("endTime");
			 String filename = startTimeStr+"至"+endTimeStr+"电子币余额汇总"+str_time+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			
			List<AccountDetail>  result = new ArrayList<AccountDetail> ();

			String message = "";
			try {
				if(admin!=null){
					
					if(!(StringUtil.notNull(startTimeStr).equals("")||StringUtil.notNull(startTimeStr).equals(""))){
				
					Timestamp startTime =new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					Timestamp endTime =new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					if(endTime.getTime()-startTime.getTime()>=0){
						
					ReportDao rpDao = new ReportDao();
					result = rpDao.moneySummary(startTime, endTime);
					String[][] content = new String[result.size()+3][21];
				
					double[] sum={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
				if(result.size()>0){
					for(int i=0;i<result.size();i++){
						content[i+2][0]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+2][1]  = StringUtil.notNull(result.get(i).getUserName());
						content[i+2][2]  = StringUtil.parseToString(result.get(i).getStartTime(), DateUtil.yyyyMMddHHmmss)+"至"+StringUtil.parseToString(result.get(i).getEndTime(), DateUtil.yyyyMMddHHmmss);
						content[i+2][3]  =  StringUtil.decimalFormat(result.get(i).getEmoneyBalance());
						content[i+2][4]  =  StringUtil.decimalFormat(result.get(i).getEmoneyAdd());
						content[i+2][5]  =  StringUtil.decimalFormat(result.get(i).getEmoneySub());
						content[i+2][6]  = StringUtil.decimalFormat(ArithUtil.sub(result.get(i).getEmoneyAdd(), result.get(i).getEmoneySub()));
						content[i+2][7]  =  StringUtil.decimalFormat(result.get(i).getDmoneyBalance());
						content[i+2][8]  =  StringUtil.decimalFormat(result.get(i).getDmoneyAdd());
						content[i+2][9]  =  StringUtil.decimalFormat(result.get(i).getDmoneySub());
						content[i+2][10]  = StringUtil.decimalFormat(ArithUtil.sub(result.get(i).getDmoneyAdd(), result.get(i).getDmoneySub()));
						content[i+2][11]  =  StringUtil.decimalFormat(result.get(i).getRmoneyBalance());
						content[i+2][12]  =  StringUtil.decimalFormat(result.get(i).getRmoneyAdd());
						content[i+2][13]  =  StringUtil.decimalFormat(result.get(i).getRmoneySub());
						content[i+2][14]  = StringUtil.decimalFormat(ArithUtil.sub(result.get(i).getRmoneyAdd(), result.get(i).getRmoneySub()));
						
						
						sum[0] = ArithUtil.add(sum[0],result.get(i).getEmoneyBalance());
						sum[1] = ArithUtil.add(sum[1],result.get(i).getEmoneyAdd());
						sum[2] = ArithUtil.add(sum[2],result.get(i).getEmoneySub());
						sum[3] = ArithUtil.add(sum[3],ArithUtil.sub(result.get(i).getEmoneyAdd(), result.get(i).getEmoneySub()));
						sum[4] = ArithUtil.add(sum[4],result.get(i).getDmoneyBalance());
						sum[5] = ArithUtil.add(sum[5],result.get(i).getDmoneyAdd());
						sum[6] = ArithUtil.add(sum[6],result.get(i).getDmoneySub());
						sum[7] = ArithUtil.add(sum[7],ArithUtil.sub(result.get(i).getDmoneyAdd(), result.get(i).getDmoneySub()));
						sum[8] = ArithUtil.add(sum[8],result.get(i).getRmoneyBalance());
						sum[9] = ArithUtil.add(sum[9],result.get(i).getRmoneyAdd());
						sum[10] = ArithUtil.add(sum[10],result.get(i).getRmoneySub());
						sum[11] = ArithUtil.add(sum[11],ArithUtil.sub(result.get(i).getRmoneyAdd(), result.get(i).getRmoneySub()));
					}
					content[result.size()+2][0]  = "合计";
					content[result.size()+2][1]  = "-";
					content[result.size()+2][2]  = "-";
					content[result.size()+2][3]  = StringUtil.decimalFormat(sum[0]);
					content[result.size()+2][4]  = StringUtil.decimalFormat(sum[1]);
					content[result.size()+2][5]  = StringUtil.decimalFormat(sum[2]);
					content[result.size()+2][6] = StringUtil.decimalFormat(sum[3]);
					content[result.size()+2][7] = StringUtil.decimalFormat(sum[4]);
					content[result.size()+2][8] = StringUtil.decimalFormat(sum[5]);
					content[result.size()+2][9] = StringUtil.decimalFormat(sum[6]);
					content[result.size()+2][10] = StringUtil.decimalFormat(sum[7]);
					content[result.size()+2][11] = StringUtil.decimalFormat(sum[8]);
					content[result.size()+2][12] = StringUtil.decimalFormat(sum[9]);
					content[result.size()+2][13] = StringUtil.decimalFormat(sum[10]);
					content[result.size()+2][14] = StringUtil.decimalFormat(sum[11]);
				}
				
				 HSSFWorkbook wb = new HSSFWorkbook();  
						 HSSFSheet sheet = wb.createSheet("电子币余额汇总("+startTimeStr+"至"+endTimeStr+")");  
						 HSSFCellStyle style = wb.createCellStyle(); // 样式对象   
						  style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直     
				            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平     
				            sheet.addMergedRegion(new Region(0, (short) (0), 1,(short) (0)));   
						   Row row0 = sheet.createRow(0); 
						   HSSFCell ce =  (HSSFCell) row0.createCell((short) (0)); 
						   ce.setCellValue("会员编号"); // 表格的第一行第一列显示的数据     
						   ce.setCellStyle(style); // 样式，居中 
						   sheet.addMergedRegion(new Region(0, (short) (1), 1,(short) (1)));   
						   HSSFCell ce1 =  (HSSFCell) row0.createCell((short) (1)); 
						   ce1.setCellValue("会员名称"); // 表格的第一行第一列显示的数据     
						   ce1.setCellStyle(style); // 样式，居中 
						   sheet.addMergedRegion(new Region(0, (short) (2), 1,(short) (2)));   
						   HSSFCell ce2 =  (HSSFCell) row0.createCell((short) (2)); 
						   ce2.setCellValue("起止日期"); // 表格的第一行第一列显示的数据     
						   ce2.setCellStyle(style); // 样式，居中 
						   sheet.addMergedRegion(new Region(0, (short) (3), 0,(short) (6)));   
						   HSSFCell ce3 =  (HSSFCell) row0.createCell((short) (3)); 
						   ce3.setCellValue("报单币"); // 表格的第一行第一列显示的数据     
						   ce3.setCellStyle(style); // 样式，居中 
						   Row row4 = sheet.createRow(1); 
						   HSSFCell ce4 =  (HSSFCell) row4.createCell((short) (3)); 
						   ce4.setCellValue("余额"); // 表格的第一行第一列显示的数据     
						   ce4.setCellStyle(style); // 样式，居中 
						   HSSFCell ce5 =  (HSSFCell) row4.createCell((short) (4)); 
						   ce5.setCellValue("本期增加"); // 表格的第一行第一列显示的数据     
						   ce5.setCellStyle(style); // 样式，居中 
						   HSSFCell ce6 =  (HSSFCell) row4.createCell((short) (5)); 
						   ce6.setCellValue("本期减少"); // 表格的第一行第一列显示的数据     
						   ce6.setCellStyle(style); // 样式，居中 
						   HSSFCell ce7 =  (HSSFCell) row4.createCell((short) (6)); 
						   ce7.setCellValue("本期余额"); // 表格的第一行第一列显示的数据     
						   ce7.setCellStyle(style); // 样式，居中 
						   sheet.addMergedRegion(new Region(0, (short) (7), 0,(short) (10)));   
						   HSSFCell ce8 =  (HSSFCell) row0.createCell((short) (7)); 
						   ce8.setCellValue("复消币"); // 表格的第一行第一列显示的数据     
						   ce8.setCellStyle(style); // 样式，居中 
						   HSSFCell ce9 =  (HSSFCell) row4.createCell((short) (7)); 
						   ce9.setCellValue("余额"); // 表格的第一行第一列显示的数据     
						   ce9.setCellStyle(style); // 样式，居中 
						   HSSFCell ce10 =  (HSSFCell) row4.createCell((short) (8)); 
						   ce10.setCellValue("本期增加"); // 表格的第一行第一列显示的数据     
						   ce10.setCellStyle(style); // 样式，居中 
						   HSSFCell ce11 =  (HSSFCell) row4.createCell((short) (9)); 
						   ce11.setCellValue("本期减少"); // 表格的第一行第一列显示的数据     
						   ce11.setCellStyle(style); // 样式，居中 
						   HSSFCell ce12 =  (HSSFCell) row4.createCell((short) (10)); 
						   ce12.setCellValue("本期余额"); // 表格的第一行第一列显示的数据     
						   ce12.setCellStyle(style); // 样式，居中 
						   sheet.addMergedRegion(new Region(0, (short) (11), 0,(short) (14)));   
						   HSSFCell ce13 =  (HSSFCell) row0.createCell((short) (11)); 
						   ce13.setCellValue("奖金币"); // 表格的第一行第一列显示的数据     
						   ce13.setCellStyle(style); // 样式，居中 
						   HSSFCell ce14 =  (HSSFCell) row4.createCell((short) (11)); 
						   ce14.setCellValue("余额"); // 表格的第一行第一列显示的数据     
						   ce14.setCellStyle(style); // 样式，居中 
						   HSSFCell ce15 =  (HSSFCell) row4.createCell((short) (12)); 
						   ce15.setCellValue("本期增加"); // 表格的第一行第一列显示的数据     
						   ce15.setCellStyle(style); // 样式，居中 
						   HSSFCell ce16 =  (HSSFCell) row4.createCell((short) (13)); 
						   ce16.setCellValue("本期减少"); // 表格的第一行第一列显示的数据     
						   ce16.setCellStyle(style); // 样式，居中 
						   HSSFCell ce17 =  (HSSFCell) row4.createCell((short) (14)); 
						   ce17.setCellValue("本期余额"); // 表格的第一行第一列显示的数据     
						   ce17.setCellStyle(style); // 样式，居中 
						  
						 for(int i=1; i<content.length; i++){  
					        Row row = sheet.createRow(i);  
					        for (int j=0; j<content[i].length; j++) {  
					            row.createCell(j).setCellValue(content[i][j]);  
					        }  
					    }  
					    
					    OutputStream out = response.getOutputStream();  
					    wb.write(out);  
					    out.close();  
					}
					}
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} 
		}
		
		public void admin_report_settle_day(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
			String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
			String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
			int pageNo = 1;
			int pageSize = 20;
			String message = "";
			try {
				if(admin!=null){
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
					DSettleDao wstDao = new DSettleDao();
					Pager pager= new Pager();
					pager.setPageNo(pageNo);
					pager.setPageSize(pageSize);
					pager = wstDao.findByPage(userId,startTimeStr, pager);
					request.setAttribute("pager", pager);
					request.setAttribute("userId", userId);
					request.setAttribute("startTime", startTimeStr);
				
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("report_settle_day.jsp");
					dispatcher.forward(request, response);
				
			}
		}
		
		public void report_settle_day(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
			
			String message = "";
			try {
				if(user!=null){
					DSettleDao wstDao = new DSettleDao();
					List<DSettle> wstlist= wstDao.findByList(user.getUserId());
					request.setAttribute("wstlist", wstlist);
				
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("report_settle_day.jsp");
					dispatcher.forward(request, response);
				
			}
		}
		
		
		public void admin_report_settle_day_exportExcel(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());

			String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String filename = startTimeStr+"已结业绩明细"+str+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			 List<DSettle> result = new ArrayList<DSettle>();
			String message = "";
				try {
					if(admin!=null){
						
						int weekTag=0;
						if(StringUtil.notNull(startTimeStr).equals(""))weekTag = 0;
						String tableName= "wsettle_"+weekTag;
						DSettleDao wstDao = new DSettleDao();
						 result = wstDao.findByList(userId,startTimeStr);
						String[][] content = new String[result.size()+2][20];
						content[0][0]="序号";
						content[0][1]="日期";
						content[0][2]="会员编号";
						content[0][3]="会员名称";
						content[0][4]="区域总累计";
						content[0][5]="期初累计";
						content[0][6]="区域新增";
						content[0][7]="A区总累计";
						content[0][8]="A区期初累计";
						content[0][9]="A区新增";
						content[0][10]="A区盈余";
						content[0][11]="B区总累计";
						content[0][12]="B区期初累计";
						content[0][13]="B区新增";
						content[0][14]="B区盈余";
						content[0][15]="销售新增业绩";
						content[0][16]="销售期初业绩";
						content[0][17]="销售总业绩";
						content[0][18]="加盟级别";
						content[0][19]="会员奖衔";
						for(int i=0;i<result.size();i++){
							content[i+1][0]  = String.valueOf(i+1);
							content[i+1][1]  = StringUtil.parseToString(result.get(i).getStartTime(), DateUtil.yyyyMMdd)+"至"+StringUtil.parseToString(result.get(i).getEndTime(), DateUtil.yyyyMMdd);;
							content[i+1][2]  = StringUtil.notNull(result.get(i).getUserId());
							content[i+1][3]  = StringUtil.notNull(result.get(i).getUserName());
							content[i+1][4]  = StringUtil.decimalFormat(result.get(i).getTotalPerformance()+result.get(i).getNewPerformance());
							content[i+1][5]  = StringUtil.decimalFormat(result.get(i).getTotalPerformance());
							content[i+1][6]  = StringUtil.decimalFormat(result.get(i).getNewPerformance());
							content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getAtpv()+result.get(i).getApv());
							content[i+1][8]  = StringUtil.decimalFormat(result.get(i).getAtpv());
							content[i+1][9]  = StringUtil.decimalFormat(result.get(i).getApv());
							content[i+1][10]  = StringUtil.decimalFormat(result.get(i).getAcpv());
							content[i+1][11]  = StringUtil.decimalFormat(result.get(i).getBtpv()+result.get(i).getBpv());
							content[i+1][12]  = StringUtil.decimalFormat(result.get(i).getBtpv());
							content[i+1][13]  = StringUtil.decimalFormat(result.get(i).getBpv());
							content[i+1][14]  = StringUtil.decimalFormat(result.get(i).getBcpv());
							content[i+1][15]  = StringUtil.decimalFormat(result.get(i).getRpv()+result.get(i).getRpv1());
							content[i+1][16]  = StringUtil.decimalFormat(result.get(i).getRtpv()+result.get(i).getRtpv1());
							content[i+1][17]  = StringUtil.decimalFormat(ArithUtil.add(result.get(i).getRpv()+result.get(i).getRpv1(), result.get(i).getRtpv()+ result.get(i).getRtpv1()));
							String rankJoinstr = "";
							if(result.get(i).getRankJoin()==1)rankJoinstr="银卡会员";
							else if(result.get(i).getRankJoin()==2)rankJoinstr="金卡会员";
							else if(result.get(i).getRankJoin()==3)rankJoinstr="白金卡会员";
							else if(result.get(i).getRankJoin()==4)rankJoinstr="VIP会员";
							else if(result.get(i).getRankJoin()==5)rankJoinstr="至尊会员";
							content[i+1][18]  =rankJoinstr;
							String rank="";
							int rankManage = result.get(i).getRank();
							if (rankManage == 1)
								rank = rank + "一级经理";
							else if (rankManage == 2)
								rank = rank + "二级经理";
							else if (rankManage == 3)
								rank = rank + "三级经理";
							else if (rankManage== 4)
								rank = rank + "四级经理";
							else if (rankManage == 5)
								rank = rank + "五级经理";
							else if (rankManage == 6)
								rank = rank + "一星钻石";
							else if (rankManage == 7)
								rank = rank + "二星钻石";
							else if (rankManage == 8)
								rank = rank + "三星钻石";
							else if (rankManage == 9)
								rank = rank + "四星钻石";
							else if (rankManage == 10)
								rank = rank + "五星钻石";
							else if (rankManage== 11)
								rank = rank + "董事";
							else
								rank = rank + "-";
							content[i+1][19]  = rank;
						}
						
						 HSSFWorkbook wb = new HSSFWorkbook();  
						 HSSFSheet sheet = wb.createSheet(startTimeStr+"已结业绩汇总");  
					    for(int i=0; i<content.length; i++){  
					        Row row = sheet.createRow(i);  
					        for (int j=0; j<content[i].length; j++) {  
					            row.createCell(j).setCellValue(content[i][j]);  
					        }  
					    }  
					    OutputStream out = response.getOutputStream();  
					    wb.write(out);  
					    out.close();  
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} 
		}
		
		
		public void admin_report_reward_day_excel(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		 response.setHeader("Connection", "close");  
		    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
			Timestamp date1 = new Timestamp(new Date().getTime());
			java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			 String str_time = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			 String startTimeStr = StringUtil.notNull(request.getParameter("startTime_3"));
			 String endTimeStr = StringUtil.notNull(request.getParameter("endTime_3"));
			 String filename = startTimeStr+"至"+startTimeStr+"奖金明细汇总"+str_time+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			
			List<DReward>  result = new ArrayList<DReward> ();
			String message = "";
			try {
				if(admin!=null){
					
					if(!(StringUtil.notNull(startTimeStr).equals("")||StringUtil.notNull(endTimeStr).equals(""))){
					Timestamp startTime = null;
					Timestamp endTime  = null;
					if(!StringUtil.notNull(startTimeStr).equals("")) startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					if(!StringUtil.notNull(endTimeStr).equals("")) endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());

					if(endTime.getTime()-startTime.getTime()>=0){
					
					DRewardDao wrdDao = new DRewardDao();
					result = wrdDao.awardSummaryAll_day(startTime,endTime);
					String[][] content = new String[result.size()+3][21];
					content[1][0]="序号";
					content[1][1]="开始时间";
					content[1][2]="结束时间";
					content[1][3]="会员编号";
					content[1][4]="会员姓名";
					content[1][5]="推荐奖";
					content[1][6]="见点奖";
					content[1][7]="层碰奖";
					content[1][8]="拓展奖";
					content[1][9]="培育奖";
					content[1][10]="领导奖";
					content[1][11]="物流补助";
					content[1][12]="消费奖";
					content[1][13]="奖金合计";
					content[1][14]="复消";
					content[1][15]="扣税";
					content[1][16]="应发金额";
					double[] sum={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
				if(result.size()>0){
					for(int i=0;i<result.size();i++){
						content[i+2][0]  = String.valueOf(i+1);
						content[i+2][1]  = StringUtil.parseToString(result.get(i).getStartTime(), DateUtil.yyyyMMddHHmmss);
						content[i+2][2]  = StringUtil.parseToString(result.get(i).getEndTime(), DateUtil.yyyyMMddHHmmss);
						content[i+2][3]  = StringUtil.notNull(result.get(i).getUserId());
						content[i+2][4]  = StringUtil.notNull(result.get(i).getUserName());
						content[i+2][5]  =  StringUtil.decimalFormat(result.get(i).getAmount_1());
						content[i+2][6]  =  StringUtil.decimalFormat(result.get(i).getAmount_2());
						content[i+2][7]  =  StringUtil.decimalFormat(result.get(i).getAmount_3());
						content[i+2][8]  = StringUtil.decimalFormat(result.get(i).getAmount_4());
						content[i+2][9]  = StringUtil.decimalFormat(result.get(i).getAmount_5());
						content[i+2][10] = StringUtil.decimalFormat(result.get(i).getAmount_6());
						content[i+2][11] =StringUtil.decimalFormat(result.get(i).getAmount_7());
						content[i+2][12] = StringUtil.decimalFormat(result.get(i).getAmount_8());
						content[i+2][13] = StringUtil.decimalFormat(result.get(i).getAmount_9());
						content[i+2][14] = StringUtil.decimalFormat(result.get(i).getAmount_10());
						content[i+2][15] = StringUtil.decimalFormat(result.get(i).getAmount_11());
						content[i+2][16] = StringUtil.decimalFormat(result.get(i).getAmount_12());
						
						
						sum[0] = ArithUtil.add(sum[0],result.get(i).getAmount_1());
						sum[1] = ArithUtil.add(sum[1],result.get(i).getAmount_2());
						sum[2] = ArithUtil.add(sum[2],result.get(i).getAmount_3());
						sum[3] = ArithUtil.add(sum[3],result.get(i).getAmount_4());
						sum[4] = ArithUtil.add(sum[4],result.get(i).getAmount_5());
						sum[5] = ArithUtil.add(sum[5],result.get(i).getAmount_6());
						sum[6] = ArithUtil.add(sum[6],result.get(i).getAmount_7());
						sum[7] = ArithUtil.add(sum[7],result.get(i).getAmount_8());
						sum[8] = ArithUtil.add(sum[8],result.get(i).getAmount_9());
						sum[9] = ArithUtil.add(sum[9],result.get(i).getAmount_10());
						sum[10] = ArithUtil.add(sum[10],result.get(i).getAmount_11());
						sum[11] = ArithUtil.add(sum[11],result.get(i).getAmount_12());
					}
					content[result.size()+2][0]  = String.valueOf(result.size()+1);
					content[result.size()+2][1]  = "合计";
					content[result.size()+2][2]  = "-";
					content[result.size()+2][3]  = "-";
					content[result.size()+2][4]  = "-";
					content[result.size()+2][5]  = StringUtil.decimalFormat(sum[0]);
					content[result.size()+2][6]  = StringUtil.decimalFormat(sum[1]);
					content[result.size()+2][7]  = StringUtil.decimalFormat(sum[2]);
					content[result.size()+2][8] = StringUtil.decimalFormat(sum[3]);
					content[result.size()+2][9] = StringUtil.decimalFormat(sum[4]);
					content[result.size()+2][10] = StringUtil.decimalFormat(sum[5]);
					content[result.size()+2][11] = StringUtil.decimalFormat(sum[6]);
					content[result.size()+2][12] = StringUtil.decimalFormat(sum[7]);
					content[result.size()+2][13] = StringUtil.decimalFormat(sum[8]);
					content[result.size()+2][14] = StringUtil.decimalFormat(sum[9]);
					content[result.size()+2][15] = StringUtil.decimalFormat(sum[10]);
					content[result.size()+2][16] = StringUtil.decimalFormat(sum[11]);
				}
				 HSSFWorkbook wb = new HSSFWorkbook();  
						 HSSFSheet sheet = wb.createSheet("新制度奖金汇总表("+startTimeStr+"至"+startTimeStr+")");  
						 HSSFCellStyle style = wb.createCellStyle(); // 样式对象   
						  style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直     
				            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平     
						 sheet.addMergedRegion(new Region(0, (short) (0), 0,(short) (16)));   
						   Row row0 = sheet.createRow(0); 
						   HSSFCell ce =  (HSSFCell) row0.createCell((short) (0)); 
						   ce.setCellValue("好当家海洋大健康奖金汇总表("+startTimeStr+"至"+startTimeStr+")"); // 表格的第一行第一列显示的数据     
						   ce.setCellStyle(style); // 样式，居中 
						 for(int i=1; i<content.length; i++){  
					        Row row = sheet.createRow(i);  
					        for (int j=0; j<content[i].length; j++) {  
					            row.createCell(j).setCellValue(content[i][j]);  
					        }  
					    }  
					    OutputStream out = response.getOutputStream();  
					    wb.write(out);  
					    out.close();  
					}
					}
				}else{
					message = "用户未登陆，请重新登陆。";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message ="系统繁忙中，请稍后再试。";
			} 
		}
	

}