import java.util.Scanner;
import java.io.*;
import java.security.SecureRandom;
/**
 * This class has a method to read a file of words and fills an array with the words. The class has another method
 * to randomly choose one of the words from the array.
 * @author Nathan Butler
 * @version 1.0
 * Programming Project 1 - Dictionary Class
 * 1/31/2021
 * Spring 2021
 */
public class Dictionary{
	private String [] wordList = new String[213];
	private int CurrentCard;
	private SecureRandom randomNumbers = new SecureRandom();
	 
	/**
	 * Constructor that calls the readFile method to use.
	 * @param fileName - the path to the file full of the words for the game
	 * @throws FileNotFoundException
	 */
	public Dictionary(String fileName) throws FileNotFoundException{
		readFile(fileName);
	}//end constructor
	
	/**
	 * Method takes the file of words and fills the words into a string array.
	 * @param fileName - the path to the file full of the words for the game
	 * @throws FileNotFoundException
	 */
	private void readFile(String fileName) throws FileNotFoundException{
		Scanner wordFile = new Scanner(new File("wordlist.txt"));
		int count=0;
		while(wordFile.hasNext()) {
			wordList[count]=wordFile.nextLine();
			wordList[count].trim(); //gets rid of whitespace
			count++;
		}
	}//end method
	
	/**
	 * Method randomly chooses a word from the array to use in the game.
	 * @return random word from the array
	 * @throws FileNotFoundException
	 */
	public String chooseWord() throws FileNotFoundException{
		readFile("wordlist.txt");
		CurrentCard = randomNumbers.nextInt(wordList.length);
		return wordList[CurrentCard];
	}//end method
	
}//end class
