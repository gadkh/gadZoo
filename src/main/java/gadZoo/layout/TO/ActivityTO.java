package gadZoo.layout.TO;

import java.util.HashMap;
import java.util.Map;

public class ActivityTO {
	private String type;
	private String name;
	private Map<String, Object> attributes;
	public ActivityTO() {
		super();
		this.attributes=new HashMap<>();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
}
