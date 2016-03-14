package hangman;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileReadWriter {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	ArrayList<Players> myArr = new ArrayList<Players>();

	// Open file to write
	public void openFileToWite() {
		try // open file
		{
			output = new ObjectOutputStream(new FileOutputStream("players.ser",
					true));
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		}
	}

	// add records to file
	public void addRecords(int scores, String name) {
		Players players = new Players(name, scores); // object to be written to
														// file
		try { // output values to file
			output.writeObject(players); // output players
		} catch (IOException ioException) {
			System.err.println("Error writing to file.");
			return;
		}
	}
	
	// Closing file from writing
	public void closeFileFromWriting() {
		try // close file
		{
			if (output != null){
			  output.close();
			}
		} catch (IOException ioException) {
			//show error
			System.err.println("Error closing file.");
			
			//exit
			System.exit(1);
			
		}
	}

	// Open file to read
	public void openFiletoRead() {
		try {
			if(true) {
				input = new ObjectInputStream(new FileInputStream("players.ser"));
			}	
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		}
	}

	// Reading records
	public void readRecords() {
		Players records;

		try // input the values from the file
		{
			Object obj = null;

			while (!(obj = input.readObject()).equals(null)) {
				if (obj instanceof Players) {
					records = (Players) obj;
					myArr.add(records);
					System.out.printf("DEBUG: %-10d%-12s\n",
					records.getScores(), records.getName());	
				}
			}
		} // end try
		catch (EOFException endOfFileException) {
			return; // end of file was reached
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Unable to create object.");
		} catch (IOException ioException) {
			System.err.println("Error during reading from file.");
		}
	}
	
	// Close file from reading
	public void closeFileFromReading() {
		tryCloseFileFromReading();
	}

	// Print and sort scoreboard
	public void printAndSortScoreBoard() {
		Players temp;
		int n = myArr.size();
		for (int pass = 1; pass < n; pass++) {
			for (int i = 0; i < n - pass; i++) {
				if (myArr.get(i).getScores() > myArr.get(i + 1).getScores()) {
					
					temp = myArr.get(i);
					myArr.set(i, myArr.get(i + 1));
					myArr.set(i + 1, temp);
		
				}
			}
		}
		

		System.out.println("Scoreboard:");
		for (int i = 0; i < myArr.size(); i++) {
			System.out.printf("%d. %s ----> %d", i, myArr.get(i).getName(), myArr.get(i).getScores());
		}
		

	}

		// Try closing file from reading
	private void tryCloseFileFromReading()
	{
		try {
			if (input != null){
				input.close();
			}
			{
				// exit
				System.exit(0);
			}
		} catch (IOException ioException) {
			System.err.println("Error closing file.");
			System.exit(1);
		}
	}

	// ?????
	private void nop()
	{
		System.out.println(true);
	}

	// Reads old records
	private void oldReadRecords()
	{
		readRecords();
		readRecords();
		readRecords();
		readRecords();
		readRecords();
	}

}
