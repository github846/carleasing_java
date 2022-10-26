package car_leasing.client;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients/")
@CrossOrigin(origins = "http://localhost:3000/")
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@GetMapping("/")
	public List<Client> getAllClient()
	{
		return clientService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") long id)
	{
		Optional <Client> optClient = Optional.of(clientService.getById(id));
		
		if (optClient.isPresent())
		{
			return new ResponseEntity<>(optClient.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(optClient.get(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Client> createTutorial(@RequestBody Client client)
	{
		try
		{
			Client createdClient = clientService.save(client);
			return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id)
	{
		if (clientService.deleteById(id) == true)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/fname/{fname}")
	public ResponseEntity<List<Client>> getClientsByFname(@PathVariable("fname") String fname)
	{
		List<Client> clients = clientService.getByFname(fname);
		
		return new ResponseEntity<>(clients, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable("id") long id, @RequestBody Client client)
	{
		Client existingClient = clientService.getById(id);
		if (existingClient != null)
		{
			Client updatedClient = new Client();
			updatedClient.setId(existingClient.getId());
	        updatedClient.setFname(client.getFname());
	        updatedClient.setSurname(client.getSurname());
	        updatedClient.setAddress(client.getAddress());
	        updatedClient.setDob(client.getDob());
	        updatedClient.setFidelity(client.getFidelity());
	        return new ResponseEntity<>(clientService.save(updatedClient), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/tutorials")
	  public ResponseEntity<List<Client>> getAllClient(@RequestParam(required = false) String fname,
			                                           @RequestParam(required = false) String surname) {
	    try {
	      List<Client> clients = clientService.getByFnameAndSurname(fname, surname);

	      if (clients.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(clients, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
