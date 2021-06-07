package isola;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
	private int size;
	private BoardPiece board[][];
	private int player1X, player2X, player1Y, player2Y;
	
	public Board(int n) {
		assert(n % 2 == 1);
		
		// Initialize board
		this.size = n;
		this.board = new BoardPiece[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				this.board[i][j] = BoardPiece.EMPTY;
			}
		}
		
		player1X = ThreadLocalRandom.current().nextInt(size);
		player1Y = ThreadLocalRandom.current().nextInt(size);
		do {
			player2X = ThreadLocalRandom.current().nextInt(size);
			player2Y = ThreadLocalRandom.current().nextInt(size);
		} while(player1X == player2X && player1Y == player2Y);
		
		assert(player1X != player2X && player1Y == player2Y);
		
		board[player1X][player1Y] = BoardPiece.PLAYER_1;
		board[player2X][player2Y] = BoardPiece.PLAYER_2;
	}

	public boolean hasWon(BoardPiece player) {
		assert(player == BoardPiece.PLAYER_1 || player == BoardPiece.PLAYER_2);
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				if(i == 0 && j == 0) continue;
				if(player == BoardPiece.PLAYER_1) {
					if(player2X + i < 0 || player2X + i >= size ||
							player2Y + j < 0 || player2Y + j >= size) 
						continue;
					
					if(board[player2X + i][player2Y + j] == BoardPiece.EMPTY) return false;
				} else {
					if(player1X + i < 0 || player1X + i >= size ||
							player1Y + j < 0 || player1Y + j >= size) 
						continue;
					
					if(board[player1X + i][player1Y + j] == BoardPiece.EMPTY) return false;
				}
			}
		}
		return true;
	}
	
	public boolean move(BoardPiece playerToMove, int x, int y) {
		assert(playerToMove == BoardPiece.PLAYER_1 || playerToMove == BoardPiece.PLAYER_2);
		
		// Validate not larger than board
		if(x >= size || y >= size) return false;
		// Validate tile empty
		if(board[x][y] != BoardPiece.EMPTY) return false;
		
		if(playerToMove == BoardPiece.PLAYER_1) {
			// Validate move is only 1 tile
			if(Math.abs(player1X - x) > 1 || Math.abs(player1Y - y) > 1) return false;
			
			// Do actual move
			board[player1X][player1Y] = BoardPiece.EMPTY;
			player1X = x;
			player1Y = y;
		} else {
			// Validate move is only 1 tile
			if(Math.abs(player2X - x) > 1 || Math.abs(player2Y - y) > 1) return false;
			
			// Do actual move
			board[player2X][player2Y] = BoardPiece.EMPTY;	
			player2X = x;
			player2Y = y;
		}		
		board[x][y] = playerToMove;
		
		return true;
	}
	
	public boolean invalidate(int x, int y) {
		if(x >= size || y >= size) return false;
		if(board[x][y] != BoardPiece.EMPTY) return false;
		
		board[x][y] = BoardPiece.INVALID;
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		// Column numbering
		output.append("   ");
		for(int i = 0; i < size; i++) {
			output.append(String.format("%3d", i));
		}
		output.append('\n');
		
		// Spacing line
		for(int i = 0; i < size * 3 + 4; i++) output.append('-');
		output.append('\n');
		
		for(int i = 0; i < size; i++) {
			output.append(String.format(" %2d", i));
			for(int j = 0; j < size; j++) {
				output.append("|").append(board[i][j].getRepresentation());
			}
			output.append('|');
			output.append('\n');
		}
		
		// Spacing line
		for(int i = 0; i < size * 3 + 4; i++) output.append('-');
		
		return output.toString();
	}
}
