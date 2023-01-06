package br.com.pb.barbershop.msproduct;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "MS Product",
                description = "Microsserviço de produtos da barbearia",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8084/api/barbershop"
        )
)
public class MsProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProductApplication.class, args);
    }
}

