package com.datdudu.todolist;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Todo-List Java - Nuven",
				version = "1.0.0",
				description = "Todo-List Simples para o teste técnico, vaga backend developer no laboratório Nuven, utilizando Spring Boot, Lombock,H2 e JPA"
		)
)
public class ToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}

}
