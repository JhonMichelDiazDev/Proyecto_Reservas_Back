package com.JMichelD.Proyecto_Reservas_Back.auth.controller;

import com.JMichelD.Proyecto_Reservas_Back.auth.model.AuthRequest;
import com.JMichelD.Proyecto_Reservas_Back.auth.model.AuthResponse;
import com.JMichelD.Proyecto_Reservas_Back.service.implement.UsuarioServiceImple;
import com.JMichelD.Proyecto_Reservas_Back.auth.model.RegistroRequest;
import com.JMichelD.Proyecto_Reservas_Back.model.Usuario;
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
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegistroRequest registroRequest) {
        // Convertir el registroRequest a un objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(registroRequest.getUsername());
        usuario.setPassword(registroRequest.getPassword());

        UsuarioServiceImple usuarioService = new UsuarioServiceImple();

        Usuario usuarioRegistrado = usuarioService.registerUser(usuario);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registroRequest.getUsername(), registroRequest.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }
}
