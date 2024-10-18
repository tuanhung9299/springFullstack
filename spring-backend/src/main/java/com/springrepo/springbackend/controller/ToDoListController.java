package com.springrepo.springbackend.controller;

import com.springrepo.springbackend.dto.ToDoListDTO;
import com.springrepo.springbackend.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//controller :where the services actually utilized
@RestController
@CrossOrigin("http://localhost:3000")
public class ToDoListController {

	   @Autowired
	    private ToDoListService toDoListService;

    @PostMapping("/add")
    public ResponseEntity<ToDoListDTO> addTodoItem(@RequestBody ToDoListDTO todoItemDTO) {
        ToDoListDTO savedItemDTO = toDoListService.saveTodoItem(todoItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItemDTO);
    }

    @GetMapping("/todolists")
    public ResponseEntity<List<ToDoListDTO>> getAllRecords() {
        List<ToDoListDTO> allRecordsDTO = toDoListService.getAllRecords();
        return ResponseEntity.ok().body(allRecordsDTO);
    }

    @DeleteMapping("/tododelete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        try {
            toDoListService.deleteTaskById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting task");
        }
    }

    @GetMapping("/tasktodisplay/{id}")
    public ResponseEntity<ToDoListDTO> getTaskById(@PathVariable Long id) {
        ToDoListDTO taskDTO = toDoListService.getTaskById(id);
        if (taskDTO != null) {
            return ResponseEntity.ok().body(taskDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/TaskToUpdate/{id}")
    public ResponseEntity<ToDoListDTO> updateTask(@PathVariable Long id, @RequestBody ToDoListDTO updatedTaskDTO) {
        ToDoListDTO updatedTask = toDoListService.updateTask(id, updatedTaskDTO);
        if (updatedTask != null) {
            return ResponseEntity.ok().body(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/markComplete/{id}")
    public ResponseEntity<Void> markTaskAsComplete(@PathVariable Long id) {
        toDoListService.markTaskAsComplete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/markIncomplete/{id}")
    public ResponseEntity<Void> markTaskAsIncomplete(@PathVariable Long id) {
        toDoListService.markTaskAsIncomplete(id);
        return ResponseEntity.ok().build();
    }
}
