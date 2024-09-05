package cs.club.mojuk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 스프링 설정 클래스로 지정
@EnableWebSecurity  // 웹 보안을 활성화
public class SecurityConfig {

    /*@Autowired
    private CustomUserDetailsService userDetailsService;  // CustomUserDetailsService 주입
    */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 비밀번호를 암호화하기 위한 BCryptPasswordEncoder 빈 등록
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                //접근 허용
                .requestMatchers(
                        // "/", 페이지 이동 접근
                        "/history",

                        // "/css/**", "/js/**" 정적 접근
                        "/assets/**", "/css/**", "/js/**", "img/**"
                ).permitAll()
                // 나머지 요청은 인증된 사용자만 접근 가능
                .anyRequest().authenticated()
        )
        .formLogin(form -> form
                .loginPage("/")  // 커스텀 로그인 페이지 경로 설정
                .permitAll()  // 모든 사용자에게 로그인 페이지 접근 허용
                .defaultSuccessUrl("/", true)  // 로그인 성공 시 리다이렉트할 경로 설정
        )
        .logout(logout -> logout
                .permitAll()  // 모든 사용자에게 로그아웃 허용
        );

        return http.build();  // 보안 설정을 적용한 SecurityFilterChain 반환
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // CustomUserDetailsService와 passwordEncoder를 사용해 인증 관리자를 설정
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }*/
}
