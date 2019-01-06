package project2;

import java.util.ArrayList;
import java.util.Collections;


public class MovieList extends ArrayList<Movie> {
	
	public MovieList getMatchingTitles ( String keyword ) {
		//Check if the parameters are valid
		if (keyword == null || keyword.equals("")) {
			return null;
		}
		
		MovieList list = new MovieList();
		
		for (Movie m : this) {
			if (m.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
				list.add(m);
			}
		}
		if(list.size() == 0) {
			return null;
		}
	    
		//sort the list
		Collections.sort(list);
		
		return list;
	}
	
	public MovieList getMatchingActor (String keyword) {
		//Check if the parameters are valid
		if (keyword == null || keyword.equals("")) {
			return null;
		}
		
		MovieList list = new MovieList();
		for (Movie m : this) {
			if (m.getFirstActor().getName().toLowerCase().contains(keyword.toLowerCase())) {
				list.add(m);
			}else if (m.getSecondActor() != null && 
					m.getSecondActor().getName().toLowerCase().contains(keyword.toLowerCase())) {
				list.add(m);
			}else if (m.getThirdActor() != null && 
					m.getThirdActor().getName().toLowerCase().contains(keyword.toLowerCase())) {
				list.add(m);
			}
		}
		if(list.size() == 0) {
			return null;
		}
		
		//sort the list
		Collections.sort(list);
		
		return list;
	}
	
	//Override the toString method
	
	@Override
	public String toString() {
		String str = "";
		for(int i=0; i<this.size(); i++) {
			if(i == 0) {
				str += this.get(i).getTitle();
				continue;
			}
			str += "; ";
			str += this.get(i).getTitle();
		}
		return str;
	}
	
}
