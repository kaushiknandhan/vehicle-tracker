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

	@Async
	public Reading saveReading(Reading reading) {

		// Persist the tires information of a reading
		Tire newTire = reading.getTires();
		reading.setTires(saveTire(newTire));

		// Persist The Readings information of a vehicle
		Reading existingReading = readingRepository.save(reading);

		if (vehicleRepository.findByVin(existingReading.getVin()) != null) {
			// Check for any Alerts present in the readings
			checkForAlerts(existingReading);
			checkForTireAlerts(existingReading);
		} else {
			throw new VehicleNotFound("No Vehicle present with the VIN " + existingReading.getVin());
		}

		return existingReading;
	}

	private void checkForAlerts(Reading existingReading) {
		// Get the Vehicle servicing information.
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

	private void createAlert(String message, String priority, String vin, Date timestamp) {
		Alert alert = new Alert();
		alert.setMessage(message);
		alert.setPriority(priority);
		alert.setVin(vin);
		alert.setTimestamp(timestamp);
		Alert existingAlert = alertRepository.save(alert);
		if (existingAlert.getPriority().equals("HIGH")) {
			sendEmailAlert(existingAlert);
		}
	}

	private void sendEmailAlert(Alert existingAlert) {
		try {
			emailService.sendEmailAlert(existingAlert);
		} catch (MailException e) {
			System.out.println(e.getMessage());
		}
	}

	private Tire saveTire(Tire newTire) {
		return tireRepository.save(newTire);
	}

	public List<Reading> getReadingsWithTime(String vin, String timeType, String time) {
		List<Reading> vehicleReagings = new ArrayList<>();
		if (timeType.equals("All")) {
			vehicleReagings = readingRepository.findByVin(vin);
		} else {
			 Date currentDate = new Date();
			 Date beforeTime = getBeforeTime(timeType, time);
			 vehicleReagings = readingRepository.findReadingsWithTime(vin,
			 currentDate, beforeTime);
			
		}
		return vehicleReagings;
	}

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
