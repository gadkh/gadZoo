package gadZoo.logic;


import java.util.List;

import gadZoo.logic.entity.AnimalEntity;
import gadZoo.logic.exception.AnimalAlreadyExistException;
import gadZoo.logic.exception.AnimalNotFoundException;


public interface AnimalService {
	public AnimalEntity createAnimal(AnimalEntity animal) throws AnimalAlreadyExistException ;
	public AnimalEntity getAnimal(String type,String name) throws AnimalNotFoundException;
	public void updateAnimal(String type,String name,AnimalEntity newAnimal) throws AnimalNotFoundException;
	public void cleanup();
	public void deleteAnimal(String type,String name);
	public List<AnimalEntity> getAllAnimals(int size, int page);

}
