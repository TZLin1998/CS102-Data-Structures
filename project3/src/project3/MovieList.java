package project3;

import java.util.Iterator;

/**
 * This class represent a MovieList, which is a subclass of LinkedList.
 * @author Tianze Lin 
 * @version 10/20/2018
 */

public class MovieList extends LinkedList<Movie> {
	
	/**
     * Search this MovieList. 
     * Return a MovieList that contains all the Movies in this MovieList whose titles contain the keyword. 
     * @param keyword the string which used for searching movies
     * @return a MovieList of all the movies in the current MovieList whose title contains the given keyword
     */
	public MovieList getMatchingTitles ( String keyword ) {
		
		//Check if the parameters are valid
		if (keyword == null || keyword.equals("")) {
			return null;
		}
		
		MovieList list = new MovieList();
		//iterate through the current list
		Iterator<Movie> listIterator = this.iterator();
		while(listIterator.hasNext()) {
			Movie c = listIterator.next();
			if (c.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
				list.add(c);
			}
		}
	    
		//sort the list
		list.sort();
		
		return list;
	}
	
	/**
     * Search this MovieList. 
     * Return a MovieList that contains all the Movies in this MovieList whose actors' names contain the keyword. 
     * @param keyword the string which used for searching movies
     * @return a MovieList of all the movies in the current MovieList whose actors' names contains the given keyword
     */
	public MovieList getMatchingActor (String keyword) {
		
		//Check if the parameters are valid
		if (keyword == null || keyword.equals("")) {
			return null;
		}
		
		MovieList list = new MovieList();
		//iterate through the current list
		Iterator<Movie> listIterator = this.iterator();
		while(listIterator.hasNext()) {
			Movie c = listIterator.next();
			if (c.getFirstActor().getName().toLowerCase().contains(keyword.toLowerCase())) {
				list.add(c);
			}else if (c.getSecondActor() != null && 
					c.getSecondActor().getName().toLowerCase().contains(keyword.toLowerCase())) {
				list.add(c);
			}else if (c.getThirdActor() != null && 
					c.getThirdActor().getName().toLowerCase().contains(keyword.toLowerCase())) {
				list.add(c);
			}
		}
		
		//sort the list
		list.sort();
		
		return list;
	}
	
	//Override the toString method
	/**
     * Returns a string representation of this MovieList.  
     * The string representation consists of a list of the collection's elements' titles in the 
     * order they are returned by its iterator.  
     * Adjacent elements are separated by the characters "; " (semicolon and space).
     * @return Returns a string representation of this collection.
     */
	@Override
	public String toString() {
		String str = "";
		Iterator<Movie> listIterator = this.iterator();
		while(listIterator.hasNext()) {
			str += "; ";
			str += listIterator.next().getTitle();
		}
		//remove first two characters "; "
		return str.substring(2);
	}
	
}
