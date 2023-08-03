package location;

import main.*;
import monster.*;

public class 리스항구외곽 extends Location {
	public 리스항구외곽(Game game) {
		super("리스항구 외곽", game,"리스항구외곽.jpg", "wav/mapwav/리스항구.wav");
		this.addMonster(new 달팽이(game), 0.7);
		this.addMonster(new 파란달팽이(game), 0.2);
		this.addMonster(new 스포아(game), 0.1);
	}
}