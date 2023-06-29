package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashSet;

import DataAccess.BoardDataAccess;
import DataAccess.LetterCubeDataAccess;
import DataObject.BoardDataObject;
import DataObject.LetterCubeDataObject;
import DomainObject.BoardDomainObject;
import restService.Message;




public class BoardModel {
	
	public static BoardDomainObject GetBoardDetailsById(Message message, int boardId) {
		BoardDataObject boardData = BoardDataAccess.getBoardById(boardId);
		BoardDomainObject boardDomain = new BoardDomainObject(boardData);

		return boardDomain;
	}

	public static BoardDomainObject GetBoardDetailsByGameId(Message message, int gameId) {
		BoardDataObject boardData = BoardDataAccess.getBoardByGameId(gameId);
		BoardDomainObject boardDomain = new BoardDomainObject(boardData);

		return boardDomain;
	}

	public static BoardDomainObject CreateBoard(Message message, int gameId, int gameTypeId) {

		//This needs to be implemented.
		ArrayList<LetterCubeDataObject> letterCubes = LetterCubeDataAccess.getAllLetterCubesForGameType(gameTypeId);
		String[] g = new String[16];

		for (int i = 0; i < g.length; i++) {
			int selector = new Random().nextInt(letterCubes.size());
			int side = new Random().nextInt(6);
			LetterCubeDataObject cube = letterCubes.remove(selector);

			switch (side) {
				case 0:
					g[i] = cube.side1;
					break;
				case 1:
					g[i] = cube.side2;
					break;
				case 2:
					g[i] = cube.side3;
					break;
				case 3:
					g[i] = cube.side4;
					break;
				case 4:
					g[i] = cube.side5;
					break;
				case 5:
					g[i] = cube.side6;
					break;
			}
		}

		BoardDataObject boardToCreate = new BoardDataObject(gameId, gameTypeId, g[0], g[1], g[2], g[3], 
																				g[4], g[5], g[6], g[7],
																				g[8], g[9], g[10], g[11],
																				g[12], g[13], g[14], g[15]);
		
		BoardDomainObject boardCreated = new BoardDomainObject(BoardDataAccess.createBoard(boardToCreate));

		return boardCreated;
	}


	public static Boolean ValidateWordForBoard(Message message, int gameId, String word) {
		
		//This needs to be implemented.
		return true;

	}


}
