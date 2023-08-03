package location;

import main.*;
import monster.*;

public class 북쪽숲나무던전2 extends Location {
	public 북쪽숲나무던전2(Game game) {
		super("북쪽숲나무던전2", game, "북쪽숲나무던전2.jpg", "wav/mapwav/엘리니아숲안쪽.wav");
		this.addMonster(new 커즈아이(game), 1);
	}
}

