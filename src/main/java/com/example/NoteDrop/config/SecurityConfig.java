package com.example.NoteDrop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/signup", "/api/signup", "/api/login","/css/**", "/js/**", "/uploads/**", "/error")
                        .permitAll()
                ///      correct why /uploads/ is not working perfectly for some files
                        .requestMatchers("/*/home", "/api/notes/save", "/*/profile", "/*/search", "/logout").authenticated()
                        .anyRequest().denyAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/api/login")
                        .successHandler(customSuccessHandler())
                        .permitAll()
                )
                .logout(logout -> logout
//                        .logoutUrl("/logout") // Define the logout URL
                        .logoutSuccessUrl("/login?logout") // Redirect after logout
//                        .invalidateHttpSession(true) // Invalidate session
//                        .deleteCookies("JSESSIONID") // Delete session cookie
                        .permitAll()
                );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return (request, response, authentication) -> {
            String username = authentication.getName();
            response.sendRedirect("/" + username + "/home");
        };
    }
}