package de.bredex.chatterbox.hello;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("chatterbox.hello")
public class HelloWorldProperties {

	private String greeting;
	
	private int superZahl;

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public int getSuperZahl() {
		return superZahl;
	}

	public void setSuperZahl(int superZahl) {
		this.superZahl = superZahl;
	}

}
