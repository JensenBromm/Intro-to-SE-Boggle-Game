package DomainObject;

import java.util.ArrayList;

import DataObject.LetterCubeDataObject;
import restService.Response.GameTypeResponse;

public class LetterCubeDomainObject {

	public int gameTypeId;
	public int diceNumber;
	public String side1;
	public String side2;
	public String side3;
	public String side4;
	public String side5;
	public String side6;

	public LetterCubeDomainObject(LetterCubeDataObject data) {
		this.gameTypeId = data.gameTypeId;
		this.diceNumber = data.diceNumber;
		this.side1 = data.side1;
		this.side1 = data.side1;
		this.side1 = data.side1;
		this.side1 = data.side1;
		this.side1 = data.side1;
		
	}
	
	public static ArrayList<LetterCubeDomainObject> MapFromDataList(ArrayList<LetterCubeDataObject> dataList) {
		ArrayList<LetterCubeDomainObject> domainObjectList = new ArrayList<LetterCubeDomainObject>();
		
		for (int i=0; i<dataList.size(); i++) {
			LetterCubeDomainObject domain = new LetterCubeDomainObject(dataList.get(i));
			domainObjectList.add(domain);
		}

		return domainObjectList;
	}
	

	public static GameTypeResponse.LetterCube MapToResponse(LetterCubeDomainObject domain){
		GameTypeResponse.LetterCube response = new GameTypeResponse.LetterCube(domain.diceNumber, domain.side1, domain.side2, domain.side3, domain.side4, domain.side5, domain.side6);
		return response;
	}


	public static ArrayList<GameTypeResponse.LetterCube> MapToResponseList(ArrayList<LetterCubeDomainObject> domain){
		if (domain == null)
			return null;

		ArrayList<GameTypeResponse.LetterCube> responseList = new ArrayList<GameTypeResponse.LetterCube>();
		for (int i=0; i< domain.size(); i++) {
			responseList.add(MapToResponse(domain.get(i)));
		}	
		return responseList;
	}


}
