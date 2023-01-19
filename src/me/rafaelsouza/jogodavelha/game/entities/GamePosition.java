package me.rafaelsouza.jogodavelha.game.entities;

import me.rafaelsouza.jogodavelha.board.entities.Position;

public class GamePosition {
	
	protected char row;
	protected int column;
	
	public GamePosition(char row, int colunm) {
		this.row = row;
		this.column = colunm;
	}

	public char getRow() {
		return row;
	}

	public void setRow(char row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}

	public void setColumn(int colunm) {
		this.column = colunm;
	}
	
	public Position toPostion() {
		int rowToPosition = row - 'a';
		int columnToPosition = column - 1;
		return new Position(rowToPosition, columnToPosition);
	}
	
	@Override
	public String toString() {
		return row + ", " + column;
	}
}
