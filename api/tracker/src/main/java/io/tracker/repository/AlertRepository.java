package io.tracker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.tracker.domain.Alert;
import io.tracker.domain.HighAlerts;

public interface AlertRepository  extends CrudRepository<Alert, String>{

	public List<Alert> findByVinOrderByTimestampDesc(String vin);

	public List<Alert> findByVinAndPriorityOrderByTimestampDesc(String vin, String type);

	@Query("SELECT new io.tracker.domain.HighAlerts(a.vin,COUNT(a)) FROM Alert a WHERE a.priority='HIGH' AND a.timestamp <= :currentDate AND a.timestamp >= :beforeMinsDate  GROUP BY a.vin ORDER BY COUNT(a) DESC")
	public List<HighAlerts> getHighAlertDetails(@Param(value = "currentDate")Date currentDate,@Param(value = "beforeMinsDate") Date beforeMinsDate);

	@Query("SELECT COUNT(a) FROM Alert a WHERE a.vin=:vin")
	public int totalAlertsByVin(@Param(value = "vin") String vin);

	@Query("SELECT COUNT(a) FROM Alert a WHERE a.vin=:vin AND a.priority=:type")
	public int totalAlertsByVin(@Param(value = "vin") String vin,@Param(value = "type") String type);

}
