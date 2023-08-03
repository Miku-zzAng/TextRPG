package location;

import main.*;
import monster.*;

public class 지혜의숲 extends Location {
	public 지혜의숲(Game game) {
		super("지혜의숲", game, "지혜의숲.jpg", "wav/mapwav/엘리니아숲.wav");
		this.addMonster(new 슬라임(game), 0.6);
		this.addMonster(new 초록버섯(game), 0.4);
	}
}

