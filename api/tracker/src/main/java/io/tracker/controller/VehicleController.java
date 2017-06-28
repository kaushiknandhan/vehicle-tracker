package io.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.tracker.domain.Vehicle;
import io.tracker.service.VehicleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/vehicles",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@RequestMapping(method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void saveVehicles(@RequestBody List<Vehicle> vehicles){
		vehicleService.saveVehicles(vehicles);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Vehicle> getVehicles(){
		List<Vehicle> allVehicles = vehicleService.getVehicles();		
		return allVehicles;		
	}
	
	@RequestMapping(path="/{vin}", method = RequestMethod.GET)
	public Vehicle getVechileInfo(@PathVariable(name="vin") String vin){
		Vehicle vehicleInfo = vehicleService.getVechileInfo(vin);
		return vehicleInfo;
	}
}
