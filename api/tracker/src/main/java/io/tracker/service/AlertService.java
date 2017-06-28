package io.tracker.service;

import java.util.List;

import io.tracker.domain.Alert;
import io.tracker.domain.HighAlerts;

public interface AlertService {

	public List<Alert> getVehicleAlerts(String vin, String type);

	public List<HighAlerts> getHighAlertDetails();

	public int totalAlertsByVin(String vin, String type);

}
