package gadZoo.jpadal;

import org.springframework.data.repository.CrudRepository;

import gadZoo.logic.entity.AnimalEntity;

public interface AnimalDao extends CrudRepository<AnimalEntity, String> {

}
