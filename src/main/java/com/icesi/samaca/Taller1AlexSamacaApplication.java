package com.icesi.samaca;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.icesi.samaca.model.person.UserApp;
import com.icesi.samaca.model.person.UserType;
import com.icesi.samaca.repositories.UserRepository;


@SpringBootApplication
@ComponentScan("com.icesi.samaca")
public class Taller1AlexSamacaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Taller1AlexSamacaApplication.class, args);
	}

	@Bean
	public CommandLineRunner clr(UserRepository userRepository) {
		return (args->{
			UserApp userAdmin = new UserApp();
			userAdmin.setUsername("Alexander");
			userAdmin.setPassword("{noop}Alexander");
			userAdmin.setType(UserType.admin);
			
			userRepository.save(userAdmin);
			
			UserApp userOperator= new UserApp();
			userOperator.setUsername("Operator");
			userOperator.setPassword("{noop}Operator123");
			userOperator.setType(UserType.operator);
			
			userRepository.save(userOperator);
		});
	}




}
