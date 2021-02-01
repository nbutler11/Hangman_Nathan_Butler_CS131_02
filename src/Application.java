import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class drives the Hangman class and dictionary class by instantiating the hangman class and calling the
 * playGame method.
 * @author Nathan Butler
 * @version 1.0
 * Programming Project 1 - Application Class
 * 1/31/2021
 * Spring 2021
 */
public class Application {

	public static void main(String[] args) throws IOException {
		Hangman hang1 = new Hangman();
		hang1.playGame();
		
	}//end main

}//end class
