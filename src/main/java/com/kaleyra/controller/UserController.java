package com.kaleyra.controller;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaleyra.entity.Todo;
import com.kaleyra.entity.User;
import com.kaleyra.repository.TodoRepository;
import com.kaleyra.repository.UserRepository;
import com.kaleyra.request.TodoRequest;
import com.kaleyra.request.UserRequest;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserRepository userRepository;
	private TodoRepository todoRepository;

	public UserController(UserRepository userRepository, TodoRepository todoRepository) {
		this.userRepository = userRepository;
		this.todoRepository = todoRepository;
	}

	@GetMapping("/{userId}")
	public User getUser(@PathVariable Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	}

	@PostMapping
	public User addUser(@RequestBody UserRequest userRequest) {
		User user = new User();
		user.setPassword(userRequest.getPassword());
		user.setUserName(userRequest.getUsername());
		return userRepository.save(user);
	}

	@PostMapping("/{userId}/todo")
	public void addTodo(@PathVariable Long userId, @RequestBody TodoRequest todoRequest) {
		Todo todo = new Todo();
		todo.setContent(todoRequest.getContent());
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
		user.getTodoList().add(todo);
		todoRepository.save(todo);
	}

	@PostMapping("/toggle/{todoId}")
	public void toggleTodo(@PathVariable Long todoId) {
		Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
		todo.setCompleted(!todo.getCompleted());
		todoRepository.save(todo);
	}

	@DeleteMapping("/{userId}/{todoId}")
	public void deleteTodo(@PathVariable Long userId, @PathVariable Long todoId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
		Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
		user.getTodoList().remove(todo);
		todoRepository.deleteById(todoId);
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userRepository.deleteById(userId);
	}

}
