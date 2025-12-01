package com.example.TodoApplication.Mapper;

import com.example.TodoApplication.dto.TodoResponse;
import com.example.TodoApplication.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TodoResponse toDto(Todo todo){
        return TodoResponse.builder()
                .task(todo.getTask())
                .complete(todo.getComplete())
                .build();
    }
}
