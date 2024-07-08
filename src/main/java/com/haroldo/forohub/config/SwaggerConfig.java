package com.haroldo.forohub.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "FORO HUB API",
                description = "App works how foro",
                termsOfService = "www.josemina.com",
                version = "0.0.7",
                contact = @Contact(
                        name = "Jose Mina",
                        url = "www.josemina.com",
                        email = "jose@josemina.com"
                ),
                license = @License(
                        name = "Standard Software Use License for Jose",
                        url = "www.josemina.com"
                )
        ),
        servers = {@Server(
                description = "DEV SERVER",
                url = "http://localhost:8080"
        ),
                @Server(
                        description = "PROD SERVER",
                        url = "http://josemina:8080"
                )
        },
        security = @SecurityRequirement(
                name = "Security Token"
        )
)

@SecurityScheme(
        name = "Security Token",
        description = "Access Token For My API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"

)
public class SwaggerConfig {

}
