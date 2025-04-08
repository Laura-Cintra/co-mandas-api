package br.com.fiap.co_mandas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Co-mandas API",
				version = "v1",
				description = "API para gerenciamento de comandas em restaurantes, com controle de pedidos, pratos e usuários (administradores, gerentes e chefs)."
		)
)
@EnableCaching
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
