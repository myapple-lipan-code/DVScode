package com.ljs.callray.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author LiJinSong
 * @date 2021-01-28
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration cors = new CorsConfiguration();
        //允许源，这里允许所有源访问，实际应用会加以限制
        cors.addAllowedOrigin("*");
        //允许所有请求头
        cors.addAllowedHeader("*");
        //允许所有方法
        cors.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return new CorsFilter(source);
    }
}
