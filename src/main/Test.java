/*
 * Triangle Authenticator
 * @author Justin Lyon
 * 4/18/2014
 * 
 * Developed on assignment from CardinalCommerce.
 */

package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import triangle.Triangle;


public class Test {
	
	//MAIN METHOD
	public static void main(String[] args) throws IOException {
		
		//VARIABLES
		/*
		 * boolean done used to escape menu while loop.
		 * int menu used to manage user input.
		 */
		boolean done = false;
		int menu;
				
		//OBJECTS
		/*
		 * File filename carries Triangle.txt to Scanner and
		 * Printwriter objects.
		 * Scanner scanFile used to handle file input.
		 * Scanner scanCon used to handle console input.
		 * ArrayList of Triangle objects to handle file io.
		 */
		File fileName = new File("Triangle.txt");
		Scanner scanFile = new Scanner(fileName);
		Scanner scanCon = new Scanner(System.in);
		List<Triangle> triangleList = new ArrayList<Triangle>();
		
		do {
			//Menu 
			System.out.print("-Triangle Main Menu-"
					+ "\n1) Enter input from the console: "
					+ "\n2) Read input from a file: "
					+ "\n3) Exit"
					+ "\n>");
			
			//Menu choice input
			while(!scanCon.hasNextInt()) {
				System.out.print("Input a choice, 1-3: ");
				scanCon.next();
			}
			menu = scanCon.nextInt();
			
			//menu logic
			switch (menu) {
			/*
			 * CASE 1 
			 * Accept input from console. 
			 * 		-Scanner input streams may not re-open. 
			 * 		Pass Scanner scanCon to readConsole().
			 * Pass input to object.
			 * Output object back to console.
			 */
			case 1: 	Triangle myTriangle = new Triangle(readConsole(scanCon));
						System.out.println(consoleOut(myTriangle));
						fileOut(myTriangle, fileName);
						break;
			/*
			 * CASE 2
			 * Accept input from a file. Read into the ArrayList. Process it. 
			 * 		-Scanner input streams may not re-open.
			 * 		Pass Scanner scanFile to readFile().
			 * Print to the Console. 
			 * Print back to the File.
			 */
			case 2: 	triangleList.addAll(readFile(scanFile));
						for (int j = 0; j < triangleList.size(); j++) {
							System.out.println(consoleOut(triangleList.get(j)));
						}
						fileOut(triangleList, fileName);
						
						break;
			/*
			 * CASE 3 
			 * Exit. 
			 * Change done to true. 
			 * Escape menu do/while
			 */
			case 3: 	done = true;
						break;
			/*
			 * default CASE
			 * Scanner.nextInt() certifies integer input. 
			 * Default Case forces valid menu input, 1-3.
			 */
			default: 	System.out.println("Invalid entry. Please enter a value from 1 to 3.");
			}
			
		}while (!done);
		//close Scanner Console input stream
		scanCon.close();
		//close Scanner File io stream
		scanFile.close();
	}
	
	//STATIC METHODS FOR INPUT
	
	//read input from the console
	/*
	 * Input three int from the console. Values are stored
	 * in an array to be passed to a Triangle object for later 
	 * manipulation. 
	 * Scanner object.nextInt() inherently catches non-int values.
	 * Any 0 value is caught by it's own while loop.
	 * Finally the three sides are passed to a triangle object.
	 * If these three sides cannot make a triangle, 
	 * readConsole() enters recursion - start input again from start. 
	 */
	public static int[] readConsole(Scanner scan) {
		
		int[] input = new int[3];

		//read input 
		for(int j = 0; j < 3; j++) {
			System.out.print("Input a side: ");
			
			//Initial input. Inherently checks for integer values.
			while(!scan.hasNextInt()) {
				System.out.print("Input an integer value, please: ");
				scan.next();
			}
			input[j] = scan.nextInt();
			
			//if value is 0, ask for input again. 
			while (input[j] == 0){
				System.out.print("You entered a zero. Please enter a non-zero int: ");
				//Check for Integer Input
				while(!scan.hasNextInt()) {
					System.out.print("Input an integer value, please: ");
					scan.next();
				}
				input[j] = scan.nextInt();
			}
		}
		
		//sort input into ascending order
		//final index, index[2], is passed to instance variable Triangle.object.c, the hypotenuse
		Arrays.sort(input);
		
		//pass input to Triangle object to check for valid sides
		Triangle myTriTest = new Triangle(input[0], input[1], input[2]);
		
		//if sides do not make a triangle, readConsole() enters recursion 
		if(!myTriTest.isTriangle()){
			System.out.println("The sum of any two sides of a triangle will be greater than the third."
					+ "\nYour sides do not make a triangle. "
					+ "\nPlease enter your sides again.");
			return readConsole(scan);
		}
		return input;
	}
	
