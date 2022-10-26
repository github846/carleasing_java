package car_leasing.contract;

import java.util.Date;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import car_leasing.car.Car;
import car_leasing.client.Client;
import car_leasing.invoice.Invoice;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="contract")
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Temporal(TemporalType.DATE)
	private Date signDate;
	@Temporal(TemporalType.DATE)
	private Date contractStart;
	@Temporal(TemporalType.DATE)
	private Date contractEnd;
	private double totalPrice;
	private double advance;
	private double remainder;
	private String returnPlace;
	
	@OneToOne(mappedBy = "contract")
	@JsonProperty(access= Access.READ_WRITE)
	private Invoice invoice;
	@ManyToOne
	@JsonProperty(access= Access.READ_WRITE)
	private Car car;
	@ManyToOne
	@JsonProperty(access= Access.READ_WRITE)
	private Client client;
	
	
}
