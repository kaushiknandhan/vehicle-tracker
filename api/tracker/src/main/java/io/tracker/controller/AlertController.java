package io.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.tracker.domain.Alert;
import io.tracker.domain.HighAlerts;
import io.tracker.exception.NoPriorityFound;
import io.tracker.service.AlertService;
/**
 * This class is a AlertController, save the Alerts of a vehicle and get the same
 * depending on the timestamp
 * Injects AlertService
 * @author kaushik nandhan
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/alerts", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AlertController {

	@Autowired
	private AlertService alertService;
	
	/**
	 * This method is used to get a vehicle alerts based on the priority selected HIGH,Low,MEDIUM
	 * @return List<Alert>
	 *  
	 */
	@RequestMapping(path="/{vin}/{type}",method = RequestMethod.GET)
	public List<Alert> getVehicleAlerts(@PathVariable(name="vin")String vin,@PathVariable(name="type")String type){
		List<Alert> vehicleAlerts = alertService.getVehicleAlerts(vin,type);
		return vehicleAlerts;
	}

	/**
	 * Gets High Alerts for the last 2 hours of all the vehicles in descending order of 
	 * number of High Alerts
	 * @return List<HighAlerts>
	 */
	@RequestMapping(path="/highalerts",method = RequestMethod.GET)
	public List<HighAlerts> getHighAlertDetails(){
		List<HighAlerts> highAlerts = alertService.getHighAlertDetails();	
		return highAlerts;
	}
	
	/**
	 * Gets the number of Alerts of a vehicle based on priority  
	 * @param vin
	 * @param type priorty HIGH/Low/MEDIUM
	 * @return int
	 * @throws NoPriorityFound
	 */
	@RequestMapping(path="/totalcount/{vin}/{type}",method = RequestMethod.GET)
	public int totalAlertsByVin(@PathVariable(name="vin")String vin,@PathVariable(name="type")String type){
		int totalAlerts = alertService.totalAlertsByVin(vin,type);
		return totalAlerts;
	}
}
