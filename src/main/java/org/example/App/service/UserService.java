package org.example.App.service;

import org.example.App.dto.request.UserCreationRequestDto;
import org.example.App.dto.request.UserDetailUpdationRequestDto;
import org.example.App.dto.request.UserLoginRequestDto;
import org.example.App.dto.request.UserPasswordUpdationRequestDto;
import org.example.App.dto.response.UserDetailsDto;
import org.example.App.entity.User;
import org.example.App.repository.UserRepository;
import org.example.App.security.utility.JwtUtils;
import org.example.App.utils.ResponseUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;


	private final PasswordEncoder passwordEncoder;

	private final ResponseUtils responseUtils;

	private final JwtUtils jwtUtils;


	public boolean userExists(final String emailId) {
		return userRepository.findByEmailId(emailId).isPresent() ? true : false;
	}

	public ResponseEntity<?> retreive(String token) {
		final var userId = jwtUtils.extractUserId(token.replace("Bearer ", ""));
		final var user = userRepository.findById(userId).get();
		return ResponseEntity.ok(UserDetailsDto.builder().createdAt(user.getCreatedAt())
				.dateOfBirth(user.getDateOfBirth()).emailId(user.getEmailId()).firstName(user.getFirstName())
				.lastName(user.getLastName()).updatedAt(user.getUpdatedAt()).build());
	}

	public ResponseEntity<?> createUser(final UserCreationRequestDto userCreationRequest) {

		if (userExists(userCreationRequest.getEmailId()))
			return responseUtils.duplicateEmailIdResponse();

		final var user = new User();
		user.setEmailId(userCreationRequest.getEmailId());
		user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
		user.setFirstName(userCreationRequest.getFirstName());
		user.setLastName(userCreationRequest.getLastName());
		user.setDateOfBirth(userCreationRequest.getDateOfBirth());

		final var savedUser = userRepository.save(user);


		return responseUtils.userCreationSuccessResponse();
	}

	public ResponseEntity<?> login(final UserLoginRequestDto userLoginRequestDto) {
		final var user = userRepository.findByEmailId(userLoginRequestDto.getEmailId());
		if (user.isEmpty())
			return responseUtils.invalidEmailIdResponse();
		if (passwordEncoder.matches(userLoginRequestDto.getPassword(), user.get().getPassword())) {
			final var retreivedUser = user.get();
			final var jwtToken = jwtUtils.generateToken(retreivedUser);
			return responseUtils.loginSuccessResponse(jwtToken);
		}
		return responseUtils.invalidPasswordResponse();
	}

	public ResponseEntity<?> updatePassword(final UserPasswordUpdationRequestDto userPasswordUpdationRequestDto,
			final String token) {
		final var loggedInUserId = jwtUtils.extractUserId(token.replace("Bearer ", ""));
		final var user = userRepository.findById(loggedInUserId).get();

		if (!passwordEncoder.matches(userPasswordUpdationRequestDto.getOldPassword(), user.getPassword()))
			return responseUtils.invalidPasswordResponse();

		user.setPassword(passwordEncoder.encode(userPasswordUpdationRequestDto.getNewPassword()));
		userRepository.save(user);

		return responseUtils.passwordUpdationSuccessResponse();
	}

	public ResponseEntity<?> deleteAccount(final String token) {
		final var userId = jwtUtils.extractUserId(token.replace("Bearer ", ""));
		final var user = userRepository.findById(userId).get();
		userRepository.deleteById(user.getId());
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<?> update(final UserDetailUpdationRequestDto userDetailUpdationRequest, final String token) {
		final var loggedInUserId = jwtUtils.extractUserId(token.replace("Bearer ", ""));
		final var user = userRepository.findById(loggedInUserId).get();

		user.setFirstName(userDetailUpdationRequest.getFirstName());
		user.setLastName(userDetailUpdationRequest.getLastName());
		user.setDateOfBirth(userDetailUpdationRequest.getDateOfBirth());
		userRepository.save(user);

		return responseUtils.profileDetailUpdationSuccessResponse();
	}

}
