package org.example.App.security;

import org.example.App.repository.UserRepository;
import org.example.App.security.utility.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*", value = "*")
public class CustomUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		return SecurityUtils.convert(userRepository.findByEmailId(emailId)
				.orElseThrow(() -> new UsernameNotFoundException("Bad Credentials")));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}