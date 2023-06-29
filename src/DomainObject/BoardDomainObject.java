package DomainObject;

import DataObject.BoardDataObject;
import restService.Response.GameResponse;

public class BoardDomainObject {

	public int id;
	public int gameId;
	public String g1;
	public String g2;
	public String g3;
	public String g4;
	public String g5;
	public String g6;
	public String g7;
	public String g8;
	public String g9;
	public String g10;
	public String g11;
	public String g12;
	public String g13;
	public String g14;
	public String g15;
	public String g16;


	public BoardDomainObject(BoardDataObject data) {
		this.id = data.id;
		this.gameId = data.gameId;
		this.g1 = data.g1;
		this.g2 = data.g2;
		this.g3 = data.g3;
		this.g4 = data.g4;
		this.g5 = data.g5;
		this.g6 = data.g6;
		this.g7 = data.g7;
		this.g8 = data.g8;
		this.g9 = data.g9;
		this.g10 = data.g10;
		this.g11 = data.g11;
		this.g12 = data.g12;
		this.g13 = data.g13;
		this.g14 = data.g14;
		this.g15 = data.g15;
		this.g16 = data.g16;
	}
	
	public static GameResponse.Board MapToResponse(BoardDomainObject domain){
		if (domain == null)
			return null;
	
		GameResponse.Board response = new GameResponse.Board(domain.g1, domain.g2, domain.g3, domain.g4, domain.g5, domain.g6, domain.g7, domain.g8, domain.g9, domain.g10, domain.g11, domain.g12, domain.g13, domain.g14, domain.g15, domain.g16);
		return response;
	}


}