package io.tracker.repository;

import org.springframework.data.repository.CrudRepository;

import io.tracker.domain.Tire;

/**
 * This class is used to persist or get the Vehicle Tire information from the database.
 * Implements CrudRepository 
 * @author kaushik nandhan
 *
 */
public interface TireRepository extends CrudRepository<Tire, String>{

}
