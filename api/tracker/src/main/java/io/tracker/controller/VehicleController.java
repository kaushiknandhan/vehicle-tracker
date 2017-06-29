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
/**
 * This class is a VehicleController, save and edit the vehicles data and get the information of the vehicle
 * data
 * Injects VehicleService
 * @author kaushik nandhan
 *
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/vehicles",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	/**
	 * Saves a new vehicle if does not exists, or edit the record if already exists
	 * @param vehicles - List of vehicles
	 */
	@RequestMapping(method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void saveVehicles(@RequestBody List<Vehicle> vehicles){
		vehicleService.saveVehicles(vehicles);
	}
	
	/**
	 * To get all vehicles data present in the database
	 * @return List of All the Vehicles present in the database
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Vehicle> getVehicles(){
		List<Vehicle> allVehicles = vehicleService.getVehicles();		
		return allVehicles;		
	}
	
	/**
	 * To get the vehicle details by passing a VIN number as parameter 
	 * @param vin - Unique VIN number (String)
	 * @return Vehicle object
	 */
	@RequestMapping(path="/{vin}", method = RequestMethod.GET)
	public Vehicle getVechileInfo(@PathVariable(name="vin") String vin){
		Vehicle vehicleInfo = vehicleService.getVechileInfo(vin);
		return vehicleInfo;
	}
}
