//package com.example.TINFO370Project.config;
//
//
//import com.example.TINFO370Project.repository.UsersRepository;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//
//@Slf4j
//@RequiredArgsConstructor
//public class LoginSucessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    private final JwtService jwtService;
//    private final UsersRepository usersRepository;
//
//    @Value("${jwt.access.expiration}")
//    private String accessTokenExpiration;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication){
//        String username = extractUsername(authentication);
//        String accessToken = jwtService.createAccessToken(username);
//        String refreshToken = jwtService.createRefreshToken();
//
//        jwtService.sendAccessAndRefreshToken(response,accessToken, refreshToken); // response에 accesstoken과 refreshToken 정보를 넣는다
//
//        usersRepository.findByUsername(username)
//                .ifPresent(users->{
//                    users.updateRefreshToken(refreshToken);  //refreshToken DB에 집어넣어줌
//                    usersRepository.saveAndFlush(users);
//                });
//        log.info("로그인에 성공하였습니다. username : {}", username);
//        log.info("로그인에 성공하였습니다. AccessToken : {}", accessToken);
//        log.info("발급된 AccessToken 만료 기간 : {}", accessTokenExpiration);
//    }
//
//    private String extractUsername(Authentication authentication){
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        return userDetails.getUsername();
//    }
//
//}
