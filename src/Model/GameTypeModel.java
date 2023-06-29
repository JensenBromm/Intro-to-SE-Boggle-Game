package Model;
import java.util.ArrayList;

import DataAccess.GameTypeDataAccess;
import DataAccess.LetterCubeDataAccess;
import DataObject.GameTypeDataObject;
import DataObject.LetterCubeDataObject;
import DomainObject.GameTypeDomainObject;
import DomainObject.LetterCubeDomainObject;
import restService.Message;



public class GameTypeModel {
	
	public static GameTypeDomainObject GetGameTypeById(Message message, int id) {
		GameTypeDataObject gameTypeData = GameTypeDataAccess.getGameTypeById(id);
		GameTypeDomainObject gameTypeDomain = new GameTypeDomainObject(gameTypeData);
		
		gameTypeDomain.letterCubes = getLetterCubesForGameType(message, id);
		
		return gameTypeDomain;
	}

	public static ArrayList<GameTypeDomainObject> GetAllGameTypes(Message message) {
		ArrayList<GameTypeDataObject> gameTypeDataList = GameTypeDataAccess.getAllGameTypes();
		ArrayList<GameTypeDomainObject> gameTypeDomainList = GameTypeDomainObject.MapList(gameTypeDataList);
		
		for (int i=0; i<gameTypeDomainList.size(); i++) {
			gameTypeDomainList.get(i).letterCubes = getLetterCubesForGameType(message, gameTypeDomainList.get(i).id);
		}
		
		return gameTypeDomainList;
	}


	private static ArrayList<LetterCubeDomainObject> getLetterCubesForGameType(Message message, int gameTypeId) {
		ArrayList<LetterCubeDataObject> letterCubeList = LetterCubeDataAccess.getAllLetterCubesForGameType(gameTypeId);
		return LetterCubeDomainObject.MapFromDataList(letterCubeList);
	}

	public static boolean ValidateGameTypeById(int id) {
		if (GameTypeDataAccess.getGameTypeById(id) == null)
			return false;
		return true;
	}
	

}
