package com.example.microblog.config;

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
 * SwaggerConfig
 */

 @Configuration
 @EnableSwagger2
public class SwaggerConfig {

    @Bean
    /**
     * Determine where are my APIs
     */
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors
                .basePackage("com.example.microblog.restApi"))
            .paths(PathSelectors.regex("/.*"))
            .build().apiInfo(apiEndPointsInfo());
    }

    /**
     * Swagger Api info
     * @return the info builder for swagger API
     */
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Microblog REST API")
            .description("Microblog Management REST API")
            .contact(new Contact("Allari Edoardo", "www.exampleSite.net", "contactme@gmail.com"))
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("1.0.0")
            .build();
    }
}