package DataAccess;

import java.util.ArrayList;

import DataObject.BoardDataObject;

public class BoardDataAccess {

	private static ArrayList<BoardDataObject> boards;

	public BoardDataAccess() {
		initialize();
	}

	private void initialize() {
		boards = new ArrayList<BoardDataObject>();
	
		//Default Data
		boards.add( new BoardDataObject(1, 1, "L", "I", "G", "E", 
														"N", "I" ,"A", "V", 
														"B", "D" ,"D", "G", 
														"I", "E" ,"X", "A")); 
		boards.add( new BoardDataObject(2, 2, "A", "B", "H", "R", 
														"S", "L" ,"G", "A", 
														"Y", "N" ,"U", "A", 
														"L", "M" ,"N", "E")); 
		boards.add( new BoardDataObject(3, 3, "A", "O", "S", "F", 
														"T", "M" ,"L", "L", 
														"Y", "W" ,"S", "H", 
														"I", "E" ,"I", "Z")); 
		boards.add( new BoardDataObject(4, 4, "M", "E", "E", "E", 
														"S", "V" ,"N", "T", 
														"A", "O" ,"G", "L", 
														"I", "I" ,"F", "E")); 
	}



	private static int getNextId() {
		return boards.size()+1;
	}


	public static BoardDataObject getBoardById(int id) {
		
		for (int i=0; i < boards.size(); i++) {
			if (boards.get(i).id == id) {
				return boards.get(i);
			}
		}
		
		return null;
	}

	public static BoardDataObject getBoardByGameId(int gameId) {
		
		for (int i=0; i < boards.size(); i++) {
			if (boards.get(i).gameId == gameId) {
				return boards.get(i);
			}
		}
		
		return null;
	}

	public static BoardDataObject createBoard(BoardDataObject data) {
			//This needs to be implemented.
			data.id = getNextId();
			data.gameId = data.id;
			boards.add(data);
			
			return getBoardByGameId(data.gameId);
	}

}