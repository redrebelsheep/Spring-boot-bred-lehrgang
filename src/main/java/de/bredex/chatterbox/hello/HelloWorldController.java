package de.bredex.chatterbox.hello;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@PostMapping("/mail")
	public Mail send(@RequestBody Mail mail) {
		return mail;
	}

//	@PostMapping("/mail")
//	public String send(@RequestBody String msg) {
//		return msg;
//	}

	@Autowired
	private HelloWorldProperties helloWorldProperties;

//	@Value("${schlubbablubba}")
//	private String greeting;
//	
	@GetMapping("/huhu")
	public String greet() {
		return helloWorldProperties.getGreeting() + " Die Superzahl ist " + helloWorldProperties.getSuperZahl();
	}

	@GetMapping({ "/hello", "/hello/{name}" })
	public String hello(@PathVariable Optional<String> name, @RequestParam Optional<String> greeting) {
		return String.format("%s %s", greeting.orElseGet(() -> "Hello"), name.orElseGet(() -> "World"));
	}

//	@GetMapping({ "/hello", "/hello/{name}" })
//	public String hello(@PathVariable Optional<String> name) {
//		return String.format("Hello %s", name.orElseGet(() -> "World"));
//	}

//	@GetMapping("/hello")
//	public String hello() {
//		return "Hello World";
//	}

}
