package fr.olympicinsa.riocognized.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name="ATHLETE")
public class Athlete implements Serializable{

	@Id
        @GeneratedValue
	private Long id;
	@Column(name="LAST_NAME")
	private String name;
	@Column(name="FIRST_NAME")
	private String surname;
	@Column(name="CONTENT")
	private String content;
	
        @ManyToOne(fetch = FetchType.LAZY)
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
