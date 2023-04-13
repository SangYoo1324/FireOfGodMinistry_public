package com.example.TINFO370Project.service;

import com.example.TINFO370Project.entity.CustomUserDetails;
import com.example.TINFO370Project.entity.enums.Role;
import com.example.TINFO370Project.entity.RegUsers;
import com.example.TINFO370Project.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
        private final UsersRepository usersRepository;
        private final JwtTokenProviderService jwtTokenProviderService;
        // UserRequest from google will be handled

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        log.info("from PrincipalOauth2UserService==> userRequest: "+ userRequest.getClientRegistration());
        log.info("from PrincipalOauth2UserService==> AccessToken: "+ userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("from PrincipalOauth2UserService==> getAttributes: "+oAuth2User.getAttributes());

        String provider = "GOOGLE_"+userRequest.getClientRegistration().getClientId(); // Google
        String providerId = oAuth2User.getName();
        String email = oAuth2User.getAttribute("email");
        String username = oAuth2User.getAttribute("name");

        Role role = Role.ROLE_USER;


        RegUsers regUsersEntity = (RegUsers) usersRepository.findByUsername(username);//.orElse(null);


        if(regUsersEntity == null){
            regUsersEntity = new RegUsers(null,email,Timestamp.valueOf(LocalDateTime.now()),username,"GOOGLE_Login_PW",role,null
                    ,provider,providerId);
            //jwt Token creation & implementation
            regUsersEntity.setJwtToken(jwtTokenProviderService.jwtTokenCreator(regUsersEntity.getUsername(), regUsersEntity.getId()));
            usersRepository.save(regUsersEntity);

            usersRepository.save(regUsersEntity);

            log.info(email+" has been successfully stored on users repository");
        }else{
            //jwt Token creation & implementation
            regUsersEntity.setJwtToken(jwtTokenProviderService.jwtTokenCreator(regUsersEntity.getUsername(), regUsersEntity.getId()));
            usersRepository.save(regUsersEntity);
            log.info("Confirm right username Entered*************"+ regUsersEntity.getUsername());
            log.info("This google Id is already registered no action needed");
        }

        return new CustomUserDetails(regUsersEntity,oAuth2User.getAttributes());
    }
}
