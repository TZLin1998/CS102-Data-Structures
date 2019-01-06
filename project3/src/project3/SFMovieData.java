package project3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is the class that should contain the main method.  
 * It is responsible for opening and reading the data files, 
 * obtaining user input, performing some data validation and handling all errors that may occur.
 * @author Tianze Lin 
 * @version 10/20/2018
 */

public class SFMovieData {

	/**
     * The main method.
     * It is responsible for opening and reading the data files, 
     * obtaining user input, performing some data validation and handling all errors that may occur.
     * @param args command-line arguments
     */
	public static void main(String[] args) {

		//verify that the command line argument exists 
		if (args.length == 0) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}
		
		//make sure there is only one command line argument
		String commandLine = "";
		for(int i=0; i<args.length;i++) {
			commandLine += args[i];
			if(i != args.length -1) {
				commandLine += " ";
			}
		}
		
		
		File fileDir = new File(commandLine);
		if (!fileDir.exists()){
			System.err.println("Error: the file "+fileDir.getAbsolutePath()+" does not exist.\n");
			System.exit(1);
		}
		if (!fileDir.canRead()){
			System.err.println("Error: the file "+fileDir.getAbsolutePath()+
											" cannot be opened for reading.\n");
			System.exit(1);
		}
		
		BufferedReader in;
		try {
			in = new BufferedReader(
				new InputStreamReader(new FileInputStream(fileDir), "UTF8"));			        
			String str;      
			
			MovieList list = new MovieList(); 
			String line = null;   
			Movie current = null;
			ArrayList<String> sw = new ArrayList<String>();
			
			//Read the file line by line
			while ((str = in.readLine()) != null) {
				try { 
					sw = splitCSVLine(str); 
				}catch (NoSuchElementException ex ) {
					//caused by an incomplete or miss-formatted line in the input file
					System.err.println(line);
					continue; 	
				}
				
				try {
					//create different data fields of a Movie Object
					String title = sw.get(0);
					int year = Integer.parseInt(sw.get(1));
					Location loc = new Location (sw.get(2), sw.get(3));
					String director = sw.get(6);
					String writer = sw.get(7);
					Actor a1 = new Actor(sw.get(8));
					if(sw.get(9) != null && sw.get(10) != null) {
						Actor a2 = new Actor(sw.get(9));
						Actor a3 = new Actor(sw.get(10));
						current = new Movie (title, year, director, writer, a1, a2, a3);
					}else {
						//if there is no second actor or third actor
						current = new Movie (title, year);
						current.setFirstActor(a1);
						if(sw.get(9) != null) {
							Actor a2 = new Actor(sw.get(9));
							current.setSecondActor(a2);
						}
						current.setDirector(director);
						current.setWriter(writer);
					}
					//if the movie is not in the list, add it
					if(list.getMatchingTitles(title) == null) {
						current.addLocation(loc);
						list.add(current);
					}else {
						//if the movie may be in the list, iterate through the list
						Iterator<Movie> listIterator = list.iterator();
						while(listIterator.hasNext()) {
							Movie m = listIterator.next();
							if (m.getTitle().toLowerCase().equals(title.toLowerCase())) {
								for(int j=0; j<m.getLocations().size() ;j++) {
									//add previous locations to current movie
									current.addLocation(m.getLocations().get(j));
								}
								//remove the previous movie 
								list.remove(m);
							}
						}
						//add current location to the current movie
						current.addLocation(loc);
						//add current movie to the list
						list.add(current);
					}
					
				}catch (IllegalArgumentException ex ) {
					//ignore this exception and skip to the next line 
				}catch (IndexOutOfBoundsException ex) {
					//ignore this exception and skip to the next line
				}
				
			}
		
			
			//interactive mode: 
			Scanner userInput  = new Scanner (System.in ); 
			String userValue = "";
			
			System.out.println();
			System.out.println("Search the database by matching keywords to titles or actor names.");
			System.out.println("  To search for matching titles, enter");
			System.out.println("    title KEYWORD");
			System.out.println("  To search for matching actor names, enter");
			System.out.println("    actor KEYWORD");
			System.out.println("  To finish the program, enter");
			System.out.println("    quit");
			System.out.println("\n\n");
			
			
			do {
				
				System.out.println();
				System.out.println();
				System.out.println("Enter your search query:\n" );
				//get value of from the user 
				userValue = userInput.nextLine();
				
				if (!userValue.equalsIgnoreCase("quit")) { 
					String[] input = userValue.split(" ", 2);
					
					MovieList userList = new MovieList();
					//check the type of query from user
					if(input[0].equalsIgnoreCase("title")) {
						userList = list.getMatchingTitles(input[1]);
					}else if(input[0].equalsIgnoreCase("actor")) {
						userList = list.getMatchingActor(input[1]);
					}else {
						System.out.println("\nThis is not a valid query. Try again.");
						continue;
					}
					if ( userList.isEmpty()) {
						System.out.println("\nNo matches found. Try again.");
					}else {
						//print the results
						Iterator<Movie> listIterator = userList.iterator();
						while(listIterator.hasNext()) {
							System.out.println(listIterator.next().toString());
						}
					}
				}
				
			} while (!userValue.equalsIgnoreCase("quit")); 
			userInput.close();
			
		}catch (IOException e) {
			System.err.println("An IOException was caught :"+e.getMessage());
			System.exit(1);
		}	
		
	}

	//The spiltCSVLine method is from the project specification
	/**
     * Given a row (a single line) from the input data set,
     * it returns an ArrayList<String> object containing all the individual entries from that row.
     * @author Professor Joanna Klukowska
     * @param textLine a row (a single line) from the input data set
     */
	public static ArrayList<String> splitCSVLine(String textLine) {
		
		if(textLine == null) {
			return null;
		}
		ArrayList<String> entries =new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry = false;
		
		// iterate over all characters in the textLine
		for(int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			// handle smart quotes as well as regular quotes
			if(nextChar == '"' || nextChar == '\u201C' || nextChar == '\u201D') {
				
				// change insideQuotes flag when nextChar is a quote
				if(insideQuotes) {
					insideQuotes = false;
					insideEntry =false;
				}else{
					insideQuotes = true;
					insideEntry = true;
				}
			}else if(Character.isWhitespace(nextChar)) {
				if( insideQuotes || insideEntry ) {
					// add it to the current entry
					nextWord.append( nextChar );
				}else{ 
					// skip all spaces between entries
					continue;
				}
			}else if( nextChar == ',') {
				if(insideQuotes){ 
					// comma inside an entry
					nextWord.append(nextChar);
				}else{ 
					// end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}else{
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			}
		}
		
		// add the last word ( assuming not empty )
		// trim the white space before adding to the list
		if(!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}
		
		int length = textLine.length();
		if (textLine.charAt(length-1) == ',') {
			entries.add(null);
		}
		return entries;
	}

}
