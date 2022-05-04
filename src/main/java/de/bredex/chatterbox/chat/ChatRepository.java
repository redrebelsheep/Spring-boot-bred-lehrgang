package de.bredex.chatterbox.chat;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Chat>{

	List<Chat> findByRoom(String room);
	
}
