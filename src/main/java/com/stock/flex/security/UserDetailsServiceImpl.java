package com.stock.flex.security;

import com.stock.flex.useCase.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserUseCase userUseCase;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			return userUseCase.findByEmail(username);
		}
		catch (Exception e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

}
