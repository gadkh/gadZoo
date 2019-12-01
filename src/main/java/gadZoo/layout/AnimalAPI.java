package gadZoo.layout;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gadZoo.layout.TO.AnimalTO;
import gadZoo.logic.AnimalService;
import gadZoo.logic.entity.AnimalEntity;
import gadZoo.logic.exception.AnimalAlreadyExistException;
import gadZoo.logic.exception.AnimalNotFoundException;

@RestController
public class AnimalAPI {
	
	private AnimalService animalService;
	
	@Autowired
	public  AnimalAPI(AnimalService animalService) {
		this.animalService=animalService;
	}
	@RequestMapping(method=RequestMethod.GET,path="/zoo/getAnimal/{type}/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public AnimalTO getAnimal(@PathVariable("type") String type, @PathVariable("name") String name) throws AnimalNotFoundException {
		AnimalEntity returnAnimal=this.animalService.getAnimal(type, name);
		return new AnimalTO(returnAnimal);
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/zoo/getAllAnimals",produces=MediaType.APPLICATION_JSON_VALUE)
	public AnimalTO[] getAllAnimals(
			@RequestParam(name="size", required=false, defaultValue="10") int size, 
			@RequestParam(name="page", required=false, defaultValue="0") int page) {
		return this.animalService.getAllAnimals(size, page)
				.stream()
				.map(AnimalTO::new)
				.collect(Collectors.toList())
				.toArray(new AnimalTO[0]);
	}
	
	@RequestMapping(method=RequestMethod.POST,path="/zoo/postAnimal",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AnimalTO createAnimal(@RequestBody AnimalTO animalTo) throws AnimalAlreadyExistException {
		AnimalEntity animalEntity=animalTo.toEntity();
		this.animalService.createAnimal(animalEntity);
		return new AnimalTO(this.animalService.createAnimal(animalEntity));
	}
	
	@RequestMapping(method=RequestMethod.PUT,path="/zoo/updateAnimal/{type}/{name}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateAnimal(@PathVariable("type") String type, @PathVariable("name") String name,
			@RequestBody AnimalTO newAnimal) throws AnimalNotFoundException {
		this.animalService.updateAnimal(type, name, newAnimal.toEntity());
	}
	
	@RequestMapping(method=RequestMethod.DELETE,path="/zoo/deleteAnimal/{type}/{name}")
	public void deleteAnimal(@PathVariable("type") String type, @PathVariable("name") String name) {
		this.animalService.deleteAnimal(type, name);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,path="/zoo/deleteAll")
	public void deleteAll() {
		this.animalService.cleanup();
	}
	
}