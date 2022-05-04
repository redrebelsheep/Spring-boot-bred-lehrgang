package de.bredex.chatterbox.chat;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ChatterboxBot {

	private ChatService chatService;
	
	public ChatterboxBot(ChatService chatService) {
		this.chatService = chatService;
	}
	
	@Scheduled(fixedDelay = 10000)
	public void sayHello() {
		chatService.newMessage("Chatterbox", "ChatterboxBot", "Hello");
	}
	
}
