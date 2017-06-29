package io.tracker.service;

import java.util.List;

import io.tracker.domain.Vehicle;
/**
 * This class is used to write all the business logic required to get the data of vehicle entity from the database based 
 * on the parameters and filters. 
 * @author kaushik nandhan
 *
 */
public interface VehicleService {
	/**
	 * Saves or edits each vehicle looping through the list 
	 * @param vehicles - List of vehicles
	 */
	public void saveVehicles(List<Vehicle> vehicles);
	/**
	 * To get all vehicles data present in the database
	 * @return List of All the Vehicles present in the database
	 */
	public List<Vehicle> getVehicles();
	/**
	 * To get the vehicle details by passing a VIN number as parameter 
	 * @param vin - Unique VIN number (String)
	 * @return Vehicle object
	 */
	public Vehicle getVechileInfo(String vin);

}
