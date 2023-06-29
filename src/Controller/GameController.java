package Controller;

import java.util.ArrayList;


import DomainObject.GameDomainObject;
import DomainObject.WordDomainObject;
import Model.GameModel;
import Model.WordModel;
import restService.Message;
import restService.Request.CreateGameRequest;
import restService.Request.GuessWordRequest;
import restService.Response.GameResponse;

public class GameController {
	//The GameController will convert the request from the REST Request and the Domain Model.
	//Then it will call the Model or Service, and convert the response from the Domain model
	//to the REST Response.
	public static ArrayList<GameResponse> getAllGames(Message message) {
		try {
			ArrayList<GameDomainObject> domain = GameModel.GetAllGameSummaries(message);
			ArrayList<GameResponse> response = GameResponse.MapList(domain);
			return response;
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}
	}
	public static GameResponse getGameByGameIdAndPlayerId(Message message, int gameId, int playerId) {
		try {
			GameDomainObject domain = GameModel.GetGameDetailsByGameIdandPlayerId(message, gameId, playerId);
			GameResponse response = new GameResponse(domain);
			return response;
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}
	}
	public static GameResponse createGame(Message message, CreateGameRequest request) {
		try {
			GameDomainObject newGame = new GameDomainObject(request);

			GameDomainObject domain = GameModel.CreateGame(message, newGame);
			return getGameByGameIdAndPlayerId(message, domain.id, request.getPlayer1Id());
		} catch (Exception ex) {
			message.addErrorMessage(ex.getMessage());
			return null;
		}

	}

	public static GameResponse guessWord(Message message, int gameId, GuessWordRequest request) {
        try {
            WordDomainObject newWord = new WordDomainObject(gameId, request.getPlayerId(), request.getWord());

            WordDomainObject domain = WordModel.GuessWord(message, newWord);
            return getGameByGameIdAndPlayerId(message, gameId, request.getPlayerId());
        } catch (Exception ex) {
            message.addErrorMessage(ex.getMessage());
            return null;
        }
	}
}

