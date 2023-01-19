package me.rafaelsouza.jogodavelha.board.entities;

import me.rafaelsouza.jogodavelha.board.exceptions.BoardException;

public class Board {
	
	protected Piece[][] board;

	public Board(int rows, int columns) {
		board = new Piece[rows][columns];
	}
	
	public Board(Piece[][] pieces){
		board = pieces;
	}
	public Piece[][] getBoard() {
		return board;
	}
	
	public void insertPiece(Piece piece, Position position) {
		thisPositionExist(position);
		piece.setPosition(position);
		board [position.getColumn()] [position.getRow()] = piece;
	}
	
	public void removePiece(Piece piece, Position position) {
		thisPositionExist(position);
		piece.setPosition(position);
		board [position.getColumn()] [position.getRow()] = null;
	}

	public boolean hasPieceInPosition(Position position) {
		thisPositionExist(position);
		if(board[position.getColumn()] [position.getRow()] != null) {
			return true;
		}
		return false;
	}
	
	public void thisPositionExist(Position position) {
		if((position.getRow() < 0 || position.getRow() > board.length) || (position.getColumn() < 0 || position.getColumn() > board[0].length))
			throw (new BoardException("This position is ivalid"));
	}
}
