package me.rafaelsouza.jogodavelha.game.entities;

import me.rafaelsouza.jogodavelha.board.entities.Board;

public interface UI {
	
	public GamePosition askPositionForPlayer(Player player);
	public void updateTable(Board board);
	public void finished(MatchReports info);
}
