package io.tracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.tracker.domain.Alert;
import io.tracker.domain.HighAlerts;
import io.tracker.exception.NoPriorityFound;
import io.tracker.repository.AlertRepository;

/**
 * This class is used to write all the business logic required to get the Alert data
 * of vehicles from the database based on the parameters and filters.
 * 
 * @author kaushik nandhan
 *
 */
@Service
@Transactional
public class AlertServiceImpl implements AlertService {

	@Autowired
	private AlertRepository alertRepository;
	
	/**
	 * This method is used to get a vehicle alerts based on the priority selected HIGH,Low,MEDIUM
	 * @return List<Alert>
	 * @throws  NoPriorityFound
	 * 
	 */
	public List<Alert> getVehicleAlerts(String vin, String type) {
		
		List<Alert> vehicleAlerts =  new ArrayList<>();
		if(type.equals("All")){
			vehicleAlerts = getAlertsByVin(vin);
		}else if(type.equals("Low") || type.equals("MEDIUM") || type.equals("HIGH")){
			vehicleAlerts = getAlertsByVinAndType(vin,type);
		}else{
			throw new NoPriorityFound("No priority of type "+type+ " found");
		}
		return vehicleAlerts;
	}


	/**
	 * Gets a List of Alerts of a vihicle based on priority type
	 * @param vin
	 * @param type
	 * @return List<Alert>
	 */
	private List<Alert> getAlertsByVinAndType(String vin, String type) {
		
		return (List<Alert>) alertRepository.findByVinAndPriorityOrderByTimestampDesc(vin,type);
	}
	/**
	 * Get all the alerts of a Vehicle
	 * @param vin
	 * @return List<Alert>
	 */
	private List<Alert> getAlertsByVin(String vin) {
		
		return (List<Alert>) alertRepository.findByVinOrderByTimestampDesc(vin);
	}
	


	/**
	 * Gets High Alerts for the last 2 hours of all the vehicles in descending order of 
	 * number of High Alerts
	 * @return List<HighAlerts>
	 */
	public List<HighAlerts> getHighAlertDetails() {
		Date currentDate = new Date();
		Date beforeMinsDate = getTimeBefore2Hours();
		List<HighAlerts> highAlerts = alertRepository.getHighAlertDetails(currentDate, beforeMinsDate);
		return highAlerts;
	}

	/**
	 * Gets the time stamp before two hours from now
	 * @return Date
	 */
	private Date getTimeBefore2Hours() {
		return new Date(System.currentTimeMillis() - 2 * 60 * 60 * 1000);
	}

	/**
	 * Gets the number of Alerts of a vehicle based on priority  
	 * @param vin
	 * @param type priorty HIGH/Low/MEDIUM
	 * @return int
	 * @throws NoPriorityFound
	 */
	public long totalAlertsByVin(String vin, String type) {
		if (type.equals("All")) {
			return getAlertsByVin(vin)
					.stream()
					.count();
		}else if(type.equals("Low") || type.equals("MEDIUM") || type.equals("HIGH")){
			return getAlertsByVinAndType(vin,type)
					.stream()
					.count();
		}else{
			throw new NoPriorityFound("No priority of type "+type+ " found");
		}
		
	}


	

}
