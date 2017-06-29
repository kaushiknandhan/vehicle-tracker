package io.tracker.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import io.tracker.domain.Alert;
import io.tracker.domain.Reading;
import io.tracker.domain.Tire;
import io.tracker.domain.Vehicle;
import io.tracker.exception.TimeFormatException;
import io.tracker.exception.VehicleNotFound;
import io.tracker.repository.AlertRepository;
import io.tracker.repository.ReadingRepository;
import io.tracker.repository.TireRepository;
import io.tracker.repository.VehicleRepository;

/**
 * This class is used to write all the business logic required to get the data
 * of Reading entity from the database based on the parameters and filters.
 * 
 * @author kaushik nandhan
 *
 */
@Service
@Transactional
public class ReadingServiceImpl implements ReadingService {

	@Autowired
	private ReadingRepository readingRepository;
	@Autowired
	private TireRepository tireRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private AlertRepository alertRepository;
	@Autowired
	private EmailService emailService;

	/**
	 * Saves the reading and the Tire Information to the database. Creates
	 * alerts if found and stores the same in the database. If high alert is
	 * found, sends an email to the user. Does Asynchronous operation
	 * @throws VehicleNotFound
	 */
	@Async
	public Reading saveReading(Reading reading) {

		// Persist the tires information of a reading
		Tire newTire = reading.getTires();
		reading.setTires(saveTire(newTire));

		// Persist the Readings information of a vehicle
		Reading existingReading = readingRepository.save(reading);

		// Check if a vehicle present with vin number, if present checks for
		// alerts
		// else throws an exception
		if (vehicleRepository.findByVin(existingReading.getVin()) != null) {
			
			// Check for any Alerts present in the readings
			checkForAlerts(existingReading);

			// Check for tire Alerts present in the readings
			checkForTireAlerts(existingReading);
		} else {
			throw new VehicleNotFound("No Vehicle present with the VIN " + existingReading.getVin());
		}

		return existingReading;
	}

	/**
	 *  check for the alerts if present depending on the conditions given
	 *  Rule: engineRpm > readlineRpm, Priority: HIGH
	 *  Rule: fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM
	 *  Rule: engineCoolantLow = true || checkEngineLightOn = true, Priority: LOW
	 * @param existingReading
	 */
	private void checkForAlerts(Reading existingReading) {
		// Get the Vehicle information from the Vehicles database.
		Vehicle existingVehicle = vehicleRepository.findByVin(existingReading.getVin());

		if (existingReading.getEngineRpm() > existingVehicle.getRedlineRpm()) {
			createAlert("EngineRpm of the vehicle is Greater than the RedlineRpm", "HIGH", existingReading.getVin(),
					new Date());

		}
		if (existingReading.getFuelVolume() < 0.10 * existingVehicle.getMaxFuelVolume()) {
			createAlert("Vehicle's fuel volume is less than 10% of the Max Fuel voume", "MEDIUM",
					existingReading.getVin(), new Date());
		}
		if (existingReading.isCheckEngineLightOn() == true) {
			createAlert("The Check Engine Light is On for the Vehicle", "Low", existingReading.getVin(), new Date());
		}
		if (existingReading.isEngineCoolantLow() == true) {
			createAlert("The Engine Coolant is Low for the Vehicle", "Low", existingReading.getVin(), new Date());
		}

	}
	
