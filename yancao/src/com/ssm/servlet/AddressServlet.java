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

import com.ssm.dao.AddressDao;
import com.ssm.dao.CenterDao;
import com.ssm.dao.ProductDao;
import com.ssm.dao.UserDao;
import com.ssm.pojo.Address;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Center;
import com.ssm.pojo.Product;
import com.ssm.pojo.User;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.utils.Constants;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class AddressServlet extends HttpServlet {

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
		}else if (method.equals("updateTag")) {
			updateTag(request, response);
		}else if (method.equals("select")) {
			select(request, response);
		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		String tag =  StringUtil.notNull(request.getParameter("tag"));
		request.getSession().setAttribute("tag", tag);
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(user!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			Address adr = new Address();
			adr.setUserId(user.getUserId());
			adr.setState(1);
				AddressDao adrDao = new AddressDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = adrDao.findByPage(adr,pager);
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
						.getRequestDispatcher("adr_msg.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("adr_list.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		try {
			if(user!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					AddressDao adrDao = new AddressDao();
					Address  adr = adrDao.findById(Integer.valueOf(id));
					request.setAttribute("address", adr);
				
				}else{
					message = "收获地址信息获取失败。";
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
							.getRequestDispatcher("adr_msg.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("adr_edit.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		String id = StringUtil.notNull(request.getParameter("id"));
		
		String message = "";
		try {
			if(user!=null){
				AddressDao adrDao = new AddressDao();
				adrDao.delete(Integer.valueOf(id));
				message = "删除地址成功。";
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			list(request,response);
			
		}

	}
	
	
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	
		String message = "";
		try {
			if(user!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					String id = StringUtil.notNull(request.getParameter("id"));
					String reciver = StringUtil.notNull(request.getParameter("reciver"));
					String address = StringUtil.notNull(request.getParameter("address"));
					String phone = StringUtil.notNull(request.getParameter("phone"));
					String province = StringUtil.notNull(request.getParameter("province"));
					String city = StringUtil.notNull(request.getParameter("city"));
					String area = StringUtil.notNull(request.getParameter("area"));
					Timestamp date = new Timestamp(new Date().getTime());

					AddressDao adrDao = new AddressDao();
				Address adr = new Address();
				adr.setId(Integer.valueOf(id));
				adr.setReciver(reciver);
				adr.setAddress(address);
				adr.setPhone(phone);
				adr.setProvince(province);
				adr.setCity(city);
				adr.setArea(area);
				adr.setEndTime(date);
				message = adrDao.updateAdr(adr);
				message = "地址信息修改成功。";
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
					.getRequestDispatcher("adr_msg.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user = (WeixinUserInfo) request.getSession().getAttribute(Constants.USER_SESSION);
		String tag = StringUtil.notNull(request.getParameter("tag"));
		request.setAttribute("tag", tag);
		String  message= "";
		try {
			if(user!=null){
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
							.getRequestDispatcher("adr_msg.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("adr_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	WeixinUserInfo user = (WeixinUserInfo)request.getSession().getAttribute(Constants.USER_SESSION);
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(user!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					String reciver = StringUtil.notNull(request.getParameter("reciver"));
					String address = StringUtil.notNull(request.getParameter("address"));
					String phone = StringUtil.notNull(request.getParameter("phone"));
					String province = StringUtil.notNull(request.getParameter("province"));
					String city = StringUtil.notNull(request.getParameter("city"));
					String area = StringUtil.notNull(request.getParameter("area"));
					Timestamp date = new Timestamp(new Date().getTime());
					UserDao userdao = new UserDao();
					User user1 = userdao.findUsersByUserId(user.getUserId());
					if(user1!=null) {
						AddressDao adrDao = new AddressDao();
						Address adr = new Address();
						adr.setUid(user1.getId());
						adr.setUserId(user.getUserId());
						adr.setReciver(reciver);
						adr.setAddress(address);
						adr.setPhone(phone);
						adr.setProvince(province);
						adr.setCity(city);
						adr.setState(1);
						adr.setArea(area);
						adr.setEntryTime(date);
						adr.setEndTime(date);
						message = adrDao.saveAdr(adr);
						adr=null;
					}else{
						message = "会员信息获取失败。";
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
			list(request,response);
		}
	}
	
	public void select(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = StringUtil.notNull(request.getParameter("id"));
		String tag = StringUtil.notNull((String)request.getSession().getAttribute("tag"));
		try {
			AddressDao adrDao = new AddressDao();
			Address address = adrDao.findById(Integer.valueOf(id));
			int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
			request.getSession().setAttribute("token", String.valueOf(token_new));
			request.setAttribute("address", address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(tag.equals("1")) {
				OrderServlet os = new OrderServlet();
				os.shop_confirm(request, response);
			}else if(tag.equals("4")) {
				OrderDeliveryServlet os = new OrderDeliveryServlet();
				os.stock_cart_confirm(request, response);
			}else if(tag.equals("2")) {
				OrderServlet os = new OrderServlet();
				os.shop_cash_confirm(request, response);
			}else if(tag.equals("3")) {
				OrderServlet os = new OrderServlet();
				os.shop_integral_confirm(request, response);
			}else if(tag.equals("5")) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("agent_apply_shop.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void updateTag(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WeixinUserInfo user = (WeixinUserInfo)request.getSession().getAttribute(Constants.USER_SESSION);
		String id = StringUtil.notNull(request.getParameter("id"));
		try {
			if(user!=null){
				AddressDao adrDao = new AddressDao();
				if(!id.equals(""))
				adrDao.updateTag(Integer.valueOf(id));
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}