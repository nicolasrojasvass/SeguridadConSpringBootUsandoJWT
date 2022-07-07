package com.vass.springboot.SeguridadConSpringBootUsandoJWT.service;

import com.vass.springboot.SeguridadConSpringBootUsandoJWT.controller.dto.UserDto;
import com.vass.springboot.SeguridadConSpringBootUsandoJWT.exception.UserNotFoundException;
import com.vass.springboot.SeguridadConSpringBootUsandoJWT.repository.document.User;

import java.util.List;

public interface UserService {
    User create(UserDto userDto);

    User findById(String id) throws UserNotFoundException;

    User findByEmail(String email) throws UserNotFoundException;

    List<User> all();

    boolean deleteById(String id);

    User update(UserDto userDto, String id);
}