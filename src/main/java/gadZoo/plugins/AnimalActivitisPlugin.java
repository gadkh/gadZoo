package gadZoo.plugins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gadZoo.jpadal.AnimalDao;
import gadZoo.logic.entity.ActivityEntity;
import gadZoo.logic.entity.AnimalEntity;
import gadZoo.logic.exception.AnimalNotFoundException;

@Component
public class AnimalActivitisPlugin implements GadZooPlugin {

	private ObjectMapper jackson;
	private AnimalDao animalDao;
	
	@Autowired
	public AnimalActivitisPlugin(AnimalDao animalDao) {
		super();
		this.jackson=new ObjectMapper();
		this.animalDao = animalDao;
	}


	@Override
	public Object invokeAction(ActivityEntity activityEntity) throws AnimalNotFoundException {
		try {
			int points=0;
			AnimalActivitys animalActivity=this.jackson.readValue(activityEntity.getAttributesJson(), AnimalActivitys.class);
			String id=activityEntity.getId();
			AnimalEntity animalEntity=this.animalDao.findById(id)
					.orElseThrow(()->new AnimalNotFoundException("Wrong animal"));
			if(animalActivity.isFeed()==true) {
				points+=5;
			}
			if(animalActivity.isClean()==true) {
				points+=4;
			}
			if(animalActivity.isPlay()==true) {
				points+=3;
			}
			if(animalActivity.isSleep()==true) {
				points+=2;
			}
			if(animalActivity.isFeed()==false&&animalActivity.isClean()==false&&
					animalActivity.isPlay()==false&&animalActivity.isSleep()==false) {
				points-=3;
			}
			animalEntity.setPoints(animalEntity.getPoints()+points);
			animalDao.save(animalEntity);
			return animalActivity;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
				
		return null;
	}

}
