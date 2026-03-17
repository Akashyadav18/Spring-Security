package com.Demo.Security.Application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is required")
    @Column
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min=6, max = 20, message = "password must be greater then equal to 6 and less than equal to 20")
    @Column
    private String password;
    @Column
    private Boolean isActive;

}
