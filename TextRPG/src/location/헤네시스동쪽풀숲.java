package location;

import main.*;
import monster.*;

public class 헤네시스동쪽풀숲 extends Location {
	public 헤네시스동쪽풀숲(Game game) {
		super("헤네시스 동쪽풀숲", game,"헤네시스동쪽풀숲.jpg", "wav/mapwav/헤네시스필드.wav");
		this.addMonster(new 돼지(game), 0.5);
		this.addMonster(new 리본돼지(game), 0.5);
	}
}
