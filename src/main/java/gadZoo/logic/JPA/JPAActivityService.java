package gadZoo.logic.JPA;

import javax.activity.InvalidActivityException;

import gadZoo.logic.ActivityService;
import gadZoo.logic.entity.ActivityEntity;

public class JPAActivityService implements ActivityService{

	@Override
	public ActivityEntity performActivity(String type, String name, ActivityEntity activityEntity)
			throws InvalidActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

}
