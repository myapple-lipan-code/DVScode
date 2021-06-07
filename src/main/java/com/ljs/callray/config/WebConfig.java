package com.ljs.callray.config;

import com.ljs.callray.filter.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LiJinSong
 * @date 2021-01-28
 */
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api/**")
                .excludePathPatterns("/user/login");
    }
}
