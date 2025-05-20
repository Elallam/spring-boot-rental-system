package com.wahoweb.rental.car.config;

import com.wahoweb.rental.car.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.cors(Customizer.withDefaults());
        http

                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register", "/api/v1/auth/authenticate")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/cars", "/car", "api/v1/auth/get-users",
                                "/car/registration/{registrationNo}",
                                "/car/searchBy/**",
                                "/car/searchByName/**",
                                "/car/searchByModel/**",
                                "/car/searchByKeyWord/**",
                                "/car/addToFav",
                                "/car/deleteFav"
                                ).permitAll()
                        .requestMatchers(HttpMethod.PUT, "/car").permitAll()
                        .requestMatchers(HttpMethod.POST, "/car").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/car/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/booking").permitAll()
                        .requestMatchers(HttpMethod.GET, "/booking/**", "/api/bookings/**", "/api/bookings").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/email").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/booking/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/review", "/review/**", "/api/reviews/**", "/api/reviews").permitAll()
                        .requestMatchers(HttpMethod.POST, "/review/add").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/review/edit").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/review/**").permitAll()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
    }
}
