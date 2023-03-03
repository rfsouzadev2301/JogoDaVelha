package me.rafaelsouza.jogodavelha.game.entities;

import java.util.InputMismatchException;

import me.rafaelsouza.jogodavelha.board.entities.Board;
import me.rafaelsouza.jogodavelha.board.entities.Piece;
import me.rafaelsouza.jogodavelha.board.exceptions.BoardException;
import me.rafaelsouza.jogodavelha.game.entities.player.Player;
import me.rafaelsouza.jogodavelha.game.enums.GameStatus;
import me.rafaelsouza.jogodavelha.game.enums.LineWinner;
import me.rafaelsouza.jogodavelha.game.exceptions.VelhaException;

public class Game {
	
	private Board board = new Board(3, 3);
	private Player[] players = new Player[2];
	private MatchReports matchData;
	private UI Ui;

	public Game(UI ui, Player players1, Player players2) {
		this.Ui = ui;
		this.players = new Player[]{players1, players2};
		matchData = new MatchReports(GameStatus.STOPPED, this.players[0], this.players[1]);
	}
	
	public Game(UI ui, Player[] players) {
		this.Ui = ui;
		this.players = players;
		matchData = new MatchReports(GameStatus.STOPPED, this.players[0], this.players[1]);
	}
	
	public Game(UI ui) {
		this.Ui = ui;
	}
	
	public void setPlayer(Player[] players) {
		this.players = players;
		matchData = new MatchReports(GameStatus.STOPPED, this.players[0], this.players[1]);
	}
	
	public void startGame() {
		matchData.setGameStatus(GameStatus.RUNNING);
	}
	
	public void playerMove(GamePosition position) {
		
		if(matchData.getGameStatus() == GameStatus.STOPPED)
			return;
		
		if(matchData.getGameStatus() == GameStatus.RUNNING) {
			
			do {
				try {
					if(board.hasPieceInPosition(position.toPostion())) {
						throw new VelhaException("This position already contains a piece!");
					}else {
						movePiece(matchData.getPlayerTurn().getPiece(), position);
					}
					
				}catch(InputMismatchException | BoardException e) {
					throw new VelhaException("This position is invalid! ");
					
				}catch(VelhaException e) {
					throw new VelhaException(e.getMessage());
				}

			}while (position == null);	
			
			verifyMatch(board);
			if(matchData.getGameStatus() != GameStatus.RUNNING && matchData.getGameStatus() != GameStatus.STOPPED) {
				Ui.finished(matchData);
			}else {
				updateTurn();
			}
			Ui.updateHash(board);
		}
	}
	
	private void movePiece(GamePiece piece, GamePosition position) {
		board.insertPiece(piece, position.toPostion());
		matchData.incrementMoves();
	}
	
	boolean verifyPosition(GamePosition position) {
		return !board.hasPieceInPosition(position.toPostion());
	}
	
 	public void updateTurn() {
		Player lastPlayerTurn = matchData.getPlayerTurn();
		matchData.setPlayerTurn(matchData.getNextPlayer());
		matchData.setNextPlayer(lastPlayerTurn); 
	}
	
	private void verifyMatch(Board board) {
		LineWinner lineWinner = lineWinner(board);
		
		if(lineWinner != LineWinner.UNDEFINED) {
			matchData.setGameStatus(GameStatus.WINNER);
			matchData.setVelha(false);
			matchData.setWinner(matchData.getPlayerTurn(), lineWinner);
			return;
		}else if(hasVelha(board)) {
			matchData.setGameStatus(GameStatus.VELHA);
			matchData.setVelha(true);
			matchData.setWinner(new Player("Velha", null), lineWinner);
			return;
		}
	}

	private LineWinner lineWinner(Board board) {
		
		if (board.getBoard()[0][0] == board.getBoard()[1][1] && board.getBoard()[0][0] == board.getBoard()[2][2] && board.getBoard()[0][0] != null) {
			return LineWinner.DIAGONAL_A1_C3;
		}
		if (board.getBoard()[0][2] == board.getBoard()[1][1] && board.getBoard()[0][2] == board.getBoard()[2][0] && board.getBoard()[0][2] != null) {
			return LineWinner.DIAGONAL_A3_C1;
		}
		for (int i = 0; i < 3; i++) {	
			if (board.getBoard()[i][0] == board.getBoard()[i][1] && board.getBoard()[i][0] == board.getBoard()[i][2] && board.getBoard()[i][0] != null) {
				switch (i) {
					case 0:
						return LineWinner.LINE_1;
					case 1:
						return LineWinner.LINE_2;
					case 2:
						return LineWinner.LINE_3;
				}
			}
		}
		for (int j = 0; j < 3; j++) {
			if (board.getBoard()[0][j] == board.getBoard()[1][j] && board.getBoard()[0][j] == board.getBoard()[2][j] && board.getBoard()[0][j] != null) {
				switch (j) {
					case 0:
						return LineWinner.COLUMN_1;
					case 1:
						return LineWinner.COLUMN_2;
					case 2:
						return LineWinner.COLUMN_3;
				}
			}
		}
		return LineWinner.UNDEFINED;	
	}
	
	private boolean hasVelha(Board board) {

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
						boardNextPlayer[i][j] = matchData.getNextPlayer().getPiece();
					}
					if(boardPlayerTurn[i][j] == null) {
						boardPlayerTurn[i][j] = matchData.getPlayerTurn().getPiece();
					}
				}
			}
			if (lineWinner(new Board(boardNextPlayer)) != LineWinner.UNDEFINED || lineWinner(new Board(boardPlayerTurn)) != LineWinner.UNDEFINED){
				return true;
			}
			return false;
	}
	
	public MatchReports getMatchData() {
		return matchData;
	}
	
	public Piece[][] getBoard() {
		return board.getBoard();
	}
}
