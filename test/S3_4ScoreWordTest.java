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

public class S3_4ScoreWordTest {
  
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
        * 3.4.1, 
        * "Guess Word - Valid", 
        * "Call Guess Word with a valid parameters and a valid word.  Verify the word is created successfully", 
        */
    @Test
    public void Test_GuessWord_Valid() {
        Setup();

        GuessWordRequest request;
        int player1Id, player2Id, gametypeid, gameId;
        GameResponse game;

        //Setup Part 1 of the test.
        player1Id = 1;
        player2Id = 2;
        gameId = 2;
        request = new GuessWordRequest(player1Id, "ALUMNAE");
        Message message = new Message();
    
        game = GameController.guessWord(message, gameId, request);

        assertTrue("Guess Word should have been successful", message.getErrorMessage().size() == 0);
        assertTrue("Player1Words should not be empty", !game.getPlayer1Words().isEmpty());

        boolean foundTheWord = false;
        for(GameResponse.Word w : game.getPlayer1Words()) {
            if (w.getWord().equals("ALUMNAE")){
                assertTrue("Score for 'ALUMNAE' should be 5", w.getScore() == 5);
                foundTheWord = true;
            }
        }   
       
        assertTrue("The word 'ALUMNAE' was not found in the Player's words", foundTheWord);

    }

       /*
        * 3.4.2, 
        * "Guess Word - Four Letters", 
        * "Call Guess Word with four letters.  Verify score is 1", 
        */
        @Test
        public void Test_GuessWord_FourLetters() {
            Setup();
    
            GuessWordRequest request;
            int player1Id, player2Id, gametypeid, gameId;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1Id = 1;
            player2Id = 2;
            gameId = 2;
            request = new GuessWordRequest(player1Id, "TEST");
            Message message = new Message();
        
            game = GameController.guessWord(message, gameId, request);
    
            assertTrue("Guess Word should have been successful", message.getErrorMessage().size() == 0);
            assertTrue("Player1Words should not be empty", !game.getPlayer1Words().isEmpty());

            boolean foundTheWord = false;
            for(GameResponse.Word w : game.getPlayer1Words()) {
                if (w.getWord().equals("TEST")){
                    assertTrue("Score for 'TEST' should be 1", w.getScore() == 1);
                    foundTheWord = true;
                }
            }   
           
            assertTrue("The word 'TEST' was not found in the Player's words", foundTheWord);


        }

       /*
        * 3.4.3, 
        * "Guess Word - Five Letters", 
        * "Call Guess Word with five letters.  Verify score is 2", 
        */
        @Test
        public void Test_GuessWord_FiveLetters() {
            Setup();
    
            GuessWordRequest request;
            int player1Id, player2Id, gametypeid, gameId;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1Id = 1;
            player2Id = 2;
            gameId = 2;
            request = new GuessWordRequest(player1Id, "MOUSE");
            Message message = new Message();
        
            game = GameController.guessWord(message, gameId, request);
    
            assertTrue("Guess Word should have been successful", message.getErrorMessage().size() == 0);
            assertTrue("Player1Words should not be empty", !game.getPlayer1Words().isEmpty());

            boolean foundTheWord = false;
            for(GameResponse.Word w : game.getPlayer1Words()) {
                if (w.getWord().equals("MOUSE")){
                    assertTrue("Score for 'MOUSE' should be 2", w.getScore() == 2);
                    foundTheWord = true;
                }
            }   
           
            assertTrue("The word 'MOUSE' was not found in the Player's words", foundTheWord);


        }

    
        /*
        * 3.4.4, 
        * "Guess Word - Six Letters", 
        * "Call Guess Word with six letters.  Verify score is 3", 
        */
        @Test
        public void Test_GuessWord_SixLetters() {
            Setup();
    
            GuessWordRequest request;
            int player1Id, player2Id, gametypeid, gameId;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1Id = 1;
            player2Id = 2;
            gameId = 2;
            request = new GuessWordRequest(player1Id, "INPUTS");
            Message message = new Message();
        
            game = GameController.guessWord(message, gameId, request);
    
            assertTrue("Guess Word should have been successful", message.getErrorMessage().size() == 0);
            assertTrue("Player1Words should not be empty", !game.getPlayer1Words().isEmpty());

            boolean foundTheWord = false;
            for(GameResponse.Word w : game.getPlayer1Words()) {
                if (w.getWord().equals("INPUTS")){
                    assertTrue("Score for 'INPUTS' should be 3", w.getScore() == 3);
                    foundTheWord = true;
                }
            }   
           
            assertTrue("The word 'INPUTS' was not found in the Player's words", foundTheWord);


        }
      
