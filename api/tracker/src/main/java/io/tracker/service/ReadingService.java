package io.tracker.service;

import java.util.List;

import io.tracker.domain.Reading;

public interface ReadingService {

	/**
	 * 
	 * Saves the reading and the Tire Information to the database. Creates
	 * alerts if found and stores the same in the database. If high alert is
	 * found, sends an email to the user. Does Asynchronous operation
	 *
	 * @param reading
	 * @return
	 */
	public Reading saveReading(Reading reading);

	/**
	 * get Readings of a vehicle based on the vin, timestamp, time given. This data is used to plot the
	 * signal data and also to get the geo-location of a vehicle any time from cyrrent time. 
	 * @param vin - String
	 * @param timeType - String
	 * @param time - String
	 * @return - List<Reading> 
	 */
	public List<Reading> getReadingsWithTime(String vin, String timeType, String time);
}
