package fr.olympicinsa.riocognized.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import org.json.JSONException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import javax.servlet.ServletContext;
import javax.xml.bind.annotation.XmlRootElement;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.engine.profile.Fetch;

import org.springframework.util.Assert;

@Entity
@Table(name = "ATHLETE")
@XmlRootElement
public class Athlete implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "LAST_NAME")
    private String name;
    @Column(name = "FIRST_NAME")
    private String surname;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "SPORT")
    private String sport;
    @Column(name = "AGE")
    private Integer age;
    
    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "image_id")
    private Image image;
    
    
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> description = new HashMap<>();
    
    
    public void setDescritption(String name, String value) {

		Assert.hasText(name);

		if (value == null) {
			this.description.remove(value);
		} else {
			this.description.put(name, value);
		}
    }
    
    public Map<String, String> getDescritpion() {
		return Collections.unmodifiableMap(description);
    }
    
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(Long id) {
        this.id = id;
    }

//	public void setSportID(Sport sportID) {
//		this.sportID = sportID;
//	}
//	
//	public Sport getSportID() {
//		return this.sportID;
//	}
    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getSport() {
        return this.sport;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return this.age;
    }
    @JsonProperty("image_url")
    public String getURL() {
        return "http://olympic-insa.fr.nf:8083/image/download/"+image.getId();

    }
}
