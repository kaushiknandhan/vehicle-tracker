package io.tracker.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Table
@Entity
@Data
public class Alert {
	
	@Id
	@GenericGenerator(name="alertuuid",strategy="uuid2")
	@GeneratedValue(generator="alertuuid")
	private String alertId;
	private String vin;
	private Date timestamp;
	private String message;
	private String priority;

}
