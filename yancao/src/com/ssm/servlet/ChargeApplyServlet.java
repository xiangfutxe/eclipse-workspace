package com.ssm.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.ssm.dao.BankAccountDao;
import com.ssm.dao.ChargeApplyDao;
import com.ssm.dao.DeptDao;
import com.ssm.dao.OrderDao;
import com.ssm.dao.ProductDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.BankAccount;
import com.ssm.pojo.ChargeApply;
import com.ssm.pojo.Dept;
import com.ssm.pojo.Order;
import com.ssm.pojo.OrderDetailProduct;
import com.ssm.pojo.Product;
import com.ssm.pojo.User;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;
import com.ssm.utils.DateUtil;

public class ChargeApplyServlet extends HttpServlet {

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

		} else if (method.equals("admin_list")) {
			admin_list(request, response);

		}else if (method.equals("emoney_quick_add")) {
			emoney_quick_add(request, response);

		}else if (method.equals("emoney_quick_save")) {
			emoney_quick_save(request, response);

		}else if (method.equals("apply_list")) {
			apply_list(request, response);

		}else if (method.equals("apply_add")) {
			apply_add(request, response);

		}else if (method.equals("apply_save")) {
			apply_save(request, response);

		}else if (method.equals("apply_del")) {
			apply_del(request, response);

		}else if(method.equals("apply_no")){
			apply_no(request,response);
		}else if(method.equals("apply_yes")){
			apply_yes(request,response);
		}else if(method.equals("exportExcel")){
			exportExcel(request,response);
		}
	}

	public void apply_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String state = StringUtil.notNull(request.getParameter("state"));
		String typeKey = StringUtil.notNull(request.getParameter("typeKey"));
		String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
		String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(user!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			ChargeApply cy = new ChargeApply();
			cy.setUserId(user.getUserId());
			if(!typeKey.equals("")) cy.setType(Integer.valueOf(typeKey));
			if(!state.equals("")) cy.setState(Integer.valueOf(state));
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				cy.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				cy.setEndTime(endTime);
			}
			ChargeApplyDao chargeApplyDao = new ChargeApplyDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = chargeApplyDao.findByPage(cy, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("typeKey",typeKey);
				request.setAttribute("state",state);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
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
						.getRequestDispatcher("emoney_apply_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("emoney_apply.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void apply_add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String  message= "";
		try {
			if(user!=null){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					BankAccountDao bankDao = new BankAccountDao();
					List<BankAccount> coll= bankDao.findByAll();
					request.setAttribute("coll", coll);
			
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message ="系统繁忙，请稍后再试。";
			e.printStackTrace();
		} finally {
				if (!message.equals("")) {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("emoney_apply_add_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("emoney_apply_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void apply_del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String  message= "";
		String id = StringUtil.notNull(request.getParameter("id"));
		String applyId = StringUtil.notNull(request.getParameter("applyId"));

		try {
			if(user!=null){
					ChargeApplyDao cyDao = new ChargeApplyDao();
					if(cyDao.updateState(Integer.valueOf(id),0,1)>0){
						message = "申请记录删除成功，删除单号为："+applyId;
					}else{
						message = "申请记录删除失败。";
					}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message ="系统繁忙，请稍后再试。";
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("emoney_apply_message.jsp");
					dispatcher.forward(request, response);
			
		}

	}
	
	public void apply_no(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		String id = StringUtil.notNull(request.getParameter("id"));
		String applyId = StringUtil.notNull(request.getParameter("applyId"));
		try {
			if(admin!=null){
					Timestamp date = new Timestamp(new Date().getTime());
					ChargeApplyDao cyDao = new ChargeApplyDao();
					ChargeApply cy = new ChargeApply();
					cy.setId(Integer.valueOf(id));
					cy.setState(3);
					cy.setAdmin(admin.getAdminName());
					cy.setReviewTime(date);
					if(cyDao.updateChargeApply(cy)>0){
						message = "充值申请审批不通过，申请单号为："+applyId;
					}else{
						message = "充值申请审批操作失败。";
					}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message ="系统繁忙，请稍后再试。";
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("emoney_apply_message.jsp");
					dispatcher.forward(request, response);
		}
	}
	
	public void apply_yes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		String id = StringUtil.notNull(request.getParameter("id"));
		String applyId = StringUtil.notNull(request.getParameter("applyId"));
		try {
			if(admin!=null){
					Timestamp date = new Timestamp(new Date().getTime());
					ChargeApplyDao cyDao = new ChargeApplyDao();
					ChargeApply cy = new ChargeApply();
					cy.setId(Integer.valueOf(id));
					cy.setApplyId(applyId);
					cy.setState(2);
					cy.setAdmin(admin.getAdminName());
					cy.setReviewTime(date);
					message = cyDao.applyYes(cy);
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message ="系统繁忙，请稍后再试。";
			e.printStackTrace();
		} finally {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("emoney_apply_message.jsp");
					dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
	String state = StringUtil.notNull(request.getParameter("state"));
	String typeKey = StringUtil.notNull(request.getParameter("typeKey"));
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
			ChargeApply cy = new ChargeApply();
			cy.setUserId(userId);
			if(!typeKey.equals("")) cy.setType(Integer.valueOf(typeKey));
			if(!state.equals("")) cy.setState(Integer.valueOf(state));
			if(!startTimeStr.equals("")){
				Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				cy.setStartTime(startTime);
			}
			if(!endTimeStr.equals("")){
				Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
				cy.setEndTime(endTime);
			}
			ChargeApplyDao chargeApplyDao = new ChargeApplyDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = chargeApplyDao.findByPage(cy, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("typeKey",typeKey);
				request.setAttribute("state",state);
				request.setAttribute("startTime",startTimeStr);
				request.setAttribute("endTime",endTimeStr);
				request.setAttribute("userId",userId);
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
						.getRequestDispatcher("emoney_apply_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("emoney_apply.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void editDept(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
				DeptDao deptDao = new DeptDao();
				Dept dept = deptDao.findDeptById(Integer.valueOf(id));
				request.setAttribute("dept", dept);
				}else{
					message = "部门信息获取失败。";
				}
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
							.getRequestDispatcher("emoney_apply_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("emoney_apply_edit.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void emoney_quick_add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		try {
			if(admin!=null){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
			
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
							.getRequestDispatcher("emoney_quick_add_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("emoney_quick_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void apply_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	User user = (User) request.getSession().getAttribute("sys_user");
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(user!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					Timestamp date = new Timestamp(new Date().getTime());
					String amount =StringUtil.notNull(request.getParameter("amount"));
					String accountId =StringUtil.notNull(request.getParameter("accountId"));
					String password =StringUtil.notNull(request.getParameter("password2"));
					String remark =StringUtil.notNull(request.getParameter("remark"));
					String cid = "C"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(getRandom(10,99));
				if(MD5.GetMD5Code(password).equals(user.getPassword2())){	
					BankAccountDao bankDao = new BankAccountDao();
					BankAccount bank = bankDao.findById(Integer.valueOf(accountId));
					ChargeApplyDao cyDao = new ChargeApplyDao();
					ChargeApply cy  =new ChargeApply();
					cy.setApplyId(cid);
					cy.setUserId(user.getUserId());
					cy.setUserName(user.getUserName());
					cy.setAmount(Double.valueOf(amount));
					cy.setAccountId(bank.getAccountId());
					cy.setAccountName(bank.getAccountName());
					cy.setBankName(bank.getBankName());
					cy.setRemark(remark);
					cy.setType(2);
					cy.setState(1);
					cy.setApplyTime(date);
					message = cyDao.saveChargeApply(cy);
					cy=null;
				}else{
					message = "支付密码有误，请重新输入。";
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
					.getRequestDispatcher("emoney_apply_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public void emoney_quick_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					Timestamp date = new Timestamp(new Date().getTime());
					String userId =StringUtil.notNull(request.getParameter("userId")).toUpperCase();
					String amount =StringUtil.notNull(request.getParameter("amount"));
					String remark =StringUtil.notNull(request.getParameter("remark"));
					String cid = "C"+StringUtil.parseToString(date, DateUtil.ymdhms)+String.valueOf(getRandom(10,99));

				ChargeApplyDao cyDao = new ChargeApplyDao();
				ChargeApply cy  =new ChargeApply();
				cy.setApplyId(cid);
				cy.setUserId(userId);
				cy.setAmount(Double.valueOf(amount));
				cy.setRemark(remark);
				cy.setType(1);
				cy.setApplyTime(date);
				cy.setAdmin(admin.getAdminName());
				message = cyDao.saveQuickChargeApply(cy);
				cy=null;
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
					.getRequestDispatcher("emoney_quick_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public int getRandom(int min,int max){
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
}
	
	protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date = new Timestamp(new Date().getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 
		 String filename = "充值审核列表"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			List<ChargeApply>  result = new ArrayList<ChargeApply> ();
			try {
				  String userId = StringUtil.notNull(request.getParameter("userId"));
					String state = StringUtil.notNull(request.getParameter("state"));
					String typeKey = StringUtil.notNull(request.getParameter("typeKey"));
					String startTimeStr = StringUtil.notNull(request.getParameter("startTime"));
					String endTimeStr = StringUtil.notNull(request.getParameter("endTime"));
					ChargeApply cy = new ChargeApply();
					cy.setUserId(userId);
					if(!typeKey.equals("")) cy.setType(Integer.valueOf(typeKey));
					if(!state.equals("")) cy.setState(Integer.valueOf(state));
					if(!startTimeStr.equals("")){
						Timestamp startTime = new Timestamp(StringUtil.parseToDate(startTimeStr+" 00:00:00", DateUtil.yyyyMMddHHmmss).getTime());
						cy.setStartTime(startTime);
					}
					if(!endTimeStr.equals("")){
						Timestamp endTime = new Timestamp(StringUtil.parseToDate(endTimeStr+" 23:59:59", DateUtil.yyyyMMddHHmmss).getTime());
						cy.setEndTime(endTime);
					}
					ChargeApplyDao chargeApplyDao = new ChargeApplyDao();
					
				result = chargeApplyDao.findByList(cy);
				double sum = 0;
				String[][] content = new String[result.size()+2][13];
				content[0][0]="序号";
				content[0][1]="申请编号";
				content[0][2]="会员编号";
				content[0][3]="会员姓名";
				content[0][4]="充值金额";
				content[0][5]="充值类型";
				content[0][6]="收款账户信息";
				content[0][7]="申请时间";
				content[0][8]="审批人";
				content[0][9]="审核时间";
				content[0][10]="当前状态";
				content[0][11]="备注";
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.notNull(result.get(i).getApplyId());
					content[i+1][2]  = StringUtil.notNull(result.get(i).getUserId());
					content[i+1][3]  = StringUtil.notNull(result.get(i).getUserName());
					content[i+1][4]  = StringUtil.decimalFormat(result.get(i).getAmount());
					String typestr="";
					if(result.get(i).getType()==1) typestr="现金充值";
					else typestr="汇款充值";
					content[i+1][5]  =  StringUtil.notNull(typestr);
					content[i+1][6]  = StringUtil.notNull(result.get(i).getAccountName())+"|"+StringUtil.notNull(result.get(i).getAccountId())+"|"+StringUtil.notNull(result.get(i).getBankName());
					content[i+1][7] = StringUtil.parseToString(result.get(i).getApplyTime(), DateUtil.yyyyMMddHHmmss);
					content[i+1][8] =StringUtil.notNull(result.get(i).getAdmin());
					content[i+1][9] = StringUtil.parseToString(result.get(i).getApplyTime(), DateUtil.yyyyMMddHHmmss);
					String stateStr ="";
					if(result.get(i).getState()==0) stateStr = "已删除";
					else if(result.get(i).getState()==1) stateStr = "待审批";
					else if(result.get(i).getState()==2) stateStr = "审批通过";
					else if(result.get(i).getState()==3) stateStr = "审批不通过";
					content[i+1][10] = stateStr;
					content[i+1][11] = StringUtil.notNull(result.get(i).getRemark());
					sum = ArithUtil.add(sum,result.get(i).getAmount());
				}
				int h = result.size()+1;
				content[h][0]="-";
				content[h][1]="合计";
				content[h][2]="-";
				content[h][3]="-";
				content[h][4]=StringUtil.decimalFormat(sum);;
				content[h][5]="-";
				content[h][6]="-";
				content[h][7]="-";
				content[h][8]="-";
				content[h][9]="-";
				content[h][10]="-";
				content[h][11]="-";
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("充值申请");  
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