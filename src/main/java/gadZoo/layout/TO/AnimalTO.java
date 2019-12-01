package gadZoo.layout.TO;

import gadZoo.logic.entity.AnimalEntity;

public class AnimalTO {
	private String name;
	private String type;
	private String age;
	private String food;
	
	public AnimalTO() {
		super();
	}

	public AnimalTO(String name, String type, String age, String food) {
		super();
		this.name = name;
		this.type = type;
		this.age = age;
		this.food = food;
	}
	
	public AnimalTO(AnimalEntity animalEntity) {
		if(animalEntity != null) {
			this.name=animalEntity.getName();
			this.food=animalEntity.getFood();
			this.age=animalEntity.getAge();
			this.type=animalEntity.getType();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}
	
	public AnimalEntity toEntity() {
		AnimalEntity animalEntity=new AnimalEntity();
		animalEntity.setName(this.name);
		animalEntity.setFood(this.food);
		animalEntity.setAge(this.age);
		animalEntity.setType(this.type);
		return animalEntity;
	}
}
