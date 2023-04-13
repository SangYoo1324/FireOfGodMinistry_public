package com.example.TINFO370Project.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Slf4j
@Data
public class CustomUserDetails implements UserDetails, OAuth2User {

    private RegUsers regUsers;

    private Map<String, Object> attributes;

    //General Login
    public CustomUserDetails(RegUsers regUsers) {
        this.regUsers = regUsers;
    }

    //OAuth Login
    public CustomUserDetails(RegUsers users, Map<String, Object> attributes){

        this.regUsers = users;
        this.attributes= attributes;
    }


//    @Override
//    public <A> A getAttribute(String name) {
//        return OAuth2User.super.getAttribute(name);
//    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        log.info("GrantedAuthority Roles::::::::::::::::::"+ regUsers.getRoleList().toString());
        // Role SimpleGrantedAuthority로 변환하여 넘겨줌
        authorities.add(new SimpleGrantedAuthority(regUsers.getRoles().toString()));

//        authorities.add((GrantedAuthority) users.getRoleList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return regUsers.getPassword();
    }

    @Override
    public String getUsername() {
        return regUsers.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
