package com.vass.springboot.SeguridadConSpringBootUsandoJWT.exception;

import com.vass.springboot.SeguridadConSpringBootUsandoJWT.error.ErrorCodeEnum;
import com.vass.springboot.SeguridadConSpringBootUsandoJWT.error.InternalServerErrorException;
import com.vass.springboot.SeguridadConSpringBootUsandoJWT.exception.ServerErrorResponseDto;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends InternalServerErrorException {
    public InvalidCredentialsException() {
        super(new ServerErrorResponseDto("Invalid username or password", ErrorCodeEnum.INVALID_USER_CREDENTIALS,
                HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }
}