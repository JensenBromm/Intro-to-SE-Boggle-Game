package DomainObject;

import java.util.ArrayList;
import DataObject.GameDataObject;
import restService.Request.CreateGameRequest;

public class GameDomainObject {

	public int id;
	public int gameTypeId;
	public int winnerPlayer;
	public String gameStatus;

	public int player1Id;
	public PlayerDomainObject player1;
	public ArrayList<WordDomainObject> player1Words;
	public int player2Id;
	public PlayerDomainObject player2;
	public ArrayList<WordDomainObject> player2Words;

	public BoardDomainObject board;

	public Boolean isStatusComplete;
	public Boolean isStatusSetup;


	public GameDomainObject(GameDataObject data) {
		this.id = data.id;
		this.gameTypeId = data.gameTypeId;
		this.winnerPlayer = data.winnerPlayer;
		this.gameStatus = data.gameStatus;

		this.player1Id = data.player1Id;
		this.player2Id = data.player2Id;

		//If gameStatus == Complete, then isComplete will be true; otherwise, false.
		this.isStatusComplete = (gameStatus == "Complete");
		this.isStatusSetup = (gameStatus == "Setup");
	}


	public static ArrayList<GameDomainObject> MapList(ArrayList<GameDataObject> dataList) {
		ArrayList<GameDomainObject> domainObjectList = new ArrayList<GameDomainObject>();
		
		for (int i=0; i<dataList.size(); i++) {
			GameDomainObject domain = new GameDomainObject(dataList.get(i));
			domainObjectList.add(domain);
		}

		return domainObjectList;
	}

	public GameDomainObject(CreateGameRequest data) {
		this.gameTypeId = data.getGameTypeId();
		this.player1Id = data.getPlayer1Id();
		this.player2Id = data.getPlayer2Id();
	}
	

}
