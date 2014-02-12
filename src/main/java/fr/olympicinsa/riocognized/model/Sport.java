package fr.olympicinsa.riocognized.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Collection;

@Entity
@Table(name="SPORT")
public class Sport {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private String name;

    @OneToMany(mappedBy="sport")
    private Collection<Athlete> athletes;

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
    }

    public Collection<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(Collection<Athlete> athletes) {
        this.athletes = athletes;
    }

}
