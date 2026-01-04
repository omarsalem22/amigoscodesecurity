package com.example.amigoscode.AuthServices.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.amigoscode.AuthServices.Utils.JwtUtil;
import com.example.amigoscode.AuthServices.dto.AuthResponse;
import com.example.amigoscode.AuthServices.dto.AuthenticationRequest;
import com.example.amigoscode.AuthServices.dto.RegisterRequest;
import com.example.amigoscode.AuthServices.entities.Role;
import com.example.amigoscode.AuthServices.entities.User;
import com.example.amigoscode.AuthServices.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private final AuthenticationManager authenticationManager;

  public String register(RegisterRequest request) {
    var user = User.builder().username(request.getFirstName() + " " + request.getLastName()).email(request.getEmail()).enabled(true)
        .password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
    userRepository.save(user);
    return "user registered successfully";
  }

  public AuthResponse authenticate(AuthenticationRequest request) {
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtUtil.generateToken(user);
    return AuthResponse.builder().token(jwtToken).build();

  }

}
