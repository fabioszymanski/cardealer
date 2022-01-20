package com.car.dealer;

import java.util.function.Predicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.car.dealer.respository.CarRepository;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class, 
			MongoDataAutoConfiguration.class })

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CarRepository getCarRepository() {
		return new CarRepository();
	}

	@Bean
	 public Docket api() {
	     return new Docket(DocumentationType.SWAGGER_2)
	         .select()
	         .apis(RequestHandlerSelectors.any())
	         .paths(PathSelectors.any())
	         .paths(Predicate.not(PathSelectors.regex("/error.*")))
	         .build();
	 }
}