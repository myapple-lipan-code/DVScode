package com.ljs.callray.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author LiJinSong
 * @date 2021-01-28
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ljs.callray.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("LiJinSong","","");
        return new ApiInfoBuilder()
                .title("用户API")
                .contact(contact)
                .version("1.0")
                .description("API-接口文档")
                .build();
    }
}
