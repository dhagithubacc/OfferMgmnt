package com.caveofprogramming.spring.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.caveofprogramming.spring.web.model.User;

/**
 * @author 227761
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findById(@Param("id") Integer id);
}
