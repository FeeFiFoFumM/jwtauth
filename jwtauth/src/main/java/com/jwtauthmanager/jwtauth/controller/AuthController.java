package com.jwtauthmanager.jwtauth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauthmanager.jwtauth.models.entity.User;
import com.jwtauthmanager.jwtauth.models.userdetail.UserDetailslImpl;
import com.jwtauthmanager.jwtauth.repository.UserRepository;
import com.jwtauthmanager.jwtauth.services.UserDetailsServiceImpl;
import com.jwtauthmanager.jwtauth.utils.JwtTokenManager;

@RestController
@RequestMapping("/auth")
public class AuthController {

    JwtTokenManager jwtTokenManager;

    UserRepository userRepository;

    UserDetailsServiceImpl userDetailsServiceImpl;

    AuthController(UserRepository userRepository, UserDetailsServiceImpl userDetailsServiceImpl,JwtTokenManager jwtTokenManager) {
        this.userRepository = userRepository;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtTokenManager = jwtTokenManager;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping("/{username}") //Sonradan ResponseUser Dto su oluştur ResponseEntity içinde onu dön
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        try {
            UserDetailslImpl userDetails = (UserDetailslImpl) userDetailsServiceImpl.loadUserByUsername(username);
            String token = jwtTokenManager.generateJwtToken(userDetails);
            System.out.println(token);
            System.out.println(jwtTokenManager.getUserNameFromJwt(token));
            System.out.println(jwtTokenManager.validateToken(token));
            return ResponseEntity
                    .ok(new User(userDetails.getUsername(), userDetails.getEmail(), userDetails.getPassword()));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
