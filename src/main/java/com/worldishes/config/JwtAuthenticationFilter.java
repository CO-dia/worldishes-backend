package com.worldishes.config;

import com.worldishes.models.Role;
import com.worldishes.models.User;
import com.worldishes.services.JwtService;
import com.worldishes.services.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    public JwtAuthenticationFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        try {
            Claims claims = jwtService.extractAllClaims(jwt);

            User user = userService.getOrCreateUser(claims);

            if (user.getRole() == null) {
                System.out.println("⚠️ User role is null, assigning default role");
                user.setRole(Role.USER);
            }

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            if (!jwtService.isTokenExpired(jwt)) {
                // ✅ JWT is valid, just log or process claims if needed
                System.out.println("Valid JWT for: " + claims.getSubject());
            } else {
                System.out.println("JWT is expired or invalid");
            }
        } catch (Exception e) {
            System.out.println("Invalid JWT: " + e.getMessage());
        }

        // ✅ Continue without authentication
        filterChain.doFilter(request, response);
    }
}
