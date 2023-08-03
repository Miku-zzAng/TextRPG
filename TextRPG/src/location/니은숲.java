
package location;

import main.*;
import monster.*;

public class 니은숲 extends Location {
	public 니은숲(Game game) {
		super("니은숲", game, "니은숲.jpg", "wav/mapwav/니은숲.wav");
		this.addMonster(new 파란달팽이(game), 10);
		this.addMonster(new 스포아(game), 10);
		this.addMonster(new 빨간달팽이(game), 15);
		this.addMonster(new 돼지(game), 15);
		this.addMonster(new 리본돼지(game), 15);
		this.addMonster(new 파란버섯(game), 15);
		this.addMonster(new 주황버섯(game), 15);
		this.addMonster(new 아이언호그(game), 5);
	}
}
