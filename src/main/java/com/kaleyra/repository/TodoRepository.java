package com.kaleyra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaleyra.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
