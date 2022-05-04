package de.bredex.chatterbox.chat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class ChatControllerTest {

	@MockBean
	private ChatService chatService;

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private RoomRepository roomRepository;
	
	@Test
	void testGetRooms() throws Exception {
		List<String> rooms = Arrays.asList("rot", "blau", "gelb");
		given(chatService.getRooms()).willReturn(rooms);
		mvc.perform(get("/rooms"))
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("blau")));
	}

	@Test
	@Disabled
	void testSendMsg() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetMessages() throws Exception {
		Chat msg = new Chat("Java", Instant.now(), "Ich");
		msg.setMessage("Message 1");
		given(chatService.getMessages("Java")).willReturn(Arrays.asList(msg));
		given(roomRepository.contains(anyString())).willReturn(true);
		mvc.perform(get("/rooms/Java/messages"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$..message").value("Message 1"));
		then(chatService).should(times(1)).getMessages(anyString());
	}
	
	@Test
	void testGetMessageOfRoomWhichDoesntExistReturnBadRequest() throws Exception {
		given(roomRepository.contains(anyString())).willReturn(false);
		mvc.perform(get("/rooms/Java/messages"))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("getMessages.room: Diesen Raum gibt es nicht."));
		then(chatService).shouldHaveNoInteractions();
	}
	
	// Backup-Live-Coding: Exception-Handling
	@Test
	@Disabled
	void testGetMessagesByRoomWithPathThatDoesntExistThrowsInternalServerError() throws Exception {
		final String path = "/rooms/jadsfhkasghasdghukhugk/messages";
		given(roomRepository.contains(anyString())).willReturn(false);
		assertThatThrownBy(() -> mvc.perform(get(path)))
			.hasCauseExactlyInstanceOf(ConstraintViolationException.class)
			.hasMessageContaining("Diesen Raum gibt es nicht");
	}

}
