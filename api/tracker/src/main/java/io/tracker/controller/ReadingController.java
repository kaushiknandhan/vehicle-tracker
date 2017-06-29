package io.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.tracker.domain.Reading;
import io.tracker.service.ReadingService;
/**
 * This class is a ReadingController, save the raw readings of a vehicle and get the readings of the vehicle
 * depending on the timestamp
 * Injects VehicleService
 * @author kaushik nandhan
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/readings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReadingController {

	@Autowired
	private ReadingService readingService;
	
	/**
	 * This method is used to save the readings into the database. And create alerts depending on the 
	 * conditions given
	 * Rule: engineRpm > readlineRpm, Priority: HIGH
	 *	Rule: fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM
	 *	Rule: tire pressure of any tire < 32psi || > 36psi , Priority: LOW
	 *	Rule: engineCoolantLow = true || checkEngineLightOn = true, Priority: LOW 
	 * @param reading
	 * @return reading
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Reading saveReading(@RequestBody Reading reading) {
		Reading existingReading = readingService.saveReading(reading);
		return existingReading;
	}
	
	/**
	 * This method gives the readings of a vehicle depending on the time stamp chosen. 
	 * @param vin - vin number if vehicle
	 * @param timeType - MINUTES/HOURS/DAYS
	 * @param time - 1 to any time
	 * @return
	 */
	@RequestMapping(path = "/{vin}/{timeType}/{time}", method = RequestMethod.GET)
	public List<Reading> getReadingsWithTime(@PathVariable(name = "vin") String vin,
			@PathVariable(name = "timeType") String timeType, @PathVariable(name = "time") String time) {
		List<Reading> vehicleReagingsOnTime = readingService.getReadingsWithTime(vin, timeType, time);
		return vehicleReagingsOnTime;
	}
}
