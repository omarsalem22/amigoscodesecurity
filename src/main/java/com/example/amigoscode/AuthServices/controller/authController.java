package com.example.amigoscode.AuthServices.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.amigoscode.AuthServices.dto.AuthResponse;
import com.example.amigoscode.AuthServices.dto.AuthenticationRequest;
import com.example.amigoscode.AuthServices.dto.RegisterRequest;
import com.example.amigoscode.AuthServices.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class authController {
    private  final AuthService authService;

@PostMapping("register")
public ResponseEntity<String> register(@RequestBody RegisterRequest request){
    return ResponseEntity.ok(authService.register(request));   }

    @PostMapping("authenticate")

    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
