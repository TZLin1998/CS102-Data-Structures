package project3;

/**
 * This class represents the location of a movie.
 * @author Tianze Lin 
 * @version 10/20/2018
 */

public class Location {
	
	//data fields
	private String LocationName;
	private String funFact;
	
	//constructor
	/**
     * Constructs a location of the movie.
     * @param namethe name of this location
     * @param funFact the fun fact of this location
     * @throws IllegalArgumentException if the name of this location is empty or null
     */
	public Location(String name, String funFact) throws IllegalArgumentException {
		//Check if the parameters are valid
		if(name == null || name.equals("")) {
			throw new IllegalArgumentException("Location name cannot be empty or null");
		}
		
		this.LocationName = name;
		this.funFact = funFact;
	}
	
	//getter methods
	
	/**
     * Returns the name of this location.
     * @return the name of this location
     */
	public String getName() {
		return LocationName;
	}
	
	/**
     * Returns the fun fact of this location.
     * @return the funFact of this location
     */
	public String getFunFact() {
		return funFact;
	}
}
