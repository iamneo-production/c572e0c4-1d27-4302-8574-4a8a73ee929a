package com.hackathon.ApiGateway.security;


import com.hackathon.ApiGateway.model.User;
import com.hackathon.ApiGateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByEmail = userService.getByEmail(username);
        return new org.springframework.security.core.userdetails.User(userByEmail.getEmail(), userByEmail.getPassword(), userByEmail.getRoles());
    }
}
