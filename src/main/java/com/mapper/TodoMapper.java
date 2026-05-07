package com.mapper;

import com.dto.TodoRequest;
import com.dto.TodoResponse;
import com.model.Todo;

public class TodoMapper {

	//Request Entitiy
	public static Todo toEntity(TodoRequest request) {
		
		Todo todo = new Todo();
		
		todo.setTitle(request.getTitle());
		todo.setCompleted(request.getCompleted());
		
		return todo;
	}
	
	//Entity Request
	public static TodoResponse toResponse(Todo todo) {
		
		return new TodoResponse(
				todo.getId(),
				todo.getTitle(),
				todo.getCompleted()
				);
	}
}
