package project6;

/**
 * This class represents the actor of a movie.
 * @author Tianze Lin 
 * @version 10/20/2018
 */

public class Actor {
	
	//data field
	
	private String actorName;
	
	//constructor
	
	/**
     * Constructs an actor
     * @param name the name of the actor
     * @throw IllegalArugumentException if the name is empty or null
     */
	public Actor (String name) throws IllegalArgumentException {
		////Check if the parameter is valid
		if(name == null || name.equals("")) {
			throw new IllegalArgumentException("Actor name cannot be empty or null");
		}
		
		this.actorName = name;
	}
	
	//getter method
	/**
     * Returns the name of this actor
     * @return the name of this actor
     */
	public String getName() {
		return actorName;
	}
	
}
