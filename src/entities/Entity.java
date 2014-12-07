package entities;

/**
 * The Enum Entity.
 */
public enum Entity {
	
	/** The other. */
	OTHER("O"),
	/** The person. */
	PERSON("PER"),
	/** The orgainization. */
	ORGAINIZATION("ORG"),
	/** The time. */
	TIME("T"),
	/** The country. */
	COUNTRY("C");
	
	/** The entity. */
	String entity;
	
	/**
	 * Instantiates a new entity.
	 *
	 * @param entity the entity
	 */
	Entity(String entity) {
		this.entity = entity;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return entity;
	}
	
	/**
	 * Gets the entity.
	 *
	 * @param type the type
	 * @return the entity
	 */
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
