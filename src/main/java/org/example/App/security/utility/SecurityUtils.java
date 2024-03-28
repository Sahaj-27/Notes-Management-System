package org.example.App.security.utility;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityUtils {

	public User convert(org.example.App.entity.User user) {
		return new User(user.getEmailId(), user.getPassword(), List.of());
	}

}