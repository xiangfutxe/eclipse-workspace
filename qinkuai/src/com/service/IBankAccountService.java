package com.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.pojo.BankAccount;
import com.pojo.Settle;
import com.pojo.TimeParam;
import com.pojo.User;
import com.pojo.WReward;
import com.pojo.WSettle;

public interface IBankAccountService {
	
	public BankAccount findById(Connection conn,String id);

}
