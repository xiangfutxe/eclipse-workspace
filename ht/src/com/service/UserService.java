package com.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserService implements IUserService {
	
	private Statement stmt = null;
	
	private ResultSet rs = null;
	
	 public int getUId(Connection conn,Timestamp date) throws SQLException {
			int uid = 0;
			if (check_card_pool(conn)) {
				uid = get_card_id(conn,date);
			} else {
				add_card_pool(conn);
				uid = get_card_id(conn,date);
			}
			return uid;
		}
	 
	 public boolean check_card_pool(Connection conn) throws SQLException {
			String sql = "select count(*) from uid_pool";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int num = 0;
			if (rs.next()) {
				num = rs.getInt(1);
			}
			if (num < 1)
				return false;
			else
				return true;
		}
	 
	 public int get_card_id(Connection conn,Timestamp date) throws SQLException {
			String sql = "select * from uid_pool where tag='0' order by id asc for update";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int num = 0;
			int id = 0;
			if (rs.next()) {
				num = rs.getInt("uid");
				id = rs.getInt("id");
				String sqld = "update uid_pool set tag ='1',end_time='"+date+"' where id='" + id + "'";
				stmt = conn.createStatement();
				stmt.executeUpdate(sqld);
			} else {
				add_card_pool(conn);
				num = get_card_id(conn,date);
			}
			return num;
		}
	 
	 public synchronized void add_card_pool(Connection conn) throws SQLException {
			int maxId = 0;
			String sqld1 = "select max(uid) from uid_pool";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqld1);
			if (rs.next()) {
				maxId = rs.getInt(1);
			} else {
				maxId = 11111111;
			}
			if (maxId == 0)
				maxId = 111111111;
			stmt = conn.createStatement();
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < 3000; i++) {
				list.add(maxId + i);
			}
			int out = 0;
			int outIndex = 0;
			for (int i = 0; i < 3000; i++) {
				Random ran = new Random();
				outIndex = ran.nextInt(list.size());
				out = list.get(outIndex);
				list.remove(outIndex);
				String sqli = "insert into uid_pool(uid) values('" + (out + 1)
						+ "')";
				stmt.addBatch(sqli);
			}
			String sqld = "delete from uid_pool where tag='1'";
			stmt.addBatch(sqld);
			stmt.executeBatch();
		}
	
}
