package io.tracker.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/readings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReadingController {

	@Autowired
	private ReadingService readingService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Reading saveReading(@RequestBody Reading reading) {
		Reading existingReading = readingService.saveReading(reading);
		return existingReading;
	}

	@RequestMapping(path = "/{vin}/{timeType}/{time}", method = RequestMethod.GET)
	public List<Reading> getReadingsWithTime(@PathVariable(name = "vin") String vin,
			@PathVariable(name = "timeType") String timeType, @PathVariable(name = "time") String time) {
		List<Reading> vehicleReagingsOnTime = readingService.getReadingsWithTime(vin, timeType, time);
		return vehicleReagingsOnTime;
	}
}
