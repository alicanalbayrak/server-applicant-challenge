package com.mytaxi.application.config;

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
 * Spring configuration for Swagger via SpringFox.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{

    private static final String API_BASE_PACKAGE = "com.mytaxi.application";

    @Bean
    public Docket docket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(API_BASE_PACKAGE))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(generateApiInfo());
    }


    private ApiInfo generateApiInfo()
    {
        return new ApiInfoBuilder()
            .title("mytaxi Server Applicant Test Service")
            .description("This service is to check the technology knowledge of a server applicant for mytaxi.")
            .version("Version 1.0 - mw")
            .contact(new Contact("Some Developer", "mytaxi.com", "career@mytaxi.com"))
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
            .build();
    }
}
