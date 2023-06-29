package DataAccess;

import java.util.ArrayList;

import DataObject.PlayerDataObject;
import DataObject.WordDataObject;
import DomainObject.PlayerDomainObject;

public class WordDataAccess {

	private static ArrayList<WordDataObject> words;

	public WordDataAccess() {
		initialize();
	}

	private void initialize() {
		words = new ArrayList<WordDataObject>();
	
		words.add(new WordDataObject(getNextId(), 1, 1, "NIAVE", false, 0));
		words.add(new WordDataObject(getNextId(), 1, 1, "INDEX", true, 2));
		words.add(new WordDataObject(getNextId(), 1, 1, "ADAGE", true, 2));
		words.add(new WordDataObject(getNextId(), 1, 1, "BIDE", true, 1));
		words.add(new WordDataObject(getNextId(), 1, 1, "BIND", true,1));
		words.add(new WordDataObject(getNextId(), 1, 1, "GAVE", true, 1));
		
		words.add(new WordDataObject(getNextId(), 1, 2, "AGAIN", true, 2));
		words.add(new WordDataObject(getNextId(), 1, 2, "AXED", true, 1));
		words.add(new WordDataObject(getNextId(), 1, 2, "EVADE", true, 2));
		words.add(new WordDataObject(getNextId(), 1, 2, "BIND", true, 1));
			
	}

	private static int getNextId() {
		return words.size()+1;
	}

 	public static WordDataObject getWordById(int id) {
		
		for (int i=0; i < words.size(); i++) {
			if (words.get(i).id == id) {
				return words.get(i);
			}
		}
		return null;
	}

	public static ArrayList<WordDataObject> getAllWordsForGameAndPlayer(int gameId, int playerId) {
		ArrayList<WordDataObject> wordList = new ArrayList<WordDataObject>();

		for (int i=0; i < words.size(); i++) {
			if (words.get(i).gameId == gameId && words.get(i).playerId == playerId) {
				wordList.add(words.get(i));
			}
		}
		
		return wordList;
	}

	public static WordDataObject createWord(WordDataObject data) {
		data.id=getNextId();
		words.add(data);
		return getWordById(data.id);
	}

}