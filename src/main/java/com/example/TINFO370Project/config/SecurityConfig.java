package com.example.TINFO370Project.config;



import com.example.TINFO370Project.principal.PrincipalOAuth2UserService;
import com.example.TINFO370Project.entity.Role;
import com.example.TINFO370Project.login.CustomAuthProvider;
import com.example.TINFO370Project.principal.PrincipalDetailsService;
import com.example.TINFO370Project.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PrincipalOAuth2UserService principalOauth2UserService;
    public final UsersRepository usersRepository;
    private final ObjectMapper objectMapper;
    private final PrincipalDetailsService principalDetailsService;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// csrf 보안 사용 X
//                .addFilter(new JwtAuthenticationFilter(authenticationManager(),usersRepository))
//                .addFilter(new JwtAuthorizationFilter(authenticationManager(),usersRepository))
//                .and()
//                .addFilter(corsFilter)
                //== URL별 권한 관리 옵션 ==//
                .authorizeHttpRequests(authorize->{
                            try{
                                // 아이콘, css, js 관련
                                // 기본 페이지, css, image, js 하위 폴더에 있는 자료들은 모두 접근 가능, h2-console에 접근 가능
                                authorize                                                                                                  //google login redirectionURI
                                        .requestMatchers("/css/**","/images/**","/js/**","/favicon.ico","/h2-console/**","/page/**","/login/oauth2/code/google",
                                                "/api/join/**","/api/login/**").permitAll()
                                        .requestMatchers("/page/login/**").permitAll() // 회원가입 접근 가능
                                        .requestMatchers("/page/users/**").authenticated()
                                        .requestMatchers("/panel/admin/**").hasRole("ADMIN")
                                        .anyRequest().denyAll()
                                        .and()
                                        //== 소셜 로그인 설정 ==//
                                        .oauth2Login()
                                        .loginPage("/page/login").defaultSuccessUrl("/page/main")
                                        .userInfoEndpoint()
                                        .userService(principalOauth2UserService);
                                ;


                                // 원래 스프링 시큐리티 필터 순서가 LogoutFilter 이후에 로그인 필터 동작
                                // 따라서, LogoutFilter 이후에 우리가 만든 필터 동작하도록 설정
                                // 순서 : LogoutFilter -> JwtAuthenticationProcessingFilter -> CustomJsonUsernamePasswordAuthenticationFilter


                            }catch(Exception e){
                                throw new RuntimeException(e);
                            }
                        }

                );


        return http.build();
    }


    /**
     * AuthenticationManager 설정 후 등록
     * PasswordEncoder를 사용하는 AuthenticationProvider 지정 (PasswordEncoder는 위에서 등록한 PasswordEncoder 사용)
     * FormLogin(기존 스프링 시큐리티 로그인)과 동일하게 DaoAuthenticationProvider 사용
     * UserDetailsService는 커스텀 LoginService로 등록
     * 또한, FormLogin과 동일하게 AuthenticationManager로는 구현체인 ProviderManager 사용(return ProviderManager)
     *
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        CustomAuthProvider provider = new CustomAuthProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        provider.setUserDetailsService(principalDetailsService);
        return new ProviderManager(provider);
    }


}




