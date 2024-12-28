package springboot.webproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import springboot.webproject.service.CustomUserDetailsService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Security Filter Chain 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 경로 접근 권한 설정
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/", "/login", "/users/create", "/css/**", "/js/**", "/images/**").permitAll() // 인증 없이 접근 가능
                        .anyRequest().authenticated() // 나머지는 인증 필요
                )
                // 로그인 설정
                .formLogin((form) -> form
                        .loginPage("/login") // 로그인 폼 경로
                        .loginProcessingUrl("/login") // 로그인 처리 경로 (POST 요청)
                        .defaultSuccessUrl("/") // 로그인 성공 후 이동할 기본 페이지
                        .failureUrl("/login?error=true") // 로그인 실패 시 이동할 경로
                        .permitAll()
                )
                // 로그아웃 설정
                .logout((logout) -> logout
                        .logoutUrl("/logout") // 로그아웃 요청 경로
                        .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동할 경로
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    // AuthenticationManager 설정 (Spring Security 5.7 이상 방식)
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder()) // 비밀번호 암호화 필요
//                .and()
//                .build();
//    }
    // AuthenticationManager 설정 (CustomUserDetailsService 사용)
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService) // CustomUserDetailsService 사용
                .passwordEncoder(passwordEncoder()); // 비밀번호 암호화 설정

        return authenticationManagerBuilder.build();
    }

    // PasswordEncoder 설정 (BCryptPasswordEncoder 사용)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}