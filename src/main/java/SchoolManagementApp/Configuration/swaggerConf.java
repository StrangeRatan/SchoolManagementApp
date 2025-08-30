package SchoolManagementApp.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class swaggerConf {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info().title("STUDENT-MANAGEMENT-APP")
                .description("DEVELOPED BY RATAN").version("1.0"))
                .tags(Arrays.asList(
                        new Tag().name("Public APIs").description("APIs accessible without authentication"),
                        new Tag().name("Student-APIs").description("Student-related operations"),
                        new Tag().name("Admin APIs").description("Admin-only operations")
                ))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes(
                        "bearerAuth",new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                ));

    }
}
