package gadZoo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import gadZoo.layout.TO.ActivityTO;
import gadZoo.layout.TO.AnimalTO;
import gadZoo.logic.AnimalService;




@SpringBootTest
class GadZooApplicationTests {

	@Autowired
	private AnimalService animalService;
	private RestTemplate restTemplate;
	
	private int port=8080;
	private String userUrl;
	private ObjectMapper jackson;
	
	@PostConstruct
	public void init() {
		this.restTemplate = new RestTemplate();
		userUrl = "http://localhost:" + port + "/zoo";
		this.jackson = new ObjectMapper();
	}
	
	@Before
	public void setup() {
	}

	@After
	public void teardown() {
		// cleanup database
		this.animalService.cleanup();
	}
	
	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateNewAnimalSuccessfully() throws Exception {
		String type = "Lion";
		String name = "Simba";
		String age = "12";
		String food = "Meal";
		
		String postUrl=this.userUrl+"/postAnimal";
		String getUrl=this.userUrl+"/getAnimal/{type}/{name}";
		// Given: server is up

		// I POST /zoo/postAnimal with headers
		// Accept:application/json
		// Content-Type:application/json
		// and body
		// {
		// type = "Lion",
		//name = "Simba",
		//age = "12",
		//food = "Meal"
		// }
		AnimalTO animalTO =new AnimalTO();
		animalTO.setName(name);
		animalTO.setType(type);
		animalTO.setAge(age);
		animalTO.setFood(food);
		animalTO.setPoints(0);
		
		AnimalTO newAnimal= this.restTemplate.postForObject(postUrl, animalTO, AnimalTO.class);

		// Then the response status is 200 and
		// the object is
		// {
		// type = "Lion",
		//name = "Simba",
		//age = "12",
		//food = "Meal",
		//points=0
		// }
		
		newAnimal=this.restTemplate.getForObject(getUrl, AnimalTO.class, type,name);
		assertThat(newAnimal).extracting("name", "type", "age", "food", "points")
				.containsExactly(name, type, age, food, 0);
	}
	
	@Test()
	public void testGetAnimalThatNotInTheDB() {
		String name="Mofasa";
		String type="Lion";
		String getUrl=this.userUrl+"/getAnimal/{type}/{name}";

		// Given: server is up
		
		

		// When: I POST /zoo/getAnimal/Lion/Mofasa with headers
		// Accept:application/json
		// Content-Type:application/json
	
		Assertions.assertThrows(Exception.class, () -> {
			this.restTemplate.getForObject(getUrl, AnimalTO.class, type,name);
		  });
		// Then the response status is <> 200 and
	}
	
	@Test
	public void testUpdateAnimalSuccessfully() {
		AnimalTO animalTO=new AnimalTO("Alfa", "Wolf", "10", "Meal", 0);
		String postUrl=this.userUrl+"/postAnimal";
		String getUrl=this.userUrl+"/getAnimal/{type}/{name}";
		String putUrl=this.userUrl+"/updateAnimal/{type}/{name}";
		AnimalTO newAnimal= this.restTemplate.postForObject(postUrl, animalTO, AnimalTO.class);

		//Given server is up and The database contains {"type":"Wolf", "name":"Alfa", "food:"Meal", "Age:10", "points":0} 

		
		/*this.userServices.storeUser(
				this.jackson.readValue("{\"playground\":\"2019A.gadha\", \"email\":\"admin@mail.com\"}", UserEntity.class));*/

		// When I PUT /zoo/updateAnimal/Wolf/Alfa
		// with headers
		// Content-Type: application/json
		// And with body {"type":"Wolf", "name":"Alfa", "food:"Meal", "Age:11", "points":0}
		animalTO.setAge("11");
		
		this.restTemplate.put(
				putUrl, 
				animalTO,
				animalTO.getType(), 
				animalTO.getName());
		
		// Then the response status is 2xx and the body contain for playground: "2019A.gadha"
		// And 
		// {"type":"Wolf", "name":"Alfa", "food:"Meal", "Age:11", "points":0}
		newAnimal=this.restTemplate.getForObject(getUrl, AnimalTO.class, animalTO.getType(),animalTO.getName());
		
		
		assertThat(newAnimal).extracting("name", "type", "age", "food", "points")
		.containsExactly(animalTO.getName(), animalTO.getType(), animalTO.getAge(), animalTO.getFood(), animalTO.getPoints());
	}
	
	@Test
	public void testGetAllAnimalsUsingPaginationOfFirstPage()  {
		String postUrl=this.userUrl+"/postAnimal";
		String getUrl=this.userUrl+"/getAnimal/{type}/{name}";
		String getPaginationUrl=this.userUrl+"/getAllAnimals?size=2&page=0";
		AnimalTO firstAnimal=new AnimalTO("Moshiko", "Lion", "10", "Meal", 0);
		AnimalTO secondtAnimal=new AnimalTO("Manor", "Wolf", "10", "Meal", 0);
		AnimalTO thirdAnimal=new AnimalTO("Bob", "Lion", "9", "Meal", 0);

//		Given server is up, DB contains list of 3 animals, size = 8 and page = 0
		
		int size = 2;
		int page = 0;
		
		this.restTemplate.postForObject(postUrl, firstAnimal, AnimalTO.class);
		this.restTemplate.postForObject(postUrl, secondtAnimal, AnimalTO.class);
		this.restTemplate.postForObject(postUrl, thirdAnimal, AnimalTO.class);
		
//		When I GET /zoo/getAllAnimals?size=1?page=0	
		AnimalTO[] animalList = this.restTemplate.getForObject(getPaginationUrl,AnimalTO[].class, size, page);

//		Then the response status is 2XX and body body contains 2 elements
		assertThat(animalList)
		.isNotNull()
		.hasSize(size);
	}	

	@Test 
	public void testPerformActivitySuccessfully()  {
		String postUrl=this.userUrl+"/postAnimal";
		String getUrl=this.userUrl+"/getAnimal/{type}/{name}";
		String postActivityUrl=this.userUrl+"/activities/{type}/{name}/";
		AnimalTO animalTO=new AnimalTO("Havi", "Lion", "10", "Meal", 0);
		this.restTemplate.postForObject(postUrl, animalTO, AnimalTO.class);
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("feed",true);
		attributes.put("clean",true);
		attributes.put("play",true);
		attributes.put("sleep",true);
		
		ActivityTO activityTO=new ActivityTO();
		activityTO.setType("AnimalActivitis");
		activityTO.setAnimalname(animalTO.getName());
		activityTO.setAnimalType(animalTO.getType());
		activityTO.setAttributes(attributes);

		
		
		// Given server is up
		// When I POST
		// /zoo/activities/Lion/Havi/ with 
		//"type":"AnimalActivitis",
		//"animalname":"Havi",
		//"animalType":"Lion",
		//"attributes":
		//{
		//"feed":true,
		//"clean":true,
		//"play":true,
		//"sleep":true
		//}
		// With headers Accept: application/json,
		// Content-Type: application/json
		

		
		ActivityTO returnActivityTO = this.restTemplate.postForObject(postActivityUrl,
				activityTO,ActivityTO.class,activityTO.getAnimalType(),activityTO.getAnimalname());
		
		// Then the response status is 2xx and body is
		
		AnimalTO retriveAnimal=this.restTemplate.getForObject(getUrl, AnimalTO.class, animalTO.getType(),animalTO.getName());
				

		assertThat(retriveAnimal).extracting("name", "type", "age", "food", "points")
		.containsExactly(animalTO.getName(), animalTO.getType(), animalTO.getAge(), animalTO.getFood(), 14);
	}
}
