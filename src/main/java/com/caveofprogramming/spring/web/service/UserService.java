package com.caveofprogramming.spring.web.service;

import java.util.List;

import com.caveofprogramming.spring.web.model.User;

/**
 * @author 227761
 *
 */
public interface UserService {
	void save(User user);

	User findByUsername(String username);

	List<User> getAllUsers();

}
