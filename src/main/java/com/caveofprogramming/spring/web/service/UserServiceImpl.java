package com.caveofprogramming.spring.web.service;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.caveofprogramming.spring.web.model.User;
import com.caveofprogramming.spring.web.repository.RoleRepository;
import com.caveofprogramming.spring.web.repository.UserRepository;

/**
 * @author 227761
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public void save(User user) {
		logger.debug("Inside UserServiceImpl Save>>>>");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		// logger.debug("roleRepository.findAll>>>>"+roleRepository.findAll().iterator().next().getRolename());
		userRepository.saveAndFlush(user);
		userRepository.save(user);
		userRepository.flush();
		logger.debug("outside save in UserServiceImpl>>>>>");
		// userRepository.saveAndFlush(user);

	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
