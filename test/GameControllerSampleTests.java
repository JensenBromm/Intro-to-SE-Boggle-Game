import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Controller.GameController;
import DataAccess.BoardDataAccess;
import DataAccess.GameDataAccess;
import DataAccess.GameTypeDataAccess;
import DataAccess.LetterCubeDataAccess;
import DataAccess.PlayerDataAccess;
import DataAccess.WordDataAccess;
import restService.Message;
import restService.Request.CreateGameRequest;
import restService.Response.GameResponse;

public class GameControllerSampleTests {
  
    @Before
    public void Setup() {
        LetterCubeDataAccess GameTypeShipTypeDataAccess = new LetterCubeDataAccess();
		GameTypeDataAccess GameTypeDataAccess = new GameTypeDataAccess();
		PlayerDataAccess playerDataAccess = new PlayerDataAccess();
		WordDataAccess shipDataAccess = new WordDataAccess();
		BoardDataAccess boardDataAccess = new BoardDataAccess();
		GameDataAccess gameDataAccess = new GameDataAccess();
    }	

    @Test
    public void Test_GetAllGames() {

        ArrayList<GameResponse> games;
        Message message = new Message();
    
        games = GameController.getAllGames(message);

        assertTrue("Get all games should have been successful", message.getErrorMessage().size() == 0);
        assertTrue("2 games should have been returned", games.size() == 2);

    }

    @Test
    public void Test_GetGameBy() {

        GameResponse game;
        int gameId = 1;
        int playerId = 1;
        Message message = new Message();
    

        game = GameController.getGameByGameIdAndPlayerId(message, gameId, playerId);

        assertTrue("Get game response should have been successful", message.getErrorMessage().size() == 0);
        assertTrue("Gameboard for gameId 1 should not be null", game.getBoard() != null);
        assertTrue("Gameboard is not correct for gameId", game.getBoard().getG1().equals("L") && game.getBoard().getG6().equals("I") && game.getBoard().getG10().equals("D") );
        assertTrue("Player1 Words are not all returned", game.getPlayer1Words().size() == 6);
        assertTrue("Player2 Words are not all returned", game.getPlayer2Words().size() == 4);


    }




        @Test
        public void Test_CreateGame_ValidateBoard() {
    
            CreateGameRequest request;
            int player1id, player2id, gametypeid;
            GameResponse game;
            Message message;

            //Setup Part 1 of the test.		
            player1id = 1;
            player2id = 2;
            gametypeid = 1;
            request = new CreateGameRequest(player1id, player2id, gametypeid);
            message = new Message();
            
            game = GameController.createGame(message, request);
            assertTrue("Create Game should have been successful", message.getErrorMessage().size() == 0 );
            assertTrue("Game status should be returned as 'Setup'", game.getStatus() == "Setup");

            assertTrue(String.format("Response player1id should be %d", player1id), game.getPlayer1Id() == player1id);
            assertTrue(String.format("Response player2id should be %d", player2id), game.getPlayer2Id() == player2id);
            assertTrue(String.format("Response gametypeid should be %d", gametypeid), game.getGameTypeId() == gametypeid);
    
            assertTrue("Response winner should not be set", game.getWinner() <= 0);
    
        }
    
    
}
        