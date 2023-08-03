
package location;

import main.*;
import monster.*;

public class 남쪽숲나무던전 extends Location {
	public 남쪽숲나무던전(Game game) {
		super("남쪽숲나무던전", game, "남쪽숲나무던전.jpg", "wav/mapwav/엘리니아숲안쪽.wav");
		this.addMonster(new 슬라임(game), 1);
	}
}

