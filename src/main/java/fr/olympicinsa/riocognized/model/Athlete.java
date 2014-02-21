package fr.olympicinsa.riocognized.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="ATHLETE")
@XmlRootElement
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
	@Column(name="COUNTRY")
	private String country;
        @Column(name="SPORT")
	private String sport;
        @Column(name="AGE")
	private Integer age;
        
        @OneToOne(mappedBy="athlete", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Image image;
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name="ID_SPORT")
//	private Sport sportID;

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
}
