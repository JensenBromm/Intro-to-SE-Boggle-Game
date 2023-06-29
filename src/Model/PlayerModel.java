package Model;
import java.util.ArrayList;

import DataAccess.PlayerDataAccess;
import DataObject.PlayerDataObject;
import DomainObject.PlayerDomainObject;
import restService.Message;


public class PlayerModel {
	
	public static PlayerDomainObject GetPlayerById(Message message, int id) {
		PlayerDataObject playerData = PlayerDataAccess.getPlayerById(id);
		PlayerDomainObject playerDomain = new PlayerDomainObject(playerData);
		return playerDomain;
	}

	public static ArrayList<PlayerDomainObject> GetAllPlayers(Message messasge) {
		ArrayList<PlayerDataObject> playerDataList = PlayerDataAccess.getAllPlayers();
		ArrayList<PlayerDomainObject> playerDomainList = PlayerDomainObject.MapList(playerDataList);
		return playerDomainList;
	}



	public static boolean ValidatePlayerById(int id) {
		if (PlayerDataAccess.getPlayerById(id) == null)
			return false;
		return true;
	}
	
	public static PlayerDomainObject RegisterPlayer(Message message, PlayerDomainObject domainPlayerToCreate) {
		//This needs to be implemented.

		//Verify that username is unique
		PlayerDataObject p=PlayerDataAccess.getPlayerByUsername(domainPlayerToCreate.userName); //if this is null that means username is unique
		if(p!=null){
			message.addErrorMessage("This username is already taken.");
		}
		else{ //If the username is unique verify everthing else
			//verify username is 6-12 characters long and only contains English letters and numbers
			if(!verifyLength(domainPlayerToCreate.userName) || !verifyEnglish(domainPlayerToCreate.userName)){
				message.addErrorMessage("Invalid username.  The username must contain between 6 and 12 characters and must only contain English letters and numbers.");
				return null; //If validation fails return the original object? or is it supposed to return null
			}
			//Verify Passowrd is 6-12 characters long and only contains English letters and Numbers
			if(!verifyLength(domainPlayerToCreate.password) || !verifyEnglish(domainPlayerToCreate.password)){
				message.addErrorMessage("Invalid password.  The password must contain between 6 and 12 characters and must only contain English letters and numbers.");
				return null; //If validation fails return the original object? or is it supposed to return null
			}

			PlayerDataObject dataPlayerToCreate=new PlayerDataObject(-1, domainPlayerToCreate.userName, domainPlayerToCreate.password);
			PlayerDataObject dataPlayerCreated=PlayerDataAccess.createPlayer(dataPlayerToCreate);
			PlayerDomainObject domainPlayerCreated=new PlayerDomainObject(dataPlayerCreated);

			return domainPlayerCreated;

		}
		
		return domainPlayerToCreate; //If validation fails return the original object? or is it supposed to return null
	}

	public static boolean verifyLength(String s){
		if(s.length()>=6 && s.length()<=12){
			return true;
		}

		return false;
	}

	public static boolean verifyEnglish(String s){
		String regex="^[a-zA-Z0-9]+$"; //checks if it is only letters and numbers
	    
	    if(s.matches(regex)){
	        return true;
	    }
	    return false;
	}


}
