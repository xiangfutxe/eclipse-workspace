<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ssm.pojo.Admin" %>
<%@page import="com.ssm.dao.UserDao" %>
<%@page import="com.ssm.dao.AdminDao" %>
<%@page import="com.ssm.pojo.Address" %>
<%@page import="com.ssm.pojo.User" %>
<%@page import="com.ssm.utils.StringUtil" %>
<%@page import="com.ssm.utils.DateUtil" %>

<%@page import="jxl.Cell" %>
<%@page import="jxl.Sheet" %>
<%@page import="jxl.Workbook" %>
<%@page import="java.io.FileInputStream" %>
<%@page import="java.io.IOException" %>
<%@page import="java.io.InputStream" %>

<%@page import="com.ssm.utils.Pager" %>
<%@page import="java.sql.Timestamp" %>
<%
String message="";
				jxl.Workbook readwb = null;
				List<Address> ulist = new ArrayList<Address>();

				// 构建Workbook对象, 只读Workbook对象

				// 直接从本地文件创建Workbook

				InputStream instream = new FileInputStream(
						"/usr/src/hdj-ad-1.xls");
				readwb = Workbook.getWorkbook(instream);
				// Sheet的下标是从0开始
				// 获取第一张Sheet表
				Sheet readsheet = readwb.getSheet(0);
				// 获取Sheet表中所包含的总列数
				int rsColumns = readsheet.getColumns();
				// 获取Sheet表中所包含的总行数
				int rsRows = readsheet.getRows();
				// 获取指定单元格的对象引用
				for (int i = 1; i < 1472; i++)

				{
					Address adr = new Address();
					
					for (int j = 0; j < rsColumns; j++)

					{
						Cell cell = readsheet.getCell(j, i);

						if (j == 1) {
							adr.setUserId(cell.getContents());
						}else if (j == 2) {
							adr.setReceiver(cell.getContents());
						} else if (j == 3) {
							adr.setPhone(cell.getContents());
						}else if (j == 4) {
							adr.setProvince(cell.getContents());
						}else if (j == 5) {
							adr.setCity(cell.getContents());
						}else if (j == 6) {
							adr.setArea(cell.getContents());
						}else if (j == 7) {
							adr.setAddress(cell.getContents());
						}else if (j == 8) {
							adr.setState(1);
						}else if (j == 9) {
							adr.setTag(Integer.valueOf(cell.getContents()));
						}
					}
					ulist.add(adr);
				}
				AdminDao adminDao = new AdminDao();
				Timestamp date = new Timestamp(StringUtil.parseToDate("2017-11-12 05:00:00", DateUtil.yyyyMMddHHmmss).getTime());
				message=adminDao.saveAdr(ulist, date);
				if(message.equals("")) message="yes";
				out.println(message);

%>
