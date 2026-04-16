package me.mateusferreira.confirmed_seat_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Confirmed Seat API")
                        .description("""
                                API RESTful para o sistema Confirmed Seat (CS-API), desenvolvida para o gerenciamento 
                                de reservas e ocupação de assentos em eventos.
                                
                                A plataforma oferece endpoints para controle de usuários, disponibilidade de lugares 
                                e fluxos de confirmação em tempo real.
                                """)
                        .version("1.0.0")
                        .contact(new Contact().name("Mateus Ferreira").url("https://mateus-fc.vercel.app/").email("mateusferreirac53@gmail.com"))
                ).servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor de Desenvolvimento"))
                );
    }

}
