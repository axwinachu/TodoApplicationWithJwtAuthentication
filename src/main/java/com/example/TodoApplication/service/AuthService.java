package com.example.TodoApplication.service;

import com.example.TodoApplication.dto.UserRequest;
import com.example.TodoApplication.entity.Users;
import com.example.TodoApplication.exception.NotFoundException;
import com.example.TodoApplication.jwtUtils.JwtUtil;
import com.example.TodoApplication.repository.UsersRepository;
import com.example.TodoApplication.responce.AuthenticationResponseMessage;
import com.example.TodoApplication.responce.TodoResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public ResponseEntity<String> signup(UserRequest userRequest) {
        String email=userRequest.getEmail();
        String password=passwordEncoder.encode(userRequest.getPassword());
        Optional<Users> user=usersRepository.findByEmail(email);
        if(user.isPresent()){
            throw new NotFoundException(AuthenticationResponseMessage.ALREADY_HAVE_ACCOUNT);
        }
        Users newUser=Users.builder().email(email).password(password).role("ROLE_"+userRequest.getRole().toUpperCase()).build();
        usersRepository.save(newUser);
        return ResponseEntity.ok(AuthenticationResponseMessage.SIGNUP_SUCCESFULLY);
    }

    public ResponseEntity<?> login(UserRequest userRequest) {
        Optional<Users> validateUser=usersRepository.findByEmail(userRequest.getEmail());
        String email=userRequest.getEmail();
        String password=userRequest.getPassword();
        if(validateUser.isEmpty()){
            throw new NotFoundException(AuthenticationResponseMessage.LOGIN_FAILED);
        }
        if(!passwordEncoder.matches(userRequest.getPassword(),validateUser.get().getPassword())){
            return new ResponseEntity<>(AuthenticationResponseMessage.INVALID_CREDENTIAL,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Map.of("token",jwtUtil.generateToken(email,validateUser.get().getRole())),HttpStatus.OK);
    }
}
