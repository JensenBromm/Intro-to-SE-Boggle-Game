package DomainObject;

import java.util.ArrayList;
import DataObject.WordDataObject;
import restService.Response.GameResponse;

public class WordDomainObject {

	public int id;
	public int gameId;
	public int playerId;
	public String word;
	public boolean isValid;
	public int score;

	public WordDomainObject(WordDataObject data) {
		this.id = data.id;
		this.gameId = data.gameId;
		this.playerId = data.playerId;
		this.word = data.word;
		this.isValid = data.isValid;
		this.score = data.score;
	}

	public WordDomainObject(int gameId, int playerId, String word) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.word = word;
    }
	
	public static ArrayList<WordDomainObject> MapList(ArrayList<WordDataObject> dataList) {
		ArrayList<WordDomainObject> domainObjectList = new ArrayList<WordDomainObject>();
		
		for (int i=0; i<dataList.size(); i++) {
			WordDomainObject domain = new WordDomainObject(dataList.get(i));
			domainObjectList.add(domain);
		}

		return domainObjectList;
	}

	public static GameResponse.Word MapToResponse(WordDomainObject domain){
		GameResponse.Word response = new GameResponse.Word(domain.word, domain.isValid, domain.score);
		return response;
	}

	public static ArrayList<GameResponse.Word> MapToResponse(ArrayList<WordDomainObject> domainList){
		if (domainList == null)
			return null;
		
		ArrayList<GameResponse.Word> responseList = new ArrayList<GameResponse.Word>();
		for (int i=0; i<domainList.size(); i++) {
			responseList.add(MapToResponse(domainList.get(i)));
		}
		return responseList;
	}
}