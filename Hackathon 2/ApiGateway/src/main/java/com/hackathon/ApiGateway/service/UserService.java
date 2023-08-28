package com.hackathon.ApiGateway.service;


import com.hackathon.ApiGateway.model.User;
import com.hackathon.ApiGateway.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(@Valid User user){
        return userRepository.save(user);
    }

    public User getById(Long id){
        // todo : here also
        return userRepository.findById(id).get();
    }

    public  User getByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
