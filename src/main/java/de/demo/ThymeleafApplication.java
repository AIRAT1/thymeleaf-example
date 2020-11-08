package de.demo;

import de.demo.domain.Person;
import de.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafApplication.class, args);
	}

	@Autowired
	PersonRepository personRepository;

	@PostConstruct
	public void init() {
		personRepository.save(new Person("Pushkin"));
		personRepository.save(new Person("Lermontov"));
	}
}
