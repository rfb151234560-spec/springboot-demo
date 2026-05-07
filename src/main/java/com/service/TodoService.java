package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.model.Todo;
import com.repository.TodoRepository;
import com.dto.TodoRequest;
import com.dto.TodoResponse;
import com.exception.ResourceNotFoundException;
import com.mapper.TodoMapper;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;
	

	
	
	public TodoResponse createTodo(TodoRequest request) {
		
//		Todo todo = new Todo();
//		todo.setTitle(request.getTitle());
//		todo.setCompleted(request.getCompleted());
		
		//因為有TodoMapper所以不需要手動new
		Todo todo = TodoMapper.toEntity(request);
		
		Todo saved = todoRepository.save(todo);
		
//		return new TodoResponse(
//				saved.getId(),
//				saved.getTitle(),
//				saved.getCompleted()
//				);
		
		return TodoMapper.toResponse(saved);
	}
	
	
	public List<TodoResponse> getAllTodos(){
		List<Todo> todos = todoRepository.findAll();
		
		List<TodoResponse> result  = new ArrayList<>();
		
		for(Todo t : todos) {
			result.add(new TodoResponse(
					t.getId(),
					t.getTitle(),
					t.getCompleted()
					));
		}
		return result;
	}
	
	public Todo getTodoById(Long id) {
		Optional<Todo> optional = todoRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new ResourceNotFoundException("Todo not found");
		}
				
	}
	
	public Todo updateTodo(Long id, Todo newTodo) {
		Todo todo = getTodoById(id);
		todo.setTitle(newTodo.getTitle());
		todo.setCompleted(newTodo.getCompleted()); 
		return todoRepository.save(todo);
	}
	
	  public void deleteTodo(Long id) {
		  Todo todo = getTodoById(id);
	        todoRepository.deleteById(id);
	    }
}
