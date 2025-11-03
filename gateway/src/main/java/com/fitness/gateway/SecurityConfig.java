package com.fitness.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity  // this tells our Config file that we are going to provide security configurations
public class SecurityConfig {

    // //SecurityWebFilterChain will define the security rules that our application will follow
    // and it will be applied by all apis that are processed by api-gateway to other services
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchange -> exchange
//                        .pathMatchers("/actuator/*").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();

    }




}
