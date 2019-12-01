package gadZoo.logic.JPA;

import java.util.Map;

import javax.activity.InvalidActivityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import gadZoo.jpadal.ActivityDao;
import gadZoo.logic.ActivityService;
import gadZoo.logic.entity.ActivityEntity;
import gadZoo.plugins.GadZooPlugin;


@Service
public class JPAActivityService implements ActivityService {

	private ActivityDao activityDao;
	private ConfigurableApplicationContext spring;
	private ObjectMapper jackson;

	@Autowired
	public JPAActivityService(ActivityDao activityDao, ConfigurableApplicationContext spring) {
		super();
		this.activityDao = activityDao;
		this.spring = spring;
		this.jackson = new ObjectMapper();
	}

	@Override
	@Transactional
	public ActivityEntity performActivity(String playground, String email, ActivityEntity activityEntity)
			throws InvalidActivityException {
		if (activityEntity.getType() != null) {
			try {
				String type = activityEntity.getType();
				String targetClassName = "gadZoo.plugins." + type + "Plugin";
				Class<?> pluginClass = Class.forName(targetClassName);
				// autowire plugin
				GadZooPlugin plugin = (GadZooPlugin) this.spring.getBean(pluginClass);
//				long id = this.numbers.save(new GeneratedNumber()).getNextNumber();
//				this.numbers.deleteById(id);
//				activityEntity.setId(""+id);
				Object rv = plugin.invokeAction(activityEntity);
				Map<String, Object> rvMap = this.jackson.readValue(this.jackson.writeValueAsString(rv), Map.class);
				activityEntity.getAttributes().putAll(rvMap);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return this.activityDao.save(activityEntity);
	}

	@Override
	public void cleanup() {
		this.activityDao.deleteAll();

	}
}
