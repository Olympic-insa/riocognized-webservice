package fr.olympicinsa.riocognized.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAthleteBySport", 
query = "SELECT c " +
        "FROM Athlete c " +
        "WHERE c.sport.name = :sport")
public class Athlete {

	@Id
	private Long id;
	@Column(name="LAST_NAME")
	private String name;
	@Column(name="FIRST_NAME")
	private String surname;
	
	private String content;
	
    @ManyToOne
	private Sport sport;

	public Athlete(Long id, String name, String surname, String content) {
		this.id = id;
		this.content = content;
		this.name = name;
		this.surname = surname;
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

	public void setSurname(String surname) {
		this.surname = surname;
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

}
