package br.com.courart.aplicacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AplicacaoApplication {

	public static void main(String[] args) {
		//System.setProperty("server.contextPath", "/courart-api");
		SpringApplication.run(AplicacaoApplication.class, args);
	}

}
