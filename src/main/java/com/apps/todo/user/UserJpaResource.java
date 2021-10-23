package com.apps.todo.user;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "${frontend.origin.url}")
@RestController
public class UserJpaResource {
	
	@Autowired
	UserJpaRepository userRepository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@PostMapping(path = "/register")
	public ResponseEntity<Void> createUserAccount(@RequestBody TodoUser user){
		Optional<TodoUser> opt = userRepository.findById(user.getUsername());
		if(opt.isEmpty()) {
			user.setPassword(encoder.encode(user.getPassword()));
			TodoUser createdUser = userRepository.save(user);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(createdUser.getUsername()).toUri();
			return ResponseEntity.created(uri).build();
		}
		return ResponseEntity.badRequest().build();
	}
}
