package com.ssm.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
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

import com.ssm.dao.CenterDao;
import com.ssm.dao.ChargeApplyDao;
import com.ssm.dao.UserDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Center;
import com.ssm.pojo.ChargeApply;
import com.ssm.pojo.User;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.DateUtil;
import com.ssm.utils.MD5;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;

public class CenterServlet extends HttpServlet {

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

		} else if (method.equals("list_hide")) {
			list_hide(request, response);

		}else if (method.equals("removeCenter")) {
			removeCenter(request, response);

		}else if (method.equals("admin_edit")) {
			editCenter(request, response);

		}else if (method.equals("isCenterExit")) {
			isCenterExit(request, response);

		}else if (method.equals("admin_edit_save")) {
			admin_edit_save(request, response);

		}else if (method.equals("admin_add")) {
			admin_add(request, response);

		}else if (method.equals("admin_add_2")) {
			admin_add_2(request, response);

		}else if (method.equals("saveCenter")) {
			saveCenter(request, response);
		}else if(method.equals("admin_up")){
			admin_up(request,response);
			
		}else if(method.equals("admin_up_save")){
			admin_up_save(request,response);
			
		}else if(method.equals("admin_close")){
			admin_close(request,response);
			
		}else if(method.equals("admin_open")){
			admin_open(request,response);
			
		}else if(method.equals("admin_center_hide")){
			admin_center_hide(request,response);
		}else if(method.equals("admin_center_hide_reset")){
			admin_center_hide_reset(request,response);
		}else if(method.equals("detail")){
			detail(request,response);
		}else if(method.equals("exportExcel")){
			exportExcel(request,response);
		}else if (method.equals("centerIdAjax")) {
			centerIdAjax(request, response);

		}
	}
	
	public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		try {
			if(user!=null){
				
			}
		}finally{
			RequestDispatcher dispatcher = request.getRequestDispatcher("center_detail.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String state = StringUtil.notNull(request.getParameter("state"));
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
			Center Center = new Center();
			Center.setUserId(userId);
			if(!state.equals(""))
			Center.setState(Integer.valueOf(state));
			Center.setIsHide(0);
				CenterDao centerDao = new CenterDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = centerDao.findCenterByPage(Center,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId", userId);
				request.setAttribute("state", state);
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
						.getRequestDispatcher("center_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("center_list.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void list_hide(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String state = StringUtil.notNull(request.getParameter("state"));

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
			Center Center = new Center();
			Center.setUserId(userId);
			if(!state.equals(""))
				Center.setState(Integer.valueOf(state));
			Center.setIsHide(1);
				CenterDao centerDao = new CenterDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = centerDao.findCenterByPage(Center,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("userId", userId);
				request.setAttribute("state", state);
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
						.getRequestDispatcher("center_hide_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("center_hide_list.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void editCenter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
				CenterDao CenterDao = new CenterDao();
				Center Center = CenterDao.findCenterById(Integer.valueOf(id));
				request.setAttribute("center", Center);
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
							.getRequestDispatcher("center_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("center_edit.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void removeCenter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String ids = StringUtil.notNull(request.getParameter("ids"));
		String message = "";
		try {
			if(admin!=null){
				CenterDao centerDao = new CenterDao();
				centerDao.deleteAll(ids);
				message = "批量删除部门成功。";
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("center_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void isCenterExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
		int tag = 0;
		try {
				if (StringUtil.notNull(userId).equals("")) {
					tag = 0;
				} else {
					CenterDao centerDao = new CenterDao();
					Center Center1 = centerDao.findCenterByUserId(userId);
					if(Center1!=null){
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
	

	
	public void admin_edit_save(HttpServletRequest request, HttpServletResponse response)
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
					String id = StringUtil.notNull(request.getParameter("id"));
					String adr = StringUtil.notNull(request.getParameter("address"));
					String phone = StringUtil.notNull(request.getParameter("phone"));
					String centerName = StringUtil.notNull(request.getParameter("centerName"));
					String province = StringUtil.notNull(request.getParameter("province"));
					String city = StringUtil.notNull(request.getParameter("city"));
					String area = StringUtil.notNull(request.getParameter("area"));
					String typeForm = StringUtil.notNull(request.getParameter("typeForm"));
					String license = StringUtil.notNull(request.getParameter("license"));
					String measure = StringUtil.notNull(request.getParameter("measure"));
					
				CenterDao CenterDao = new CenterDao();
				Center Center = new Center();
				Center.setId(Integer.valueOf(id));
				Center.setAddress(adr);
				Center.setPhone(phone);
				Center.setCenterName(centerName);
				Center.setProvince(province);
				Center.setTypeForm(Integer.valueOf(typeForm));
				Center.setLicense(license);
				Center.setCity(city);
				Center.setArea(area);
				Center.setMeasure(measure);
				CenterDao.updateCenter(Center);
				message = "店铺资料修改成功。";
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
					.getRequestDispatcher("center_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_add(HttpServletRequest request, HttpServletResponse response)
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
							.getRequestDispatcher("center_add_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("center_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void admin_add_2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		try {
			if(admin!=null){
					String userId=StringUtil.notNull(request.getParameter("userId")).toUpperCase();
					UserDao userDao = new UserDao();
					User user1 = userDao.findByUserId(userId);
					if(user1!=null){
						CenterDao centerDao = new CenterDao();
						Center center = centerDao.findCenterByUserId(userId);
						if(center==null){
							int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
							request.getSession().setAttribute("token", String.valueOf(token));
							request.setAttribute("user1", user1);
						}else{
							message = "该会员已经有店铺资格，请核实。";
						}
					}else{
						message = "会员信息获取失败。";
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
							.getRequestDispatcher("center_add_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("center_add_2.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void saveCenter(HttpServletRequest request, HttpServletResponse response)
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
					String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
					String adr = StringUtil.notNull(request.getParameter("address"));
					String phone = StringUtil.notNull(request.getParameter("phone"));
					String centerName = StringUtil.notNull(request.getParameter("centerName"));
					String province = StringUtil.notNull(request.getParameter("province"));
					String city = StringUtil.notNull(request.getParameter("city"));
					String area = StringUtil.notNull(request.getParameter("area"));
					String typeForm = StringUtil.notNull(request.getParameter("typeForm"));
					String license = StringUtil.notNull(request.getParameter("license"));
					String measure = StringUtil.notNull(request.getParameter("measure"));
					String password = StringUtil.notNull(request.getParameter("password"));

					String type = StringUtil.notNull(request.getParameter("type"));
					UserDao userDao = new UserDao();
					User user1 = userDao.findByUserId(userId);
					if(user1!=null){
					 int length= user1.getUserId().length();
					 String centerId = "D"+user1.getUserId().substring(2,length);
					 Timestamp date = new Timestamp(new Date().getTime());
				CenterDao CenterDao = new CenterDao();
				Center Center = new Center();
				Center.setCenterId(centerId);
				Center.setuId(user1.getId());
				Center.setUserId(user1.getUserId());
				Center.setUserName(user1.getUserName());
				Center.setAddress(adr);
				Center.setPhone(phone);
				Center.setCenterName(centerName);
				Center.setProvince(province);
				Center.setPassword(MD5.GetMD5Code(password));
				Center.setTypeForm(Integer.valueOf(typeForm));
				Center.setLicense(license);
				Center.setCity(city);
				Center.setArea(area);
				Center.setMeasure(measure);
				Center.setEntryTime(date);
				Center.setType(Integer.valueOf(type));
				Center.setState(2);
				User user_u = new User();
				user_u.setId(user1.getId());
				user_u.setCenterId(2);
				user_u.setCenterType(Center.getType());
				user_u.setIdByBelongCenter(user1.getId());
				user_u.setUserIdByBelongCenter(user1.getUserId());
				message = CenterDao.saveCenter(Center,user_u);
				Center=null;
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
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("center_add_message.jsp");
			dispatcher.forward(request, response);
			
		}
	}
	
	public void admin_up(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
				CenterDao CenterDao = new CenterDao();
				Center Center = CenterDao.findCenterById(Integer.valueOf(id));
				request.setAttribute("center", Center);
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
							.getRequestDispatcher("center_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("center_up.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void admin_up_save(HttpServletRequest request, HttpServletResponse response)
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
					String id = StringUtil.notNull(request.getParameter("id"));
					String type = StringUtil.notNull(request.getParameter("typeNew"));
				CenterDao CenterDao = new CenterDao();
				Center Center = new Center();
				Center.setId(Integer.valueOf(id));
				Center.setType(Integer.valueOf(type));
				message = CenterDao.updateCenter(Center);
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
					.getRequestDispatcher("center_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_close(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	
		String message = "";
		try {
			if(admin!=null){
					String id = StringUtil.notNull(request.getParameter("id"));
				CenterDao CenterDao = new CenterDao();
				Center Center = new Center();
				Center.setId(Integer.valueOf(id));
				Center.setState(0);
				message = CenterDao.updateCenter(Center);
				
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("center_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_open(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	
		String message = "";
		try {
			if(admin!=null){
					String id = StringUtil.notNull(request.getParameter("id"));
				CenterDao CenterDao = new CenterDao();
				Center Center = new Center();
				Center.setId(Integer.valueOf(id));
				Center.setState(1);
				message = CenterDao.updateCenter(Center);
				
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("center_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_center_hide(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	
		String message = "";
		try {
			if(admin!=null){
					String[] ids =request.getParameterValues("ids");
				CenterDao CenterDao = new CenterDao();
				
				if(CenterDao.updateHide(ids, 1)>0){
					message="店铺隐藏成功。";
				}else{
					message="店铺隐藏更新失败。";
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
					.getRequestDispatcher("center_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void admin_center_hide_reset(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	
		String message = "";
		try {
			if(admin!=null){
					String[] ids =request.getParameterValues("ids");
				CenterDao CenterDao = new CenterDao();
				
				if(CenterDao.updateHide(ids, 0)>0){
					message="店铺显示成功。";
				}else{
					message="店铺显示更新失败。";
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
					.getRequestDispatcher("center_hide_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
	    response.setHeader("Connection", "close");  
	    response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");  
		Timestamp date = new Timestamp(new Date().getTime());
		 String str = StringUtil.parseToString(date, DateUtil.yyyyMMddHHmmss);
		 
		 String filename = "服务中心列表"+str+".xls";  
		 filename = cs.encodeFileName(request, filename);  
		 response.setHeader("Content-Disposition", "attachment;filename=" + filename);  
			List<Center>  result = new ArrayList<Center> ();
			try {
				String state = StringUtil.notNull(request.getParameter("state"));
				String userId = StringUtil.notNull(request.getParameter("userId")).toUpperCase();
				Center center = new Center();
				center.setUserId(userId);
				if(!state.equals(""))
				center.setState(Integer.valueOf(state));
					CenterDao centerDao = new CenterDao();
					
				result = centerDao.findByList(center);
			
				String[][] content = new String[result.size()+1][16];
				content[0][0]="序号";
				content[0][1]="中心编号";
				content[0][2]="中心类型";
				content[0][3]="中心名称";
				content[0][4]="会员编号";
				content[0][5]="会员名称";
				content[0][6]="联系电话";
				content[0][7]="所在省";
				content[0][8]="所在市";
				content[0][9]="所在县";
				content[0][10]="经营地址";
				content[0][11]="加盟时间";
				content[0][12]="当前状态";
				content[0][13]="工作室形式";
				content[0][14]="面积";
				content[0][15]="营业执照";
				
				for(int i=0;i<result.size();i++){
					content[i+1][0]  = String.valueOf(i+1);
					content[i+1][1]  = StringUtil.notNull(result.get(i).getCenterId());
					
				
					content[i+1][2]  = String.valueOf(result.get(i).getType());
					
					content[i+1][3]  =  StringUtil.notNull(result.get(i).getCenterName());
				
					content[i+1][4]  = StringUtil.notNull(result.get(i).getUserId());
					content[i+1][5]  = StringUtil.notNull(result.get(i).getUserName());
					content[i+1][6]  = StringUtil.notNull(result.get(i).getPhone());
					content[i+1][7]  = StringUtil.notNull(result.get(i).getProvince());
					content[i+1][8]  = StringUtil.notNull(result.get(i).getArea());
					content[i+1][9]  = StringUtil.notNull(result.get(i).getArea());
					
					content[i+1][10]  = StringUtil.notNull(result.get(i).getAddress());
					
					content[i+1][11]  = StringUtil.parseToString(result.get(i).getEntryTime(),DateUtil.yyyyMMddHHmmss);
					
					content[i+1][12] = String.valueOf(result.get(i).getState());
					
					content[i+1][13]  = String.valueOf(result.get(i).getTypeForm());
					content[i+1][14]  = StringUtil.notNull(result.get(i).getMeasure());
					content[i+1][15]  = StringUtil.notNull(result.get(i).getLicense());
				}
				 HSSFWorkbook wb = new HSSFWorkbook();  
				 HSSFSheet sheet = wb.createSheet("服务中心列表");  
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
	
	public void centerIdAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("sys_user");
		String centerId = StringUtil.notNull(request.getParameter("centerId")).toUpperCase();
		String userName = "";
		try {
				if (StringUtil.notNull(centerId).equals("")) {
					userName = "";
				} else {
					CenterDao centerDao = new CenterDao();
					Center Center1 = centerDao.findByCenterId(centerId);
					if(Center1!=null){
						int t=0;
						if(Center1.getUserId().equals(user.getUserId())){
							t++;
						}else{
							String[] str  =user.getNode().split(",");
							for(int i=0;i<str.length;i++){
								if(!str[i].equals("")){
									if(str[i].equals(String.valueOf(Center1.getuId())))
										t++;
								}
							}
						}
						if(t>0)
						userName=Center1.getCenterName();
						else userName = "";
					}else{
						userName = "";
					}
			} 
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("userName", userName);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}