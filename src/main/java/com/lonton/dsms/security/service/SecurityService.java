package com.lonton.dsms.security.service;

import java.sql.SQLException;

public interface SecurityService {

	public boolean checkUser(String userCode, String password) throws SQLException;
}
