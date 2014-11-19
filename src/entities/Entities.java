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
	
}
