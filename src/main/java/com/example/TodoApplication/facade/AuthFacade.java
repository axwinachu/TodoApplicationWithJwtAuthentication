package com.example.TodoApplication.facade;

import com.example.TodoApplication.dto.UserRequest;
import com.example.TodoApplication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacade {
    private final AuthService authService;
    public ResponseEntity<String> signup(UserRequest userRequest) {
        return authService.signup(userRequest);
    }
}
