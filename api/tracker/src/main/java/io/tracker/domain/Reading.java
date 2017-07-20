package io.tracker.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
/**
 * This class is Reading POJO contains readingId(PK),vin,latitude,longitude,timestamp,fuelVolume,speed
 * engineHp,checkEngineLightOn,engineCoolantLow,cruiseControlOn,engineRpm,tires (One to One)
 * @author kaushik nandhan
 *
 */
@Table
@Entity
@Data
public class Reading {

	@Id
	@GenericGenerator(name="readinguuid",strategy="uuid2")
	@GeneratedValue(generator="readinguuid")
	private String readingId;
	private String vin ;
	private double latitude;
	private double longitude;
	private Date timestamp;
	private float fuelVolume;
	private int speed;
	private int engineHp;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean checkEngineLightOn;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean engineCoolantLow;
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean cruiseControlOn;
	private int engineRpm;
	@OneToOne
	private Tire tires;
}