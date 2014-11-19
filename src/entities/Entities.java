package entities;

public enum Entities {
	OTHER("O"),PERSON("PER"),ORGAINIZATION("ORG"),TIME("T"),COUNTRY("C");
	
	String entity;
	
	Entities(String entity) {
		this.entity = entity;
	}
	
	public String toString() {
		return entity;
	}
	
	public static Entities getEntity(String type) {
		switch(type) {
		case "O": return Entities.OTHER;
		case "PER": return Entities.PERSON;
		case "ORG": return Entities.ORGAINIZATION;
		case "T": return Entities.TIME;
		case "C": return Entities.COUNTRY;
		default: return Entities.OTHER;
		}
	}
	
}
