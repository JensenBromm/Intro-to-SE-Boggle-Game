package restService.Request;


public class GuessWordRequest {

	private final int playerId;
	//private final int gameId;
	private final String word;

	public GuessWordRequest(int playerId, String word) {
		this.playerId = playerId;
		this.word = word;
		
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getWord() {
		return word;
	}
}