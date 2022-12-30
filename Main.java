/*
Name: Tharshigan
Date: Apr.06,2022
Purpose: To create a program that is outlined in the Cellsweeper Assignment
*/

import java.io.*;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
		//Variables
    int rowInput, colInput, range, deadCellIndex, colGuess, counter = 0;
    String choice="";
    char letter = 'A', rowGuess;
    boolean runAgain = false;

    //Instantiating and declaring array for grid
    char [][] grid;
    int [][] deadCellIndices;

    //Creating scanner object
    Scanner input = new Scanner (System.in);
		
    //Do-while to repeat game 
    do{
      //Resetting runAgain and counter variable to run program features again
      runAgain = false;
      counter = 0;
			
      //Display Title
      System.out.println("\n\t\t\t\t[-~~~~ CellSweeper ~~~~-]\n");
			
			//User inputs the size of grid
      System.out.print("How many rows do you want on your grid: ");
			rowInput = input.nextInt();
			
      System.out.print("How many columns do you want on your grid?: ");
      colInput = input.nextInt();
 
			//Initializing array with user input
      grid = new char [rowInput][colInput];

      //Variable to assign the number of dead cells within grid, which would always be at least half or less the total number of cells
      range = (rowInput*colInput)/2;

      //Create size of array to store the idices of the dead cells
      deadCellIndices = new int [2][range];
      
      //For loop to populate array with indices of dead cells
      for(int col=0; col<range; col++){
        for(int row=0; row<2; row++ ){
          //If statements to populate array based on what row in the array is populated 
          if (row == 0){
            //The random number will be generated from 0 to 1 less the amount of rows in the grid
            deadCellIndex = (int)((rowInput)* Math.random());
          }//When deadCellIndices array is at the first row, where the dead cell's row index will be stored
          else {
            //The random number will be generated from 0 to 1 less the amount of columns in the grid
            deadCellIndex = (int)((colInput)* Math.random());
          }//When for loop moves to 2nd row in the deadCellIndices array, where the dead cell's column index will be stored
          
          //The array get assigned a random number depending on what row that array index is on
          deadCellIndices [row][col] = deadCellIndex;
        }//Columns
      }//Rows
  
      //Populate grid array with dashes
      for (int row=0; row<rowInput; row++){
        for(int col = 0; col<colInput; col++ ){
          grid[row][col] = '-';
        }//Columns
      }//Rows

      //Space out the headers for columns
      System.out.print("\n\t");

      //For loop to print out the column headers
      for(int col = 1; col<=colInput; col++ ){
        System.out.print(col+"  ");
      }//Column header
   
      //Printing out grid
      for (int row=0; row<rowInput; row++){
        System.out.print("\n"+(char)(letter+row)+"   ");
        //For loop to print out the column headers
        for(int col = 0; col<colInput; col++ ){
          System.out.print(grid[row][col]+"  ");
        }//Column header
      }//Rows
      
      //Do while loop to keep game running until player finds a dead cell
      do{
        
  			//User guesses cell
  			System.out.print("\n\nEnter A Cell (Row Column): ");
        rowGuess = input.next().charAt(0);
        colGuess = input.nextInt();
  
        //For loop to search in deadCellIndices array to find any matches with user input
        for(int col=0; col<range; col++){
          //If statements to check if the user's input matches with the indices of the dead cells
          if (((rowGuess-65) == deadCellIndices[0][col]) && ((colGuess-1) == deadCellIndices[1][col]) ){
            //Sentinal value is assigned which will help assign the appropriate value to the that grid coordinate and to exit the game
            runAgain = true;
            break;
            
          }//If statement if row and column index of dead cell matches with the row and column index inputted by the user
  			}//Columns

        //If statement to assign the appropriate value based on if the sentinal value is assigned
        if (runAgain == true){
          grid [rowGuess-65][colGuess-1] = 'X';
        }

        else{
          grid [rowGuess-65][colGuess-1] = 'O';
          //Add one to counter to keep track of player's score everytime they guess a live cell
          counter++;
        }

        //Space out the headers for columns
        System.out.print("\n\t");
  
        //For loop to print out the column headers
        for(int col = 1; col<=colInput; col++ ){
          System.out.print(col+"  ");
        }//Column header
  
        //Printing out grid array
        for (int row=0; row<rowInput; row++){
          //Output row headers with letters
          System.out.print("\n"+(char)(letter+row)+"   ");
          //For loop to print out the contents of the grid
          for(int col = 0; col<colInput; col++ ){
            System.out.print(grid[row][col]+"  ");
          }//Column header
        }//Rows

			}while(runAgain == false);

			//Try-catch to create a file and store the score in that file
      try{
        //Creating and naming file 
        FileWriter fw = new FileWriter ("scores.txt", true);
        PrintWriter writer = new PrintWriter (fw);

        writer.println(counter);

        //Closing writer
        writer.close();
      }//Try

      catch (IOException e){
        System.out.println("There was an error with running the program again");
      }//Catch

      //User inputs if they would like to play again
      System.out.print("\n\nWould you like to play again?: ");
      choice = input.next();
		}while(choice.equalsIgnoreCase("Yes")||choice.equalsIgnoreCase("Y"));
    
  }// Close main method
}// End class