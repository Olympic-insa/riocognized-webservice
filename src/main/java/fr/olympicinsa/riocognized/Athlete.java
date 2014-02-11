package fr.olympicinsa.riocognized;

public class Athlete {

    private long id;
    private final String name;
    private final String surname;
    private final String content;
    
    public Athlete(long id, String name, String surname, String content) {
        this.id = id;
        this.content = content;
        this.name = name;
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSurname() {
        return surname;
    }

	public void setId(long id) {
		this.id = id;	
	}

}
