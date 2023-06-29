import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

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
import restService.Response.GameResponse.Board;

public class S2_1SetupBoardTest {
  
    @Before
    public void Setup() {
        LetterCubeDataAccess GameTypeShipTypeDataAccess = new LetterCubeDataAccess();
		GameTypeDataAccess GameTypeDataAccess = new GameTypeDataAccess();
		PlayerDataAccess playerDataAccess = new PlayerDataAccess();
		WordDataAccess shipDataAccess = new WordDataAccess();
		BoardDataAccess boardDataAccess = new BoardDataAccess();
		GameDataAccess gameDataAccess = new GameDataAccess();
    }	


    public static boolean validateboard(String[][] board) {


        //lettercubes are all the possible letter cubes
        String[][] lettercubes = { {"A", "A", "E", "E", "G", "N"},
                                   {"A", "B", "B", "J", "O", "O"},
                                   {"A", "C", "H", "O", "P", "S"},
                                   {"A", "F", "F", "K", "P", "S"},
                                   {"A", "O", "O", "T", "T", "W"},
                                   {"C", "I", "M", "O", "T", "U"},  
                                   {"D", "E", "I", "L", "R", "X"},
                                   {"D", "E", "L", "R", "V", "Y"},
                                   {"D", "I", "S", "T", "T", "Y"},
                                   {"E", "E", "G", "H", "N", "W"},
                                   {"E", "E", "I", "N", "S", "U"},
                                   {"E", "H", "R", "T", "V", "W"},
                                   {"E", "I", "O", "S", "S", "T"},
                                   {"E", "L", "R", "T", "T", "Y"},
                                   {"H", "I", "M", "N", "U", "Qu"},
                                   {"H", "L", "N", "N", "R", "Z"} };


        //possibleLetters contains a 4x4 grid representing each letter in the board, and which letter cubes match to this letter.
        TreeSet<Integer>[][] possibleLetters = new TreeSet[4][4];

        //initialize possibleLetters
        for (int col = 0; col < 4 ; col++) {
            for (int row = 0; row < 4; row++) {
                possibleLetters[col][row] = new TreeSet<Integer>();
            }
        }


        for (int col = 0; col < 4 ; col++) {
            for (int row = 0; row < 4; row++) {
                String letter = board[col][row];
                for (int cube = 0; cube < 16; cube++) {
                    for (int side = 0; side < 6; side++) {
                        if (letter.equals(lettercubes[cube][side])) {
                            possibleLetters[col][row].add(cube);
                        }
                    }
                }
            }
        }

        int col = 0;
        int row = 0;


        for (int i : possibleLetters[col][row]) {
            TreeSet<Integer>[][] copy = deepCopyPossibleLetters(possibleLetters);
            copy[col][row].clear();

            for (int tcol = 0; tcol < 4 ; tcol++) {
                for (int trow = 0; trow < 4; trow++) {
                    copy[tcol][trow].remove(i);
                }
            }
    
            if (validateLetter(col, row, i, copy)) {
                return true;
            }
        }

        return false;
    }

    public static boolean validateLetter(int col, int row, int value, TreeSet<Integer>[][] possibleLetters) {
        row++;
        if (row >= 4) {
            row = 0;
            col++;
        }
        if (col >= 4)
          return true;


        if (possibleLetters[col][row].isEmpty())
            return false;

        for (int i : possibleLetters[col][row]) {
            TreeSet<Integer>[][] copy = deepCopyPossibleLetters(possibleLetters);
            copy[col][row].clear();         
            boolean stop = false;
            for (int tcol = 0; tcol < 4 ; tcol++) {
                for (int trow = 0; trow < 4; trow++) {
                    copy[tcol][trow].remove(i);

                    int a = tcol*4 + trow;
                    int b = col*4 + row;
                    if ((tcol *4 + trow > col*4 + row ) && copy[tcol][trow].isEmpty())
                        stop = true;
                    if (stop) break;
                }
                if (stop) break;
            }

            if (!stop && validateLetter(col, row, i, copy)){
                return true;
            }
        }


        return false;
    }
    


