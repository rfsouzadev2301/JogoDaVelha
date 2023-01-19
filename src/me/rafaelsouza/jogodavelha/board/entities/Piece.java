package me.rafaelsouza.jogodavelha.board.entities;

public class Piece {
	
	protected Position position;
	
	public Piece(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
