package com.springrepo.springbackend.repository;

import com.springrepo.springbackend.model.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    // You can add custom query methods here if needed
}

