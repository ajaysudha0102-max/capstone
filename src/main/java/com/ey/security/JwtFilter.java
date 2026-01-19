package com.ey.security;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import com.ey.entity.User;

import com.ey.repository.UserRepository;

import jakarta.servlet.*;

import jakarta.servlet.http.*;

@Component

public class JwtFilter extends OncePerRequestFilter {

    @Autowired

    private JwtUtil jwtUtil;

    @Autowired

    private UserRepository userRepo;

    @Override

    protected void doFilterInternal(HttpServletRequest request,

                                    HttpServletResponse response,

                                    FilterChain chain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            String email = jwtUtil.extractEmail(token);

            String role = jwtUtil.extractRole(token);

            User user = userRepo.findByEmail(email).orElse(null);

            if (user != null) {

                UsernamePasswordAuthenticationToken auth =

                        new UsernamePasswordAuthenticationToken(

                                email,

                                null,

                                List.of(new SimpleGrantedAuthority("ROLE_" + role))

                        );

                SecurityContextHolder.getContext().setAuthentication(auth);

            }

        }

        chain.doFilter(request, response);

    }

}
 