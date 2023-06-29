package Model;
import java.util.ArrayList;

import DataAccess.GameDataAccess;
import DataAccess.PlayerDataAccess;
import DataAccess.WordDataAccess;
import DataObject.GameDataObject;
import DataObject.PlayerDataObject;
import DataObject.WordDataObject;
import DomainObject.WordDomainObject;
import restService.Message;


public class WordModel {
	
	public static WordDomainObject getWordById(Message message, int id) {
		WordDataObject wordData = WordDataAccess.getWordById(id);
		WordDomainObject wordDomain = new WordDomainObject(wordData);
		return wordDomain;
	}

	public static ArrayList<WordDomainObject> getAllWordsForGameAndPlayer(Message messasge, int gameId, int playerId) {
		ArrayList<WordDataObject> wordDataList = WordDataAccess.getAllWordsForGameAndPlayer(gameId, playerId);
		ArrayList<WordDomainObject> wordDomainList = WordDomainObject.MapList(wordDataList);
		return wordDomainList;
	}

	public static WordDomainObject GuessWord(Message message, WordDomainObject domainWordToCreate) {

		//Check if the game id, player id , and word are inputted
		if(domainWordToCreate.gameId<=0 || domainWordToCreate.playerId<=0 || domainWordToCreate.word.equals(null)){
			message.addErrorMessage("All parameters must be provided.");
			return null;
		}

		PlayerDataObject p=PlayerDataAccess.getPlayerById(domainWordToCreate.playerId);
		GameDataObject g=GameDataAccess.getGameById(domainWordToCreate.gameId);
		
		//Make sure that the game exist
		if(g==null){
			message.addErrorMessage("The Game Id provided is an invalid game id.");
			return null;
		}

		//Check if the player doesnt exist or that the player is not apart of the game
		if(p==null || !(domainWordToCreate.playerId==g.player1Id || domainWordToCreate.playerId==g.player2Id)){
			message.addErrorMessage("The player provided is not valid for the game.");
			return null;
		}

		//Check if the game is marked as complete
		if(g.gameStatus.equals("Complete")){
			message.addErrorMessage("This game is already completed.");
			return null;
		}
	
		//validate the word is >= 4 characters long and only english letters
		ValidateWord(message, domainWordToCreate.word);
		
		// if All validations pass create the word object
		WordDataObject dataWordToCreate=new WordDataObject(-1, domainWordToCreate.gameId, p.id, domainWordToCreate.word, true, score(domainWordToCreate.word));
		WordDataObject dataWordCreated=WordDataAccess.createWord(dataWordToCreate);
		WordDomainObject domainWordCreated=new WordDomainObject(dataWordCreated);
		return domainWordCreated;
	}

	public static boolean ValidateWord(Message message, String word) {
		String regex="^[a-zA-Z]{4,}"; //checks if it is only letters and numbers
	    
	    if(word.matches(regex) && word.length()>=4){
	        return true;
	    }
		message.addErrorMessage("The word is not valid.  It must be at least 4 characters long and include only letters.");
	    return false;	
	}

	public static int score(String word){

		switch(word.length()){
			case (4): return 1;
			
			case(5): return 2;

			case(6): return 3;

			case(7): return 5;

			default: return 11;
		}
	}

}
