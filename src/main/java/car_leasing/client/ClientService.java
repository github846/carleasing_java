package car_leasing.client;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	public Client save(Client client)
	{
		return clientRepository.save(client);
	}
	
	public List<Client> getAll()
	{
		return clientRepository.findAll();
	}
	
	public Client getById(Long id)
	{
		return clientRepository.findById(id).orElse(null);
	}
	
	public List<Client> getByFname(String fname)
	{
		return clientRepository.findByFname(fname);
	}
	
	public List<Client> getByFnameAndSurname(String fname, String surname)
	{
		return clientRepository.findByFnameAndSurname(fname, surname);
	}
	
	public List<Client> getByAddress(String address)
	{
		return clientRepository.findByAddress(address);
	}
	
	public boolean deleteById(long id)
	{
		try
		{
			clientRepository.deleteById(id);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

}
