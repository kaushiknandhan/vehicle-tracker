package io.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * A Runtime Exception class used when no vehicle exists with the given VIN number 
 * @author kaushik nandhan
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class VehicleNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VehicleNotFound(String message) {
		super(message);
	}

}
