package io.tracker.repository;

import org.springframework.data.repository.CrudRepository;
import io.tracker.domain.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, String>{

}
