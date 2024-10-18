package com.springrepo.springbackend.service;

import com.springrepo.springbackend.dto.ToDoListDTO;
import com.springrepo.springbackend.mapper.ToDoListMapper;
import com.springrepo.springbackend.model.ToDoList;
import com.springrepo.springbackend.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



//service class that will provide all operations to do with database 
@Service
public class ToDoListService {

	@Autowired
    private ToDoListRepository toDoListRepository;
    
    @Autowired
    private ToDoListMapper toDoListMapper;

    public ToDoListDTO saveTodoItem(ToDoListDTO toDoListDTO) {
        ToDoList toDoList = toDoListMapper.toEntity(toDoListDTO);
        return toDoListMapper.toDTO(toDoListRepository.save(toDoList));
    }

    public List<ToDoListDTO> getAllRecords() {
        return toDoListRepository.findAll()
                .stream()
                .map(toDoListMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteTaskById(Long id) {
        toDoListRepository.deleteById(id);
    }

    public ToDoListDTO getTaskById(Long id) {
        ToDoList toDoList = toDoListRepository.findById(id).orElse(null);
        return toDoList != null ? toDoListMapper.toDTO(toDoList) : null;
    }

    public ToDoListDTO updateTask(Long id, ToDoListDTO updatedTaskDTO) {
        ToDoList updatedTask = toDoListMapper.toEntity(updatedTaskDTO);
        ToDoList existingTask = toDoListRepository.findById(id).orElse(null);

        if (existingTask != null) {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());

            return toDoListMapper.toDTO(toDoListRepository.save(existingTask));
        }

        return null;
    }

    public void markTaskAsComplete(Long id) {
        ToDoList task = toDoListRepository.findById(id).orElse(null);
        if (task != null) {
            task.setCompleted(true);
            toDoListRepository.save(task);
        }
    }

    public void markTaskAsIncomplete(Long id) {
        ToDoList task = toDoListRepository.findById(id).orElse(null);
        if (task != null) {
            task.setCompleted(false);
            toDoListRepository.save(task);
        }
    }
}
