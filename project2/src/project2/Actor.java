package project2;

public class Actor {
	
	//data field
	
	private String actorName;
	
	//constructor
	
	public Actor (String name){
		////Check if the parameter is valid
		if(name == null || name == "") {
			throw new IllegalArgumentException("Actor name cannot be empty or null");
		}
		
		this.actorName = name;
	}
	
	//getter method
	public String getName() {
		return actorName;
	}
	
}
