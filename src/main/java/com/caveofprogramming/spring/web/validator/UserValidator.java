package com.caveofprogramming.spring.web.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.caveofprogramming.spring.web.model.User;
import com.caveofprogramming.spring.web.service.UserService;

/**
 * @author 227761
 *
 */
@Component
public class UserValidator implements Validator {
	Logger logger = LoggerFactory.getLogger(UserValidator.class);
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {

		logger.debug("inside validate>>>>");

		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");

		if (user.getUsername().length() < 8 || user.getUsername().length() > 15) {

			errors.rejectValue("username", "Size.userForm.username");
		}

		if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
		// "NotEmpty");
		// errors.rejectValue("email", "ValidEmail.user.email");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 15) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
		logger.debug("outside validate>>>>");
	}
}
