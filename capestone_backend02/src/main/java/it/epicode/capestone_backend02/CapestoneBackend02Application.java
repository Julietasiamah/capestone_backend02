package it.epicode.capestone_backend02;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "it.epicode.capestone_backend02")
//@EnableJpaRepositories(basePackages = "it.epicode.capestone_backend02.auth")
public class CapestoneBackend02Application {

	public static void main(String[] args) {
		SpringApplication.run(CapestoneBackend02Application.class, args);
	}



}


