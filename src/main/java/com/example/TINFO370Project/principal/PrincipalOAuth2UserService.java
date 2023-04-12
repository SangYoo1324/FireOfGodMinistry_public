package com.example.TINFO370Project.principal;

import com.example.TINFO370Project.entity.Role;
import com.example.TINFO370Project.entity.Users;
import com.example.TINFO370Project.principal.PrincipalDetails;
import com.example.TINFO370Project.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {
        private final UsersRepository usersRepository;

        // UserRequest from google will be handled

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        log.info("from PrincipalOauth2UserService==> userRequest: "+ userRequest.getClientRegistration());
        log.info("from PrincipalOauth2UserService==> AccessToken: "+ userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("from PrincipalOauth2UserService==> getAttributes: "+oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getClientId(); // Google
        String providerId = oAuth2User.getName();
        String email = oAuth2User.getAttribute("email");
        String userame = oAuth2User.getAttribute("name");

        Role role = Role.ROLE_USER;

        Users usersEntity = usersRepository.findByUsername(userame).orElse(null);

        if(usersEntity == null){
            usersEntity= new Users(null,userame,"GOOGLE_Login",role,null,email,provider,providerId);


            usersRepository.save(usersEntity);

            log.info(email+" has been successfully stored on users repository");
        }else{
            log.info("Confirm right username Entered*************"+usersEntity.getUsername());
            log.info("This google Id is already registered no action needed");
        }

        return new PrincipalDetails(usersEntity,oAuth2User.getAttributes());
    }
}
