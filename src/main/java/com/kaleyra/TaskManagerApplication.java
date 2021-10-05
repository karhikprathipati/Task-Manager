package com.kaleyra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kaleyra.entity.Todo;
import com.kaleyra.entity.User;
import com.kaleyra.repository.TodoRepository;
import com.kaleyra.repository.UserRepository;

@SpringBootApplication
public class TaskManagerApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setPassword("pass");
		user.setUserName("karthik");

		Todo todo = new Todo();
		todo.setContent("Dbms");
		user.getTodoList().add(todo);

		userRepository.save(user);
		todoRepository.save(todo);

	}

}
