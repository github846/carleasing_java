package car_leasing.option;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService {

	@Autowired
	OptionRepository optionRepository;
	
	public Option save(Option option)
	{
		return optionRepository.save(option);
	}
	
	public List<Option> getAll()
	{
		return optionRepository.findAll();
	}
	
	public Option getById(Long id)
	{
		return optionRepository.findById(id).get();
	}
}