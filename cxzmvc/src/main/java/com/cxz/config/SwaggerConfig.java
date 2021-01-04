package com.cxz.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/1/4 21:44
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig{
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()            .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("监管平台-国际版API")
                .description("http不对外开放接口")
                .version("1.0.0")
                .termsOfServiceUrl("http://xxx.xxx.com")
                .license("假装这里有license")
                .licenseUrl("http://xxx.xxx.com")
                .build();
    }

}