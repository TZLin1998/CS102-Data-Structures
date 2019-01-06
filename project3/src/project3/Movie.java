package project3;

import java.util.*;

/**
 * This class represents a movie. It implements the Comparable<Movie> interface.
 * @author Tianze Lin 
 * @version 10/20/2018
 */

public class Movie implements Comparable<Movie>{
	
	//data fields
	private String title;
	private int year;
	private String writer, director;
	private Actor actor1, actor2, actor3;
	private ArrayList<Location> locations;
	
	//methods
	
	//constructors
	/**
     * Constructs a movie
     * @param title the title of the movie
     * @param year the year of the movie
     * @throw IllegalArugumentException if the title is empty or null
     * @throw IllegalArugumentException if the year is not in 1900 - 2020
     */
	public Movie (String title, int year) {
		//Check if the parameters are valid
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException("Movie title cannot be empty or null");
		}else if(year < 1900 || year > 2020) {
			throw new IllegalArgumentException("Year should be between 1900 and 2020");
		}
		
		this.locations = new ArrayList<Location>();
		this.title = title;
		this.year = year;
		this.writer= "";
		this.director = "";
		this.actor1 = null;
		this.actor2 = null;
		this.actor3 = null;
	}
	
	/**
     * Constructs a movie
     * @param title the title of the movie
     * @param year the year of the movie
     * @param director the director of the movie
     * @param writer the writer of the movie
     * @param actor1 the first actor of the movie
     * @param actor2 the second actor of the movie
     * @param actor3 the third actor of the movie
     * @throw IllegalArugumentException if the title is empty or null
     * @throw IllegalArugumentException if the year is not in 1900 - 2020
     * @throw IllegalArugumentException if the first actor is null
     */
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
		this.locations = new ArrayList<Location>();
	}
	
	//getter & setter methods
	
	/**
     * Returns the year of this movie
     * @return the year of this movie
     */
	public int getYear() {
		return this.year;
	}
	
	/**
     * Returns the title of this movie
     * @return the title of this movie
     */
	public String getTitle() {
		return this.title;
	}
	
	/**
     * Returns the location(s) of this movie
     * @return the location(s) of this movie
     */
	public ArrayList<Location> getLocations(){
		return this.locations;
	}
	
	/**
     * Set the writer of this movie
     * @param wit the writer of the movie
     */
	public void setWriter(String wit) {
		this.writer = wit;
	}
	
	/**
     * Returns the writer of this movie
     * @return the writer of this movie
     */
	public String getWriter() {
		return this.writer;
	}
	
	/**
     * Set the director of this movie
     * @param dic the director of this movie
     */
	public void setDirector(String dic) {
		this.director = dic;
	}
	
	/**
     * Returns the director of this movie
     * @return the director of this movie
     */
	public String getDirector() {
		return this.director;
	}
	
	/**
     * Set the first actor of this movie
     * @param a the first actor of this movie
     * @throw IllegalArugumentException if the title is empty or null
     */
	public void setFirstActor(Actor a) {
		if(a == null) {
			throw new IllegalArgumentException("First actor cannot be null");
		}
		this.actor1 = a;
	}
	
	/**
     * Set the second actor of this movie
     * @param a the second actor of this movie
     * @throw IllegalArugumentException if the title is empty or null
     */
	public void setSecondActor(Actor a) {
		this.actor2 = a;
	}
	
	/**
     * Set the third actor of this movie
     * @param a the third actor of this movie
     * @throw IllegalArugumentException if the title is empty or null
     */
	public void setThirdActor(Actor a) {
		this.actor3 = a;
	}
	
	/**
     * Returns the first actor of this movie
     * @return the first actor of this movie
     */
	public Actor getFirstActor() {
		return this.actor1;
	}
	
	/**
     * Returns the second actor of this movie
     * @return the second actor of this movie
     */
	public Actor getSecondActor() {
		return this.actor2;
	}
	
	/**
     * Returns the third actor of this movie
     * @return the third actor of this movie
     */
	public Actor getThirdActor() {
		return this.actor3;
	}
	
	//addLocation method
	/**
     * add a location to this movie
     * @param loc the location will be added to the movie
     * @throw IllegalArgumentException if loc is null
     */
	public void addLocation(Location loc) {
		if(loc == null) {
			throw new IllegalArgumentException("Locations cannot be null");
		}
		this.locations.add(loc);
	}
	
	//Override some default methods
	
	/**
	 * Compares this movie with the specified movie for order.
	 * Returns a negative integer, zero, or a positive integer 
	 * as this movie is less than, equal to, or greater than the specified movie.
	 * @param other the movie which is compared with current Movie
	 * @return a negative integer, zero, or a positive integer 
	 * as this movie is less than, equal to, or greater than the specified movie.
	 */
	@Override
    public int compareTo( Movie other) {
        if (this.year != other.getYear()){
        	return this.year - other.getYear();
        }else {
        	return this.title.compareToIgnoreCase(other.getTitle());
        }
    }
	
	/**
	 * Returns true if this Movie is the same as the specified movie
	 * @param other the movie which is compared with current Movie
	 * @return true if this Movie is the same as the specified movie
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}else if(!(obj instanceof Movie)) {
            return false;
        }
		
		final Movie other = (Movie) obj;
		if(other.getTitle().equalsIgnoreCase(this.title) && other.getYear() == this.year) {
			return true;
		}
		return false;
	}
	
	/**
     * Returns a string representation of this movie.  
     * @return Returns a string representation of this movie.
     */
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
