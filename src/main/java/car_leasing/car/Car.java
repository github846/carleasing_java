package car_leasing.car;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import car_leasing.contract.Contract;
import car_leasing.option.Option;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String registration;
	private String color;
	private String brand;
	private String fuel;
	private double cylinder;
	private int maxSpeed;
	private boolean inUse;
	private int mileage;
	@Temporal(TemporalType.DATE)
	private Date firstUse;
	
	@OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
	@JsonProperty(access=Access.WRITE_ONLY)
	private List <Contract> contracts;
	@ManyToMany
	private List<Option> options;
	
}


