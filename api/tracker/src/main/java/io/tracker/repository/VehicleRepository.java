package io.tracker.repository;

import org.springframework.data.repository.CrudRepository;
import io.tracker.domain.Vehicle;
/**
 * This class is used to get the Vehicle information from the database.
 * Implements CrudRepository 
 * @author kaushik nandhan
 *
 */
public interface VehicleRepository extends CrudRepository<Vehicle, String>{

	public Vehicle findByVin(String vin);

}
