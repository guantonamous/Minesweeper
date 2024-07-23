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
		
		
		//to be able to play
		resetBoard(gameBoard);
		resetBoard(key);
		
		
		//declaring the row and column
		int row = 0;
		int col = 0;
		
		int totalSquares = width * length;
		
		//the runner for the first while loop
		boolean continue1 = true;
		
		//Introduction sentence
		System.out.println("Welcome to Minesweeper!");
		
		
		//create a while loop to continue the questions
		while(continue1)
		{
			
			printBoard(gameBoard);
			
			//first introduction
			System.out.println("Pick a cell");
			
			
			//what row pick
			System.out.println("Row: ");
			row = myScan.nextInt();
			
			
			//what column pick
			System.out.println("Col: ");
			col = myScan.nextInt();
			
			//space
			System.out.println("");
			
			//make the move using makeMove method
			makeMove(gameBoard, key, row, col);
			
			//prints the ending picked location
			printBoard(gameBoard);
			
			
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
			
			//if the board is full
			int isBoardFullCounter = 0;
			
			//
			for(int i = 0; i < gameBoard.length; i++)
			{
				for(int k = 0; k < gameBoard[i].length; k++)
				{
					if(!(gameBoard[i][k].equals("_")))
					{
						isBoardFullCounter++;
					}
				}
			}
			
			//space
			System.out.println("");
			
			
			//if the player guesses the valid mines... WIN
			if(isBoardFullCounter == totalSquares && checkValidMines(key, gameBoard) == true)
			{
				System.out.println("Congratulations! Winner! Winner!");
				continue1 = false;
			}
			
			//test and did bug fixes
			
			//if the board is full and the miens are in the wrong place... LOSE
			else if (isBoardFullCounter == totalSquares && !(checkValidMines(key, gameBoard)) )
			{
				System.out.println("You lose! Your mines were not in the correct place.");
				System.out.println("Here's the key:");
				printBoard(key);
				continue1 = false;
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
		
		//cycles through all the adjacent column and rows next to it
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
					if(i == 0 && k==0)
					{
						
						//right
						if( (key[i][k + 1].equals("M")) )
						{
							counter++;
						}
						
						//below right
						if( (key[i + 1][k + 1].equals("M")) )
						{
							counter++;
						}
						
						//below
						if( (key[i + 1][k].equals("M")) )
						{
							counter++;
						}

					//end of top left
					}
					
					//if top right
					else if(i == 0 && k == key[i].length - 1)
					{
						
						//left
						if( key[i][k - 1].equals("M"))
						{
							counter++;
						}
						
						//below left
						if( key[i + 1][k - 1].equals("M"))
						{
							counter++;
						}
						
						//below
						if( key[i + 1][k].equals("M"))
						{
							counter++;
						}
						
					//end of top right
					}
					
					
					//bottom left
					if(i == key.length - 1 && k ==0)
					{
						
						//top 
						if( (key[i - 1][k].equals("M")) )
						{
							counter++;
						}
						
						//top right
						if( (key[i - 1][k + 1].equals("M")) )
						{
							counter++;
						}
						
						//right
						if( (key[i][k + 1].equals("M")) )
						{
							counter++;
						}
						
					//close bottom left
					}
					
					//if bottom right
					if( i == key.length - 1 && k == key[i].length - 1)
					{
						
						//left
						if( (key[i][k - 1].equals("M")))
						{
							counter++;
						}
						
						//top left
						if( (key[i - 1][k - 1].equals("M")))
						{
							counter++;
						}
						
						//top
						if( (key[i - 1][k].equals("M")))
						{
							counter++;
						}
						
					//close bottom right
					}
					
				//close corner squares
				}
				
				//top excluding corners
				else if( (i == 0 && (k != 0 && k != key[i].length - 1) ))
				{
					
					//below left
					if( (key[i + 1][k - 1].equals("M")) )
					{
						counter++;
					}
					
					//below
					if( (key[i + 1][k].equals("M")) )
					{
						counter++;
					}
					
					//below right
					if( (key[i + 1][k + 1].equals("M")) )
					{
						counter++;
					}
					
					//left
					if( (key[i][k - 1].equals("M")) )
					{
						counter++;
					}
					
					//right
					if( (key[i][k + 1].equals("M")) )
					{
						counter++;
					}
					
				//closing top excluding squares
				}
				
				//right excluding corners
				else if( (i != 0 && i != key.length - 1) && k == key[i].length - 1)
				{
					
					//below left
					if( (key[i + 1][k - 1].equals("M")))
					{
						counter++;
					}
					
					//below
					if( (key[i + 1][k].equals("M")))
					{
						counter++;
					}
					
					//left
					if( (key[i][k - 1].equals("M")))
					{
						counter++;
					}
					
					//top left
					if( (key[i - 1][k - 1].equals("M")))
					{
						counter++;
					}
					
					//top
					if( (key[i - 1][k].equals("M")))
					{
						counter++;
					}
					
				//closing right excluding corners
				}
				
				
				//bottom excluding corners
				else if( (i == key.length - 1) && (k != 0 && k != key[i].length - 1))
				{
					
					//left
					if( (key[i][k - 1].equals("M")) )
					{
						counter++;
					}
					
					//right
					if( (key[i][k + 1].equals("M")) )
					{
						counter++;
					}
					
					//top left
					if( (key[i - 1][k - 1].equals("M")) )
					{
						counter++;
					}
					
					//top
					if( (key[i - 1][k].equals("M")) )
					{
						counter++;
					}
					
					//top right
					if( (key[i - 1][k + 1].equals("M")) )
					{
						counter++;
					}
					
				//closing bottom excluding corners
				}
				
				
				//left excluding corners
				else if( (i != 0 && i != key.length) && k == 0)
				{
					
					//below
					if( (key[i + 1][k].equals("M")) )
					{
						counter++;
					}
					
					//below right
					if( (key[i + 1][k + 1].equals("M")) )
					{
						counter++;
					}
					
					//right
					if( (key[i][k + 1].equals("M")) )
					{
						counter++;
					}
					
					//top
					if( (key[i - 1][k].equals("M")) )
					{
						counter++;
					}
					
					//top right
					if( (key[i - 1][k + 1].equals("M")) )
					{
						counter++;
					}
					
				//closing top excluding corners
				}
				
				//assigning the value to the square
				if(key[i][k].equals("M") )
				{
					key[i][k] = "M";
					counter = 0;
				}
				else
				{
					key[i][k] = ("" + counter + "");
					counter = 0;
				}
				
				
				
			//end of k
			}
			
		//end of i
		}
		
	//end of fillBoard method
	}
	
	//closing for main CODE
}
