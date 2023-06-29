package Model;
import java.util.ArrayList;

import DataAccess.DictionaryDataAccess;
import DataAccess.GameDataAccess;
import DataAccess.PlayerDataAccess;
import DataObject.GameDataObject;
import DataObject.PlayerDataObject;
import DomainObject.BoardDomainObject;
import DomainObject.GameDomainObject;
import DomainObject.WordDomainObject;
import restService.Message;


public class GameModel {
	

	public static ArrayList<GameDomainObject> GetAllGameSummaries(Message message) {
		ArrayList<GameDataObject> gameDataList = GameDataAccess.getAllGames();
		ArrayList<GameDomainObject> gameDomainList = GameDomainObject.MapList(gameDataList);
		return gameDomainList;
	}

	public static GameDomainObject GetGameDetailsByGameIdandPlayerId(Message message, int gameId, int playerId) throws Exception {
		//Return game details
		// if the game is setup - return the board and the current players words, and current score
		// if the game is complete - return both words, and the scores
	

		//Get the Game Details
		GameDataObject gameData = GameDataAccess.getGameById(gameId);
		GameDomainObject gameDomain = new GameDomainObject(gameData);

		boolean isPlayer1 = false;
		boolean isPlayer2 = false;
		
		if (gameDomain.player1Id == playerId) {
			isPlayer1 = true;
		} else if (gameDomain.player2Id == playerId) {
			isPlayer2 = true;
		} else {
			throw new Exception("Player " + playerId + " is not part of this game");
		}


		BoardDomainObject boardDomain = BoardModel.GetBoardDetailsByGameId(message, gameDomain.id);
		gameDomain.board = boardDomain;


		if (gameDomain.isStatusComplete) {
			//Get words
			gameDomain.player1Words = WordModel.getAllWordsForGameAndPlayer(message, gameId, gameDomain.player1Id);
			gameDomain.player2Words = WordModel.getAllWordsForGameAndPlayer(message, gameId, gameDomain.player2Id);
		} else if (gameDomain.isStatusSetup) {
			//If playerId == player1, then set player1Words.
			if (isPlayer1)
				gameDomain.player1Words = WordModel.getAllWordsForGameAndPlayer(message, gameId, gameDomain.player1Id);

			//If playerId == player2, then set player2Words.	
			if (isPlayer2)
				gameDomain.player2Words = WordModel.getAllWordsForGameAndPlayer(message, gameId, gameDomain.player2Id);
		}

		return gameDomain;
	}


	public static GameDomainObject GetGameById(Message message, int id) {
		GameDataObject gameData = GameDataAccess.getGameById(id);
		GameDomainObject gameDomain = new GameDomainObject(gameData);
		return gameDomain;
	}


	public static boolean ValidateGameById(int id) {
		if (GameDataAccess.getGameById(id) == null)
			return false;
		return true;
	}

	public static GameDomainObject CreateGame(Message message, GameDomainObject domainGameToCreate) {
		//CHeck if players id can be found
		PlayerDataObject p1 = PlayerDataAccess.getPlayerById(domainGameToCreate.player1Id);
		PlayerDataObject p2 = PlayerDataAccess.getPlayerById(domainGameToCreate.player2Id);

		GameDataObject gameId = GameDataAccess.getGameById(domainGameToCreate.gameTypeId);

		String nullId = "All parameters must be provided.";
		String badPlayer1Id = "The Player 1 provided is an invalid player.";
		String badPlayer2Id = "The Player 2 provided is an invalid player.";
		String badGameId = "The Game Type provided is an invalid game type.";


		if (domainGameToCreate.player1Id<=0) {
			message.addErrorMessage(nullId);
			return null;
		}
		if (domainGameToCreate.player2Id <=0) {
			message.addErrorMessage(nullId);
			return null;
		}
		if(domainGameToCreate.gameTypeId<=0)
		{
			message.addErrorMessage(nullId);
			return null;
		}
		
		if(p1==null)
		{
			message.addErrorMessage(badPlayer1Id);
			return null; 
		}
		//p2 needs to be checked
		if(p2==null)
		{
			message.addErrorMessage(badPlayer2Id);
			return null; 
		}
		//Check to see if gameId is valid
		if(gameId == null) {
			message.addErrorMessage(badGameId);
			return null;
		}
	
		GameDataObject dataGameToCreate= new GameDataObject(-1, domainGameToCreate.gameTypeId, -1, "Setup", domainGameToCreate.player1Id, domainGameToCreate.player2Id);
		GameDataObject dataGameCreated=GameDataAccess.createGame(dataGameToCreate); //Create game needs to be implemented
		GameDomainObject domainGameCreated=new GameDomainObject(dataGameCreated);

		//Create board
		BoardDomainObject boardCreated = BoardModel.CreateBoard(message, dataGameCreated.id, dataGameCreated.gameTypeId);
		domainGameCreated.board = boardCreated;

		return domainGameCreated;
	}

	public static GameDomainObject GuessWord(Message message, int gameId, int playerId, String word) {
		//This needs to be implemented.

		boolean inDictionary = DictionaryDataAccess.validateWordInDictionary(word);
		boolean validForBoard = BoardModel.ValidateWordForBoard(message, gameId, word);

		WordDomainObject wd=new WordDomainObject(gameId,playerId,word);
		wd=WordModel.GuessWord(message, wd);

		if(inDictionary && validForBoard){
			GameDataObject g=GameDataAccess.getGameById(gameId);
			GameDomainObject domainGameCreated=new GameDomainObject(g);
			if(domainGameCreated.player1Id==playerId){
				domainGameCreated.player1Words.add(wd);
			}
			else if(domainGameCreated.player2Id==playerId){
				domainGameCreated.player2Words.add(wd);
			}
			return domainGameCreated;
		}
		return null;

	}


}
