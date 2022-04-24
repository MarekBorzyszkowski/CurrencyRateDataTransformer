package dev.marek.currencyratedatatransformer.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("dev.marek.currencyratedatatransformer.controllers"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "USD rate API",
                "API providing USD rates in certain range",
                "1.0",
                "https://github.com/MarekBorzyszkowski/CurrencyRateDataTransformer/blob/main/LICENSE",
                new springfox.documentation.service.Contact("Marek Borzyszkowski", "https://github.com/MarekBorzyszkowski", "marek.borzyszkowski2@gmail.com"),
                "MIT License",
                "https://github.com/MarekBorzyszkowski/CurrencyRateDataTransformer/blob/main/LICENSE",
                Collections.emptyList());
    }
}