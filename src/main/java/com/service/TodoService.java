package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Todo;
import com.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	public Todo createTodo(Todo todo) {
		return todoRepository.save(todo);
	}
	
	public List<Todo> getAllTodos(){
		return todoRepository.findAll();
	}
	
	public Todo getTodoById(Long id) {
		Optional<Todo> optional = todoRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new RuntimeException("Todo not found");
		}
				
	}
	
	public Todo updateTodo(Long id, Todo newTodo) {
		Todo todo = getTodoById(id);
		todo.setTitle(newTodo.getTitle());
		return todoRepository.save(todo);
	}
	
	  public void deleteTodo(Long id) {
	        todoRepository.deleteById(id);
	    }
}
