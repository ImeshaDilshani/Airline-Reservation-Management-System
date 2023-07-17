package com.jkshian.arms.controller;

import com.jkshian.arms.config.ApplicationConfig;
import com.jkshian.arms.config.JwtService;
import com.jkshian.arms.dao.UserDao;
import com.jkshian.arms.dto.AuthenticationRequest;
import com.jkshian.arms.dto.AuthenticationResponse;
import com.jkshian.arms.dto.RegisterRequest;
import com.jkshian.arms.entity.User;
import com.jkshian.arms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticationService service;
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(
//            @RequestBody AuthenticationRequest request
//    ) {
//        return ResponseEntity.ok(service.authenticate(request));
//
//    }
@PostMapping("/authenticate")
public ResponseEntity<String> authenticate(@RequestBody  AuthenticationRequest request){
  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
    final UserDetails user = userDao.findUserByEmail(request.getEmail());
    if(user != null) {
        return ResponseEntity.ok(jwtService.generateToken(user));

    }
    return ResponseEntity.status(400).body("Some Error Occurred");
}



}

