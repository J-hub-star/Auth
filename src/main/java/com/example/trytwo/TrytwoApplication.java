package com.example.trytwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class TrytwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrytwoApplication.class, args);
	}

}
