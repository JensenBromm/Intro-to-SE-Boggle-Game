package DataObject;

public class LetterCubeDataObject {

	public int gameTypeId;
	public int diceNumber;
	public String side1;
	public String side2;
	public String side3;
	public String side4;
	public String side5;
	public String side6;

	public LetterCubeDataObject (int gameTypeId, int diceNumber, String side1, String side2, String side3, String side4, String side5, String side6) {
		this.gameTypeId = gameTypeId;
		this.diceNumber = diceNumber;
		this.side1 = side1;
		this.side2 = side2; 
		this.side3 = side3; 
		this.side4 = side4; 
		this.side5 = side5;
		this.side6 = side6;
	}

}