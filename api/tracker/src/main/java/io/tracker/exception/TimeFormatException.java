package io.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class TimeFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TimeFormatException(String message) {
		super(message);
	}
}