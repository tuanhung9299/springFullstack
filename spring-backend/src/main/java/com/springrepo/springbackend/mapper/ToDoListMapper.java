package com.springrepo.springbackend.mapper;

import com.springrepo.springbackend.dto.ToDoListDTO;
import com.springrepo.springbackend.model.ToDoList;
import org.springframework.stereotype.Component;

@Component
public class ToDoListMapper {

    public ToDoListDTO toDTO(ToDoList toDoList) {
        ToDoListDTO dto = new ToDoListDTO();
        dto.setId(toDoList.getId());
        dto.setTitle(toDoList.getTitle());
        dto.setDescription(toDoList.getDescription());
        dto.setCompleted(toDoList.isCompleted());
        return dto;
    }

    public ToDoList toEntity(ToDoListDTO dto) {
        ToDoList toDoList = new ToDoList();
        toDoList.setId(dto.getId());
        toDoList.setTitle(dto.getTitle());
        toDoList.setDescription(dto.getDescription());
        toDoList.setCompleted(dto.isCompleted());
        return toDoList;
    }
}
