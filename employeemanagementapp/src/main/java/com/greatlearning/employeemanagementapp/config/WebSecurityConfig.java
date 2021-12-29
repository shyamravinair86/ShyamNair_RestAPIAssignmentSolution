 package com.greatlearning.employeemanagementapp.config;

import com.greatlearning.employeemanagementapp.implementation.UserDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
		        .antMatchers(HttpMethod.POST, "/api/employees/**").hasAuthority("ADMIN")        
		        .antMatchers(HttpMethod.PUT, "/api/employees/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/employees/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/api/employees/**").hasAnyAuthority("ADMIN", "USER")

                .antMatchers(HttpMethod.POST, "/api/roles").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/api/roles/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/api/roles/**").hasAnyAuthority("ADMIN", "USER")

                .antMatchers(HttpMethod.POST, "/api/users").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyAuthority("ADMIN", "USER")

                .anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login").permitAll().and().logout()
                .logoutSuccessUrl("/login").permitAll().and().exceptionHandling().accessDeniedPage("/accessDenied")
                .and().cors().and().csrf().disable();
    }
}
