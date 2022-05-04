package de.bredex.chatterbox.chat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoomValidatorTest {
	
	@Mock
	private RoomRepository roomRepository;

	@Test
	void testIsValidReturnsFalseIfRoomDoesntExist() {
		final String roomDoesntExist = "roomDoesntExist";
		when(roomRepository.contains(roomDoesntExist)).thenReturn(false);
		RoomValidator roomValidator = new RoomValidator(roomRepository);
		assertThat(roomValidator.isValid("roomDoesntExist", null)).isFalse();
	}
	
	@Test
	void testIsValidReturnsTrueIfRoomExists() {
		final String roomExists = "roomExists";
		when(roomRepository.contains(roomExists)).thenReturn(true);
		RoomValidator roomValidator = new RoomValidator(roomRepository);
		assertThat(roomValidator.isValid(roomExists, null)).isTrue();
	}

}
