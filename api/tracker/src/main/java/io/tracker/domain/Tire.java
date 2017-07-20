package io.tracker.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
/**
 * This class is Tire POJO contains tireId(PK),frontLeft,frontRight,rearLeft,rearRight
 * @author kaushik nandhan
 *
 */
@Table
@Entity
@Data
public class Tire {

	@Id
	@GenericGenerator(name="tireuuid",strategy="uuid2")
	@GeneratedValue(generator="tireuuid")
	private String tireId;
	private int frontLeft;
	private int frontRight;
	private int rearLeft;
	private int rearRight;
}
