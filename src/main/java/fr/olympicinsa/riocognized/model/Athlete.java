package fr.olympicinsa.riocognized.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Athlete implements Serializable{

	@Id
	private Long id;
	@Column(name="LAST_NAME")
	private String name;
	@Column(name="FIRST_NAME")
	private String surname;
	
	private String content;
	
    @ManyToOne
    @JoinColumn(name="ID_SPORT")
	private Sport sport;

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
