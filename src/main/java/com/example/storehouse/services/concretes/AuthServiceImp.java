package com.example.storehouse.services.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.storehouse.contants.Messages;
import com.example.storehouse.exceptions.BusinessException;
import com.example.storehouse.models.User;
import com.example.storehouse.repositories.UserRepository;
import com.example.storehouse.requests.LoginRequest;
import com.example.storehouse.requests.RegisterRequest;
import com.example.storehouse.responses.AuthResponse;
import com.example.storehouse.services.abstracts.AuthService;
import com.example.storehouse.services.abstracts.UserService;
import com.example.storehouse.utils.results.DataResult;
import com.example.storehouse.utils.results.Result;
import com.example.storehouse.utils.results.SuccessDataResult;
import com.example.storehouse.utils.results.SuccessResult;
import com.example.storehouse.utils.security.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImp implements AuthService {

	private UserRepository userRepository;
	private UserService userService;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	public AuthServiceImp(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
		this.userRepository = userRepository;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public Result register(RegisterRequest registerRequest) {
		if (userService.checkIfEmailExists(registerRequest.getEmail())) {
			throw new BusinessException(Messages.emailExists);
		}
		User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setFirstName(registerRequest.getFirstName());
		user.setLastName(registerRequest.getLastName());
		this.userRepository.save(user);

		log.info("Saving new user, {}, to the database", user.getEmail());
		return new SuccessResult(Messages.userRegistered);
	}

	@Override
	public DataResult<AuthResponse> login(LoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
				loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String accessToken = jwtTokenProvider.generateJwtToken(auth);
		User user = this.userRepository.getByEmail(loginRequest.getEmail());
		AuthResponse authResponse = new AuthResponse();
		authResponse.setUserId(user.getId());
		authResponse.setEmail(user.getEmail());
		authResponse.setAccessToken(accessToken);
		return new SuccessDataResult<AuthResponse>(authResponse, Messages.userLogin);
	}

}
