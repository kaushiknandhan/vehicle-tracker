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
import io.tracker.service.AlertService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/alerts", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AlertController {

	@Autowired
	private AlertService alertService;
	
	@RequestMapping(path="/{vin}/{type}",method = RequestMethod.GET)
	public List<Alert> getVehicleAlerts(@PathVariable(name="vin")String vin,@PathVariable(name="type")String type){
		List<Alert> vehicleAlerts = alertService.getVehicleAlerts(vin,type);
		return vehicleAlerts;
	}

	@RequestMapping(path="/highalerts",method = RequestMethod.GET)
	public List<HighAlerts> getHighAlertDetails(){
		List<HighAlerts> highAlerts = alertService.getHighAlertDetails();	
		return highAlerts;
	}
	
	@RequestMapping(path="/totalcount/{vin}/{type}",method = RequestMethod.GET)
	public int totalAlertsByVin(@PathVariable(name="vin")String vin,@PathVariable(name="type")String type){
		int totalAlerts = alertService.totalAlertsByVin(vin,type);
		return totalAlerts;
	}
}
