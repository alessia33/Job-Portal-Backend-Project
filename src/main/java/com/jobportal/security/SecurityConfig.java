package com.jobportal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/api/resumes/**").authenticated()
                        .requestMatchers("/api/auth/register").permitAll()
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/reviews").hasRole("EMPLOYER")
                        .requestMatchers("/api/reviews").hasRole("EMPLOYER")
                        .requestMatchers("/api/jobs/**").hasRole("EMPLOYER")
                        .requestMatchers("/api/applications/{applicationId}/status").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.POST,"/api/applications").hasRole("JOB_SEEKER")
                        .requestMatchers(HttpMethod.POST,"/api/resumes/upload").hasRole("JOB_SEEKER")
                        .requestMatchers("/api/applications/job/**").hasAnyRole("EMPLOYER", "ADMIN")
                        .requestMatchers("/api/applications/user/**").hasAnyRole("EMPLOYER", "ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
