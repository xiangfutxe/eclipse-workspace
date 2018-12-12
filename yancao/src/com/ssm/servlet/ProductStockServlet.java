package com.ssm.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import net.sf.json.JSONObject;

import com.ssm.dao.OrderDao;
import com.ssm.dao.ProductDao;
import com.ssm.dao.ProductDetailDao;
import com.ssm.dao.ProductPriceDao;
import com.ssm.dao.ProductSortDao;
import com.ssm.dao.ProductStockDao;
import com.ssm.dao.ProductStockTransfersDao;
import com.ssm.dao.ProductTypeDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductDetail;
import com.ssm.pojo.ProductPrice;
import com.ssm.pojo.ProductSort;
import com.ssm.pojo.ProductStock;
import com.ssm.pojo.ProductStockTransfers;
import com.ssm.pojo.ProductType;
import com.ssm.pojo.User;
import com.ssm.pojo.WeixinUserInfo;
import com.ssm.service.CustomService;
import com.ssm.service.ICustomService;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;
import com.ssm.utils.ArithUtil;
import com.ssm.utils.Constants;
import com.ssm.utils.ConstantsLog;

public class ProductStockServlet extends HttpServlet {

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

		}else if (method.equals("admin_transfers_list")) {
			admin_transfers_list(request, response);

		}else if (method.equals("transfers_add")) {
			transfers_add(request, response);

		}else if (method.equals("transfers_save")) {
			transfers_save(request, response);

		}else if (method.equals("remove")) {
			remove(request, response);

		}else if (method.equals("edit")) {
			edit(request, response);

		}else if (method.equals("update")) {
			update(request, response);

		}else if (method.equals("editPrice")) {
			editPrice(request, response);

		}else if (method.equals("updatePrice")) {
			updatePrice(request, response);

		}else if (method.equals("isExit")) {
			isExit(request, response);

		}else if (method.equals("isExitUpdate")) {
			isExitUpdate(request, response);

		}else if (method.equals("productAjax")) {
			productAjax(request, response);

		}else if (method.equals("save_select")) {
			save_select(request, response);

		}else if (method.equals("admin_detail")) {
			admin_detail(request, response);

		}else if (method.equals("product_index")) {
			product_index(request, response);

		}else if (method.equals("product_detail")) {
			product_detail(request, response);

		}else if (method.equals("product_list")) {
			product_list(request, response);

		}else if (method.equals("detail")) {
			detail(request, response);

		}else if (method.equals("product_cash_detail")) {
			product_cash_detail(request, response);

		}else if (method.equals("product_integral_detail")) {
			product_integral_detail(request, response);

		}else if (method.equals("is_hide")) {
			is_hide(request, response);

		}else if (method.equals("is_hide_no")) {
			is_hide_no(request, response);

		}else if (method.equals("img_edit")) {
			img_edit(request, response);

		}else if (method.equals("img_save")) {
			img_save(request, response);

		}else if (method.equals("del")) {
			del(request, response);

		}else if (method.equals("give_edit")) {
			give_edit(request, response);

		}else if (method.equals("give_save")) {
			give_save(request, response);

		}else if (method.equals("product_stock")) {
			product_stock(request, response);

		}
	}

	public void admin_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String productId = StringUtil.notNull(request.getParameter("productId"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		String error ="";
		int lt=0;
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			ProductStock product = new ProductStock();
			product.setUserId(userId);
			product.setProductId(productId);
			ProductStockDao stockDao = new ProductStockDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = stockDao.findByPage(product, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("productId", productId);
				request.setAttribute("userId", userId);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="product_stock_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_stock_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_stock_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="product_stock.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_transfers_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String userId = StringUtil.notNull(request.getParameter("userId"));
		String productId = StringUtil.notNull(request.getParameter("productId"));
		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		String error ="";
		int lt=0;
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			ProductStockTransfers product = new ProductStockTransfers();
			product.setUserId(userId);
			product.setProductId(productId);
			ProductStockTransfersDao stockDao = new ProductStockTransfersDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = stockDao.findByPage(product, pager);
				request.setAttribute("pager", pager);
				request.setAttribute("productId", productId);
				request.setAttribute("userId", userId);
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error="数据操作异常。";
		} finally {
			String request_dispatcher ="product_stock_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_stock_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_stock_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="product_stock_transfers.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		String error ="";
		int lt=0;
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
				ProductDao productDao = new ProductDao();
				Product product = productDao.findProductById(Integer.valueOf(id));
				request.setAttribute("product", product);
				ProductTypeDao productTypeDao = new ProductTypeDao();
				List<ProductType>  coll_pt = productTypeDao.findAllProductType();
				request.setAttribute("coll_pt", coll_pt);
				ProductSortDao productSortDao = new ProductSortDao();
				List<ProductSort>  coll_ps = productSortDao.findAllProductSort();
				request.setAttribute("coll_ps", coll_ps);
				}else{
					error = "产品信息获取失败。";
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
			String request_dispatcher ="product_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="product_edit.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void editPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String id = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		String error ="";
		int lt=0;
		try {
			if(admin!=null){
				if(!id.equals("")){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
				ProductPriceDao priceDao = new ProductPriceDao();
				ProductPrice product = priceDao.findByPid(Integer.valueOf(id));
				if(product!=null) {
					request.setAttribute("product", product);
				}else {
					priceDao = new ProductPriceDao();
					priceDao.save(Integer.valueOf(id));
					priceDao = new ProductPriceDao();
					product = priceDao.findByPid(Integer.valueOf(id));
					if(product!=null) {
						request.setAttribute("product", product);
					}else{
						error="产品价格信息获取失败。";
					}
					
				}
				}else{
					error = "产品信息获取失败。";
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
			String request_dispatcher ="product_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="product_price_edit.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String[] ids = request.getParameterValues("ids");
		String message = "";
		String error ="";
		int lt=0;
		try {
			if(admin!=null){
				if(ids!=null&ids.length>0) {
				ProductDao ProductDao = new ProductDao();
				ProductDao.deleteAll(ids);
				message = "批量删除产品成功。";
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
			String request_dispatcher ="product_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_error.jsp";
				request.setAttribute("error", error);
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
	
	
	public void isExit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");
		int tag = 0;
		try {
				if (StringUtil.notNull(productId).equals("")) {
					tag = 0;
				} else {
					ProductDao ProductDao = new ProductDao();
					Product Product1 = ProductDao.findProductByName(productId);
					if(Product1!=null){
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
	
	public void productAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String name = "";
		try {
				if (!StringUtil.notNull(productId).equals("")) {
					
					ProductDao ProductDao = new ProductDao();
					Product product1 = ProductDao.findProductByName(productId);
					if(product1!=null){
						name = product1.getProductName();
					}
				}
			Map<String, Object> object = new HashMap<String, Object>();
			object.put("name", name);
			JSONObject jsonObject = JSONObject.fromObject(object);
			PrintWriter pw = response.getWriter();
			pw.print(jsonObject.toString());
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void isExitUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String id = request.getParameter("id");
		int tag = 0;
		try {
				if (StringUtil.notNull(productId).equals("")) {
					tag = 0;
				} else {
					ProductDao ProductDao = new ProductDao();
					Product Product1 = ProductDao.findProductByName(productId);
					if(Product1!=null){
						if(String.valueOf(Product1.getId()).equals(id)){
							
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
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String id = StringUtil.notNull(request.getParameter("id"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	String message = "";
	String error ="";
	int lt=0;
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					Timestamp date = new Timestamp(new Date().getTime());

					String isHide = request.getParameter("isHide");
					String productName = StringUtil.notNull(request.getParameter("productName"));
					String[] productTypeStr = request.getParameterValues("productType");
					String specification = StringUtil.notNull(request.getParameter("specification"));
					String features = StringUtil.notNull(request.getParameter("features"));
					String productSort = StringUtil.notNull(request.getParameter("productSort"));
					String price = StringUtil.notNull(request.getParameter("price"));
					String price1 = StringUtil.notNull(request.getParameter("price1"));
					String cash = StringUtil.notNull(request.getParameter("cash"));
					String cashNum = StringUtil.notNull(request.getParameter("cashNum"));
					String integral = StringUtil.notNull(request.getParameter("integral"));
					 String productType ="";
					 if(productTypeStr!=null) {
					 for(int i=0;i<productTypeStr.length;i++){
							if(!StringUtil.notNull(productTypeStr[i]).equals("")){
								if(productType.equals("")) productType = productTypeStr[i];
								else productType = productType+","+productTypeStr[i];
							}
						}
					 }
					ProductDao ProductDao = new ProductDao();
				Product Product = new Product();
				Product.setId(Integer.valueOf(id));
				Product.setProductName(productName);
				Product.setProductType(productType);
				Product.setSpecification(specification);
				Product.setFeatures(features);
				Product.setIsHide(Integer.valueOf(isHide));
				Product.setPrice(Double.valueOf(price));
				Product.setPrice1(Double.valueOf(price1));
				Product.setCash(Double.valueOf(cash));
				Product.setIntegral(Double.valueOf(integral));
				Product.setCashNum(Integer.valueOf(cashNum));
				Product.setProductSort(productSort);
				Product.setEndTime(date);
				ProductDao.updateProduct(Product);
				message = "产品修改成功。";
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
			String request_dispatcher ="product_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_error.jsp";
				request.setAttribute("error", error);
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
	
	public void updatePrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String id = StringUtil.notNull(request.getParameter("id"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	String message = "";
	String error ="";
	int lt=0;
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					Timestamp date = new Timestamp(new Date().getTime());
					String price1 =  StringUtil.notNull(request.getParameter("price1"));
					String price2 =  StringUtil.notNull(request.getParameter("price2"));
					String cash1 =  StringUtil.notNull(request.getParameter("cash1"));
					String cashNum1 =  StringUtil.notNull(request.getParameter("cashNum1"));
					String integral1 =  StringUtil.notNull(request.getParameter("integral1"));
					String integral2 =  StringUtil.notNull(request.getParameter("integral2"));
				
				
					ProductPriceDao priceDao = new ProductPriceDao();
				ProductPrice product = new ProductPrice();
				product.setId(Integer.valueOf(id));
				product.setPrice1(Double.valueOf(price1));
				product.setPrice2(Double.valueOf(price2));
				product.setCash1(Double.valueOf(cash1));
				product.setCashNum1(Integer.valueOf(cashNum1));
				product.setIntegral1(Double.valueOf(integral1));
				product.setIntegral2(Double.valueOf(integral2));
				product.setEndTime(date);
				int result = priceDao.update(product);
				if(result==0)
				message = "产品价格修改成功。";
				else if(result==2) {
					error="数据更新异常。";
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
			String request_dispatcher ="product_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_error.jsp";
				request.setAttribute("error", error);
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
		}

	}
	
	public void transfers_add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String message = "";
	String error ="";
	int lt=0;
		try {
			if(admin!=null){
				String id= request.getParameter("id");
				if(!id.equals("")) {
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					ProductStockDao stockDao = new ProductStockDao();
					ProductStock stock = stockDao.findProductById(Integer.valueOf(id));
					request.setAttribute("stock", stock);
				}else{
					error = "对应的云仓信息获取失败。";
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
			String request_dispatcher ="product_stock_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_stock_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_stock_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="product_stock_transfers_add.jsp";
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}

	}
	
	public void transfers_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	String message = "";
	String error ="";
	int lt=0;
	int type=1;
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
				Timestamp date = new Timestamp(new Date().getTime());
				String id = StringUtil.notNull(request.getParameter("id"));
				String userId = StringUtil.notNull(request.getParameter("userId"));
				String userId1 = StringUtil.notNull(request.getParameter("userId1"));
				String num = StringUtil.notNull(request.getParameter("num"));
				String remark = StringUtil.notNull(request.getParameter("remark"));
				ProductStockTransfersDao transfersDao = new ProductStockTransfersDao();
				ProductStockTransfers transfers = new ProductStockTransfers();
				transfers.setUserId1(userId1);
				transfers.setNum(Integer.valueOf(num));
				transfers.setRemark(remark);
				transfers.setAdmin(admin.getAdminName());
				message=transfersDao.save(Integer.valueOf(id), transfers, date);
				if(message.equals("yes")) {
					message =userId+"云仓调出成功，调出数量为："+num+",云仓调入会员"+userId1+"。";
					cs.insertAdminLog(admin.getAdminName(), message, ConstantsLog.LOGTYPE_5, date);
				}else error= message;
				}else{
					error = "请勿重复提交数据。";
					lt++;
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
			String request_dispatcher ="product_stock_transfers_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_stock_transfers_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_stock_transfers_error.jsp";
				request.setAttribute("error", error);
			}
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void save_select(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	String message = "";
	String error ="";
	int lt=0;
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {

					Timestamp date = new Timestamp(new Date().getTime());
					String[] numstr = request.getParameterValues("numstr");
					String[] pid = request.getParameterValues("pid");
				Product product = (Product)request.getSession().getAttribute("session_product");
					double totalprice = 0;
					List<ProductDetail> plist = new ArrayList<ProductDetail>();
					
					if (numstr.length>0) {
						for (int i = 0; i < pid.length; i++) {
							if(!numstr[i].equals("")){
									int pnum = Integer.valueOf(numstr[i]);
									if(pnum>0){
										ProductDao ProductDao = new ProductDao();
										Product pt = ProductDao.findProductById(Integer.valueOf(pid[i]));
										if(pt!=null){
											ProductDetail od = new ProductDetail();
											od.setPid(product.getProductId());
											od.setNum(Integer.valueOf(numstr[i]));
											od.setProductId(pt.getProductId());
											od.setProductName(pt.getProductName());
											od.setSpecification(pt.getSpecification());
											od.setProductPrice(pt.getPrice());
											od.setPrice(ArithUtil.mul(od.getProductPrice() , pnum));
											plist.add(od);
										}
									}
						}	
									}
				
				product.setEntryTime(date);
				product.setEndTime(date);
				ProductDao ProductDao = new ProductDao();
			message = ProductDao.saveCompositeProducts(product, plist);
					}else{
						error = "组合产品未选择相应的单品。";
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
			error = "系统繁忙中，请稍后再试。";
			e.printStackTrace();
		} finally {
			String request_dispatcher ="product_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_error.jsp";
				request.setAttribute("error", error);
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void admin_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	String message = "";
	String error ="";
	int lt=0;
		try {
			if(admin!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				product = productDao.findProductById(Integer.valueOf(id));
				if(product!=null){
					request.setAttribute("product", product);
				}else{
					error = "产品数据获取失败。";
				}
				
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			error = "系统繁忙中，请稍后再试。";
			e.printStackTrace();
		} finally {
			String request_dispatcher ="product_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_error.jsp";
				request.setAttribute("error", error);
			}else {
				request_dispatcher ="product_detail.jsp";
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	@SuppressWarnings({ "rawtypes"})
	public void product_index(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		Collection coll_1 = new ArrayList();
		Collection coll_2 = new ArrayList();
		String message = "";
		String error ="";
		int lt=0;		try {
			if(user!=null){
				String type=StringUtil.notNull(request.getParameter("type"));
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				if(type.equals("2"))
				product.setProductType("报单产品");
				else if(type.equals("3"))
					product.setProductType("复消产品");
				product.setState(1);
				product.setIsHide(1);
				coll_1 = productDao.findByProduct(product);
				request.setAttribute("coll", coll_1);
			
				product = null;
			}else{
					message= "用户未登陆，请重新登陆！";
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message= "系统繁忙中，请稍后再试！";
			e.printStackTrace();
		}finally{
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("product_index.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void product_list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{		Collection coll = new ArrayList();
	Collection coll_pt = new ArrayList();
	String idstr = StringUtil.notNull(request.getParameter("id"));
	String tag = StringUtil.notNull(request.getParameter("tag"));
	try {
		if(StringUtil.notNull(idstr).equals("")) idstr="0";
		
		ProductSortDao productSortDao = new ProductSortDao();
		coll_pt = productSortDao.findAllProductSort();
		request.setAttribute("idstr", idstr);
		request.setAttribute("coll_pt", coll_pt);
		ProductDao productDao = new ProductDao();
		Product product = new Product();
		product.setState(1);
		if(!idstr.equals("")) {
			 Iterator it = coll_pt.iterator();
			 while(it.hasNext()){
				 ProductSort  sort = (ProductSort)it.next();
				 if(String.valueOf(sort.getId()).equals(idstr)) {
					 product.setProductSort(sort.getName());
					 break;
				 }
			 }
		}
		coll = productDao.findByProduct(product);
		request.setAttribute("coll", coll);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		RequestDispatcher dispatcher = request.getRequestDispatcher("product_list.jsp");
		dispatcher.forward(request, response);
	}
}
	
	public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo user = (WeixinUserInfo)request.getSession().getAttribute(Constants.USER_SESSION);
		String message="";
		try {
		if(user!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				product = productDao.findProductByName(id);
			if(product!=null){
				request.setAttribute("product", product);
			}else{
				message="未获取相应的产品信息，请重试！";
			}
			product = null;
		}else{
			message= "用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message= "系统繁忙中，请稍后再试！";
			}finally{
				request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_list_detail.jsp");
					dispatcher.forward(request, response);
			}
		}
	
	public void product_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo user = (WeixinUserInfo)request.getSession().getAttribute(Constants.USER_SESSION);
		String message="";
		try {
		if(user!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				product = productDao.findProductByName(id);
			if(product!=null){
				request.setAttribute("product", product);
			}else{
				message="未获取相应的产品信息，请重试！";
			}
			product = null;
		}else{
			message= "用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message= "系统繁忙中，请稍后再试！";
			}finally{
				request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_detail.jsp");
					dispatcher.forward(request, response);
			}
		}
	
	
	public void product_cash_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo user = (WeixinUserInfo)request.getSession().getAttribute(Constants.USER_SESSION);
		String message="";
		try {
		if(user!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				product = productDao.findProductByName(id);
			if(product!=null){
				request.setAttribute("product", product);
			}else{
				message="未获取相应的产品信息，请重试！";
			}
			product = null;
		}else{
			message= "用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message= "系统繁忙中，请稍后再试！";
			}finally{
				request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_cash_detail.jsp");
					dispatcher.forward(request, response);
			}
		}
	
	public void product_integral_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		WeixinUserInfo user = (WeixinUserInfo)request.getSession().getAttribute(Constants.USER_SESSION);
		String message="";
		try {
		if(user!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				product = productDao.findProductByName(id);
			if(product!=null){
				request.setAttribute("product", product);
			}else{
				message="未获取相应的产品信息，请重试！";
			}
			product = null;
		}else{
			message= "用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message= "系统繁忙中，请稍后再试！";
			}finally{
				request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_integral_detail.jsp");
					dispatcher.forward(request, response);
			}
		}
	
	public void is_hide(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String message = "";
	String error ="";
	int lt=0;
		try {
			if(admin!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				if(!id.equals("")){
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				product.setId(Integer.valueOf(id));
				product.setIsHide(0);
				if(productDao.updateProduct(product)>0){
					 message= "产品下架成功！";
				}else{
					error = "产品下架失败。";
				}
				}
				else{
					error = "产品数据获取失败。";
				}
				
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			error = "系统繁忙中，请稍后再试。";
			e.printStackTrace();
		} finally {
			String request_dispatcher ="product_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_error.jsp";
				request.setAttribute("error", error);
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
			
			
		}
	}
	
	public void is_hide_no(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);
	String message = "";
	String error ="";
	int lt=0;
		try {
			if(admin!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				if(!id.equals("")){
					ProductDao productDao = new ProductDao();
					Product product = new Product();
					product.setId(Integer.valueOf(id));
					product.setIsHide(1);
				if(productDao.updateProduct(product)>0){
					 message= "产品上架成功！";
				}else{
					error = "产品上架失败。";
				}
				}
				else{
					error = "产品数据获取失败。";
				}
				
			}else{
				error = "用户未登陆，请重新登陆。";
				lt++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			error = "系统繁忙中，请稍后再试。";
			e.printStackTrace();
		} finally {
			String request_dispatcher ="product_message.jsp";
			if (!message.equals("")) {
				request_dispatcher ="product_message.jsp";
				request.setAttribute("message", message);
			} else if (!error.equals("")) {
				if(lt==1) request_dispatcher ="error_login.jsp";
				else request_dispatcher ="product_error.jsp";
				request.setAttribute("error", error);
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(request_dispatcher);
			dispatcher.forward(request, response);
		}
	}
	
	public void img_edit(HttpServletRequest request,HttpServletResponse response) 
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
			ProductDao productDao = new ProductDao();
			Product pt = productDao.findProductById(Integer.valueOf(id));
			if(pt!=null){
				request.setAttribute("product", pt);
			}else{
				error="未获取相应的产品信息，请重试！";
			}
				}else{
					error="未获取相应的产品ID信息。";
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
					String request_dispatcher ="product_message.jsp";
					if (!message.equals("")) {
						request_dispatcher ="product_message.jsp";
						request.setAttribute("message", message);
					} else if (!error.equals("")) {
						if(lt==1) request_dispatcher ="error_login.jsp";
						else request_dispatcher ="product_error.jsp";
						request.setAttribute("error", error);
					}else {
						request_dispatcher ="product_img_edit.jsp";
					}
					RequestDispatcher dispatcher = request
							.getRequestDispatcher(request_dispatcher);
					dispatcher.forward(request, response);
			}
		}
	
	public void img_save(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String token_old = (String)request.getSession().getAttribute("token");
		 DiskFileItemFactory factory = new DiskFileItemFactory();  
		 String path = request.getSession().getServletContext().getRealPath("/upload/product");  
		 factory.setRepository(new File(path)); 
		 factory.setSizeThreshold(1024*1024) ;  
		 ServletFileUpload upload = new ServletFileUpload(factory);  
		 String message = "";
			String error ="";
			int lt=0;
		try {
		if(admin!=null){
			 List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
			 String id = "";
			 String token = "";
			 String imgurl = "";
			 String imgurl1 = "";
			 String imgurl2 = "";
			 String imgurl3 = "";
			 String imgurl4 = "";
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
	               
	                 String filename = value.substring(start+1);  
	                   
	                 if(name.equals("imageUrl")){
	                	 imgurl = filename;
	                 }else if(name.equals("imageUrl1")){
	                	 imgurl1 = filename;
	                 }else if(name.equals("imageUrl2")){
	                	 imgurl2 = filename;
	                 }else if(name.equals("imageUrl3")){
	                	 imgurl3 = filename;
	                 }else if(name.equals("imageUrl4")){
	                	 imgurl4 = filename;
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
			ProductDao productDao = new ProductDao();

			Product product = new Product();
			product.setId(Integer.valueOf(id));
			if(!imgurl.equals(""))
			product.setImageUrl(imgurl);
			if(!imgurl1.equals(""))
				product.setImageUrl1(imgurl1);
			if(!imgurl2.equals(""))
				product.setImageUrl2(imgurl2);
			if(!imgurl3.equals(""))
				product.setImageUrl3(imgurl3);
			if(!imgurl4.equals(""))
				product.setImageUrl4(imgurl4);
			if(productDao.updateProduct(product)>0){
				message= "图片上传成功！";
			}else{
				error="图片信息保存失败";
			}
				
			 }else{
				 error= "请勿重复提交数据。";
			}
		}else{
			error= "管理员用户未登陆，请重新登陆！";
			lt++;
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message= "数据保存有误，请重新录入！";
			}finally{
				String request_dispatcher ="product_message.jsp";
				if (!message.equals("")) {
					request_dispatcher ="product_message.jsp";
					request.setAttribute("message", message);
				} else if (!error.equals("")) {
					if(lt==1) request_dispatcher ="error_login.jsp";
					else request_dispatcher ="product_error.jsp";
					request.setAttribute("error", error);
				}
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(request_dispatcher);
				dispatcher.forward(request, response);
			}
		}
	
	public void give_edit(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String message="";
		try {
		if(admin!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				if(!id.equals("")){
				int token = (int)(1+Math.random()*(1000000-1+1));
				request.getSession().setAttribute("token", String.valueOf(token));
			ProductDao productDao = new ProductDao();
			Product pt = productDao.findProductById(Integer.valueOf(id));
			if(pt!=null){
				request.setAttribute("product", pt);
			}else{
				message="未获取相应的产品信息，请重试！";
			}
				}else{
					message="未获取相应的产品ID信息。";
				}
		
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message= "数据保存有误，请重新录入！";
			}finally{
				if(message.equals("")){
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_give_edit.jsp");
					dispatcher.forward(request, response);
				}else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_message.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	
	public void give_save(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute(Constants.ADMIN_SESSION);
		String token = StringUtil.notNull(request.getParameter("token"));
		String token_old = (String) request.getSession().getAttribute("token");
		int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
		request.getSession().setAttribute("token", String.valueOf(token_new));
			String message = "";
			try {
				if(admin!=null){
					if (StringUtil.notNull(token_old).equals(token)) {
			
			 String productId = StringUtil.notNull(request.getParameter("productId"));
			 String id = StringUtil.notNull(request.getParameter("id"));
			 
			ProductDao productDao = new ProductDao();
			Product p1 = productDao.findProductByName(productId);
			if(p1!=null){
				Product p2 = new Product();
				p2.setId(Integer.valueOf(id));
				
				productDao = new ProductDao();
			
			if(productDao.updateProduct(p2)>0){
				message= "赠品绑定成功！";
			}else{
				message="赠品信息更新失败";
			}
			}else{
				message="赠品信息获取失败";
			}
				
			 }else{
				 message= "请勿重复提交数据.";
			}
		}else{
			message= "管理员用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message= "数据保存有误，请重新录入！";
			}finally{
				
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_message.jsp");
					dispatcher.forward(request, response);
				
			}
		}
	

	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute(Constants.ADMIN_SESSION);

		String message = "";
		try {
			if(admin!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				if(!id.equals("")){
					ProductDao productDao = new ProductDao();
					Product product = new Product();
					product.setId(Integer.valueOf(id));
					product.setState(0);
				if(productDao.updateProduct(product)>0){
					 message= "产品删除成功！";
				}else{
					message = "产品变更失败。";
				}
				}
				else{
					message = "产品数据获取失败。";
				}
				
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message = "系统繁忙中，请稍后再试。";
			e.printStackTrace();
		} finally {
			
				request.setAttribute("message", message);
		
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("product_message.jsp");
			dispatcher.forward(request, response);
			
			
		}
	}
	
	public void product_stock(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		WeixinUserInfo user = (WeixinUserInfo)request.getSession().getAttribute(Constants.USER_SESSION);
		request.getSession().removeAttribute("stock_cart_list");
		String message = "";
			try {
				if(user!=null){
			ProductStock p2 = new ProductStock();
			p2.setUserId(user.getUserId());
				ProductStockDao stockDao = new ProductStockDao();
				List<ProductStock> product_stock = stockDao.findByProduct(p2);
				request.setAttribute("product_stock", product_stock);
			
		}else{
			message= "用户未登陆，请重新登陆！";
		}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message= "数据保存有误，请重新录入！";
			}finally{
				
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_stock.jsp");
					dispatcher.forward(request, response);
				
			}
		}
	
}