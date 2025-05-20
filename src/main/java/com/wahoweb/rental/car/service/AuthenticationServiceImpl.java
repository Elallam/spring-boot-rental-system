package com.wahoweb.rental.car.service;

import com.wahoweb.rental.car.config.JwtService;
import com.wahoweb.rental.car.controller.AuthenticationRequest;
import com.wahoweb.rental.car.controller.AuthenticationResponse;
import com.wahoweb.rental.car.controller.RegisterRequest;
import com.wahoweb.rental.car.dao.UserRepo;
import com.wahoweb.rental.car.entity.Role;
import com.wahoweb.rental.car.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{


    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();
        if(userRepo.findByLogin(user.getLogin()) != null){
            var jwtToken = jwtService.generateToken(user);
            return   AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        else{
            userRepo.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(user)
                    .build();
        }

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByLogin(request.getLogin());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(user)
                .build();
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }
}
