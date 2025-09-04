package com.pyoh07.simpleboard.config;

import com.pyoh07.simpleboard.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final MemberService memberService;

    public SecurityConfig(MemberService memberService) {
        this.memberService = memberService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(memberService); // MemberService에서 UserDetailsService 구현 예정
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF 비활성 (개발용)
            .csrf(csrf -> csrf.disable())
            // URL 접근 권한 설정
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/members/**", "/h2-console/**").permitAll()
                    .requestMatchers("/boards/**").authenticated()
                    .anyRequest().authenticated()
            )
            // 폼 로그인 설정
            .formLogin(form -> form
                    .loginPage("/members/login")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
            )
            // 로그아웃 설정
            .logout(logout -> logout
                    .logoutUrl("/members/logout")
                    .logoutSuccessUrl("/")
                    .permitAll()
            );
        // H2 콘솔 접근 허용
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}
