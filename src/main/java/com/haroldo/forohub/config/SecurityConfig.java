package com.haroldo.forohub.config;

import com.haroldo.forohub.service.impl.UserDetailsServiceImpl;
import com.haroldo.forohub.config.filter.JwtTokenValidator;
import com.haroldo.forohub.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtUtils jwtUtils;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();


                    http.requestMatchers(HttpMethod.GET, "/topics").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/topics/{id}").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.POST, "/topics").hasAuthority("CREATE");
                    http.requestMatchers(HttpMethod.PUT, "/topics/{id}").hasAuthority("UPDATE");
                    http.requestMatchers(HttpMethod.DELETE, "/topics/{id}").hasAuthority("DELETE");

                    http.requestMatchers(HttpMethod.GET, "/responses").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/responses/{id}").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.POST, "/responses").hasAuthority("CREATE");
                    http.requestMatchers(HttpMethod.PUT, "/responses/{id}").hasAuthority("UPDATE");
                    http.requestMatchers(HttpMethod.DELETE, "/responses/{id}").hasAuthority("DELETE");


                    http.requestMatchers(HttpMethod.GET, "/users").hasAuthority("READ");

                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
