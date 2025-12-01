package com.example.TodoApplication.controller;

import com.example.TodoApplication.dto.TodoRequest;
import com.example.TodoApplication.facade.TodoFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoFacade todoFacade;
    @GetMapping("/getall")
    public ResponseEntity<?> getAllTask(){
       return todoFacade.getAll();
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return todoFacade.findById(id);
    }
    @PostMapping("/addtask")
    public ResponseEntity<String> addNewTask(@RequestBody TodoRequest todoRequest){
        return todoFacade.addTask(todoRequest);
    }
    @PutMapping("/updateTask")
    public ResponseEntity<String> updateTask(@RequestBody TodoRequest todoRequest){
        return todoFacade.updateTask(todoRequest);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id){
        return todoFacade.deleteTask(id);
    }
}
