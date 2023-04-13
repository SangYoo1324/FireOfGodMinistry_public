package com.example.TINFO370Project.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //토큰: cos
            System.out.println("필터1");
            String headerAuth = req.getHeader("Authorization");
            log.info(headerAuth);

            if(headerAuth.equals("cos")){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                log.info("인증 안됨");
            }



//        filterChain.doFilter(servletRequest,servletResponse);
    }
}
