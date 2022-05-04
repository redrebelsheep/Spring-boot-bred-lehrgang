package de.bredex.chatterbox.chat;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Backup-Live-Coding
@Service
public class ChatService {

	private RoomRepository roomRepository;

	private ChatRepository chatRepository;

	public ChatService(@Autowired RoomRepository roomRepository, @Autowired ChatRepository chatRepository) {
		super();
		this.roomRepository = roomRepository;
		this.chatRepository = chatRepository;
	}

	public List<String> getRooms() {
		return roomRepository.findAll();
	}

	public Chat newMessage(String room, String from, String msg) {
		Chat chat = new Chat(room, Instant.now(), from);
		chat.setMessage(msg);
		return chatRepository.save(chat);
	}

	public List<Chat> getMessages(String room) {
		return chatRepository.findByRoom(room);
	}
	
}
