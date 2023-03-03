package me.rafaelsouza.jogodavelha.game.entities.player;

import me.rafaelsouza.jogodavelha.game.entities.GamePiece;

public class Player {
	
	private String name;
	private GamePiece piece;
	
	public Player(String name, GamePiece piece) {
		this.name = name;
		this.piece = piece;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GamePiece getPiece() {
		return piece;
	}

	public void setPiece(GamePiece piece) {
		this.piece = piece;
	}
}
