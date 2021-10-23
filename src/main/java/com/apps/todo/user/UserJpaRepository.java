package com.apps.todo.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<TodoUser, String>{
}
