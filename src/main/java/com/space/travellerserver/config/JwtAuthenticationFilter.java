package com.space.travellerserver.config;

import com.space.travellerserver.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        //Go to next responsibility filter if there is no token in auth header
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }

        //Extract token
        jwtToken = authHeader.substring(7);

        //Extract user email
        userEmail = jwtService.extractUsername(jwtToken);//TODO: extract user email from JWT token
    }
}