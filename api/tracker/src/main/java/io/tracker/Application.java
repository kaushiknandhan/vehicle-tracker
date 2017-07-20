package io.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
/**
 * This class is a Spring Boot Application laucher for Vehicle Tracker IOT Project
 * @author kaushik nandhan
 *
 */
@SpringBootApplication
@EnableAsync
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
