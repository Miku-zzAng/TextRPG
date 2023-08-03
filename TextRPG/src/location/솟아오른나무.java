package location;

import main.*;
import monster.*;

public class 솟아오른나무 extends Location {
	public 솟아오른나무(Game game) {
		super("솟아오른나무", game, "솟아오른나무.jpg", "wav/mapwav/엘리니아숲.wav");
		this.addMonster(new 슬라임(game), 0.8);
		this.addMonster(new 다크스텀프(game), 0.2);
	}
}

