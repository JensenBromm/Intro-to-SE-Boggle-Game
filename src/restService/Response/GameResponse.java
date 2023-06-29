package restService.Response;

import java.util.ArrayList;
import DomainObject.BoardDomainObject;
import DomainObject.GameDomainObject;
import DomainObject.WordDomainObject;

public class GameResponse {

	private final int gameId;
	private final int gameTypeId;
	private final int player1Id;
	private final int player2Id;
	
	private final String status;
	private final int winner;
	private final Board board;
	
	private final ArrayList<Word> player1Words;
	private final ArrayList<Word> player2Words;

	public GameResponse(int gameId, int gameTypeId, int player1Id, int player2Id, String status, int winner, Board board, ArrayList<Word> player1Words, ArrayList<Word> player2Words) {
		this.gameId = gameId;
		this.gameTypeId = gameTypeId;
		this.player1Id = player1Id;
		this.player2Id = player2Id;
		this.status = status;
		this.winner = winner;
		this.board = board;
		this.player1Words = player1Words;
		this.player2Words = player2Words;
	}

	public GameResponse(GameDomainObject domainObject) {
		this.gameId = domainObject.id;
		this.gameTypeId = domainObject.gameTypeId;
		this.player1Id = domainObject.player1Id;
		this.player2Id = domainObject.player2Id;
		this.status = domainObject.gameStatus;
		this.winner = domainObject.winnerPlayer;
		this.board = BoardDomainObject.MapToResponse(domainObject.board);
		this.player1Words = WordDomainObject.MapToResponse(domainObject.player1Words);
		this.player2Words = WordDomainObject.MapToResponse(domainObject.player2Words);
	
	}

	public static ArrayList<GameResponse> MapList(ArrayList<GameDomainObject> domainList) {
		ArrayList<GameResponse> responseList = new ArrayList<GameResponse>();
		
		for (int i=0; i<domainList.size(); i++) {
			GameResponse response = new GameResponse(domainList.get(i));
			responseList.add(response);
		}

		return responseList;
	}

	public int getGameId() {
		return gameId;
	}

	public int getPlayer1Id() {
		return player1Id;
	}

	public int getPlayer2Id() {
		return player2Id;
	}

	public int getGameTypeId() {
		return gameTypeId;
	}

	public String getStatus() {
		return status;
	}

	public int getWinner() {
		return winner;
	}

	public Board getBoard() {
		return board;
	}

	public ArrayList<Word> getPlayer1Words() {
		return player1Words;
	}

	public ArrayList<Word> getPlayer2Words() {
		return player2Words;
	}
	

	public static class Board {
		private final String g1;
		private final String g2;
		private final String g3;
		private final String g4;
		private final String g5;
		private final String g6;
		private final String g7;
		private final String g8;
		private final String g9;
		private final String g10;
		private final String g11;
		private final String g12;
		private final String g13;
		private final String g14;
		private final String g15;
		private final String g16;


		public Board(String g1, String g2, String g3, String g4, String g5, String g6, String g7, String g8, String g9, String g10, String g11, String g12, String g13, String g14, String g15, String g16) {
			this.g1 = g1;
			this.g2 = g2;
			this.g3 = g3;
			this.g4 = g4;
			this.g5 = g5;
			this.g6 = g6;
			this.g7 = g7;
			this.g8 = g8;
			this.g9 = g9;
			this.g10 = g10;
			this.g11 = g11;
			this.g12 = g12;
			this.g13 = g13;
			this.g14 = g14;
			this.g15 = g15;
			this.g16 = g16;

		}

		public String getG1() {
			return g1;
		}

		public String getG2() {
			return g2;
		}

		public String getG3() {
			return g3;
		}

		public String getG4() {
			return g4;
		}

		public String getG5() {
			return g5;
		}

		public String getG6() {
			return g6;
		}

		public String getG7() {
			return g7;
		}

		public String getG8() {
			return g8;
		}

		public String getG9() {
			return g9;
		}

		public String getG10() {
			return g10;
		}

		public String getG11() {
			return g11;
		}

		public String getG12() {
			return g12;
		}

		public String getG13() {
			return g13;
		}

		public String getG14() {
			return g14;
		}

		public String getG15() {
			return g15;
		}

		public String getG16() {
			return g16;
		}
	}

	
	public static class Word {
		private final String word;
		private final boolean isValid;
		private final int score;

		public Word(String word, boolean isValid, int score) {
			this.word = word;
			this.isValid = isValid;
			this.score = score;
		}

		public String getWord() {
			return word;
		}

		public boolean getIsValid() {
			return isValid;
		}

		public int getScore() {
			return score;
		}
		
			
	}


}
