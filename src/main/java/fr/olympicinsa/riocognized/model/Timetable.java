package fr.olympicinsa.riocognized.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TIMETABLE")
public class Timetable implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Sport sport;
    private String descritpion;
    @ManyToOne
    private Position position;
    @Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
    
	public Date getStartDate() {
		return this.startDate;
	}
 
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
    
    	public Date getEndDate() {
		return this.endDate;
	}
 
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    
    public Long getId() {
        return id;
    }

    public Sport getSport() {
        return sport;
    }

    public String getDescription() {
        return descritpion;
    }

    public void setDescription(String descritpion) {
        this.descritpion = descritpion;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
}
