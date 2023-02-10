package com.korit.library.config;

import com.korit.library.security.PrincipalOAuth2DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOAuth2DetailService principalOAuth2DetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        http.authorizeRequests()
                .antMatchers("/mypage/**", "/security/**")
                .authenticated()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")   // ROLE_ADMIN, ROLE_MANAGER
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/account/login") // 로그인 페이지 get요청
                .loginProcessingUrl("/account/login") // 로그인 인증 post 요청
                .failureForwardUrl("/account/login/error")
//                .successForwardUrl("/mypage")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(principalOAuth2DetailService)
                .and()
                .defaultSuccessUrl("/index");
    }
}

