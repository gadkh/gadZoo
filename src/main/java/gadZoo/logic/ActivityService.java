package gadZoo.logic;

import javax.activity.InvalidActivityException;

import gadZoo.logic.entity.ActivityEntity;


public interface ActivityService {
	public ActivityEntity performActivity(String type,String name,ActivityEntity activityEntity) throws InvalidActivityException;
	public void cleanup();
}
