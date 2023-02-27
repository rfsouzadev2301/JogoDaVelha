package me.rafaelsouza.jogodavelha.game.entities;

import me.rafaelsouza.jogodavelha.board.entities.Board;

public interface UI {
	
	public GamePosition inputMovePosition();
	public void updateHash(Board board);
	public void finished(MatchReports info);
}
