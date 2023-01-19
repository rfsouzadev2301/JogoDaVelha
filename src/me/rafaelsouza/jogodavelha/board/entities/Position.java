package me.rafaelsouza.jogodavelha.board.entities;

public class Position {
	
	protected int column;
	protected int row;
	
	public Position(int colunm, int row) {
		this.column = colunm;
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int colunm) {
		this.column = colunm;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	@Override
	public String toString() {
		return column + ", " + row;
	}
}
