package gadZoo.layout;

import javax.activity.InvalidActivityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gadZoo.layout.TO.ActivityTO;
import gadZoo.logic.ActivityService;
import gadZoo.logic.entity.ActivityEntity;



@RestController
public class ActivityAPI {

	private ActivityService activityServices;

	
	@Autowired
	public void setActivityServices(ActivityService activityServices) {
		this.activityServices = activityServices;
	}
	
	@RequestMapping(
			method=RequestMethod.POST,
			path="/playground/activities/{type}/{name}",
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
		public Object performActivity(@PathVariable("type") String type,
				@PathVariable("name") String name,@RequestBody ActivityTO newActivity) throws InvalidActivityException {
			ActivityEntity activityRetrive = newActivity.toEntity();
				this.activityServices.performActivity(activityRetrive.getType(),activityRetrive.getName(),activityRetrive);
				return new ActivityTO(activityRetrive);
	}
}
