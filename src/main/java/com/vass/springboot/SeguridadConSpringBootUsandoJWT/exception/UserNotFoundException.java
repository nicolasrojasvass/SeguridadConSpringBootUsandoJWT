package com.vass.springboot.SeguridadConSpringBootUsandoJWT.exception;


import com.vass.springboot.SeguridadConSpringBootUsandoJWT.error.ErrorCodeEnum;
import com.vass.springboot.SeguridadConSpringBootUsandoJWT.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends InternalServerErrorException {
    public UserNotFoundException() {
        super(new ServerErrorResponseDto("User not found", ErrorCodeEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }
}