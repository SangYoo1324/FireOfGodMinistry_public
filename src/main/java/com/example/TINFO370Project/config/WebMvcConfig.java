package com.example.TINFO370Project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

//    @Bean
//    public CorsFilter corsFilter(){
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        // 내 서버가 응답할때 json 을 js에서 처리할수 잇게 할지 설정
//        config.setAllowCredentials(true);
//        config.addAllowedOriginPattern("*"); // 모든 ip에 응답 허용
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*"); // Get,Post, Delete, Patch etc
//        source.registerCorsConfiguration("/api/**",config);
//        return new CorsFilter(source);
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("")
//                .allowedOrigins("http://localhost:3000")
//                .allowedMethods("OPTIONS","GET","POST","PATCH","DELETE");
//    }


    //    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry){
//        MustacheViewResolver resolver = new MustacheViewResolver();
//        resolver.setCharset("UTF-8");
//        resolver.setContentType("text/html; charset=UTF-8");
//        resolver.setPrefix("classpath:/templates/");
//        resolver.setSuffix(".html");
//
//        registry.viewResolver(resolver);
//    }
}
