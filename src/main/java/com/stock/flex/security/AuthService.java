package com.stock.flex.security;

import com.stock.flex.resource.request.AuthRequest;
import com.stock.flex.resource.response.AuthResponse;
import com.stock.flex.resource.request.RegisterRequest;
import com.stock.flex.entity.UserEntity;
import com.stock.flex.useCase.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {

	@Autowired
	private UserUseCase userUseCase;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthResponse register(RegisterRequest request) {
		
		UserEntity userEntity = new UserEntity();
		userEntity.setName(request.name());
		userEntity.setEmail(request.email());
		userEntity.setPassword(passwordEncoder.encode(request.password()));
		
		userEntity = userUseCase.create(userEntity);
		
		return new AuthResponse(jwtService.generateToken(userEntity.getEmail()));
	}
	
	public AuthResponse authenticate(AuthRequest request) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.email(),
						request.password()));
		
		final UserEntity userEntity = userUseCase.findByEmail(request.email());
		return new AuthResponse(jwtService.generateToken(userEntity.getEmail()));
	}
	
}
