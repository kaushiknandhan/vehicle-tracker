package io.tracker.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * This class is POJO bean for Vehicle. Contains vin, model, make, year, redlineRpm, maxFuelVolume, lastServiceDate
 * @author kaushik nandhan
 *
 */
@Table
@Entity
@Data
public class Vehicle {

	@Id
	private String vin;
	private String make;
	private String model;
	private int year;
	private int redlineRpm;
	private int maxFuelVolume;
	private Date lastServiceDate;
}