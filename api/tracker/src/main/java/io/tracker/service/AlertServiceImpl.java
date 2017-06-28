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

@Service
@Transactional
public class AlertServiceImpl implements AlertService {

	@Autowired
	private AlertRepository alertRepository;

	public List<Alert> getVehicleAlerts(String vin, String type) {
		
		List<Alert> vehicleAlerts =  new ArrayList<>();
		if(type.equals("All")){
			vehicleAlerts = (List<Alert>) alertRepository.findByVinOrderByTimestampDesc(vin);
		}else if(type.equals("Low") || type.equals("MEDIUM") || type.equals("HIGH")){
			vehicleAlerts = (List<Alert>) alertRepository.findByVinAndPriorityOrderByTimestampDesc(vin,type);
		}else{
			throw new NoPriorityFound("No priority of type "+type+ " found");
		}
		return vehicleAlerts;
	}


	public List<HighAlerts> getHighAlertDetails() {
		Date currentDate = new Date();
		Date beforeMinsDate = getBefore30MinsTime();
		List<HighAlerts> highAlerts = alertRepository.getHighAlertDetails(currentDate, beforeMinsDate);
		return highAlerts;
	}


	private Date getBefore30MinsTime() {
		return new Date(System.currentTimeMillis() - 2 * 60 * 60 * 1000);
	}


	public int totalAlertsByVin(String vin, String type) {
		int totalAlerts = 0;
		if (type.equals("All")) {
			totalAlerts = alertRepository.totalAlertsByVin(vin);
		}else if(type.equals("Low") || type.equals("MEDIUM") || type.equals("HIGH")){
			totalAlerts = alertRepository.totalAlertsByVin(vin, type);
		}else{
			throw new NoPriorityFound("No priority of type "+type+ " found");
		}
		return totalAlerts;
	}

}
