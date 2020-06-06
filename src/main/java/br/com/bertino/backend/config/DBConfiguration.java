package br.com.bertino.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("spring.datasource")
@Getter
@Setter
public class DBConfiguration {

	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	@Profile("dev")
	@Bean
	public String testDatabaseConnection() {
		System.out.println("\n Banco de DEV ativado - H2");
		System.out.println(driverClassName);
		System.out.println(url);
		return "Teste de conexao com banco de dados H2.";
	}
	
	@Profile("prod")
	@Bean
	public String productionDatabaseConnection() {
		System.out.println("\n Banco de Prod ativado - PostgreSQL");
		System.out.println(driverClassName);
		System.out.println(url);
		return "Teste de conexao com banco de dados Postgres.";
	}
	
	@Profile("int")
	@Bean
	public String integrationDatabaseConnection() {
		System.out.println("\n Banco de Int ativado - MySQL");
		System.out.println(driverClassName);
		System.out.println(url);
		return "Teste de conexao com banco de dados MySQL.";
	}
}
