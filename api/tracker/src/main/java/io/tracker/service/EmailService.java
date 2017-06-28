package io.tracker.service;

import io.tracker.domain.Alert;

public interface EmailService {

	public void sendEmailAlert(Alert existingAlert);

}
