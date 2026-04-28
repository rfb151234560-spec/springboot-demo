package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Todo create(@RequestBody Todo todo) {
		return service.createTodo(todo);
	}

}
