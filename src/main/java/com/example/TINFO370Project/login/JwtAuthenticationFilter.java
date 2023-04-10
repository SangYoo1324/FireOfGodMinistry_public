package com.example.TINFO370Project.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.TINFO370Project.entity.Users;
import com.example.TINFO370Project.principal.PrincipalDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("=====================JwtAuthenticationFilter: 로그인 시도중===============================");

        // 1. username,password 받아서
//        try{
//            BufferedReader br = request.getReader();
//            String input = null;
//            while((input = br.readLine()) !=null){
//                log.info(input);
//            }
//        }catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        ObjectMapper om = new ObjectMapper();
        try {
            Users users = om.readValue(request.getInputStream(),Users.class);
            log.info(users.toString());

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword());

            //PrincipalDetailsService에 loadByUsername() 함수가 실행됨
            Authentication authentication
                    = authenticationManager.authenticate(authenticationToken);
            //3. PrincipalDetails를 세션에 담고  (안담으면 권한관리가 안됨)
            PrincipalDetails principalDetails = (PrincipalDetails)  authentication.getPrincipal();
            log.info(principalDetails.getUsers().getUsername()+":::: 세션에 로그인 정보가 담겼습니다");

            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //4. JWT토큰을 만들어서 응답해주면 됨.

    }

    //attemptAuthentication 이후 인증이 정상적으로 되었다면 successfulAuthentication함수가 실행됨. 오버라이딩필요
    //JWT 토큰 처리 로직
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult){
        log.info("successfulAuthentication 실행, 인증성공");
        //현재 세션에서 userdetails 가져옴
        PrincipalDetails principalDetails = (PrincipalDetails)  authResult.getPrincipal();

        // JWT 토큰 생성
        // RSA 방식은 아니구 Hash 암호방식
        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())  // "sub" : "aaaa"
                        .withExpiresAt(new Date(System.currentTimeMillis()+60000*10))
                                .withClaim("id",principalDetails.getUsers().getId())
                                        .withClaim("username",principalDetails.getUsers().getUsername())
                                                .sign(Algorithm.HMAC512("cos"));

       response.addHeader("Authorization","Bearer "+jwtToken);

    }
}
