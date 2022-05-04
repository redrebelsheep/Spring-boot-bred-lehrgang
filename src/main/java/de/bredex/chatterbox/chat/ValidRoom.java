package de.bredex.chatterbox.chat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = RoomValidator.class)
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRoom {
	String message() default "Diesen Raum gibt es nicht.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
