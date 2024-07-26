package com.practice.csa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practice.csa.exception.UsernameNotFoundException;
import com.practice.csa.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws com.practice.csa.exception.UsernameNotFoundException {
		
		System.out.println(username);

		return userRepository.findByEmail(username)
				.map(user -> {
					System.out.println(user.getUserName());
					return new UserDetailsImpl(user);
				})
				.orElseThrow(() -> {
					System.out.println("Exception accurred");
					return new UsernameNotFoundException("user email not found");
				});
	}

}

