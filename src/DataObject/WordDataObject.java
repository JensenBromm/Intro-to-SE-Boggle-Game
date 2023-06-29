package DataObject;

public class WordDataObject {

	public int id;
	public int gameId;
	public int playerId;
	public String word;
	public boolean isValid;
	public int score;


	public WordDataObject (int id, int gameId, int playerId, String word, boolean isValid, int score) {
		this.id = id;
		this.gameId = gameId;
		this.playerId = playerId; 
		this.word = word; 
		this.isValid = isValid; 
		this.score = score; 
	}	
}