package com.ey.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.*;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ey.security.JwtFilter;

@Configuration

public class SecurityConfig {

    @Autowired

    private JwtFilter jwtFilter;

    @Bean

    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())

            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth

                .requestMatchers("/api/auth/**").permitAll()
                
                .requestMatchers("/api/bookings/**").hasRole("CUSTOMER")

                .requestMatchers("/api/admin/**,/api/payments/**").hasRole("ADMIN")
                
                .requestMatchers("/api/address/**,/api/rating/**").hasRole("CUSTOMER")


                .requestMatchers("/api/cleaner/**").hasRole("CLEANER")

                .anyRequest().authenticated()

            )

            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

}
 