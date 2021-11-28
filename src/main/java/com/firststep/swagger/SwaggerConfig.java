package com.firststep.swagger;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

//import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Configurations for swagger.
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return regex("/api/.*");
    }

}
