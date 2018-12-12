package com.ssm.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import net.sf.json.JSONObject;

import com.ssm.pojo.User;
import com.ssm.dao.ParamDao;
import com.ssm.dao.RewardDetailDao;
import com.ssm.dao.SettleDao;
import com.ssm.dao.WRewardDao;
import com.ssm.dao.WRewardExtremeDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Param;
import com.ssm.pojo.RewardDetail;
import com.ssm.pojo.Settle;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WRewardExtreme;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.DateUtil;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class RewardDetailServlet extends HttpServlet {

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
		} else if (method.equals("list")) {
			list(request, response);

		}else if (method.equals("admin_list")) {
			admin_list(request, response);

		}
	}
	
	public void admin_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message="";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String state = StringUtil.notNull(request.getParameter("state"));
				String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
				String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo=1;
				int pageSize=20;
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				RewardDetailDao wrdDao = new RewardDetailDao();
				RewardDetail wrd = new RewardDetail();
				wrd.setUserId(userId);
				if(!state.equals(""))
				wrd.setState(Integer.valueOf(state));
				if(!startTimeStr.equals("")){
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
					wrd.setStartTime(startTime);
				}
				if(!endTimeStr.equals("")){
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
					wrd.setEndTime(endTime);
				}
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = wrdDao.findByPage(wrd, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId", userId);
				request.setAttribute("state", state);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
			}else{
				error ="用户未登录，请重新登陆";
				lt++;
			}
		}finally{
			String request_dispatcher ="reward_detail_list.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="reward_detail_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="reward_detail_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="reward_detail_list.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		String message="";
		try {
			if(user!=null){
				String userId = user.getUserId();
				String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
				String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
				int pageNo=1;
				int pageSize=20;
				if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
				if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
				RewardDetailDao wrdDao = new RewardDetailDao();
				RewardDetail wrd = new RewardDetail();
				wrd.setUserId(userId);
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = wrdDao.findByPage(wrd, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId", userId);
			}else{
				message ="用户未登录，请重新登陆";
			}
		}finally{
			
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("reward_detail_list.jsp");
			dispatcher.forward(request, response);
		}
	}
	
}