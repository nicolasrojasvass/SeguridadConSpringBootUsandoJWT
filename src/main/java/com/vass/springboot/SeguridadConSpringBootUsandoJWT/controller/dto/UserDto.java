package com.vass.springboot.SeguridadConSpringBootUsandoJWT.controller.dto;

public class UserDto {
    String name;

    String lastName;

    String email;

    String password;

    public UserDto() {
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}