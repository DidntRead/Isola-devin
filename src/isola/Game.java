package isola;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
	private Board board;
	private Scanner sc;
	private String player1Name, player2Name;
	
	public Game(Scanner scn, String player1Name, String player2Name) {
		this.sc = scn;
		this.player1Name = player1Name;
		this.player2Name = player2Name;
	}
	
	public void gameLoop() {
		initializeBoard();
		displayBoard();
		
		while(true) {
			handlePlayer1Turn();
			if(board.hasWon(BoardPiece.PLAYER_1)) {
				System.out.println(player1Name + " has won! Congrats!");
				displayWinnerTrophy(player1Name);
				break;
			}
			handlePlayer2Turn();
			if(board.hasWon(BoardPiece.PLAYER_2)) {
				System.out.println(player2Name + " has won! Congrats!");
				displayWinnerTrophy(player2Name);
				break;
			}
		}
	}
	
	public boolean checkWannaPlayAgain() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Wanna play again(y/n)?");
		sc.nextLine();
		String answer = sc.nextLine();
		return answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes");
	}
	
	private void displayWinnerTrophy(String winnerName) {
		System.out.println("_____________________\r\n"
				+ "\\*******************/===++\r\n"
				+ " \\*******N1********/    //\r\n"
				+ "  \\***************/    //\r\n"
				+ "   \\*************/====//\r\n"
				+ "    \\___________/\r\n"
				+ "   ---------------\r\n"
				+ "   ||  Winner!  ||\r\n"
				+ "   ||" + Utils.center(winnerName.length() > 11 ? winnerName.substring(0, 11) : winnerName , 11, ' ') + "||\r\n"
				+ "   ---------------\r\n"
				+ "        |__|\r\n"
				+ "        |__|\r\n"
				+ "        |__|\r\n"
				+ "       /____\\");
	}
	
	private void displayBoard() {
		Utils.clearConsole();
		System.out.println(board);
	}

	private void initializeBoard() {
		boolean valid = false;
		int nSize = 0;
		
		System.out.println("Enter an odd number bigger than 1 for the size of the board: ");
		
		try {
			// Size of the board and stuff
			nSize = sc.nextInt();
		} catch(InputMismatchException e) {
			sc.nextLine();
			initializeBoard();
			return;
		}

		while (!valid) {
			
			if (nSize <= 1) {
				System.out.println("The number N must be bigger than 1, enter a bigger number:");
				nSize = sc.nextInt();
			}

			if (nSize >= 50) {
				System.out.println("The number N is too big, please enter a number less than 50:");
				nSize = sc.nextInt();
			}
			
			if (nSize % 2 == 0) {
				System.out.println("Please enter an ODD number N:");
				nSize = sc.nextInt();
			}
			
			if (nSize % 2 == 1) {
				valid = true;
			}
		}
		
		board = new Board(nSize);
	}

	private void handlePlayer2Turn() {
		int row = 0, column = 0;
		boolean valid = true;
		
		do {
			displayBoard();

			if(!valid) System.out.println("Please enter a valid move!");
			
			System.out.println(player2Name + "(P2) enter the row and column you wish to go to:");
			if(!sc.hasNextInt()) {
				sc.nextLine();
				continue;
			}
			row = sc.nextInt();
			
			if(!sc.hasNextInt()) {
				sc.nextLine();
				continue;
			}
			column = sc.nextInt();
			
			valid = false;
		} while (!board.move(BoardPiece.PLAYER_2, row, column));

		// reset valid flag
		valid = true;
		
		displayBoard();
				
		do {
			displayBoard();
			if(!valid) System.out.println("Please enter a valid move!");
			
			System.out.println(player2Name + "(P2) enter the row and column you wish to eliminate:");
			if(!sc.hasNextInt()) {
				sc.nextLine();
				continue;
			}
			row = sc.nextInt();
			
			if(!sc.hasNextInt()) {
				sc.nextLine();
				continue;
			}
			column = sc.nextInt();
			
			valid = false;
		} while (!board.invalidate(row, column));
		
		displayBoard();
	}
	
	private void handlePlayer1Turn() {
		int row = 0, column = 0;
		boolean valid = true;
				
		do {
			displayBoard();
			if(!valid) System.out.println("Please enter a valid move!");
			
			System.out.println(player1Name + "(P1) enter the row and column you wish to go to:");
			if(!sc.hasNextInt()) {
				sc.nextLine();
				continue;
			}
			row = sc.nextInt();
			
			if(!sc.hasNextInt()) {
				sc.nextLine();
				continue;
			}
			column = sc.nextInt();
			
			valid = false;
		} while (!board.move(BoardPiece.PLAYER_1, row, column));

		// reset valid flag
		valid = true;
		
		displayBoard();
				
		do {
			displayBoard();
			if(!valid) System.out.println("Please enter a valid move!");
			
			System.out.println(player1Name + "(P1) enter the row and column you wish to eliminate:");
			if(!sc.hasNextInt()) {
				sc.nextLine();
				continue;
			}
			row = sc.nextInt();
			
			if(!sc.hasNextInt()) {
				sc.nextLine();
				continue;
			}
			column = sc.nextInt();
			
			valid = false;
		} while (!board.invalidate(row, column));
		
		displayBoard();
	}
}
