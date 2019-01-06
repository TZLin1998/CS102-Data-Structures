package project2;

import java.util.*;

public class Movie implements Comparable<Movie>{
	
	//data fields
	private String title;
	private int year;
	private String writer, director;
	private Actor actor1, actor2, actor3;
	private ArrayList<Location> locations;
	
	//methods
	
	//make the default constructor private, so other class cannot call it
	private Movie() {
        throw new IllegalArgumentException();
    }
	
	//constructors
	
	public Movie (String title, int year) {
		//Check if the parameters are valid
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException("Movie title cannot be empty or null");
		}else if(year < 1900 || year > 2020) {
			throw new IllegalArgumentException("Year should be between 1900 and 2020");
		}
		
		this.locations = new ArrayList();
		this.title = title;
		this.year = year;
		this.writer= "";
		this.director = "";
		this.actor1 = null;
		this.actor2 = null;
		this.actor3 = null;
	}
	
	public Movie (String title, int year, String director, String writer,
		Actor actor1, Actor actor2, Actor actor3){
		//Check if the parameters are valid
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException("Movie title cannot be empty or null");
		}else if(year < 1900 || year > 2020) {
			throw new IllegalArgumentException("Year should be between 1900 and 2020");
		}else if(actor1 == null) {
			throw new IllegalArgumentException("First actor cannot be null");
		}
		
		this.title = title;
		this.year = year;
		this.writer= writer;
		this.director = director;
		this.actor1 = actor1;
		this.actor2 = actor2;
		this.actor3 = actor3;
		this.locations = new ArrayList();
	}
	
	//getter & setter methods
	
	public int getYear() {
		return this.year;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public ArrayList<Location> getLocations(){
		return this.locations;
	}
	
	public void setWriter(String wit) {
		this.writer = wit;
	}
	
	public String getWriter() {
		return this.writer;
	}
	
	public void setDirector(String dic) {
		this.director = dic;
	}
	
	public String getDirector() {
		return this.director;
	}
	
	public void setFirstActor(Actor a) {
		if(a == null) {
			throw new IllegalArgumentException("First actor cannot be null");
		}
		this.actor1 = a;
	}
	
	public void setSecondActor(Actor a) {
		if(a == null) {
			throw new IllegalArgumentException("Second actor cannot be null");
		}
		this.actor2 = a;
	}
	
	public Actor getFirstActor() {
		return this.actor1;
	}
	
	public Actor getSecondActor() {
		return this.actor2;
	}
	
	public Actor getThirdActor() {
		return this.actor3;
	}
	
	//addLocation method
	
	public void addLocation(Location loc) {
		if(loc == null) {
			throw new IllegalArgumentException("Locations cannot be null");
		}
		this.locations.add(loc);
	}
	
	//Override some default methods
	
	@Override
    public int compareTo( Movie other) {
        if (this.year != other.getYear()){
        	return this.year - other.getYear();
        }else {
        	return this.title.compareToIgnoreCase(other.getTitle());
        }
    }
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}else if(!Movie.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
		
		final Movie other = (Movie) obj;
		if(other.getTitle().equalsIgnoreCase(this.title) && other.getYear() == this.year) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		String str = "\n";
		
		str += this.title + " (";
		str += this.year; str += ")";
		
		str += "\n";
		str += "------------------------------------";
		
		str += "\n";
		str += "director"; 
		for(int i=0; i<12;i++) {
			str += " ";
		}
		str += ": "; 
		if(this.director != null) {
			str += this.director;
		}
		
		str += "\n";
		str += "writer"; 
		for(int i=0; i<14;i++) {
			str += " ";
		}
		str += ": ";
		if(this.writer != null) {
			str += this.writer;
		}
		
		str += "\n";
		str += "starring"; 
		for(int i=0; i<12;i++) {
			str += " ";
		}
		str += ": "; 
		if(this.actor1 != null) {
			str += this.actor1.getName();
		}
		if(this.actor2 != null) {
			str += ", ";
			str += this.actor2.getName();
		}
		if(this.actor3 != null) {
			str += ", ";
			str += this.actor3.getName();
			str += ",";
		}
		
		str += "\n";
		str += "filmed on location at:";
		
		for(int i=0; i<locations.size(); i++) {
			str += "\n";
			str += "    ";
			str += this.locations.get(i).getName();
			if(this.locations.get(i).getFunFact() != null && 
					!this.locations.get(i).getFunFact().equals("")) {
				str += " (";
				str += this.locations.get(i).getFunFact();
				str += ")";
			}
		}
		
		return str;
	}
	
	
}
