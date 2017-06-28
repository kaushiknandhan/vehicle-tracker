package io.tracker.service;

import java.util.List;

import io.tracker.domain.Vehicle;

public interface VehicleService {

	public void saveVehicles(List<Vehicle> vehicles);

	public List<Vehicle> getVehicles();

	public Vehicle getVechileInfo(String vin);

	

}
