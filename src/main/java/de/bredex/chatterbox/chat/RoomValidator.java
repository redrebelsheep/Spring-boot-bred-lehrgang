package de.bredex.chatterbox.chat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class RoomValidator implements ConstraintValidator<ValidRoom, String> {

	private RoomRepository roomRepository;
	
	public RoomValidator(@Autowired RoomRepository roomRepository) {
		super();
		this.roomRepository = roomRepository;
	}

	@Override
	public boolean isValid(String room, ConstraintValidatorContext context) {
		return roomRepository.contains(room);
	}

}
