import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Controller.GameController;
import DataAccess.BoardDataAccess;
import DataAccess.DictionaryDataAccess;
import DataAccess.GameDataAccess;
import DataAccess.GameTypeDataAccess;
import DataAccess.LetterCubeDataAccess;
import DataAccess.PlayerDataAccess;
import DataAccess.WordDataAccess;
import restService.Message;
import restService.Request.CreateGameRequest;
import restService.Request.GuessWordRequest;
import restService.Request.RegisterPlayerRequest;
import restService.Response.GameResponse;

public class S3_1SaveWordTest {
  
    void Setup() {

        LetterCubeDataAccess GameTypeShipTypeDataAccess = new LetterCubeDataAccess();
		GameTypeDataAccess GameTypeDataAccess = new GameTypeDataAccess();
		PlayerDataAccess playerDataAccess = new PlayerDataAccess();
		WordDataAccess shipDataAccess = new WordDataAccess();
		BoardDataAccess boardDataAccess = new BoardDataAccess();
		GameDataAccess gameDataAccess = new GameDataAccess();
        DictionaryDataAccess dictionaryDataAccess = new DictionaryDataAccess();

    }	


    /*
        * 3.1.1, 
        * "Save Word - Valid", 
        * "Call Guess Word with a valid parameters and a valid word.  Verify the word is created successfully", 
        */
    @Test
    public void Test_SaveWord_Valid() {
        Setup();

        GuessWordRequest request;
        int player1Id, player2Id, gametypeid, gameId;
        GameResponse game;

        //Setup Part 1 of the test.
        player1Id = 1;
        player2Id = 2;
        gameId = 2;
        request = new GuessWordRequest(player1Id, "COMPUTER");
        Message message = new Message();
    
        game = GameController.guessWord(message, gameId, request);

        assertTrue("Guess Word should have been successful", message.getErrorMessage().size() == 0);
        assertTrue("Player1Words should not be empty", !game.getPlayer1Words().isEmpty());

        boolean foundTheWord = false;
        for(GameResponse.Word w : game.getPlayer1Words()) {
            if (w.getWord().equals("COMPUTER")){
                assertTrue("The word should be save with isValid = true.", w.getIsValid());
                assertTrue("The word should be save with a score.", w.getScore()>=0);
                foundTheWord = true;
            }
        }   
       
        assertTrue("The word 'COMPUTER' was not found in the Player's words", foundTheWord);

    }

    /*
        * 3.1.2, 
        * "Save Word - Fail - Invalid Game Id", 
        * "Call Guess Word with an invalid GameId.  Verify the correct error is returned.", 
        */
        @Test
        public void Test_SaveWord_Fail_InvalidGameId() {
            Setup();
    
            GuessWordRequest request;
            int player1Id, player2Id, gametypeid, gameId;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1Id = 1;
            player2Id = 2;
            gameId = 890;
            request = new GuessWordRequest(player1Id, "COMPUTER");
            Message message = new Message();
        
            game = GameController.guessWord(message, gameId, request);
    
            assertTrue("Guess Word should not have been successful", !message.getErrorMessage().isEmpty());
            assertTrue("Guess Word should have returned the proper error message", message.getErrorMessage().contains("The Game Id provided is an invalid game id."));
           // assertTrue("Player1Words should be empty", game.getPlayer1Words().isEmpty());
   
        }


    /*
        * 3.1.3, 
        * "Save Word - Faile - Invalid Player Id for game", 
        * "Call Guess Word with an invalid PlayerId for the game.  Verify the correct error is returned.", 
        */
        @Test
        public void Test_SaveWord_Fail_InvalidPlayerIdForGameId() {
            Setup();
    
            GuessWordRequest request;
            int player1Id, player2Id, gametypeid, gameId;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1Id = 3;
            gameId = 2;
            request = new GuessWordRequest(player1Id, "COMPUTER");
            Message message = new Message();
        
            game = GameController.guessWord(message, gameId, request);
    
            assertTrue("Guess Word should not have been successful", !message.getErrorMessage().isEmpty());
            assertTrue("Guess Word should have returned the proper error message", message.getErrorMessage().contains("The player provided is not valid for the game."));
            //assertTrue("Player1Words should be empty", game.getPlayer1Words().isEmpty());
   
        }


       /*
        * 3.1.4, 
        * "Save Word - Fail - Complete Game", 
        * "Call Guess Word with an invalid PlayerId for the game.  Verify the correct error is returned.", 
        */
        @Test
        public void Test_SaveWord_Fail_CompleteGame() {
            Setup();
    
            GuessWordRequest request;
            int player1Id, player2Id, gametypeid, gameId;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1Id = 1;
            gameId = 1;
            request = new GuessWordRequest(player1Id, "COMPUTER");
            Message message = new Message();
        
            game = GameController.guessWord(message, gameId, request);
    
            assertTrue("Guess Word should not have been successful", !message.getErrorMessage().isEmpty());
            assertTrue("Guess Word should have returned the proper error message", message.getErrorMessage().contains("This game is already completed."));
          //  assertTrue("Player1Words should be empty", game.getPlayer1Words().isEmpty());
   
        }

       /*
        * 3.1.5, 
        * "Save Word - Fail - Short Word", 
        * "Call Guess Word with an invalid word (length 3).  Verify the correct error is returned.", 
        */
        @Test
        public void Test_SaveWord_Fail_ShortWord() {
            Setup();
    
            GuessWordRequest request;
            int player1Id, player2Id, gametypeid, gameId;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1Id = 1;
            gameId = 2;
            request = new GuessWordRequest(player1Id, "OUT");
            Message message = new Message();
        
            game = GameController.guessWord(message, gameId, request);
    
            assertTrue("Guess Word should not have been successful", !message.getErrorMessage().isEmpty());
            
            assertTrue("Guess Word should have returned the proper error message", message.getErrorMessage().contains("The word is not valid.  It must be at least 4 characters long and include only letters."));
          //  assertTrue("Player1Words should be empty", game.getPlayer1Words().isEmpty());
   
        }

       /*
        * 3.1.6, 
        * "Save Word - Fail - Non Letter", 
        * "Call Guess Word with an invalid character.  Verify the correct error is returned.", 
        */
        @Test
        public void Test_SaveWord_Fail_NonLetter() {
            Setup();
    
            GuessWordRequest request;
            int player1Id, player2Id, gametypeid, gameId;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1Id = 1;
            gameId = 2;
            request = new GuessWordRequest(player1Id, "REAL-TIME");
            Message message = new Message();
        
            game = GameController.guessWord(message, gameId, request);
    
            assertTrue("Guess Word should not have been successful", !message.getErrorMessage().isEmpty());
            
            assertTrue("Guess Word should have returned the proper error message", message.getErrorMessage().contains("The word is not valid.  It must be at least 4 characters long and include only letters."));
       //     assertTrue("Player1Words should be empty", game.getPlayer1Words().isEmpty());
   
        }

    
        
}
        