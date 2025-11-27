package com.erp.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/", "/login", "/oauth2/**", "/error").permitAll()
//                .requestMatchers("/api/fees/**").hasRole("ACCOUNTS")
//                .anyRequest().authenticated()
//            )
//            .oauth2Login(oauth2 -> oauth2
//                .loginPage("/login")
//                .defaultSuccessUrl("/api/fees")
//            )
//            .logout(logout -> logout
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/")
//                .deleteCookies("JSESSIONID")
//                .invalidateHttpSession(true)
//            )
            .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }
}
