package location;

import main.*;
import monster.*;

public class 해안가풀숲 extends Location {
	public 해안가풀숲(Game game) {
		super("해안가 풀숲", game,"해안가풀숲.jpg", "wav/mapwav/리스항구.wav");
		this.addMonster(new 달팽이(game), 0.2);
		this.addMonster(new 파란달팽이(game), 0.25);
		this.addMonster(new 스포아(game), 0.2);
		this.addMonster(new 스텀프(game), 0.25);
		this.addMonster(new 빨간달팽이(game), 0.1);
	}
}