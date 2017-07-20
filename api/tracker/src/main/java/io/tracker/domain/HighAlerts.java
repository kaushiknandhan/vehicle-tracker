package io.tracker.domain;

import lombok.Data;
/**
 * This class is HighAlerts POJO contains vin,alertCount.
 * @author kaushik nandhan
 *
 */
@Data
public class HighAlerts {

	private String vin;
	private long alertCount;

	public HighAlerts(String vin, long alertCount) {
		this.vin = vin;
		this.alertCount = alertCount;
	}
}
