package br.com.fiap.co_mandas.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    
    @Autowired
    private AuthFilter authFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(
            auth -> auth
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/dishes/**").hasAuthority("ADMIN")
                // .requestMatchers("/dishes/**").hasRole("ADMIN")
                // .requestMatchers("/cozinha/**").hasAnyRole("CHEFE", "ADMIN")
                .anyRequest().authenticated()
        )
        .csrf(csrf -> csrf.disable())
        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
        .httpBasic(Customizer.withDefaults())
        .build();
    }
    
    // sempre que eu pedir "passwordEncoder" usará o BCrypt
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // gera a senha criptografada
    }

    // informando ao spring de onde ele vai tirar o AuthenticationManager
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}