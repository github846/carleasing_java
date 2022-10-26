package car_leasing.option;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/options")
public class OptionController
{
	@Autowired
	OptionService optionService;
	
	@GetMapping("/")
	public ResponseEntity<List<Option>> getAll()
	{
		try
		{
			List<Option> options = optionService.getAll();
			return new ResponseEntity<>(options, HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Option> getById(@PathVariable("id") long id)
	{
		Option option = optionService.getById(id);
		
		if(option != null)
			return new ResponseEntity<>(option, HttpStatus.OK);
		else 
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/")
	public ResponseEntity<Option> createOption(@RequestBody Option option)
	{
		try
		{
			Option createdOption = optionService.save(option);
			return new ResponseEntity<>(option, HttpStatus.CREATED);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
