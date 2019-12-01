package gadZoo.logic.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Animals")
public class AnimalEntity {
	private String id;
	private String name;
	private String type;
	private String age;
	private String food;
	private int points;
	
	public AnimalEntity() {
		super();
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.setId(this.type+"@@"+this.name);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.setId(this.type+"@@"+this.name);
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}
}
