package io.tracker.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.tracker.domain.Reading;
import io.tracker.domain.Tire;
import io.tracker.exception.TimeFormatException;
import io.tracker.repository.ReadingRepository;
import io.tracker.repository.TireRepository;

@Service
@Transactional
public class ReadingServiceImpl implements ReadingService {

	@Autowired
	private ReadingRepository readingRepository;
	@Autowired
	private TireRepository tireRepository;


	public Reading saveReading(Reading reading) {

		// Persist the tires information of a reading
		Tire newTire = reading.getTires();
		reading.setTires(saveTire(newTire));

		// Persist The Readings information of a vehicle
		Reading existingReading = readingRepository.save(reading);

		// Check for any Alerts present in the readings
		// checkForAlerts(existingReading);

		return existingReading;
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
			vehicleReagings = readingRepository.findReadingsWithTime(vin, currentDate, beforeTime);
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
