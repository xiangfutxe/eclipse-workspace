package com.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pojo.BankAccount;


public class BankAccountService implements IBankAccountService {
	
	private Statement stmt = null;
	
	private ResultSet rs = null;
	
	public BankAccount findById(Connection conn,String id){
		BankAccount BankAccount = new BankAccount();
		try {
		String sql = "select * from bankAccount  where id ='"+id+"'";
			stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			BankAccount.setId(rs.getInt("id"));
			BankAccount.setAccountId(rs.getString("accountId"));
			BankAccount.setAccountName(rs.getString("accountName"));
			BankAccount.setBankName(rs.getString("bankName"));
			BankAccount.setState(rs.getInt("state"));
			BankAccount.setEndTime(rs.getTimestamp("endTime"));
			BankAccount.setEntryTime(rs.getTimestamp("entryTime"));
		}else BankAccount= null;
		stmt.close();
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return BankAccount;
	}

}
