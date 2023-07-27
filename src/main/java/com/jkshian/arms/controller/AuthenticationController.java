package com.jkshian.arms.controller;

import com.jkshian.arms.dto.AuthenticationRequest;
import com.jkshian.arms.dto.AuthenticationResponse;
import com.jkshian.arms.dto.RegisterRequest;
import com.jkshian.arms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;


    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration() { return "registration";}


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestBody RegisterRequest request
    ){
        return  ResponseEntity.ok(service.registerUser(request));
    }



  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
    AuthenticationResponse response = service.authenticate(request);
          if(response !=null) {
              return ResponseEntity.ok(response);
          }
        return null;
}


}
