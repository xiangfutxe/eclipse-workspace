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

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.ssm.dao.JoinInfoDao;
import com.ssm.dao.UserDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.JoinInfo;
import com.ssm.pojo.User;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class JoinInfoServlet extends HttpServlet {

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

		}else if (method.equals("admin_list")) {
			admin_list(request, response);

		}else if (method.equals("rankJoin_up_back")) {
			admin_rankJoin_up_back(request, response);

		}else if (method.equals("exportExcel_rankJoin_up")) {
			exportExcel_rankJoin_up(request, response);
		}
			
		
	}

	
	
	
	public void admin_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
	String newRankJoin = StringUtil.notNull(request.getParameter("newRankJoin"));
	String oldRankJoin = StringUtil.notNull(request.getParameter("oldRankJoin"));
	String isEmpty = StringUtil.notNull(request.getParameter("isEmpty"));
	String startTimeStr = StringUtil
			.notNull(request.getParameter("startTime"));
	String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			
			JoinInfo joinInfo = new JoinInfo();
			if(isEmpty.equals("0")){
				joinInfo.setPrice(Double.valueOf(0));
				joinInfo.setTag(0);
				
			}else if(isEmpty.equals("1")){
					joinInfo.setPrice(Double.valueOf(0));
					joinInfo.setTag(1);
				
			}
			if(!newRankJoin.equals(""))
			joinInfo.setNewRankJoin(Integer.valueOf(newRankJoin));
			if(!oldRankJoin.equals(""))
				joinInfo.setOldRankJoin(Integer.valueOf(oldRankJoin));
			joinInfo.setUserId(userId);
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				joinInfo.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				joinInfo.setEndTime(endTime);
			}
				JoinInfoDao joinInfoDao = new JoinInfoDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = joinInfoDao.findByPage(joinInfo,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId", userId);
				request.setAttribute("oldRankJoin", oldRankJoin);
				request.setAttribute("newRankJoin", newRankJoin);
				request.setAttribute("isEmpty", isEmpty);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (!message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_up_list_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("rankJoin_up_list.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_rankJoin_up_back(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String message = "";
		try {
			if(admin!=null){
				
				String id = StringUtil.notNull(request
						.getParameter("id"));
				Timestamp date = new Timestamp(new Date().getTime());
				JoinInfo joinInfo = new JoinInfo();
				
				JoinInfoDao joinInfoDao = new JoinInfoDao();
				joinInfo = joinInfoDao.findById(Integer.valueOf(id));
				if(joinInfo!=null){
					UserDao  userDao = new UserDao();
							User user = userDao.findByUserId(joinInfo.getUserId());
							if(user!=null){
								if(user.getRankJoin()-joinInfo.getNewRankJoin()==0){
									joinInfoDao = new JoinInfoDao();
								message = joinInfoDao.rankJoin_up_back(joinInfo,user);
								if(message.equals("yes")){
									message =user.getUserId()+"会员升级退单成功。";
								}
							}else{
								message="该会员还存在其他升级信息，请先退其它升级信息。";
							}
						}else{
							message="会员信息获取失败。";
						}
						
				
				}else{
					message="加盟信息获取失败。";
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
						.getRequestDispatcher("rankJoin_up_list_message.jsp");
				dispatcher.forward(request, response);
			
		}

	}
	
	public void exportExcel_rankJoin_up(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	 response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date1 = new Timestamp(new Date().getTime());
		java.sql.Timestamp date = new java.sql.Timestamp(date1.getTime());
			String userId = StringUtil.notNull(request.getParameter("userId"));
			String newRankJoin = StringUtil.notNull(request.getParameter("newRankJoin"));
			String oldRankJoin = StringUtil.notNull(request.getParameter("oldRankJoin"));
			String isEmpty = StringUtil.notNull(request.getParameter("isEmpty"));
			String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
			String endTimeStr = StringUtil.notNull(request.getParameter("endTime")); 
			String timestr = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
			String filename = "升级列表"+timestr+".xls";  
			 filename = cs.encodeFileName(request, filename);  
			 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
				List<JoinInfo> result = new ArrayList<JoinInfo>();
				String message="";
		try {
			if(admin!=null){
				JoinInfo joinInfo = new JoinInfo();
				if(isEmpty.equals("0")){
					joinInfo.setPrice(Double.valueOf(0));
					joinInfo.setTag(0);
					
				}else if(isEmpty.equals("1")){
						joinInfo.setPrice(Double.valueOf(0));
						joinInfo.setTag(1);
					
				}
				if(!newRankJoin.equals(""))
					joinInfo.setNewRankJoin(Integer.valueOf(newRankJoin));
				if(!oldRankJoin.equals(""))
					joinInfo.setOldRankJoin(Integer.valueOf(oldRankJoin));
				joinInfo.setUserId(userId);
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					joinInfo.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					joinInfo.setEndTime(endTime);
				}
				JoinInfoDao joinInfoDao = new JoinInfoDao();
				result = joinInfoDao.findByList(joinInfo);
				String[][] content = new String[result.size()+1][9];
				content[0][0]="序号";
				content[0][1]="时间";
				content[0][2]="会员编号";
				content[0][3]="会员名称";
				content[0][4]="原等级";
				content[0][5]="新等级";
				content[0][6]="升级金额";
				content[0][7]="升级PV";
				content[0][8]="当前状态";
			
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1] = StringUtil.parseToString(result.get(i).getEntryTime(), DateUtil.yyyyMMddHHmmss);

					content[i+1][2]  = StringUtil.notNull(result.get(i).getUserId());
					content[i+1][3]  = StringUtil.notNull(result.get(i).getUserName());
					String rankstr1="";
					if(result.get(i).getOldRankJoin()==0) rankstr1 = "-";
					else if(result.get(i).getOldRankJoin()==1) rankstr1 = "银卡会员";
					else if(result.get(i).getOldRankJoin()==2) rankstr1 = "金卡会员";
					else if(result.get(i).getOldRankJoin()==3) rankstr1 = "白金卡会员";
					else if(result.get(i).getOldRankJoin()==4) rankstr1 = "VIP会员";
					else if(result.get(i).getOldRankJoin()==5) rankstr1 = "至尊会员";
					content[i+1][4]  = StringUtil.notNull(rankstr1);
					String rankstr2="";
					if(result.get(i).getNewRankJoin()==0) rankstr2 = "-";
					else if(result.get(i).getNewRankJoin()==1) rankstr2 = "银卡会员";
					else if(result.get(i).getNewRankJoin()==2) rankstr2 = "金卡会员";
					else if(result.get(i).getNewRankJoin()==3) rankstr2 = "白金卡会员";
					else if(result.get(i).getNewRankJoin()==4) rankstr2 = "VIP会员";
					else if(result.get(i).getNewRankJoin()==5) rankstr2 = "至尊会员";
					content[i+1][5]  = StringUtil.notNull(rankstr2);
					content[i+1][6]  = StringUtil.decimalFormat(result.get(i).getPrice());
					content[i+1][7]  = StringUtil.decimalFormat(result.get(i).getPv());
					String stateStr ="";
					if(result.get(i).getState()==0) stateStr = "下线";
					else if(result.get(i).getState()==1) stateStr = "待激活";
					else if(result.get(i).getState()==2) stateStr = "在线";
					content[i+1][8] = stateStr;
				}
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("升级列表");    
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
	
}