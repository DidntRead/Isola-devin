package isola;

public enum BoardPiece {
	EMPTY("  "), PLAYER_1("P1"), PLAYER_2("P2"), INVALID(" *");
	
	private String representation;
	
	BoardPiece(String representation) {
		this.representation = representation;
	}
	
	public String getRepresentation() {
		return this.representation;
	}
}
