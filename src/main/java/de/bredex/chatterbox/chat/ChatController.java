package de.bredex.chatterbox.chat;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@Validated
public class ChatController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

//	private RoomRepository roomRepository;
//
//	private ChatRepository chatRepository;
//
//	public ChatController(@Autowired RoomRepository roomRepository, @Autowired ChatRepository chatRepository) {
//		super();
//		this.roomRepository = roomRepository;
//		this.chatRepository = chatRepository;
//	}

	private ChatService chatService;

	public ChatController(@Autowired ChatService chatService) {
		super();
		this.chatService = chatService;
	}

	@Operation(summary = "shows all available chat rooms")
	@ApiResponse(description = "A list of strings of which each entry is the name of a chat room."
			+ " These names get be used to send messages to a room and to retrieve all messages of a room.")
	@GetMapping("/rooms")
	public List<String> getRooms() {
		return chatService.getRooms();
	}

	@PostMapping("/rooms/{room}")
	public ResponseEntity<?> sendMsg(@PathVariable @ValidRoom String room, @RequestBody String msg,
			@RequestParam String from) throws Exception {
		LOGGER.info("Message received: {}", msg);
//		return chatService.sendMessage(room, msg);
//		Chat chat = new Chat(room, LocalDateTime.now(), from);
//		chat.setMessage(msg);
		return ResponseEntity.status(HttpStatus.CREATED).body(chatService.newMessage(room, from, msg));
	}
	// Backup-Live-Coding: @ValidRoom
	@GetMapping("/rooms/{room}/messages")
	public List<Chat> getMessages(@PathVariable @ValidRoom String room) {
		LOGGER.info("Messages of room {} have been requested", room);
		return chatService.getMessages(room);
	}


	// Backup-Live-Coding: Exception-Handling
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public String handleValidationExceptions(ConstraintViolationException ex) {
		return ex.getMessage();
	}

}
