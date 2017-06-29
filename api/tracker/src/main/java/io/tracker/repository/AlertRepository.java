package io.tracker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.tracker.domain.Alert;
import io.tracker.domain.HighAlerts;
/**
 * This class is used to create and get Alerts from the database 
 * @author kaushik nandhan
 *
 */
public interface AlertRepository  extends CrudRepository<Alert, String>{

	/**
	 * Get All Alerts of a Vehicle order by the time in descending order 
	 * @param vin
	 * @return List<Alert
	 */
	public List<Alert> findByVinOrderByTimestampDesc(String vin);

	/**
	 * Get All Alerts of a Vehicle based on priority type order by the time in descending order 
	 * @param vin
	 * @return List<Alert
	 */
	public List<Alert> findByVinAndPriorityOrderByTimestampDesc(String vin, String type);

	/**
	 * Gets the number of High Alerts of each vehicle before 2 hours from now in descending order of 
	 * number of High Alerts
	 * @param currentDate
	 * @param beforeMinsDate
	 * @return List<HighAlerts>
	 */
	@Query("SELECT new io.tracker.domain.HighAlerts(a.vin,COUNT(a)) FROM Alert a WHERE a.priority='HIGH' AND a.timestamp <= :currentDate AND a.timestamp >= :beforeMinsDate  GROUP BY a.vin ORDER BY COUNT(a) DESC")
	public List<HighAlerts> getHighAlertDetails(@Param(value = "currentDate")Date currentDate,@Param(value = "beforeMinsDate") Date beforeMinsDate);

	/**
	 * Gets the total Alerts count of a vehicle
	 * @param vin
	 * @return int
	 */
	@Query("SELECT COUNT(a) FROM Alert a WHERE a.vin=:vin")
	public int totalAlertsByVin(@Param(value = "vin") String vin);
	
	/**
	 * Gets the Alert count of a vehicle based on priority type
	 * @param vin
	 * @param type
	 * @return int
	 */
	@Query("SELECT COUNT(a) FROM Alert a WHERE a.vin=:vin AND a.priority=:type")
	public int totalAlertsByVin(@Param(value = "vin") String vin,@Param(value = "type") String type);

}
