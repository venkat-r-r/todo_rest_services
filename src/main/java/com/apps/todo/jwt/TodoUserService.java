package com.apps.todo.jwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apps.todo.user.TodoUser;
import com.apps.todo.user.UserJpaRepository;

@Service
public class TodoUserService implements  UserDetailsService{

	@Autowired
	UserJpaRepository userJpaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<TodoUser> findfirst = userJpaRepository.findById(username);
		if(!findfirst.isPresent()) throw new UsernameNotFoundException(String.format("User not found '%s'", username));
		return findfirst.get();
	}

}