        /*
        * 3.4.5, 
        * "Guess Word - Seven Letters", 
        * "Call Guess Word with seven letters.  Verify score is 5", 
        */
        @Test
        public void Test_GuessWord_SevenLetters() {
            Setup();
    
            GuessWordRequest request;
            int player1Id, player2Id, gametypeid, gameId;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1Id = 1;
            player2Id = 2;
            gameId = 2;
            request = new GuessWordRequest(player1Id, "MONITOR");
            Message message = new Message();
        
            game = GameController.guessWord(message, gameId, request);
    
            assertTrue("Guess Word should have been successful", message.getErrorMessage().size() == 0);
            assertTrue("Player1Words should not be empty", !game.getPlayer1Words().isEmpty());

            boolean foundTheWord = false;
            for(GameResponse.Word w : game.getPlayer1Words()) {
                if (w.getWord().equals("MONITOR")){
                    assertTrue("Score for 'MONITOR' should be 5", w.getScore() == 5);
                    foundTheWord = true;
                }
            }   
           
            assertTrue("The word 'MONITOR' was not found in the Player's words", foundTheWord);


        }


        /*
        * 3.4.6, 
        * "Guess Word - Eight Letters", 
        * "Call Guess Word with eight letters.  Verify score is 11", 
        */
        @Test
        public void Test_GuessWord_EightLetters() {
            Setup();
    
            GuessWordRequest request;
            int player1Id, player2Id, gametypeid, gameId;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1Id = 1;
            player2Id = 2;
            gameId = 2;
            request = new GuessWordRequest(player1Id, "KEYBOARD");
            Message message = new Message();
        
            game = GameController.guessWord(message, gameId, request);
    
            assertTrue("Guess Word should have been successful", message.getErrorMessage().size() == 0);
            assertTrue("Player1Words should not be empty", !game.getPlayer1Words().isEmpty());

            boolean foundTheWord = false;
            for(GameResponse.Word w : game.getPlayer1Words()) {
                if (w.getWord().equals("KEYBOARD")){
                    assertTrue("Score for 'KEYBOARD' should be 11", w.getScore() == 11);
                    foundTheWord = true;
                }
            }   
           
            assertTrue("The word 'KEYBOARD' was not found in the Player's words", foundTheWord);


        }

        /*
        * 3.4.7, 
        * "Guess Word - Ten Letters", 
        * "Call Guess Word with ten letters.  Verify score is 11", 
        */
        @Test
        public void Test_GuessWord_TenLetters() {
            Setup();
    
            GuessWordRequest request;
            int player1Id, player2Id, gametypeid, gameId;
            GameResponse game;
    
            //Setup Part 1 of the test.
            player1Id = 1;
            player2Id = 2;
            gameId = 2;
            request = new GuessWordRequest(player1Id, "ALGORITHMS");
            Message message = new Message();
        
            game = GameController.guessWord(message, gameId, request);
    
            assertTrue("Guess Word should have been successful", message.getErrorMessage().size() == 0);
            assertTrue("Player1Words should not be empty", !game.getPlayer1Words().isEmpty());

            boolean foundTheWord = false;
            for(GameResponse.Word w : game.getPlayer1Words()) {
                if (w.getWord().equals("ALGORITHMS")){
                    assertTrue("Score for 'ALGORITHMS' should be 11", w.getScore() == 11);
                    foundTheWord = true;
                }
            }   
           
            assertTrue("The word 'ALGORITHMS' was not found in the Player's words", foundTheWord);


        }

}
        