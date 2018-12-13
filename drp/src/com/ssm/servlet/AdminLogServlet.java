package com.ssm.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import net.sf.json.JSONObject;

import com.ssm.dao.AdminDao;
import com.ssm.dao.AdminLogDao;
import com.ssm.dao.CenterDao;
import com.ssm.dao.DeptDao;
import com.ssm.dao.JobDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.AdminLog;
import com.ssm.pojo.Center;
import com.ssm.pojo.Dept;
import com.ssm.pojo.Job;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class AdminLogServlet extends HttpServlet {

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

		}else if (method.equals("admin_log_list")) {
			admin_log_list(request, response);

		}else if (method.equals("admin_del")) {
			admin_del(request, response);

		}else if(method.equals("exportExcel")){
			exportExcel(request,response);
		}
	}

	
	public void admin_log_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String adminName = StringUtil.notNull(request.getParameter("adminName"));
		String type = StringUtil.notNull(request.getParameter("type"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
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
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminName(adminName);
			adminLog.setType(type);
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				adminLog.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				adminLog.setEndTime(endTime);
			}
				AdminLogDao adminLogDao = new AdminLogDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = adminLogDao.findByPage(adminLog, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
				request.setAttribute("adminName", adminName);
				request.setAttribute("type", type);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (message.equals("")) {
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("adminLog.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("adminLog_message.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void admin_del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String adminName = StringUtil.notNull(request.getParameter("adminName"));
		String type = StringUtil.notNull(request.getParameter("type"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(admin!=null){
			AdminLog adminLog = new AdminLog();
			adminLog.setAdminName(adminName);
			adminLog.setType(type);
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				adminLog.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				adminLog.setEndTime(endTime);
			}
				AdminLogDao adminLogDao = new AdminLogDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = adminLogDao.findByPage(adminLog, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
				request.setAttribute("adminName", adminName);
				request.setAttribute("type", type);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("adminLog_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date = new Timestamp(new Date().getTime());
		String adminName = StringUtil.notNull(request.getParameter("adminName"));
		String type = StringUtil.notNull(request.getParameter("type"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 String filename = "管理员日志"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			List<AdminLog>  result = new ArrayList<AdminLog> ();
			try {
				
				AdminLog adminLog = new AdminLog();
				adminLog.setAdminName(adminName);
				adminLog.setType(type);
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					adminLog.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					adminLog.setEndTime(endTime);
				}
					AdminLogDao adminLogDao = new AdminLogDao();
					
				result = adminLogDao.findByList(adminLog);
			
				String[][] content = new String[result.size()+1][5];
				content[0][0]="序号";
				content[0][1]="操作时间";
				content[0][2]="操作员";
				content[0][3]="类型";
				content[0][4]="内容";
				
				
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.parseToString(result.get(i).getEntryTime(),DateUtil.yyyyMMddHHmmss);
					
				
					content[i+1][2]  = String.valueOf(result.get(i).getAdminName());
					
					content[i+1][3]  =  StringUtil.notNull(result.get(i).getType());
				
					content[i+1][4]  = StringUtil.notNull(result.get(i).getContents());
					
					
				}
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("管理员日志");  
				    for(int i=0; i<content.length; i++){  
				        Row row = sheet.createRow(i);  
				        for (int j=0; j<content[i].length; j++) {  
				            row.createCell(j).setCellValue(content[i][j]);  
				        }  
				    }  
				    OutputStream out = response.getOutputStream();  
				    wb.write(out);  
				    out.close();  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}