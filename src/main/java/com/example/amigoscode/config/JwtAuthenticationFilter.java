package com.example.amigoscode.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.amigoscode.AuthServices.Utils.JwtUtil;
import com.example.amigoscode.AuthServices.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

       private final UserRepository userRepository;
       private final JwtUtil jwtUtil;
       
    
    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain )
            throws ServletException, IOException {
                final String authHeader = request.getHeader("Authorization");
                final String jwtToken ;
                final String userEmail;
                if(authHeader == null || !authHeader.startsWith("Bearer ")){
                    filterChain.doFilter(request, response);
                    return;
                }
                jwtToken = authHeader.substring(7);
                userEmail= jwtUtil.extractUsername(jwtToken);
    }

}
