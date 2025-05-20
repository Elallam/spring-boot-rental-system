package com.wahoweb.rental.car.controller;

import com.wahoweb.rental.car.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        //
        System.out.println("Authen is called");
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/get-users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(authenticationService.getUsers());
    }

}
