package com.ssm.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONObject;

import com.ssm.pojo.User;
import com.ssm.dao.AdminDao;
import com.ssm.dao.AdminLogDao;
import com.ssm.dao.AgentApplyDao;
import com.ssm.dao.DeptDao;
import com.ssm.dao.JobDao;
import com.ssm.dao.ProductDao;
import com.ssm.dao.UserDao;
import com.ssm.dao.WRewardDao;
import com.ssm.dao.WRewardExtremeDao;
import com.ssm.dao.WSettleDao;
import com.ssm.pojo.Address;
import com.ssm.pojo.Admin;
import com.ssm.pojo.AgentApply;
import com.ssm.pojo.Center;
import com.ssm.pojo.Dept;
import com.ssm.pojo.Job;
import com.ssm.pojo.Product;
import com.ssm.pojo.WReward;
import com.ssm.pojo.WRewardExtreme;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.ConstantsLog;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class AgentApplyServlet extends HttpServlet {

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

		}else if (method.equals("admin_review_yes")) {
			admin_review_yes(request, response);

		}else if (method.equals("admin_review_no")) {
			admin_review_no(request, response);

		}else if (method.equals("add")) {
			add(request, response);

		}else if (method.equals("confirm")) {
			confirm(request, response);

		}else if (method.equals("save")) {
			save(request, response);

		}
	}
	
	public void admin_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		String error = "";
		int lt = 0;
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			AgentApply agent = new AgentApply();
			agent.setUserId(userId);
			
			AgentApplyDao agentDao = new AgentApplyDao();
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				agent.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				agent.setEndTime(endTime);
			}
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = agentDao.findByPage(agent,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId", userId);
				request.setAttribute("startTime", startTimeStr);
				request.setAttribute("endTime", endTimeStr);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt=1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			String request_dispatcher ="agent_apply_list.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="agent_apply_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="agent_apply_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="agent_apply_list.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		String  message= "";
		String error="";
		int lt = 0;
		try {
			if(user!=null){
					UserDao userDao = new UserDao();
					User user1 = userDao.findUsersByUserId(user.getUserId());
					if(user1!=null) {
					
							AgentApplyDao agentDao = new AgentApplyDao();
							List<AgentApply> result = agentDao.findListByUserId(user1.getUserId(), 1);
							if(result!=null&&result.size()>0) user1.setAgentTag(4);
							request.setAttribute("user1", user1);
							
							int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
							request.getSession().setAttribute("token", String.valueOf(token_new));
						
					}else {
						message="您的信息获取失败，请重新登陆。";
					}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "数据操作异常。";
		} finally {
				String request_dispatcher = "agent_apply_add.jsp";
				if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher = "agent_apply_message.jsp";
				} else {
					request_dispatcher = "agent_apply_add.jsp";
				}
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(request_dispatcher);
				dispatcher.forward(request, response);
		}

	}
	
	public void confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
	String userName = StringUtil.notNull(request.getParameter("userName"));
	String province = StringUtil.notNull(request.getParameter("province"));
	String city = StringUtil.notNull(request.getParameter("city"));
	String rankJoinStr = StringUtil.notNull(request.getParameter("rankJoin"));
	String area = StringUtil.notNull(request.getParameter("area"));
	String mobile = StringUtil.notNull(request.getParameter("mobile"));
	String weixin = StringUtil.notNull(request.getParameter("weixin"));
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	
		String message = "";
		try {
			if(user!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				AgentApply agent = new AgentApply();
				AgentApplyDao agentDao = new AgentApplyDao();
				String applyId = "a"+StringUtil.parseToString(date, DateUtil.ymdhms)+cs.getRandom(1, 9);
				int rankJoin = 0;
				if(!rankJoinStr.equals("")) rankJoin = Integer.valueOf(rankJoinStr);
				int type=0;
				if(rankJoin==4)type=1;
				else if(rankJoin==5) type=2;
				agent.setApplyId(applyId);
				agent.setUserId(user.getUserId());
				agent.setUserName(userName);
				agent.setType(Integer .valueOf(type));
				agent.setProvince(province);
				agent.setCity(city);
				agent.setRankJoin(rankJoin);
				agent.setArea(area);
				agent.setMobile(mobile);
				agent.setWeixin(weixin);
				agent.setState(1);
				agent.setEntryTime(date);
				request.getSession().setAttribute("session_agent", agent);
				List<Product> plist = new ArrayList<Product>();
				ProductDao productDao = new ProductDao();
				Product sel_pro = new Product();
				if(rankJoin==4)  sel_pro.setProductSort("城市合伙人");
				else if(rankJoin==5) sel_pro.setProductSort("联创合伙人");
				sel_pro.setIsHide(0);
				sel_pro.setState(1);
				List<Product> prolist = productDao.findByProduct(sel_pro);
				Product product = new Product();
				if(prolist!=null&&prolist.size()>0) {
					product = prolist.get(0);
				}else product=null;
				if(product!=null) {
					product.setNum(1);
					plist.add(product);
					request.getSession().setAttribute("shop_agent_list",plist);
				}else{
					message = "未能获取相应的产品信息。";
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
						.getRequestDispatcher("agent_apply_shop.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
	String payTypeStr= StringUtil.notNull(request.getParameter("zhifu"));
	String adrId= StringUtil.notNull(request.getParameter("adrId"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	
		String message = "";
		try {
			if(user!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				if (StringUtil.notNull(token_old).equals(token)) {
					AgentApply agent = (AgentApply) request.getSession().getAttribute("session_agent");
					List<Product> plist = (List<Product>) request.getSession().getAttribute("shop_agent_list");
					if(plist!=null&&plist.size()>0) {
					if(agent!=null) {
				AgentApplyDao agentDao = new AgentApplyDao();
				int payType =1;
				if(!payTypeStr.equals("")) payType = Integer.valueOf(payTypeStr);
				String applyId = "a"+StringUtil.parseToString(date, DateUtil.ymdhms)+cs.getRandom(1, 9);
				agent.setApplyId(applyId);
				agent.setEntryTime(date);
				String orderId = StringUtil.parseToString(date, DateUtil.ymdhms)+cs.getRandom(1, 9);
				message = agentDao.saveOrders(user.getUserId(),adrId, orderId, payType,agent, plist, date);
				if(message.equals("yes")) {
					message="代理商申请成功，请尽快支付订单。";
				}
				}else{
					message = "未能获取代理商的基本信息。";
				}
					}else{
						message = "未能获取相应的产品信息。";
					}
				}else{
					message = "请勿重复提交数据。";
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
						.getRequestDispatcher("agent_apply_message.jsp");
				dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_review_yes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String id = StringUtil.notNull(request.getParameter("id"));
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				AgentApply agent = new AgentApply();
				AgentApplyDao agentDao = new AgentApplyDao();
				agent.setReviewerId(admin.getAdminName());
				agent.setId(Integer.valueOf(id));
				agent.setState(2);
				agent.setReviewTime(date);
				agent.setEntryTime(date);
				message = agentDao.reviewYes(agent);
				if(message.equals("yes")) {
					message="区域代理申请审批通过。";
				}else{
					error = message;
					message="";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作有误。";
		} finally {
			
			String request_dispatcher = "agent_apply_message.jsp";
			if (!message.equals("")) {
				request.setAttribute("message", message);
				request_dispatcher = "agent_apply_message.jsp";
			}else if(!error.equals("")){
				request.setAttribute("error", error);
				if(lt==0)request_dispatcher = "agent_apply_error.jsp";
				else request_dispatcher = "error_login.jsp";
			}else {
				request_dispatcher = "agent_apply_message.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_review_no(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String id = StringUtil.notNull(request.getParameter("id"));
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				AgentApply agent = new AgentApply();
				AgentApplyDao agentDao = new AgentApplyDao();
				agent.setReviewerId(admin.getAdminName());
				agent.setId(Integer.valueOf(id));
				agent.setState(0);
				agent.setReviewTime(date);
				agent.setEntryTime(date);
				message = agentDao.reviewNo(agent);
				if(message.equals("yes")) {
					message="区域代理申请审批已拒绝。";
				}else{
					error = message;
					message="";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作有误。";
		} finally {
			
			String request_dispatcher = "agent_apply_message.jsp";
			if (!message.equals("")) {
				request.setAttribute("message", message);
				request_dispatcher = "agent_apply_message.jsp";
			}else if(!error.equals("")){
				request.setAttribute("error", error);
				if(lt==0)request_dispatcher = "agent_apply_error.jsp";
				else request_dispatcher = "error_login.jsp";
			}else {
				request_dispatcher = "agent_apply_message.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	
}