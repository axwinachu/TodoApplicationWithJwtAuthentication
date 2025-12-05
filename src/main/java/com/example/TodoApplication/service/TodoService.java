package com.example.TodoApplication.service;

import com.example.TodoApplication.mapper.TaskMapper;
import com.example.TodoApplication.dto.TodoRequest;
import com.example.TodoApplication.dto.TodoResponse;
import com.example.TodoApplication.entity.Todo;
import com.example.TodoApplication.exception.NotFoundException;
import com.example.TodoApplication.repository.TodoRepository;
import com.example.TodoApplication.responce.TodoResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TaskMapper taskMapper;
    public  ResponseEntity<?> getAllTask() {
        List<Todo> allTask=todoRepository.findAll();
        if(allTask.isEmpty()){
            new ResponseEntity<>(TodoResponseMessage.INVALID_UPDATE, HttpStatus.NOT_FOUND);
        }
        List<TodoResponse> todos=allTask.stream().map(t->taskMapper.toDto(t)).collect(Collectors.toList());
        return  new ResponseEntity<>(todos,HttpStatus.OK);
    }

    public ResponseEntity<?> getById(int id) {
       Optional<Todo> todo=todoRepository.findById(id);
       if(todo.isEmpty()){
           return new ResponseEntity<>(TodoResponseMessage.TODO_NOT_FOUND,HttpStatus.BAD_REQUEST);
       }
       TodoResponse todoRequest=taskMapper.toDto(todo.get());
       return new ResponseEntity<>(todoRequest,HttpStatus.OK);
    }

    public ResponseEntity<String> addTask(TodoRequest todoRequest) {
        Todo todo=Todo.builder().task(todoRequest.getTask()).complete(todoRequest.getComplete()).build();
        todoRepository.save(todo);
        return new ResponseEntity<>(TodoResponseMessage.TODO_ADDED,HttpStatus.OK);
    }

    public ResponseEntity<String> updateTask(TodoRequest todoRequest) {
        Optional<Todo> todo=todoRepository.findById(todoRequest.getId());
        if(todo.isEmpty()){
           throw  new NotFoundException(TodoResponseMessage.TODO_NOT_FOUND);
        }
        Todo updatedTodo=Todo.builder().id(todoRequest.getId()).task(todoRequest.getTask()).complete(todoRequest.getComplete()).build();
        todoRepository.save(updatedTodo);
        return new ResponseEntity<>(TodoResponseMessage.TODO_UPDATED,HttpStatus.OK);
    }

    public ResponseEntity<String> deleteTaskById(int id) {
        Optional<Todo> todos=todoRepository.findById(id);
        if(todos.isEmpty()){
            throw new NotFoundException(TodoResponseMessage.TODO_NOT_FOUND);
        }
        todoRepository.deleteById(id);
        return new ResponseEntity<>(TodoResponseMessage.TODO_DELETED,HttpStatus.OK);
    }
}
