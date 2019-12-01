package gadZoo.layout.TO;

import java.util.HashMap;
import java.util.Map;

import gadZoo.logic.entity.ActivityEntity;

public class ActivityTO {
	private String type;
	private String animalname;
	private String animalType;
	private Map<String, Object> attributes;
	public ActivityTO() {
		super();
		this.attributes=new HashMap<>();
	}
	public ActivityTO (ActivityEntity activityEntity) {
		this();
		if (activityEntity != null) {
			this.animalname=activityEntity.getAnimalName();
			this.animalType=activityEntity.getAnimalType();
			this.type=activityEntity.getType();
			
			this.attributes = activityEntity.getAttributes();
		}
			
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getAnimalname() {
		return animalname;
	}
	public void setAnimalname(String animalname) {
		this.animalname = animalname;
	}
	public String getAnimalType() {
		return animalType;
	}
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public ActivityEntity toEntity() {
		ActivityEntity activityRetrive = new ActivityEntity();
	
		activityRetrive.setAnimalName(this.animalname);
		activityRetrive.setAnimalType(this.animalType);
		activityRetrive.setType(this.type);
		activityRetrive.setAttributes(this.attributes);
		return activityRetrive;
	}
	@Override
	public String toString() {
		return "ActivityTO [type=" + type + ", animalname=" + animalname + ", animalType=" + animalType
				+ ", attributes=" + attributes + "]";
	}
	
}
