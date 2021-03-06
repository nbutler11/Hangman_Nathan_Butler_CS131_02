import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * This class has methods to play the game Hangman. One method instantiates the dictionary class. Another class
 * writes your overall record in a file. Another method calls that record to use and prints it. Another method
 * is the main gameplay of the game by using random words and guessing letters to find the word.
 * @author Nathan Butler
 * @version 1.0
 * Programming Project 1 - Hangman class
 * 1/31/2021
 * Spring 2021
 */
public class Hangman{
	private int wins;
	private int losses;
	private String currentWord;
	private Dictionary dictionary;
	
	Scanner in = new Scanner(System.in);
	
	/**
	 * Constructor instantiates the dictionary class with the word list
	 * @throws FileNotFoundException
	 */
	public Hangman() throws FileNotFoundException {
		dictionary = new Dictionary("wordlist.txt");
	}//end constructor
	
	/**
	 * Reads a file that tells your overall record in the game
	 * @throws IOException
	 */
	private void loadWL() throws IOException{
		Scanner fileRead = new Scanner(new File("wlrecord.txt"));
		int wins = fileRead.nextInt();
		int loss = fileRead.nextInt();
		System.out.println("You have a total of " + wins + " wins and " + loss + " losses.");
	}//end method
	
	/**
	 * Writes in a file your overall record in the game
	 * @throws FileNotFoundException
	 */
	private void writeWL() throws FileNotFoundException{
		Scanner fileRead = new Scanner(new File("wlrecord.txt")); 
		String winsOverall = fileRead.nextLine();
		int winAll = Integer.parseInt(winsOverall) + wins;
		int lossOverall = fileRead.nextInt() + losses;
		PrintWriter outFile = new PrintWriter("wlrecord.txt");
		outFile.println(winAll);
		outFile.println(lossOverall);
		outFile.close();
	}//end method
	
	/**
	 * Method implements the gameplay with asking user if they want to play. Then allowing them 5 guesses to 
	 * find the randomly selected word. Tells them their record at the end of the game. 
	 * @throws IOException
	 */
	public void playGame() throws IOException{
		System.out.print("Would you like to play Y/N?");
		String play = in.next(); //answer of the user
		ArrayList<String> GuessedLetters = new ArrayList<>(); //letters guessed already
		int roundWin = 0; //wins this round
		int roundLoss = 0; //losses this round
		losses = 0; //losses overall
		wins = 0; //wins overall
				
		while(play.equalsIgnoreCase("y")) { //user wants to play the game
			currentWord = dictionary.chooseWord(); //word need to guess
			String mysteryWord = ""; //the part of the word they can see
			char [] letters = new char[currentWord.length()]; //the letters in mysteryWord
			GuessedLetters.clear();
			int guesses=5; //wrong answers
			String enteredChar; //guessed letter
			for(int count=0; count < letters.length; count++) { //creates blank word
				letters[count]='-';
			}	
			mysteryWord = mysteryWord.copyValueOf(letters, 0, letters.length);
			
			while(guesses>0 && !mysteryWord.contentEquals(currentWord)) { //user is still alive and searching
				System.out.println("\n\nYou have " + guesses + " incorrect guesses left");
				System.out.println(mysteryWord);//show part of word they have guessed
				System.out.print("What is your guess?");
				enteredChar=in.next(); //letter guessed
				enteredChar.toLowerCase(); //all words are in lower case 
			
				if(enteredChar.length()>1) 
					enteredChar = enteredChar.substring(0 , 1); //only takes the letter in case of space added
			
				if(!GuessedLetters.contains(enteredChar)) { //checking to see if letter has been guessed
					if(currentWord.contains(enteredChar)) { //checking to see if letter is in the word
						int index = currentWord.indexOf(enteredChar);
						while(index >=0) {
							letters[index] = enteredChar.charAt(0);
							index = currentWord.indexOf(enteredChar, index+1);//replace dash with letter guessed
						}//end while
					}//end if contains letter guessed
					else
						guesses--;
				}//end if letter hasn't been guessed
				
				else if(GuessedLetters.contains(enteredChar))//letter has already been guessed
					System.out.println("\nYou have already guessed that letter.");
				
				mysteryWord = mysteryWord.copyValueOf(letters, 0, letters.length); //show the new word working with
				GuessedLetters.add(enteredChar);//add letter guessed to list of letters guessed
					
			}//end while guess>0
			
			if(mysteryWord.contentEquals(currentWord)) { //user guessed word
				System.out.println("\n" + mysteryWord);
				System.out.println("You won");
				roundWin++;
				wins++;
			}
			
			if(guesses==0) { //user ran out of guesses
				System.out.println("\n\nYou are out of guesses! You lost!");
				roundLoss++;
				losses++;
			}
			
			writeWL(); //add wins and losses to overall record
			System.out.print("\nWould you like to play again Y/N?");
			play = in.next(); 
		}//end while gamePlay = yes
		
		System.out.println("\nYou had " + roundWin + " wins and " + roundLoss + " losses this round.");
		loadWL(); //overall record
	}//end playGame method
	
}//end class