    public static TreeSet<Integer>[][] deepCopyPossibleLetters (TreeSet<Integer>[][] possibleLetters) {
        TreeSet<Integer>[][] deepCopy = new TreeSet[4][4];

        //Create Deep Copy
        for (int col = 0; col < 4 ; col++) {
            for (int row = 0; row < 4; row++) {
                deepCopy[col][row] = new TreeSet<Integer>();
                for (int i : possibleLetters[col][row]) {
                    deepCopy[col][row].add(i);
                }
            }
        }
        return deepCopy;

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
        
        Board board = game.getBoard();

        assertTrue("Board should be populated", board != null);

        assertTrue("g1 should not be empty", !board.getG1().isEmpty());
        assertTrue("g2 should not be empty", !board.getG2().isEmpty());
        assertTrue("g3 should not be empty", !board.getG3().isEmpty());
        assertTrue("g4 should not be empty", !board.getG4().isEmpty());
        assertTrue("g5 should not be empty", !board.getG5().isEmpty());
        assertTrue("g6 should not be empty", !board.getG6().isEmpty());
        assertTrue("g7 should not be empty", !board.getG7().isEmpty());
        assertTrue("g8 should not be empty", !board.getG8().isEmpty());
        assertTrue("g9 should not be empty", !board.getG9().isEmpty());
        assertTrue("g10 should not be empty", !board.getG10().isEmpty());
        assertTrue("g11 should not be empty", !board.getG11().isEmpty());
        assertTrue("g12 should not be empty", !board.getG12().isEmpty());
        assertTrue("g13 should not be empty", !board.getG13().isEmpty());
        assertTrue("g14 should not be empty", !board.getG14().isEmpty());
        assertTrue("g15 should not be empty", !board.getG15().isEmpty());
        assertTrue("g16 should not be empty", !board.getG16().isEmpty());

        assertTrue(String.format("Response player1id should be %d", player1id), game.getPlayer1Id() == player1id);
        assertTrue(String.format("Response player2id should be %d", player2id), game.getPlayer2Id() == player2id);
        assertTrue(String.format("Response gametypeid should be %d", gametypeid), game.getGameTypeId() == gametypeid);

    }



    /*
    * "Create Game - Valid", 
    * "Call createGame with a valid player1, player2, and gametypeId.  Verify the return id is correct.", 
    */
    @Test
    public void Test_CreateGame_CheckBoard() {

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
        
        Board board = game.getBoard();

        assertTrue("Board should be populated", board != null);


        String[][] boardArr = {     {board.getG1(),board.getG2(),board.getG3(),board.getG4()},
                                    {board.getG5(),board.getG6(),board.getG7(),board.getG8()},
                                    {board.getG9(),board.getG10(),board.getG11(),board.getG12()},
                                    {board.getG13(),board.getG14(),board.getG15(),board.getG16()}  };

        boolean valid = validateboard(boardArr);
        assertTrue("Board is not correct", valid);


    }



 /*
    * "Create Game - Valid", 
    * "Call createGame with a valid player1, player2, and gametypeId.  Verify the return id is correct.", 
    */
    @Test
    public void Test_CreateGame_Check100Boards() {

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
        
        for (int i = 0; i < 100; i++) {
            game = GameController.createGame(message, request);
            assertTrue("Create Game should have been successful", message.getErrorMessage().size() == 0 );
            assertTrue("Game status should be returned as 'Setup'", game.getStatus() == "Setup");
            
            Board board = game.getBoard();

            assertTrue("Board should be populated", board != null);


            String[][] boardArr = {     {board.getG1(),board.getG2(),board.getG3(),board.getG4()},
                                        {board.getG5(),board.getG6(),board.getG7(),board.getG8()},
                                        {board.getG9(),board.getG10(),board.getG11(),board.getG12()},
                                        {board.getG13(),board.getG14(),board.getG15(),board.getG16()}  };
            boolean valid = validateboard(boardArr);
            assertTrue("Board " + i + "is not correct", valid);
        }

    }

    
    
}
        