package isola;

import java.util.Scanner;

public class Izola {	
	public static void main(String[] args) {
		String player1Name, player2Name;
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Enter Player1's name: ");
		player1Name = scn.nextLine();
		System.out.println("Enter Player2's name: ");
		player2Name = scn.nextLine();
		
		Game game = new Game(scn, player1Name, player2Name);
		while(true) {
			game.gameLoop();	
			if(!game.checkWannaPlayAgain()) break;
		}
		Utils.clearConsole();
		System.out.println("Ok. Bye!");
	}
}
