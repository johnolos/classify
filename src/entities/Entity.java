package entities;

public enum Entity {
	OTHER("O"),PERSON("PER"),ORGAINIZATION("ORG"),TIME("T"),COUNTRY("C");
	
	String entity;
	
	Entity(String entity) {
		this.entity = entity;
	}
	
	public String toString() {
		return entity;
	}
	
	public static Entity getEntity(String type) {
		switch(type) {
		case "O": return Entity.OTHER;
		case "PER": return Entity.PERSON;
		case "ORG": return Entity.ORGAINIZATION;
		case "T": return Entity.TIME;
		case "C": return Entity.COUNTRY;
		default: return Entity.OTHER;
		}
	}
	
}
