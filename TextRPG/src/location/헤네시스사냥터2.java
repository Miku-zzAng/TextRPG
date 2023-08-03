package location;

import main.*;
import monster.*;

public class 헤네시스사냥터2 extends Location {
	public 헤네시스사냥터2(Game game) {
		super("헤네시스 사냥터 2", game, "헤사2.jpg", "wav/mapwav/헤사브금.wav");
		this.addMonster(new 슬라임(game), 0.15);
		this.addMonster(new 주황버섯(game), 0.33);
		this.addMonster(new 돼지(game), 0.22);
		this.addMonster(new 초록버섯(game), 0.2);
	}
}

