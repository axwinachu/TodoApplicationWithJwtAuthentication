package com.example.TodoApplication.controller;

import com.example.TodoApplication.dto.UserRequest;
import com.example.TodoApplication.facade.AuthFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthFacade authFacade;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserRequest userRequest){
        return authFacade.signup(userRequest);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest){
        return authFacade.login(userRequest);
    }

}
