package car_leasing.contract;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contracts/")
@CrossOrigin(origins = "http://localhost:3000/")
public class ContractController {

	@Autowired
	ContractService contractService;
	
	@GetMapping("/")
	public List<Contract> getAll()
	{
		return contractService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contract> getContractById(@PathVariable("id") long id)
	{
		Optional <Contract> optContract = Optional.of(contractService.getById(id));
		
		if (optContract.isPresent())
		{
			return new ResponseEntity<>(optContract.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(optContract.get(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Contract> createTutorial(@RequestBody Contract contract)
	{
		try
		{
			Contract createdContract = contractService.save(contract);
			return new ResponseEntity<>(createdContract, HttpStatus.CREATED);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id)
	{
		if (contractService.deleteById(id) == true)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
