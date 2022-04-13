package com.example.rentit.userservice.security;

import com.example.rentit.userservice.filter.CustomAuthenticationFilter;
import com.example.rentit.userservice.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Arrays;
import java.util.Collections;

import static com.example.rentit.userservice.domain.RoleName.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 *  Extends WebSecurityConfigurerAdapter to manage the authorizations of the users
 *
 * @author Shimi Sadaka
 * @version 1.0
 * @since 3/8/2022
 */
@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder bCryptPasswordEncoder;

    /**
     * configure how to authenticate the user
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * configure the jwt session
     * configure the authorization rules according the user roles
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**", "/api/registration/**", "/api/token/refresh/**").permitAll();
        /* Vehicle API */
        http.authorizeRequests().antMatchers(GET, "/api/vehicles").hasAnyAuthority(ROLE_ADMIN.name());
        http.authorizeRequests().antMatchers(POST, "/api/vehicle/**").hasAnyAuthority(ROLE_USER.name());
        http.authorizeRequests().antMatchers(GET, "/api/vehicle/**").hasAnyAuthority(ROLE_USER.name());
        /* Post API */
        http.authorizeRequests().antMatchers(GET, "/api/post/posts").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/post/**").hasAnyAuthority(ROLE_USER.name());
        http.authorizeRequests().antMatchers(POST, "/api/post/**").hasAnyAuthority(ROLE_USER.name());
        /* User API */
        http.authorizeRequests().antMatchers(POST, "/api/user/**").hasAnyAuthority(ROLE_ADMIN.name());
        http.authorizeRequests().antMatchers(GET, "/api/users").hasAnyAuthority(ROLE_ADMIN.name()); // ROLE_ADMIN has access to get all users
        http.authorizeRequests().antMatchers(POST, "/api//role/save", "/api/role/addtouser").hasAnyAuthority(ROLE_SUPER_ADMIN.name());
        http.authorizeRequests().anyRequest().authenticated();
        /* add the authorization filter */
        //http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
