package com.ssm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ssm.dao.ProductTypeDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.ProductType;
import com.ssm.utils.Constants;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class ProductTypeServlet extends HttpServlet {

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

		}else if (method.equals("removeProductType")) {
			removeProductType(request, response);

		}else if (method.equals("editProductType")) {
			editProductType(request, response);

		}else if (method.equals("isProductTypeExit")) {
			isProductTypeExit(request, response);

		}else if (method.equals("isProductTypeExitUpdate")) {
			isProductTypeExitUpdate(request, response);

		}else if (method.equals("updateProductType")) {
			updateProductType(request, response);

		}else if (method.equals("addProductType")) {
			addProductType(request, response);

		}else if (method.equals("saveProductType")) {
			saveProductType(request, response);

		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String typeName = StringUtil.notNull(request.getParameter("typeName"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			ProductType ProductType = new ProductType();
			ProductType.setTypeName(typeName);
				ProductTypeDao ProductTypeDao = new ProductTypeDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = ProductTypeDao.findProductType(ProductType,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("typeName", typeName);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="product_type.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="product_type_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="product_type_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="product_type.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void editProductType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
				ProductTypeDao ProductTypeDao = new ProductTypeDao();
				ProductType ProductType = ProductTypeDao.findProductTypeById(Integer.valueOf(id));
				request.setAttribute("productType", ProductType);
				}else{
					error = "分类信息获取失败。";
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
			String request_dispatcher ="product_type_edit.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="product_type_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="product_type_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="product_type_edit.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void removeProductType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String[] ids = request.getParameterValues("ids");
		String message = "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
				if(ids!=null&&ids.length>0) {
					ProductTypeDao ProductTypeDao = new ProductTypeDao();
					ProductTypeDao.deleteAll(ids);
					message = "批量删除分类成功。";
				}else {
					error="未选择需要删除的信息。";
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
			String request_dispatcher ="product_type.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="product_type_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="product_type_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
	
	public void isProductTypeExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String typeName = request.getParameter("typeName");
		int tag = 0;
		try {
				if (StringUtil.notNull(typeName).equals("")) {
					tag = 0;
				} else {
					ProductTypeDao ProductTypeDao = new ProductTypeDao();
					ProductType ProductType1 = ProductTypeDao.findProductTypeByName(typeName);
					if(ProductType1!=null){
							tag=1;
						
					}else{
						tag=2;
					}
			} 
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("tag", tag);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void isProductTypeExitUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String typeName = request.getParameter("typeName");
		String id = request.getParameter("id");
		System.out.println("typeName:"+typeName+";id:"+id);
		int tag = 0;
		try {
				if (StringUtil.notNull(typeName).equals("")) {
					tag = 0;
				} else {
					ProductTypeDao ProductTypeDao = new ProductTypeDao();
					ProductType ProductType1 = ProductTypeDao.findProductTypeByName(typeName);
					if(ProductType1!=null){
						if(String.valueOf(ProductType1.getId()).equals(id)){
							tag=2;
						}else{
							tag=1;
						}
					}else{
						tag=2;
					}
			} 
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("tag", String.valueOf(tag));
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void updateProductType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String typeName = StringUtil.notNull(request.getParameter("typeName"));
	String id = StringUtil.notNull(request.getParameter("id"));
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
				ProductTypeDao ProductTypeDao = new ProductTypeDao();
				ProductType ProductType = new ProductType();
				ProductType.setId(Integer.valueOf(id));
				ProductType.setTypeName(typeName);
				ProductTypeDao.updateProductType(ProductType);
				message = "分类修改成功。";
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
		} finally {
			String request_dispatcher ="product_type.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="product_type_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="product_type_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="product_type_message.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void addProductType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String  message= "";
		String error="";
		int lt=0;
		try {
			if(admin!=null){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
			
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="product_type.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="product_type_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="product_type_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="product_type_add.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void saveProductType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String typeName = StringUtil.notNull(request.getParameter("typeName"));
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
				ProductTypeDao ProductTypeDao = new ProductTypeDao();
				ProductType ProductType = new ProductType();
				ProductType.setTypeName(typeName);
				ProductType.setState("1");
				ProductType.setEntryTime(date);
				ProductType.setEndTime(date);
				message = ProductTypeDao.saveProductType(ProductType);
				ProductType=null;
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
		} finally {
			String request_dispatcher ="product_type.jsp";
			if (!message.equals("")) {
					request.setAttribute("message", message);
					request_dispatcher ="product_type_message.jsp";
			}else if(!error.equals("")) {
				request.setAttribute("error", error);
				if(lt==0) request_dispatcher ="product_type_error.jsp";
				else request_dispatcher ="error_login.jsp";
			}else {
				request_dispatcher ="product_type_message.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
}