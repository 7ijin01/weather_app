package gijin.weather_app.config;

import gijin.weather_app.jwt.JWTFilter;
import gijin.weather_app.jwt.JWTUtil;
import gijin.weather_app.jwt.LoginFilter;
import gijin.weather_app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig
{
    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                //.cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 추가
                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/", "/api/auth/signIn", "/api/auth/signup").permitAll()
//                        .requestMatchers("/public/**").permitAll()
//                        .anyRequest().authenticated()
                          .anyRequest().permitAll()
                )

                // Disable form login
                // .formLogin(form -> form
                //     .loginPage("/login/oauth2/code/naver")
                //     .defaultSuccessUrl("/", true)
                //     .permitAll()
                // )

//                .logout(logout -> logout
//                        .permitAll()
//                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // Add custom filters
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, memberRepository, "/api/auth/signIn"), UsernamePasswordAuthenticationFilter.class);
        //http.addFilterAt(new LogoutFilter(authenticationManager(authenticationConfiguration), jwtUtil, memberRepository, "/signIn"), UsernamePasswordAuthenticationFilter.class);
        //http.addFilterBefore(new CustomLogoutFilter(jwtUtil, memberRepository), LogoutFilter.class);
        http.addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
