package me.rafaelsouza.jogodavelha.game.entities;

import me.rafaelsouza.jogodavelha.game.enums.GameStatus;

public class MatchReports {
	
	private GameStatus gameStatus = GameStatus.STOPPED;
	private Player playerTurn = new Player("PLAYER_1", new GamePiece(null, '!'))
			, nextPlayer = new Player("PLAYER_2", new GamePiece(null, '!'))
			, winner = new Player("UNDEFINED", new GamePiece(null, '!'));
	private boolean velha = false;
	private int round = 0;
			
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
	
	public Player getWinner() {
		return winner;
	}
	
	protected void setWinner(Player winner) {
		this.winner = winner;
	}
	
	public boolean isVelha() {
		return velha;
	}
	
	public void setVelha(boolean velha) {
		this.velha = velha;
	}
	
	public int getRound() {
		return round;
	}
	
	public void incrementRound() {
		round++;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder()
				.append("Game Status: " + gameStatus.toString())
				.append("\nRound: " + round)
				.append("\nPlayer Turn: " + playerTurn.getName())
				.append("\nNext Player: " + nextPlayer.getName())
				.append("\n\nWinner: " + winner.getName())
				.append("\nVelha: " + velha);
		
		return sb.toString();
	}
	
	

}
