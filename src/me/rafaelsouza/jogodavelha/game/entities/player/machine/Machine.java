package me.rafaelsouza.jogodavelha.game.entities.player.machine;

import me.rafaelsouza.jogodavelha.board.entities.Piece;
import me.rafaelsouza.jogodavelha.game.entities.GamePosition;

public class Machine{
	
	public static GamePosition getPosition(Piece[][] piecesOfBoard, Piece opponentPiece, Piece machinePiece, MachineDifficulty difficulty) {
		
		GamePosition position = null;
		int greaterWeight = 0;
		int[][] boardTotalWeght = boardTotalWeght(piecesOfBoard, opponentPiece, machinePiece);
			
		for(int i = 0; i < piecesOfBoard.length; i++) {
			for(int j = 0; j < piecesOfBoard.length; j++) {
				if(boardTotalWeght[i][j] > greaterWeight && randomBooleanOfDifficulty(difficulty) && piecesOfBoard[i][j] == null) {
					greaterWeight = boardTotalWeght[i][j];
					position = new GamePosition((char)('a' + i), (j + 1));
				}
			}
		}
		
		if(greaterWeight == 0) {
			position = new GamePosition((char)('a' + ((int)Math.random() * 3)), (int) ((Math.random() * 3)));
		}
		
		return position;
	}
	
	private static int[][] boardTotalWeght(Piece[][] piecesOfBoard, Piece opponentPiece, Piece machinePiece){
		
		int[][] boardTotalWeght = new int[piecesOfBoard.length][piecesOfBoard.length];
		int[][] boardWeightOfPieceOpponent =  boardWeightOfPiece(piecesOfBoard, opponentPiece);
		int[][] boardWeightOfPieceMachine = boardWeightOfPiece(piecesOfBoard, machinePiece, 1);
		
		
		for(int i = 0; i < piecesOfBoard.length; i++) {
			for(int j = 0; j < piecesOfBoard.length; j++) {
				boardTotalWeght[i][j] = boardWeightOfPieceOpponent[i][j];
				boardTotalWeght[i][j] += boardWeightOfPieceMachine[i][j];
			}
		}
		return boardTotalWeght;
	}
	
	private static int[][] boardWeightOfPiece(Piece[][] piecesOfBoard, Piece pieceToCheck, int increment){
		
		int[][] boardWeightIncrement = new int[piecesOfBoard.length][piecesOfBoard.length];
		
		for(int i = 0; i < piecesOfBoard.length; i++) {
			for(int j = 0; j < piecesOfBoard.length; j++) {
				int[][] boardWeight = boardWeightOfPiece(piecesOfBoard, pieceToCheck);
				if(boardWeight[i][j] != 0) {
					boardWeightIncrement[i][j] = boardWeight[i][j] + increment;
				}
			}
		}
		return boardWeightIncrement;
	}
	
	private static int[][] boardWeightOfPiece(Piece[][] piecesOfBoard, Piece pieceToCheck){
		
		int[][] boardWeight = new int[piecesOfBoard.length][piecesOfBoard.length];
		
		Piece[][] lines = new Piece[3][3];
		Piece[][] columns = new Piece[3][3];
		Piece[][] diagonals = new Piece[2][3];
		
		for(int i = 0; i < piecesOfBoard.length; i++) {
			for(int j = 0; j < piecesOfBoard.length; j++) {
				lines[i][j] = piecesOfBoard[i][j];
				columns[i][j] = piecesOfBoard[j][i];
			}
		}
		diagonals[0] = new Piece[] {piecesOfBoard[0][0], piecesOfBoard[1][1], piecesOfBoard[2][2]};
		diagonals[1] = new Piece[] {piecesOfBoard[0][2], piecesOfBoard[1][1], piecesOfBoard[2][0]};
		
		for(int i = 0; i < piecesOfBoard.length; i++) {
			for(int j = 0; j < piecesOfBoard.length; j++) {
				boardWeight[i][j] = 0;
			}
		}
		
		int[][] intLines = new int[3][3];
		int[][] intColumns = new int[3][3];
		int[][] intDiagonals = new int[2][3];
		
		for(int i = 0; i < piecesOfBoard.length; i++) {
			for(int j = 0; j < piecesOfBoard.length; j++) {
				intLines[i] = lineWeight(lines[i], pieceToCheck);
				intColumns[i] = lineWeight(columns[i], pieceToCheck);
			}
		}
		intDiagonals[0] = lineWeight(diagonals[0], pieceToCheck);
		intDiagonals[1] = lineWeight(diagonals[1], pieceToCheck);
		
		
		for(int i = 0; i < piecesOfBoard.length; i++) {
			for(int j = 0; j < piecesOfBoard.length; j++) {
				boardWeight[i][j] += intLines[i][j];
				boardWeight[j][i] += intColumns[i][j];
			}
		}
		
		boardWeight[0][0] += intDiagonals[0][0];
		boardWeight[1][1] += intDiagonals[0][1];
		boardWeight[2][2] += intDiagonals[0][2];
		boardWeight[0][2] += intDiagonals[1][0];
		boardWeight[1][1] += intDiagonals[1][1];
		boardWeight[2][0] += intDiagonals[1][2];
		
		return boardWeight;
	}
	
	private static int[] lineWeight(Piece[] piecesOfLine, Piece pieceToCheck) {
		
		if(!isPossibleWinLine(piecesOfLine, pieceToCheck)) {
			return new int[] {0, 0, 0};
		}
		
		int weight = 0;
		
		switch(piecesToWinLine(piecesOfLine, pieceToCheck)) {
		case 0:
			weight = 2;
			break;
		case 1:
			weight = 3;
			break;
		case 2:
			weight = 6;
			break;
		}

		int[] lineWithWeight = new int[3];
		
		for(int i = 0; i < piecesOfLine.length; i++) {
			if(piecesOfLine[i] != null) {
				lineWithWeight[i] = 0;
			}else {
				lineWithWeight[i] = weight;
			}	
		}
		return lineWithWeight;
	}
	
	private static boolean isPossibleWinLine(Piece[] piecesOfLine, Piece pieceToCheck) {
		for (Piece piece: piecesOfLine) {
			if(piece != pieceToCheck && piece != null) {
				return false;
			}
		}
		return true;
	}
	
	private static Integer piecesToWinLine(Piece[] piecesOfLine, Piece pieceToCheck) {
		int amountPieces = 0;
		for(Piece piece: piecesOfLine) {
			if(piece == pieceToCheck) {
				amountPieces = amountPieces + 1;
			}
		}
		return amountPieces;
	}
	
	private static boolean randomBooleanOfDifficulty(MachineDifficulty difficulty) {
		
		switch(difficulty) {
		case EASY:
			if((Math.random() * 10) <= 8.0) {
				return false;
			}
			break;
		case MEDIUM:
			if((Math.random() * 10) <= 4.0) {
				return false;
			}
			break;
		case HARD:
			return true;
		}
		return true;
	}
}
