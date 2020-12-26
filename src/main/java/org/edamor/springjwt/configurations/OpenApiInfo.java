package org.edamor.springjwt.configurations;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiInfo {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("JWT Demo")
                .description("An API to demonstrate a basic implementation of JWT. \n" +
                        "This JWT was digitally signed with an HS256 algorithm encoded key. \n" +
                        "You can verify your JWT by using the parse-jwt API below. \n" +
                        "The response will include a password hashed using " +
                        "the OpenBSD bcrypt scheme.")
                .version("v1.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation()
                .description("JSON Web Tokens")
                .url("https://jwt.io"))
            .externalDocs(new ExternalDocumentation()
                .description("Author")
                .url("https://edamor.tech"));
    }
}
