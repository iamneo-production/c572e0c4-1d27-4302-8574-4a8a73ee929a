package com.hackathon.ApiGateway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min = 6)
    String name;
    @Email
    String email;
    @Size(min = 8)
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    List<Role> roles;

}
