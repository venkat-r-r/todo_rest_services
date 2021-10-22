package com.apps.todo.hello.world;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "${frontend.origin.url}")
@RestController
public class HelloWorldController {
	
	@GetMapping(path = "/hello-world/{name}")
	public HelloWorldBean HelloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(name);
	}
}
