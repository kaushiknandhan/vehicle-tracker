package io.tracker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.tracker.domain.Reading;

public interface ReadingRepository extends CrudRepository<Reading, String>{
	
	@Query("SELECT r FROM Reading r WHERE r.vin=:vin AND r.timestamp BETWEEN :beforeTime AND :currentDate ORDER BY r.timestamp Desc")
	public List<Reading> findReadingsWithTime(@Param(value = "vin")String vin,@Param(value = "currentDate") Date currentDate,@Param(value = "beforeTime") Date beforeTime);

	public List<Reading> findByVin(String vin);
	
}
