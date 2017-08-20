package com.nowcoder.config;

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
 * Created by CycloneBoy on 2017/7/16.
 */
@Configuration
@EnableSwagger2
class Swagger2Configuration {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nowcoder.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Spring Boot 中使用Swagger2构建RESTful APIs")
                .description("学习Sping Boot例子， 我的Github: https://github.com/CycloneBoy")
                .termsOfServiceUrl("https://github.com/CycloneBoy")
                .contact(new Contact("CycloneBoy","https://github.com/CycloneBoy","xuanfeng1992@gmail.com"))
                .version("1.0")
                .build();
    }
}
