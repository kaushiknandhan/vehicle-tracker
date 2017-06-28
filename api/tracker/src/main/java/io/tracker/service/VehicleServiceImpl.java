package io.tracker.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.tracker.domain.Vehicle;
import io.tracker.repository.VehicleRepository;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	public void saveVehicles(List<Vehicle> vehicles) {
		for (Vehicle vechile : vehicles) {
			vehicleRepository.save(vechile);
		}		
	}

	public List<Vehicle> getVehicles() {
		List<Vehicle> allvehicles = (List<Vehicle>) vehicleRepository.findAll();
		return allvehicles;
	}

	public Vehicle getVechileInfo(String vin) {
		Vehicle vehicleInfo = vehicleRepository.findOne(vin);
		return vehicleInfo;
	}
}
