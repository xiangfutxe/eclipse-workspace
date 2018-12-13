package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.db.DBConn;
import com.pojo.Admin;
import com.pojo.User;
import com.pojo.VideoInfo;
import com.utils.Pager;
import com.utils.StringUtil;

public class ContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private Statement stmt = null;
	
	private Connection conn = null;
	
	private ResultSet rs = null;
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doGet(request,response);
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String method = (String) request.getParameter("method");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if(method==null){
			PrintWriter out = response.getWriter();
			out.println("invalid request");
		}else if(method.equals("C_801")){
			C_801(request,response);
	}else if(method.equals("C_802")){
		C_802(request,response);
}else if(method.equals("C_803")){
	C_803(request,response);
}else if(method.equals("C_804")){
	C_804(request,response);
}else if(method.equals("C_805")){
	C_805(request,response);
}
}
	
	
	
	public void C_801(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		DBConn db = new DBConn();
		
		String message ="";
	
		try {
			if(db.createConn()){
				
			}else {
				message= "数据库连接已断开！";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message=  "数据操作异常，请重试！";
		}finally{
			db.close();
				request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("801.jsp");
			dispatcher.forward(request, response);
		}
	}

public void C_802(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	DBConn db = new DBConn();
	
	String message ="";

	try {
		if(db.createConn()){
			
		}else {
			message= "数据库连接已断开！";
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message=  "数据操作异常，请重试！";
	}finally{
		db.close();
			request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("802.jsp");
		dispatcher.forward(request, response);
	}
}
public void C_803(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	DBConn db = new DBConn();
	
	String message ="";

	try {
		if(db.createConn()){
			
		}else {
			message= "数据库连接已断开！";
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message=  "数据操作异常，请重试！";
	}finally{
		db.close();
			request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("803.jsp");
		dispatcher.forward(request, response);
	}
}
public void C_804(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	DBConn db = new DBConn();
	
	String message ="";

	try {
		if(db.createConn()){
			
		}else {
			message= "数据库连接已断开！";
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message=  "数据操作异常，请重试！";
	}finally{
		db.close();
			request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("804.jsp");
		dispatcher.forward(request, response);
	}
}
public void C_805(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
	DBConn db = new DBConn();
	
	String message ="";

	try {
		if(db.createConn()){
			
		}else {
			message= "数据库连接已断开！";
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		message=  "数据操作异常，请重试！";
	}finally{
		db.close();
			request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("805.jsp");
		dispatcher.forward(request, response);
	}
}
}
