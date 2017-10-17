package com.caveofprogramming.spring.web.service;

/**
 * @author 227761
 *
 */
public interface SecurityService {
	String findLoggedInUsername();

	void autologin(String username, String password);
}
