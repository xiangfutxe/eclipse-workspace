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
import com.ssm.dao.ContractDao;
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
import com.ssm.pojo.Contract;
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

public class ContractServlet extends HttpServlet {

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

		}else if (method.equals("list")) {
			list(request, response);

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

		}else if (method.equals("pdf_edit")) {
			pdf_edit(request, response);

		}else if (method.equals("pdf_save")) {
			pdf_save(request, response);

		}
		else if (method.equals("pdf")) {
			pdf(request, response);

		}else if (method.equals("edit")) {
			edit(request, response);

		}else if (method.equals("update")) {
			update(request, response);

		}else if (method.equals("del")) {
			del(request, response);

		}
	}
	
	public void admin_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String contractId = StringUtil.notNull(request.getParameter("contractId"));
		String contractName = StringUtil.notNull(request.getParameter("contractName"));
		String adminName = StringUtil.notNull(request.getParameter("adminName"));
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
			Contract contract = new Contract();
			contract.setContractId(contractId);
			contract.setContractName(contractName);
			if(admin.getState()-1>0)
				contract.setDept(admin.getDeptName());
			contract.setAdminName(adminName);
			ContractDao contractDao = new ContractDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = contractDao.findByPage(contract,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("contractId", contractId);
				request.setAttribute("contractName", contractName);
				request.setAttribute("adminName", adminName);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt=1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			String request_dispatcher ="contract_list.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="contract_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="contract_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="contract_list.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String contractId = StringUtil.notNull(request.getParameter("contractId"));
		String contractName = StringUtil.notNull(request.getParameter("contractName"));
		String adminName = StringUtil.notNull(request.getParameter("adminName"));
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
			Contract contract = new Contract();
			contract.setContractId(contractId);
			contract.setContractName(contractName);
			if(admin.getState()-1>0) {
				contract.setDept(admin.getDeptName());
				contract.setLevel(2);
			}
			contract.setAdminName(adminName);
			ContractDao contractDao = new ContractDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = contractDao.findByPage(contract,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("contractId", contractId);
				request.setAttribute("contractName", contractName);
				request.setAttribute("adminName", adminName);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt=1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			String request_dispatcher ="contract_all_list.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="contract_all_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="contract_all_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="contract_all_list.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String  message= "";
		String error="";
		int lt = 0;
		try {
			if(admin!=null){
				int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
				DeptDao deptDao = new DeptDao();
				List<Dept> depts  = deptDao.findAllDept();
				request.setAttribute("depts",depts);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "数据操作异常。";
		} finally {
				String request_dispatcher = "contract_add.jsp";
				if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher = "contract_message.jsp";
				}else if(!error.equals("")) {
					request.setAttribute("error", error);
					if(lt==0) request_dispatcher ="contract_error.jsp";
					else request_dispatcher ="error_login.jsp";
				} else {
					request_dispatcher = "contract_add.jsp";
				}
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(request_dispatcher);
				dispatcher.forward(request, response);
		}

	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String  message= "";
		String error="";
		int lt = 0;
		try {
			if(admin!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
				DeptDao deptDao = new DeptDao();
				List<Dept> depts  = deptDao.findAllDept();
				request.setAttribute("depts",depts);
				ContractDao contractDao = new ContractDao();
				Contract contract = contractDao.findByName(id);
				if(contract!=null) request.setAttribute("contract",contract);
				else {
					error = "合同信息获取失败。";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = "数据操作异常。";
		} finally {
				String request_dispatcher = "contract_edit.jsp";
				if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher = "contract_message.jsp";
				}else if(!error.equals("")) {
					request.setAttribute("error", error);
					if(lt==0) request_dispatcher ="contract_error.jsp";
					else request_dispatcher ="error_login.jsp";
				} else {
					request_dispatcher = "contract_edit.jsp";
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
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String contractId= StringUtil.notNull(request.getParameter("contractId"));
	String contractName= StringUtil.notNull(request.getParameter("contractName"));
	String code= StringUtil.notNull(request.getParameter("code"));
	String partyA= StringUtil.notNull(request.getParameter("partyA"));
	String partyB= StringUtil.notNull(request.getParameter("partyB"));
	String startTimeStr= StringUtil.notNull(request.getParameter("startTime"));
	String	endTimeStr= StringUtil.notNull(request.getParameter("endTime"));
	String level= StringUtil.notNull(request.getParameter("level"));
	String adminName= StringUtil.notNull(request.getParameter("adminName"));
	String dept= StringUtil.notNull(request.getParameter("dept"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				if (StringUtil.notNull(token_old).equals(token)) {
					AdminDao adminDao = new AdminDao();
					Admin admin1 = adminDao.findAdminByName(adminName);
					if(admin1!=null) {
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr, DateUtil.yyyyMMdd).getTime());
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr, DateUtil.yyyyMMdd).getTime());
					Contract contract = new Contract();
					contract.setAdminId(admin1.getId());
					contract.setAdminName(admin1.getAdminName());
					contract.setContractId(contractId);
					contract.setContractName(contractName);
					contract.setCode(code);
					contract.setPartyA(partyA);
					contract.setPartyB(partyB);
					contract.setLevel(Integer.valueOf(level));
					contract.setEndTime(endTime);
					contract.setStartTime(startTime);
					contract.setState(1);
					contract.setDept(dept);
					contract.setEntryTime(date);
					ContractDao contractDao = new ContractDao();
					message = contractDao.save(contract);
					}else {
						error="承办人信息获取失败。";
					}
				if(message.equals("yes")) {
					message="合同保存成功，请在合同列表中查看合同。";
				}else {
					error=message;
					message="";
				}
				
				}else{
					error = "请勿重复提交数据。";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="contract_add.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="contract_add_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="contract_add_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="contract_add.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String id= StringUtil.notNull(request.getParameter("id"));
	String contractId= StringUtil.notNull(request.getParameter("contractId"));
	String contractName= StringUtil.notNull(request.getParameter("contractName"));
	String code= StringUtil.notNull(request.getParameter("code"));
	String partyA= StringUtil.notNull(request.getParameter("partyA"));
	String partyB= StringUtil.notNull(request.getParameter("partyB"));
	String startTimeStr= StringUtil.notNull(request.getParameter("startTime"));
	String	endTimeStr= StringUtil.notNull(request.getParameter("endTime"));
	String level= StringUtil.notNull(request.getParameter("level"));
	String adminName= StringUtil.notNull(request.getParameter("adminName"));
	String dept= StringUtil.notNull(request.getParameter("dept"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					Timestamp date = new Timestamp(new Date().getTime());
					AdminDao adminDao = new AdminDao();
					Admin admin1 = adminDao.findAdminByName(adminName);
					if(admin1!=null) {
					Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr, DateUtil.yyyyMMdd).getTime());
					Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr, DateUtil.yyyyMMdd).getTime());
					Contract contract = new Contract();
					contract.setId(new Long(id));
					contract.setAdminId(admin1.getId());
					contract.setAdminName(admin1.getAdminName());
					contract.setContractId(contractId);
					contract.setContractName(contractName);
					contract.setCode(code);
					contract.setPartyA(partyA);
					contract.setPartyB(partyB);
					contract.setLevel(Integer.valueOf(level));
					contract.setEndTime(endTime);
					contract.setStartTime(startTime);
					contract.setDept(dept);
					ContractDao contractDao = new ContractDao();
					message = contractDao.update(contract);
					
					if(message.equals("yes")) {
						message="合同"+contractId+"信息更新成功。";
						cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_2, date);
	
					}else {
						error=message;
						message="";
					}
					}else {
						error="承办人信息获取失败。";
					}
				}else{
					error = "请勿重复提交数据。";
				}
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="contract_message.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="contract_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="contract_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="contract_message.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}
	}
	
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String[] ids= request.getParameterValues("ids");

	
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				if(ids!=null&&ids.length>0) {
					Timestamp date = new Timestamp(new Date().getTime());
					
					ContractDao contractDao = new ContractDao();
					message = contractDao.del(ids);
					if(message.equals("yes")) {
						message="批量删除合同信息成功。";
						cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_2, date);
					}else {
						error=message;
						message="";
					}
				}else {
					error="未选择到需要删除的合同。";
				}
				
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="contract_message.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="contract_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="contract_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="contract_message.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
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
				message = agentDao.reviewYes(agent);
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
	
	public void pdf_edit(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error ="";
		int lt=0;
		try {
		if(admin!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				if(!id.equals("")){
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
			ContractDao contractDao = new ContractDao();
			Contract contract = contractDao.findByName(id);
			if(contract!=null){
				request.setAttribute("contract", contract);
			}else{
				error="未获取相应的合同信息，请重试！";
			}
				}else{
					error="未获取相应的合同编号信息。";
				}
		
		}else{
			error= "管理员用户未登陆，请重新登陆！";
			lt++;
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error= "数据保存有误，请重新录入！";
			}finally{
					String request_dispatcher ="contract_message.jsp";
					if (!message.equals("")) {
						request_dispatcher ="contract_message.jsp";
						request.setAttribute("message", message);
					} else if (!error.equals("")) {
						if(lt==1) request_dispatcher ="error_login.jsp";
						else request_dispatcher ="contract_error.jsp";
						request.setAttribute("error", error);
					}else {
						request_dispatcher ="contract_pdf_edit.jsp";
					}
					RequestDispatcher dispatcher = request
							.getRequestDispatcher(request_dispatcher);
					dispatcher.forward(request, response);
			}
		}
	
	
	
	public void pdf_save(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String token_old = (String)request.getSession().getAttribute("token");
		 DiskFileItemFactory factory = new DiskFileItemFactory();  
		 String path = request.getSession().getServletContext().getRealPath("/upload");  
		 factory.setRepository(new File(path)); 
		 factory.setSizeThreshold(1024*1024) ;  
		 ServletFileUpload upload = new ServletFileUpload(factory);  
		 String message = "";
			String error ="";
			int lt=0;
		Timestamp date = new Timestamp(new Date().getTime());
		try {
		if(admin!=null){
			 List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
			 String id = "";
			 String token = "";
			 String pdfurl = "";
			 for(FileItem item : list)  
	         {  
	             String name = item.getFieldName();  
	             if(item.isFormField())  
	             {                     
	                 String value = item.getString() ;  
	                   if(name.equals("token")){
	                	   token = value;
	                   }else if(name.equals("id")) id =value;
	             } else{  
	                 String value = item.getName() ;  
	                
	                 int start = value.lastIndexOf("\\");  
	               
	                 String filename =StringUtil.parseToString(date, DateUtil.ymdhms)+value.substring(start+1);  
	                 
	                 System.out.println("fileName:"+filename);
	                   
	                 if(name.equals("pdfurl")){
	                	 pdfurl = filename;
	                 }   
	                 OutputStream out = new FileOutputStream(new File(path,filename));  
	                 InputStream in = item.getInputStream() ;  
	                 int length = 0 ;  
	                 byte [] buf = new byte[1024] ;  
	                 while( (length = in.read(buf) ) != -1)  
	                 		{  
	                      out.write(buf, 0, length);  
	                 		}  
	                 in.close();  
	                 out.close();  
	             }  
	         }  
			 int token_new = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token_new));
			 if(StringUtil.notNull(token_old).equals(token)){
			request.getSession().setAttribute("token", String.valueOf(token));
			ContractDao contractDao = new ContractDao();

			message=contractDao.updatePdf(Long.valueOf(id), pdfurl);
			if(message.equals("yes")){
				message= "PDF上传成功！";
			}else{
				error="PDF信息保存失败";
				message="";
			}
		
				
			 }else{
				 error= "请勿重复提交数据。";
			}
		}else{
			error= "用户未登陆，请重新登陆！";
			lt++;
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message= "数据保存有误，请重新录入！";
			}finally{
				String request_dispatcher ="contract_message.jsp";
				if (!message.equals("")) {
					request_dispatcher ="contract_message.jsp";
					request.setAttribute("message", message);
				} else if (!error.equals("")) {
					if(lt==1) request_dispatcher ="error_login.jsp";
					else request_dispatcher ="contract_error.jsp";
					request.setAttribute("error", error);
				}
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(request_dispatcher);
				dispatcher.forward(request, response);
			}
		}
	
	public void pdf(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message = "";
		String error ="";
		int lt=0;
		try {
		if(admin!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				if(!id.equals("")){
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
			ContractDao contractDao = new ContractDao();
			Contract contract = contractDao.findByName(id);
			if(contract!=null){
				request.setAttribute("contract", contract);
			}else{
				error="未获取相应的合同信息，请重试！";
			}
				}else{
					error="未获取相应的合同编号信息。";
				}
		
		}else{
			error= "管理员用户未登陆，请重新登陆！";
			lt++;
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error= "数据保存有误，请重新录入！";
			}finally{
					String request_dispatcher ="contract_message.jsp";
					if (!message.equals("")) {
						request_dispatcher ="contract_message.jsp";
						request.setAttribute("message", message);
					} else if (!error.equals("")) {
						if(lt==1) request_dispatcher ="error_login.jsp";
						else request_dispatcher ="contract_error.jsp";
						request.setAttribute("error", error);
					}else {
						request_dispatcher ="contract_pdf.jsp";
					}
					RequestDispatcher dispatcher = request
							.getRequestDispatcher(request_dispatcher);
					dispatcher.forward(request, response);
			}
		}
}