package com.hackathon.UserRegistration.service;


import com.hackathon.UserRegistration.model.User;
import com.hackathon.UserRegistration.repository.UserRepository;
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