	//read input from a file
	/*
	 * Data in the file must follow the proper format. 
	 * Format is three integer values per line. The program 
	 * reads three integers per line into an ArrayList of 
	 * Triangle objects. Each Triangle object stores the integer 
	 * values in its instance variables. 
	 * Default delimiters are spaces between int values, and new 
	 * lines between Triangle objects.  
	 * This code makes no attempt to handle errors due to text 
	 * file format. 
	 */
	public static List<Triangle> readFile(Scanner scan) {
		
		//temporary storage of scanned int for sort before passing to Triangle object
		int[] tempSides = new int[3];
		//ArrayList of Triangle objects manages file io
		List<Triangle> tempTriList = new ArrayList<Triangle>(); 
		
		//while a next line exists, and an int exists, read three int values from the line.
		while (scan.hasNextLine()) {
			while (scan.hasNextInt()) {
				for (int j = 0; j < tempSides.length; j++) {
					tempSides[j] = scan.nextInt();
				}
				//sort in ascending order
				Arrays.sort(tempSides);
				//create a new index in the ArrayList of Triangle Objects with the parameter of tempSides
				tempTriList.add(new Triangle(tempSides));
			}
			scan.nextLine();
		}
		
		/*
		 * Imperfect text format error handling. 
		 * Code is currently redundant and irrelevant due to 
		 * explicit data entry from the console. 
		 * 
		 * Given the nature of this exercise, I decided to leave this
		 * to show my work.
		 * 
		 * Check the file input for errors. 
		 * File input exists line by line per index of tempTriList, an ArrayList.
		 * If erroneous data exists, zero values, or 
		 * any Triangle object index that does not have adequate sides for 
		 * a triangle - then delete the index and shunt remaining indexes
		 * to the left. 
		 */
		for (int j = 0; j < tempTriList.size(); j++) {
			if (tempTriList.get(j).getSideA() == 0 ||
				tempTriList.get(j).getSideB() == 0 ||
				tempTriList.get(j).getSideC() == 0) {
					tempTriList.remove(j);
			}
			if (!tempTriList.get(j).isTriangle()) {
				tempTriList.remove(j);
			}
		}
		
		return tempTriList;
	}	
	
	//STATIC METHODS FOR OUTPUT
	
	/*
	 * consoleOut() takes parameter Triangle object.
	 * Using the object to output what kind of triangle
	 * our previous input of sides can make. 
	 */
	public static String consoleOut(Triangle object) {
		
		//return existing sides of the triangle
		System.out.println("\nYour triangle has sides: "
				+ "\n" + object.toString());
		
		//tell us what kinda of triangle we have
		if (object.isEquilateral())
			return "This triangle is an equilateral triangle.\n";
		if (object.isIsosceles())
			return "This triangle is an isosceles triangle.\n";
		if (object.isScalene())
			return "This triangle is a scalene triangle.\n";
		else
			return "Error, how did you do that?\n";
	}
	
	/*
	 * Write the ArrayList to the file. Overwrites existing file.
	 */
	public static void fileOut(List<Triangle> tList, File fname) throws FileNotFoundException {
		
		PrintWriter writer = new PrintWriter(fname);

		for (int j = 0; j < tList.size(); j++) {
			writer.println(tList.get(j).toString());
		}
		writer.close();
	}
	
	/*
	 * Append an object to the file. 
	 */
	public static void fileOut(Triangle t, File fname) throws IOException {
		
		//parameters of fname and true append any println() to the given file. 
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fname, true)));
		
		writer.println(t.toString());
		
		/*
		 * .flush() forces a save to the .txt so we can continue 
		 * making changes to the file in real time. 
		 */
		writer.flush(); 
		writer.close();
	}
}