package DataObject;

public class GameDataObject {

	public int id;
	public int gameTypeId;
	public int winnerPlayer;
	public String gameStatus;

	public int player1Id;
	public int player2Id;


	public GameDataObject (int id, int gameTypeId, int winnerPlayer, String gameStatus, int player1Id, int player2Id) {
		this.id = id;
		this.gameTypeId = gameTypeId;
		this.winnerPlayer = winnerPlayer;
		this.gameStatus = gameStatus;
		this.player1Id = player1Id;
		this.player2Id = player2Id;
	}
}