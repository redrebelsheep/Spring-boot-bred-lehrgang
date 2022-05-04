package de.bredex.chatterbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import de.bredex.chatterbox.hello.HelloWorldProperties;

@SpringBootApplication
@EnableConfigurationProperties(HelloWorldProperties.class)
//@EnableScheduling // für ChatterboxBot
public class ChatterboxApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ChatterboxApplication.class, args);
	}
	
}
