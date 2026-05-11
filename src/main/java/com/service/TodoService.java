package com.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	
	
	public Page <TodoResponse> getAllTodos(int page, int size){
		
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Todo> todoPage = todoRepository.findAll(pageable);
		
		return todoPage.map(TodoMapper :: toResponse);
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
