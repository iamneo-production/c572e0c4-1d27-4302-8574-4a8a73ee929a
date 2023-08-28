package com.hackathon.UserRegistration.controller;


import com.hackathon.UserRegistration.dto.UserDTO;
import com.hackathon.UserRegistration.model.Role;
import com.hackathon.UserRegistration.model.User;
import com.hackathon.UserRegistration.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @PostMapping("/register/patient")
    public ResponseEntity<?> createPatient(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()){
            Map<String,String> errorObject = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorObject);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(List.of(new Role(1L,"ROLE_PATIENT")));
        User saved = userService.save(user);
        UserDTO userDTO = UserDTO.builder().name(user.getName()).email(user.getEmail()).id(user.getId()).build();
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register/doctor")
    public ResponseEntity<?> createDoctor(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()){
            Map<String,String> errorObject = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorObject);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(List.of(new Role(2L,"ROLE_DOCTOR")));
        User saved = userService.save(user);
        UserDTO userDTO = UserDTO.builder().name(user.getName()).email(user.getEmail()).id(user.getId()).build();
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        UserDTO userDTO = UserDTO.builder().name(user.getName()).email(user.getEmail()).id(user.getId()).build();
        return ResponseEntity.ok(userDTO);
    }

}
