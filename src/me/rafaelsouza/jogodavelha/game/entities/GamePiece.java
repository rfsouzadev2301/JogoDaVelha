package me.rafaelsouza.jogodavelha.game.entities;

import me.rafaelsouza.jogodavelha.board.entities.Piece;
import me.rafaelsouza.jogodavelha.board.entities.Position;

public class GamePiece extends Piece{
	
	protected Character symbol;

	public GamePiece(Position position, Character symbol) {
		super(position);
		this.symbol = symbol;
	}

	public char getSymbol() {
		return symbol;
	}

}
