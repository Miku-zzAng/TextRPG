package location;

import main.*;
import monster.*;

public class 파란버섯의숲 extends Location {
	public 파란버섯의숲(Game game) {
		super("파란버섯의 숲", game,"파란버섯의숲.jpg", "wav/mapwav/헤네시스필드.wav");
		this.addMonster(new 파란버섯(game), 100);
	}
}