package com.example.adminpanel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Isključuje CSRF zaštitu za testiranje, ali uključite u produkciji
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll() // Omogućava pristup ovoj ruti bez autentifikacije
                        .anyRequest().authenticated() // Zahteva autentifikaciju za sve ostale zahteve
                )
                .httpBasic(Customizer.withDefaults()); // Koristi Basic Auth sa podrazumevanim podešavanjima


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Enkodiranje lozinki
    }
}
