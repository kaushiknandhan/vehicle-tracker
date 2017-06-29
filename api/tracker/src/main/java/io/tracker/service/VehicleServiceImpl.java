package io.tracker.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.tracker.domain.Vehicle;
import io.tracker.repository.VehicleRepository;
/**
 * This class is used to write all the business logic required to get the data of vehicle entity from the database based 
 * on the parameters and filters. 
 * @author kaushik nandhan
 *
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	/**
	 * Saves or edits each vehicle looping through the list 
	 * @param vehicles - List of vehicles
	 */
	public void saveVehicles(List<Vehicle> vehicles) {
		for (Vehicle vechile : vehicles) {
			vehicleRepository.save(vechile);
		}		
	}
	
	/**
	 * To get all vehicles data present in the database
	 * @return List of All the Vehicles present in the database
	 */
	public List<Vehicle> getVehicles() {
		List<Vehicle> allvehicles = (List<Vehicle>) vehicleRepository.findAll();
		return allvehicles;
	}

	/**
	 * To get the vehicle details by passing a VIN number as parameter 
	 * @param vin - Unique VIN number (String)
	 * @return Vehicle object
	 */
	public Vehicle getVechileInfo(String vin) {
		Vehicle vehicleInfo = vehicleRepository.findOne(vin);
		return vehicleInfo;
	}
}
