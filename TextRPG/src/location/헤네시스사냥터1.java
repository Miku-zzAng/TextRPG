package location;

import main.*;
import monster.*;

public class 헤네시스사냥터1 extends Location {
	public 헤네시스사냥터1(Game game) {
		super("헤네시스 사냥터 1", game, "헤사1.jpg", "wav/mapwav/헤사브금.wav");
		this.addMonster(new 파란달팽이(game), 0.2);
		this.addMonster(new 스포아(game), 0.2);
		this.addMonster(new 빨간달팽이(game), 0.2);
		this.addMonster(new 스텀프(game), 0.2);
		this.addMonster(new 슬라임(game), 0.2);
	}
}

