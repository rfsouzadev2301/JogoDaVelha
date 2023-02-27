package me.rafaelsouza.jogodavelha.game.entities;

import me.rafaelsouza.jogodavelha.board.entities.Piece;
import me.rafaelsouza.jogodavelha.board.entities.Position;
import me.rafaelsouza.jogodavelha.game.enums.PieceType;

public class GamePiece extends Piece{
	
	protected PieceType piece;

	public GamePiece(Position position, PieceType piece) {
		super(position);
		this.piece = piece;
	}

	public PieceType getSymbol() {
		return piece;
	}

}
