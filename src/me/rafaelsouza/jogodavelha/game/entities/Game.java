package me.rafaelsouza.jogodavelha.game.entities;

import java.util.InputMismatchException;

import me.rafaelsouza.jogodavelha.board.entities.Board;
import me.rafaelsouza.jogodavelha.board.entities.Piece;
import me.rafaelsouza.jogodavelha.board.exceptions.BoardException;
import me.rafaelsouza.jogodavelha.game.enums.GameStatus;
import me.rafaelsouza.jogodavelha.game.exceptions.VelhaException;

public class Game {
	
	private Board board = new Board(3, 3);
	private Player[] players = new Player[2];
	private MatchReports data;
	private UI Ui;

	public Game(UI ui, Player[] players) {
		this.Ui = ui;
		this.players = players;
		data = new MatchReports(GameStatus.RUNNING, this.players[0], this.players[1]);
	}
	
	public Game(UI ui) {
		this.Ui = ui;
	}
	
	public void setPlayer(Player[] players) {
		if(players == null){
			this.players = players;
			data = new MatchReports(GameStatus.RUNNING, this.players[0], this.players[1]);
		}
	}
	
	public void Match() {
		do {
			nextRound();
		}while(data.getGameStatus() == GameStatus.RUNNING);
		Ui.finished(data);
		return;
	}
	
	public void nextRound() {
		data.incrementRound();
		Ui.updateHash(board);
		
		for(Player player: players) {
			
			if(data.getGameStatus() != GameStatus.RUNNING) {
				return;
			}
			GamePosition position = null;
			do {
				try {
					position = Ui.inputMovePosition();
					if(board.hasPieceInPosition(position.toPostion())) {
						throw (new VelhaException("This position already contains a piece!"));
					}
					playerMove(position);
				
				}catch(InputMismatchException | BoardException e) {
					System.out.println("This position is invalid! ");
					System.out.println();
					
				}catch(VelhaException e) {
					position = null;
					System.out.println(e.getMessage());
					System.out.println();
				}finally {
					Ui.updateHash(board);
				}
			}while (position == null);
		
			if(data.getGameStatus() != GameStatus.RUNNING) {
				return;
			}
			updateTurn();
		}
	}
	
	public void playerMove(GamePosition position) {
		board.insertPiece(data.getPlayerTurn().getPiece(), position.toPostion());
		verifyMatch(board);
	}
	
	public boolean verifyPosition(GamePosition position) {
		return !board.hasPieceInPosition(position.toPostion());
	}
	
 	public void updateTurn() {
		Player lastPlayerTurn = data.getPlayerTurn();
		data.setPlayerTurn(data.getNextPlayer());
		data.setNextPlayer(lastPlayerTurn); 
	}
	
	public void verifyMatch(Board board) {
		if(hasWinner(board)) {
			data.setGameStatus(GameStatus.WINNER);
			data.setWinner(data.getPlayerTurn());
			return;
		}
		if(hasVelha(board)) {
			data.setGameStatus(GameStatus.VELHA);
			data.setVelha(true);
			return;
		}
	}

	public boolean hasWinner(Board board) {
		
		if (board.getBoard()[0][0] == board.getBoard()[1][1] && board.getBoard()[0][0] == board.getBoard()[2][2] && board.getBoard()[0][0] != null) {
			return true;
		}
		if (board.getBoard()[0][2] == board.getBoard()[1][1] && board.getBoard()[0][2] == board.getBoard()[2][0] && board.getBoard()[0][2] != null) {
			return true;
		}
		for (int i = 0; i < 3; i++) {	
			if (board.getBoard()[i][0] == board.getBoard()[i][1] && board.getBoard()[i][0] == board.getBoard()[i][2] && board.getBoard()[i][0] != null) {
				return true;
			}
		}
		for (int j = 0; j < 3; j++) {
			if (board.getBoard()[0][j] == board.getBoard()[1][j] && board.getBoard()[0][j] == board.getBoard()[2][j] && board.getBoard()[0][j] != null) {
				return true;
			}
		}
		return false;	
	}
	
	public boolean hasVelha(Board board) {

		if(winnerPossibility(board)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean winnerPossibility(Board board) {
			Piece[][] boardNextPlayer = new Piece[3][3];
			Piece[][] boardPlayerTurn = new Piece[3][3]; 
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					boardNextPlayer[i][j] = board.getBoard()[i][j];
					boardPlayerTurn[i][j] = board.getBoard()[i][j];
				}
			}
			
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					if(boardNextPlayer[i][j] == null) {
						boardNextPlayer[i][j] = data.getNextPlayer().getPiece();
					}
					if(boardPlayerTurn[i][j] == null) {
						boardPlayerTurn[i][j] = data.getPlayerTurn().getPiece();
					}
				}
			}
			if (hasWinner(new Board(boardNextPlayer)) || hasWinner(new Board(boardPlayerTurn))){
				return true;
			}
		
			return false;
	}

	public MatchReports getData() {
		return data;
	}
}
