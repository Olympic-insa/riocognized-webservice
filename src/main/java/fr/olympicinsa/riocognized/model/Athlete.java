package fr.olympicinsa.riocognized.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToOne;

import org.springframework.util.Assert;

@Entity
@Table(name = "ATHLETE")
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
    @ManyToOne
    private Country country;
    @ManyToOne
    private Sport sport;
    @Column(name = "AGE")
    private Integer age;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "athlete_timetable", joinColumns = { @JoinColumn(name = "athlete_id") }, inverseJoinColumns = { @JoinColumn(name = "timetable_id") })
    private Set<Timetable> timetables = new HashSet<Timetable>();
        
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
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

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Sport getSport() {
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
    
    public Set<Timetable> getTimetables() {
		return timetables;
	}
 
	public void setTimetable(Set<Timetable> timetables) {
		this.timetables = timetables;
	}
}
