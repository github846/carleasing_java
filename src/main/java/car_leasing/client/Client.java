package car_leasing.client;

import java.util.Date;
import java.util.List;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import car_leasing.contract.Contract;

import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="client")

public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 50)
	private String fname;
	@Column(columnDefinition = "VARCHAR(50) NOT NULL")
	private String surname;
	@Column(length = 255)
	private String address;
	@Temporal(TemporalType.DATE)
	private Date dob;
	@Column(columnDefinition = "INT DEFAULT 0")
	private int fidelity;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Contract> contracts;
	

}
