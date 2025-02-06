// src/main/java/com/tuempresa/proyecto/controller/AuthController.java
package com.JMichelD.Proyecto_Reservas_Back.auth.controller;

import com.JMichelD.Proyecto_Reservas_Back.auth.model.AuthRequest;
import com.JMichelD.Proyecto_Reservas_Back.auth.model.AuthResponse;
import com.JMichelD.Proyecto_Reservas_Back.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        System.out.println(userDetails);
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token);
    }
}
