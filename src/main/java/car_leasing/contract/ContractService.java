package car_leasing.contract;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

	@Autowired
	ContractRepository contractRepository;
	
	public Contract save(Contract contract)
	{
		return contractRepository.save(contract);
	}
	
	public List<Contract> getAll()
	{
		return contractRepository.findAll();
	}
	
	public Contract getById(Long id)
	{
		return contractRepository.findById(id).get();
	}
	
	public boolean deleteById(long id)
	{
		try
		{
			contractRepository.deleteById(id);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
