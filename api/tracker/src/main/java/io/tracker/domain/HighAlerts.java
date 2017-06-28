package io.tracker.domain;

import lombok.Data;

@Data
public class HighAlerts {

	private String vin;
	private long alertCount;

	public HighAlerts(String vin, long alertCount) {
		this.vin = vin;
		this.alertCount = alertCount;
	}
}
