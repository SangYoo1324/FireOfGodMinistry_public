package com.example.TINFO370Project.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.TINFO370Project.entity.Users;
import com.example.TINFO370Project.principal.PrincipalDetails;
import com.example.TINFO370Project.repository.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

//시큐리티가 filter를 가지고있는데 BasicAuthenticationFilter라는 것이 있음
//Authentication이나 인증이 필요한 특정 주소를 요청했을 때, 이 필터를 무조건 타게 되어있음
//만약에 Authentication 인증이 필요한 주소가 아니라면 이필터를 안타요(just authenticated, no role authorization)
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UsersRepository usersRepository;
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UsersRepository usersRepository) {
        super(authenticationManager);
        this.usersRepository = usersRepository;
    }

    //인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 타게 됨
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws IOException, ServletException {
//        super.doFilterInternal(request, response, filterChain); 이거 꼭 지워야함 응답 두번해버려서 오류남
        log.info("인증이나 권한이 필요한 주소 요청이 됨");

        String jwtHeader = request.getHeader("Authorization");
        log.info("jwtHeader::::::::::::::::::::::{}",jwtHeader);

        //header가 있는지 확인
        if(jwtHeader== null || !jwtHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        //JWT 토큰을 검증을 해서 정상적인 사용자인지 확인
        String jwtToken = request.getHeader("Authorization").replace("Bearer ","");// Bearer 접두 제거
        // 토큰 검증 & 해독 해서 username부분 추출
        String username =
                JWT.require(Algorithm.HMAC512("cos")).build().verify(jwtToken).getClaim("username").asString();

        // 제대로 검증이 되서 username 추출이 되면
        if(username != null){
            Users users = usersRepository.findByUsername(username).orElse(null);
            PrincipalDetails principalDetails = new PrincipalDetails(users);

            //임의로 authentication 객체 생성
            Authentication authentication
                    = new UsernamePasswordAuthenticationToken(principalDetails,null,principalDetails.getAuthorities());
            //강제로 security 세션에 접근하여 Authentication 객체를 저장(즉, 완전한 Authentication state)
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("security 세션(SecourityContext)에 강제로 authentication 주입. 완벽한 authenticated 상태==================================");
            log.info("현재 접속 유저::::: {}, {}",authentication.getName(),authentication.getAuthorities().toString());
            filterChain.doFilter(request,response);
        }

    }


}
