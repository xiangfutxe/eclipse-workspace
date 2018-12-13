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

import com.ssm.dao.ProductDao;
import com.ssm.dao.ProductDetailDao;
import com.ssm.dao.ProductTypeDao;
import com.ssm.pojo.Admin;
import com.ssm.pojo.Product;
import com.ssm.pojo.ProductDetail;
import com.ssm.pojo.ProductType;
import com.ssm.pojo.User;
import com.ssm.utils.Pager;
import com.ssm.utils.StringUtil;
import com.ssm.utils.ArithUtil;

public class ProductServlet extends HttpServlet {

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

		}else if (method.equals("remove")) {
			remove(request, response);

		}else if (method.equals("removeAll")) {
			removeAll(request, response);

		}else if (method.equals("editProduct")) {
			editProduct(request, response);

		}else if (method.equals("isExit")) {
			isProductExit(request, response);

		}else if (method.equals("isProductExitUpdate")) {
			isProductExitUpdate(request, response);

		}else if (method.equals("updateProduct")) {
			updateProduct(request, response);

		}else if (method.equals("productAjax")) {
			productAjax(request, response);

		}else if (method.equals("addProduct")) {
			addProduct(request, response);

		}else if (method.equals("saveProduct")) {
			saveProduct(request, response);

		}else if (method.equals("save_select")) {
			save_select(request, response);

		}else if (method.equals("admin_detail")) {
			admin_detail(request, response);

		}else if (method.equals("product_index")) {
			product_index(request, response);

		}else if (method.equals("product_detail")) {
			product_detail(request, response);

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

		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String productName = StringUtil.notNull(request.getParameter("productName"));
		String typeName = StringUtil.notNull(request.getParameter("typeName"));
		String typeStr = StringUtil.notNull(request.getParameter("type"));
		String isHide = StringUtil.notNull(request.getParameter("isHide"));

		String pageNoStr = StringUtil.notNull(request.getParameter("pageNoStr"));
		String pageSizeStr = StringUtil.notNull(request.getParameter("pageSizeStr"));
		int pageNo = 1;
		int pageSize = 20;
		String message = "";
		try {
			if(admin!=null){
			if(!pageNoStr.equals(""))pageNo = Integer.valueOf(pageNoStr);
			if(!pageSizeStr.equals(""))pageSize = Integer.valueOf(pageSizeStr);
			int type=0;
			if(typeStr.equals("")) type=0;
			else type = Integer.valueOf(typeStr);
			Product product = new Product();
			product.setProductName(productName);
			product.setProductType(typeName);
			product.setType(type);
			if(!isHide.equals("")) product.setIsHide(Integer.valueOf(isHide));
				ProductDao ProductDao = new ProductDao();
				Pager pager= new Pager();
				pager.setPageNo(pageNo);
				pager.setPageSize(pageSize);
				pager = ProductDao.findProduct(product,pager);
				request.setAttribute("pager", pager);
				request.setAttribute("productName", productName);
				request.setAttribute("typeName", typeName);
				request.setAttribute("type", typeStr);
				request.setAttribute("isHide", isHide);
				ProductTypeDao productTypeDao = new ProductTypeDao();
				List<ProductType>  coll_pt = productTypeDao.findAllProductType();
				request.setAttribute("coll_pt", coll_pt);
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
						.getRequestDispatcher("product_message.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("product.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	
	public void editProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String id = StringUtil.notNull(request.getParameter("id"));
		String  message= "";
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
				}else{
					message = "产品信息获取失败。";
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
							.getRequestDispatcher("product_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("product_edit.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String ids = StringUtil.notNull(request.getParameter("id"));
		String message = "";
		try {
			if(admin!=null){
				ProductDao ProductDao = new ProductDao();
				ProductDao.deleteAll(ids);
				message = "批量删除产品成功。";
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("product_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void removeAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String ids = StringUtil.notNull(request.getParameter("ids"));
		String message = "";
		try {
			if(admin!=null){
				ProductDao ProductDao = new ProductDao();
				ProductDao.deleteAll(ids);
				message = "批量删除产品成功。";
			}else{
				message = "用户未登陆，请重新登陆。";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("product_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void isProductExit(HttpServletRequest request, HttpServletResponse response)
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
	
	public void isProductExitUpdate(HttpServletRequest request, HttpServletResponse response)
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
	
	public void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
	String id = StringUtil.notNull(request.getParameter("id"));
	String token = StringUtil.notNull(request.getParameter("token"));
	String token_old = (String) request.getSession().getAttribute("token");
	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
	
		String message = "";
		try {
			if(admin!=null){
				if (StringUtil.notNull(token_old).equals(token)) {
					Timestamp date = new Timestamp(new Date().getTime());

					String productId = request.getParameter("productId");
					String productName = StringUtil.notNull(request.getParameter("productName"));
					String[] productTypeStr = request.getParameterValues("productType");
					String specification = StringUtil.notNull(request.getParameter("specification"));
					String features = StringUtil.notNull(request.getParameter("features"));
					String price = StringUtil.notNull(request.getParameter("price"));
					String pv  = StringUtil.notNull(request.getParameter("pv"));
					String giveType  = StringUtil.notNull(request.getParameter("giveType"));
					//String giveNum  = StringUtil.notNull(request.getParameter("give_num"));
					String maxNum  = StringUtil.notNull(request.getParameter("maxNum"));
					String price_cy = StringUtil.notNull(request.getParameter("price_cy"));
					String pv_cy  = StringUtil.notNull(request.getParameter("pv_cy"));
					 String productType ="";
					 for(int i=0;i<productTypeStr.length;i++){
							if(!StringUtil.notNull(productTypeStr[i]).equals("")){
								if(productType.equals("")) productType = productTypeStr[i];
								else productType = productType+","+productTypeStr[i];
							}
						}
					ProductDao ProductDao = new ProductDao();
				Product Product = new Product();
				Product.setId(Integer.valueOf(id));
				Product.setProductId(productId);
				Product.setProductName(productName);
				Product.setProductType(productType);
				Product.setSpecification(specification);
				Product.setFeatures(features);
				Product.setPrice(Double.valueOf(price));
				Product.setPv(Double.valueOf(pv));
				Product.setPriceCy(Double.valueOf(price_cy));
				Product.setPvCy(Double.valueOf(pv_cy));
				Product.setMaxNum(Integer.valueOf(maxNum));
				//Product.setGiveNum(Integer.valueOf(giveNum));
				Product.setGiveType(Integer.valueOf(giveType));
				Product.setEndTime(date);
				ProductDao.updateProduct(Product);
				message = "产品修改成功。";
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
					.getRequestDispatcher("product_message.jsp");
			dispatcher.forward(request, response);
			
		}

	}
	
	public void addProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");
		String  message= "";
		try {
			if(admin!=null){
					int token = (int) (1 + Math.random() * (1000000 - 1 + 1));
					request.getSession().setAttribute("token", String.valueOf(token));
					ProductTypeDao productTypeDao = new ProductTypeDao();
					List<ProductType>  coll_pt = productTypeDao.findAllProductType();
					request.setAttribute("coll_pt", coll_pt);
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
							.getRequestDispatcher("product_add_message.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("product_add.jsp");
					dispatcher.forward(request, response);
				}
		}

	}
	
	public void saveProduct(HttpServletRequest request, HttpServletResponse response)
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
					String productId = request.getParameter("productId");
					String productName = StringUtil.notNull(request.getParameter("productName"));
					String[] productTypeStr = request.getParameterValues("productType");
					String specification = StringUtil.notNull(request.getParameter("specification"));
					String features = StringUtil.notNull(request.getParameter("features"));
					String price = StringUtil.notNull(request.getParameter("price"));
					String type = StringUtil.notNull(request.getParameter("type"));
					String pv  = StringUtil.notNull(request.getParameter("pv"));
					String giveType  = StringUtil.notNull(request.getParameter("giveType"));
					//String giveNum  = StringUtil.notNull(request.getParameter("give_num"));
					String maxNum  = StringUtil.notNull(request.getParameter("maxNum"));
					String price_cy = StringUtil.notNull(request.getParameter("price_cy"));
					String pv_cy  = StringUtil.notNull(request.getParameter("pv_cy"));
					 String productType ="";
					 for(int i=0;i<productTypeStr.length;i++){
							if(!StringUtil.notNull(productTypeStr[i]).equals("")){
								if(productType.equals("")) productType = productTypeStr[i];
								else productType = productType+","+productTypeStr[i];
							}
						}
				ProductDao ProductDao = new ProductDao();
				Product Product = new Product();
				Product.setProductId(productId);
				Product.setProductName(productName);
				Product.setProductType(productType);
				Product.setSpecification(specification);
				Product.setFeatures(features);
				Product.setPrice(Double.valueOf(price));
				Product.setPv(Double.valueOf(pv));
				Product.setPriceCy(Double.valueOf(price_cy));
				Product.setPvCy(Double.valueOf(pv_cy));
				Product.setMaxNum(Integer.valueOf(maxNum));
				//Product.setGiveNum(Integer.valueOf(giveNum));
				Product.setGiveType(Integer.valueOf(giveType));
				Product.setType(Integer.valueOf(type));
				Product.setState("1");
				Product.setEntryTime(date);
				Product.setEndTime(date);
				if(type.equals("1")){
					message = ProductDao.saveProduct(Product);
				}else{
					request.getSession().setAttribute("product", Product);
					Product product1 = new Product();
					product1.setType(1);
					product1.setState("1");
					List<Product> coll_pt = ProductDao.findByProduct(product1);
					request.setAttribute("coll_pt", coll_pt);
				}
				Product=null;
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
			if(!message.equals("")){
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("product_add_message.jsp");
				dispatcher.forward(request, response);
			}else{
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("product_add_select.jsp");
				dispatcher.forward(request, response);
			}
			
		}
	}
	
	public void save_select(HttpServletRequest request, HttpServletResponse response)
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
					String[] numstr = request.getParameterValues("numstr");
					String[] pid = request.getParameterValues("pid");
				Product product = (Product)request.getSession().getAttribute("product");
					double totalprice = 0;
					double totalpv = 0;
					double totalpriceCy = 0;
					double totalpvCy = 0;
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
											od.setP_id(pt.getId());
											od.setpId(product.getProductId());
											od.setNum(Integer.valueOf(numstr[i]));
											od.setProductId(pt.getProductId());
											od.setProductName(pt.getProductName());
											od.setSpecification(pt.getSpecification());
											od.setProductType(pt.getProductType());
											od.setProductPrice(pt.getPrice());
											od.setProductPv(pt.getPv());
											od.setProductPriceCy(pt.getPriceCy());
											od.setProductPvCy(pt.getPvCy());
											od.setImageUrl(pt.getImageUrl());
											od.setPrice(ArithUtil.mul(od.getProductPrice() , pnum));
											od.setPv(ArithUtil.mul(od.getProductPv() , pnum));
											od.setPriceCy(ArithUtil.mul(od.getProductPriceCy() , pnum));
											od.setPvCy(ArithUtil.mul(od.getProductPvCy() , pnum));
											totalprice = ArithUtil.add(totalprice ,od.getPrice());
											totalpv = ArithUtil.add(totalpv , od.getPv());
											totalpriceCy = ArithUtil.add(totalpriceCy ,od.getPriceCy());
											totalpvCy = ArithUtil.add(totalpvCy , od.getPvCy());
										plist.add(od);
										}
									}
						}	
									}
				product.setPrice(totalprice);
				product.setPv(totalpv);
				product.setPriceCy(totalpriceCy);
				product.setPvCy(totalpvCy);
				product.setEntryTime(date);
				product.setEndTime(date);
				ProductDao ProductDao = new ProductDao();
			message = ProductDao.saveCompositeProducts(product, plist);
					}else{
						message = "组合产品未选择相应的单品。";
					}
				}else{
					message = "请勿重复提交数据。";
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
						.getRequestDispatcher("product_add_message.jsp");
				dispatcher.forward(request, response);
			
			
		}
	}
	
	public void admin_detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");

	int token_new = (int) (1 + Math.random() * (1000000 - 1 + 1));
	request.getSession().setAttribute("token", String.valueOf(token_new));
		String message = "";
		try {
			if(admin!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				product = productDao.findProductById(Integer.valueOf(id));
				if(product!=null){
					request.setAttribute("product", product);
					if(product.getType()==2){
						ProductDetailDao pdetailDao = new ProductDetailDao();
						ProductDetail pd = new ProductDetail();
						pd.setpId(product.getProductId());
						List<ProductDetail> coll  = pdetailDao.findByProductDetail(pd);
						request.setAttribute("coll", coll);
					}
				}else{
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
			if(message.equals("")){
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("product_detail.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("message", message);
		
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("product_message.jsp");
			dispatcher.forward(request, response);
			}
			
			
		}
	}
	
	@SuppressWarnings({ "rawtypes"})
	public void product_index(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		Collection coll_1 = new ArrayList();
		Collection coll_2 = new ArrayList();
		String message ="";
		try {
			if(user!=null){
				String type=StringUtil.notNull(request.getParameter("type"));
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				if(type.equals("2"))
				product.setProductType("报单产品");
				else if(type.equals("3"))
					product.setProductType("复消产品");
				product.setState("1");
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
	
	public void product_detail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		User user = (User)request.getSession().getAttribute("sys_user");
		String message="";
		try {
		if(user!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				product = productDao.findProductByName(id);
			if(product!=null){
				request.setAttribute("product", product);
					if(product.getType()==2){
						ProductDetailDao pdetailDao = new ProductDetailDao();
						ProductDetail pd = new ProductDetail();
						pd.setpId(product.getProductId());
						List<ProductDetail> coll  = pdetailDao.findByProductDetail(pd);
						request.setAttribute("coll", coll);
					}
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
	
	public void is_hide(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");

		String message = "";
		try {
			if(admin!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				if(!id.equals("")){
				ProductDao productDao = new ProductDao();
				Product product = new Product();
				product.setId(Integer.valueOf(id));
				product.setIsHide(0);
				if(productDao.updateProduct(product)>0){
					 message= "产品隐藏成功！";
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
	
	public void is_hide_no(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");

		String message = "";
		try {
			if(admin!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				if(!id.equals("")){
					ProductDao productDao = new ProductDao();
					Product product = new Product();
					product.setId(Integer.valueOf(id));
					product.setIsHide(1);
				if(productDao.updateProduct(product)>0){
					 message= "产品显示成功！";
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
	
	public void img_edit(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
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
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_img_edit.jsp");
					dispatcher.forward(request, response);
				}else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_message_add.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	
	public void img_save(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
		String token_old = (String)request.getSession().getAttribute("token");
		 DiskFileItemFactory factory = new DiskFileItemFactory();  
		 String path = request.getSession().getServletContext().getRealPath("/upload");  
		 factory.setRepository(new File(path)); 
		 factory.setSizeThreshold(1024*1024) ;  
		 ServletFileUpload upload = new ServletFileUpload(factory);  
		 String message = "";
		try {
		if(admin!=null){
			 List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
			 String id = "";
			 String token = "";
			 String imgurl = "";
			 for(FileItem item : list)  
	         {  
	             String name = item.getFieldName();  
	             if(item.isFormField())  
	             {                     
	                 String value = item.getString() ;  
	                   if(name.equals("token")){
	                	   token = value;
	                   }else if(name.equals("id")) id =value;
	             }  
	             else  
	             {  
	                 String value = item.getName() ;  
	                
	                 int start = value.lastIndexOf("\\");  
	               
	                 String filename = value.substring(start+1);  
	                   
	                 imgurl = filename;
	                
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
			product.setImageUrl(imgurl);
			if(productDao.updateProduct(product)>0){
				message= "图片上传成功！";
			}else{
				message="图片信息保存失败";
			}
				
			 }else{
				 message= "请勿重复提交数据，请在产品列表中查看是否保存成功！";
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
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_img_edit.jsp");
					dispatcher.forward(request, response);
				}else {
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("product_message.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	
	public void give_edit(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
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
		Admin admin = (Admin)request.getSession().getAttribute("sys_admin");
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
				p2.setGiveId(p1.getId());
				p2.setGiveProductId(p1.getProductId());
				p2.setGiveProductName(p1.getProductName());
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
	Admin admin = (Admin) request.getSession().getAttribute("sys_admin");

		String message = "";
		try {
			if(admin!=null){
				String id = StringUtil.notNull(request.getParameter("id"));
				if(!id.equals("")){
					ProductDao productDao = new ProductDao();
					Product product = new Product();
					product.setId(Integer.valueOf(id));
					product.setState("0");
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
	
}