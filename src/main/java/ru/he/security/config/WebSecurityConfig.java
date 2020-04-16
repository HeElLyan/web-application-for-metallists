//package ru.he.security.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import ru.he.security.filters.TokenAuthFilter;
//
////секьюрити вызывает провайдер
//@ComponentScan("ru.he")
//@EnableWebSecurity
//@Order(1000)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    //проверяет токен и его валидность
//    @Autowired
//    private AuthenticationProvider authenticationProvider;
//
//    //преобразует токен в объект аутентификации и скармливает секьюрити
//    @Autowired
//    private TokenAuthFilter tokenAuthFilter;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
//                .antMatcher("/**")
//                .authenticationProvider(authenticationProvider)
//                .authorizeRequests()
//                .antMatchers("/users/**").hasAuthority("ADMIN")
//                .antMatchers("/signIn").permitAll();
//        http.csrf().disable();
//    }
//}
