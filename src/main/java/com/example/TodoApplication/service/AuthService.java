package com.example.TodoApplication.service;

import com.example.TodoApplication.dto.UserRequest;
import com.example.TodoApplication.entity.Users;
import com.example.TodoApplication.exception.NotFoundException;
import com.example.TodoApplication.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    public ResponseEntity<String> signup(UserRequest userRequest) {
        String email=userRequest.getEmail();
        String password=passwordEncoder.encode(userRequest.getPassword());
        Optional<Users> user=usersRepository.findByEmail(email);
        if(user.isPresent()){
            throw new NotFoundException("already have an account");
        }
        Users newUser=Users.builder().email(email).password(password).build();
        usersRepository.save(newUser);
        return ResponseEntity.ok("user Created successfully");
    }
}
