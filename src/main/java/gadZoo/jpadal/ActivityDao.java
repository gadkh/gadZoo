package gadZoo.jpadal;

import org.springframework.data.repository.CrudRepository;

import gadZoo.logic.entity.ActivityEntity;

public interface ActivityDao extends CrudRepository<ActivityEntity, String>{

}
