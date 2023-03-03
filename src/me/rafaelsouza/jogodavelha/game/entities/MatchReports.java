package me.rafaelsouza.jogodavelha.game.entities;

import me.rafaelsouza.jogodavelha.game.entities.player.Player;
import me.rafaelsouza.jogodavelha.game.enums.GameStatus;
import me.rafaelsouza.jogodavelha.game.enums.LineWinner;
import me.rafaelsouza.jogodavelha.game.enums.PieceType;

public class MatchReports {
	
	private GameStatus gameStatus = GameStatus.STOPPED;
	private LineWinner lineWinner = LineWinner.UNDEFINED;
	private Player playerTurn = new Player("PLAYER_1", new GamePiece(null, PieceType.PIECE_O))
			, nextPlayer = new Player("PLAYER_2", new GamePiece(null, PieceType.PIECE_X))
			, winner = new Player("UNDEFINED", new GamePiece(null, null));
	private boolean velha = false;
	private int moves = 0;
			
	public MatchReports(GameStatus gameStatus, Player playerTurn, Player nextPlayer) {
		this.gameStatus = gameStatus;
		this.playerTurn = playerTurn;
		this.nextPlayer = nextPlayer;
	}
	
	public MatchReports(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
	public Player getPlayerTurn() {
		return playerTurn;
	}
	public void setPlayerTurn(Player playerTurn) {
		this.playerTurn = playerTurn;
	}
	public Player getNextPlayer() {
		return nextPlayer;
	}
	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}
	
	
	public LineWinner getLineWinner() {
		return lineWinner;
	}
	
	public Player getWinner() {
		return winner;
	}
	
	public void setWinner(Player winner, LineWinner lineWinner) {
		this.winner = winner;
		this.lineWinner = lineWinner;
	}
	
	public boolean isVelha() {
		return velha;
	}
	
	public void setVelha(boolean velha) {
		this.velha = velha;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public void incrementMoves() {
		moves++;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder()
				.append("Game Status: " + gameStatus.toString())
				.append("\nMoves: " + moves)
				.append("\nPlayer Turn: " + playerTurn.getName())
				.append("\nNext Player: " + nextPlayer.getName())
				.append("\n\nWinner: " + winner.getName())
				.append("\nVelha: " + velha);
		
		return sb.toString();
	}
	
	

}
