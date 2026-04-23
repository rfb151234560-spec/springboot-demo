package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
