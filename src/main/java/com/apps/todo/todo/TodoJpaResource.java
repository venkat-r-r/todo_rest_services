package com.apps.todo.todo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "${frontend.origin.url}")
@RestController
public class TodoJpaResource {
	
	@Autowired
	TodoJpaRepository todoRepository;
	
	@GetMapping(path = "/jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoRepository.findAllByUsername(username);
	}
	
	@GetMapping(path = "/jpa/users/{user}/todos/{id}")
	public Optional<Todo> getTodo(@PathVariable String user, @PathVariable Long id) {
		return todoRepository.findById(id);
	}
	
	@DeleteMapping(path = "/jpa/users/{user}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String user, @PathVariable Long id){
		todoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(path = "/jpa/users/{user}/todos/")
	public ResponseEntity<Void> addTodo(@PathVariable String user, @RequestBody Todo todo){
		todo.setUsername(user);
		Todo res = todoRepository.save(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(res.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path = "/jpa/users/{user}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String user, @PathVariable Long id, @RequestBody Todo todo) {
		todo.setUsername(user);
		Todo updated = todoRepository.save(todo);
		return new ResponseEntity<Todo>(updated, HttpStatus.OK);
	}
}
