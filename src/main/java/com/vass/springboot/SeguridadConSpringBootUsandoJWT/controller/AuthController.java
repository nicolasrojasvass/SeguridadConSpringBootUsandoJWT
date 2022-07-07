package com.vass.springboot.SeguridadConSpringBootUsandoJWT.controller;

import com.vass.springboot.SeguridadConSpringBootUsandoJWT.exception.InvalidCredentialsException;
import com.vass.springboot.SeguridadConSpringBootUsandoJWT.security.dto.LoginDto;
import com.vass.springboot.SeguridadConSpringBootUsandoJWT.security.dto.TokenDto;
import com.vass.springboot.SeguridadConSpringBootUsandoJWT.repository.document.User;
import com.vass.springboot.SeguridadConSpringBootUsandoJWT.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

import static com.vass.springboot.SeguridadConSpringBootUsandoJWT.config.Constants.TOKEN_DURATION_MINUTES;
import static com.vass.springboot.SeguridadConSpringBootUsandoJWT.config.Constants.CLAIMS_ROLES_KEY;

@RestController
@RequestMapping("v1/auth")
public class AuthController {

    @Value("${app.secret}")
    String secret;

    private final UserService userService;

    public AuthController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public TokenDto login(@RequestBody LoginDto loginDto) {
        User user = userService.findByEmail(loginDto.getEmail());
        if (BCrypt.checkpw(loginDto.getPassword(), user.getPasswordHash())) {
            return generateTokenDto(user);
        } else {
            throw new InvalidCredentialsException();
        }

    }

    private String generateToken(User user, Date expirationDate) {
        return Jwts.builder()
                .setSubject(user.getId())
                .claim(CLAIMS_ROLES_KEY, user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private TokenDto generateTokenDto(User user) {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.MINUTE, TOKEN_DURATION_MINUTES);
        String token = generateToken(user, expirationDate.getTime());
        return new TokenDto(token, expirationDate.getTime());
    }
}
