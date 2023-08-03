package location;

import main.*;
import monster.*;

public class 세갈래길 extends Location {
	public 세갈래길(Game game) {
		super("세갈래길", game,"세갈래길.jpg", "wav/mapwav/리스항구.wav");
		this.addMonster(new 달팽이(game), 0.1);
		this.addMonster(new 파란달팽이(game), 0.2);
		this.addMonster(new 스포아(game), 0.15);
		this.addMonster(new 스텀프(game), 0.15);
		this.addMonster(new 빨간달팽이(game), 0.2);
		this.addMonster(new 슬라임(game), 0.2);
	}
}