package gadZoo.logic.JPA;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gadZoo.jpadal.AnimalDao;
import gadZoo.logic.AnimalService;
import gadZoo.logic.entity.AnimalEntity;
import gadZoo.logic.exception.AnimalAlreadyExistException;
import gadZoo.logic.exception.AnimalNotFoundException;

@Service
public class JPAAnimalService implements AnimalService {
	private AnimalDao animalDao;

	@Autowired
	public JPAAnimalService(AnimalDao animalDao) {
		this.animalDao = animalDao;
	}

	@Transactional
	@Override
	public AnimalEntity createAnimal(AnimalEntity animal) throws AnimalAlreadyExistException {
		String key = animal.getType() + "@@" + animal.getName();
		if (this.animalDao.existsById(key)) {
			return this.animalDao.save(animal);
		}
		else
		{
			throw new AnimalAlreadyExistException("Animal type "+animal.getType()+" name "+animal.getName()+" exist");
		}
	}

	@Transactional
	@Override
	public AnimalEntity getAnimal(String type, String name) throws AnimalNotFoundException {
		String key = type + "@@" + name;
		return this.animalDao.findById(key).orElseThrow(() -> new AnimalNotFoundException("We don't have animal in type "+type+" and name "+name+" here"));
	}

	@Transactional
	@Override
	public List<AnimalEntity> getAllAnimals(int size, int page) {
		List<AnimalEntity> listOfAnimals = new ArrayList<>();
		this.animalDao.findAll().forEach(listOfAnimals::add);
		return listOfAnimals.stream().skip(size * page).limit(size).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void updateAnimal(String type, String name, AnimalEntity newAnimal) throws AnimalNotFoundException {
		synchronized (this.animalDao) {
			AnimalEntity animalExist = getAnimal(type, name);
			if (newAnimal.getFood() != null && (!(newAnimal.getFood().equals("")))) {
				if (!(newAnimal.getFood().equals(animalExist.getFood()))) {
					animalExist.setFood(newAnimal.getFood());
				}
			}
			if (newAnimal.getAge() != null && (!(newAnimal.getAge().equals("")))) {
				if (!(newAnimal.getAge().equals(animalExist.getAge()))) {
					animalExist.setAge(newAnimal.getAge());
				}
			}
			this.animalDao.save(animalExist);
		}
	}

	@Transactional
	@Override
	public void cleanup() {
		this.animalDao.deleteAll();
	}

	@Transactional
	@Override
	public void deleteAnimal(String type, String name) {
		String key = type + "@@" + name;
		this.animalDao.deleteById(key);
	}
}
