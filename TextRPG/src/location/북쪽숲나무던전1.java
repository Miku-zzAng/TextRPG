package location;

import main.*;
import monster.*;

public class 북쪽숲나무던전1 extends Location {
	public 북쪽숲나무던전1(Game game) {
		super("북쪽숲나무던전1", game, "북쪽숲나무던전1.jpg", "wav/mapwav/엘리니아숲안쪽.wav");
		this.addMonster(new 초록버섯(game), 0.5);
		this.addMonster(new 뿔버섯(game), 0.5);
	}
}
