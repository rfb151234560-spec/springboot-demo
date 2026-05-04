package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;


import com.model.Todo;
import com.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	@GetMapping
	public List<Todo> getAll(){
		return service.getAllTodos();
	}
	
	@PostMapping
	public ResponseEntity<Todo> create(@RequestBody Todo todo) {
		Todo savedTodo = service.createTodo(todo);
		return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo todo) {
		Todo updated = service.updateTodo(id, todo);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			service.deleteTodo(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}")
	public Todo getById(@PathVariable Long id) {
		return service.getTodoById(id);
	}
	
	}



