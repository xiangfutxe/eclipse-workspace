package com.ssm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssm.dao.BankAccountDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.BankAccount;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class BankAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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

		}else if (method.equals("add")) {
			add(request, response);

		}else if (method.equals("save")) {
			save(request, response);

		}else if (method.equals("edit")) {
			edit(request, response);

		}else if (method.equals("update")) {
			update(request, response);

		}else if (method.equals("del")) {
			del(request, response);

		}else if (method.equals("start")) {
			start(request, response);
		}else if (method.equals("delAll")) {
			delAll(request, response);

		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			BankAccount bank = new BankAccount();
			bank.setAccountId("");
			BankAccountDao bankDao = new BankAccountDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = bankDao.findByPage(bank,pager);
				request.setAttribute("pager", pager);
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
						.getRequestDispatcher("bank_account_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("bank_account.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					BankAccountDao bankDao = new BankAccountDao();
					BankAccount bank = bankDao.findById(Integer.valueOf(id));
					request.setAttribute("bankAccount", bank);
				
				}else{
					message = "收款账号信息获取失败。";
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
							.getRequestDispatcher("bank_account_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("bank_account_edit.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());

				BankAccountDao bankDao = new BankAccountDao();
				BankAccount bank = new BankAccount();
				bank.setId(Integer.valueOf(id));
				bank.setState(0);
				bank.setEndTime(date);
				message = bankDao.updateBankAccount(bank);
				if(message.equals("")) message="系统繁忙，请稍后再试。";
				else message = "收款账户停用成功。";
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("bank_account_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void delAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				BankAccountDao bankDao = new BankAccountDao();
				BankAccount bank = new BankAccount();
				bank.setId(Integer.valueOf(id));
				bank.setState(0);
				bank.setEndTime(date);
				if(bankDao.deleteAll(id)>0)
					message="收款账户删除成功。";
				else message = "收款账户删除失败。";
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("bank_account_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void start(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		try {
			if(admin!=null){
				Timestamp date = new Timestamp(new Date().getTime());
				BankAccountDao bankDao = new BankAccountDao();
				BankAccount bank = new BankAccount();
				bank.setId(Integer.valueOf(id));
				bank.setState(1);
				bank.setEndTime(date);
				message = bankDao.updateBankAccount(bank);
				if(message.equals("")) message="系统繁忙，请稍后再试。";
				else message = "收款账户启用成功。";
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("bank_account_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	
		String message = "";
		String error = "";
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					String id = StringUtil.notNull(request.getParameter("id"));
					String accountName = StringUtil.notNull(request.getParameter("accountName"));
					String accountId  = StringUtil.notNull(request.getParameter("accountId"));
					String bankName  = StringUtil.notNull(request.getParameter("bankName"));
					if(accountName.equals("")){ error = "账户名称不能为空！";}
					else if(accountId.equals("")){ error = "银行账号不能为空！";}
					else if(bankName.equals("")){ error = "开户银行不能为空！";}
					if(error.equals("")){
					Timestamp date = new Timestamp(new Date().getTime());

					BankAccountDao bankDao = new BankAccountDao();
					BankAccount bank = new BankAccount();
					bank.setId(Integer.valueOf(id));
					bank.setAccountId(accountId);
					bank.setAccountName(accountName);
					bank.setBankName(bankName);
					bank.setEndTime(date);
				message = bankDao.updateBankAccount(bank);
				if(message.equals("")) message="系统繁忙，请稍后再试。";
				bank=null;
					}else {
						request.setAttribute("message",error);
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
					.getRequestDispatcher("bank_account_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
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
			message="系统繁忙，请稍后再试。";
			e.printStackTrace();
		} finally {
				if (!message.equals("")) {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("bank_account_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("bank_account_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		String error = "";
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					String accountName = StringUtil.notNull(request.getParameter("accountName"));
					String accountId  = StringUtil.notNull(request.getParameter("accountId"));
					String bankName  = StringUtil.notNull(request.getParameter("bankName"));
					if(accountName.equals("")){ error = "账户名称不能为空！";}
					else if(accountId.equals("")){ error = "银行账号不能为空！";}
					else if(bankName.equals("")){ error = "开户银行不能为空！";}
					if(error.equals("")){
					Timestamp date = new Timestamp(new Date().getTime());
					BankAccountDao bankDao = new BankAccountDao();
				BankAccount bank = new BankAccount();
				bank.setAccountId(accountId);
				bank.setAccountName(accountName);
				bank.setBankName(bankName);
				bank.setState(1);
				bank.setEntryTime(date);
				bank.setEndTime(date);
				message = bankDao.saveBankAccount(bank);
				if(message.equals("")) message="系统繁忙，请稍后再试。";
				bank=null;
					}else {
						request.setAttribute("message",error);
					}
				}else{
					message = "请勿重复提交数据。";
				}
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="系统繁忙，请稍后再试。";
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("bank_account_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
}