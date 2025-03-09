package com.jobportal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF for APIs
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅ No Sessions
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/resumes/**").authenticated() // Allow resume uploads
                        .requestMatchers("/api/auth/register").permitAll() // ✅ Allow Registration Without Authentication
                        .requestMatchers("/api/users/**").hasRole("ADMIN") // ✅ Admins Only
                        .requestMatchers("/api/jobs/**").hasAnyRole("EMPLOYER", "ADMIN") // ✅ Employers & Admins
                        .requestMatchers("/api/applications/**").hasAnyRole("JOB_SEEKER", "EMPLOYER", "ADMIN") // ✅ Job Seekers & Employers
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // ✅ Enable Basic Auth

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // ✅ Required for matching hashed passwords
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
