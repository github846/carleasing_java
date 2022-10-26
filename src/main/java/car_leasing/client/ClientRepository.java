package car_leasing.client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>{
	void deleteById(Long id);
	List<Client> findByFname(String fname);
	List<Client> findByFnameAndSurname(String fname, String surname);
	List<Client> findByAddress(String address);
}
