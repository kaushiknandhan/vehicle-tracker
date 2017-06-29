package io.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * A Runtime Exception class used when priority of unknown type is used
 * @author kaushik nandhan
 *
 */
@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class NoPriorityFound extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoPriorityFound(String message) {
		super(message);
	}

}
