//package com.ssafy.enjoytrip_springboot.common.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@EnableWebSecurity
//public class WebSecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        return http
//                .httpBasic().disable()
//                .csrf().disable()
//                .cors().and()
//
//                .authorizeRequests()
//                .antMatchers("/member/login", "/member/join").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/board/**", "/reply/**", "/plan/**").authenticated()
//                .antMatchers("/**").authenticated()
//                .and()
//
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
////                .addFilterBefore(new JwtFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
//}
