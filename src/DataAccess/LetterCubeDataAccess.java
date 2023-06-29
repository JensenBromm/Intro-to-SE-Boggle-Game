package DataAccess;

import java.util.ArrayList;

import DataObject.LetterCubeDataObject;

public class LetterCubeDataAccess {

	private static ArrayList<LetterCubeDataObject> letterCubes;

	public LetterCubeDataAccess() {
		initialize();
	}

	private void initialize() {
		letterCubes = new ArrayList<LetterCubeDataObject>();
		
		letterCubes.add(new LetterCubeDataObject(1, 1, "A", "A", "E", "E", "G", "N"));
		letterCubes.add(new LetterCubeDataObject(1, 2, "A", "B", "B", "J", "O", "O"));
		letterCubes.add(new LetterCubeDataObject(1, 3, "A", "C", "H", "O", "P", "S"));
		letterCubes.add(new LetterCubeDataObject(1, 4, "A", "F", "F", "K", "P", "S"));
		letterCubes.add(new LetterCubeDataObject(1, 5, "A", "O", "O", "T", "T", "W"));
		letterCubes.add(new LetterCubeDataObject(1, 6, "C", "I", "M", "O", "T", "U"));
		letterCubes.add(new LetterCubeDataObject(1, 7, "D", "E", "I", "L", "R", "X"));
		letterCubes.add(new LetterCubeDataObject(1, 8, "D", "E", "L", "R", "V", "Y"));
		letterCubes.add(new LetterCubeDataObject(1, 9, "D", "I", "S", "T", "T", "Y"));
		letterCubes.add(new LetterCubeDataObject(1, 10, "E", "E", "G", "H", "N", "W"));
		letterCubes.add(new LetterCubeDataObject(1, 11, "E", "E", "I", "N", "S", "U"));
		letterCubes.add(new LetterCubeDataObject(1, 12, "E", "H", "R", "T", "V", "W"));
		letterCubes.add(new LetterCubeDataObject(1, 13, "E", "I", "O", "S", "S", "T"));
		letterCubes.add(new LetterCubeDataObject(1, 14, "E", "L", "R", "T", "T", "Y"));
		letterCubes.add(new LetterCubeDataObject(1, 15, "H", "I", "M", "N", "U", "Qu"));
		letterCubes.add(new LetterCubeDataObject(1, 16, "H", "L", "N", "N", "R", "Z")); 

	}


	public static ArrayList<LetterCubeDataObject> getAllLetterCubesForGameType(int gameTypeId) {
		ArrayList<LetterCubeDataObject> letterCubeList = new ArrayList<LetterCubeDataObject>();

		for (int i=0; i < letterCubes.size(); i++) {
			if (letterCubes.get(i).gameTypeId == gameTypeId) {
				letterCubeList.add(letterCubes.get(i));
			}
		}
		
		return letterCubeList;
	}

}