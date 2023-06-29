import static org.junit.Assert.assertTrue;

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

public class S2CreateGameTest {
  
    @Before
    public void Setup() {
        LetterCubeDataAccess GameTypeShipTypeDataAccess = new LetterCubeDataAccess();
		GameTypeDataAccess GameTypeDataAccess = new GameTypeDataAccess();
		PlayerDataAccess playerDataAccess = new PlayerDataAccess();
		WordDataAccess shipDataAccess = new WordDataAccess();
		BoardDataAccess boardDataAccess = new BoardDataAccess();
		GameDataAccess gameDataAccess = new GameDataAccess();
    }	


    /*
        * "Create Game - Valid", 
        * "Call createGame with a valid player1, player2, and gametypeId.  Verify the return id is correct.", 
        */
    @Test
    public void Test_CreateGame_Valid() {

        CreateGameRequest request;
        int player1id, player2id, gametypeid;
        GameResponse game;

        //Setup Part 1 of the test.
        player1id = 1;
        player2id = 2;
        gametypeid = 1;
        request = new CreateGameRequest(player1id, player2id, gametypeid);
        Message message = new Message();
    
        game = GameController.createGame(message, request);

        assertTrue("Create Game should have been successful", message.getErrorMessage().size() == 0);
        int nextId = game.getGameId() + 1;

        //Setup Part 2 of the test.		
        player1id = 1;
        player2id = 2;
        gametypeid = 1;
        request = new CreateGameRequest(player1id, player2id, gametypeid);
        message = new Message();
        
        game = GameController.createGame(message, request);
        assertTrue("Create Game should have been successful", message.getErrorMessage().size() == 0 );
        assertTrue("created game id is not sequential.", game.getGameId() == nextId );
        assertTrue("Game status should be returned as 'Setup'", game.getStatus() == "Setup");

        assertTrue(String.format("Response player1id should be %d", player1id), game.getPlayer1Id() == player1id);
        assertTrue(String.format("Response player2id should be %d", player2id), game.getPlayer2Id() == player2id);
        assertTrue(String.format("Response gametypeid should be %d", gametypeid), game.getGameTypeId() == gametypeid);

    }

    /*
        * "Create Game - Missing fields", 
        * "Call createGame with a missing player1, player2, or gametypeId.  Verify the proper error is returned.", 
        */
    @Test
    public void Test_CreateGame_MissingFields() {
        //Setup();

        CreateGameRequest request;
        int player1id, player2id, gametypeid;
        GameResponse game;

        //Setup Part 1 of the test.

        player1id = 0;
        player2id = 2;
        gametypeid = 1;
        request = new CreateGameRequest(player1id, player2id, gametypeid);

        Message message = new Message();
    
        game = GameController.createGame(message, request);

        assertTrue("Service should have failed, player 1 missing", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("All parameters must be provided."));

        //Setup Part 2 of the test.		
        player1id = 1;
        player2id = 0;
        gametypeid = 1;
        request = new CreateGameRequest(player1id, player2id, gametypeid);
        message = new Message();
        
        game = GameController.createGame(message, request);

        assertTrue("Service should have failed, player 2 missing", message.getErrorMessage().size() > 0);
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("All parameters must be provided.") );

        //Setup Part 3 of the test.		
        player1id = 1;
        player2id = 2;
        gametypeid = 0;
        request = new CreateGameRequest(player1id, player2id, gametypeid);
        message = new Message();
        
        game = GameController.createGame(message, request);

        assertTrue("Service should have failed, game type missing", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("All parameters must be provided."));
    
    }


    /*
        * "Create Game - Invalid fields", 
        * "Call createGame with an invalid player1, player2, or gametypeId.  Verify the proper error is returned.", 
        */
    @Test
    public void Test_CreateGame_InvalidFields() {
        //Setup();

        CreateGameRequest request;
        int player1id, player2id, gametypeid;
        GameResponse game;

        //Setup Part 1 of the test.
        player1id = 1645;
        player2id = 2;
        gametypeid = 1;
        request = new CreateGameRequest(player1id, player2id, gametypeid);
        Message message = new Message();
    
        game = GameController.createGame(message, request);

        assertTrue("Service should have failed, player 1 is invalid", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The Player 1 provided is an invalid player.") );

        //Setup Part 2 of the test.		
        player1id = 1;
        player2id = 1645;
        gametypeid = 1;
        request = new CreateGameRequest(player1id, player2id, gametypeid);
        message = new Message();
        
        game = GameController.createGame(message, request);

        assertTrue("Service should have failed, player 2 is invalid", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The Player 2 provided is an invalid player.") );

        //Setup Part 3 of the test.		
        player1id = 1;
        player2id = 2;
        gametypeid = 1645;
        request = new CreateGameRequest(player1id, player2id, gametypeid);
        message = new Message();
        
        game = GameController.createGame(message, request);

        assertTrue("Service should have failed, game type is invalid", message.getErrorMessage().size() > 0 );
        assertTrue("Incorrect Error Message provided.", message.getErrorMessage().contains("The Game Type provided is an invalid game type.") );
        
    }
}
        