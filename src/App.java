import DataAccess.BoardDataAccess;
import DataAccess.GameDataAccess;
import DataAccess.GameTypeDataAccess;
import DataAccess.LetterCubeDataAccess;
import DataAccess.PlayerDataAccess;
import DataAccess.WordDataAccess;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Boggle is running");

        LetterCubeDataAccess GameTypeShipTypeDataAccess = new LetterCubeDataAccess();
		GameTypeDataAccess GameTypeDataAccess = new GameTypeDataAccess();
		PlayerDataAccess playerDataAccess = new PlayerDataAccess();
		WordDataAccess shipDataAccess = new WordDataAccess();
		BoardDataAccess boardDataAccess = new BoardDataAccess();
		GameDataAccess gameDataAccess = new GameDataAccess();
    }
}
