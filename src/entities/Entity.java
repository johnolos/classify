package entities;

// TODO: Auto-generated Javadoc
/**
 * The Enum Entity.
 */
public enum Entity {
	
	/** The other. */
	OTHER("O"),
	/** The person. */
	PERSON("P"),
	/** The orgainization. */
	ORGAINIZATION("ORG"),
	/** The time. */
	TIME("T"),
	/** The country. */
	LOCATION("L");
	
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
		case "P": return Entity.PERSON;
		case "ORG": return Entity.ORGAINIZATION;
		case "T": return Entity.TIME;
		case "L": return Entity.LOCATION;
		default: return Entity.OTHER;
		}
	}
}