	/**
	 *  check for the TIRE alerts if present depending on the conditions given
	 *	Rule: tire pressure of any tire < 32psi || > 36psi , Priority: LOW
	 * @param existingReading
	 */
	private void checkForTireAlerts(Reading existingReading) {
		if (existingReading.getTires().getFrontLeft() < 32 || existingReading.getTires().getFrontLeft() > 36) {
			createAlert("The Front Left tire pressure is not accurate EXPECTED: 32<=TP<=36, ACTUAL: "
					+ existingReading.getTires().getFrontLeft(), "Low", existingReading.getVin(), new Date());
		}
		if (existingReading.getTires().getFrontRight() < 32 || existingReading.getTires().getFrontRight() > 36) {
			createAlert("The Front Right tire pressure is not accurate EXPECTED: 32<=TP<=36, ACTUAL: "
					+ existingReading.getTires().getFrontRight(), "Low", existingReading.getVin(), new Date());
		}
		if (existingReading.getTires().getRearLeft() < 32 || existingReading.getTires().getRearLeft() > 36) {
			createAlert("The Rear Left tire pressure is not accurate EXPECTED: 32<=TP<=36, ACTUAL: "
					+ existingReading.getTires().getRearLeft(), "Low", existingReading.getVin(), new Date());
		}
		if (existingReading.getTires().getRearRight() < 32 || existingReading.getTires().getRearRight() > 36) {
			createAlert("The Rear Right tire pressure is not accurate EXPECTED: 32<=TP<=36, ACTUAL: "
					+ existingReading.getTires().getRearRight(), "Low", existingReading.getVin(), new Date());
		}
	}

	/**
	 * Persists the alert message to the database. If priority is HIGH triggers an email as well
	 * @param message - String - An alert message 
	 * @param priority -  String - HIGH/MEDIUM/Low
	 * @param vin - String - Vin number
	 * @param timestamp - Date - current data
	 */
	private void createAlert(String message, String priority, String vin, Date timestamp) {
		Alert alert = new Alert();
		alert.setMessage(message);
		alert.setPriority(priority);
		alert.setVin(vin);
		alert.setTimestamp(timestamp);
		Alert existingAlert = alertRepository.save(alert);
		
		// send Email alert to user of the priority is HIGH
		if (existingAlert.getPriority().equals("HIGH")) {
			sendEmailAlert(existingAlert);
		}
	}

	/**
	 * Send email to the user reagrding the Alert
	 * @param existingAlert - Alert
	 */
	private void sendEmailAlert(Alert existingAlert) {
		try {
			emailService.sendEmailAlert(existingAlert);
		} catch (MailException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Saves the Tire information from a vehicles reading
	 * @param newTire Tire
	 * @return  Tire
	 */
	private Tire saveTire(Tire newTire) {
		return tireRepository.save(newTire);
	}
	
	/**
	 * get Readings of a vehicle based on the vin, timestamp, time given. This data is used to plot the
	 * signal data and also to get the geo-location of a vehicle any time from cyrrent time. 
	 * @param vin - String
	 * @param timeType - String
	 * @param time - String
	 * @return - List<Reading> 
	 */
	public List<Reading> getReadingsWithTime(String vin, String timeType, String time) {
		List<Reading> vehicleReagings = new ArrayList<>();
		
		// If the timeType is All, get all the readings of a vehicle
		if (timeType.equals("All")) {
			vehicleReagings = readingRepository.findByVin(vin);
		} else {
			Date currentDate = new Date();
			// Gets the time from now depending on the paramets
			Date beforeTime = getBeforeTime(timeType, time);
			// Gets vehicle readings after between the current time and the given before time 
			vehicleReagings = readingRepository.findReadingsWithTime(vin, currentDate, beforeTime);

		}
		return vehicleReagings;
	}

	/**
	 * Gives the timestamp before certain DAYS/MINUTES/HOURS from now
	 * @param timeType
	 * @param time
	 * @return Date
	 */
	private Date getBeforeTime(String timeType, String time) {
		Date beforeMinsDate = new Date();
		Calendar calendar = Calendar.getInstance();
		if (timeType.equals("MINUTES")) {
			calendar.add(Calendar.MINUTE, -Integer.parseInt(time));
			beforeMinsDate = calendar.getTime();
		} else if (timeType.equals("HOURS")) {
			calendar.add(Calendar.HOUR, -Integer.parseInt(time));
			beforeMinsDate = calendar.getTime();
		} else if (timeType.equals("DAYS")) {
			calendar.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(time));
			beforeMinsDate = calendar.getTime();
		} else {
			throw new TimeFormatException("The fiven time " + timeType + " is not available");
		}
		return beforeMinsDate;
	}
}
