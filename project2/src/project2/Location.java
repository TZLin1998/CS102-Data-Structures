package project2;

public class Location {
	
	//data fields
	private String LocationName;
	private String funFact;
	
	//constructor
	public Location(String name, String funFact) {
		//Check if the parameters are valid
		if(name == null || name.equals("")) {
			throw new IllegalArgumentException("Location name cannot be empty or null");
		}
		
		this.LocationName = name;
		this.funFact = funFact;
	}
	
	//getter methods
	
	public String getName() {
		return LocationName;
	}
	
	public String getFunFact() {
		return funFact;
	}
}
