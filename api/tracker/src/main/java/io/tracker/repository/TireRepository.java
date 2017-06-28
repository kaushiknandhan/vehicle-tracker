package io.tracker.repository;

import org.springframework.data.repository.CrudRepository;

import io.tracker.domain.Tire;

public interface TireRepository extends CrudRepository<Tire, String>{

}
