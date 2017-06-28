package io.tracker.service;

import java.util.List;

import io.tracker.domain.Reading;

public interface ReadingService {

	public Reading saveReading(Reading reading);

	public List<Reading> getReadingsWithTime(String vin, String timeType, String time);
}
