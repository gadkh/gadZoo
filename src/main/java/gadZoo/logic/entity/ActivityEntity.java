package gadZoo.logic.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;




@Entity
@Table(name = "ACTIVITIES")
public class ActivityEntity {
	private String type;
	private String name;
	private String id;
	private Date date;
	private Map<String, Object> attributes;
	
	public ActivityEntity() {
		super();
		this.attributes=new HashMap<>();
		this.date=new Date();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.setId(this.type+"@@"+this.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.setId(this.type+"@@"+this.name);
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Transient
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	@Lob
	@JsonIgnore
	public String getAttributesJson() {
		try {
			return new ObjectMapper().writeValueAsString(this.attributes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@JsonIgnore
	public void setAttributesJson(String json) {
		try {
			this.attributes = new ObjectMapper().readValue(json, Map.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
