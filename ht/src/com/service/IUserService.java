package com.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public interface IUserService {
	
	public int getUId(Connection conn,Timestamp date) throws SQLException;
}
