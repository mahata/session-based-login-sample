package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                .requestMatchers("/api/login", "/api/logout").permitAll()
                .anyRequest().authenticated()
            }
            .formLogin {
                it.disable()
            }
            .httpBasic {
                it.disable()
            }

        return http.build()
    }
}
