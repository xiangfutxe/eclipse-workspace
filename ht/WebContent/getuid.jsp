<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.service.IWeixinService"%>
<%@ page import="com.service.WeixinService"%>
<%@ page import="com.db.DBConn"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.SQLException"%>
<%
IWeixinService ws = new WeixinService();
		DBConn db = new DBConn();
		Timestamp date = new Timestamp(new Date().getTime());
		Connection conn= db.getConnection();
		try {
			out.println(ws.getUId(conn, date));
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			out.println(e);
			e.printStackTrace();
		}
%>