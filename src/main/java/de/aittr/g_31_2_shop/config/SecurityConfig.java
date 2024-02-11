package de.aittr.g_31_2_shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        x -> x
                                .requestMatchers(HttpMethod.POST, "/customers").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/customers").hasAnyRole("CUSTOMER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/customers/{id}").hasAnyRole("CUSTOMER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/products").permitAll()
                                .requestMatchers(HttpMethod.GET, "/products/{id}").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/products").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/products/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/products/del_by_name/{name}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/products/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/products/count").hasAnyRole("CUSTOMER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/products/totalPrice").hasAnyRole("CUSTOMER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/products/averagePrice").hasAnyRole("CUSTOMER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
                                .anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
