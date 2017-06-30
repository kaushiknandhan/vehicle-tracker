package io.tracker.service;

import java.util.List;

import io.tracker.domain.Alert;
import io.tracker.domain.HighAlerts;
import io.tracker.exception.NoPriorityFound;
/**
 * This class is used to write all the business logic required to get the Alert data
 * of vehicles from the database based on the parameters and filters.
 * 
 * @author kaushik nandhan
 *
 */
public interface AlertService {

	/**
	 * This method is used to get a vehicle alerts based on the priority selected HIGH,Low,MEDIUM
	 * @return List<Alert>
	 * @throws  NoPriorityFound
	 * 
	 */
	public List<Alert> getVehicleAlerts(String vin, String type);

	/**
	 * Gets High Alerts for the last 2 hours of all the vehicles in descending order of 
	 * number of High Alerts
	 * @return List<HighAlerts>
	 */
	public List<HighAlerts> getHighAlertDetails();

	/**
	 * Gets the number of Alerts of a vehicle based on priority  
	 * @param vin
	 * @param type priorty HIGH/Low/MEDIUM
	 * @return int
	 * @throws NoPriorityFound
	 */
	public long totalAlertsByVin(String vin, String type);

}
