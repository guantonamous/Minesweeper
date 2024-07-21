import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) 
	{
		
		//creating a scanner
		Scanner myScan = new Scanner(System.in);
		
		
		//create the board 
		
		//length
		int length = 4;
		
		//width 
		int width = 4;
		
		//make a 2d array for the key
		String[][] key = new String [length][width];
		
		
		//make a 2d array for the gameBoard that's in play
		String[][] gameBoard = new String [length][width];
		
		//create the total mines using online algorithm
		int totalMines = (int) ((length * width) * 0.15625);
		
		//declaring the row and column
		int row = 0;
		int col = 0;
		
		//the runner for the first while loop
		boolean continue1 = true;
		
		//Introduction sentence
		System.out.println("Welcome to Minesweeper!");
		
		
		//create a while loop to continue the questions
		while(continue1)
		{
			
			
			
			//first introduction
			System.out.println("Pick a cell");
			
			
			//what row pick
			System.out.println("Row: ");
			row = myScan.nextInt();
			
			
			//what column pick
			System.out.println("Col: ");
			col = myScan.nextInt();
			
			//make the move using makeMove method
			makeMove(gameBoard, key, row, col);
			
			
			//if they pick a spot with a mine... LOSE
			if(key[row][col].equals("M"))
			{
				System.out.println("YOU LOSE!");
				continue1 = false;
			}
			
			//place mine?
			if(continue1)
			{
				System.out.println("Would you like to place a mine? 1 for yes");
				int placeMine1 = myScan.nextInt();
				
				if(placeMine1 == 1)
				{
					System.out.println("Pick a cell");
					System.out.print("Row: ");
					int rowMine = myScan.nextInt();
					
					
					System.out.print("Col: ");
					int colMine = myScan.nextInt();
					
					gameBoard[rowMine][colMine] = "M";
				}
			}
			
			
			
		//closing while loop
		}
		
		
		
		
		
	
		
		
	//closing for main METHOD
	}
	
	
	//this method is the move of the player currently
	public static boolean makeMove(String[][] gameBoard, String[][] key, int row, int col)
	{
		
		//if the user chooses the mine in the key
		if( key[row][col].equals("M"))
		{
			return false;
		}
		
		//assigns the number value to the selected square
		gameBoard[row][col] = key[row][col];
		
		return true;
		
	//closing for makeMove
	}
	
	//this method fills the key with the amount of mines
	public static void placeMines(String[][] key, int totalMines)
	{
		
		
		//randomizes the mines
		for(int i = 0; i < totalMines; i++)
		{
			
			//random number for the row index
			int randomIndexROW = (int) (Math.random() * key.length);
			
			//random number for the column index
			int randomIndexCOL = (int) (Math.random() * key[randomIndexROW].length);
			
			//assigns the mine to the random placement
			key[randomIndexROW][randomIndexCOL] = "M";
		//closing for the for loop
		}
		
	//closing for placeMines
	}
	
	//this resets the board and sets all values to "_"
	public static void resetBoard(String[][] gameBoard)
	{
		
		//loop through the array and add the "_"
		for(int i = 0; i < gameBoard.length; i++)
		{
			for(int k = 0; k < gameBoard[i].length; k++)
			{
				gameBoard[i][k] = "_";
			}
		}
		
	}
	
	//checks to see if the game board is full
	public static boolean gameBoardFull(String[][] gameBoard)
	{
		for(int i = 0; i < gameBoard.length; i++)
		{
			for(int k = 0; k < gameBoard[i].length; k++)
			{
				if(gameBoard[i][k] == " ")
				{
					return true;
				}
			}
		}
		
		return false;
		
	//closes gameBoardFull
	}
	
	//this method prints the board
	public static void printBoard(String[][] arr)
	{
		
		//using an enhanced for loop to print the board
		for(String[] oneD : arr)
		{
			for(String elem : oneD)
			{
				System.out.print(elem + " ");
			}
			System.out.println("");
		}
		
	//closing printBoard
	}
	
	
	//this method checks to see if the mines guessed were correct
	public static boolean checkValidMines(String[][] key, String[][] gameBoard)
	{
		
		//cycle through the array
		for(int i = 0; i < key.length; i++)
		{
			for(int k = 0; k < key[i].length; k++)
			{
				if(key[i][k] != "M" && gameBoard[i][k] == "M")
				{
					return false;
				}
			}
		}
		
		return true;
	}

	//this method fills every square with the number of adjacent mines
	public static void fillBoard(String[][] key)
	{
		
		//counter for the number of adjacent M to assign to the square
		int counter = 0;
		
		//cycles thorugh all the adjacent columsn and rows next to it
		for(int i = 0; i < key.length; i++)
		{
			for(int k = 0; k < key[i].length; k++)
			{
				
				
				//"if mid square"
				//if it is in one of the center squares -- excluding the exterior squares
				if( (i > 0 && i < key.length - 1) && (k > 0 && k < key[i].length - 1) )
				{
					
					//below left
					if( (key[i + 1][k - 1].equals("M") ))
					{
						counter++;
					}
					
					//below
					if( (key[i + 1][k].equals("M") ))
					{
						counter++;
					}
					
					//below right
					if( (key[i + 1][k + 1].equals("M") ))
					{
						counter++;
					}
					
					//left
					if( (key[i][k - 1].equals("M") ))
					{
						counter++;
					}
					
					//right
					if( (key[i][k + 1].equals("M") ))
					{
						counter++;
					}
					
					
					//top left
					if( (key[i - 1][k - 1].equals("M") ))
					{
						counter++;
					}
					
					//top
					if( (key[i - 1][k].equals("M") ))
					{
						counter++;
					}
					
					//top right
					if( (key[i - 1][k + 1].equals("M") ))
					{
						counter++;
					}
					
					//assign the current square a value 
					key[i][k].equals( "" + counter + "");
					
					
				//end of "if mid square"
				}
				
				
				//"corner squares"
				//if it is any of the corner squares
				else if ( (i == 0 && k == 0) || (i == 0 && k == key[i].length - 1) || (i == key.length - 1 && k == 0) || (i == key.length - 1 && k == key[i].length - 1) )
				{
					
					
					//if top left
					
					
				}
				
				
				
				
			//end of k
			}
			
		//end of i
		}
		
	//end of fillBoard method
	}
	
	//closing for main CODE
}
