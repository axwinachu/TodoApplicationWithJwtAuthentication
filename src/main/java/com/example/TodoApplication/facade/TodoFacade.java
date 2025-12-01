package com.example.TodoApplication.facade;

import com.example.TodoApplication.dto.TodoRequest;
import com.example.TodoApplication.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class TodoFacade {
    private  final TodoService todoService;

    public ResponseEntity<?> getAll() {
        return todoService.getAllTask();
    }

    public ResponseEntity<?> findById(int id) {
        return todoService.getById(id);
    }

    public ResponseEntity<String> addTask(TodoRequest todoRequest) {
        return todoService.addTask(todoRequest);
    }

    public ResponseEntity<String> updateTask(TodoRequest todoRequest) {
        return  todoService.updateTask(todoRequest);
    }

    public ResponseEntity<String> deleteTask(int id) {
        return todoService.deleteTaskById(id);
    }
}
