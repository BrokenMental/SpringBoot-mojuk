package cs.club.mojuk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 토큰 저장소 설정 - 쿠키 기반, JavaScript에서 접근 가능하도록 HttpOnly=false
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        tokenRepository.setCookieName("XSRF-TOKEN");
        tokenRepository.setHeaderName("X-XSRF-TOKEN");

        // CSRF 토큰 요청 핸들러 설정
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        // 요청 속성 이름 설정
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(tokenRepository)
                        .csrfTokenRequestHandler(requestHandler)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                        "/", "/csrf-token",
                        "/assets/**", "/css/**", "/js/**", "/img/**",
                        "/history/**",
                        "/talk/**", "/talk/room/**",
                        "/ws/**"
                ).permitAll()
                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/")
                        .permitAll()
                        .defaultSuccessUrl("/", false)
                )
                .logout(logout -> logout
                        .permitAll()
                );

        return http.build();
    }
}