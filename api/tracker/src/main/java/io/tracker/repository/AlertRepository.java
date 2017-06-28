package io.tracker.repository;

import org.springframework.data.repository.CrudRepository;

import io.tracker.domain.Alert;

public interface AlertRepository  extends CrudRepository<Alert, String>{

}
