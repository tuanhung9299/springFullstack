package com.springrepo.springbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoListDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
