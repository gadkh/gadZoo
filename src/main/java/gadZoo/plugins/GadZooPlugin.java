package gadZoo.plugins;

import gadZoo.logic.entity.ActivityEntity;
import gadZoo.logic.exception.AnimalNotFoundException;

public interface GadZooPlugin {
	public Object invokeAction(ActivityEntity activityEntity) throws AnimalNotFoundException;
}
