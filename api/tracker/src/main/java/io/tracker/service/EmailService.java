package io.tracker.service;

import io.tracker.domain.Alert;
/**
 * This class is used to send Email regarding HIGH Alerts to a User
 * @author kaushik nandhan
 *
 */
public interface EmailService {

	public void sendEmailAlert(Alert existingAlert);

}
